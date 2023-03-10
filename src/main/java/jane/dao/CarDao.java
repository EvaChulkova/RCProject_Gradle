package jane.dao;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import jane.entity.Car;
import jane.query.QPredicate;
import jane.query.filter.CarFilter;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

import static jane.entity.QCar.car;

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

    public List<Car> findAllByBrand(Session session, String brand) {
        return new JPAQuery<Car>(session)
                .select(car)
                .from(car)
                .where(car.brand.eq(brand))
                .fetch();
    }

    public List<Car> findAllByBrandAndModel(Session session, CarFilter carFilter) {
        Predicate predicate = QPredicate.builder()
                .add(carFilter.getBrand(), car.brand::eq)
                .add(carFilter.getModel(), car.model::eq)
                .buildAnd();

        return new JPAQuery<Car>(session)
                .select(car)
                .from(car)
                .where(predicate)
                .fetch();
    }




    public static CarDao getInstance() {
        return INSTANCE;
    }
}
