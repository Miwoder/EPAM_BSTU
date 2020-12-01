package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReebokSneakerPage {
    private static final String SNEAKER_URL = "https://www.reebok.com/us/zig-kinetica-shoes/FW5289.html";
    private final By addToWishListLocator = By.xpath("//div[@data-auto-id=\"wishlist-button\"]");
    private final By goToWishListLocator = By.xpath("//a[@data-auto-id=\"cart-wishlist-icon-header\"]");
    private final By addToCartLocator = By.xpath("//button[@data-auto-id=\"add-to-bag\"]");
    private final By addSizeLocator = By.xpath("//button[@data-di-id=\"di-id-e8fd413a-43bcf44b\"]");
    private final By goToCartLocator = By.xpath("//a[@data-di-id=\"di-id-c981eed9-91374266\"]");

    private WebDriver driver;

    public ReebokSneakerPage(WebDriver driver){
        this.driver = driver;
    }

    public ReebokSneakerPage addItemToWishList(){
        driver.get(SNEAKER_URL);
        WebElement addToWishListButton = new WebDriverWait(driver,10)
                .until(ExpectedConditions.presenceOfElementLocated(addToWishListLocator));
        addToWishListButton.click();
        return this;
    }

    public ReebokWishListPage openWishListPage(){
        WebElement goToWishListButton = new WebDriverWait(driver,10)
                .until(ExpectedConditions.presenceOfElementLocated(goToWishListLocator));
        goToWishListButton.click();
        return new ReebokWishListPage(driver);
    }

    public ReebokSneakerPage addItemToCart(){
        driver.get(SNEAKER_URL);
        WebElement addSizeButton = new WebDriverWait(driver,10)
                .until(ExpectedConditions.presenceOfElementLocated(addSizeLocator));
        addSizeButton.click();
        WebElement addToCartButton = new WebDriverWait(driver,10)
                .until(ExpectedConditions.presenceOfElementLocated(addToCartLocator));
        addToCartButton.click();
        return this;
    }

    public ReebokCartPage openCartListPage(){
        WebElement goToCartButton = new WebDriverWait(driver,10)
                .until(ExpectedConditions.presenceOfElementLocated(goToCartLocator));
        goToCartButton.click();
        return new ReebokCartPage(driver);
    }
}
