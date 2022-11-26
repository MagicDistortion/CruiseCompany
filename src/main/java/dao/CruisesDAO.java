package dao;

import models.cruises.Cruise;
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

    /* метод додавання круїзу  */
    public void insertCruise(Cruise cruise) {
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement
                     = connection.prepareStatement(Constants.INSERT_CRUISE, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, cruise.getShipId());
            preparedStatement.setString(2, cruise.getName());
            preparedStatement.setInt(3, cruise.getNumberOfPorts());
            preparedStatement.setDouble(4, cruise.getPrice());
            preparedStatement.setObject(5, cruise.getStartTime());
            preparedStatement.setObject(6, cruise.getEndTime());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                generatedKeys.next();
                cruise.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            logger.error("failed to insert cruise", e);
            throw new RuntimeException(e);
        }
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

    /* метод отримання списку всіх круїзів */
    public List<Cruise> findAllCruises() {
        List<Cruise> cruiseList = new ArrayList<>();
        try (Connection connection = dbManager.getConnection();
             ResultSet resultSet = connection.prepareStatement(Constants.FROM_CRUISES).executeQuery()) {
            while (resultSet.next()) {
                Cruise cruise = getCruise(resultSet);
                cruiseList.add(cruise);

            }
        } catch (SQLException e) {
            logger.error("failed to get cruises list", e);
            throw new RuntimeException(e);
        }
        return cruiseList;
    }
}
