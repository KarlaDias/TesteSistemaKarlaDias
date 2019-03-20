package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/features",
        glue= "stepDefinitionsCenario01",
        tags={"@pesquisar-produto-nome"})

public class RunnerCenario01 {
}
