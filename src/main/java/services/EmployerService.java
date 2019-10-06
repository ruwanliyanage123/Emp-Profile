package services;

import Bean.Employer;
import Connection.DatabaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployerService {

    private static Logger logger = LoggerFactory.getLogger(EmployerService.class);
    private DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
    private Connection connection = databaseConnection.getConnection();

    /**
     * get all employers from database
     *
     * @return employer list
     */
    public List<Employer> getAllEmployers() {
        List<Employer> employerList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM employee");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employer employer = new Employer();
                employer.setEmployerId(resultSet.getInt(1));
                employer.setEmployerName(resultSet.getString(2));
                employer.setPosition(resultSet.getString(3));
            }
            connection.close();
        } catch (SQLException e) {
            logger.error("Check database name, Table Name, Column order");
        }
        return employerList;
    }
}
