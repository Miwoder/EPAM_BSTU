package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReebokWishListPage {
    private WebDriver driver;
    private final By wishListResultLocator = By.xpath("//a[@data-di-id=\"di-id-ae1433ad-c51b9be6\"]");

    public ReebokWishListPage(WebDriver driver){
        this.driver = driver;
    }

    public String getWishListResult(){
        WebElement wishListResult = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(wishListResultLocator));
        return wishListResult.getText();
    }
}
