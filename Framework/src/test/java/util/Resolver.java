package util;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Resolver {
    public static int resolveCost(String cost){
        return Integer.parseInt(String.join("", cost.trim().split("[.$]")));
    }

    public static int resolveInt(String string){
        return Integer.parseInt(string.trim());
    }

    public static String resolveTemplate(String template, String data){
        return String.format(template, data);
    }

    public static String resolveTemplate(String template, int data){
        return String.format(template, data);
    }

    public static int resolveDiscount(int cost, int discount){
        return cost - (int)(cost * (discount / 100.));
    }

    public static List<Integer> getIntegerPriceList(List<WebElement> webElementList){
        List<Integer> priceList = new ArrayList<>();
        priceList.add(Integer.parseInt(webElementList.get(0).getText().substring(1)));
        priceList.add(Integer.parseInt(webElementList.get(1).getText().substring(1)));
        return priceList;
    }
}
