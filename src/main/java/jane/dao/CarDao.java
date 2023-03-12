package jane.dao;

import jane.entity.Car;

import java.util.List;
import java.util.Optional;

public class CarDao implements Dao<Long, Car> {

    private static final CarDao INSTANCE = new CarDao();

    @Override
    public List<Car> findAll() {
        return null;
    }

    @Override
    public Optional<Car> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public Car update(Car entity) {
        return null;
    }

    @Override
    public Car save(Car entity) {
        return null;
    }

    public static CarDao getInstance() {
        return INSTANCE;
    }
}
