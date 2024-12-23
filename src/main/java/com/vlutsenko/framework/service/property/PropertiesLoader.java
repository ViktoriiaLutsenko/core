package com.vlutsenko.framework.service.property;

import com.vlutsenko.framework.service.resource.ResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

    private static final Logger log = LoggerFactory.getLogger(PropertiesLoader.class);

    private static final String DEFAULT_PROPERTIES = "config/default.properties";

    private static Properties properties;

    public static void configureProperties(){
        properties = loadProperties();
        for (String key : properties.stringPropertyNames()){
            if(System.getProperties().containsKey(key)){
                properties.setProperty(key, System.getProperty(key));
            }
        }

        log.info("Test properties");
        log.info("---------------------------------------");
        for (String key: properties.stringPropertyNames()){

            log.info("{}={}", key, properties.getProperty(key));
        }
        log.info("---------------------------------------");
    }

    public static String getProperty(String key){
        return properties.getProperty(key);
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try(InputStream stream = ResourceLoader.getResources(DEFAULT_PROPERTIES)) {
            properties.load(stream);
        } catch (IOException e) {
            log.error("Unable to load properties file {}", DEFAULT_PROPERTIES, e);
        }
        return properties;
    }
}
