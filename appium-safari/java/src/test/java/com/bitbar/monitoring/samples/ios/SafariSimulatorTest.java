package com.bitbar.monitoring.samples.ios;


import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.TimeoutException;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 *
 *
 */
public class SafariSimulatorTest {

    private static IOSDriver driver;
    private static int picNo = 0;
    private static String testURL = "http://bitbar.github.io/testdroid-samples/";
    
    @BeforeClass
    public static void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "iPhone 5s");
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("platformVersion", "9.3");
        capabilities.setCapability("browserName", "safari");
        driver = new IOSDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"),
                capabilities);
        // open the test web page
        driver.get(testURL);
    }

    @Test
    public void testClickButtonPresent() throws Exception {
        driver.get(testURL);
        System.out.println("Taking screenshot of sample web app");
        waitButtonText("Click for answer");
        takeScreenshot("samples_page.png");
    }

    @Test
    public void testCurrentUrl() throws Exception {
        driver.get(testURL);
        String URL = driver.getCurrentUrl();
        System.out.println("Taking screenshot of current web page");
        takeScreenshot("github_sample.png");
        assertEquals(URL.contains("testdroid-samples"), true);
    }

    @Test
    public void testColorChange() throws Exception {
        driver.get(testURL);
        waitButtonText("Click for answer");
        System.out.println("Taking screenshot of bitbar.com");
        takeScreenshot("button-not-clicked.png");
        String buttonText = "Click for answer";
        // click button
        driver.findElement(By.xpath("//button[text()='"+ buttonText +"']")).click();
        takeScreenshot("button_clicked.png");
        driver.findElement(By.xpath("//button[@id='test_button' and contains(@style, 'rgb(127, 255, 0)')]"));
    }

    @Test
    public void testButtonAction() throws Exception {
        driver.get(testURL);
        System.out.println("Screenshot before test");
        takeScreenshot("not_clicked.png");
        String buttonText = "Click for answer";
        waitButtonText(buttonText);
        // button is present click it
        driver.findElement(By.xpath("//button[text()='"+ buttonText +"']")).click();
        takeScreenshot("button_clicked.png");
        driver.findElement(By.xpath("//*[@id='result_element' and contains(., 'Testdroid real device cloud')]"));
    }


    private void waitButtonText(String buttonText) throws TimeoutException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 40);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='"+ buttonText +"']")));
        } catch (TimeoutException e) {
            System.out.println("---> Button text not found in time! Taking additional screenshot.");
            String picName = buttonText.replace(' ', '_');
            takeScreenshot("error_button_not_found_"+ picName +".png");
            throw e;
        }
    }
    
    private void takeScreenshot(String name) {
        try {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File testScreenshot = new File("screenshots", getPicNo() +"_"+ name);
            FileUtils.copyFile(scrFile, testScreenshot);
            System.out.println("Screenshot stored to " + testScreenshot.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WebDriverException e) {
            e.printStackTrace();
        }
    }

    private static int getPicNo() {
        return ++picNo;
    }

    /**
     * For resetting the app between tests one should use the
     * resetApp() method. Not really needed for web apps when one can
     * always reload the page to start from scratch.
     */
    // @After
    // public void afterTest() throws Exception {
    //     driver.resetApp();
    // }
    
    @AfterClass
    public static void tearDown() throws Exception {
        driver.quit();
    }
}
