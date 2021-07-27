package com.assignment.lib.config;

import io.cucumber.core.api.Scenario;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/*
 *  author : Nilesh Pawar
 * */
public final class TestCase {
    private List<String> scenarioIDs;
    private final DriverUtil driverUtil;
    private WebDriver driver;
    private static final Logger LOGGER = LoggerFactory.getLogger(TestCase.class);

    public TestCase() {
        driverUtil = new DriverUtil();
        driver = driverUtil.getDriverInstance();
    }

    public void setCucumberScenarioTags(Scenario scenario) {
        scenarioIDs = new ArrayList<>(scenario.getSourceTagNames());
    }

    public void setJunitScenarioTags() {
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void closeAndQuitBrowser() {
        driver.close();
        driver.quit();
    }
}
