import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.ReebokSneakerPage;

public class WebDriverReebokTest {
    private WebDriver driver;
    private final String expectedWishListResult =  "Кроссовки Reebok Classic Leather";

    @BeforeMethod(alwaysRun = true)
    public void driverSetup(){
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

    @AfterMethod(alwaysRun = true)
    public void driverShutDown(){
        driver.quit();
        driver=null;
    }
}
