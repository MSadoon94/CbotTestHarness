package com.sadoon.cbotbdd.glue;

import com.sadoon.cbotbdd.pages.ProfileCreationPage;
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

    @Given("user has navigated to profile creation page")
    public void userHasNavigatedToProfileCreationPage() {
        WebDriverManager.chromedriver().setup();
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://localhost:3000/profile-creation");

        ProfileCreationPage profileCreationPage
                = PageFactory.initElements(webDriver, ProfileCreationPage.class);

        assertThat(profileCreationPage.getHeading().getText(), is("Profile Creation"));
    }

    @When("user submits these values")
    public void userSubmitsTheseValues() {
        
    }

    @Then("profile will be created with same values")
    public void profileWillBeCreatedWithSameValues() {
    }
}
