package jane.query;

import jane.dao.CarDao;
import jane.entity.Car;
import jane.query.filter.CarFilter;
import jane.util.HibernateTestUtil;
import jane.util.TestDataImporter;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
public class CarDaoQueryIT {
    private final SessionFactory sessionFactory = HibernateTestUtil.buildSessionFactory();
    private final CarDao carDao = CarDao.getInstance();

    @BeforeAll
    public void initDB() {
        TestDataImporter.importData(sessionFactory);
    }

    @AfterAll
    public void finish() {
        sessionFactory.close();
    }

    @Test
    void findAllByBrand() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Car> results = carDao.findAllByBrand(session, "Audi");
        assertThat(results).hasSize(2);

        session.getTransaction().commit();
    }

    @Test
    void findAllByBrandAndModel() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        CarFilter carFilter = CarFilter.builder()
                .brand("Audi")
                .model("RS6")
                .build();

        List<Car> results = carDao.findAllByBrandAndModel(session, carFilter);
        assertThat(results).hasSize(1);

        List<String> brands = results.stream().map(Car::getBrand).toList();
        assertThat(brands).contains(carFilter.getBrand());

        List<String> models = results.stream().map(Car::getModel).toList();
        assertThat(models).contains(carFilter.getModel());

        session.getTransaction().commit();
    }

}
