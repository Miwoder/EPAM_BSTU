package page;

import driver.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ReebokHomePage extends AbstractPage {
        private static final String HOMEPAGE_URL = "https://www.reebok.com/us";

        @FindBy(xpath = "//div[@class=\"search-icon___1MZ8G\"]")
        private WebElement searchButton;

        @FindBy(xpath = "//input[@data-auto-id=\"searchinput\"]")
        private WebElement searchField;

        public ReebokHomePage(){
            super(DriverSingleton.getInstance());
        }

        @Override
        public ReebokHomePage openPage(){
            driver.navigate().to(HOMEPAGE_URL);
            waitUntilAjaxCompleted();
            return this;
        }

        public ReebokSearchResult search(String request){
            waitUntilElementIsClickableAndClickAvoidModalWindow(searchButton);
            waitUntilVisibilityOf(searchField).sendKeys(request);
            searchField.sendKeys(Keys.ENTER);
            return new ReebokSearchResult();
        }
}
