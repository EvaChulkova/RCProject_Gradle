package jane.query;

import jane.dao.BookingDao;
import jane.util.HibernateTestUtil;
import jane.util.TestDataImporter;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
public class BookingDaoQueryIT {
    private final SessionFactory sessionFactory = HibernateTestUtil.buildSessionFactory();
    private final BookingDao bookingDao = BookingDao.getInstance();

    @BeforeAll
    public void initDB() {
        TestDataImporter.importData(sessionFactory);
    }

    @AfterAll
    public void finish() {
        sessionFactory.close();
    }

}
