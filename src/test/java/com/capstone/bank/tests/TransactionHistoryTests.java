package com.capstone.bank.tests;

import com.capstone.bank.pages.DashboardPage;
import com.capstone.bank.pages.LoginPage;
import com.capstone.bank.pages.TransactionsPage;
import com.capstone.bank.pages.TransferPage;
import com.capstone.bank.utils.BaseTest;
import com.capstone.bank.utils.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class TransactionHistoryTests extends BaseTest {

    @Test(groups = "history")
    public void transactions_shouldAppearAfterTransfer() {
        new LoginPage(driver).login(Config.get("username"), Config.get("password"));
        DashboardPage dp = new DashboardPage(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Step 1: Wait for accounts overview page
        wait.until(ExpectedConditions.titleContains("Accounts Overview"));

        // Step 2: Find all account links (more flexible locator)
        List<WebElement> accounts = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//table[contains(@id,'accountTable')]//a")));

        Assert.assertTrue(accounts.size() > 0, "No accounts found for this user!");

        // Pick first account as fromId
        String fromId = accounts.get(0).getText().trim();

        // Pick second account if available, else same account
        String toId = (accounts.size() > 1) ? accounts.get(1).getText().trim() : fromId;

        // Step 3: Do a transfer
        dp.goToTransferFunds();
        TransferPage tp = new TransferPage(driver);
        tp.transfer(fromId, toId, 10.00);

        // Step 4: Go back to accounts overview and check history
        dp.goToAccountsOverview();
        TransactionsPage tr = new TransactionsPage(driver);
        tr.openFirstAccountActivity();

        Assert.assertTrue(tr.hasAnyTransactions(),
                "There should be at least one transaction in history after transfer");
    }
}
