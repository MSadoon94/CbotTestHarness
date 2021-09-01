package com.sadoon.cbotbdd.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StrategyModalPage {
    private final WebDriver driver;

    @FindBy(id = "baseInput")
    private WebElement baseInput;

    @FindBy(id = "quoteInput")
    private WebElement quoteInput;

    @FindBy(id = "currencyPairOutcome")
    private WebElement currencyPairOutcome;

    public StrategyModalPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getBaseInput() {
        return baseInput;
    }

    public WebElement getQuoteInput() {
        return quoteInput;
    }

    public WebElement getCurrencyPairOutcome() {
        return currencyPairOutcome;
    }
}
