package dao;

import models.Route;
import org.apache.log4j.Logger;
import utils.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RouteDAO {
    private final static Logger logger = Logger.getLogger(RouteDAO.class);
    private final DBManager dbManager = DBManager.getInstance();

    /* метод отримання маршруту з різалтсету */
    public Route getRoute(ResultSet resultSet) throws SQLException {
        Route route = new Route(resultSet.getString("name"), resultSet.getString("route"));
        route.setId(resultSet.getInt("route_id"));
        return route;
    }

    /* метод додавання маршруту  */
    public void insertRoute(Route route) {
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement
                     = connection.prepareStatement(Constants.INSERT_ROUTE, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, route.getName());
            preparedStatement.setString(2, route.getRoute());
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

    /* метод отримання списку всіх маршрутів */
    public List<Route> findAllRoutes() {
        List<Route> routeList = new ArrayList<>();
        try (Connection connection = dbManager.getConnection();
             ResultSet resultSet = connection.prepareStatement(Constants.FROM_ROUTE).executeQuery()) {
            while (resultSet.next()) routeList.add(getRoute(resultSet));
        } catch (SQLException e) {
            logger.error("failed to get route list", e);
            throw new RuntimeException(e);
        }
        return routeList;
    }

    /* метод пошуку маршруту по id */
    public Route findRouteByID(int id) {
        Route route = null;
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constants.FIND_ROUTE_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getResultSet()) {
                if (resultSet.next()) route = getRoute(resultSet);
            }
        } catch (SQLException e) {
            logger.error("failed to find route by id ->" + id, e);
            throw new RuntimeException(e);
        }
        return route;
    }
}
