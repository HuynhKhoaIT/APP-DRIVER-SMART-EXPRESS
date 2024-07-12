package ww.smartexpress.driver.data.model.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomeRequest {
    private int bookingState;
    private String startDate;
    private String endDate;
}
