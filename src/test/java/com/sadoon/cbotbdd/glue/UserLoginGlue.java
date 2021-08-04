package com.sadoon.cbotbdd.glue;

import com.sadoon.cbotbdd.glue.util.TestListener;
import com.sadoon.cbotbdd.pages.UserStartPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class UserLoginGlue {

    private WebDriver driver;
    private UserStartPage page;
    private String name, pass;

    public UserLoginGlue(TestListener listener) {
        this.driver = listener.getDriver();
        page = PageFactory.initElements(driver, UserStartPage.class);
    }

    @Given("user has navigated to login page")
    public void userHasNavigatedToLoginPage() {
        driver.get("http://localhost:3000/start");

        assertThat(page.getHeading().getText(), is("User Start"));
    }

    @And("clicks login")
    public void andClicksLogin(){
        page.getName().sendKeys(page.getName().getText());
        page.getPass().sendKeys(page.getPass().getText());
        page.getLoginButton().click();
    }

    @Then("user will be redirected to user home page")
    public void userWillBeRedirectedToUserHomePage() {
        assertThat(page.getHomePageHeading().getText(), is("User Home"));
    }

    @Then("user will see failed login message")
    public void userWillSeeFailedLoginMessage() {
    assertThat(page.getSubmitOutcome().getText(), is("Error: user could not be logged in."));
    }

    private void clearValues(){
        page.getName().clear();
        page.getPass().clear();
    }

    private boolean textBoxesHaveValuesAlready(String name, String pass){
        return page.getName().getText().equals(name)
                && page.getPass().getText().equals(pass);
    }
}
