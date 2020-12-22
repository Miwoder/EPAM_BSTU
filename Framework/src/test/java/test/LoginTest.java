package test;

import model.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.AccountPage;
import page.LoginPage;
import page.RegistrationPage;
import service.UserCreator;

public class LoginTest extends TestBase {

    //@Test
    public void loginTest() {
        User testUser= UserCreator.withCredentialsFromProperty();
        AccountPage accountPage = new LoginPage()
                .openPage()
                .inputUserLogin(testUser.getEmail())
                .inputUserPassword(testUser.getPassword())
                .clickSubmitButton()
                .goToAccount();
        Assert.assertTrue(accountPage.isLogIn());
    }

    //@Test
    public void incorrectEmailRegistrationTest() {
        User testUser= UserCreator.withCredentialsFromProperty();
        RegistrationPage registrationPage = new LoginPage()
                .openPage()
                .goToRegistration()
                .inputUserFirstName(testUser.getName())
                .inputUserLastName(testUser.getName())
                .inputUserEmail(testUser.getEmail())
                .inputUserPassword(testUser.getPassword())
                .clickTermsConfirmButton()
                .clickAgeConfirmButton()
                .clickSubmitButton();
        Assert.assertTrue(registrationPage.isErrorActive());
    }
}
