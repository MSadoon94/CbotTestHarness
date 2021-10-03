package com.sadoon.cbotbdd.glue;

import com.sadoon.cbotbdd.glue.util.TestListener;
import com.sadoon.cbotbdd.pages.UserStartPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class UserSignupGlue {

    private WebDriver driver;
    private UserStartPage page;

    public UserSignupGlue(TestListener listener) {
        this.driver = listener.getDriver();
        page = PageFactory.initElements(driver, UserStartPage.class);
    }

    @Given("user has navigated to signup page")
    public void userHasNavigatedToSignupPage() {
        driver.get("http://localhost:3000/start");

        assertThat(page.getHeading().getText(), is("User Start"));
    }
    @When("user enters these values")
    public void userEntersTheseValues(DataTable table){
        clearValues();

        page.getName().sendKeys(table.cell(1, 0));
        page.getPass().sendKeys(table.cell(1, 1));

    }

    @And("enters this value for password confirmation")
    public void entersThisValueForPasswordConfirmation(DataTable table){
        page.getConfirmPass().sendKeys(table.cell(1, 0));
    }

    @And("clicks signup")
    public void clicksSignup(){
        page.getCreateButton().click();
        assertThat(page.getSubmitOutcome().getText(), is(notNullValue()));
    }

    @Then("user will be created with same values")
    public void userWillBeCreatedWithSameValues() {
        assertThat(page.getSubmitOutcome().getText(), is("User was created successfully."));
    }

    private void clearValues(){
        page.getName().clear();
        page.getPass().clear();
    }

    @Then("user will not be able to click the sign up button")
    public void userWillNotBeAbleToClickTheSignUpButton() {
        page.getCreateButton().click();
        assertThat(page.getCreateButton().isEnabled(), is(false));
    }
}
