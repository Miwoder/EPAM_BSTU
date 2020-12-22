package page;

import driver.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.Resolver;

import java.util.List;
import java.util.regex.Pattern;

public class ReebokMenShoesPage extends AbstractPage{
    private static final String MEN_SHOES_URL = "https://www.reebok.com/us/men-cross_training-shoes";
    private final By ITEMS_PRICE_LOCATOR = By.xpath("//div[@class=\"gl-price-item gl-price-item--small notranslate\"]");

    @FindBy(xpath = "//button[@class=\"gl-dropdown-custom__select label dropdown-select\"][@title=\"Sort By\"]")
    private WebElement sortButton;

    @FindBy(xpath = "//button[@class=\"gl-dropdown-custom__option\"][@value=\"price-high-to-low\"]")
    private WebElement sortHighToLowButton;

    public ReebokMenShoesPage clickSortButton(){
        sortButton.click();
        return this;
    }

    public ReebokMenShoesPage clickHighToLowButton(){
        waitUntilAjaxCompleted();
        sortHighToLowButton.click();
        return this;
    }

    public List<Integer> getItemsPriceList(){
        List<Integer> priceList = Resolver.getIntegerPriceList(new WebDriverWait(driver,20)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(ITEMS_PRICE_LOCATOR)));
        return priceList;
    }

    public ReebokMenShoesPage(){
        super(DriverSingleton.getInstance());
    }

    @Override
    public ReebokMenShoesPage openPage(){
        driver.get(MEN_SHOES_URL);
        return this;
    }
}
