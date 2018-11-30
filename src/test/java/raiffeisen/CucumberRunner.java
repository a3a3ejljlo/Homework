package raiffeisen;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/"}, glue = {""},
        plugin = {"json:target/cucumber-report.json",
                "pretty",
                "reporter.AllureReporter"},
        tags = {"@web"})
public class CucumberRunner {
}