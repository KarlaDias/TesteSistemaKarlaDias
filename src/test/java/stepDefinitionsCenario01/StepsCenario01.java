package stepDefinitionsCenario01;

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

public class StepsCenario01 {

    private WebDriver driver;
    private NetshoesMainPage netshoesMainPage;
    NetshoesSearchPage netshoesSearchPage;

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
		//para funcionar no Jenkins é preciso informar o caminho do binário do chrome
		options.setBinary("/home/karla/usr/share/applications");	
		driver = new ChromeDriver(options);
   }

    @Given("o navegador está na página inicial da Netshoes")
    public void o_navegador_está_na_página_inicial_da_Netshoes() {
        netshoesMainPage = new NetshoesMainPage(driver);
        netshoesMainPage.accessarPagina();
    }

    @When("o usuário digita {string} no campo de busca")
    public void o_usuário_digita_no_campo_de_busca(String string) {
        netshoesMainPage.preencherCampoBusca(string);
    }

    @When("clica no botão de lupa")
    public void clica_no_botão_de_lupa() {
        netshoesSearchPage = netshoesMainPage.clicarBotaoLupa();
    }

    @Then("é exibida uma lista de produtos")
    public void é_exibida_uma_lista_de_produtos() {
        netshoesSearchPage.esperarTituloResultadoBuscaFicarVisivel();
    }

    @Then("o título da lista contém a palavra {string}")
    public void o_título_da_lista_contém_a_palavra(String string) {
        Assert.assertTrue(netshoesSearchPage.tituloResultadoBusca().contains(string));
    }

    @After
    public void depoisCenario(){
        driver.quit();
    }

}
