package jane;

import jane.entity.Booking;
import jane.entity.Car;
import jane.entity.Client;
import jane.entity.User;
import jane.entity.enums.BookingStatusEnum;
import jane.entity.enums.CarColorEnum;
import jane.entity.enums.CarStatusEnum;
import jane.entity.enums.RoleEnum;
import jane.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class AppRunnerTest {

    @Test
    void createBooking() {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Client client = session.get(Client.class, 1L);
            Car car = session.get(Car.class, 1L);

            Booking booking = Booking.builder()
                    .client(client)
                    .car(car)
                    .rentalStart(LocalDate.of(2023, 3, 7))
                    .rentalFinish(LocalDate.of(2023, 3, 9))
                    .status(BookingStatusEnum.APPROVED)
                    .build();
            session.save(booking);

            session.getTransaction().commit();
        }
    }

    @Test
    void createCar() {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Car car = Car.builder()
                    .brand("Renault")
                    .model("Logan")
                    .color(CarColorEnum.WHITE)
                    .seatAmount(4)
                    .pricePerDay(1400)
                    .status(CarStatusEnum.AVAILABLE)
                    .build();
            session.save(car);

            session.getTransaction().commit();
        }
    }

    @Test
    void createUserAndClient() {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            User user = User.builder()
                    .firstName("Oxana")
                    .lastName("Makarova")
                    .login("oxana@gmail.com")
                    .password("123")
                    .role(RoleEnum.CLIENT)
                    .build();

            Client client = Client.builder()
                    .birthDate(LocalDate.of(1974, 8,1))
                    .drivingLicenceNo(1156)
                    .validity(LocalDate.of(2028, 1, 6))
                    .build();
            session.save(user);
            client.setUser(user);
            session.save(client);

            session.getTransaction().commit();
        }
    }

    @Test
    void createUser() {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            User userAdmin = User.builder()
                    .firstName("Alex")
                    .lastName("Alekseev")
                    .login("alexAdmin@gmail.com")
                    .password("123")
                    .role(RoleEnum.ADMIN)
                    .build();
            session.save(userAdmin);

            session.getTransaction().commit();
        }
    }
}