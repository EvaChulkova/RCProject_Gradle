package jane.dao;

import jane.entity.Client;

import java.util.List;
import java.util.Optional;

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

    public static ClientDao getInstance() {
        return INSTANCE;
    }
}
