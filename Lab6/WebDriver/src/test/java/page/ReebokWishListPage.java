package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReebokWishListPage {
    private WebDriver driver;
    private final By closeDialogLocator = By.xpath("/html/body/div[3]/div[1]/a/span");
    private final By wishListResultLocator = By.xpath("//*[@id=\"dwfrm_wishlist_items\"]/div[1]/div/a");

    public ReebokWishListPage(WebDriver driver){
        this.driver = driver;
    }

    public String getWishListResult(){
        WebElement closeDialogButton = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(closeDialogLocator));
        closeDialogButton.click();
        WebElement wishListResult = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(wishListResultLocator));
        return wishListResult.getText();
    }
}
