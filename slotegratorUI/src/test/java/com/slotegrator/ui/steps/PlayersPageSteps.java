package com.slotegrator.ui.steps;

import com.slotegrator.ui.config.DriverBase;
import com.slotegrator.ui.page_objects.DashboardElementsObjects;
import com.slotegrator.ui.page_objects.DashboardHomePage;
import com.slotegrator.ui.page_objects.LoginPage;
import com.slotegrator.ui.page_objects.PlayersPage;
import com.slotegrator.ui.params.Constants;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class PlayersPageSteps extends DriverBase {

    WebDriver driver;
    private LoginPage loginPage;
    private DashboardHomePage dashboardHomePage;
    private DashboardElementsObjects dashboardElementsObjects;
    private PlayersPage playersPage;

    @Given("I logged in at dashboard home page")
    public void iLoggedInAtDashboardHomePages() {

        loginPage = new LoginPage(driver);

        loginPage.navigateToLoginPage();
        loginPage.fillAdminCreds(Constants.ADMIN_LOGIN, Constants.ADMIN_PASSWORD);
        loginPage.pressSignInButton();

        dashboardHomePage = new DashboardHomePage(driver);
        dashboardHomePage.waitForDashboardHomeElements();
        dashboardHomePage.checkDashboardURL();
        dashboardHomePage.checkForLoginUserArtifact(Constants.ADMIN_LOGIN);

    }

    @When("I click on the {string} tab in menus")
    public void iClickOnTheTabInMenus(String tabTitle) {
        dashboardElementsObjects = new DashboardElementsObjects(driver);
        dashboardElementsObjects.checkMenuIsExpanded();
        dashboardElementsObjects.clickMenuTab(tabTitle);
    }

    @Then("Sub-menu {string} is displayed")
    public void subMenuIsDisplayed(String tabTitle) {
        dashboardElementsObjects.checkSubMenuIsDisplayed(tabTitle);
    }

    @And("I click on the {string} sub-tab")
    public void iClickOnTheSubTab(String submenuTitle) {
        dashboardElementsObjects.clickSubMenuTab(submenuTitle);
    }

    @Then("I should be at the players page")
    public void iShouldBeAtThePlayersPage() {
        playersPage = new PlayersPage(driver);
        playersPage.checkPlayersPageArtifact();
        playersPage.checkPlayersURL();

    }

    @And("The table with the players loaded")
    public void theTableWithThePlayersLoaded() {
        playersPage.checkPlayersTable();
    }


    @Given("I am at the players page")
    public void iAmAtThePlayersPage() {
        playersPage = new PlayersPage(driver);
        playersPage.navigateToPlayersPage();
        playersPage.checkPlayersPageArtifact();
        playersPage.checkPlayersTable();
        playersPage.checkPlayersURL();

        //actually, this step is not required, but notable that first 400+  ascended ordered names and surnames
        // have an empty values.
        // So just to be sure you may run it with uncommenting following string
        // Also, it will extremely increase tests' execution time.
        //new Select(driver.findElement(By.cssSelector("select#pageSizePlayers"))).selectByVisibleText("500");

        playersPage.waitForGridLoaded();

    }

    @When("I click on the column {string}")
    public void iClickOnTheColumn(String column) {

        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        playersPage.clickOnColumn(column);
        playersPage.waitForGridLoaded();

    }

    @Then("Data in column {string} will be ordered {string}")
    public void dataInColumnWillBeOrdered(String column, String type) {

        playersPage.getColumnIndex(column);
        playersPage.collectColumnData(playersPage.getColumnIndex());
        playersPage.checkSorting(type);

    }

    @Before
    public void startUp() throws Exception {
        driver = getDriver();
    }

    @After
    public void tearDown(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./target/screenshots/" + scenario.getName() + " " + System.nanoTime() + ".png"));
            System.out.println("the Screenshot is taken");
        }

        if (driver != null) {
            driver.quit();
        }
    }
}