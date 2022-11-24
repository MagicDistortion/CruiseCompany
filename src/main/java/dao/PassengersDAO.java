package dao;

import models.users.Passenger;
import org.apache.log4j.Logger;
import utils.Constants;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PassengersDAO {
    private final static Logger logger = Logger.getLogger(PassengersDAO.class);
    private final DBManager dbManager = DBManager.getInstance();
    private final UsersDAO usersDAO = new UsersDAO();

    /* метод отримання Пасажирів  */
    public List<Passenger> findAllPassengers() {
        List<Passenger> passengerList = new ArrayList<>();
        try (Connection connection = dbManager.getConnection();
             ResultSet resultSet = connection.prepareStatement(Constants.FIND_ALL_PASSENGERS).executeQuery()) {
            while (resultSet.next()) {
                Passenger passenger = new Passenger(usersDAO.getUser(resultSet));
                passengerList.add(passenger);
            }
        } catch (SQLException e) {
            logger.error("failed to get passengers list", e);
            throw new RuntimeException(e);
        }
        return passengerList;
    }
}
