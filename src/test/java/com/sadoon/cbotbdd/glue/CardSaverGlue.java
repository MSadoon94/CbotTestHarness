package com.sadoon.cbotbdd.glue;

import com.sadoon.cbotbdd.glue.util.TestListener;
import com.sadoon.cbotbdd.pages.UserHomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CardSaverGlue {

    private WebDriver driver;
    private UserHomePage page;

    private String cardAccount;
    private String cardPassword;

    public CardSaverGlue(TestListener listener) {
        this.driver = listener.getDriver();
        page = PageFactory.initElements(driver, UserHomePage.class);
        cardAccount = System.getenv("KRAKEN_API_KEY");
        cardPassword = System.getenv("KRAKEN_PRIVATE_KEY");
    }

    @Given("user clicks new card button")
    public void userClicksNewCardButton() {
        page.getNewCardButton().click();
    }

    @When("user submits valid card information")
    public void userSubmitsValidCardInformation() {
        sendValidInputs();

        page.getSaveCardButton().click();
    }

    @Then("user will see card save success message")
    public void userWillSeeCardSaveSuccessMessage() {
        assertThat(page.getSaveCardResponse().getText(), is("Card was saved successfully."));
    }

    @When("^user submits this invalid card \"([^\"]*)\"$")
    public void userSubmitsThisInvalidCardInput(String input) {
        sendValidInputs();
        WebElement invalidTargetInput = getTargetWebElement(input);
        invalidTargetInput.clear();
        getTargetWebElement(input).sendKeys("Invalid");
        page.getSaveCardButton().click();
    }

    @Then("user will see save card fail message")
    public void userWillSeeSaveCardFailMessage() {
        assertThat(page.getSaveCardResponse().getText(), is("Error: Card could not be saved."));
    }

    private void sendValidInputs() {
        page.getCardNameInput().sendKeys("MockCard");
        page.getCardAccountInput().sendKeys(cardAccount);
        page.getCardPasswordInput().sendKeys(cardPassword);
        page.getCardBrokerageInput().sendKeys("kraken");
    }

    private WebElement getTargetWebElement(String invalidInput) {
        return Map.of(
                        "Account", page.getCardAccountInput(),
                        "Password", page.getCardPasswordInput(),
                        "Brokerage", page.getCardBrokerageInput())
                .get(invalidInput);
    }

}
