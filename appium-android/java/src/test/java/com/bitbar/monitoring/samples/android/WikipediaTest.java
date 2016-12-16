package com.bitbar.monitoring.samples.android;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
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

    private AndroidDriver driver;

    private static int counter;


    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Android Device");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),
                capabilities);
    }

    @Test
    public void runTest() throws Exception {
        //System.out.println(driver.getPageSource());

        screenshot("before_search");
        driver.findElement(By.xpath("//android.widget.TextView[@text='Search Wikipedia']")).sendKeys("Helsinki");

        try {
            Thread.sleep(10000);
        } catch(Exception ignore) {
        }

        assert(driver.getPageSource().contains("Capital city of Finland"));
        screenshot("after_search");
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