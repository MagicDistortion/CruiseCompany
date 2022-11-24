package dao;

import models.users.Admin;
import org.apache.log4j.Logger;
import utils.Constants;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminsDAO {
    private final static Logger logger = Logger.getLogger(AdminsDAO.class);
    private final DBManager dbManager = DBManager.getInstance();
    private final UsersDAO usersDAO = new UsersDAO();


    /* метод отримання Адмінів  */
    public List<Admin> findAllAdmins() {
        List<Admin> adminsList = new ArrayList<>();
        try (Connection connection = dbManager.getConnection();
             ResultSet resultSet = connection.prepareStatement(Constants.FIND_ALL_ADMINS).executeQuery()) {
            while (resultSet.next()) {
                Admin admin = new Admin(usersDAO.getUser(resultSet));
                adminsList.add(admin);
            }
        } catch (SQLException e) {
            logger.error("failed to get admins list", e);
            throw new RuntimeException(e);
        }
        return adminsList;
    }
}
