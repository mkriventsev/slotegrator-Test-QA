package com.slotegrator.ui.page_objects;

import com.slotegrator.ui.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    private final By loginSelector = By.id("UserLogin_username");
    private final By passwordSelector = By.id("UserLogin_password");
    private final By signInSelector = By.xpath("//div[@class=\"signin-body\"]" +
            "//input[@type=\"submit\"]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage fillAdminCreds(String login, String password) {
        WebElement loginField = driver.findElement(loginSelector);
        WebElement passwordField = driver.findElement(passwordSelector);

        loginField.sendKeys(login);
        passwordField.sendKeys(password);

        return new LoginPage(driver);
    }

    public LoginPage pressSignInButton() {
        WebElement signInButton = driver.findElement(signInSelector);

        signInButton.submit();

        return new LoginPage(driver);
    }


}