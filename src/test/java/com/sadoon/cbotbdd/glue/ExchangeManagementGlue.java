package com.sadoon.cbotbdd.glue;

import com.sadoon.cbotbdd.pages.UserHomePage;
import com.sadoon.cbotbdd.util.TestListener;
import com.sadoon.cbotbdd.util.Waiter;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class ExchangeManagementGlue {
    private WebDriver driver;
    private UserHomePage page;

    public ExchangeManagementGlue(TestListener listener) {
        this.driver = listener.getDriver();
        page = PageFactory.initElements(driver, UserHomePage.class);
    }

    @Given("user registers exchange")
    public void userRegistersExchange(){
        userClicksOnSideBar();
        page.getExchangeSelect().getWrappedElement().click();
        page.getExchangeSelect().selectByVisibleText("Kraken");
        Waiter.waitUntilElementVisible(driver, page.getCredentialAccountInput());
        page.getCredentialAccountInput().sendKeys("MockAccount");
        page.getCredentialPasswordInput().sendKeys("MockPassword");
        clicksAddCredentialButton();
        userClicksOnSideBar();
    }

    @Given("user clicks on side bar")
    public void userClicksOnSideBar(){
        page.getSideBarButton().click();
    }

    @When("user enters these exchange values")
    public void userEntersTheseExchangeValues(DataTable table) {
        page.getExchangeSelect().getWrappedElement().click();
        page.getExchangeSelect().selectByVisibleText(table.cell(0, 0));
        Waiter.waitUntilElementVisible(driver, page.getCredentialAccountInput());
        page.getCredentialAccountInput().sendKeys(table.cell(0, 1));
        page.getCredentialPasswordInput().sendKeys(table.cell(0, 2));
    }

    @And("clicks add credential button")
    public void clicksAddCredentialButton() {
        page.getAddCredentialsButton().click();
    }

    @Then("user will see exchange card in side bar")
    public void userWillSeeExchangeCardInSideBar() {
        assertThat(page.getLoadedCard().isDisplayed(), is(true));
    }

    @Then("user will see error message in side bar")
    public void userWillSeeErrorMessageInSideBar() {
        assertThat(page.getRejectedCredentialsResponse().getText(), containsString("[EAPI:Invalid key]"));
    }
}