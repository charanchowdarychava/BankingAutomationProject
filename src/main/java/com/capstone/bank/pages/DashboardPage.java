package com.capstone.bank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {
    private WebDriver driver;
    public DashboardPage(WebDriver driver) { this.driver = driver; }

    public boolean isOnAccountsOverview() {
        return driver.getTitle().contains("Accounts Overview") || driver.getPageSource().contains("Accounts Overview");
    }

    public void goToTransferFunds() {
        driver.findElement(By.linkText("Transfer Funds")).click();
    }

    public void goToRequestLoan() {
        driver.findElement(By.linkText("Request Loan")).click();
    }

    public void goToAccountsOverview() {
        driver.findElement(By.linkText("Accounts Overview")).click();
    }
}
