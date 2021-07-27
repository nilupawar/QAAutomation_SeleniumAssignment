package com.assignment.lib.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/*
 *  author : Nilesh Pawar
 * */
public final class TestConfig {
    private static TestConfig testConfig;
    private Properties properties = new Properties();
    private static final Logger LOGGER = LoggerFactory.getLogger(TestConfig.class);

    private TestConfig() {
        System.out.println("Constructor called");
        try (InputStream input = TestConfig.class.getClassLoader().getResourceAsStream("TestConfig.properties")) {
            properties.load(input);
            properties.forEach((key, value) -> System.setProperty(key.toString(), value.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static TestConfig getInstance() {
        if (testConfig == null) {
            testConfig = new TestConfig();
        }
        return testConfig;
    }

    public static String getConfig(String propertyName) {
        return getInstance().properties.getProperty(propertyName);
    }
}
