package jane.entity;

import jane.entity.enums.CarColorEnum;
import jane.entity.enums.CarStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cars", schema = "public")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    @Enumerated(EnumType.STRING)
    private CarColorEnum color;

    @Column(name = "seat_amount")
    private Integer seatAmount;

    @Column(name = "price_per_day")
    private Integer pricePerDay;

    @Enumerated(EnumType.STRING)
    private CarStatusEnum status;

    @OneToOne(mappedBy = "car", cascade = CascadeType.ALL)
    private Booking booking;

}
