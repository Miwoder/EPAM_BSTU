package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.ReebokSneakerPage;

import java.io.File;
import java.io.IOException;

public class WebDriverReebokTest {
    private WebDriver driver;
    private static ChromeDriverService service;
    private final String expectedWishListResult = "Zig Kinetica Shoes";
    private final String expectedCartResult =  "ZIG KINETICA SHOES";

    @BeforeMethod(alwaysRun = true)
    public void driverSetup() throws IOException {
        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("C:\\TAT_EPAM_LABS\\ChromeDriver\\chromedriver.exe"))
                .usingAnyFreePort()
                .build();
        service.start();
        driver = new ChromeDriver();
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
    }

}
