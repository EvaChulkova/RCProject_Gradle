package jane.dao;

import jane.entity.Booking;
import jane.entity.Car;
import jane.entity.Client;
import jane.entity.User;
import jane.entity.enums.BookingStatusEnum;
import jane.entity.enums.CarColorEnum;
import jane.entity.enums.CarStatusEnum;
import jane.entity.enums.PaymentStateEnum;
import jane.entity.enums.RoleEnum;
import jane.util.HibernateTestUtil;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BookingDaoIT {
    private final SessionFactory sessionFactory = HibernateTestUtil.buildSessionFactory();

    @Test
    void findAll() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        User user = createUser();
        session.save(user);

        Client client = createClient();
        client.setUser(user);
        session.save(client);

        Car car = createCar();
        session.save(car);

        Booking booking = creteBooking();
        booking.setClient(client);
        booking.setCar(car);
        session.save(booking);

        List<Booking> actualResult = session.createQuery("select b from Booking b", Booking.class)
                .list();
        assertThat(actualResult).containsExactlyInAnyOrder(booking);

        session.getTransaction().commit();
    }

    @Test
    void findById() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        User user = createUser();
        session.save(user);

        Client client = createClient();
        client.setUser(user);
        session.save(client);

        Car car = createCar();
        session.save(car);

        Booking booking = creteBooking();
        booking.setClient(client);
        booking.setCar(car);
        session.save(booking);

        Booking actualResult = session.get(Booking.class, booking.getId());
        assertThat(actualResult).isEqualTo(booking);

        session.getTransaction().commit();
    }

    @Test
    void delete() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        User user = createUser();
        session.save(user);

        Client client = createClient();
        client.setUser(user);
        session.save(client);

        Car car = createCar();
        session.save(car);

        Booking booking = creteBooking();
        booking.setClient(client);
        booking.setCar(car);
        session.save(booking);

        session.delete(booking);

        Booking actualResult = session.get(Booking.class, booking.getId());
        assertThat(actualResult).isNull();

        session.getTransaction().commit();
    }


    @Test
    void update() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        User user = createUser();
        session.save(user);

        Client client = createClient();
        client.setUser(user);
        session.save(client);

        Car car = createCar();
        session.save(car);

        Booking booking = creteBooking();
        booking.setClient(client);
        booking.setCar(car);
        session.save(booking);

        Booking updatedBooking = session.get(Booking.class, booking.getId());
        updatedBooking.setStatus(BookingStatusEnum.CANCELLED);
        session.save(updatedBooking);

        Booking actualResult = session.get(Booking.class, booking.getId());
        assertThat(actualResult).isEqualTo(updatedBooking);

        session.getTransaction().commit();
    }


    @Test
    void save() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        User user = createUser();
        session.save(user);

        Client client = createClient();
        client.setUser(user);
        session.save(client);

        Car car = createCar();
        session.save(car);

        Booking booking = creteBooking();
        booking.setClient(client);
        booking.setCar(car);
        session.save(booking);

        Booking actualResult = session.get(Booking.class, booking.getId());
        assertThat(actualResult).isNotNull();

        session.getTransaction().commit();
    }

    private static Booking creteBooking() {
        Booking booking = Booking.builder()
                .client(createClient())
                .car(createCar())
                .rentalStart(LocalDate.of(2023, 1,1))
                .rentalFinish(LocalDate.of(2023, 1, 10))
                .status(BookingStatusEnum.COMPLETED)
                .paymentState(PaymentStateEnum.PAID)
                .comment("Nice to meet you again!")
                .build();

        return booking;
    }

    private static Car createCar() {
        Car car = Car.builder()
                .brand("Audi")
                .model("A5")
                .color(CarColorEnum.BLACK)
                .seatAmount(4)
                .pricePerDay(3100)
                .status(CarStatusEnum.AVAILABLE)
                .image("AudiA5Image")
                .build();

        return car;
    }

    private static Client createClient() {
        Client client = Client.builder()
                .user(createUser())
                .birthDate(LocalDate.of(2000, 1, 1))
                .drivingLicenceNo(123123)
                .validity(LocalDate.of(2040,1,1))
                .build();

        return client;
    }

    private static User createUser() {
        User user = User.builder()
                .firstName("Anna")
                .lastName("Petrova")
                .login("anna@gmail.com")
                .password("123")
                .role(RoleEnum.CLIENT)
                .build();

        return user;
    }

}
