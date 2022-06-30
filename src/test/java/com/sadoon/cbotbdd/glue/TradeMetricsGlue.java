package com.sadoon.cbotbdd.glue;

import com.sadoon.cbotbdd.pages.TradeWidgetSubPage;
import com.sadoon.cbotbdd.pages.UserHomePage;
import com.sadoon.cbotbdd.util.TestListener;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TradeMetricsGlue {
    private WebDriver driver;
    private TradeWidgetSubPage page;
    private Actions actionsBuilder;

    public TradeMetricsGlue(TestListener listener) {
        this.driver = listener.getDriver();
        page = PageFactory.initElements(driver, TradeWidgetSubPage.class);
        actionsBuilder = new Actions(driver);
    }

    @Given("trade has been created")
    public void tradeHasBeenCreated() {
        assertThat(page.getTradeBox().isDisplayed(), is(true));
    }

    @When("trade is clicked on")
    public void tradeIsClickedOn() {
        page.getTradeBox().click();
    }

    @Then("user will see candle graph attached to trade metrics section")
    public void userWillSeeCandleGraphAttachedToTradeMetricsSection() {
        assertThat(page.getTradeMetricsBox().isDisplayed(), is(true));
    }
}
