package com.assignment.test.glue;

import com.assignment.lib.config.TestScenario;
import com.assignment.lib.exception.FrameworkException;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class CucumberHooks {
    private Scenario scenario;
    private final TestScenario testScenario;
    private final Logger LOGGER = LoggerFactory.getLogger(CucumberHooks.class);

    @Before(order = 0)
    public void initialiseTest(Scenario scenario) {
        LOGGER.info("-------------------------------------------------------------------------------");
        LOGGER.info("Starting execution for new test, having tags : " + scenario.getSourceTagNames());
        this.scenario = scenario;
        this.testScenario.setCucumberScenario(scenario);
    }

    @After(order = 0)
    public void cleanUp() {
        if (scenario.isFailed()) {
            try {
                scenario.embed(FileUtils.readFileToByteArray(testScenario.takeScreenShot()), "Test execution failed");
            } catch (IOException e) {
                e.printStackTrace();
                throw new FrameworkException(e.getMessage());
            }
        }
        testScenario.closeAndQuitBrowser();
    }

    public CucumberHooks(TestScenario testScenario) {
        this.testScenario = testScenario;
    }
}
