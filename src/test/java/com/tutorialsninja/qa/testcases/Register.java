package com.tutorialsninja.qa.testcases;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class Register extends Base
{
    RegisterPage registerPage;
    AccountSuccessPage accountSuccessPage;
    public WebDriver driver;

    public Register() throws IOException
    {
        super();
    }

    @AfterMethod
    public void tearDown()
    {
        driver.quit();
    }

    @BeforeMethod
    public void setup()
    {
        driver = initializeBrowserAndOpenApplicationURL(propConfig.getProperty("browser"));
        HomePage homePage = new HomePage(driver);
        registerPage = homePage.navigateToRegisterPage();
    }

    @Test(priority = 1)
    public void verifyRegisterAccountWithMandatoryFields()
    {
        accountSuccessPage = registerPage.registerwithMandatoryFields(propData.getProperty("firstName"), propData.getProperty("lastName"), Utilities.generateEmailTimeStamp(), propData.getProperty("telephoneNumber"), propData.getProperty("pwd"), propData.getProperty("pwdConfirm"));
        Assert.assertEquals(accountSuccessPage.getMessageSuccess(), propData.getProperty("messageRegisterSuccessfully"));
    }

    @Test(priority = 2)
    public void verifyRegisterAccountProvidingAllFields()
    {
        accountSuccessPage = registerPage.registerWithAllFields(propData.getProperty("firstName"), propData.getProperty("lastName"), Utilities.generateEmailTimeStamp(), propData.getProperty("telephoneNumber"), propData.getProperty("pwd"), propData.getProperty("pwdConfirm"));
        Assert.assertEquals(accountSuccessPage.getMessageSuccess(), propData.getProperty("messageRegisterSuccessfully"));
    }

    @Test(priority = 3)
    public void verifyRegisterAccountNotAnyFields()
    {
        //Click on continue button
        registerPage.clickContinueBtn();
        //Check the message
        Assert.assertEquals(registerPage.getMessageAlert(), propData.getProperty("warningMessage"));
    }
}
