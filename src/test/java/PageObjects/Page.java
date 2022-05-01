package PageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import tests.BaseClass;

public class Page{

    //page locators
    public static String baseURL = "https://www.google.com/";

    public static String googleSearchField = "//input[@title=\"Search\"]";

    public static String readMoreBtn= "//div[text() = 'Read more']";

    public static String agreeBtn = "//div[text() = 'I agree']";

    public static String searchField = "//input[@type=\"search\"]";

    public static String allLinks = "//div[@role=\"link\"]" ;

    public static String correctLink = "https://www.alpha-sense.com" ;

    public static String alphaSenseLogoIdentifer = "//img[@id=\"mobile-logo\"]" ;

    public static String seeMoreBtn = "//span[contains(text(),'See more')]";

    public static String alphaSenseQueryString= "https://www.google.com/search?q=alphasense";

    public static String resultCountIdentifier = "//*[@role=\"presentation\"]";

    //keyword to search - test data
    public static String searchText = "alphasense";




}
