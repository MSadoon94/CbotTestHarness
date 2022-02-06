package com.sadoon.cbotbdd.glue;

import com.sadoon.cbotbdd.util.TestListener;
import com.sadoon.cbotbdd.pages.StrategyModalPage;
import com.sadoon.cbotbdd.pages.UserHomePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class CryptoSelectionGlue {

    private WebDriver driver;
    private UserHomePage homePage;
    private StrategyModalPage modalPage;


    public CryptoSelectionGlue(TestListener listener){
        this.driver = listener.getDriver();
        this.homePage = PageFactory.initElements(driver, UserHomePage.class);
        this.modalPage = PageFactory.initElements(driver, StrategyModalPage.class);
    }

    @Given("strategy modal is open")
    public void strategyModalIsOpen(){
        homePage.getNewStrategyButton().click();
        assertThat(homePage.getStrategyModal().isDisplayed(), is(true));
    }

    @When("user enters this symbol pair")
    public void userEntersThisSymbolPair(DataTable table) {
        modalPage.getBaseInput().sendKeys(table.cell(1,0));
        modalPage.getQuoteInput().sendKeys(table.cell(1,1));
    }


    @Then("user will see checkmark next to entry")
    public void userWillSeeCheckmarkNextToEntry() {
        assertThat(modalPage.getCryptoValidity().getText(), is("✔"));
    }

    @Then("user will see error message next to entry")
    public void userWillSeeErrorMessageNextToEntry() {
        assertThat(modalPage.getCryptoValidity().getText(), is("BTCUS is invalid."));
    }
}
