package com.capstone.bank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class TransferPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public TransferPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By amount = By.id("amount");
    private By fromAccount = By.id("fromAccountId");
    private By toAccount = By.id("toAccountId");
    private By transferBtn = By.cssSelector("input[value='Transfer']");

    public void transfer(String fromId, String toId, double amt) {
        wait.until(ExpectedConditions.presenceOfElementLocated(amount)).clear();
        driver.findElement(amount).sendKeys(String.valueOf(amt));

        // Handle From Account
        WebElement fromDropdown = wait.until(ExpectedConditions.presenceOfElementLocated(fromAccount));
        List<WebElement> fromOptions = fromDropdown.findElements(By.tagName("option"));
        if (!fromOptions.isEmpty()) {
            fromOptions.get(0).click();
        }

        // Handle To Account
        WebElement toDropdown = wait.until(ExpectedConditions.presenceOfElementLocated(toAccount));
        List<WebElement> toOptions = toDropdown.findElements(By.tagName("option"));
        if (toOptions.size() > 1) {
            toOptions.get(1).click(); // pick 2nd account if available
        } else if (!toOptions.isEmpty()) {
            toOptions.get(0).click(); // fallback to same account
        }

        wait.until(ExpectedConditions.elementToBeClickable(transferBtn)).click();
    }

    public boolean isSuccess() {
        return driver.getPageSource().toLowerCase().contains("transfer complete");
    }

    public boolean isFailure() {
        return driver.getPageSource().toLowerCase().contains("error");
    }
}
