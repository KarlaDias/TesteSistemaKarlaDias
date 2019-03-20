package stepDefinitionsCenario04;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.NetshoesDetailProductPage;
import pages.NetshoesMainPage;
import pages.NetshoesSearchPage;

public class StepsCenario04 {
    private WebDriver driver;
    private NetshoesMainPage netshoesMainPage;
    private NetshoesSearchPage netshoesSearchPage;
    private NetshoesDetailProductPage netshoesDetailProductPage;

    @Before
    public void antesCenario(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/driver/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized"); // open Browser in maximized mode
        options.addArguments("disable-infobars"); // disabling infobars
        options.addArguments("--disable-extensions"); // disabling extensions
        options.addArguments("--disable-gpu"); // applicable to windows os only
        options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        options.addArguments("--no-sandbox"); // Bypass OS security model
        options.addArguments("--headless");

        driver = new ChromeDriver(options);
    }

    @Given("são exibidas as informações detalhadas de um {string} da Netshoes")
    public void são_exibidas_as_informações_detalhadas_de_um_da_Netshoes(String string) {
        netshoesMainPage = new NetshoesMainPage(driver);
        netshoesMainPage.accessarPagina();
        netshoesMainPage.preencherCampoBusca(string);
        netshoesSearchPage = netshoesMainPage.clicarBotaoLupa();
        netshoesSearchPage.esperarNomePrimeiroProdutoFicarVisivel();
        netshoesDetailProductPage = netshoesSearchPage.clicarNomePrimeiroProduto();
        netshoesDetailProductPage.esperarNomeProdutoFicarVisivel();
        netshoesDetailProductPage.esperarBotaoComprarFicarClicavel();
    }

    @When("o usuário clica no botão comprar")
    public void o_usuário_clica_no_botão_comprar() {
        netshoesDetailProductPage.clicarBotaoComprar();
    }

    @Then("é exibida a mensagem Selecione o tamanho")
    public void é_exibida_a_mensagem_Selecione_o_tamanho() {
        Assert.assertEquals(netshoesDetailProductPage.mensagemErro(),"Selecione o tamanho");
    }


    @After
    public void depoisCenario(){
        driver.quit();
    }
}

