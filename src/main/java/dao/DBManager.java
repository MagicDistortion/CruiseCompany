package dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.log4j.Logger;
import utils.ConfigurationManager;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Consumer;

/* Клас для роботи з БД*/
public class DBManager {
    private final static Logger logger = Logger.getLogger(DBManager.class);
    private static DBManager instance;
    private final DataSource dataSource;

    /* приватний конструктор DBManager , налаштовує конекшен до БД череш ConfigurationManager */
    private DBManager() {
        ConfigurationManager configInstance = ConfigurationManager.getInstance();
        String dbUrl = configInstance.getConfigValue("db.url");
        String dbDriver = configInstance.getConfigValue("db.driver.class.name");
        if (dbUrl == null || dbDriver == null) {
            logger.error("cannot connection to db");
            throw new RuntimeException("cannot connection to db");
        }
        try {
            HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.setJdbcUrl(dbUrl);
            hikariConfig.setMaximumPoolSize(100);
            hikariConfig.setDriverClassName(dbDriver);
            this.dataSource = new HikariDataSource(hikariConfig);

        } catch (Exception e) {
            logger.error("cannot connection to db", e);
            throw new RuntimeException("cannot connection to db", e);
        }
    }

    /* метод отримання синглтону DBManager */
    public static synchronized DBManager getInstance() {
        if (instance == null) instance = new DBManager();
        return instance;
    }

    /* метод отримання зв'язку з бд */
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /*Обгортання методу у транзакцію */
    public void inTransaction(Consumer<Connection> action) {
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            action.accept(connection);
            connection.commit();
        } catch (Exception e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    logger.error("failed to rollback connection", e);
                }
            }
            if (e instanceof RuntimeException) throw (RuntimeException) e;
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error("failed to close connection", e);
                }
            }
        }
    }
}
