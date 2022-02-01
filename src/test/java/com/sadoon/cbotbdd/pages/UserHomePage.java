package com.sadoon.cbotbdd.pages;

import com.sadoon.cbotbdd.glue.util.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class UserHomePage {

    private final WebDriver driver;

    @FindBy(className = "title")
    private WebElement heading;

    @FindBy(id = "cardNameInput")
    private WebElement cardNameInput;

    @FindBy(id = "cardAccountInput")
    private WebElement cardAccountInput;

    @FindBy(id = "cardPasswordInput")
    private WebElement cardPasswordInput;

    @FindBy(id = "cardBrokerageInput")
    private WebElement cardBrokerageInput;

    @FindBy(id = "logoutButton")
    private WebElement logout;

    @FindBy(id = "newStrategyButton")
    private WebElement newStrategyButton;

    @FindBy(id = "strategyModal")
    private WebElement strategyModal;

    @FindBy(id = "newCardButton")
    private WebElement newCardButton;

    @FindBy(id = "saveCardButton")
    private WebElement saveCardButton;

    @FindBy(id = "saveCardResponse")
    private WebElement saveCardResponse;

    @FindBy(id = "cardPassword")
    private WebElement cardPasswordVerify;

    @FindBy(id = "cardPasswordButton")
    private WebElement cardPasswordButton;

    @FindBy(id = "cardPassResponse")
    private WebElement cardPasswordResponse;

    @FindBy(id = "MockCard")
    private WebElement loadedCard;

    @FindBy(id = "strategyDetails")
    private WebElement strategyDetails;

    @FindBy(id = "MockStrategyCheckbox")
    private WebElement strategyCheckbox;

    @FindBy(id = "cbotPowerButton")
    private WebElement cbotPowerButton;

    public UserHomePage(WebDriver driver) {
        this.driver = driver;
    }
    
    public WebElement getHeading() {
        return heading;
    }

    public Select getCardSelect(){
        return new Select(driver.findElement(By.id("loadCardsSelect")));
    }

    public WebElement getCardAccountInput() {
        return cardAccountInput;
    }

    public WebElement getCardPasswordInput() {
        return cardPasswordInput;
    }

    public WebElement getCardBrokerageInput() {
        return cardBrokerageInput;
    }

    public WebElement getLogout() {
        return logout;
    }

    public WebElement getNewStrategyButton() {
        return newStrategyButton;
    }

    public WebElement getStrategyModal() {
        return Waiter.waitUntilElementVisible(driver, strategyModal);
    }

    public WebElement getCardNameInput() {
        return Waiter.waitUntilElementVisible(driver, cardNameInput);
    }

    public WebElement getNewCardButton() {
        return newCardButton;
    }

    public WebElement getSaveCardButton() {
        return saveCardButton;
    }

    public WebElement getSaveCardResponse() {
        Waiter.waitUntilOutcomeNotBlank(driver, saveCardResponse);
        return saveCardResponse;
    }

    public WebElement getCardPasswordVerify() {
        return Waiter.waitUntilElementVisible(driver, cardPasswordVerify);
    }

    public WebElement getCardPasswordButton() {

        return Waiter.waitUntilElementVisible(driver, cardPasswordButton);
    }

    public WebElement getCardPasswordResponse() {
        Waiter.waitUntilOutcomeNotBlank(driver, cardPasswordResponse);
        return cardPasswordResponse;
    }

    public WebElement getLoadedCard() {
        return Waiter.waitUntilElementVisible(driver, loadedCard);
    }

    public WebElement getStrategyDetails() {
        return strategyDetails;
    }

    public WebElement getStrategyCheckbox() {
        return strategyCheckbox;
    }

    public WebElement getCbotPowerButton() {
        return cbotPowerButton;
    }
}
