package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReebokSneakerPage {
    private static final String SNEAKER_URL = "https://www.reebok.ru/krossovki-reebok-classic-leather/FZ1188.html";
    private final By addToWishListLocator = By.xpath("/html/body/div[2]/div/div/div/div/div[2]/div[2]/div[2]/section/div[3]/div/div");
    private final By goToWishListLocator = By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div/div[3]/div/div[2]/div[3]");
    private WebDriver driver;

    public ReebokSneakerPage(WebDriver driver){
        this.driver = driver;
    }

    public ReebokSneakerPage addItemToWishList(){
        driver.get(SNEAKER_URL);
        WebElement addToWishListBtn = new WebDriverWait(driver,10)
                .until(ExpectedConditions.presenceOfElementLocated(addToWishListLocator));
        addToWishListBtn.click();
        return this;
    }

    public ReebokWishListPage openWishListPage(){
        WebElement goToWishListBtn = new WebDriverWait(driver,10)
                .until(ExpectedConditions.presenceOfElementLocated(goToWishListLocator));
        goToWishListBtn.click();
        return new ReebokWishListPage(driver);
    }
}
