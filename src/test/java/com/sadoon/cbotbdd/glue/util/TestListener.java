package com.sadoon.cbotbdd.glue.util;

import com.sadoon.cbotbdd.database.MongoRepo;
import com.sadoon.cbotbdd.database.Repository;
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

import java.util.logging.Level;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TestListener {

    private Repository repo;

    private WebDriver driver;

    public TestListener() {
        this.repo = new MongoRepo();
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
    public void setUpForLogin(){
        UserStartPage page = getStartPage();

        signUp(page);
    }
    @Before("not (@sign-up or @login or @load-card)")
    public void setUpWithUserEntry(){
        UserStartPage page = getStartPage();

        signUp(page);
        page.getLoginButton().click();

        assertThat(page.getHomePageHeading().getText(), is("User Home"));
    }

    @Before("@load-card or silent-refresh")
    public void setUpWithCardSaved(){
        setUpWithUserEntry();
        saveCard(PageFactory.initElements(driver, UserHomePage.class));
    }

    private UserStartPage getStartPage(){

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(getOptionsForLogging());
        driver.get("http://localhost:3000/start");
        return PageFactory.initElements(driver, UserStartPage.class);
    }

    private ChromeOptions getOptionsForLogging(){
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logPrefs);
        return options;
    }

    private void signUp(UserStartPage page){
        page.getName().sendKeys("TestUser");
        page.getPass().sendKeys("TestPassword1-");
        page.getConfirmPass().sendKeys("TestPassword1-");
        page.getCreateButton().click();
        assertThat(page.getSubmitOutcome().getText(), is("User was created successfully."));
    }

    private void saveCard(UserHomePage page){
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
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
            scenario.log(driver.manage().logs().get(LogType.BROWSER).toJson().toString());
        }
        repo.deleteAllUsers();
        driver.close();
    }

}
