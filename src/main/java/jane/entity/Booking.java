package jane.entity;

import jane.entity.enums.BookingStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class Booking {
    @Id
    private Integer id;
    //private Client client;
    //private Car car;
    private LocalDateTime rentalStart;
    private LocalDateTime rentalFinish;
    private BookingStatusEnum status;
    private String comment;

}
