package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReebokSneakerPage {
    private static final String SNEAKER_URL = "https://www.reebok.ru/krossovki-reebok-classic-leather/FZ1188.html?forceSelSize=FZ1188_570";
    private final By addToWishListLocator = By.xpath("/html/body/div[2]/div/div/div/div/div[2]/div[2]/div[2]/section/div[3]/div/div");
    private final By goToWishListLocator = By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div/div[3]/div/div[2]/div[3]");
    private final By addToCartLocator = By.xpath("/html/body/div[2]/div/div/div/div/div[2]/div[2]/div[2]/section/div[3]/button");
    private final By addSizeLocator = By.xpath("/html/body/div[2]/div/div/div/div/div[2]/div[2]/div[2]/section/div[1]/div[2]/button[1]");
    private final By goToCartLocator = By.xpath("/html/body/div[3]/div/div/div/div[2]/div/section/div[3]/button/span");

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
