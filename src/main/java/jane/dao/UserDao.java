package jane.dao;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import jane.entity.User;
import jane.query.QPredicate;
import jane.query.filter.UserFilter;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

import static jane.entity.QUser.user;

public class UserDao implements Dao<Long, User> {
    private static final UserDao INSTANCE = new UserDao();

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public User update(User entity) {
        return null;
    }

    @Override
    public User save(User entity) {
        return null;
    }

    public List<User> findAllByFirstName(Session session, String firstName) {
        return new JPAQuery<User>(session)
                .select(user)
                .from(user)
                .where(user.firstName.eq(firstName))
                .fetch();
    }

    public List<User> findAllByLogin(Session session, String login) {
        return new JPAQuery<User>(session)
                .select(user)
                .from(user)
                .where(user.login.eq(login))
                .fetch();
    }

    public List<User> findUserByFirstNameAndLastName(Session session, UserFilter userFilter) {
        Predicate predicate = QPredicate.builder()
                .add(userFilter.getFirstName(), user.firstName::eq)
                .add(userFilter.getLastname(), user.lastName::eq)
                .buildAnd();

        return new JPAQuery<User>(session)
                .select(user)
                .from(user)
                .where(predicate)
                .fetch();
    }


    public static UserDao getInstance() {
        return INSTANCE;
    }
}
