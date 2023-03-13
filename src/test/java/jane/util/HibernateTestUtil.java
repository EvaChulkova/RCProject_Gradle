package jane.util;

import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.testcontainers.containers.PostgreSQLContainer;

@UtilityClass
public class HibernateTestUtil {

    private static final PostgreSQLContainer<?> rentcar_postgres = new PostgreSQLContainer<>("postgres:13");

    static {
        rentcar_postgres.start();
    }

    public static SessionFactory buildSessionFactory() {
        Configuration configuration = HibernateUtil.buildConfiguration();
        configuration.setProperty("hibernate.connection.url", rentcar_postgres.getJdbcUrl());
        configuration.setProperty("hibernate.connection.username", rentcar_postgres.getUsername());
        configuration.setProperty("hibernate.connection.password", rentcar_postgres.getPassword());
        configuration.configure();

        return configuration.buildSessionFactory();
    }

}
