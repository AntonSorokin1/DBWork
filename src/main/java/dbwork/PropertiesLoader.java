package dbwork;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
    private static final Logger LOGGER = LogManager.getLogger();

    private PropertiesLoader() {
        // Private constructor
    }

    public static Properties getProperties(String pathToProperties) {
        Properties properties = new Properties();
        try (InputStream stream = PropertiesLoader.class.getClassLoader().getResourceAsStream(pathToProperties)) {
            properties.load(stream);
        }
        catch (IOException e) {
            LOGGER.error("Can not read properties...");
        }
        return properties;
    }
}
