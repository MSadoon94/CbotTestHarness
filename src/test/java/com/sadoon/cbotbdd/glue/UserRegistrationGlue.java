package com.sadoon.cbotbdd.glue;

import com.sadoon.cbotbdd.pages.UserRegistrationPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class UserRegistrationGlue {

    private WebDriver driver;
    private UserRegistrationPage page;
    private String name, pass;

    @Before
    public void setUp(){

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        page = PageFactory.initElements(driver, UserRegistrationPage.class);
    }

    @Given("user has navigated to user registration page")
    public void userHasNavigatedToUserCreationPage(DataTable table) {
        name = table.cell(1,0);
        pass = table.cell(1,1);

        driver.get("http://localhost:3000/authenticate");

        assertThat(page.getHeading().getText(), is("User Creation"));
    }

    @When("user submits these values for user creation")
    public void userSubmitsTheseValuesForUserCreation() {
        inputNameAndPass();
        page.getCreateButton().click();
    }

    @When("user submits these values for login")
    public void userSubmitsTheseValuesForLogin() {
        inputNameAndPass();
        page.getLoginButton().click();
    }

    @Then("user will be created with same values")
    public void userWillBeCreatedWithSameValues() {
        assertThat(page.getSubmitOutcome().getText(), is(name + " was created successfully."));
    }

    @Then("user will be logged in")
    public void userWillBeLoggedIntoProfile(){
        assertThat(page.getSubmitOutcome().getText(), is("Welcome back, " + name));
    }

    @After
    public void tearDown(){
        driver.close();
    }

    private void inputNameAndPass(){
        page.getName().sendKeys(name);
        page.getPass().sendKeys(pass);
    }
}
