package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReebokCartPage {
    private WebDriver driver;
    private final By cartResultLocator = By.xpath("/html/body/div[2]/div/div/div/div/div[2]/div/main/div[2]/div/div/div/div/div/div[2]/div[1]/div[1]/div/div[1]/div/a/span");

    public ReebokCartPage(WebDriver driver){
        this.driver = driver;
    }

    public String getCartResult(){
        WebElement cartResult = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(cartResultLocator));
        return cartResult.getText();
    }
}
