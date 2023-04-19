package com.tutorialsninja.qa.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.ExtentReporter;
import com.tutorialsninja.qa.utils.Utilities;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MyListeners implements ITestListener
{
    ExtentReports extentReport;
    ExtentTest extentTest;
//    private ExtentReports;

    @Override
    public void onStart(ITestContext context)
    {
        extentReport = ExtentReporter.generateExtentReport();
    }

    @Override
    public void onTestStart(ITestResult result)
    {
        String testName = result.getName();
        extentTest = extentReport.createTest(testName);
        extentTest.log(Status.INFO, testName + " started executing !!!");
    }

    @Override
    public void onTestSuccess(ITestResult result)
    {
        String testName = result.getName();
        extentTest.log(Status.PASS, testName + " got successfully executed !!!");
    }

    @Override
    public void onTestFailure(ITestResult result)
    {
        String testName = result.getName();
        WebDriver driver = null;
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        String destinationsPath = Utilities.captureScreenshot(driver, result.getName());
        extentTest.fail("detail nae").addScreenCaptureFromPath(destinationsPath);

        extentTest.log(Status.INFO, result.getThrowable());
        extentTest.log(Status.FAIL, testName + " got FAILED !!!");

    }

    @Override
    public void onTestSkipped(ITestResult result)
    {
        String testName = result.getName();
        extentTest.log(Status.INFO, result.getThrowable());
        extentTest.log(Status.SKIP, testName + " got SKIP");
    }


    @Override
    public void onFinish(ITestContext context)
    {
        extentReport.flush();
        String pathOfReport = "/test-output/ExtentReports/Index.html";
//        System.getProperty("user.dir") +
        File file = new File(pathOfReport);
        try {
            Desktop.getDesktop().browse(file.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
