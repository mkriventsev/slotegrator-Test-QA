package com.slotegrator.ui.config;

import com.slotegrator.ui.config.DriverFactory;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DriverBase {

    protected RemoteWebDriver driver;
    private String currentTestName;

    public static RemoteWebDriver getDriver() throws Exception {
        return new DriverFactory().getDriver();

    }

    public static void waitForElement(WebDriver driver, final By by) {
        Wait<WebDriver> wait = new WebDriverWait(driver, 10);
        wait.until((ExpectedCondition<Boolean>) d -> d.findElement(by).isDisplayed());

        /*wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return driver.findElement(by).isDisplayed();
            }
        });*/
    }

//    @BeforeAll
//    public void setup() throws Exception {
//        driver = getDriver();
//    }
//
//    @AfterAll()
//    public void clearCookies() {
//        try {
//            driver.manage().deleteAllCookies();
//        } catch (Exception ignored) {
//            System.out.println("Unable to clear cookies, driver object is not viable...");
//        }
//
//        System.out.println("quit");
//        driver.quit();
//    }

    protected boolean waitForJSandJQueryToLoad(WebDriver driver) {

        WebDriverWait wait = new WebDriverWait(driver, 30);

        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver d) {
                try {
                    return ((Long) ((JavascriptExecutor) d).executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    // no jQuery present
                    return true;
                }
            }
        };

        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver d) {
                return ((JavascriptExecutor) d).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };

        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }
}