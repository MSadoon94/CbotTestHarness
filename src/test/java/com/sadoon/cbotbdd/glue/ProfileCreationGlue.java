package com.sadoon.cbotbdd.glue;

import com.sadoon.cbotbdd.pages.ProfileCreationPage;
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

public class ProfileCreationGlue {

    private WebDriver driver;
    private ProfileCreationPage creationPage;

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        creationPage = PageFactory.initElements(driver, ProfileCreationPage.class);
    }

    @Given("user has navigated to profile creation page")
    public void userHasNavigatedToProfileCreationPage() {

        driver.get("http://localhost:3000/profile-creation");

        assertThat(creationPage.getHeading().getText(), is("Profile Creation"));
    }

    @When("user submits these values")
    public void userSubmitsTheseValues(DataTable table) {
        String name = table.cell(1,0);
        String pass = table.cell(1,1);

        creationPage.getName().sendKeys(name);
        creationPage.getPass().sendKeys(pass);

        creationPage.getSubmitButton().submit();
    }

    @Then("profile will be created with same values")
    public void profileWillBeCreatedWithSameValues() {
    }

    @After
    public void tearDown(){
        driver.close();
    }
}
