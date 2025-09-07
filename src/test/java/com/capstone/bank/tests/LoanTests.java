package com.capstone.bank.tests;

import com.capstone.bank.pages.DashboardPage;
import com.capstone.bank.pages.LoanPage;
import com.capstone.bank.pages.LoginPage;
import com.capstone.bank.utils.BaseTest;
import com.capstone.bank.utils.Config;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoanTests extends BaseTest {

    @Test(groups = "loan")
    public void personalLoan_request_shouldReturnResult() {
        new LoginPage(driver).login(Config.get("username"), Config.get("password"));
        DashboardPage dp = new DashboardPage(driver);
        dp.goToRequestLoan();

        LoanPage loan = new LoanPage(driver);
        loan.requestLoan(5000, 500, null);

        Assert.assertTrue(loan.isApproved() || loan.isDenied(),
                "Loan request should return either Approved or Denied");
    }

    @Test(groups = "loan")
    public void homeLoan_request_shouldReturnResult() {
        new LoginPage(driver).login(Config.get("username"), Config.get("password"));
        DashboardPage dp = new DashboardPage(driver);
        dp.goToRequestLoan();

        LoanPage loan = new LoanPage(driver);
        loan.requestLoan(150000, 1000, null);

        Assert.assertTrue(loan.isApproved() || loan.isDenied(),
                "Loan request should return either Approved or Denied");
    }
}
