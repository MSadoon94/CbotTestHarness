package com.sadoon.cbotbdd.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserStartPage {

    private final WebDriver driver;

    @FindBy(className = "title")
    private WebElement heading;

    @FindBy(id = "name")
    private WebElement name;

    @FindBy(id = "pass")
    private WebElement pass;

    @FindBy(id = "createButton")
    private WebElement createButton;

    @FindBy(id = "loginButton")
    private WebElement loginButton;

    @FindBy(className = "outcome")
    private WebElement submitOutcome;


    public UserStartPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getHeading() {
        return heading;
    }

    public WebElement getHomePageHeading() {
        WebDriverWait waitForRedirect = new WebDriverWait(driver, 5);

        waitForRedirect.until(ExpectedConditions.textToBePresentInElement(heading, "User Home"));

        return heading;
    }

    public WebElement getName() {
        return name;
    }

    public WebElement getPass() {
        return pass;
    }

    public WebElement getCreateButton() {
        return createButton;
    }

    public WebElement getLoginButton() {
        return loginButton;
    }

    public WebElement getSubmitOutcome() {
        WebDriverWait waitForSubmit = new WebDriverWait(driver, 5);

        //Wait until outcome is not blank.
        waitForSubmit.until(ExpectedConditions.not(
                ExpectedConditions.textToBe(By.className("outcome"), "")));

        return submitOutcome;
    }
}
