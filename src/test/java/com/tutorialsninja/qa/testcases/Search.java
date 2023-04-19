package com.tutorialsninja.qa.testcases;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class Search extends Base
{
    SearchPage searchPage;
    HomePage homePage;
    public WebDriver driver;

    public Search() throws IOException
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
        homePage = new HomePage(driver);
    }

    @Test(priority = 1)
    public void verifySearchWithValidProduct()
    {
        searchPage = homePage.searchByProduct(propData.getProperty("validProd"));

        //Assert the HP LP3065 is displayed
        Assert.assertTrue(searchPage.checkIfValidProd());
    }

    @Test(priority = 2)
    public void verifySearchWithInvalidProduct()
    {
        searchPage = homePage.searchByProduct(propData.getProperty("inValidProd"));

        //Assert the error message is displayed
        //Assert.assertEquals(searchPage.getWarningNoProdMessage(), propData.getProperty("warningMessageNoProd"));
        Assert.assertEquals(searchPage.getWarningNoProdMessage(), "thao");
    }

    @Test(priority = 3, dependsOnMethods = {"verifySearchWithValidProduct", "verifySearchWithInvalidProduct"})
    public void verifySearchWithoutAnyProduct()
    {
        searchPage = homePage.clickSearchBtn();
        Assert.assertEquals(searchPage.getWarningNoProdMessage(), propData.getProperty("warningMessageNoProd"));
    }
}
