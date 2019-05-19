package com.qa.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    private static Properties properties = null;
    private static final String PATH = "src/main/resources/properties/";

    private static void loadProperties(String fileName) throws IOException {
        if (properties != null) return;
        if (fileName == null) {
        }
        FileInputStream fis = new FileInputStream(PATH + "dev.properties");
        properties = new Properties();
        properties.load(fis);
    }

    public static String readProperty(String key) throws IOException {
        loadProperties(null);
        return properties.getProperty(key, null);
    }

}
