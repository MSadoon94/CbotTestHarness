package com.sadoon.cbotbdd.glue;

import com.sadoon.cbotbdd.database.MongoRepo;
import com.sadoon.cbotbdd.util.TestListener;
import com.sadoon.cbotbdd.util.Waiter;
import com.sadoon.cbotbdd.pages.UserHomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class CbotStatusGlue {

    private MongoRepo repo;
    private WebDriver driver;
    private UserHomePage page;

    public CbotStatusGlue(TestListener listener) {
        this.driver = listener.getDriver();
        this.repo = listener.getRepo();
        page = PageFactory.initElements(driver, UserHomePage.class);
    }

    @When("user clicks on red start button")
    public void userClicksOnRedStartButton() {
        Waiter.waitForCssValueToMatchExpectation(
                driver, page.getCbotPowerButton(),
                "background-color", "rgba(255, 125, 102, 1)", Duration.ofSeconds(30)
        );

        page.getCbotPowerButton().click();
    }

    @Then("user will see green start button")
    public void userWillSeeGreenStartButton() {
        Waiter.waitForCssValueToMatchExpectation(
                driver, page.getCbotPowerButton(),
                "background-color", "rgba(0, 230, 4, 1)", Duration.ofSeconds(30)
        );

        assertThat(
                page.getCbotPowerButton()
                        .getCssValue("background-color"),
                is("rgba(0, 230, 4, 1)")
        );
    }

    @Given("cbot is activated and user is logged out")
    public void cbotIsActivatedAndUserIsLoggedOut() {
        assertThat(repo.getUserAsNode("TestUser").get("cbotStatus").get("isActive").asBoolean(), is(true));
        assertThat(page.getHeading().getText(), is("User Start"));
    }
}
