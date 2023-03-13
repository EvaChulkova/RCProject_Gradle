package jane.entity;

import jane.entity.enums.BookingStatusEnum;
import jane.entity.enums.PaymentStateEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.FetchProfile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;
import java.time.LocalDate;

@NamedEntityGraph(
        name = "withClientAndCarEG",
        attributeNodes = {
                @NamedAttributeNode("client"),
                @NamedAttributeNode("car")
        }
)

@FetchProfile(name = "withClientAndCar", fetchOverrides = {
        @FetchProfile.FetchOverride(
                entity = Booking.class, association = "client", mode = FetchMode.JOIN),
        @FetchProfile.FetchOverride(
                entity = Booking.class, association = "car", mode = FetchMode.JOIN)
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"client", "car"})
@Builder
@Entity
@Table(name = "bookings", schema = "public")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Car car;

    @Column(name = "rental_start")
    private LocalDate rentalStart;

    @Column(name = "rental_finish")
    private LocalDate rentalFinish;

    @Enumerated(EnumType.STRING)
    private BookingStatusEnum status;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_state")
    private PaymentStateEnum paymentState;

    private String comment;

    public void addClient(Client client){
        this.client = client;
        this.client.getBookings().add(this);
    }

    public void addCar(Car car) {
        this.car = car;
        this.car.getBookings().add(this);
    }
}
