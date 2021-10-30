package com.sadoon.cbotbdd.pages;

import com.sadoon.cbotbdd.glue.util.Waiter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserHomePage {

    private final WebDriver driver;

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

    public UserHomePage(WebDriver driver) {
        this.driver = driver;
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
        WebDriverWait waitForModal = new WebDriverWait(driver, 5);
        waitForModal.until(ExpectedConditions.visibilityOf(strategyModal));
        return strategyModal;
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
}
