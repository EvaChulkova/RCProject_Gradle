package jane.query;

import jane.dao.ClientDao;
import jane.entity.Client;
import jane.query.filter.ClientFilter;
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
public class ClientDaoQueryIT {
    private final SessionFactory sessionFactory = HibernateTestUtil.buildSessionFactory();
    private final ClientDao clientDao = ClientDao.getInstance();

    @BeforeAll
    public void initDB() {
        TestDataImporter.importData(sessionFactory);
    }

    @AfterAll
    public void finish() {
        sessionFactory.close();
    }

    @Test
    void findAllByDrivingLicenceNo() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Client> results = clientDao.findAllByDrivingLicenceNo(session, 144096);

        assertThat(results).hasSize(1);
        assertThat(results.get(0).getDrivingLicenceNo()).isEqualTo(144096);

        session.getTransaction().commit();
    }

    @Test
    void findUserInfoAboutClientByDrivingLicenceNo() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        ClientFilter clientFilter = ClientFilter.builder()
                .drivingLicenceNo(116753)
                .build();

        List<Client> results = clientDao.findUserInfoAboutClientByDrivingLicenceNo(session, clientFilter);
        assertThat(results).hasSize(1);

        List<Integer> drivingLicenceNos = results.stream().map(Client::getDrivingLicenceNo).toList();
        assertThat(drivingLicenceNos).contains(clientFilter.getDrivingLicenceNo());

        session.getTransaction().commit();
    }
}
