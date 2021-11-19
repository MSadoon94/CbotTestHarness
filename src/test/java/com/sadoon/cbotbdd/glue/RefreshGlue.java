package com.sadoon.cbotbdd.glue;

import com.sadoon.cbotbdd.glue.util.TestListener;
import com.sadoon.cbotbdd.pages.UserHomePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RefreshGlue {

    private WebDriver driver;
    private UserHomePage page;

    public RefreshGlue(TestListener listener){
        this.driver = listener.getDriver();
        page = PageFactory.initElements(driver, UserHomePage.class);
    }

    @When("user refreshes page")
    public void userRefreshesPage(){
        driver.navigate().refresh();
    }

    @Then("user will stay logged in and on the same page")
    public void userWillStayLoggedInAndOnTheSamePage() {
        assertThat(page.getHeading().getText(), is("User Home"));
    }
}
