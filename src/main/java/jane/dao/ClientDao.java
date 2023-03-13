package jane.dao;

import com.querydsl.jpa.impl.JPAQuery;
import jane.entity.Client;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

import static jane.entity.QClient.client;

public class ClientDao implements Dao<Long, Client> {

    private static final ClientDao INSTANCE = new ClientDao();

    @Override
    public List<Client> findAll() {
        return null;
    }

    @Override
    public Optional<Client> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public Client update(Client entity) {
        return null;
    }

    @Override
    public Client save(Client entity) {
        return null;
    }

    public List<Client> findAllByDrivingLicenceNo(Session session, Integer drivingLicenceNo) {
        return new JPAQuery<Client>(session)
                .select(client)
                .from(client)
                .where(client.drivingLicenceNo.eq(drivingLicenceNo))
                .fetch();
    }

    public static ClientDao getInstance() {
        return INSTANCE;
    }
}
