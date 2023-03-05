package jane.entity;

import jane.entity.enums.CarColorEnum;
import jane.entity.enums.CarStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class Car {
    @Id
    private Integer id;
    private String brand;
    private String model;
    private CarColorEnum color;
    private Integer seatAmount;
    private Integer price;
    private CarStatusEnum status;
    private String image;
}
