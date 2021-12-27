package com.sadoon.cbotbdd.glue;

import com.sadoon.cbotbdd.pages.StrategyModalPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RefineStrategyGlue {

    private WebDriver driver;
    private StrategyModalPage page;
    private Map<String, WebElement> validation;

    public RefineStrategyGlue(WebDriver driver) {
        this.driver = driver;
        this.page = PageFactory.initElements(driver, StrategyModalPage.class);
        this.validation = Map.of(
                "stop-loss", page.getStopLossInput()
        );
    }

    @When("user enters {string} in stop loss entry box")
    public void userEntersInStopLossEntryBox(String arg0) {
        page.getStopLossInput().sendKeys(arg0);
    }

    @Then("user will see {string} next to {string} entry box")
    public void userWillSeeNextToEntryBox(String arg0, String arg1) {
        assertThat(validation.get(arg1).getText(), is(arg0));
    }
}
