package com.sadoon.cbotbdd.glue;

import com.sadoon.cbotbdd.util.TestListener;
import com.sadoon.cbotbdd.util.Waiter;
import com.sadoon.cbotbdd.pages.StrategyModalPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
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
    private Map<String, String> refinements;

    public RefineStrategyGlue(TestListener listener) {
        this.driver = listener.getDriver();
        this.page = PageFactory.initElements(driver, StrategyModalPage.class);
        this.validation = Map.of(
                "stop-loss", page.getStopLossInput(),
                "max-position", page.getMaxPositionInput(),
                "target-profit", page.getTargetProfitInput(),
                "moving-stop-loss", page.getMovingStopLossInput(),
                "max-loss", page.getMaxLossInput(),
                "long-entry", page.getLongEntryInput()
        );
    }

    @When("user clicks on refine strategy widget")
    public void userClicksOnRefineStrategyWidget() {
        page.getRefineDetails().click();
    }

    @And("saves all these refinements to the strategy")
    public void savesAllTheseRefinementsToTheStrategy(DataTable table) {
        refinements = table.asMap();

        refinements.forEach((key, value) -> validation.get(key).sendKeys(value));

        page.getStrategyNameInput().sendKeys("MockStrategy");

        page.getSaveButton().click();
        assertThat(page.getSaveOutcome().getText(), is("Strategy was saved successfully."));
    }

    @Then("user will see all saved refinements on load")
    public void userWillSeeAllSavedRefinementsOnLoad() {
        refinements.forEach((key, value) -> validation.get(key).clear());

        loadStrategy();

        refinements.forEach((key, value) -> assertThat(validation.get(key).getAttribute("value"), is(value)));
    }

    private void loadStrategy() {
        page.getStrategySelect().getWrappedElement().click();
        Waiter.waitUntilSelectHasOption(driver, page.getStrategySelect(), "MockStrategy");
        page.getStrategySelect().selectByValue("MockStrategy");
    }
}
