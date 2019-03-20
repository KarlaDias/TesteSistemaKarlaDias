package stepDefinitionsCenario03;

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

public class StepsCenario03 {
    private WebDriver driver;
    private NetshoesMainPage netshoesMainPage;
    private NetshoesSearchPage netshoesSearchPage;
    private NetshoesDetailProductPage netshoesDetailProductPage;
    private String nomePrimeiroProduto;

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
        netshoesSearchPage.esperarNomePrimeiroProdutoFicarVisivel();
    }

    @When("o usuário clica no nome do primeiro produto da lista")
    public void o_usuário_clica_no_nome_do_primeiro_produto_da_lista() {
        nomePrimeiroProduto = netshoesSearchPage.nomePrimeiroProduto();
        netshoesDetailProductPage = netshoesSearchPage.clicarNomePrimeiroProduto();
    }

    @Then("são exibidas as informações detalhadas do produto escolhido")
    public void são_exibidas_as_informações_detalhadas_do_produto_escolhido() {
        netshoesDetailProductPage.esperarDescricaoProdutoFicarVisivel();
    }

    @Then("a descrição contém o nome do produto escolhido")
    public void a_descrição_contém_o_nome_do_produto_escolhido() {
        Assert.assertTrue(netshoesDetailProductPage.descricaoProduto().contains(nomePrimeiroProduto));
    }

    @After
    public void depoisCenario(){
        driver.quit();
    }
}
