package com.sadoon.cbotbdd.glue.util;

import com.sadoon.cbotbdd.database.MongoRepo;
import com.sadoon.cbotbdd.database.Repository;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogType;

public class TestListener {

    private Repository repo;

    private WebDriver driver;

    public TestListener() {
        this.repo = new MongoRepo();
    }

    public WebDriver getDriver() {
        return driver;
    }

    @Before()
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
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
