package com.sadoon.cbotbdd.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StrategyModalPage {
    private final WebDriver driver;

    @FindBy(id = "baseInput")
    private WebElement baseInput;

    @FindBy(id = "quoteInput")
    private WebElement quoteInput;

    @FindBy(id = "selectCrypto")
    private WebElement cryptoValidity;

    @FindBy(id = "saveButton")
    private WebElement saveButton;

    @FindBy(id = "saveModal")
    private WebElement saveOutcome;

    public StrategyModalPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getBaseInput() {
        return baseInput;
    }

    public WebElement getQuoteInput() {
        return quoteInput;
    }

    public WebElement getCryptoValidity() {
        WebDriverWait waitForResults = new WebDriverWait(driver, 5);

        //Wait until outcome is not blank.
        waitForResults.until(ExpectedConditions.not(
                ExpectedConditions.textToBe(By.id(cryptoValidity.getAttribute("id")), "")));

        return cryptoValidity;
    }

    public WebElement getSaveButton() {
        return saveButton;
    }

    public WebElement getSaveOutcome() {
        WebDriverWait waitForResults = new WebDriverWait(driver, 5);

        //Wait until outcome is not blank.
        waitForResults.until(ExpectedConditions.not(
                ExpectedConditions.textToBe(By.id(saveOutcome.getAttribute("id")), "")));

        return saveOutcome;
    }
}
