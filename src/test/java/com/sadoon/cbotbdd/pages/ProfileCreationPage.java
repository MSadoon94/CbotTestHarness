package com.sadoon.cbotbdd.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfileCreationPage {

    private final WebDriver driver;

    @FindBy(className = "title")
    private WebElement heading;

    public ProfileCreationPage(WebDriver aDriver){
        driver = aDriver;
    }

    public WebElement getHeading(){
        return heading;
    }


}
