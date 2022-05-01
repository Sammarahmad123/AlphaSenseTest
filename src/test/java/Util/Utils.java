package Util;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import tests.BaseClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Utils extends BaseClass {

    //device configs
    public static String pName="platformName";
    public static String pVersion="platformVersion";
    public static String browserName="browserName";
    public static String deviceName="deviceName";
    public static String configPath="src/test/resources/config.properties";

    //waits values
    public static int implicitWait = 40;
    public static int explicitWait = 35;

    //appium configs
    public static String appiumIP = "0.0.0.0";
    public static String appiumPortNo = "6767";


    public static Properties readPropertiesFile(String fileName) throws IOException {
        FileInputStream fis = null;
        Properties prop = null;
        try {
            fis = new FileInputStream(fileName);
            prop = new Properties();
            prop.load(fis);
        } catch(FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        } finally {
            fis.close();
        }
        return prop;
    }

    private static String[] getLocatorFromWebElement(MobileElement element) {

        return (element.toString().split("->")[1].replaceFirst("(?s)(.*)\\]", "$1" + "")).split(":");
    }

    public static By getByFromElement(MobileElement element) {

        By by = null;
        String[] pathVariables = getLocatorFromWebElement(element);

        String selector = pathVariables[0].trim();
        String value = pathVariables[1].trim();

        switch (selector) {
            case "id":
                by = By.id(value);
                break;
            case "className":
                by = By.className(value);
                break;
            case "tagName":
                by = By.tagName(value);
                break;
            case "xpath":
                by = By.xpath(value);
                break;
            case "cssSelector":
                by = By.cssSelector(value);
                break;
            case "linkText":
                by = By.linkText(value);
                break;
            case "name":
                by = By.name(value);
                break;
            case "partialLinkText":
                by = By.partialLinkText(value);
                break;
            default:
                throw new IllegalStateException("locator : " + selector + " not found!!!");
        }
        return by;
    }


}
