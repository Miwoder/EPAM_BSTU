package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import page.ReebokSneakerPage;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class WebDriverReebokTest {
    private WebDriver driver;
    private static ChromeDriverService service;
    private final String expectedWishListResult = "Zig Kinetica Shoes";
    private final String expectedCartResult =  "ZIG KINETICA SHOES";

    @BeforeClass
    public static void createAndStartService() throws IOException {
        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("C:\\TAT_EPAM_LABS\\ChromeDriver\\chromedriver.exe"))
                .usingAnyFreePort()
                .build();
        service.start();
    }

    @BeforeMethod(alwaysRun = true)
    public void driverSetup() throws IOException {
        System.setProperty("webdriver.chrome.driver","C:\\TAT_EPAM_LABS\\ChromeDriver\\chromedriver.exe");
        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("C:\\TAT_EPAM_LABS\\ChromeDriver\\chromedriver.exe"))
                .usingAnyFreePort()
                .build();
        service.start();
        driver = new ChromeDriver();
        driver.manage()
                .timeouts()
                .implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void addToWishListTest() {
        String wishListResult = new ReebokSneakerPage(driver)
                .addItemToWishList()
                .openWishListPage()
                .getWishListResult();
        Assert.assertEquals(wishListResult, expectedWishListResult);
    }

    @Test
    public void addToCartTest() {
        String cartResult = new ReebokSneakerPage(driver)
                .addItemToCart()
                .openCartListPage()
                .getCartResult();
        Assert.assertEquals(cartResult, expectedCartResult);
    }

    @AfterMethod(alwaysRun = true)
    public void driverShutDown(){
        driver.quit();
        driver=null;
        service.stop();
    }
}
