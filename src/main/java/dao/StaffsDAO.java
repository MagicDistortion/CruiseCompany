package dao;

import org.apache.log4j.Logger;
import utils.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffsDAO {
    private final static Logger logger = Logger.getLogger(StaffsDAO.class);
    private final DBManager dbManager = DBManager.getInstance();

    /* метод пошуку id лайнера за id персонала */
    public int findShipByStaffId(int id) {
        int shipId = 0;
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constants.FIND_SHIP_ID_BY_STAFF_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                if (resultSet.next()) shipId = resultSet.getInt("id_of_ship");
            }
        } catch (SQLException e) {
            logger.error("failed to find cruise by ship id ->" + id, e);
            throw new RuntimeException(e);
        }
        return shipId;
    }
}
