package page;

import driver.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import service.TestDataReader;

import static util.Resolver.resolveTemplate;

public class ReebokSearchResult extends AbstractPage {
    private static final String ITEM_LINK = TestDataReader.getTestData("test.data.first.link");
//    private static final String SIZE_TEMPLATE = "//div[@class=\"sizes___3Stmf\"]//span[contains(text(), \"%s\")]/ancestor::button";
    private static final String SIZE_TEMPLATE = "//div[@class=\"sizes___3Stmf\"]//span[contains(text(), \"7.5\")]/ancestor::button";
    private static final String COUNT_TEMPLATE = ".dk_options_inner > li:nth-child(%s)";

    @FindBy(xpath = "//button[@data-auto-id=\"add-to-bag\"]")
    WebElement addToCartButton;

    @FindBy(xpath = "//div[@class=\"scarcity-message___3reHV gl-vspace\"]")
    private WebElement errorField;

    @FindBy(xpath = "//div[@data-auto-id=\"wishlist-button\"]")
    WebElement addToWishListButton;

    @FindBy(xpath = "//a[@data-auto-id=\"cart-wishlist-icon-header\"]")
    WebElement goToWishListButton;

    @FindBy(xpath = "//button[@class=\"gl-cta gl-cta--primary gl-cta--full-width gl-vspace\"]")
    WebElement goToBagButton;

    @FindBy(xpath = "/html/body/div[2]/div/div/div/div/div[2]/div[2]/div[2]/section/div[1]/div[2]/button[1]")
    WebElement selectQuantity;

    @FindBy(xpath = "//div[@id='headerTopRight']/a[contains(@class,'searchButtonWrapper')]")
    private WebElement searchButton;

    @FindBy(xpath = "//input[@id='SimpleSearchForm_SearchTerm']")
    private WebElement searchField;

    @Override
    public ReebokSearchResult openPage() {
        driver.get(ITEM_LINK);
        return this;
    }

    public ReebokSearchResult setSize(String size){
        waitUntilElementIsClickableAndClickAvoidModalWindow(By.xpath(resolveTemplate(SIZE_TEMPLATE,size)));
        return this;
    }

    public String getErrorMessage() {
        return errorField.getText();
    }

    public ReebokSearchResult addToBag() {
        waitUntilElementIsClickableAndClickAvoidModalWindow(addToCartButton);
        return this;
    }

    public ReebokSearchResult addToWishList() {
        waitUntilElementIsClickableAndClickAvoidModalWindow(addToWishListButton);
        return this;
    }

    public ReebokBagPage openBag(){
        waitUntilAjaxCompleted();
        waitUntilElementIsClickableAndClickAvoidModalWindow(goToBagButton);
        return new ReebokBagPage();
    }

    public ReebokWishListPage openWishList(){
        waitUntilAjaxCompleted();
        waitUntilElementIsClickableAndClickAvoidModalWindow(goToWishListButton);
        return new ReebokWishListPage();
    }

    public ReebokSearchResult(){
        super(DriverSingleton.getInstance());
    }

    public ReebokSearchResult setCountOfItems(String count) {
        waitUntilElementIsClickableAndClickAvoidModalWindow(selectQuantity);
        waitUntilElementIsClickable(By.cssSelector(resolveTemplate(COUNT_TEMPLATE, count))).click();

        return this;
    }

    public ReebokSearchResult setCountOfItems(int count) {
        waitUntilElementIsClickableAndClickAvoidModalWindow(selectQuantity);
        waitUntilElementIsClickable(By.cssSelector(resolveTemplate(COUNT_TEMPLATE, Integer.toString(count)))).click();

        return this;
    }

    public ReebokSearchResult search(String request){
        waitUntilElementIsClickableAndClickAvoidModalWindow(searchButton);
        waitUntilVisibilityOf(searchField).sendKeys(request);
        searchField.sendKeys(Keys.ENTER);

        waitUntilAjaxCompleted();

        return new ReebokSearchResult();
    }
}