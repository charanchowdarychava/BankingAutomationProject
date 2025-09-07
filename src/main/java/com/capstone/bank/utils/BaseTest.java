package com.capstone.bank.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected ExtentReports extent;
    protected ExtentTest test;

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional String browser, Method method) {
        extent = ExtentManager.getInstance();
        test = extent.createTest(getClass().getSimpleName() + "::" + method.getName());

        if (browser == null || browser.isEmpty()) {
            browser = Config.get("browser");
        }
        DriverFactory.initDriver(browser);
        driver = DriverFactory.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(Config.getInt("implicitWait")));
        driver.get(Config.get("baseUrl"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Passed");
        } else {
            test.skip("Skipped");
        }
        DriverFactory.quitDriver();
        ExtentManager.getInstance().flush();
    }
}
