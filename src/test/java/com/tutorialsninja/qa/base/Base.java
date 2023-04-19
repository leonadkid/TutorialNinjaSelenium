package com.tutorialsninja.qa.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Base
{
    WebDriver driver;
    protected Properties propConfig;
    protected Properties propData;

    public Base()
    {
        propConfig = new Properties();
        File fileConfig = new File(System.getProperty("user.dir") + "/src/main/java/com/tutorialsninja/qa/config/config.properties");
        FileInputStream fisConfig = null;
        try {
            fisConfig = new FileInputStream(fileConfig);
            propConfig.load(fisConfig);
            propData = new Properties();
            File fileData = new File(System.getProperty("user.dir") + "/src/main/java/com/tutorialsninja/qa/testdata/testdata.properties");
            FileInputStream fisData = new FileInputStream(fileData);
            propData.load(fisData);
        } catch (RuntimeException | IOException e) {
            e.printStackTrace();
        }
    }

    public WebDriver initializeBrowserAndOpenApplicationURL(String browserName)
    {
        if (browserName.equals("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--start-maximized");
            options.addArguments("--headless");
            driver = new ChromeDriver(options);
        }
        else if (browserName.equals("firefox")) {
            driver = new FirefoxDriver();
        }
        else if (browserName.equals("safari")) {
            driver = new SafariDriver();
        }
        else if (browserName.equals("edge")) {
            EdgeOptions option = new EdgeOptions();
            option.addArguments("--remote-allow-origins=*");
            driver = new EdgeDriver(option);
        }
        driver.get(propConfig.getProperty("url"));
        return driver;
    }
}
