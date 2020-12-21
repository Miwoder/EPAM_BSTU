package test;

import model.Item;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.ReebokHomePage;
import page.ReebokSearchResult;
import page.ReebokWishListPage;
import service.ItemCreator;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ReebokCartTest extends TestBase {

    //@Test
    public void addToCartTest(){
        Item expectedItem = ItemCreator.withCredentialsFromProperty("first");
        Item item = new ReebokSearchResult()
                .openPage()
                .setSize(expectedItem.getSize())
                .addToCart()
                .openCart()
                .getItem(1);
        assertThat(item, equalTo(expectedItem));
    }

    //@Test
    public void addToWishListTest(){
        Item expectedItem = ItemCreator.withCredentialsFromProperty("first");
        ReebokWishListPage wishListPage = new ReebokSearchResult()
                .openPage()
                .addToWishList()
                .openWishList();
        assertThat(wishListPage.getName(), equalTo(expectedItem.getName()));
        //assertThat(wishListPage.getPrice(), equalTo(expectedItem.getCentPrice()));
    }

    //@Test
    public void addToCartWithoutSizeTest(){
        ReebokSearchResult reebokSearchResult = new ReebokSearchResult()
                .openPage()
                .addToCart();
        Assert.assertTrue(reebokSearchResult.isErrorMessageVisible());
    }
}
