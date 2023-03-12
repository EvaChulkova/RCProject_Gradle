package jane.dao;

import jane.entity.Client;
import jane.entity.User;
import jane.entity.enums.RoleEnum;
import jane.util.HibernateTestUtil;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ClientDaoIT {
    private final SessionFactory sessionFactory = HibernateTestUtil.buildSessionFactory();

    @Test
    void findAll() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        User user = createUser();
        session.save(user);

        User anotherUser = createAnotherUser();
        session.save(anotherUser);

        Client client = createClient();
        client.setUser(user);
        session.save(client);

        Client anotherClient = createAnotheClient();
        anotherClient.setUser(anotherUser);
        session.save(anotherClient);

        List<Client> actualResult = session.createQuery("select c from Client c", Client.class)
                .list();
        assertThat(actualResult).containsExactlyInAnyOrder(client, anotherClient);

        session.getTransaction().commit();
    }

    @Test
    void findById() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        User user = createUser();
        session.save(user);

        Client client = createClient();
        client.setUser(user);
        session.save(client);

        Client actualResult = session.get(Client.class, client.getId());
        assertThat(actualResult).isEqualTo(client);

        session.getTransaction().commit();
    }

    @Test
    void delete() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        User user = createUser();
        session.save(user);

        Client client = createClient();
        client.setUser(user);
        session.save(client);

        session.delete(client);

        Client actualResult = session.get(Client.class, client.getId());
        assertThat(actualResult).isNull();

        session.getTransaction().commit();
    }

    @Test
    void update() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        User user = createUser();
        session.save(user);

        Client client = createClient();
        client.setUser(user);
        session.save(client);

        Client updatedClient = session.get(Client.class, client.getId());
        updatedClient.setDrivingLicenceNo(900900);
        session.save(updatedClient);

        Client actualClient = session.get(Client.class, client.getId());
        assertThat(actualClient).isEqualTo(updatedClient);

        session.getTransaction().commit();
    }

    @Test
    void save() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        User user = createUser();
        session.save(user);

        Client client = createClient();
        client.setUser(user);
        session.save(client);

        Client actualResult = session.get(Client.class, client.getId());
        assertThat(actualResult).isNotNull();

        session.getTransaction().commit();
    }


    private static Client createClient() {
        Client client = Client.builder()
                .user(createUser())
                .birthDate(LocalDate.of(2000, 1, 1))
                .drivingLicenceNo(123123)
                .validity(LocalDate.of(2040,1,1))
                .build();

        return client;
    }

    private static Client createAnotheClient() {
        Client anotherClient = Client.builder()
                .user(createAnotherUser())
                .birthDate(LocalDate.of(2001, 2, 2))
                .drivingLicenceNo(321321)
                .validity(LocalDate.of(2030,10,10))
                .build();

        return anotherClient;
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
