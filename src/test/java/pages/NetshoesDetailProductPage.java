package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NetshoesDetailProductPage {
    private WebDriver driver;

    public NetshoesDetailProductPage(WebDriver driver) {
        this.driver = driver;
    }

    //Elementos
    private By descricaoProduto = By.cssSelector("#features > p");
    private By nomeProduto = By.cssSelector("#content > div:nth-child(3) > section > section.short-description > h1");
    private By botaoComprar = By.cssSelector("#buy-button-wrapper #buy-button-now");
    private By mensagemErro = By.cssSelector("#buy-button-wrapper #buy-error");

    //Interações

    public void esperarNomeProdutoFicarVisivel(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(nomeProduto)));
    }

    public void esperarDescricaoProdutoFicarVisivel(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(descricaoProduto)));
    }

    public String descricaoProduto(){ return driver.findElement(descricaoProduto).getText(); }

    public void esperarMensagemErroFicarVisivel(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(mensagemErro)));
    }

    public String mensagemErro(){
        return driver.findElement(mensagemErro).getText();
    }

    public void esperarBotaoComprarFicarClicavel(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(botaoComprar)));
    }

    public NetshoesDetailProductPage clicarBotaoComprar(){
        driver.findElement(botaoComprar).click();
        return this;
    }

}
