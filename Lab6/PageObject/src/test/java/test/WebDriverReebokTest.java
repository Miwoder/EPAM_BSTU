package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.ReebokSneakerPage;

public class WebDriverReebokTest {
    private WebDriver driver;
    private final String expectedWishListResult = "Zig Kinetica Shoes";
    private final String expectedCartResult =  "ZIG KINETICA SHOES";

    @BeforeMethod(alwaysRun = true)
    public void driverSetup(){
        System.setProperty("webdriver.chrome.driver","C:\\TAT_EPAM_LABS\\ChromeDriver\\chromedriver.exe");
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
