package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Standard runner
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources",
        glue = "step.definitions",
        plugin = {"pretty"},
        tags = "@endpoints or (@random or @entries or @categories)"
)

public class TestRunner {
}

