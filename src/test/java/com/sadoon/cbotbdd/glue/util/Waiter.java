package com.sadoon.cbotbdd.glue.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waiter {

    public static Boolean isOutcomeNotBlank(WebDriver driver, String id){
        WebDriverWait waitForResults = new WebDriverWait(driver, 5);

        //Wait until outcome is not blank.
      return waitForResults.until(ExpectedConditions.not(
                ExpectedConditions.textToBe(By.id(id), "")));
    }
}
