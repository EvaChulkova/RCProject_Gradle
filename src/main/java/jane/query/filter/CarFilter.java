package jane.query.filter;

import jane.entity.enums.CarColorEnum;
import jane.entity.enums.CarStatusEnum;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CarFilter {
    String brand;
    String model;
    CarColorEnum color;
    Integer pricePerDay;
    CarStatusEnum status;
}
