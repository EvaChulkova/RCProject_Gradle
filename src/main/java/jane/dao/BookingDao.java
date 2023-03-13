package jane.dao;

import com.querydsl.jpa.impl.JPAQuery;
import jane.entity.Booking;
import jane.entity.enums.PaymentStateEnum;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

import static jane.entity.QBooking.booking;

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

    public List<Booking> findAllBookingsByPaymentState(Session session, PaymentStateEnum paymentState) {
        return new JPAQuery<Booking>(session)
                .select(booking)
                .from(booking)
                .where(booking.paymentState.eq(paymentState))
                .fetch();
    }

    public static BookingDao getInstance() {
        return INSTANCE;
    }
}
