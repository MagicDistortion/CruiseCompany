package dao;

import models.route.Route;
import org.apache.log4j.Logger;
import utils.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RouteDAO {
    private final static Logger logger = Logger.getLogger(RouteDAO.class);
    private final DBManager dbManager = DBManager.getInstance();
    /* метод додавання маршруту  */
    public void insertRoute(Route route) {
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement
                     = connection.prepareStatement(Constants.INSERT_ROUTE, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1,route.getName());
            preparedStatement.setString(2,route.getRoute());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                generatedKeys.next();
                route.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            logger.error("failed to insert route", e);
            throw new RuntimeException(e);
        }
    }
    /* метод перевірки наявності назви маршруту*/
    public boolean routeNameExist(String name) {
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constants.ROUTE_NAME_EXIST)) {
            preparedStatement.setString(1, name);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            return resultSet.getBoolean(1);
        } catch (SQLException e) {
            logger.error("failed to check route name -> " + name, e);
            throw new RuntimeException(e);
        }
    }
}
