package page;

import model.Item;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import util.Resolver;

import java.util.List;

import static util.Resolver.resolveCost;
import static util.Resolver.resolveTemplate;

public class ReebokBagPage extends AbstractPage {
    private static final String SIZE_TEMPLATE = "size_S_%s";
    public static final String HOMEPAGE_URL = "https://www.reebok.com/us/cart";

    String itemNameTemplate = "//div[@data-auto-id=\"glass-cart-item-list\"]/div[1]/div[1]/div[%d]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/a/span";
    String numberOfItemTemplate = "//div[contains(@class,'product_title')]/h3/*[contains(@id, \"OrderItemDetailsf_div_2_\")]";
    String itemDeleteTemplate = "//div[@data-auto-id=\"glass-cart-item-list\"]/div[1]/div[1]/div[%d]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/button";
    String itemCostTemplate = "//div[@data-auto-id=\"glass-cart-item-list\"]/div[1]/div[1]/div[%d]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]";
    String itemSizeTemplate = "//div[@data-auto-id=\"glass-cart-item-list\"]/div[1]/div[1]/div[%d]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/span[2]";
    String countOfItemTemplate = "//div[@data-auto-id=\"glass-cart-item-list\"]/div[1]/div[1]/div[%d]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/select";

    @FindBy(xpath = "//button[@data-auto-id=\"add-to-bag\"]")
    WebElement addToCartButton;

    @FindBy(xpath = "//button[@data-auto-id=\\\"view-bag-desktop\\\"]")
    WebElement openBagButton;

    @FindBy(xpath = "//div[@class=\"search-icon___1MZ8G\"]")
    private WebElement searchButton;

    @FindBy(xpath = "//input[@data-auto-id=\"searchinput\"]")
    private WebElement searchField;

    @FindBy(xpath = "//span[@data-auto-id=\"glass-cart-summary-product-value\"]")
    private WebElement summaryCost;

    @FindBy(id = "promoCodeRegion_Label")
    private WebElement promoCodeLabel;

    @FindBy(id = "promoCode")
    private WebElement promoCodeField;

    @FindBy(id = "WC_SingleShipmentOrderTotalsSummary_td_13")
    private WebElement estimatedTotalCost;

    @FindBy(id = "WC_SingleShipmentOrderTotalsSummary_td_8")
    private WebElement shippingCost;

    @FindBy(xpath = "//button[@data-auto-id=\"glass-checkout-button-right-side\"]")
    private WebElement proceedToSecureCheckout;

    @Override
    protected ReebokBagPage openPage() {
        return this;
    }

    public ReebokBagPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public ReebokSearchResult search(String request){
        waitUntilElementIsClickableAndClickAvoidModalWindow(searchButton);
        waitUntilVisibilityOf(searchField).sendKeys(request);
        searchField.sendKeys(Keys.ENTER);

        return new ReebokSearchResult();
    }

    public Item getItem(int number) {
        WebElement itemName = waitUntilPresenceOfElement(By.xpath(resolveTemplate(itemNameTemplate, number)));
        WebElement itemCost = driver.findElements(By.xpath(resolveTemplate(itemCostTemplate, number))).get(number - 1);
        WebElement itemSize = driver.findElements(By.xpath(resolveTemplate(itemSizeTemplate, number))).get(number - 1);
        WebElement itemCount = driver.findElement(By.xpath(resolveTemplate(countOfItemTemplate, number)));
        Select select = new Select(itemCount);

        String name = itemName.getText();
        String size = itemSize.getText().toLowerCase();
        int cost = Resolver.resolveCost(itemCost.getText());
        int amount = Resolver.resolveInt(select.getFirstSelectedOption().getText());

        return Item.of(name, size, cost, amount);
    }

    public ReebokBagPage changeAmountOfItem(int itemNumber, int amountOfItem){
        WebElement itemCount = waitUntilPresenceOfElement(By.xpath(resolveTemplate(countOfItemTemplate, itemNumber)));
        WebElement itemCost = driver.findElements(By.xpath(resolveTemplate(itemCostTemplate, itemNumber)))
                .get(itemNumber - 1);
        Select select = new Select(itemCount);
        String startValue = itemCost.getText();

        select.selectByValue(Integer.toString(amountOfItem));

        waitUntilFieldIsChanged(itemCost, startValue);
        waitUntilAjaxCompleted();

        return this;
    }

    public ReebokBagPage enterPromoCode(String promoCode, int numberOfPromoItem){
        waitUntilVisibilityOf(promoCodeLabel).click();
        waitUntilVisibilityOf(promoCodeField).sendKeys(promoCode);
        WebElement itemCost = driver.findElements(By.xpath(resolveTemplate(itemCostTemplate, numberOfPromoItem)))
                .get(numberOfPromoItem - 1);

        String startValue = itemCost.getText();

        promoCodeField.sendKeys(Keys.ENTER);
        waitUntilFieldIsChanged(itemCost, startValue);

        return this;
    }

    public ReebokBagPage removeItem(int number){
        WebElement removeButton = waitUntilPresenceOfElement(By.xpath(resolveTemplate(itemDeleteTemplate, number)));

        removeButton.click();

        return this;
    }

    public ReebokOrderPage proceedPurchase(){
        waitUntilElementIsClickable(proceedToSecureCheckout).click();

        return new ReebokOrderPage(driver);
    }

    public boolean isEmpty(){
        waitUntilAjaxCompleted();
        List<WebElement> items = driver.findElements(By.xpath(numberOfItemTemplate));

        return items.isEmpty();
    }

    public int getSummaryCost(){
        return resolveCost(summaryCost.getText());
    }

    public int getEstimatedTotalCost(){
        return resolveCost(estimatedTotalCost.getText());
    }

    public int getShippingCost(){
        return resolveCost(shippingCost.getText());
    }
}