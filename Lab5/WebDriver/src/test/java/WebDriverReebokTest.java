import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WebDriverReebokTest {
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void driverSetup(){
        driver = new ChromeDriver();
    }

    @Test
    public void addToWishListNewManBootsTest() {
        driver.get("https://www.reebok.ru/krossovki-reebok-classic-leather/FZ1188.html");
        WebElement addToWishListButton = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[2]/div[2]/div[2]/section/div[3]/div/div"));
        addToWishListButton.click();
        WebElement goToWishListButton = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div/div[3]/div/div[2]/div[3]"));
        goToWishListButton.click();
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[3]/div[1]/a/span")));
        WebElement closeDialog = driver.findElement(By.xpath("/html/body/div[3]/div[1]/a/span"));
        closeDialog.click();
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"dwfrm_wishlist_items\"]/div[1]/div/a")).getText(),
                "Кроссовки Reebok Classic Leather");
    }

    @Test
    public void addToWishListNewManBootsEngTest() {
        driver.get("https://www.reebok.com/us/reebok-nano-x-shoes/FV6672.html");
        WebElement addToWishListButton = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[2]/div[2]/div[2]/section/div[3]/div/div"));
        addToWishListButton.click();
        WebElement goToWishListButton = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[1]/div/div[3]/div/div[2]/div[3]"));
        goToWishListButton.click();
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"dwfrm_wishlist_items\"]/div[1]/div/a")).getText(),
                        "Reebok Nano X Shoes");
    }

    @AfterMethod(alwaysRun = true)
    public void driverShutDown(){
        driver.quit();
        driver=null;
    }
}
