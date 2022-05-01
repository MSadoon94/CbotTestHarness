package com.sadoon.cbotbdd.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sadoon.cbotbdd.database.MongoRepo;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.logging.Level;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TestListener {
    public static Logger LOGGER = LoggerFactory.getLogger(TestListener.class);
    public static ObjectMapper MAPPER = new ObjectMapper();
    private final MongoRepo repo;
    private WebDriver driver;
    private DatabaseInitializer databaseInit;
    private JsonFileUtil fileUtil;

    public TestListener() {
        startDriver();
        databaseInit = new DatabaseInitializer();
        this.repo = databaseInit.getRepo();
        this.fileUtil = databaseInit.getFileUtil();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public MongoRepo getRepo() {
        return repo;
    }

    public JsonFileUtil getFileUtil() {
        return fileUtil;
    }

    @Before("@sign-up")
    public void setUpForSignUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Before(value = "not (@sign-up or @login or @no-login)", order = 3)
    public void signInUser() {
        UserStartPage page = PageFactory.initElements(driver, UserStartPage.class);
        page.getName().sendKeys("TestUser");
        page.getPass().sendKeys("TestPassword1-");

        page.getLoginButton().click();

        assertThat(page.getHomePageHeading().getText(), is("User Home"));
    }

    public void signUp(UserStartPage page) {
        page.getName().sendKeys("TestUser");
        page.getPass().sendKeys("TestPassword1-");

        page.getConfirmPass().sendKeys("TestPassword1-");
        page.getCreateButton().click();

        assertThat(page.getSignupResponse().getText(), is("User was created successfully."));
    }

    private void startDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(getOptionsForLogging());
        driver.get("http://localhost:3000/start");
    }

    private ChromeOptions getOptionsForLogging() {
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logPrefs);
        return options;
    }

    @After
    public void onTestFinish(Scenario scenario) {
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
