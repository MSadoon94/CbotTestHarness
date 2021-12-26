package com.sadoon.cbotbdd.glue;

import com.sadoon.cbotbdd.glue.util.TestListener;
import com.sadoon.cbotbdd.glue.util.Waiter;
import com.sadoon.cbotbdd.pages.StrategyModalPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class StrategyManagementGlue {
    private WebDriver driver;
    private StrategyModalPage page;

    public StrategyManagementGlue(TestListener listener) {
        this.driver = listener.getDriver();
        this.page = PageFactory.initElements(driver, StrategyModalPage.class);
    }

    @Then("user will see save success message")
    public void userWillSeeSaveSuccessMessage() {
        assertThat(page.getSaveOutcome().getText(), is("Strategy was saved successfully."));
    }

    @When("user clicks on target strategy in load strategy combo box")
    public void userClicksOnTargetStrategyInLoadStrategyComboBox() {
        clearStrategyModalValues();
        page.getStrategySelect().getWrappedElement().click();
        Waiter.waitUntilSelectHasOption(driver, page.getStrategySelect(), "MockStrategy");
        page.getStrategySelect().selectByValue("MockStrategy");
    }

    @Then("strategy will be displayed in modal")
    public void strategyWillBeDisplayedInModal() {
        Waiter.waitUntilValueIsNotBlank(driver, page.getBaseInput());
        assertThat(page.getStrategyNameInput().getAttribute("value"), is("MockStrategy"));
        assertThat(page.getBaseInput().getAttribute("value"), is("BTC"));
        assertThat(page.getQuoteInput().getAttribute("value"), is("USD"));
    }

    @When("user inputs this data to strategy modal text boxes")
    public void userInputsThisDataToStrategyModalTextBoxes(DataTable table) {
        inputStrategyModalValues(table);

        page.getSaveButton().click();

        assertThat(page.getSaveOutcome().getText(), is("Strategy was saved successfully."));
    }

    private void inputStrategyModalValues(DataTable table){
        page.getStrategyNameInput().sendKeys(table.cell(1, 0));
        page.getBaseInput().sendKeys(table.cell(2, 0));
        page.getQuoteInput().sendKeys(table.cell(3, 0));
        assertThat(page.getCryptoValidity().getText(), is("✔"));
    }

    private void clearStrategyModalValues(){
        page.getStrategyNameInput().clear();
        page.getBaseInput().sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.BACK_SPACE));
        page.getQuoteInput().sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.BACK_SPACE));
    }
}
