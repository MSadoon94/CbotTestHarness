package com.sadoon.cbotbdd.glue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sadoon.cbotbdd.database.MongoRepo;
import com.sadoon.cbotbdd.glue.util.TestListener;
import com.sadoon.cbotbdd.glue.util.Waiter;
import com.sadoon.cbotbdd.glue.util.mockbrokerage.ResponseFileReader;
import com.sadoon.cbotbdd.pages.UserHomePage;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class CbotActivationGlue {

    private WebDriver driver;
    private MongoRepo repo;
    private UserHomePage page;
    private ResponseFileReader fileReader;

    public CbotActivationGlue(TestListener listener) {
        this.driver = listener.getDriver();
        this.repo = listener.getRepo();
        page = PageFactory.initElements(driver, UserHomePage.class);
        try {
            fileReader = new ResponseFileReader(new ObjectMapper(), "CBOT_STATUS");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Given("user selects strategy for cbot to use")
    public void userSelectsStrategyForCbotToUse() {
        page.getStrategyActDetails().click();

        Waiter.waitUntilElementVisible(driver, page.getStrategyCheckbox());
        page.getStrategyCheckbox().click();
    }

    @When("user clicks on red start button")
    public void userClicksOnRedStartButton() {

        Waiter.waitForCssValueToMatchExpectation(
                driver, page.getCbotPowerButton(),
                "background-color", "rgba(255, 0, 0, 0.5)"
        );

        page.getCbotPowerButton().click();
    }

    @Then("user will see green start button")
    public void userWillSeeGreenStartButton() {
        Waiter.waitForCssValueToMatchExpectation(
                driver, page.getCbotPowerButton(),
                "background-color", "rgba(0, 255, 0, 0.75)"
        );

        assertThat(
                page.getCbotPowerButton()
                        .getCssValue("background-color"),
                is("rgba(0, 255, 0, 0.75)")
        );
    }

    @Given("cbot is activated and {string} is logged out")
    public void cbotIsActivatedAndUserIsLoggedOut(String arg0) {
        logout();

        try {
            assertThat(repo.updateUser(arg0, "cbotStatus",
                    fileReader.getBodyAsMap().get("CbotStatus")).getModifiedCount(), is(1L));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void logout() {
        page.getLogout().click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    @After("@cbot-activation")
    public void closeReader() {
        try {
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
