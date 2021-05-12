package com.sadoon.cbotbdd.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfileCreationPage {

    private final WebDriver driver;

    @FindBy(className = "title")
    private WebElement heading;

    @FindBy(id = "name")
    private WebElement name;

    @FindBy(id = "pass")
    private WebElement pass;

    @FindBy(id = "submit")
    private WebElement submitButton;
    @FindBy(className = "outcome")
    private WebElement submitOutcome;

    public ProfileCreationPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getHeading() {
        return heading;
    }

    public WebElement getName() {
        return name;
    }

    public WebElement getPass() {
        return pass;
    }

    public WebElement getSubmitButton() {
        return submitButton;
    }

    public WebElement getSubmitOutcome(){
       WebDriverWait waitForSubmit= new WebDriverWait(driver, 10);
       waitForSubmit.until(ExpectedConditions.not(ExpectedConditions.textToBe(By.className("outcome"), "")));
        return submitOutcome;
    }
}
