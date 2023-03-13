package jane.query.filter;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class ClientFilter {
    Long id;
    LocalDate birthDate;
    Integer drivingLicenceNo;
    LocalDate validity;
}
