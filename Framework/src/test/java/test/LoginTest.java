package test;

import model.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.AccountPage;
import page.LoginPage;
import service.UserCreator;

public class LoginTest extends TestBase {

    @Test
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
}
