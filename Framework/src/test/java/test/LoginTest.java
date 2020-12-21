package test;

import model.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.AccountPage;
import page.LoginPage;
import service.UserCreator;

import static org.hamcrest.MatcherAssert.assertThat;


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
        //assertThat(accountPage.getName(), equalTo(testUser.getName()));
        //assertThat(accountPage.getEmail(), equalTo(testUser.getEmail()));
        //assertThat(accountPage.getWelcome(), equalTo("My Account"));
        Assert.assertTrue(accountPage.isLogIn());

        //assertThat(accountPage.getSex(), equalTo(testUser.getSex()));
    }
}
