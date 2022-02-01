package com.sadoon.cbotbdd.pages;

import com.sadoon.cbotbdd.glue.util.Waiter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UserStartPage {

    private final WebDriver driver;

    @FindBy(className = "title")
    private WebElement heading;

    @FindBy(id = "name")
    private WebElement name;

    @FindBy(id = "pass")
    private WebElement pass;

    @FindBy(id = "confirmPass")
    private WebElement confirmPass;

    @FindBy(id = "createButton")
    private WebElement createButton;

    @FindBy(id = "loginButton")
    private WebElement loginButton;

    @FindBy(id = "loginResponse")
    private WebElement loginResponse;

    @FindBy(id = "signupResponse")
    private WebElement signupResponse;

    public UserStartPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getHeading() {
        return heading;
    }

    public WebElement getHomePageHeading() {
        WebDriverWait waitForRedirect = new WebDriverWait(driver, Duration.ofSeconds(5));

        waitForRedirect.until(ExpectedConditions.textToBePresentInElement(heading, "User Home"));

        return heading;
    }

    public WebElement getName() {
        return name;
    }

    public WebElement getPass() {
        return pass;
    }

    public WebElement getConfirmPass() {
        return confirmPass;
    }

    public WebElement getCreateButton() {
        return createButton ;
    }

    public WebElement getLoginButton() {
        return loginButton;
    }

    public WebElement getLoginResponse() {
        Waiter.waitUntilOutcomeNotBlank(driver, loginResponse);
        return loginResponse;
    }

    public WebElement getSignupResponse() {
        Waiter.waitUntilOutcomeNotBlank(driver, signupResponse);
        return signupResponse;
    }
}
