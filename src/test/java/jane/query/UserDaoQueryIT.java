package jane.query;

import jane.dao.UserDao;
import jane.entity.User;
import jane.query.filter.UserFilter;
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
public class UserDaoQueryIT {
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
    void findAllByFirstName() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<User> results = userDao.findAllByFirstName(session, "Svetlana");

        assertThat(results).hasSize(1);
        assertThat(results.get(0).getFirstName()).isEqualTo("Svetlana");

        session.getTransaction().commit();
    }

    @Test
    void findUserByFirstNameAndLastName() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        UserFilter userFilter = UserFilter.builder()
                .firstName("Ivan")
                .lastname("Ivanov")
                .build();

        List<User> results = userDao.findUserByFirstNameAndLastName(session, userFilter);
        assertThat(results).hasSize(1);

        List<String> firstNames = results.stream().map(User::getFirstName).toList();
        assertThat(firstNames).contains(userFilter.getFirstName());

        List<String> lastNames = results.stream().map(User::getLastName).toList();
        assertThat(lastNames).contains(userFilter.getLastname());


        session.getTransaction().commit();
    }

}
