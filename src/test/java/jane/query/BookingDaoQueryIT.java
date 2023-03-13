package jane.query;

import jane.dao.BookingDao;
import jane.entity.Booking;
import jane.entity.enums.PaymentStateEnum;
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

    @Test
    void findAllBookingsByPaymentState() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Booking> results = bookingDao.findAllBookingsByPaymentState(session, PaymentStateEnum.PAID);

        Assertions.assertThat(results).hasSize(4);

        session.getTransaction().commit();
    }

}
