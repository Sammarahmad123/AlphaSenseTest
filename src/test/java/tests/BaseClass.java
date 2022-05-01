package tests;


import PageObjects.Page;
import Util.Utils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseClass  {
    static AppiumDriver<MobileElement> driver;
    Properties props;
    Page page;
    WebDriverWait wait;
    Actions action;
    JavascriptExecutor js ;


    //getting initialized appium driver
    public static AppiumDriver  getDriver(){
        return driver;
    }



    @BeforeTest     //test setup method
    public void setup()  {
        try{
            props = Utils.readPropertiesFile(Utils.configPath);
            DesiredCapabilities caps =new DesiredCapabilities();
            caps.setCapability("platformName",props.getProperty(Utils.pName));
            caps.setCapability("platformVersion",props.getProperty(Utils.pVersion));
            caps.setCapability("deviceName",props.getProperty(Utils.deviceName));
            caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
            caps.setCapability(CapabilityType.BROWSER_NAME,props.getProperty(Utils.browserName));
            caps.setCapability("noReset",true);
            caps.setCapability("fullReset",false);

            driver = new AppiumDriver<MobileElement>(new URL("http://"+Utils.appiumIP+":"+Utils.appiumPortNo+"/wd/hub"),caps);
            driver.manage().timeouts().implicitlyWait(Utils.implicitWait, TimeUnit.SECONDS);
            wait = new WebDriverWait(driver, Utils.explicitWait);
            page=new Page();
            action = new Actions(driver);
            js =  (JavascriptExecutor) driver;
        }
        catch (Exception exp){
            System.out.println("ISSUE IS="+exp.getCause());
            System.out.println("MESSAGE IS="+exp.getMessage());
        }
    }

    @AfterTest  //tear down method
    public void tearDown(){
        driver.close();
        driver.quit();
    }
}
