package com.slotegrator.ui.page_objects;

import com.slotegrator.ui.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DashboardElementsObjects extends BasePage {
    private final By bodySelector = By.cssSelector("body");
    private final By menuBarIconSelector = By.xpath("//*[contains(@class, 'toggle-min')][.//*]");

    public DashboardElementsObjects(WebDriver driver) {
        super(driver);
    }

    public DashboardElementsObjects checkMenuIsExpanded() {
        WebElement body = driver.findElement(bodySelector);
        WebElement menuBarIcon = driver.findElement(menuBarIconSelector);

        if (body.getAttribute("class").contains("nav-min"))
            menuBarIcon.click();

        return new DashboardElementsObjects(driver);
    }

    public DashboardElementsObjects clickMenuTab(String tabTitle) {
        By menuTabSelector = By.xpath("//ul[@id=\"nav\"]//a[span[contains(text(),\"" + tabTitle + "\")]]");

        WebElement menuTab = driver.findElement(menuTabSelector);

        menuTab.click();

        return new DashboardElementsObjects(driver);
    }

    public DashboardElementsObjects checkSubMenuIsDisplayed(String tabTitle) {
        By menuTabSiblingUlSelector = By.xpath("//ul[@id=\"nav\"]//a[span[contains(text(),\"" + tabTitle + "\")]]/following-sibling::ul");
        WebElement menuTabSiblingUl = driver.findElement(menuTabSiblingUlSelector);

        assertTrue(menuTabSiblingUl.isDisplayed());

        return new DashboardElementsObjects(driver);
    }

    public DashboardElementsObjects clickSubMenuTab(String subMenuTabTitle) {
        By subMenuTabSelector = By.xpath("//li[@class=\"active\"]//a[contains(text(),\"" + subMenuTabTitle + "\")]");

        WebElement subMenuTab = driver.findElement(subMenuTabSelector);

        subMenuTab.click();

        return new DashboardElementsObjects(driver);
    }
}
