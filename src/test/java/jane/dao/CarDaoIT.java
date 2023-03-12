package jane.dao;

import jane.entity.Car;
import jane.entity.enums.CarColorEnum;
import jane.entity.enums.CarStatusEnum;
import jane.util.HibernateTestUtil;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CarDaoIT {
    private final SessionFactory sessionFactory = HibernateTestUtil.buildSessionFactory();

    @Test
    void findAll() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Car car = createCar();
        Car anotherCar = createAnotherCar();
        session.save(car);
        session.save(anotherCar);

        List<Car> actualResult = session.createQuery("select c from Car c", Car.class)
                .list();

        assertThat(actualResult).containsExactlyInAnyOrder(car, anotherCar);

        session.getTransaction().commit();
    }

    @Test
    void findById() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Car car = createCar();
        session.save(car);

        Car actualCar = session.get(Car.class, car.getId());
        assertThat(actualCar).isEqualTo(car);

        session.getTransaction().commit();
    }

    @Test
    void delete() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Car car = createCar();
        session.save(car);

        session.delete(car);

        Car actualCar = session.get(Car.class, car.getId());
        assertThat(actualCar).isNull();

        session.getTransaction().commit();
    }

    @Test
    void save(){
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Car car = createCar();
        session.save(car);

        Car actualResult = session.get(Car.class, car.getId());
        assertThat(actualResult).isNotNull();

        session.getTransaction().commit();
    }

    @Test
    void update() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Car car = createCar();
        session.save(car);

        Car updatedCar = session.get(Car.class, car.getId());
        updatedCar.setColor(CarColorEnum.WHITE);
        session.save(updatedCar);

        Car actualResult = session.get(Car.class, car.getId());
        assertThat(actualResult).isEqualTo(updatedCar);

        session.getTransaction().commit();
    }


    private static Car createAnotherCar() {
        Car anotherCar = Car.builder()
                .brand("Audi")
                .model("A5")
                .color(CarColorEnum.BLACK)
                .seatAmount(4)
                .pricePerDay(3100)
                .status(CarStatusEnum.AVAILABLE)
                .image("AudiA5Image")
                .build();

        return anotherCar;
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
}
