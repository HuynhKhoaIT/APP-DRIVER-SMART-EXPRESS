package ww.smartexpress.driver.di.module;

import android.content.Context;

import androidx.core.util.Supplier;
import androidx.lifecycle.ViewModelProvider;

import ww.smartexpress.driver.MVVMApplication;
import ww.smartexpress.driver.ViewModelProviderFactory;
import ww.smartexpress.driver.data.Repository;
import ww.smartexpress.driver.data.model.api.response.BookingDetails;
import ww.smartexpress.driver.di.scope.ActivityScope;
import ww.smartexpress.driver.ui.achievement.AchievementViewModel;
import ww.smartexpress.driver.ui.allbike.AllBikeServiceViewModel;
import ww.smartexpress.driver.ui.auth.AuthViewModel;
import ww.smartexpress.driver.ui.await.AwaitViewModel;
import ww.smartexpress.driver.ui.award.AwardViewModel;
import ww.smartexpress.driver.ui.bank.BankViewModel;
import ww.smartexpress.driver.ui.base.activity.BaseActivity;
import ww.smartexpress.driver.ui.booking.details.BookingDetailsViewModel;
import ww.smartexpress.driver.ui.chat.ChatViewModel;
import ww.smartexpress.driver.ui.cod.CodViewModel;
import ww.smartexpress.driver.ui.deposit.DepositViewModel;
import ww.smartexpress.driver.ui.fragment.income.details.IncomeDetailsViewModel;
import ww.smartexpress.driver.ui.history.TripHistoryViewModel;
import ww.smartexpress.driver.ui.home.HomeViewModel;
import ww.smartexpress.driver.ui.index.IndexViewModel;
import ww.smartexpress.driver.ui.login.LoginViewModel;
import ww.smartexpress.driver.ui.main.MainViewModel;
import ww.smartexpress.driver.ui.notification.details.NotificationDetailsViewModel;
import ww.smartexpress.driver.ui.password.forget.ForgetPasswordViewModel;
import ww.smartexpress.driver.ui.password.forget.ResetForgetPasswordViewModel;
import ww.smartexpress.driver.ui.password.otp.ForgetPasswordOTPViewModel;
import ww.smartexpress.driver.ui.password.otp.VerifyForgetPasswordOTPViewModel;
import ww.smartexpress.driver.ui.password.renew.RenewPasswordViewModel;
import ww.smartexpress.driver.ui.payout.PayoutViewModel;
import ww.smartexpress.driver.ui.payout.details.PayoutDetailsViewModel;
import ww.smartexpress.driver.ui.profile.ProfileViewModel;
import ww.smartexpress.driver.ui.qrcode.QrcodeViewModel;
import ww.smartexpress.driver.ui.shipping.ShippingViewModel;
import ww.smartexpress.driver.ui.signup.SignUpViewModel;
import ww.smartexpress.driver.ui.splash.SplashViewModel;
import ww.smartexpress.driver.ui.wallet.WalletViewModel;
import ww.smartexpress.driver.ui.wallet.transaction.TransactionViewModel;
import ww.smartexpress.driver.ui.wallet.transaction.details.TransactionDetailsViewModel;
import ww.smartexpress.driver.ui.welcome.WelcomeViewModel;
import ww.smartexpress.driver.ui.profile.edit.EditProfileViewModel;
import ww.smartexpress.driver.ui.statistic.StatisticViewModel;
import ww.smartexpress.driver.utils.GetInfo;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private final BaseActivity<?, ?> activity;

    public ActivityModule(BaseActivity<?, ?> activity) {
        this.activity = activity;
    }

    @Named("access_token")
    @Provides
    @ActivityScope
    String provideToken(Repository repository) {
        return repository.getToken();
    }

    @Named("device_id")
    @Provides
    @ActivityScope
    String provideDeviceId(Context applicationContext) {
        return GetInfo.getAll(applicationContext);
    }


    @Provides
    @ActivityScope
    MainViewModel provideMainViewModel(Repository repository, Context application) {
        Supplier<MainViewModel> supplier = () -> new MainViewModel(repository, (MVVMApplication) application);
        ViewModelProviderFactory<MainViewModel> factory = new ViewModelProviderFactory<>(MainViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(MainViewModel.class);
    }

    @Provides
    @ActivityScope
    ProfileViewModel provideProfileViewModel(Repository repository, Context application) {
        Supplier<ProfileViewModel> supplier = () -> new ProfileViewModel(repository, (MVVMApplication) application);
        ViewModelProviderFactory<ProfileViewModel> factory = new ViewModelProviderFactory<>(ProfileViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(ProfileViewModel.class);
    }
    @Provides
    @ActivityScope
    EditProfileViewModel provideEditProfileViewModel(Repository repository, Context application) {
        Supplier<EditProfileViewModel> supplier = () -> new EditProfileViewModel(repository, (MVVMApplication) application);
        ViewModelProviderFactory<EditProfileViewModel> factory = new ViewModelProviderFactory<>(EditProfileViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(EditProfileViewModel.class);
    }
    @Provides
    @ActivityScope
    AllBikeServiceViewModel provideAllBikeServiceViewModel(Repository repository, Context application) {
        Supplier<AllBikeServiceViewModel> supplier = () -> new AllBikeServiceViewModel(repository, (MVVMApplication) application);
        ViewModelProviderFactory<AllBikeServiceViewModel> factory = new ViewModelProviderFactory<>(AllBikeServiceViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(AllBikeServiceViewModel.class);
    }

    @Provides
    @ActivityScope
    StatisticViewModel provideStatisticViewModel(Repository repository, Context application) {
        Supplier<StatisticViewModel> supplier = () -> new StatisticViewModel(repository, (MVVMApplication) application);
        ViewModelProviderFactory<StatisticViewModel> factory = new ViewModelProviderFactory<>(StatisticViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(StatisticViewModel.class);
    }

    @Provides
    @ActivityScope
    SplashViewModel provideSplashViewModel(Repository repository, Context application) {
        Supplier<SplashViewModel> supplier = () -> new SplashViewModel(repository, (MVVMApplication) application);
        ViewModelProviderFactory<SplashViewModel> factory = new ViewModelProviderFactory<>(SplashViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(SplashViewModel.class);
    }

    @Provides
    @ActivityScope
    WelcomeViewModel provideWelcomeViewModel(Repository repository, Context application) {
        Supplier<WelcomeViewModel> supplier = () -> new WelcomeViewModel(repository, (MVVMApplication) application);
        ViewModelProviderFactory<WelcomeViewModel> factory = new ViewModelProviderFactory<>(WelcomeViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(WelcomeViewModel.class);
    }

    @Provides
    @ActivityScope
    SignUpViewModel provideSignUpViewModel(Repository repository, Context application) {
        Supplier<SignUpViewModel> supplier = () -> new SignUpViewModel(repository, (MVVMApplication) application);
        ViewModelProviderFactory<SignUpViewModel> factory = new ViewModelProviderFactory<>(SignUpViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(SignUpViewModel.class);
    }

    @Provides
    @ActivityScope
    HomeViewModel provideHomeViewModel(Repository repository, Context application) {
        Supplier<HomeViewModel> supplier = () -> new HomeViewModel(repository, (MVVMApplication) application);
        ViewModelProviderFactory<HomeViewModel> factory = new ViewModelProviderFactory<>(HomeViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(HomeViewModel.class);
    }

    @Provides
    @ActivityScope
    TripHistoryViewModel provideTripHistoryViewModel(Repository repository, Context application) {
        Supplier<TripHistoryViewModel> supplier = () -> new TripHistoryViewModel(repository, (MVVMApplication) application);
        ViewModelProviderFactory<TripHistoryViewModel> factory = new ViewModelProviderFactory<>(TripHistoryViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(TripHistoryViewModel.class);
    }

    @Provides
    @ActivityScope
    AchievementViewModel provideAchievementViewModel(Repository repository, Context application) {
        Supplier<AchievementViewModel> supplier = () -> new AchievementViewModel(repository, (MVVMApplication) application);
        ViewModelProviderFactory<AchievementViewModel> factory = new ViewModelProviderFactory<>(AchievementViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(AchievementViewModel.class);
    }

    @Provides
    @ActivityScope
    IncomeDetailsViewModel provideIncomeDetailsViewModel(Repository repository, Context application) {
        Supplier<IncomeDetailsViewModel> supplier = () -> new IncomeDetailsViewModel(repository, (MVVMApplication) application);
        ViewModelProviderFactory<IncomeDetailsViewModel> factory = new ViewModelProviderFactory<>(IncomeDetailsViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(IncomeDetailsViewModel.class);
    }

    @Provides
    @ActivityScope
    AwardViewModel provideAwardViewModel(Repository repository, Context application) {
        Supplier<AwardViewModel> supplier = () -> new AwardViewModel(repository, (MVVMApplication) application);
        ViewModelProviderFactory<AwardViewModel> factory = new ViewModelProviderFactory<>(AwardViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(AwardViewModel.class);
    }

    @Provides
    @ActivityScope
    LoginViewModel provideLoginViewModel(Repository repository, Context application) {
        Supplier<LoginViewModel> supplier = () -> new LoginViewModel(repository, (MVVMApplication) application);
        ViewModelProviderFactory<LoginViewModel> factory = new ViewModelProviderFactory<>(LoginViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(LoginViewModel.class);
    }

    @Provides
    @ActivityScope
    ForgetPasswordViewModel provideForgetPasswordViewModel(Repository repository, Context application) {
        Supplier<ForgetPasswordViewModel> supplier = () -> new ForgetPasswordViewModel(repository, (MVVMApplication) application);
        ViewModelProviderFactory<ForgetPasswordViewModel> factory = new ViewModelProviderFactory<>(ForgetPasswordViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(ForgetPasswordViewModel.class);
    }

    @Provides
    @ActivityScope
    ForgetPasswordOTPViewModel provideForgetPasswordOTPViewModel(Repository repository, Context application) {
        Supplier<ForgetPasswordOTPViewModel> supplier = () -> new ForgetPasswordOTPViewModel(repository, (MVVMApplication) application);
        ViewModelProviderFactory<ForgetPasswordOTPViewModel> factory = new ViewModelProviderFactory<>(ForgetPasswordOTPViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(ForgetPasswordOTPViewModel.class);
    }

    @Provides
    @ActivityScope
    RenewPasswordViewModel provideRenewPasswordViewModel(Repository repository, Context application) {
        Supplier<RenewPasswordViewModel> supplier = () -> new RenewPasswordViewModel(repository, (MVVMApplication) application);
        ViewModelProviderFactory<RenewPasswordViewModel> factory = new ViewModelProviderFactory<>(RenewPasswordViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(RenewPasswordViewModel.class);
    }

    @Provides
    @ActivityScope
    IndexViewModel provideIndexViewModel(Repository repository, Context application) {
        Supplier<IndexViewModel> supplier = () -> new IndexViewModel(repository, (MVVMApplication) application);
        ViewModelProviderFactory<IndexViewModel> factory = new ViewModelProviderFactory<>(IndexViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(IndexViewModel.class);
    }

    @Provides
    @ActivityScope
    ChatViewModel provideChatViewModel(Repository repository, Context application) {
        Supplier<ChatViewModel> supplier = () -> new ChatViewModel(repository, (MVVMApplication) application);
        ViewModelProviderFactory<ChatViewModel> factory = new ViewModelProviderFactory<>(ChatViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(ChatViewModel.class);
    }

    @Provides
    @ActivityScope
    BookingDetailsViewModel provideBookingDetailsViewModel(Repository repository, Context application) {
        Supplier<BookingDetailsViewModel> supplier = () -> new BookingDetailsViewModel(repository, (MVVMApplication) application);
        ViewModelProviderFactory<BookingDetailsViewModel> factory = new ViewModelProviderFactory<>(BookingDetailsViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(BookingDetailsViewModel.class);
    }

    @Provides
    @ActivityScope
    ShippingViewModel provideShippingViewModel(Repository repository, Context application) {
        Supplier<ShippingViewModel> supplier = () -> new ShippingViewModel(repository, (MVVMApplication) application);
        ViewModelProviderFactory<ShippingViewModel> factory = new ViewModelProviderFactory<>(ShippingViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(ShippingViewModel.class);
    }

    @Provides
    @ActivityScope
    QrcodeViewModel provideQrcodeViewModel(Repository repository, Context application) {
        Supplier<QrcodeViewModel> supplier = () -> new QrcodeViewModel(repository, (MVVMApplication) application);
        ViewModelProviderFactory<QrcodeViewModel> factory = new ViewModelProviderFactory<>(QrcodeViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(QrcodeViewModel.class);
    }

    @Provides
    @ActivityScope
    WalletViewModel provideWalletViewModel(Repository repository, Context application) {
        Supplier<WalletViewModel> supplier = () -> new WalletViewModel(repository, (MVVMApplication) application);
        ViewModelProviderFactory<WalletViewModel> factory = new ViewModelProviderFactory<>(WalletViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(WalletViewModel.class);
    }

    @Provides
    @ActivityScope
    DepositViewModel provideDepositViewModel(Repository repository, Context application) {
        Supplier<DepositViewModel> supplier = () -> new DepositViewModel(repository, (MVVMApplication) application);
        ViewModelProviderFactory<DepositViewModel> factory = new ViewModelProviderFactory<>(DepositViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(DepositViewModel.class);
    }

    @Provides
    @ActivityScope
    PayoutViewModel providePayoutViewModel(Repository repository, Context application) {
        Supplier<PayoutViewModel> supplier = () -> new PayoutViewModel(repository, (MVVMApplication) application);
        ViewModelProviderFactory<PayoutViewModel> factory = new ViewModelProviderFactory<>(PayoutViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(PayoutViewModel.class);
    }

    @Provides
    @ActivityScope
    BankViewModel provideBankViewModel(Repository repository, Context application) {
        Supplier<BankViewModel> supplier = () -> new BankViewModel(repository, (MVVMApplication) application);
        ViewModelProviderFactory<BankViewModel> factory = new ViewModelProviderFactory<>(BankViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(BankViewModel.class);
    }

    @Provides
    @ActivityScope
    TransactionViewModel provideTransactionViewModel(Repository repository, Context application) {
        Supplier<TransactionViewModel> supplier = () -> new TransactionViewModel(repository, (MVVMApplication) application);
        ViewModelProviderFactory<TransactionViewModel> factory = new ViewModelProviderFactory<>(TransactionViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(TransactionViewModel.class);
    }

    @Provides
    @ActivityScope
    AuthViewModel provideAuthViewModel(Repository repository, Context application) {
        Supplier<AuthViewModel> supplier = () -> new AuthViewModel(repository, (MVVMApplication) application);
        ViewModelProviderFactory<AuthViewModel> factory = new ViewModelProviderFactory<>(AuthViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(AuthViewModel.class);
    }

    @Provides
    @ActivityScope
    CodViewModel provideCodViewModel(Repository repository, Context application) {
        Supplier<CodViewModel> supplier = () -> new CodViewModel(repository, (MVVMApplication) application);
        ViewModelProviderFactory<CodViewModel> factory = new ViewModelProviderFactory<>(CodViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(CodViewModel.class);
    }

    @Provides
    @ActivityScope
    NotificationDetailsViewModel provideNotificationDetailsViewModel(Repository repository, Context application) {
        Supplier<NotificationDetailsViewModel> supplier = () -> new NotificationDetailsViewModel(repository, (MVVMApplication) application);
        ViewModelProviderFactory<NotificationDetailsViewModel> factory = new ViewModelProviderFactory<>(NotificationDetailsViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(NotificationDetailsViewModel.class);
    }
    @Provides
    @ActivityScope
    VerifyForgetPasswordOTPViewModel provideVerifyForgetPasswordOTPViewModel(Repository repository, Context application) {
        Supplier<VerifyForgetPasswordOTPViewModel> supplier = () -> new VerifyForgetPasswordOTPViewModel(repository, (MVVMApplication) application);
        ViewModelProviderFactory<VerifyForgetPasswordOTPViewModel> factory = new ViewModelProviderFactory<>(VerifyForgetPasswordOTPViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(VerifyForgetPasswordOTPViewModel.class);
    }

    @Provides
    @ActivityScope
    ResetForgetPasswordViewModel provideResetForgetPasswordViewModel(Repository repository, Context application) {
        Supplier<ResetForgetPasswordViewModel> supplier = () -> new ResetForgetPasswordViewModel(repository, (MVVMApplication) application);
        ViewModelProviderFactory<ResetForgetPasswordViewModel> factory = new ViewModelProviderFactory<>(ResetForgetPasswordViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(ResetForgetPasswordViewModel.class);
    }

    @Provides
    @ActivityScope
    AwaitViewModel provideAwaitViewModel(Repository repository, Context application) {
        Supplier<AwaitViewModel> supplier = () -> new AwaitViewModel(repository, (MVVMApplication) application);
        ViewModelProviderFactory<AwaitViewModel> factory = new ViewModelProviderFactory<>(AwaitViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(AwaitViewModel.class);
    }

    @Provides
    @ActivityScope
    TransactionDetailsViewModel provideTransactionDetailsViewModel(Repository repository, Context application) {
        Supplier<TransactionDetailsViewModel> supplier = () -> new TransactionDetailsViewModel(repository, (MVVMApplication) application);
        ViewModelProviderFactory<TransactionDetailsViewModel> factory = new ViewModelProviderFactory<>(TransactionDetailsViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(TransactionDetailsViewModel.class);
    }

    @Provides
    @ActivityScope
    PayoutDetailsViewModel providePayoutDetailsViewModel(Repository repository, Context application) {
        Supplier<PayoutDetailsViewModel> supplier = () -> new PayoutDetailsViewModel(repository, (MVVMApplication) application);
        ViewModelProviderFactory<PayoutDetailsViewModel> factory = new ViewModelProviderFactory<>(PayoutDetailsViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(PayoutDetailsViewModel.class);
    }
}
