package jane.dao;

import jane.entity.Booking;

import java.util.List;
import java.util.Optional;

public class BookingDao implements Dao<Long, Booking> {

    private static final BookingDao INSTANCE = new BookingDao();

    @Override
    public List<Booking> findAll() {
        return null;
    }

    @Override
    public Optional<Booking> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public Booking update(Booking entity) {
        return null;
    }

    @Override
    public Booking save(Booking entity) {
        return null;
    }

    public static BookingDao getInstance() {
        return INSTANCE;
    }
}
