package jane.dao;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import jane.entity.Booking;
import jane.entity.enums.PaymentStateEnum;
import jane.query.QPredicate;
import jane.query.filter.BookingFilter;
import jane.query.filter.ClientFilter;
import org.hibernate.Session;
import org.hibernate.graph.GraphSemantic;

import java.util.List;
import java.util.Optional;

import static jane.entity.QBooking.booking;
import static jane.entity.QCar.car;
import static jane.entity.QClient.client;

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

    public List<Booking> findBookingsByStatusAndPaymentState(Session session, BookingFilter bookingFilter) {
        Predicate predicate = QPredicate.builder()
                .add(bookingFilter.getStatus(), booking.status::eq)
                .add(bookingFilter.getPaymentState(), booking.paymentState::eq)
                .buildAnd();

        return new JPAQuery<Booking>(session)
                .select(booking)
                .from(booking)
                .where(predicate)
                .fetch();
    }

    public List<Booking> findAllBookingOfOneClient(Session session, ClientFilter clientFilter) {
        Predicate predicate = QPredicate.builder()
                .add(clientFilter.getId(), client.id::eq)
                .buildAnd();

        return new JPAQuery<Booking>(session)
                .select(booking)
                .from(booking)
                .join(booking.client, client)
                .join(booking.car, car)
                .setHint(GraphSemantic.LOAD.getJpaHintName(), session.getEntityGraph("withClientAndCar"))
                .where(predicate)
                .fetch();
    }




    public static BookingDao getInstance() {
        return INSTANCE;
    }
}
