package jane;

import jane.entity.Car;
import jane.entity.User;
import jane.entity.enums.CarColorEnum;
import jane.entity.enums.CarStatusEnum;
import jane.entity.enums.RoleEnum;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.SQLException;

public class AppRunner {
    public static void main(String[] args) throws SQLException {

        Configuration configuration = new Configuration();
        configuration.configure();

        //createUser(configuration);
        createCar(configuration);
    }

    private static void createCar (Configuration configuration) {
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Car car = Car.builder()
                    .brand("Audi")
                    .model("RS6")
                    .color(CarColorEnum.BLACK)
                    .seatAmount(5)
                    .pricePerDay(17600)
                    .status(CarStatusEnum.AVAILABLE)
                    .build();
            session.save(car);

            session.getTransaction().commit();
        }
    }

    private static void createUser(Configuration configuration) {
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            User user = User.builder()
                    .firstName("Petr")
                    .lastName("Petrov")
                    .login("petrov@gmail.com")
                    .password("123")
                    .role(RoleEnum.CLIENT)
                    .build();
            session.save(user);

            session.getTransaction().commit();
        }
    }
}