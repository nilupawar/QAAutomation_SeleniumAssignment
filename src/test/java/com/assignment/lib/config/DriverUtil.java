package com.assignment.lib.config;

import com.assignment.lib.types.BrowserName;
import com.assignment.lib.util.Utility;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/*
 *  author : Nilesh Pawar
 * */
final class DriverUtil {
    private WebDriver driver;
    private BrowserName browserName;
    private Capabilities capabilities;
    private static final Logger LOGGER = LoggerFactory.getLogger(DriverUtil.class);

    public DriverUtil() {
        initialiseDriver();
        setTimeOut();
    }

    private void initialiseDriver() {
        //TODO - Improve this after workday assignment for your framework library to handle remote/grid execution
        if (TestConfig.getConfig("browserName").equalsIgnoreCase("CHROME")) {
            browserName = BrowserName.CHROME;
            WebDriverManager.chromedriver().setup();
            this.driver = new ChromeDriver();
        } else if (TestConfig.getConfig("browserName").equalsIgnoreCase("FIREFOX")) {
            browserName = BrowserName.FIREFOX;
            WebDriverManager.firefoxdriver().setup();
            this.driver = new FirefoxDriver();
        } else if (TestConfig.getConfig("browserName").equalsIgnoreCase("EDGE")) {
            browserName = BrowserName.EDGE;
            WebDriverManager.iedriver().setup();
            this.driver = new EdgeDriver();
        }
        if (Boolean.getBoolean("maximiseBrowserWhenOpen")) {
            this.driver.manage().window().maximize();
        }
    }

    private void setTimeOut() {
        driver.manage().timeouts().pageLoadTimeout(Utility.parseIntTestConfig("pageLoadTime") * 1000L, TimeUnit.MILLISECONDS);
        driver.manage().timeouts().implicitlyWait(Utility.parseIntTestConfig("objectLoadTime") * 1000L, TimeUnit.MILLISECONDS);
    }


    BrowserName getBrowserName() {
        return browserName;
    }

    //TODO - Not completely implemented
    Capabilities getCapabilities() {
        return capabilities;
    }

    WebDriver getDriverInstance() {
        return driver;
    }

}
