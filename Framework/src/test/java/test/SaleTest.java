package test;

import model.Item;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.ReebokBagPage;
import page.ReebokHomePage;
import page.ReebokSearchResult;
import service.ItemCreator;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class SaleTest extends TestBase {

    @Test
    public void incorrectPromoTest(){
        Item expectedItem = ItemCreator.withCredentialsFromProperty("first");
        String expectedMessage = "Coupon code \"ECARD\" is unknown.";
        ReebokBagPage reebokBagPage = new ReebokSearchResult()
                .openPage()
                .setSize(expectedItem.getSize())
                .addToBag()
                .openBag()
                .inputPromo("ECARD")
                .clickApplyButton();
        assertThat(expectedMessage, equalTo(reebokBagPage.getPromoError()));
    }

    @Test
    public void freeDeliveryTest(){
        Item expectedItem = ItemCreator.withCredentialsFromProperty("first");
        String expectedMessage = "FREE";
        ReebokBagPage reebokBagPage = new ReebokSearchResult()
                .openPage()
                .setSize(expectedItem.getSize())
                .addToBag()
                .openBag();
        assertThat(expectedMessage, equalTo(reebokBagPage.getDeliveryCost()));
    }

    @Test
    public void incorrectEmailForSaleTest(){
        ReebokHomePage reebokHomePage = new ReebokHomePage()
                .openPage()
                .inputEmail("sssssssss")
                .clickSubmitEmailButton();
        Assert.assertTrue(reebokHomePage.isErrorEmail());
    }
}