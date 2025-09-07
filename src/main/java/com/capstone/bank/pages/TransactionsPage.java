package com.capstone.bank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class TransactionsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public TransactionsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Flexible locator: any account link leading to activity page
    private By accountLinks = By.xpath("//a[contains(@href,'activity.htm')]");

    public void openFirstAccountActivity() {
        List<WebElement> accounts = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(accountLinks));
        if (!accounts.isEmpty()) {
            accounts.get(0).click();
        } else {
            throw new RuntimeException("No accounts found to open activity!");
        }
    }

    public boolean hasAnyTransactions() {
        return driver.getPageSource().toLowerCase().contains("transaction");
    }
}
