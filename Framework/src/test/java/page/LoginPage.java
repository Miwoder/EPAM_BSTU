package page;

import driver.DriverSingleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import service.TestDataReader;

public class LoginPage extends AbstractPage{
    private static final String LOGIN_LINK = TestDataReader.getTestData("test.data.user.login.link");

    @FindBy(id = "login-email")
    private WebElement loginInput;

    @FindBy(id = "login-password")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@type=\"submit\"]")
    private WebElement submitButton;

    @FindBy(xpath = "//a[@data-auto-id=\"personalInformation\"]")
    private WebElement goToPersonalInformationButton;

    @FindBy(xpath = "//button[@data-auto-id=\"go-to-registration-button\"]")
    private WebElement goToRegistrationButton;

    @FindBy(xpath = "//input[@name=\"firstName\"]")
    private WebElement firstNameField;
    public LoginPage(){
        super(DriverSingleton.getInstance());
    }

    @Override
    public LoginPage openPage()
    {
        driver.get(LOGIN_LINK);
        return this;
    }

    public LoginPage inputUserLogin(String login){
        loginInput.sendKeys(login);
        return this;
    }

    public LoginPage inputUserPassword(String password){
        passwordInput.sendKeys(password);
        return this;
    }

    public LoginPage clickSubmitButton(){
        submitButton.click();
        return this;
    }

    public AccountPage goToAccount() {
        waitUntilAjaxCompleted();
        waitUntilElementIsClickableAndClickAvoidModalWindow(goToPersonalInformationButton);
        return new AccountPage();
    }

    public RegistrationPage goToRegistration() {
        waitUntilAjaxCompleted();
        waitUntilElementIsClickableAndClickAvoidModalWindow(goToRegistrationButton);
        return new RegistrationPage();
    }
}