package dao;

import models.cruises.Cruise;
import org.apache.log4j.Logger;
import utils.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CruisesDAO {
    private final static Logger logger = Logger.getLogger(CruisesDAO.class);
    private final DBManager dbManager = DBManager.getInstance();

    /* метод отримання круїзу з різалтсету */
    public Cruise getCruise(ResultSet resultSet) throws SQLException {
        Cruise cruise = new Cruise(resultSet.getInt("ship_id")
                ,resultSet.getInt("route_id")
                , resultSet.getString("ship_name")
                , resultSet.getString("cruise_name")
                , resultSet.getInt("number_of_ports")
                , resultSet.getDouble("price")
                , LocalDateTime.of(resultSet.getDate("start_time").toLocalDate()
                , resultSet.getTime("start_time").toLocalTime())
                , LocalDateTime.of(resultSet.getDate("end_time").toLocalDate()
                , resultSet.getTime("end_time").toLocalTime())
        );
        cruise.setDescription(resultSet.getString("description"));
        cruise.setDuration(resultSet.getInt("duration"));
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
            preparedStatement.setString(2, cruise.getShipName());
            preparedStatement.setInt(3, cruise.getRouteId());
            preparedStatement.setString(4, cruise.getCruiseName());
            preparedStatement.setInt(5, cruise.getNumberOfPorts());
            preparedStatement.setDouble(6, cruise.getPrice());
            preparedStatement.setObject(7, cruise.getStartTime());
            preparedStatement.setObject(8, cruise.getEndTime());
            preparedStatement.setInt(9, cruise.getDuration());
            preparedStatement.setString(10, cruise.getDescription());
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

    /* метод отримання списку всіх круїзів за датою */
    public List<Cruise> findCruisesListByDate(LocalDate date) {
        List<Cruise> cruiseList = new ArrayList<>();
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constants.FROM_CRUISES_BY_DATE)) {
            preparedStatement.setObject(1, date);
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) cruiseList.add(getCruise(resultSet));
            }
        } catch (SQLException e) {
            logger.error("failed to get cruises list by date -> " + date, e);
            throw new RuntimeException(e);
        }
        return cruiseList;
    }

    /* метод отримання списку всіх круїзів за тривалістю */
    public List<Cruise> findCruisesListByDuration(int duration) {
        List<Cruise> cruiseList = new ArrayList<>();
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constants.FROM_CRUISES_BY_DURATION)) {
            preparedStatement.setInt(1, duration);
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) cruiseList.add(getCruise(resultSet));
            }
        } catch (SQLException e) {
            logger.error("failed to get cruises list by duration ->" + duration, e);
            throw new RuntimeException(e);
        }
        return cruiseList;
    }


    /* метод отримання списку круїзів за сортуванням та пагінацією */
    public List<Cruise> findCruisesWithLimit(String sorted, int start, int total) {
        start = (start - 1) * total;
        String limit = " limit " + start + "," + total;
        List<Cruise> cruiseList = new ArrayList<>();
        try (Connection connection = dbManager.getConnection();
             ResultSet resultSet = connection.prepareStatement(Constants.FROM_CRUISES + sorted + limit).executeQuery()) {
            while (resultSet.next()) {
                Cruise cruise = getCruise(resultSet);
                cruiseList.add(cruise);
            }
        } catch (SQLException e) {
            logger.error("failed to get cruises list with limit", e);
            throw new RuntimeException(e);
        }
        return cruiseList;
    }

    /* метод отримання кількості круїзів в базі*/
    public int CruisesCount() {
        int count = 0;
        try (Connection connection = dbManager.getConnection();
             ResultSet resultSet = connection.prepareStatement(Constants.FROM_CRUISES_COUNT).executeQuery()) {
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.error("failed to get a cruises count ", e);
            throw new RuntimeException(e);
        }
        return count;
    }


    /* метод перевірки чи вільний лайнер на наші дати*/
    public boolean checkingTheShipIsFreeOnDates(int shipId, String start, String end) {
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constants.THE_SHIP_IS_FREE_ON_DATES)) {
            preparedStatement.setInt(1, shipId);
            preparedStatement.setString(2, start);
            preparedStatement.setString(3, end);
            preparedStatement.setString(4, start);
            preparedStatement.setString(5, end);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            return resultSet.getInt(1) == 0;
        } catch (SQLException e) {
            logger.error("failed to check is free ship with id-> " + shipId, e);
            throw new RuntimeException(e);
        }
    }
}
