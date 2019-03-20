package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/features",
        glue= "stepDefinitionsCenario05",
        tags={"@consultar-carrinho-compra"})
public class RunnerCenario05 {

}
