package com.sadoon.cbotbdd.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserHomePage {

    private final WebDriver driver;

    @FindBy(id = "apiKey")
    private WebElement apiKeyInput;

    @FindBy(id = "privateKey")
    private WebElement privateKeyInput;

    @FindBy(id = "addBrokerage")
    private WebElement addBrokerageButton;

    @FindBy(id = "balance")
    private WebElement balance;

    @FindBy(id = "logoutButton")
    private WebElement logout;


    public UserHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public Select getBrokerageSelect() {
        return new Select(driver.findElement(By.name("brokerageSelect")));
    }

    public WebElement getApiKeyInput() {
        return apiKeyInput;
    }

    public WebElement getPrivateKeyInput() {
        return privateKeyInput;
    }

    public WebElement getAddBrokerageButton() {
        return addBrokerageButton;
    }

    public WebElement getBalance() {
        WebDriverWait waitForCard = new WebDriverWait(driver, 5);
        waitForCard.until(ExpectedConditions.visibilityOf(balance));
        return balance;
    }

    public WebElement getLogout() {
        return logout;
    }
}
