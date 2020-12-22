package page;

import driver.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ReebokHomePage extends AbstractPage {
    private static final String HOMEPAGE_URL = "https://www.reebok.com/us";

    @FindBy(xpath = "//input[@aria-labelledby=\"undefined_label\"]")
    private WebElement emailField;

    @FindBy(xpath = "//button[@aria-label=\"Submit\"]")
    private WebElement submitEmailButton;

    @FindBy(xpath = "//div[@class=\"gl-form-hint gl-form-hint--error\"]")
    private WebElement errorEmailMessage;

    public ReebokHomePage inputEmail(String email){
        emailField.sendKeys(email);
        return this;
    }

    public ReebokHomePage clickSubmitEmailButton(){
        submitEmailButton.click();
        return this;
    }

    public boolean isErrorEmail(){
        return errorEmailMessage.isDisplayed();
    }

    public ReebokHomePage(){
        super(DriverSingleton.getInstance());
    }

    @Override
    public ReebokHomePage openPage(){
        driver.get(HOMEPAGE_URL);
        return this;
    }
}
