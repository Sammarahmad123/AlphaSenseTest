package tests;

import PageObjects.Page;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Allure;
import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.commons.validator.routines.*;

import java.util.List;

public class Tests extends BaseClass {
    Page page;
    List<MobileElement> links; //global variable to access all valid links in the search results
    private int index;  //index to alpha sense link





    @Test(priority=1,description = "Open the app and search for the keyword ‘alphasense’. Make sure the search is giving the\n" +
            "results")
    public void testOne() throws InterruptedException {
        page=new Page();
        driver.get(Page.baseURL);
        driver.findElement(By.xpath(Page.readMoreBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(Page.agreeBtn)));
        Thread.sleep(2000); //without this wait/sleep cookie popup is not accepted
        driver.findElement(By.xpath(Page.agreeBtn)).click();
        driver.findElement(By.xpath(Page.searchField)).sendKeys(Page.searchText);
        action.sendKeys(Keys.RETURN);
        action.perform();
        Thread.sleep(2000);
        links = driver.findElements(By.xpath(Page.allLinks));
        int count = 0;   //count is used to get valid links count
        int i=0;
        for(MobileElement each : links)
        {
            UrlValidator defaultValidator = new UrlValidator(); // default schemes
            if (defaultValidator.isValid(each.getAttribute("aria-label"))) {
                System.out.println(each.getAttribute("aria-label")+" is a valid link");
                count++;
                if(each.getAttribute("aria-label").toString().equals(Page.correctLink)){
                    index =i;
                }
            }
            i++;

        }
        System.out.println("links count="+count);
        Allure.attachment("Logs","links count="+count);
        Assert.assertTrue(count  > 0 );     //search results should be greater than 0
    }

    @Test(priority=2,description ="Search for the keyword ‘alphasense’, open the first link from the results and verify the link\n" +
            "is loaded properly")
    public void testTwo()
    {
        System.out.println(index);
        links.get(index).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Page.alphaSenseLogoIdentifer)));
    }

    @Test(priority=3, description = "Search for the keyword ‘alphasense’ and scroll down to the bottom of the page and click\n" +
            "on ‘More results’ button")
    public void testThree() throws InterruptedException {
        driver.get(Page.alphaSenseQueryString);
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        List<MobileElement> searchCountwithoutSeeMore = driver.findElements(By.xpath(Page.resultCountIdentifier));
        driver.findElement(By.xpath(Page.seeMoreBtn)).click();
        Thread.sleep(2000);
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        List<MobileElement> searchCountwithSeeMore = driver.findElements(By.xpath(Page.resultCountIdentifier));
        System.out.println("Without="+searchCountwithoutSeeMore.size());
        System.out.println("With="+searchCountwithSeeMore.size());
        Assert.assertTrue(searchCountwithSeeMore.size() > searchCountwithoutSeeMore.size());
    }

}
