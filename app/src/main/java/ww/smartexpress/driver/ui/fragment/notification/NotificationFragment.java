package ww.smartexpress.driver.ui.fragment.notification;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.helpers.EmptyViewHelper;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ww.smartexpress.driver.BR;
import ww.smartexpress.driver.R;
import ww.smartexpress.driver.constant.Constants;
import ww.smartexpress.driver.data.model.api.response.Notification;
import ww.smartexpress.driver.data.model.api.response.Notification;
import ww.smartexpress.driver.data.model.api.response.NotificationResponse;
import ww.smartexpress.driver.data.model.api.response.WalletTransaction;
import ww.smartexpress.driver.databinding.FragmentNotificationBinding;
import ww.smartexpress.driver.di.component.FragmentComponent;
import ww.smartexpress.driver.ui.base.fragment.BaseFragment;
import ww.smartexpress.driver.ui.fragment.notification.adapter.NotificationAdapter;
import ww.smartexpress.driver.ui.notification.details.NotificationDetailsActivity;
import ww.smartexpress.driver.ui.payout.details.PayoutDetailsActivity;
import ww.smartexpress.driver.ui.payout.details.PayoutDetailsViewModel;
import ww.smartexpress.driver.ui.view.ProgressItem;
import ww.smartexpress.driver.ui.wallet.transaction.details.TransactionDetailsActivity;
import ww.smartexpress.driver.ui.wallet.transaction.item.TransactionItem;

public class NotificationFragment extends BaseFragment<FragmentNotificationBinding, NotificationFragmentViewModel>
        implements FlexibleAdapter.EndlessScrollListener, EmptyViewHelper.OnEmptyViewListener, FlexibleAdapter.OnItemClickListener{


//    public FlexibleAdapter mFlexibleAdapter;
    @Override
    public int getBindingVariable() {
        return BR.vm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_notification;
    }

    @Override
    protected void performDataBinding() {
    }

    @Override
    protected void performDependencyInjection(FragmentComponent buildComponent) {
        buildComponent.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getMyNotification();
        binding.swRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.pageNumber.set(0);
                getMyNotification();
                binding.swRefresh.setRefreshing(false);
            }
        });
        binding.readAll.setOnClickListener(view1 -> {
            readAllNotification();
        });
    }

    @Override
    public void noMoreLoad(int newItemsSize) {

    }

    @Override
    public void onLoadMore(int lastPosition, int currentPage) {
        new Handler().postDelayed(new Runnable() {
            @SuppressWarnings("unchecked")
            @Override
            public void run() {

                viewModel.pageNumber.set(viewModel.pageNumber.get()+1);
                getMyNotification();
            }
        }, 1500);
    }

    @Override
    public boolean onItemClick(View view, int position) {
        NotificationResponse notificationResponse = (NotificationResponse) viewModel.mFlexibleAdapter.get().getItem(position);
        Log.d("TAG", "onItemClick: "+notificationResponse.getMsg());
        Intent intent;
        switch (notificationResponse.getKind()){
            case Constants.NOTIFICATION_KIND_PROMOTION:
            case Constants.NOTIFICATION_KIND_WARNING:
            case Constants.NOTIFICATION_KIND_SYSTEM:
                intent = new Intent(getContext(), NotificationDetailsActivity.class);
                intent.putExtra("messageNoti", notificationResponse.getMsg());
                intent.putExtra("notificationId",notificationResponse.getId());
                startActivity(intent);
                if(notificationResponse.getState() == 0){
                    notificationResponse.setState(1);
                    viewModel.mFlexibleAdapter.get().updateItem(notificationResponse);
                    viewModel.totalUnread.set(viewModel.totalUnread.get()-1);
                }
                break;

            case Constants.NOTIFICATION_KIND_DEPOSIT_SUCCESSFULLY:
                intent = new Intent(getContext(), TransactionDetailsActivity.class);
                intent.putExtra("transactionId",Long.valueOf(notificationResponse.getRefId()));
                startActivity(intent);
                if(notificationResponse.getState() == 0){
                    notificationResponse.setState(1);
                    viewModel.mFlexibleAdapter.get().updateItem(notificationResponse);
                    viewModel.totalUnread.set(viewModel.totalUnread.get()-1);
                }
                break;
            case Constants.NOTIFICATION_KIND_APPROVE_PAYOUT:
            case Constants.NOTIFICATION_KIND_REJECT_PAYOUT:
                intent = new Intent(getContext(), PayoutDetailsActivity.class);
                intent.putExtra("payoutId",Long.valueOf(notificationResponse.getRefId()));
                startActivity(intent);
                if(notificationResponse.getState() == 0){
                    notificationResponse.setState(1);
                    viewModel.mFlexibleAdapter.get().updateItem(notificationResponse);
                    viewModel.totalUnread.set(viewModel.totalUnread.get()-1);
                }
                break;
            default:
                break;
        }

        return false;
    }

    @Override
    public void onUpdateEmptyDataView(int size) {
        if(size>0){
            binding.layoutEmpty.emptyView.setVisibility(View.GONE);
        }else {
            binding.layoutEmpty.emptyView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onUpdateEmptyFilterView(int size) {

    }

    private void getMyNotification(){
        if (viewModel.pageNumber.get() == 0){
            viewModel.showLoading();
        }
        viewModel.compositeDisposable.add(viewModel.getMyNotification()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    if(response.isResult()){
                        viewModel.totalUnread.set(Math.toIntExact(response.getData().getTotalUnread()));
                        viewModel.pageTotal.set(response.getData().getTotalPages());
                        viewModel.totalElement.set(Math.toIntExact(response.getData().getTotalElements()));
                        if (response.getData().getContent()!=null){
                            if(viewModel.pageNumber.get() == 0){

                                viewModel.mFlexibleAdapter.set(new FlexibleAdapter(response.getData().getContent(), this));

                                if(viewModel.totalElement.get()> viewModel.pageSize.get()){
                                    viewModel.mFlexibleAdapter.get()
                                            .setLoadingMoreAtStartUp(false)
                                            .setEndlessScrollListener(this, new ProgressItem())
                                            .setEndlessScrollThreshold(1)
                                            .setEndlessTargetCount(viewModel.totalElement.get())
                                            .setEndlessPageSize(viewModel.pageSize.get());
                                }
                                EmptyViewHelper.create(viewModel.mFlexibleAdapter.get(), binding.layoutEmpty.emptyView, null, this);

                                binding.rcNotification.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
                                binding.rcNotification.setAdapter(viewModel.mFlexibleAdapter.get());
                            }else {
                                viewModel.mFlexibleAdapter.get().onLoadMoreComplete(response.getData().getContent());
                            }
                        }
                    }else {
                        viewModel.showErrorMessage(response.getMessage());
                    }
                    viewModel.hideLoading();
                },error->{
                    viewModel.showErrorMessage(getActivity().getString(R.string.newtwork_error));
                    error.printStackTrace();
                    viewModel.hideLoading();
                })
        );
    }

    public void readAllNotification(){
        Log.d("TAG", "readAllNotification: ");
        viewModel.compositeDisposable.add(viewModel.readAllNotification()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    viewModel.pageNumber.set(0);
                    getMyNotification();
                },error->{
                    error.printStackTrace();
                })
        );
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
