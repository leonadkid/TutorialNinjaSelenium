package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage
{
    WebDriver driver;

    @FindBy(id = "input-email")
    private WebElement emailField;

    @FindBy(id = "input-password")
    private WebElement pwdField;

    @FindBy(css = "input.btn-primary")
    private WebElement loginbtn;

    @FindBy(css = ".alert.alert-danger.alert-dismissible")
    private WebElement invalidPwdWarning;

    public LoginPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterEmailAddress(String emailText)
    {
        emailField.sendKeys(emailText);
    }

    public void enterPwd(String pwdText)
    {
        pwdField.sendKeys(pwdText);
    }

    public void clickLogin()
    {
        loginbtn.click();
    }

    public boolean checkInvalidCredentialWarningDisplay()
    {
        return invalidPwdWarning.isDisplayed();
    }

    public AccountPage login(String email, String pwd)
    {
        enterEmailAddress(email);
        enterPwd(pwd);
        clickLogin();
        return new AccountPage(driver);
    }
}
