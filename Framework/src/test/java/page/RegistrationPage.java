package page;

import driver.DriverSingleton;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends AbstractPage{

    @FindBy(xpath = "//input[@name=\"email\"]")
    private WebElement emailField;

    @FindBy(xpath = "//input[@name=\"password\"]")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@name=\"firstName\"]")
    private WebElement firstNameField;

    @FindBy(xpath = "//input[@name=\"lastName\"]")
    private WebElement lastNameField;

    @FindBy(xpath = "//input[@name=\"ageConfirmation\"]")
    private WebElement ageConfirmButton;

    @FindBy(xpath = "//input[@name=\"terms\"]")
    private WebElement termsConfirmButton;

    @FindBy(xpath = "//div[@class=\"field__message___2XGW7 field__message--error___2CRpG gl-body--s\"]")
    private WebElement errorField;

    @FindBy(xpath = "//button[@type=\"submit\"]")
    private WebElement submitButton;

    @FindBy(xpath = "//button[@name=\"bluecoreCloseButton\"]")
    WebElement closeAddButton;

    public RegistrationPage(){
        super(DriverSingleton.getInstance());
    }

    @Override
    public RegistrationPage openPage()
    {
        return this;
    }

    public RegistrationPage inputUserFirstName(String firstName){
        waitUntilAjaxCompleted();
        firstNameField.sendKeys(firstName);
        return this;
    }

    public RegistrationPage closeAddButton(){
        waitUntilElementIsClickable(closeAddButton);
        if(closeAddButton.isDisplayed())
            closeAddButton.click();
        return this;
    }

    public RegistrationPage inputUserLastName(String lastName){
        waitUntilAjaxCompleted();
        lastNameField.sendKeys(lastName);
        return this;
    }

    public RegistrationPage inputUserEmail(String email){
        waitUntilAjaxCompleted();
        emailField.sendKeys(email);
        return this;
    }

    public RegistrationPage inputUserPassword(String password){
        waitUntilAjaxCompleted();
        passwordField.sendKeys(password);
        return this;
    }

    public RegistrationPage clickAgeConfirmButton(){
        ageConfirmButton.click();
        return this;
    }

    public RegistrationPage clickSubmitButton(){
        submitButton.click();
        return this;
    }

    public RegistrationPage clickTermsConfirmButton(){
        termsConfirmButton.click();
        return this;
    }

    public boolean isErrorActive(){
        waitUntilAjaxCompleted();
        waitUntilVisibilityOf(errorField);
        return errorField.isDisplayed();
    }

}