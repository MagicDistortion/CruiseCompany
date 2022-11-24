package dao;

import models.users.User;
import org.apache.log4j.Logger;
import utils.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO {
    private final static Logger logger = Logger.getLogger(UsersDAO.class);
    private final DBManager dbManager = DBManager.getInstance();

    /* метод отримання користувача з різалтсету */
    public User getUser(ResultSet resultSet) throws SQLException {
        User user = new User(resultSet.getString("surname")
                , resultSet.getString("name")
                , resultSet.getString("login")
                , resultSet.getString("password")
                , resultSet.getString("tel")
                , resultSet.getDate("date_of_birth").toLocalDate()
        );
        user.setId(resultSet.getInt("users_id"));
        user.setRolesId(resultSet.getInt("roles_id"));
        return user;
    }

    /* метод додавання користувача  */
    public void insertUser(User user) {
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement
                     = connection.prepareStatement(Constants.INSERT_USERS, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getSurname());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getTel());
            preparedStatement.setObject(6, user.getDateOfBirth());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                generatedKeys.next();
                user.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            logger.error("failed to insert user", e);
            throw new RuntimeException(e);
        }
    }

    /* метод отримання списку всіх користувачів */
    public List<User> findAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = dbManager.getConnection();
             ResultSet resultSet = connection.prepareStatement(Constants.FROM_USERS).executeQuery()) {
            while (resultSet.next()) {
                User user = getUser(resultSet);
                userList.add(user);
            }
        } catch (SQLException e) {
            logger.error("failed to get users list", e);
            throw new RuntimeException(e);
        }
        return userList;
    }

    /* метод оновлення ролі користувача  */
    public void updateUserRole(Connection connection, int role, int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(Constants.UPDATE_USER_ROLE)) {
            preparedStatement.setInt(1, role);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("failed to update user`s role", e);
            throw new RuntimeException(e);
        }
    }

    /* метод оновлення Прізвища користувача  */
    public void updateUserSurname(String surname, int id) {
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constants.UPDATE_USER_SURNAME)) {
            preparedStatement.setString(1, surname);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("failed to update user`s surname", e);
            throw new RuntimeException(e);
        }
    }

    /* метод оновлення Імені користувача */
    public void updateUserName(String name, int id) {
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constants.UPDATE_USER_NAME)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("failed to update user`s name", e);
            throw new RuntimeException(e);
        }
    }

    /* метод оновлення Логіну користувача */
    public void updateUserLogin(String login, int id) {
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constants.UPDATE_USER_LOGIN)) {
            preparedStatement.setString(1, login);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("failed to update user`s login", e);
            throw new RuntimeException(e);
        }
    }

    /* метод оновлення Паролю користувача */
    public void updateUserPassword(String password, int id) {
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constants.UPDATE_USER_PASSWORD)) {
            preparedStatement.setString(1, password);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("failed to update user`s password", e);
            throw new RuntimeException(e);
        }
    }

    /* метод оновлення Номера Телефона користувача */
    public void updateUserTel(String tel, int id) {
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constants.UPDATE_USER_TEL)) {
            preparedStatement.setString(1, User.updateTel(tel));
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("failed to update user`s tel", e);
            throw new RuntimeException(e);
        }
    }

    /* метод оновлення Дати народження користувача */
    public void updateUserDateOfBirth(LocalDate dateOfBirth, int id) {
        try (Connection connection = dbManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Constants.UPDATE_USER_DATE_OF_BIRTH)) {
            preparedStatement.setObject(1, dateOfBirth);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("failed to update user`s date of birth", e);
            throw new RuntimeException(e);
        }

    }
}