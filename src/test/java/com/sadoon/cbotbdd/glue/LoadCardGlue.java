package com.sadoon.cbotbdd.glue;

import com.sadoon.cbotbdd.glue.util.TestListener;
import com.sadoon.cbotbdd.glue.util.Waiter;
import com.sadoon.cbotbdd.pages.UserHomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class LoadCardGlue {

    private WebDriver driver;
    private UserHomePage page;

    public LoadCardGlue(TestListener listener){
        this.driver = listener.getDriver();
        page = PageFactory.initElements(driver, UserHomePage.class);
    }

    @Given("user clicks on target card in load card combo box")
    public void userClicksOnTargetCardInLoadCardComboBox(){
        page.getCardSelect().getWrappedElement().click();
        Waiter.waitUntilSelectHasOption(driver, page.getCardSelect(), "MockCard");
        page.getCardSelect().selectByValue("MockCard");
    }

    @When("user submits valid password for password validation")
    public void userSubmitsValidPasswordForPasswordValidation() {
        page.getCardPasswordVerify().sendKeys(System.getenv("KRAKEN_PRIVATE_KEY"));
        page.getCardPasswordButton().click();
    }

    @Then("target card will be displayed on home page")
    public void targetCardWillBeDisplayedOnHomePage() {
        assertThat(page.getLoadedCard().isDisplayed(), is(true));
    }

    @When("user submits invalid password for password validation")
    public void userSubmitsInvalidPasswordForPasswordValidation(){
        page.getCardPasswordVerify().sendKeys("InvalidPassword");
        page.getCardPasswordButton().click();
    }

    @Then("card load error will be displayed")
    public void cardLoadErrorWillBeDisplayed() {
        assertThat(page.getCardPasswordResponse().getText(), is("Card password is invalid."));
    }
}