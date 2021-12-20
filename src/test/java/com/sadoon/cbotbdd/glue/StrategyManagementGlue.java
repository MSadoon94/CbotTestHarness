package com.sadoon.cbotbdd.glue;

import com.sadoon.cbotbdd.glue.util.TestListener;
import com.sadoon.cbotbdd.glue.util.Waiter;
import com.sadoon.cbotbdd.pages.StrategyModalPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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
        page.getStrategySelect().getWrappedElement().click();
        Waiter.waitUntilSelectHasOption(driver, page.getStrategySelect(), "MockStrategy");
        page.getStrategySelect().selectByValue("MockStrategy");
    }

    @Then("strategy will be displayed in modal")
    public void strategyWillBeDisplayedInModal() {
        assertThat(page.getStrategyNameInput().getAttribute("value"), is("MockStrategy"));
    }

    @When("user submits {string} in strategy name input box")
    public void userSubmitsMockStrategyInStrategyNameInputBox(String arg0) {
        page.getStrategyNameInput().sendKeys(arg0);
        page.getSaveButton().click();
        page.getStrategyNameInput().clear();
    }
}
