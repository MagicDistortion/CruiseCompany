import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import utils.ConfigurationManager;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class BaseTest {
    private static final String CONNECTION_URL = "jdbc:h2:mem:CruiseDB;MODE=MySQL;DATABASE_TO_LOWER=TRUE;CASE_INSENSITIVE_IDENTIFIERS=TRUE";

    @BeforeAll
    static void setUpDataBase() throws Exception {
        ConfigurationManager configurationManager = ConfigurationManager.getInstance();
        configurationManager.putConfigValue("db.url", CONNECTION_URL);
        configurationManager.putConfigValue("db.driver.class.name", "org.h2.Driver");

        byte[] bytes = Files.readAllBytes(Paths.get(BaseTest.class.getClassLoader().getResource("dbinit.sql").toURI()));
        String dbInitSql = new String(bytes, StandardCharsets.UTF_8);
        try (Connection connection = DriverManager.getConnection(CONNECTION_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(dbInitSql)) {
            preparedStatement.executeUpdate();
        }
    }
    @AfterAll
    static void dropTables() throws SQLException {
        try (Connection connection = DriverManager.getConnection(CONNECTION_URL);
             PreparedStatement preparedStatement = connection.prepareStatement("DROP ALL OBJECTS")) {
            preparedStatement.executeUpdate();
        }
    }

}
