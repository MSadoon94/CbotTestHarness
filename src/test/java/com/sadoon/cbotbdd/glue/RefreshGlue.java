package com.sadoon.cbotbdd.glue;

import com.sadoon.cbotbdd.glue.util.TestListener;
import com.sadoon.cbotbdd.pages.UserHomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class RefreshGlue {

    private WebDriver driver;
    private UserHomePage page;

    public RefreshGlue(TestListener listener) {
        this.driver = listener.getDriver();
        page = PageFactory.initElements(driver, UserHomePage.class);
    }

    @When("user refreshes page")
    public void userRefreshesPage() {
        driver.navigate().refresh();
    }

    @Then("user will stay logged in and on the same page")
    public void userWillStayLoggedInAndOnTheSamePage() {
        assertThat(page.getHeading().getText(), is("User Home"));
    }


    @Given("user clicks on load card combo box")
    public void userClicksOnLoadCardComboBox() {
        page.getCardSelect().getWrappedElement().click();
    }

    @When("session silently refreshes")
    public void sessionSilentlyRefreshes() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        assertThat(wait.until(browserLog).getMessage(), containsString("\"Session refreshed.\""));
    }

    private Function<WebDriver, LogEntry> browserLog = new Function<>() {
        @Override
        public LogEntry apply(WebDriver driver) {
            page.getCardSelect().getWrappedElement().click();
            return getLogs().stream()
                    .filter(entry -> entry.getMessage().contains("\"Session refreshed.\""))
                    .findFirst()
                    .orElse(null);
        }
    };

    private List<LogEntry> getLogs() {

        return driver.manage()
                .logs()
                .get(LogType.BROWSER)
                .getAll();
    }
}
