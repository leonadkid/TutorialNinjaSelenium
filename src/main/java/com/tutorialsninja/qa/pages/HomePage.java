package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage
{
    WebDriver driver;

    @FindBy(xpath = "//span[text()='My Account']")
    private WebElement myAccountDropMenu;

    @FindBy(xpath = "//a[normalize-space()='Login']")
    private WebElement loginOption;

    @FindBy(xpath = "//li[@class='dropdown open']//a[text()='Register']")
    private WebElement registerOption;

    @FindBy(name = "search")
    private WebElement searchField;

    @FindBy(css = ".btn.btn-default.btn-lg")
    private WebElement searchBtn;

    public HomePage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnMyAccount()
    {
        myAccountDropMenu.click();
    }

    public void selectLoginOption()
    {
        loginOption.click();
    }

    public void selectRegisterOption()
    {
        registerOption.click();
    }

    public LoginPage navigateToLoginPage()
    {
        clickOnMyAccount();
        selectLoginOption();
        return new LoginPage(driver);
    }

    public RegisterPage navigateToRegisterPage()
    {
        clickOnMyAccount();
        selectRegisterOption();
        return new RegisterPage(driver);
    }

    public SearchPage searchByProduct(String prodName)
    {
        searchField.sendKeys(prodName);
        searchBtn.click();
        return new SearchPage(driver);
    }

    public SearchPage clickSearchBtn()
    {
        searchBtn.click();
        return new SearchPage(driver);
    }
}
