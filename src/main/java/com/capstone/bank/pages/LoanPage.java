package com.capstone.bank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoanPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public LoanPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By amount = By.id("amount");
    private By downPayment = By.id("downPayment");
    private By fromAccount = By.id("fromAccountId");
    private By applyBtn = By.cssSelector("input[value='Apply Now']");

    public void requestLoan(double amt, double down, String fromAccountId) {
        wait.until(ExpectedConditions.presenceOfElementLocated(amount)).clear();
        driver.findElement(amount).sendKeys(String.valueOf((int) amt));

        driver.findElement(downPayment).clear();
        driver.findElement(downPayment).sendKeys(String.valueOf((int) down));

        // Always pick the first available account dynamically
        WebElement accountDropdown = wait.until(ExpectedConditions.elementToBeClickable(fromAccount));
        accountDropdown.findElements(By.tagName("option")).get(0).click();

        wait.until(ExpectedConditions.elementToBeClickable(applyBtn)).click();
    }

    public boolean isApproved() {
        return driver.getPageSource().toLowerCase().contains("approved");
    }

    public boolean isDenied() {
        return driver.getPageSource().toLowerCase().contains("denied");
    }
}
