package jane.dao;

import com.querydsl.jpa.impl.JPAQuery;
import jane.entity.User;
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


    public static UserDao getInstance() {
        return INSTANCE;
    }
}
