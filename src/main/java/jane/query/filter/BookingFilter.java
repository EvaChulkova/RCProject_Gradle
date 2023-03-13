package jane.query.filter;

import jane.entity.enums.BookingStatusEnum;
import jane.entity.enums.PaymentStateEnum;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class BookingFilter {
    LocalDate rentalStart;
    LocalDate rentalFinish;
    BookingStatusEnum status;
    PaymentStateEnum paymentState;
}
