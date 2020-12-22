package page;

import driver.DriverSingleton;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import service.TestDataReader;

public class AccountPage extends AbstractPage {
    private static final String PROFILE_LINK = TestDataReader.getTestData("test.data.user.profile.link");

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/div/div/div[2]/div/div/div[1]/div/div[1]/div[2]/div")
    private WebElement nameField;

    @FindBy(xpath = "//div[@class=\"toUpperCase___1jAec gl-vspace-bpall-small wrapText___1Jhsn\"][3]")
    private WebElement sexField;

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/div/div/div[2]/div/div/div[1]/div/div[1]/div[2]/div")
    private WebElement emailField;

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/div/div/div[2]/div/div/div[1]/div/div[3]/div")
    private WebElement idField;

    @FindBy(xpath = "//a[@data-auto-id=\"myAccount\"]")
    private WebElement welcomeField;

    public AccountPage() {
        super(DriverSingleton.getInstance());
    }

    @Override
    public AccountPage openPage() {
        driver.get(PROFILE_LINK);
        return this;
    }

    public String getName(){
        return nameField.getText();
    }

    public String getEmail(){
        return emailField.getText();
    }

    public String getSex(){
        return sexField.getText();
    }

    public String getId(){
        return idField.getText();
    }

    public boolean isLogIn(){
        return welcomeField.isDisplayed();
    }
}
