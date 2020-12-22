package page;

import driver.DriverSingleton;
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
    String itemNameTemplate = "//div[@data-auto-id=\"glass-cart-item-list\"]/div[1]/div[1]/div[%d]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/a/span";
    String itemCostTemplate = "//div[@data-auto-id=\"glass-cart-item-list\"]/div[1]/div[1]/div[%d]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]";
    String itemSizeTemplate = "//div[@data-auto-id=\"glass-cart-item-list\"]/div[1]/div[1]/div[%d]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/span[2]";
    String countOfItemTemplate = "//div[@data-auto-id=\"glass-cart-item-list\"]/div[1]/div[1]/div[%d]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/select";

    String numberOfItemTemplate = "//div[contains(@class,'product_title')]/h3/*[contains(@id, \"OrderItemDetailsf_div_2_\")]";
    String itemDeleteTemplate = "//div[@data-auto-id=\"glass-cart-item-list\"]/div[1]/div[1]/div[%d]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/button";


    @FindBy(xpath = "//span[@data-auto-id=\"glass-cart-summary-delivery-value\"]")
    WebElement deliveryCostField;

    @FindBy(xpath = "//input[@id=\"coupon-input\"]")
    WebElement promoField;

    @FindBy(xpath = "//button[@type=\"submit\"]")
    WebElement submitButton;

    @FindBy(xpath = "//div[@class=\"gl-callout__wrapper\"]")
    WebElement errorPromoField;

    @FindBy(xpath = "//button[@name=\"bluecoreCloseButton\"]")
    WebElement closeAddButton;

    @FindBy(xpath = "//button[@data-di-id=\"di-id-7e15119f-c9bd4e3\"]")
    WebElement openSelectAmountButton;

    @FindBy(xpath = "//button[@value=\"2\"]")
    WebElement selectAmountTwoButton;

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

    public ReebokBagPage(){
        super(DriverSingleton.getInstance());
    }

    public String getDeliveryCost(){
        waitUntilAjaxCompleted();
        return deliveryCostField.getText();
    }

    public String getPromoError(){
        waitUntilAjaxCompleted();
        waitUntilVisibilityOf(errorPromoField);
        return errorPromoField.getText();
    }

    public ReebokBagPage inputPromo(String promo){
        waitUntilAjaxCompleted();
        promoField.sendKeys(promo);
        return this;
    }

    public ReebokBagPage closeAddButton(){
        waitUntilElementIsClickable(closeAddButton);
        if(closeAddButton.isDisplayed())
            closeAddButton.click();
        return this;
    }

    public ReebokBagPage openSelectAmount(){
        waitUntilElementIsClickable(openSelectAmountButton).click();
        return this;
    }

    public ReebokBagPage selectAmountTwo(){
        waitUntilElementIsClickable(selectAmountTwoButton).click();
        return this;
    }

    public ReebokBagPage clickApplyButton(){
        waitUntilElementIsClickable(submitButton).click();
        return this;
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