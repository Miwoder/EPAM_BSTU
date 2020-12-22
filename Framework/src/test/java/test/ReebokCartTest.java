package test;

import model.Item;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.ReebokSearchResult;
import page.ReebokWishListPage;
import service.ItemCreator;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ReebokCartTest extends TestBase {

    //@Test
    public void addToBagTest(){
        Item expectedItem = ItemCreator.withCredentialsFromProperty("first");
        Item item = new ReebokSearchResult()
                .openPage()
                .setSize(expectedItem.getSize())
                .addToBag()
                .openBag()
                .getItem(1);
        assertThat(item.getCentPrice(), equalTo(expectedItem.getCentPrice()*100));
        assertThat(item.getAmount(), equalTo(expectedItem.getAmount()));
        assertThat(item.getSize(), equalTo(expectedItem.getSize()+" (us men)"));
        assertThat(item.getName(), equalTo(expectedItem.getName().toUpperCase()));
    }

    //@Test
    public void addToWishListTest(){
        Item expectedItem = ItemCreator.withCredentialsFromProperty("first");
        ReebokWishListPage wishListPage = new ReebokSearchResult()
                .openPage()
                .addToWishList()
                .openWishList();
        assertThat(wishListPage.getName(), equalTo(expectedItem.getName()));
        assertThat(wishListPage.getPrice(), equalTo(expectedItem.getCentPrice()));
    }

    //@Test
    public void addToBagWithoutSizeTest(){
        String expectedMessage = "Please select your size";
        ReebokSearchResult reebokSearchResult = new ReebokSearchResult()
                .openPage()
                .addToBag();
        assertThat(expectedMessage, equalTo(reebokSearchResult.getErrorMessage()));
    }

    //@Test
    public void addTwoItemsToBagTest(){
        Item expectedItem = ItemCreator.withCredentialsFromProperty("first");
        expectedItem.setAmount(2);
        Item item = new ReebokSearchResult()
                .openPage()
                .setSize(expectedItem.getSize())
                .addToBag()
                .openBag()
                .openSelectAmount()
                .selectAmountTwo()
                .getItem(1);
        assertThat(item.getCentPrice(), equalTo(expectedItem.getCentPrice()*200));
        assertThat(item.getAmount(), equalTo(expectedItem.getAmount()));
        assertThat(item.getSize(), equalTo(expectedItem.getSize()+" (us men)"));
        assertThat(item.getName(), equalTo(expectedItem.getName().toUpperCase()));
    }
}
