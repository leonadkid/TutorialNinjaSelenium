package com.tutorialsninja.qa.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ExtentReporter
{
    public static ExtentReports generateExtentReport()
    {
        ExtentReports extentReport = new ExtentReports();
        File extentReportFile = new File(System.getProperty("user.dir") + "/test-output/ExtentReports");
        ExtentSparkReporter sparkReproter = new ExtentSparkReporter(extentReportFile);

        sparkReproter.config().setTheme(Theme.DARK);
        sparkReproter.config().setReportName("TutorialNinja Test Automation Report");
        sparkReproter.config().setDocumentTitle("TutorialNinja Report");
        sparkReproter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");

        extentReport.attachReporter(sparkReproter);

        Properties propConfig = new Properties();
        File fileConfig = new File(System.getProperty("user.dir") + "/src/main/java/com/tutorialsninja/qa/config/config.properties");
        try {
            FileInputStream fisConfig = null;
            fisConfig = new FileInputStream(fileConfig);
            propConfig.load(fisConfig);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        extentReport.setSystemInfo("Application URL", propConfig.getProperty("url"));
        extentReport.setSystemInfo("Browser Name", propConfig.getProperty("browser"));
        extentReport.setSystemInfo("Email", propConfig.getProperty("validEmail"));
        extentReport.setSystemInfo("Password", propConfig.getProperty("validPwd"));
        extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
        extentReport.setSystemInfo("Username", System.getProperty("user.name"));
        extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));

        return extentReport;
    }
}
