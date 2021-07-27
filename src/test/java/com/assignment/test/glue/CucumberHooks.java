package com.assignment.test.glue;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import com.assignment.lib.config.TestCase;

public class CucumberHooks {
    private Scenario scenario;
    private final TestCase testCase;

    @Before(order = 0)
    public void initialiseTest(Scenario scenario) {
        this.scenario = scenario;
        this.testCase.setCucumberScenarioTags(scenario);
    }

    @After(order = 0)
    public void cleanUp(){
          testCase.closeAndQuitBrowser();
    }

    public CucumberHooks(TestCase testCase) {
        this.testCase = testCase;
        System.out.println("Pico container called : " + testCase.hashCode());
    }
}
