package com.capstone.bank.tests;

import com.capstone.bank.pages.DashboardPage;
import com.capstone.bank.pages.LoginPage;
import com.capstone.bank.utils.BaseTest;
import com.capstone.bank.utils.Config;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test(groups = "login")
    public void validLogin() {
        LoginPage lp = new LoginPage(driver);
        lp.login(Config.get("username"), Config.get("password"));
        DashboardPage dp = new DashboardPage(driver);
        Assert.assertTrue(dp.isOnAccountsOverview(),
                "Should land on Accounts Overview after login");
    }

    @Test(groups = "login")
    public void invalidLogin() {
        LoginPage lp = new LoginPage(driver);
        lp.login("invalidUser", "invalidPass");
        Assert.assertTrue(lp.isErrorShown() || driver.getPageSource().toLowerCase().contains("error"),
                "Error should be shown for invalid credentials");
    }
}
