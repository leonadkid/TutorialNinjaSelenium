package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage
{
    WebDriver driver;


    @FindBy(linkText = "HP LP3065")
    private WebElement validProd;

    @FindBy(xpath = "//div[@id='content']/p[contains(text(), 'There is no product')]")
    private WebElement warningNoProd;

    public SearchPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean checkIfValidProd()
    {
        return validProd.isDisplayed();
    }

    public String getWarningNoProdMessage()
    {
        return warningNoProd.getText();
    }
}
