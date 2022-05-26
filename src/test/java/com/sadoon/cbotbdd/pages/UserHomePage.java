package com.sadoon.cbotbdd.pages;

import com.sadoon.cbotbdd.util.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class UserHomePage {

    private final WebDriver driver;

    @FindBy(className = "title")
    private WebElement heading;

    @FindBy(className = "homePage")
    private WebElement homePageDiv;

    @FindBy(id = "logOutButton")
    private WebElement logout;

    @FindBy(id = "strategyManagerButton")
    private WebElement strategyManagerButton;

    @FindBy(id = "strategyModal")
    private WebElement strategyModal;

    @FindBy(id = "KRAKEN")
    private WebElement loadedCard;

    @FindBy(id = "strategiesButton")
    private WebElement strategiesButton;

    @FindBy(id = "MockStrategyOption")
    private WebElement mockStrategyOption;

    @FindBy(id = "cbotPowerButton")
    private WebElement cbotPowerButton;

    @FindBy(id = "sideBarButton")
    private WebElement sideBarButton;

    @FindBy(id = "accountInput")
    private WebElement credentialAccountInput;

    @FindBy(id = "passwordInput")
    private WebElement credentialPasswordInput;

    @FindBy(id = "addCredentialsButton")
    private WebElement addCredentialsButton;

    @FindBy(id = "rejectedCredentialsResponse")
    private WebElement rejectedCredentialsResponse;

    @FindBy(id = "MockStrategyTradeDetails")
    private WebElement tradeDetails;

    @FindBy(id = "MockStrategyTradeStatus")
    private WebElement tradeStatus;

    @FindBy(id = "MockStrategyTradeCurrent")
    private WebElement currentTradePrice;

    @FindBy(id = "MockStrategyTradeTarget")
    private WebElement targetTradePrice;

    public UserHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getHeading() {
        return heading;
    }

    public WebElement getHomePageDiv() {
        return Waiter.waitUntilElementVisible(driver, homePageDiv);
    }

    public Select getCardSelect() {
        return new Select(driver.findElement(By.id("loadCardsSelect")));
    }

    public Select getExchangeSelect() {
        return new Select(driver.findElement(By.id("exchangeNameSelect")));
    }

    public WebElement getLogout() {
        return logout;
    }

    public WebElement getStrategyManagerButton() {
        return strategyManagerButton;
    }

    public WebElement getStrategyModal() {
        return Waiter.waitUntilElementVisible(driver, strategyModal);
    }

    public WebElement getLoadedCard() {
        return Waiter.waitUntilElementVisible(driver, loadedCard);
    }

    public WebElement getStrategiesButton() {
        return Waiter.waitUntilElementVisible(driver, strategiesButton);
    }

    public WebElement getMockStrategyOption() {
        return Waiter.waitUntilElementVisible(driver, mockStrategyOption);
    }

    public WebElement getCbotPowerButton() {
        return cbotPowerButton;
    }

    public WebElement getSideBarButton() {
        return sideBarButton;
    }

    public WebElement getCredentialAccountInput() {
        return credentialAccountInput;
    }

    public WebElement getCredentialPasswordInput() {
        return credentialPasswordInput;
    }

    public WebElement getAddCredentialsButton() {
        return addCredentialsButton;
    }

    public WebElement getRejectedCredentialsResponse() {
        return Waiter.waitUntilOutcomeNotBlank(driver, rejectedCredentialsResponse);
    }

    public WebElement getTradeDetails() {
        return Waiter.waitUntilElementVisible(driver, tradeDetails);
    }

    public WebElement getTradeStatus() {
        return Waiter.waitUntilElementVisible(driver, tradeStatus);
    }

    public WebElement getCurrentTradePrice() {
        return currentTradePrice;
    }

    public WebElement getTargetTradePrice() {
        return targetTradePrice;
    }
}
