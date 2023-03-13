package jane.query;

import jane.dao.CarDao;
import jane.entity.Car;
import jane.util.HibernateTestUtil;
import jane.util.TestDataImporter;
import lombok.Cleanup;
import org.assertj.core.api.Assertions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

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
        Assertions.assertThat(results).hasSize(2);

        session.getTransaction().commit();
    }

}
