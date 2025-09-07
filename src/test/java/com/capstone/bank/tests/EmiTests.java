package com.capstone.bank.tests;

import com.capstone.bank.utils.BaseTest;
import com.capstone.bank.utils.EmiCalculator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class EmiTests extends BaseTest {

    @DataProvider(name = "emiData")
    public Object[][] emiData() {
        return new Object[][]{
                {5000.0, 12.0, 12},
                {150000.0, 9.5, 240},
                {20000.0, 10.5, 48},
                {12000.0, 13.0, 24}
        };
    }

    @Test(dataProvider = "emiData", groups = "emi")
    public void validateEmi(double principal, double annualRate, int months) {
        double calculated = EmiCalculator.monthlyEmi(principal, annualRate, months);
        Assert.assertTrue(calculated > 0, "EMI should be greater than 0");
    }
}
