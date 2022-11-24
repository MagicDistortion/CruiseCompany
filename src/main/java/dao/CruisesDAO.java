package dao;

import models.cruises.Cruise;
import models.users.Admin;
import models.users.Passenger;
import models.users.User;
import org.apache.log4j.Logger;
import utils.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CruisesDAO {
    private final static Logger logger = Logger.getLogger(CruisesDAO.class);
    private final DBManager dbManager = DBManager.getInstance();

    /* метод отримання круїзу з різалтсету */
    public Cruise getCruise(ResultSet resultSet) throws SQLException {
        Cruise cruise = new Cruise(resultSet.getInt("ship_id")
                , resultSet.getString("cruise_name")
                , resultSet.getInt("number_of_ports")
                , resultSet.getDouble("price")
                , LocalDateTime.of(resultSet.getDate("start_time").toLocalDate()
                , resultSet.getTime("start_time").toLocalTime())
                , LocalDateTime.of(resultSet.getDate("end_time").toLocalDate()
                , resultSet.getTime("end_time").toLocalTime())
        );
        cruise.setId(resultSet.getInt("cruise_id"));
        cruise.setStatus(resultSet.getString("status"));
        return cruise;
    }


    /* метод пошуку круїзу по id */
    public Cruise findCruiseByID(int id) {
        Cruise cruise = null;
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constants.FIND_CRUISE_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                if (resultSet.next()) {
                    cruise = getCruise(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error("failed to find cruise by id ->" + id, e);
            throw new RuntimeException(e);
        }
        return cruise;
    }


    /* метод отримання вартості одного квитка за id круїзу*/
    public double getPriceOfCruise(Connection connection, int ticketId) {
        double price = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(Constants.GET_CRUISE_PRICE)) {
            preparedStatement.setInt(1, ticketId);
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                if (resultSet.next())
                    price = resultSet.getDouble("price");
            }
        } catch (SQLException e) {
            logger.error("failed to find price by ticket`s id ->" + ticketId, e);
            throw new RuntimeException(e);
        }
        return price;
    }
}
