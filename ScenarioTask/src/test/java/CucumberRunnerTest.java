import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)

@CucumberOptions(
        plugin ="pretty",
        features= {"src/test/resources/customer.feature"},
        glue= {"ScenarioTask"})

public class CucumberRunnerTest {

}
