package com.assignment.lib.core;


import com.assignment.lib.config.TestScenario;
import com.assignment.lib.config.TestConfig;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.net.MalformedURLException;
import java.net.URL;

/*
 *  author : Nilesh Pawar
 * */
public abstract class BasePage {
    protected WebDriver driver;
    private TestScenario testScenario;
    private String pageTitle;
    private static final Logger LOGGER = LoggerFactory.getLogger(TestConfig.class);

    protected BasePage(TestScenario testScenario, String pageTitle) {
        this.testScenario = testScenario;
        this.driver = testScenario.getDriver();
        this.pageTitle = pageTitle;
    }

    public void navigateTo(URL url) {
        driver.navigate().to(url);
    }

    public void navigateTo(String fullURL) throws MalformedURLException {
        navigateTo(new URL(fullURL));
    }

    public void navigateToApplication() throws MalformedURLException {
        navigateTo(new URL(TestConfig.getConfig("application_url")));
    }

    protected void takeScreenshot() {
    }

    protected void maximise() {
    }

    protected void closeAndQuitBrowser() {
    }

    protected void closeBrowserWindow() {
    }

    protected void getAlertBoxText() {
    }

    protected String getPageTitle() {
        return null;
    }

    public boolean isValidPage() {
        String title = driver.getTitle();
        if (!title.contains(pageTitle)) {
            System.out.println("Page title : actual " + title + ", expected {" + pageTitle + "}, Checks for contains");
            LOGGER.error("Page title : actual '{}' contains/equals expected '{}', Checks for contains", title, pageTitle);
            return false;
        }
        LOGGER.info("Page title : actual '{}' contains/equals expected '{}', Checks for contains", title, pageTitle);
        return true;
    }

    public void cucumberResultLog(String logMessage) {
        testScenario.cucumberResultLog(logMessage);
    }
}
