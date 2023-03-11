package jane.dao;

import jane.entity.User;
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

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserDaoTest {
    private final SessionFactory sessionFactory = HibernateTestUtil.buildSessionFactory();
    private final UserDao userDao = UserDao.getInstance();

    @BeforeAll
    public void initDB() {
        TestDataImporter.importData(sessionFactory);
    }

    @AfterAll
    public void finish() {
        sessionFactory.close();
    }

    @Test
    void findAll() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<User> users = userDao.findAll(session);
        Assertions.assertThat(users).hasSize(8);

        session.getTransaction().commit();
    }
}
