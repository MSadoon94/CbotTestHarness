package com.sadoon.cbotbdd.pages;

import com.sadoon.cbotbdd.glue.util.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

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

    @FindBy(id = "strategyName")
    private WebElement strategyNameInput;

    @FindBy(id = "refineDetails")
    private WebElement refineDetails;

    @FindBy(id = "stopLossInput")
    private WebElement stopLossInput;

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
        Waiter.waitUntilOutcomeNotBlank(driver, cryptoValidity);
        return cryptoValidity;
    }

    public WebElement getSaveButton() {
        return saveButton;
    }

    public WebElement getSaveOutcome() {
        Waiter.waitUntilOutcomeNotBlank(driver, saveOutcome);
        return saveOutcome;
    }

    public Select getStrategySelect(){
        return new Select(driver.findElement(By.id("loadStrategiesSelect")));
    }

    public WebElement getStrategyNameInput() {
        return strategyNameInput;
    }

    public WebElement getStopLossInput() {
        return stopLossInput;
    }

    public WebElement getRefineDetails() {
        return refineDetails;
    }
}
