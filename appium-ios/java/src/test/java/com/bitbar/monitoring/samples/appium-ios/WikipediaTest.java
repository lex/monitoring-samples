package com.bitbar.monitoring.samples.appium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.*;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import java.io.File;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.apache.commons.io.FileUtils;
import java.io.IOException;

import java.net.URL;

import static org.junit.Assert.*;

/**
 *
 * 
 */
public class WikipediaTest {

    private AppiumDriver<IOSElement> driver;

    private static int counter;


    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone 5s");
        capabilities.setCapability("platformVersion", "9.3");
        capabilities.setCapability("app", new File("Wikipedia Debug.app").getAbsolutePath());
        capabilities.setCapability("autoDismissAlerts", true);

        driver = new IOSDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),
                capabilities);
    }

    @Test
    public void runTest() throws Exception {

        screenshot("start");

        // Get to the next step
        driver.findElement(By.xpath("//UIAButton[contains(@name, 'GET STARTED')]")).click();
        screenshot("first_screen");

        driver.findElement(By.xpath("//UIAButton[contains(@name, 'CONTINUE')]")).click();
        screenshot("second_screen");

        driver.findElement(By.xpath("//UIAButton[contains(@name, 'CONTINUE')]")).click();
        screenshot("third_screen");

        driver.findElement(By.xpath("//UIAButton[contains(@name, 'search')]")).click();
        screenshot("before_search");

        System.out.println(driver.getPageSource());

        driver.findElement(By.xpath("//UIASearchBar[contains(@value,'Search Wikipedia')]")).sendKeys("Helsinki");

        screenshot("after_query");

        driver.findElement(By.xpath("//UIAButton[contains(@name, 'Search')]")).click();

        //TouchAction a2 = new TouchAction(driver);
        //a2.tap(100, 100).perform();

        System.out.println(driver.getPageSource());

        try {
            Thread.sleep(5000);
        } catch(Exception ignore) {
        }

        screenshot("after_search");

        assert(driver.getPageSource().contains("Capital city of Finland"));

        driver.findElement(By.xpath("//UIAStaticText[contains(@name,'Helsinki')]")).click();

        try {
            Thread.sleep(5000);
        } catch(Exception ignore) {
        }

        screenshot("after_selection");

        assert(driver.getPageSource().contains("Capital city of Finland"));
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }


    private File screenshot(String name) {
        counter = counter + 1;
        String fullFileName = System.getProperty("user.dir") + "/screenshots/" + getScreenshotsCounter() + "_" + name + ".png";

        System.out.println("Taking screenshot "+name);
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {

            FileUtils.forceMkdir(new File("screenshots"));
            File testScreenshot = new File(fullFileName);
            FileUtils.copyFile(scrFile, testScreenshot);
            System.out.println("Screenshot stored to " + testScreenshot.getAbsolutePath());

            return testScreenshot;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getScreenshotsCounter() {
        if (counter < 10) {
            return "0" + counter;
        } else {
            return String.valueOf(counter);
        }
    }

}