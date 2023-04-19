package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage
{
    WebDriver driver;

    @FindBy(id = "input-firstname")
    private WebElement firstNameField;

    @FindBy(id = "input-lastname")
    private WebElement lastNameField;

    @FindBy(id = "input-email")
    private WebElement emailField;

    @FindBy(id = "input-telephone")
    private WebElement phoneField;

    @FindBy(id = "input-password")
    private WebElement pwdField;

    @FindBy(id = "input-confirm")
    private WebElement pwdConfirmField;

    @FindBy(xpath = "//input[@type='checkbox']")
    private WebElement privacy;

    @FindBy(css = ".btn.btn-primary")
    private WebElement continueBtn;

    @FindBy(xpath = "//label[text()='Subscribe']/following-sibling::div//input[@value=1]")
    private WebElement subcribeYes;

    @FindBy(css = ".alert.alert-danger.alert-dismissible")
    private WebElement alertMessage;

    public RegisterPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setFirstNameField(String firstName)
    {
        firstNameField.sendKeys(firstName);
    }

    public void setLastNameField(String lastName)
    {
        lastNameField.sendKeys(lastName);
    }

    public void setEmailField(String email)
    {
        emailField.sendKeys(email);
    }

    public void setPhoneField(String phone)
    {
        phoneField.sendKeys(phone);
    }

    public void setPwdField(String pwd)
    {
        pwdField.sendKeys(pwd);
    }

    public void setPwdConfirmField(String pwd)
    {
        pwdConfirmField.sendKeys(pwd);
    }

    public void checkPrivacyCheckbox()
    {
        privacy.click();
    }

    public void clickContinueBtn()
    {
        continueBtn.click();
    }

    public void setSubscribeYes()
    {
        subcribeYes.click();
    }

    public String getMessageAlert()
    {
        return alertMessage.getText();
    }

    public AccountSuccessPage registerwithMandatoryFields(String fName, String lName, String email, String phone, String pwd, String cnfPwd)
    {
        setFirstNameField(fName);
        setLastNameField(lName);
        setEmailField(email);
        setPhoneField(phone);
        setPwdField(pwd);
        setPwdConfirmField(cnfPwd);
        checkPrivacyCheckbox();
        clickContinueBtn();
        return new AccountSuccessPage(driver);
    }

    public AccountSuccessPage registerWithAllFields(String fName, String lName, String email, String phone, String pwd, String cnfPwd)
    {
        setFirstNameField(fName);
        setLastNameField(lName);
        setEmailField(email);
        setPhoneField(phone);
        setPwdField(pwd);
        setPwdConfirmField(cnfPwd);
        checkPrivacyCheckbox();
        setSubscribeYes();
        clickContinueBtn();
        return new AccountSuccessPage(driver);
    }
}
