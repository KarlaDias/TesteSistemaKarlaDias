package stepDefinitionsCenario02;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.NetshoesMainPage;
import pages.NetshoesSearchPage;

public class StepsCenario02 {
    private WebDriver driver;
    private NetshoesMainPage netshoesMainPage;
    private NetshoesSearchPage netshoesSearchPage;
    private Double valorInferiorMenorFaixaPreco;
    private Double valorSuperiorMenorFaixaPreco;
    private Double precoPrimeiroProduto;

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

    @Given("os resultados de pesquisa da Netshoes para {string} são exibidos")
    public void os_resultados_de_pesquisa_da_Netshoes_para_são_exibidos(String string) {
        netshoesMainPage = new NetshoesMainPage(driver);
        netshoesMainPage.accessarPagina();
        netshoesMainPage.preencherCampoBusca(string);
        netshoesSearchPage = netshoesMainPage.clicarBotaoLupa();
    }

    @When("o usuário escolhe a menor faixa de preço")
    public void o_usuário_escolhe_a_menor_faixa_de_preço() {
        netshoesSearchPage.esperarMenorFaixaPrecoFicarVisivel();
        valorInferiorMenorFaixaPreco=netshoesSearchPage.valorInferiorMenorFaixaPreco();
        valorSuperiorMenorFaixaPreco=netshoesSearchPage.valorSuperiorMenorFaixaPreco();
        netshoesSearchPage.clicarMenorFaixaPreco();
    }

    @Then("é exibida uma lista de produtos")
    public void é_exibida_uma_lista_de_produtos() {
        netshoesSearchPage.esperarPrecoPrimeiroProdutoFicarVisivel();
        precoPrimeiroProduto = netshoesSearchPage.precoPrimeiroProduto();
    }

    @Then("o preço do primeiro produto é maior ou igual ao valor inferior da faixa")
    public void o_preço_do_primeiro_produto_é_maior_ou_igual_ao_valor_inferior_da_faixa() {
        Assert.assertTrue(precoPrimeiroProduto>=valorInferiorMenorFaixaPreco);
    }

    @Then("o preço do primeiro produto é menor ou igual ao valor superior da faixa")
    public void o_preço_do_primeiro_produto_é_menor_ou_igual_ao_valor_superior_da_faixa() {
        Assert.assertTrue(precoPrimeiroProduto<=valorSuperiorMenorFaixaPreco);
    }

    @After
    public void depoisCenario(){
        driver.quit();
    }

}
