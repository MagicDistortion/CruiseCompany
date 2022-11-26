package dao;

import models.ships.Ship;
import org.apache.log4j.Logger;
import utils.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShipsDAO {
    private final static Logger logger = Logger.getLogger(ShipsDAO.class);
    private final DBManager dbManager = DBManager.getInstance();

    /* метод додавання корабля  */
    public void insertShip(Ship ship) {
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement
                     = connection.prepareStatement(Constants.INSERT_SHIP, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1,ship.getCapacity());
            preparedStatement.setString(2,ship.getCurrent_point());
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
}
