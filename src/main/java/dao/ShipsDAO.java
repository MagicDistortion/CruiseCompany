package dao;

import models.ships.Ship;
import org.apache.log4j.Logger;
import utils.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShipsDAO {
    private final static Logger logger = Logger.getLogger(ShipsDAO.class);
    private final DBManager dbManager = DBManager.getInstance();

    private Ship getShip(ResultSet resultSet) throws SQLException {
        Ship ship = new Ship(resultSet.getString("name")
                , resultSet.getInt("capacity")
                , resultSet.getString("current_point"));
        ship.setId(resultSet.getInt("ship_id"));
        return ship;
    }

    /* метод додавання корабля  */
    public void insertShip(Ship ship) {
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement
                     = connection.prepareStatement(Constants.INSERT_SHIP, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, ship.getName());
            preparedStatement.setInt(2, ship.getCapacity());
            preparedStatement.setString(3, ship.getCurrent_point());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                generatedKeys.next();
                ship.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            logger.error("failed to insert ship", e);
            throw new RuntimeException(e);
        }
    }

    /* метод пошуку корабля по id */
    public Ship findShipByID(int id) {
        Ship ship = null;
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constants.FIND_SHIP_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                if (resultSet.next()) {
                    ship = new Ship(resultSet.getString("name")
                            , resultSet.getInt("capacity")
                            , resultSet.getString("current_point"));
                    ship.setId(resultSet.getInt("ship_id"));
                }
            }
        } catch (SQLException e) {
            logger.error("failed to find ship by id ->" + id, e);
            throw new RuntimeException(e);
        }
        return ship;
    }

    /* метод отримання списку всіх кораблів */
    public List<Ship> findAllShips() {
        List<Ship> shipList = new ArrayList<>();
        try (Connection connection = dbManager.getConnection();
             ResultSet resultSet = connection.prepareStatement(Constants.FROM_SHIPS).executeQuery()) {
            while (resultSet.next()) {
                Ship ship = getShip(resultSet);
                shipList.add(ship);
            }
        } catch (SQLException e) {
            logger.error("failed to get ships list", e);
            throw new RuntimeException(e);
        }
        return shipList;
    }


    /* метод отримання списку лайнерів за сортуванням та пагінацією */
    public List<Ship> findShipsWithLimit(String sorted, int start, int total) {
        start = (start - 1) * total;
        String limit = " limit " + start + "," + total;
        List<Ship> shipList = new ArrayList<>();
        try (Connection connection = dbManager.getConnection();
             ResultSet resultSet = connection.prepareStatement(Constants.FROM_SHIPS + sorted + limit).executeQuery()) {
            while (resultSet.next()) {
                Ship ship = getShip(resultSet);
                shipList.add(ship);
            }
        } catch (SQLException e) {
            logger.error("failed to get ships list with limit", e);
            throw new RuntimeException(e);
        }
        return shipList;
    }

    /* метод отримання кількості лайнерів в базі*/
    public int shipsCount() {
        try (Connection connection = dbManager.getConnection();
             ResultSet resultSet = connection.prepareStatement(Constants.FROM_SHIPS_COUNT).executeQuery()) {
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            logger.error("failed to get a ships count ", e);
            throw new RuntimeException(e);
        }
    }

    /* метод перевірки наявності назви лайнера*/
    public boolean shipsNameExist(String name) {
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constants.SHIPS_NAME_EXIST)) {
            preparedStatement.setString(1, name);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            return resultSet.getBoolean(1);
        } catch (SQLException e) {
            logger.error("failed to check ships name -> " + name, e);
            throw new RuntimeException(e);
        }
    }
}
