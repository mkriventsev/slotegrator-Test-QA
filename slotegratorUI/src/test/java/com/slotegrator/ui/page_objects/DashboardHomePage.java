package com.slotegrator.ui.page_objects;

import com.slotegrator.ui.base.BasePage;
import com.slotegrator.ui.params.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DashboardHomePage extends BasePage {
    private final By userButtonSelector = By.cssSelector("li.nav-profile");
    private final By pageLogoSelector = By.className("logo");
    private final By userNameSpan = By.xpath("//li[contains(@class, 'nav-profile')]//span");

    public DashboardHomePage(WebDriver driver) {
        super(driver);
    }

    public DashboardHomePage checkForLoginUserArtifact(String login) {

        assertTrue(driver.findElement(userNameSpan).getAttribute("innerText").contains(login));

        return new DashboardHomePage(driver);
    }

    public DashboardHomePage waitForDashboardHomeElements() {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        wait.until(ExpectedConditions.visibilityOfElementLocated(userButtonSelector));
        wait.until(ExpectedConditions.presenceOfElementLocated(pageLogoSelector));

        return new DashboardHomePage(driver);
    }

    public DashboardHomePage checkDashboardURL() {
        assertThat(driver.getCurrentUrl(), equalTo(Constants.DASHBOARD_URL));

        return new DashboardHomePage(driver);
    }
}
