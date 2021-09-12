package com.sadoon.cbotbdd.glue;

import com.sadoon.cbotbdd.glue.util.TestListener;
import com.sadoon.cbotbdd.pages.StrategyModalPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class StrategySavingGlue {
    private WebDriver driver;
    private StrategyModalPage page;

    public StrategySavingGlue(TestListener listener) {
        this.driver = listener.getDriver();
        this.page = PageFactory.initElements(driver, StrategyModalPage.class);
    }

    @When("user clicks save strategy")
    public void whenUserClicksSaveStrategy(){
        page.getSaveButton().click();
    }

    @Then("user will see save success message")
    public void userWillSeeSaveSuccessMessage() {
        assertThat(page.getSaveOutcome().getText(), is("Strategy was saved successfully."));
    }
}
