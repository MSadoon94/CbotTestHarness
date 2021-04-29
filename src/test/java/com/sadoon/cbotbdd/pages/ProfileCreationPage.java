package com.sadoon.cbotbdd.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfileCreationPage {

    private final WebDriver driver;

    @FindBy(className = "title")
    private WebElement heading;

    @FindBy(id = "pname")
    private WebElement name;

    @FindBy(id = "pass")
    private WebElement pass;

    @FindBy(id = "submit")
    private WebElement submitButton;

    public ProfileCreationPage(WebDriver aDriver) {
        driver = aDriver;
    }

    public WebElement getHeading() {
        return heading;
    }

    public WebElement getName() {
        return name;
    }

    public WebElement getPass() {
        return pass;
    }

    public WebElement getSubmitButton() {
        return submitButton;
    }
}
