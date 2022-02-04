package com.sadoon.cbotbdd.glue;

import com.sadoon.cbotbdd.glue.util.TestListener;
import com.sadoon.cbotbdd.glue.util.Waiter;
import com.sadoon.cbotbdd.pages.UserHomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class OrderExecutionGlue {

    private WebDriver driver;
    private UserHomePage page;

    public OrderExecutionGlue(TestListener listener) {
        this.driver = listener.getDriver();
        this.page = PageFactory.initElements(driver, UserHomePage.class);

    }

    @Given("user selects saved strategy and activates cbot")
    public void userSelectSavedStrategyAndActivatesCbot() {
        page.getStrategyDetails().click();
        Waiter.waitUntilElementVisible(driver, page.getStrategyCheckbox());
        page.getStrategyCheckbox().click();

        Waiter.waitForCssValueToMatchExpectation(
                driver, page.getCbotPowerButton(),
                "background-color", "rgba(255, 0, 0, 0.5)"
        );

        page.getCbotPowerButton().click();

        assertThat(page.getCbotPowerButton().getAttribute("data-power-status"), is("active"));
    }

    @When("potential entry point is found")
    public void potentialEntryPointIsFound() {

    }

    @Then("user will see a buy order placed message displayed")
    public void userWillSeeABuyOrderPlacedMessageDisplayed() {

    }
}
