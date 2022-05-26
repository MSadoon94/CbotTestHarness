package com.sadoon.cbotbdd.glue;

import com.sadoon.cbotbdd.pages.UserHomePage;
import com.sadoon.cbotbdd.util.TestListener;
import com.sadoon.cbotbdd.util.Waiter;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class OrderExecutionGlue {

    private WebDriver driver;
    private UserHomePage page;
    private final int timeout = 1000;

    public OrderExecutionGlue(TestListener listener) {
        this.driver = listener.getDriver();
        this.page = PageFactory.initElements(driver, UserHomePage.class);

    }

    @Given("user selects saved strategy and creates trade")
    public void userSelectsSavedStrategyAndCreatesTrade() {
        page.getStrategiesButton().click();
        moveSliderRight();

        assertThat(page.getTradeStatus().isDisplayed(), is(true));
        Waiter.waitForConditionToBeMet(
                driver,
                Duration.ofSeconds(5),
                page.getTradeStatus().getText().equals("CREATION")
        );
    }


    @And("trade status is entry searching")
    public void tradeStatusIsEntrySearching() {
        for (int i = 0; !page.getTradeStatus().getText().equals("ENTRY_SEARCHING"); i++) {
            if (i == timeout) {
                break;
            } else {
                page = PageFactory.initElements(driver, UserHomePage.class);
            }
        }

        assertThat(page.getTradeStatus().getText(), is("ENTRY_SEARCHING"));
    }

    @When("potential entry point is found")
    public void potentialEntryPointIsFound() {
        for (int i = 0; !page.getCurrentTradePrice().getText().equals(page.getTargetTradePrice().getText()); i++) {
            if (i == timeout) {
                break;
            } else {
                page = PageFactory.initElements(driver, UserHomePage.class);
            }
        }
        assertThat(page.getCurrentTradePrice().getText(), is(page.getTargetTradePrice().getText()));
    }

    @Then("user will see trade status change to entry found")
    public void userWillSeeTradeStatusChangeToEntryFound() {
        for (int i = 0; !page.getTradeStatus().getText().equals("ENTRY_FOUND"); i++) {
            if (i == timeout) {
                break;
            } else {
                page = PageFactory.initElements(driver, UserHomePage.class);
            }
        }
        assertThat(page.getTradeStatus().getText(), is("ENTRY_FOUND"));
    }

    private void moveSliderRight(){
        Dimension sliderSize = page.getMockStrategyOption().getSize();
        int sliderWidth = sliderSize.getWidth();

        Actions builder = new Actions(driver);
        builder.moveToElement(page.getMockStrategyOption())
                .dragAndDropBy(page.getMockStrategyOption(), sliderWidth, 0)
                .build()
                .perform();
    }

}
