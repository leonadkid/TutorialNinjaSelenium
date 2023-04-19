package com.tutorialsninja.qa.testcases;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class Login extends Base
{
    LoginPage loginPage;
    public WebDriver driver;

    public Login() throws IOException
    {
        super();
    }

    @AfterMethod
    public void tearDown()
    {
        driver.quit();
    }

    @BeforeMethod
    public void setup() throws IOException
    {
        driver = initializeBrowserAndOpenApplicationURL(propConfig.getProperty("browser"));
        HomePage homePage = new HomePage(driver);
        loginPage = homePage.navigateToLoginPage();
    }

    @Test(priority = 1, dataProvider = "validCredentialSupplier")
    public void verifyLoginWithValidCredentials(String email, String pwd)
    {
        AccountPage accountPage = loginPage.login(email, pwd);
        Assert.assertTrue(accountPage.checkDisplayOfEditAccount());
    }

    @DataProvider(name = "validCredentialSupplier")
    public Object[][] getDataFromExcel() throws IOException, InvalidFormatException
    {
        return Utilities.getTestDataFromExcel("login");
    }

    @Test(priority = 2)
    public void verifyLoginWithInvalidCredentials()
    {
        loginPage.login(Utilities.generateEmailTimeStamp(), propData.getProperty("invalidPassword"));
        Assert.assertTrue(loginPage.checkInvalidCredentialWarningDisplay());
    }

    @Test(priority = 3)
    public void verifyLoginWithValidEmailInvalidPwd()
    {
        loginPage.login(propConfig.getProperty("validEmail"), propData.getProperty("invalidPassword"));
        Assert.assertTrue(loginPage.checkInvalidCredentialWarningDisplay());
    }

    @Test(priority = 4)
    public void verifyLoginWithValidPwdInvalidEmail()
    {
        loginPage.login(Utilities.generateEmailTimeStamp(), propConfig.getProperty("validPwd"));
        Assert.assertTrue(loginPage.checkInvalidCredentialWarningDisplay());
    }

    @Test(priority = 5)
    public void verifyLoginWithoutCredentials()
    {
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.checkInvalidCredentialWarningDisplay());
    }
}
