package com.slotegrator.ui.page_objects;

import com.slotegrator.ui.base.BasePage;
import com.slotegrator.ui.params.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayersPage extends BasePage {

    private final By breadCrumbsTitleSelector = By.cssSelector(".breadcrumb-alt > li:nth-child(2) > a");
    private final By headerSelector = By.className("panel-heading");
    private final By gridSelector = By.id("payment-system-transaction-grid");
    private final By loadedGridSelector = By.xpath("//div[@id=\"payment-system-transaction-grid\" and @class=\"grid-view\"]");
    private int columnIndex;
    private String[] itemsText;

    public PlayersPage(WebDriver driver) {
        super(driver);
    }

    private static boolean isDataSorted(String[] itemsText, String type) {
        if (type.equals("asc")) {
            for (int i = 1; i < itemsText.length; i++) {
                if (itemsText[i - 1].compareToIgnoreCase(itemsText[i]) > 0)
                    return false;
            }
            return true;
        }

        if (type.equals("desc")) {
            for (int i = 1; i < itemsText.length; i++) {
                if (itemsText[i - 1].compareToIgnoreCase(itemsText[i]) < 0)
                    return false;
            }
            return true;
        }
        return false;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public PlayersPage checkPlayersURL() {
        assertThat(driver.getCurrentUrl(), equalTo(Constants.PLAYERS_URL));

        return new PlayersPage(driver);
    }

    public PlayersPage checkPlayersPageArtifact() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement titleInTheBreadcrumbs = driver.findElement(breadCrumbsTitleSelector);

        wait.until(ExpectedConditions.textToBePresentInElement(titleInTheBreadcrumbs, "Player management"));

        return new PlayersPage(driver);
    }

    public PlayersPage checkPlayersTable() {
        WebDriverWait wait = new WebDriverWait(driver, 30);

        wait.until(ExpectedConditions.visibilityOfElementLocated(headerSelector));
        wait.until(ExpectedConditions.visibilityOfElementLocated(gridSelector));

        return new PlayersPage(driver);
    }

    public PlayersPage waitForGridLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(loadedGridSelector));
        return new PlayersPage(driver);
    }

    public PlayersPage clickOnColumn(String column) {
        By sortColumnTitleSelector = By.xpath("//a[contains(@class,\"sort-link\") and text() = \"" + column + "\"]");
        WebElement sortColumnTitle = driver.findElement(sortColumnTitleSelector);

//        comment following line to check if sorting checker works.
        sortColumnTitle.click();

        return new PlayersPage(driver);
    }

    public PlayersPage getColumnIndex(String column) {
        By softLinkSelector = By.xpath("//th[.//a[contains(@class,\"sort-link\") and text() = \"" + column + "\"]]");
        By thSelector = By.tagName("th");
        WebElement softlink = driver.findElement(softLinkSelector);
        List<WebElement> softLinkTh = driver.findElements(thSelector);

        columnIndex = softLinkTh.indexOf(softlink) + 1;

        collectColumnData(columnIndex);

        return new PlayersPage(driver);
    }

    public PlayersPage collectColumnData(int columnIndex) {

        By itemsSelector = By.xpath("//div[@id=\"payment-system-transaction-grid\"]//tr[@class=\"odd\" or @class=\"even\"]//td[" + columnIndex + "]");
        List<WebElement> items = driver.findElements(itemsSelector);

        itemsText = new String[items.size()];

        for (int j = 0; j < itemsText.length; j++) {
            itemsText[j] = items.get(j).getText();
        }

        return new PlayersPage(driver);
    }

    public PlayersPage checkSorting(String type) {
        assertTrue(isDataSorted(itemsText, type));

        return new PlayersPage(driver);
    }
}