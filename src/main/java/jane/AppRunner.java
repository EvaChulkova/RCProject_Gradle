package jane;

import org.hibernate.cfg.Configuration;

import java.sql.SQLException;

public class AppRunner {
    public static void main(String[] args) throws SQLException {
        Configuration configuration = new Configuration();
        configuration.configure();
    }

}