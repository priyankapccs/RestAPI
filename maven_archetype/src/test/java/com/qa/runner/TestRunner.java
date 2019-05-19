package com.qa.runner;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = { "com.qa.definitions" },
        plugin = {"pretty", "html:target/cucumber", "json:target/cucumber-report.json"}
        )

public class TestRunner {
}
