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


public class CbotActivationGlue {

    private WebDriver driver;
    private UserHomePage page;

    public CbotActivationGlue(TestListener listener) {
        this.driver = listener.getDriver();
        page = PageFactory.initElements(driver, UserHomePage.class);
    }

    @Given("user selects strategy for cbot to use")
    public void userSelectsStrategyForCbotToUse(){
        page.getStrategyActDetails().click();

        Waiter.waitUntilElementVisible(driver, page.getStrategyCheckbox());
        page.getStrategyCheckbox().click();
    }

    @When("user clicks on red start button")
    public void userClicksOnRedStartButton() {
        assertThat(
                page.getCbotPowerButton()
                        .getCssValue("background-color"),
                is("rgba(255, 0, 0, 0.5)")
        );

        page.getCbotPowerButton().click();
    }

    @Then("user will see start button change to green")
    public void userWillSeeStartButtonChangeToGreen() {
       assertThat(
               page.getCbotPowerButton()
                       .getCssValue("background-color"),
               is("rgba(0, 255, 0, 0.5)")
       );
    }
}
