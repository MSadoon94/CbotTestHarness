package com.sadoon.cbotbdd.glue.util;

import com.mongodb.client.result.UpdateResult;
import com.sadoon.cbotbdd.database.MongoRepo;
import com.sadoon.cbotbdd.glue.util.mockbrokerage.MockBrokerageFactory;
import com.sadoon.cbotbdd.pages.UserHomePage;
import com.sadoon.cbotbdd.pages.UserStartPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;
import java.util.logging.Level;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TestListener {
    private final MongoRepo repo;
    private WebDriver driver;
    private String username = "TestUser";

    public TestListener() {
        this.repo = new MongoRepo();
        new MockBrokerageFactory(repo);
    }

    public WebDriver getDriver() {
        return driver;
    }

    @Before("@sign-up")
    public void setUpForSignUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Before("@login")
    public void setUpForLogin() {
        UserStartPage page = getStartPage();

        signUp(page);
    }

    @Before(value = "not (@sign-up or @login)", order = 0)
    public void setUpWithUserEntry() {
        UserStartPage page = getStartPage();

        signUp(page);
        page.getLoginButton().click();

        assertThat(page.getHomePageHeading().getText(), is("User Home"));
    }

    @Before("@load-card or @silent-refresh")
    public void setUpWithCardSaved() {
        saveCard(PageFactory.initElements(driver, UserHomePage.class));
    }

    @Before("@cbot-activation")
    public void setUpWithStrategySaved(){
        UpdateResult result = repo.setStrategies(username,
                Map.of("MockStrategy",
                        Map.of(
                                "name", "MockStrategy",
                                "base:", "USD",
                                "quote:", "BTC"
                        )
                ));

        assertThat(result.getModifiedCount(), is(1L));
    }

    public void signUp(UserStartPage page) {
        page.getName().sendKeys("TestUser");
        page.getPass().sendKeys("TestPassword1-");

        page.getConfirmPass().sendKeys("TestPassword1-");
        page.getCreateButton().click();

        assertThat(page.getSubmitOutcome().getText(), is("User was created successfully."));
    }

    private UserStartPage getStartPage() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(getOptionsForLogging());
        driver.get("http://localhost:3000/start");
        return PageFactory.initElements(driver, UserStartPage.class);
    }

    private ChromeOptions getOptionsForLogging() {
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logPrefs);
        return options;
    }

    private void saveCard(UserHomePage page) {
        page.getNewCardButton().click();

        page.getCardNameInput().sendKeys("MockCard");
        page.getCardAccountInput().sendKeys(System.getenv("KRAKEN_API_KEY"));
        page.getCardPasswordInput().sendKeys(System.getenv("KRAKEN_PRIVATE_KEY"));
        page.getCardBrokerageInput().sendKeys("kraken");

        page.getSaveCardButton().click();

        assertThat(page.getSaveCardResponse().getText(), is("Card was saved successfully."));
    }

    @After
    public void onTestFailure(Scenario scenario) {
        logFailScenario(scenario);
        repo.deleteAllUsers();
        driver.close();
    }

    private void logFailScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
            scenario.log(driver.manage().logs().get(LogType.BROWSER).toJson().toString());
        }
    }

}
