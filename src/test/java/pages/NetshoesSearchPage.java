package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NetshoesSearchPage {
    private WebDriver driver;
    private String pageUrl="https://www.netshoes.com.br/busca?nsCat";

    public NetshoesSearchPage(WebDriver driver) {
        this.driver = driver;
    }

    //Elementos
    private By tituloResultadoBusca = By.cssSelector("#content > section > section.search-list.search-list--3 > h1");
    private By nomePrimeiroProduto = By.cssSelector("#item-list > div.wrapper > div:nth-child(2) > div.n-block > a > span");
    private By menorFaixaPreco = By.cssSelector("#aggregate-salePrice > div > a:nth-child(2)");
    private By precoPrimeiroProduto = By.cssSelector("#item-list > div.wrapper > div:nth-child(2) > section.ff-ajax-price > a > div.pr > span > span > span");

    //Interações
    public void esperarTituloResultadoBuscaFicarVisivel(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(tituloResultadoBusca)));
    }

    public String tituloResultadoBusca(){ return driver.findElement(tituloResultadoBusca).getText(); }

    public void esperarNomePrimeiroProdutoFicarVisivel(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(nomePrimeiroProduto)));
    }

    public NetshoesDetailProductPage clicarNomePrimeiroProduto(){
        driver.findElement(nomePrimeiroProduto).click();
        return new NetshoesDetailProductPage(driver);
    }

    public String nomePrimeiroProduto(){
        return driver.findElement(nomePrimeiroProduto).getText();
    }

    public void esperarMenorFaixaPrecoFicarVisivel(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(menorFaixaPreco)));
    }

    public double valorInferiorMenorFaixaPreco(){
      String link = driver.findElement(menorFaixaPreco).getAttribute("href");
      int i=link.lastIndexOf("=")+1;
      String faixa = link.substring(i, link.length());
      String[] valores = faixa.split("-");
      return Double.parseDouble(valores[0])/100;
    }

    public double valorSuperiorMenorFaixaPreco(){
        String link = driver.findElement(menorFaixaPreco).getAttribute("href");
        int i=link.lastIndexOf("=")+1;
        String faixa = link.substring(i, link.length());
        String[] valores = faixa.split("-");
        return Double.parseDouble(valores[1])/100;
    }

   public NetshoesSearchPage clicarMenorFaixaPreco(){
        driver.findElement(menorFaixaPreco).click();
        return this;
   }

    public void esperarPrecoPrimeiroProdutoFicarVisivel(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(precoPrimeiroProduto)));
    }

   public Double precoPrimeiroProduto(){
       return Double.parseDouble( driver.findElement(precoPrimeiroProduto).getAttribute("content"));
   }

}
