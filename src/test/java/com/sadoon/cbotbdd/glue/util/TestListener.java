package com.sadoon.cbotbdd.glue.util;

import com.sadoon.cbotbdd.database.MongoRepo;
import com.sadoon.cbotbdd.database.Repository;
import com.sadoon.cbotbdd.pages.UserStartPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.PageFactory;

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
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    @Before("@login")
    public void setUpForLogin(){
        UserStartPage page = getStartPage();

        signUp(page);
    }
    @Before("not (@sign-up or @login)")
    public void setUpWithUserEntry(){
        UserStartPage page = getStartPage();

        signUp(page);
        page.getLoginButton().click();

        assertThat(page.getHomePageHeading().getText(), is("User Home"));
    }

    private UserStartPage getStartPage(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://localhost:3000/start");
        return PageFactory.initElements(driver, UserStartPage.class);
    }

    private void signUp(UserStartPage page){
        page.getName().sendKeys("TestUser");
        page.getPass().sendKeys("TestPassword1-");
        page.getConfirmPass().sendKeys("TestPassword1-");
        page.getCreateButton().click();
        assertThat(page.getSubmitOutcome().getText(), is("TestUser was created successfully."));
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
