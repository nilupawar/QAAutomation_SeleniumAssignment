package com.assignment.lib.config;

import com.assignment.lib.util.Utility;
import io.cucumber.core.api.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/*
 *  author : Nilesh Pawar
 * */
public final class TestScenario {
    private Scenario scenario;
    private WebDriver driver;

    private static final Logger LOGGER = LoggerFactory.getLogger(TestScenario.class);

    public TestScenario() {
        this.driver = new DriverUtil().getDriverInstance();
    }

    public void setCucumberScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void closeAndQuitBrowser() {
        driver.close();
        driver.quit();
    }

    public File takeScreenShot() throws IOException {
        File desFile = new File("./target/" + Utility.getScreenshotFileName());
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, desFile);
        return desFile;
    }

    public void cucumberResultLog(String logMessage) {
        scenario.write(logMessage);
    }
}
