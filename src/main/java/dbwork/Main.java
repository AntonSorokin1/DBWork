package dbwork;

import dbwork.db.DBManager;
import dbwork.entity.Τΰιλ;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Properties;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Properties PROPERTIES = PropertiesLoader.getProperties("db.properties");

    private static final String URL = PROPERTIES.getProperty("url");
    private static final String USERNAME = PROPERTIES.getProperty("username");
    private static final String PASSWORD = PROPERTIES.getProperty("password");
    private static final String DRIVER = PROPERTIES.getProperty("driverClassName");

    public static void main(String[] args) {
        try {
            // Get object from XML
            File xmlFile = new File("src/main/resources/sample.xml");
            XMLConverter xmlConverter = new XMLConverter();
            Τΰιλ file = (Τΰιλ) xmlConverter.toObject(xmlFile, Τΰιλ.class);

            // Open connection with database
            DBManager dbManager = new DBManager(DRIVER, URL, USERNAME, PASSWORD);
            dbManager.connect();
            // Insert object to DB
            dbManager.insert(file);

            // Close connection
            dbManager.disconnect();
        } catch (Exception e) {
            LOGGER.error("Connection failed...");
            LOGGER.error(e);
        }
    }
}
