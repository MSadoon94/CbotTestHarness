package com.sadoon.cbotbdd.glue.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class Waiter {

    public static Boolean waitUntilOutcomeNotBlank(WebDriver driver, WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, 5);

      return wait.until(not(
                textToBe(By.id(element.getAttribute("id")), "")));
    }

    public static WebElement waitUntilElementVisible(WebDriver driver, WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, 5);

        return wait.until(visibilityOf(element));
    }

    public static void waitUntilSelectHasOption(WebDriver driver, Select select, String name){
        WebDriverWait wait = new WebDriverWait(driver, 5);

        wait.until(webDriver -> select.getOptions().size() > 1);
    }
}
