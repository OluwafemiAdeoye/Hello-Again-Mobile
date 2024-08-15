package org.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {

    private static final Logger logger = LogManager.getLogger(PropertyManager.class);
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = PropertyManager.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                logger.error("Sorry, unable to find config.properties");
            } else {
                properties.load(input);
                properties.forEach((key, value) -> logger.info("Loaded property: " + key + " = " + value));
            }
        } catch (IOException ex) {
            logger.error("Error loading properties file", ex);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}