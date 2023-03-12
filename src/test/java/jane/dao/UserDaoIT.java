package jane.dao;

import jane.entity.User;
import jane.entity.enums.RoleEnum;
import jane.util.HibernateTestUtil;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDaoIT {
    private final SessionFactory sessionFactory = HibernateTestUtil.buildSessionFactory();

    @Test
    void findAll() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        User user = createUser();
        User user1 = createAnotherUser();
        session.save(user);
        session.save(user1);

        List<User> actualResult = session.createQuery("select u from User u", User.class)
                .list();

        assertThat(actualResult).containsExactlyInAnyOrder(user, user1);

        session.getTransaction().commit();
    }

    @Test
    void findById() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        User user = createUser();
        session.save(user);

        User actualResult = session.get(User.class, user.getId());
        assertThat(actualResult).isEqualTo(user);

        session.getTransaction().commit();
    }

    @Test
    void delete() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        User user = createUser();
        session.save(user);

        session.delete(user);

        User actualResult = session.get(User.class, user.getId());
        assertThat(actualResult).isNull();

        session.getTransaction().commit();
    }

    @Test
    void save() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        User user = createUser();
        session.save(user);

        User actualResult = session.get(User.class, user.getId());
        assertThat(actualResult).isNotNull();

        session.getTransaction().commit();
    }

    @Test
    void update() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        User user = createUser();
        session.save(user);

        User updatedUser = session.get(User.class, user.getId());
        updatedUser.setLastName("Sidorova");

        session.update(updatedUser);

        User actualResult = session.get(User.class, user.getId());
        assertThat(actualResult).isEqualTo(updatedUser);

        session.getTransaction().commit();
    }


    private static User createUser() {
        User user = User.builder()
                .firstName("Anna")
                .lastName("Petrova")
                .login("anna@gmail.com")
                .password("123")
                .role(RoleEnum.CLIENT)
                .build();

        return user;
    }

    private static User createAnotherUser() {
        User anotherUser = User.builder()
                .firstName("Kseniya")
                .lastName("Ivanova")
                .login("kseniya@gmail.com")
                .password("123")
                .role(RoleEnum.CLIENT)
                .build();

        return anotherUser;
    }

}
