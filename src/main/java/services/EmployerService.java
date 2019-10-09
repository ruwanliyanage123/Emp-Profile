package services;

import Bean.Employer;
import Connection.DatabaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployerService {

    private static Logger logger = LoggerFactory.getLogger(EmployerService.class);
    private Connection connection;

    public EmployerService() {
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        connection = databaseConnection.getConnection();
    }

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

                employerList.add(employer);
            }
            connection.close();
        } catch (SQLException e) {
            logger.error("Check Table Name");
        }
        return employerList;
    }

    /**
     * @param employerId represents the ID of the selected employer
     * @return selected employer object
     */
    public Employer getEmployer(int employerId) {
        Employer employer = new Employer();

        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM employee WHERE employerId=?");
            preparedStatement.setInt(1, employerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employer.setEmployerId(resultSet.getInt(1));
                employer.setEmployerName(resultSet.getString(2));
                employer.setPosition(resultSet.getString(3));
            }
            connection.close();
        } catch (SQLException e) {
            logger.error("Check Table Name, ID number");
        }

        return employer;
    }

    /**
     * @param employer selected emploter
     */
    public void addEmployer(@NotNull Employer employer) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO employee(employerName,position) VALUES (?,?)");
            preparedStatement.setString(1, employer.getEmployerName());
            preparedStatement.setString(2, employer.getPosition());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            logger.error("Check Table Name, ID number");
        }
    }

    /**
     * @param employerId ID number of selected employer
     */
    public void deleteEmployer(int employerId) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM employee WHERE employerId=?");
            preparedStatement.setInt(1, employerId);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            logger.error("Check Table Name, ID number");
        }
    }

    /**
     * @param employer selected employer who need to update
     */
    public void updateEmployer(Employer employer) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE employee SET employerName=?, position =? WHERE employerId=?");
            preparedStatement.setString(1, employer.getEmployerName());
            preparedStatement.setString(2, employer.getPosition());
            preparedStatement.setInt(3, employer.getEmployerId());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            logger.error("Check Table Name, ID number");
        }
    }
}
