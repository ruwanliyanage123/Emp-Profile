package Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Use singleton design pattern
 */
public class DatabaseConnection {
    private static Logger logger = LoggerFactory.getLogger(DatabaseConnection.class);
    private static DatabaseConnection instance;
    private Connection connection;

    /**
     * Kept constructor as private to control the object creation
     */
    private DatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String username = "root";
            String password = "19940306";
            String url = "jdbc:mysql://localhost:3306/employees";
            this.connection = DriverManager.getConnection(url, username, password);
            logger.info("Database Connection Established");
        } catch (ClassNotFoundException e) {
            logger.error(
                    "Database Driver class not found Please check about com.mysql.cj.jdbc.Driver");
        } catch (SQLException e) {
            logger.error("Database connection Error. Check your Username, Password, Database Name");
        }
    }

    public Connection getConnection() {
        return connection;
    }

    /**
     * use static keyword to make sure that there are no more access points to create the instance
     *
     * @return instance
     */
    public static DatabaseConnection getInstance() {

        try {
            if (instance == null) {
                instance = new DatabaseConnection();
            } else {
                if (instance.getConnection().isClosed()) {
                    instance = new DatabaseConnection();
                }
            }
        } catch (SQLException e) {
            logger.error("Database connection Error. Check your Username, Password, Database Name");
        }
        return instance;
    }
}
