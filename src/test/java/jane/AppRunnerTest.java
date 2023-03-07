package jane;

import jane.entity.Booking;
import jane.entity.Car;
import jane.entity.Client;
import jane.entity.User;
import jane.entity.enums.BookingStatusEnum;
import jane.entity.enums.CarColorEnum;
import jane.entity.enums.CarStatusEnum;
import jane.entity.enums.RoleEnum;
import jane.util.HibernateTestUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;


class AppRunnerTest {

    @Test
    void createBooking() {
        try (SessionFactory sessionFactory = HibernateTestUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            User user = buildUser();
            session.save(user);

            Client client = buildClient();
            client.setUser(user);
            session.save(client);

            Car car = buildCar();
            session.save(car);

            Booking expectedBooking = buildBooking();
            expectedBooking.setClient(client);
            expectedBooking.setCar(car);

            var booking = session.save(expectedBooking);
            session.evict(expectedBooking);

            Booking actualBooking = session.get(Booking.class, booking);
            assertThat(actualBooking.getId()).isEqualTo(expectedBooking.getId());


            session.getTransaction().commit();
        }
    }

    @Test
    void saveCar() {
        try (SessionFactory sessionFactory = HibernateTestUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Car expectedCar = buildCar();
            session.save(expectedCar);
            session.evict(expectedCar);

            Car actualCar = session.get(Car.class, expectedCar.getId());
            assertThat(actualCar.getId()).isEqualTo(expectedCar.getId());

            session.getTransaction().commit();
        }
    }

    @Test
    void saveClient() {
        try (SessionFactory sessionFactory = HibernateTestUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            User user = buildUser();
            Client expectedClient = buildClient();
            session.save(user);
            expectedClient.setUser(user);

            var client = session.save(expectedClient);
            session.evict(expectedClient);

            Client actualClient = session.get(Client.class, client);
            assertThat(actualClient.getId()).isEqualTo(expectedClient.getId());

            session.getTransaction().commit();
        }
    }

    @Test
    void saveUser() {
        try (SessionFactory sessionFactory = HibernateTestUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            var expectedUser = buildUser();

            session.save(expectedUser);
            session.evict(expectedUser);

            User actualUser = session.get(User.class, expectedUser.getId());
            assertThat(actualUser.getId()).isEqualTo(expectedUser.getId());

            session.getTransaction().commit();
        }
    }





    private Booking buildBooking() {
        return Booking.builder()
                .client(buildClient())
                .car(buildCar())
                .rentalStart(LocalDate.of(2023, 3, 10))
                .rentalFinish(LocalDate.of(2023, 3, 14))
                .status(BookingStatusEnum.APPROVED)
                .build();
    }

    private Car buildCar() {
        return Car.builder()
                .brand("Kia")
                .model("Rio")
                .color(CarColorEnum.RED)
                .seatAmount(4)
                .pricePerDay(2100)
                .status(CarStatusEnum.AVAILABLE)
                .build();
    }

    private Client buildClient() {
        return Client.builder()
                .birthDate(LocalDate.of(1996, 9,15))
                .drivingLicenceNo(1156)
                .validity(LocalDate.of(2031, 1, 6))
                .build();
    }

    private User buildUser() {
        return User.builder()
                .firstName("Jane")
                .lastName("Chulkova")
                .login("jane@gmail.com")
                .password("123")
                .role(RoleEnum.CLIENT)
                .build();
    }
}