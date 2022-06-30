package com.sadoon.cbotbdd.pages;

import com.sadoon.cbotbdd.util.Waiter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TradeWidgetSubPage {
    private final WebDriver driver;

    @FindBy(className = "tradeBox")
    private WebElement tradeBox;

    @FindBy(className = "tradeMetricBox")
    private WebElement tradeMetricBox;

    public TradeWidgetSubPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getTradeBox() {
        return tradeBox;
    }

    public WebElement getTradeMetricsBox(){
        return Waiter.waitUntilElementVisible(driver, tradeMetricBox);
    }

}
