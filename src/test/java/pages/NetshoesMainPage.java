package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NetshoesMainPage {
    private WebDriver driver;
    private String pageUrl="https://www.netshoes.com.br";

    public NetshoesMainPage(WebDriver driver) {
        this.driver = driver;
    }

    //Elementos
    private By campoBusca = By.cssSelector(".input-label #search-input");
    private By lupa = By.cssSelector(".input-label .ns-icon-search");
    private By carrinhoCompra = By.cssSelector("#header-content > header > div.breakpoint > section.count > a");

    //Interações
    public NetshoesMainPage accessarPagina(){
        driver.get(this.pageUrl);
        return this;
    }

    public NetshoesMainPage preencherCampoBusca(String produto){
        driver.findElement(campoBusca).sendKeys(produto);
        return this;
    }

   public NetshoesSearchPage clicarBotaoLupa(){
        driver.findElement(lupa).click();
        return new NetshoesSearchPage(driver);
    }

    public void esperarCarrinhoCompraFicarClicavel(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(carrinhoCompra)));
    }

    public NetshoesCartPage clicarCarrinhoCompra(){
        driver.findElement(carrinhoCompra).click();
        return new NetshoesCartPage(driver);
    }


}
