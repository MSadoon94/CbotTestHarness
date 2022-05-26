package com.sadoon.cbotbdd.pages;

import com.sadoon.cbotbdd.util.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class StrategyModalPage {
    private final WebDriver driver;

    @FindBy(id = "strategyExchangeInput")
    private WebElement exchangeInput;

    @FindBy(id = "baseInput")
    private WebElement baseInput;

    @FindBy(id = "quoteInput")
    private WebElement quoteInput;

    @FindBy(id = "getAssetPairDataResponse")
    private WebElement cryptoValidity;

    @FindBy(id = "saveStrategyButton")
    private WebElement saveButton;

    @FindBy(id = "saveStrategyResponse")
    private WebElement saveOutcome;

    @FindBy(id = "strategyName")
    private WebElement strategyNameInput;

    @FindBy(id = "stopLossInput")
    private WebElement stopLossInput;

    @FindBy(id = "maxPositionInput")
    private WebElement maxPositionInput;

    @FindBy(id = "targetProfitInput")
    private WebElement targetProfitInput;

    @FindBy(id = "movingStopLossInput")
    private WebElement movingStopLossInput;

    @FindBy(id = "maxLossInput")
    private WebElement maxLossInput;

    @FindBy(id = "entryInput")
    private WebElement entryInput;

    public StrategyModalPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getExchangeInput() {
        return exchangeInput;
    }

    public WebElement getBaseInput() {
        return baseInput;
    }

    public WebElement getQuoteInput() {
        return quoteInput;
    }

    public WebElement getCryptoValidity() {
       return Waiter.waitUntilOutcomeNotBlank(driver, cryptoValidity);
    }

    public WebElement getSaveButton() {
        return saveButton;
    }

    public WebElement getSaveOutcome() {
       return Waiter.waitUntilOutcomeNotBlank(driver, saveOutcome);
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


    public WebElement getMaxPositionInput() {
        return maxPositionInput;
    }

    public WebElement getTargetProfitInput() {
        return targetProfitInput;
    }

    public WebElement getMovingStopLossInput() {
        return movingStopLossInput;
    }

    public WebElement getMaxLossInput() {
        return maxLossInput;
    }

    public WebElement getEntryInput() {
        return entryInput;
    }
}
