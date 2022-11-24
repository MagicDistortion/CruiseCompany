package utils;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigurationManager {
    private static ConfigurationManager instance;
    private static final Map<String, String> config = new HashMap<>();
    final static Logger logger = Logger.getLogger(ConfigurationManager.class);

    /* приватний конструктор отримує налаштування для бд з properties файлу*/
    private ConfigurationManager() {
        try (InputStream stream = ConfigurationManager.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (stream != null) {
                Properties properties = new Properties();
                properties.load(stream);
                properties.forEach((key, value) -> putConfigValue((String) key, (String) value));
            }
        } catch (IOException e) {
            logger.error("failed to load configuration ", e);
        }
    }

    /* метод отримання синглтону ConfigurationManager */
    public static synchronized ConfigurationManager getInstance() {
        if (instance == null) instance = new ConfigurationManager();
        return instance;
    }

    public String getConfigValue(String key) {
        return config.get(key);
    }

    public void putConfigValue(String key, String value) {
        config.put(key, value);
    }
}
