package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReebokOrderPage extends AbstractPage{
    private static final String INVALID_EMAIL_MESSAGE = "Please enter a valid email address.";
    private static final String INVALID_PHONE_NUMBER_MESSAGE = "Please enter a valid phone number.";

    @FindBy(id = "firstName_shippingAddressCreateEditFormDiv_1")
    private WebElement firstNameField;

    @FindBy(id = "WC__ShoppingCartAddressEntryForm_shippingAddressCreateEditFormDiv_1_lastName_1")
    private WebElement lastNameField;

    @FindBy(id = "WC__ShoppingCartAddressEntryForm_shippingAddressCreateEditFormDiv_1_address1_1")
    private WebElement addressField;

    @FindBy(id = "WC__ShoppingCartAddressEntryForm_shippingAddressCreateEditFormDiv_1_address2_1")
    private WebElement apartmentField;

    @FindBy(id = "WC__ShoppingCartAddressEntryForm_shippingAddressCreateEditFormDiv_1_city_1")
    private WebElement cityField;

    @FindBy(id = "WC__ShoppingCartAddressEntryForm_shippingAddressCreateEditFormDiv_1_state_1")
    private WebElement stateSelect;

    @FindBy(id = "WC__ShoppingCartAddressEntryForm_shippingAddressCreateEditFormDiv_1_zipCode_1")
    private WebElement zipCodeField;

    @FindBy(id = "WC_ShoppingCartAddressEntryForm_emailr_1")
    private WebElement emailField;

    @FindBy(id = "WC__ShoppingCartAddressEntryForm_shippingAddressCreateEditFormDiv_1_phone1_1")
    private WebElement phoneNumberField;

    @FindBy(id = "WC_UnregisteredCheckout_links_4")
    private WebElement saveAndContinueButton;

    @FindBy(id = "checkout-open-payment")
    private WebElement continueToPaymentButton;

    @FindBy(id = "fieldErrorMessage_WC_ShoppingCartAddressEntryForm_emailr_1")
    private WebElement invalidEmailMessage;

    @FindBy(id = "fieldErrorMessage_WC__ShoppingCartAddressEntryForm_shippingAddressCreateEditFormDiv_1_phone1_1")
    private WebElement invalidPhoneNumberMessage;

    private final String payButton = "shippingBillingPageNext";

    protected ReebokOrderPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    protected ReebokOrderPage openPage() {
        return this;
    }

//    public ReebokOrderPage enterTheUserData(User user){
//        waitUntilVisibilityOf(firstNameField).sendKeys(user.getFirstName());
//        lastNameField.sendKeys(user.getLastName());
//        addressField.sendKeys(user.getAddress());
//        apartmentField.sendKeys(user.getApartment());
//        cityField.sendKeys(user.getCity());
//        zipCodeField.sendKeys(user.getZipCode());
//        emailField.sendKeys(user.getEmail());
//        phoneNumberField.sendKeys(user.getPhoneNumber());
//        new Select(stateSelect).selectByValue(user.getState());
//
//        return this;
//    }

    public ReebokOrderPage saveAndContinue(){
        waitUntilElementIsClickableAndClickAvoidModalWindow(saveAndContinueButton);

        return this;
    }

    public ReebokOrderPage continueToPayment(){
        waitUntilElementIsClickableAndClickAvoidModalWindow(continueToPaymentButton);

        return this;
    }

    public boolean isReadyToPay(){
        waitUntilPresenceOfElement(By.id(payButton));

        return !driver.findElements(By.id(payButton)).isEmpty();
    }

    public boolean isInvalidEmail(){
        waitUntilVisibilityOf(invalidEmailMessage);

        return invalidEmailMessage.getText().equals(INVALID_EMAIL_MESSAGE);
    }

    public boolean isInvalidPhoneNumber(){
        waitUntilVisibilityOf(invalidPhoneNumberMessage);

        return invalidPhoneNumberMessage.getText().equals(INVALID_PHONE_NUMBER_MESSAGE);
    }
}
