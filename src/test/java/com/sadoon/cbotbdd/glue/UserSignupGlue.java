package com.sadoon.cbotbdd.glue;

import com.sadoon.cbotbdd.glue.util.TestListener;
import com.sadoon.cbotbdd.pages.UserStartPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class UserSignupGlue {

    private WebDriver driver;
    private UserStartPage page;
    private String name;

    public UserSignupGlue(TestListener listener) {
        this.driver = listener.getDriver();
        page = PageFactory.initElements(driver, UserStartPage.class);
    }

    @Given("user has navigated to signup page")
    public void userHasNavigatedToSignupPage() {
        driver.get("http://localhost:3000/start");

        assertThat(page.getHeading().getText(), is("User Start"));
    }

    @When("user submits these signup values")
    public void userSubmitsTheseSignupValues(DataTable table) {
        name = table.cell(1, 0);
        page.getName().sendKeys(name);
        page.getPass().sendKeys(table.cell(1, 1));

        page.getCreateButton().click();
        page.getName().clear();
        page.getPass().clear();
    }

    @Then("user will be created with same values")
    public void userWillBeCreatedWithSameValues() {
        assertThat(page.getSubmitOutcome().getText(), is(name + " was created successfully."));
    }

}
