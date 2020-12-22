package page;

import driver.DriverSingleton;
import model.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import service.TestDataReader;
import util.Resolver;

import static util.Resolver.resolveTemplate;

public class ReebokWishListPage extends AbstractPage {
    private static final String WISH_LINK = TestDataReader.getTestData("test.data.wish.link");

    @FindBy(xpath = "//a[@class=\"name\"]")
    private WebElement nameField;

    @FindBy(xpath = "//span[@class=\"sale-price\"]")
    private WebElement priceField;

    @FindBy(xpath = "//a[@title=\"Remove\"]")
    private WebElement removeButton;

    public ReebokWishListPage() {
        super(DriverSingleton.getInstance());
    }

    @Override
    public ReebokWishListPage openPage() {
        driver.get(WISH_LINK);
        return this;
    }

    public String getName(){
        return nameField.getText();
    }

    public int getPrice(){
        return Integer.parseInt(priceField.getText());
    }

}
