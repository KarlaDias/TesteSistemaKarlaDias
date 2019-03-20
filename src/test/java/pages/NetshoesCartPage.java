package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NetshoesCartPage {
    private WebDriver driver;
    private String pageUrl="https://www.netshoes.com.br/cart";

    public NetshoesCartPage(WebDriver driver) {
        this.driver = driver;
    }

    //Elementos
    private By valorTotal = By.cssSelector("#content > section > div > section > table > tfoot > tr.cart__table__row.cart__table__row--medium > td.cart__table__column.cart__table__column--vertical-border.column-total > p.cart-price-text");

    //Interações
    public void esperarvalorTotalFicarVisivel(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(valorTotal)));
    }

    public double valorTotal(){
        String total = driver.findElement(valorTotal).getText();
        int i= total.lastIndexOf("$")+2;
        total = total.substring(i, total.length());
        total = total.replace(",",".");

        return Double.parseDouble(total);
    }
}
