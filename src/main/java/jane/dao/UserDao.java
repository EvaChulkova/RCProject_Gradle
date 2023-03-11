package jane.dao;

import jane.entity.User;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserDao {
    private static final UserDao INSTANCE = new UserDao();

    public List<User> findAll(Session session) {
        /*return session.createQuery("select u from User u", User.class)
                .list();*/


        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery<User> criteria = cb.createQuery(User.class);
        Root<User> user = criteria.from(User.class);

        criteria.select(user);

        return session.createQuery(criteria).list();
    }


    public static UserDao getInstance() {
        return INSTANCE;
    }
}
