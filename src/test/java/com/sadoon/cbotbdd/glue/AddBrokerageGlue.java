package com.sadoon.cbotbdd.glue;

import com.sadoon.cbotbdd.glue.util.TestListener;
import com.sadoon.cbotbdd.pages.UserHomePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AddBrokerageGlue {

    private WebDriver driver;
    private UserHomePage page;

    private String apiKey;
    private String privateKey;

    public AddBrokerageGlue(TestListener listener) {
        this.driver = listener.getDriver();
        page = PageFactory.initElements(driver, UserHomePage.class);
    }

    @Given("user selects add kraken brokerage")
    public void userSelectsAddKrakenBrokerage() {
        page.getBrokerageSelect().selectByVisibleText("Kraken");
    }

    @When("user submits this brokerage information")
    public void userSubmitsThisBrokerageInformation(DataTable table) {
        apiKey = table.cell(1, 0);
        privateKey = table.cell(1, 1);

        page.getApiKeyInput().sendKeys(apiKey);
        page.getPrivateKeyInput().sendKeys(privateKey);

        page.getAddBrokerageButton().click();
    }

    @Then("user will see kraken brokerage balance added to home page")
    public void userWillSeeKrakenBrokerageBalanceAddedToHomePage() {
        assertThat(page.getBalance().isDisplayed(), is(true));
    }


}
