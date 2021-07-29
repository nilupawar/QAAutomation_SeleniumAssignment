package com.assignment.test.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(dryRun = false,
        features = "classpath:features",
        glue = "com.assignment.test.glue",
        monochrome = false,
        plugin = {"pretty"
                , "html:target/cucumber_html_output"
                , "json:target/cucumber_json_report.json"
                , "progress:target/cucumber_progress_report"
        }
)
public class TestRunner {
}
