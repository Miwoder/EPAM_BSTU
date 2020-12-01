import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class WebDriverReebokTest {
    private WebDriver driver;
    private static ChromeDriverService service;

    @BeforeMethod(alwaysRun = true)
    public void driverSetup() throws IOException {
        System.setProperty("webdriver.chrome.driver","C:\\TAT_EPAM_LABS\\ChromeDriver\\chromedriver.exe");
        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("C:\\TAT_EPAM_LABS\\ChromeDriver\\chromedriver.exe"))
                .usingAnyFreePort()
                .build();
        service.start();
        driver = new ChromeDriver();
    }

    @Test
    public void addToWishListNewManBootsEngTest() {
        driver.get("https://www.reebok.com/us/reebok-nano-x-shoes/FV6672.html");
        WebElement addToWishListButton = driver.findElement(By.xpath("//div[@data-auto-id=\"wishlist-button\"]"));
        addToWishListButton.click();
        WebElement goToWishListButton = driver.findElement(By.xpath("//a[@data-auto-id=\"cart-wishlist-icon-header\"]"));
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
