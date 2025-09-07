package com.capstone.bank.tests;

import com.capstone.bank.pages.DashboardPage;
import com.capstone.bank.pages.LoginPage;
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

public class FundTransferTests extends BaseTest {

    @Test(groups = "transfer")
    public void transferFunds_shouldShowResultPage() {
        new LoginPage(driver).login(Config.get("username"), Config.get("password"));
        DashboardPage dp = new DashboardPage(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement fromAcc = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("#accountTable tbody tr td a")));
        String fromId = fromAcc.getText().trim();

        WebElement toAcc = driver.findElements(By.cssSelector("#accountTable tbody tr td a")).get(1);
        String toId = toAcc.getText().trim();

        dp.goToTransferFunds();
        TransferPage tp = new TransferPage(driver);
        tp.transfer(fromId, toId, 100.00);

        Assert.assertTrue(tp.isSuccess() || tp.isFailure(),
                "Transfer should show either success or error message");
    }
}
