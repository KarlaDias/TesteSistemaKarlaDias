package stepDefinitionsCenario05;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.NetshoesCartPage;
import pages.NetshoesDetailProductPage;
import pages.NetshoesMainPage;
import pages.NetshoesSearchPage;

public class StepsCenario05 {
    private WebDriver driver;
    private NetshoesMainPage netshoesMainPage;
    private NetshoesSearchPage netshoesSearchPage;
    private NetshoesDetailProductPage netshoesDetailProductPage;
    private NetshoesCartPage netshoesCartPage;

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

    @Given("foi adicionado um {string} ao carrinho de compra da Netshoes")
    public void foi_adicionada_um_ao_carrinho_de_compra_da_Netshoes(String string) {
        netshoesMainPage = new NetshoesMainPage(driver);
        netshoesMainPage.accessarPagina();
        netshoesMainPage.preencherCampoBusca(string);
        netshoesSearchPage = netshoesMainPage.clicarBotaoLupa();
        netshoesSearchPage.esperarNomePrimeiroProdutoFicarVisivel();
        netshoesDetailProductPage = netshoesSearchPage.clicarNomePrimeiroProduto();
        netshoesDetailProductPage.esperarNomeProdutoFicarVisivel();
        netshoesDetailProductPage.esperarBotaoComprarFicarClicavel();
        netshoesDetailProductPage.clicarBotaoComprar();
    }

    @Given("o navegador está na página inicial da Netshoes")
    public void o_navegador_está_na_página_inicial_da_Netshoes() {
        netshoesMainPage.accessarPagina();
    }

    @When("o usuário clica no carrinho de compra")
    public void o_usuário_clica_no_carrinho_de_compra() {
        netshoesMainPage.esperarCarrinhoCompraFicarClicavel();
        netshoesCartPage=netshoesMainPage.clicarCarrinhoCompra();
    }

    @Then("é exibida a lista de produtos adicionados ao carrinho de comra")
    public void é_exibida_a_lista_de_produtos_adicionados_ao_carrinho_de_comra() {
        netshoesCartPage.esperarvalorTotalFicarVisivel();
    }

    @Then("o valor total do carrinho é maior que zero")
    public void o_valor_total_do_carrinho_é_maior_que_zero() {
        Assert.assertTrue(netshoesCartPage.valorTotal()>0);
    }

    @After
    public void depoisCenario(){
        driver.quit();
    }
}
