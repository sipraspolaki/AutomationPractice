package com.example.automation.pages;

import java.io.File;
import java.io.IOException;
import java.sql.Driver;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.tracing.Propagator;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.example.automation.base.BaseFactory;
import com.example.automation.utils.commonUtils.UIUtils;

public class LoginPage extends BaseFactory {

    UIUtils uiUtils = new UIUtils();

    public LoginPage() throws IOException {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@id='user-name']")
	WebElement usernameInput;

    @FindBy(xpath="//input[@id='password']")
    WebElement passwordInput;

    @FindBy(xpath="//input[@id='login-button']")
    WebElement loginButton;
    
    public void appLogin(String url, String username, String password) {
         driver.get(url);
         usernameInput.sendKeys(username);
         passwordInput.sendKeys(password);
         loginButton.click();
    }

    public String getTitle() throws IOException {
       uiUtils.takeScreenShot(); // Capture screenshot after login
       uiUtils.allureScreenshot("PostLoginImage"); // Capture screenshot after login and append to Allure Report
       return driver.getTitle();
    }
}
