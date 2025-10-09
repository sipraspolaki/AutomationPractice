package com.example.automation.utils.commonUtils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.automation.base.BaseFactory;

public class UIUtils extends BaseFactory {
    
   // Utility method to capture screenshots and save in Screenshot direectory
	public void takeScreenShot() throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File srcFile=ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile,new File(System.getProperty("user.dir")+File.separator+"Screenshots"+File.separator+ System.currentTimeMillis()+".png"));
	}

    // Utility method for explicit wait checking visibility of an element
	public static void waitForElementVisiblibility(WebElement element, int waitTime) {
		Duration time = Duration.ofSeconds(waitTime);
		new WebDriverWait(driver,time).until(ExpectedConditions.visibilityOf(element));
	}

	public static void waitForElementVisiblibility(List<WebElement> elements, int waitTime) {
		Duration time = Duration.ofSeconds(waitTime);
		new WebDriverWait(driver,time).until(ExpectedConditions.visibilityOf(elements.get(0)));
	}
	
	// Utility method for explicit wait checking in-visibility of an element
	public static void waitForElementInVisiblibility(WebElement element, int waitTime) {
		Duration time = Duration.ofSeconds(waitTime);
		new WebDriverWait(driver,time).until(ExpectedConditions.invisibilityOf(element));
	}
	
	// Utility method for explicit wait checking an element is clickable or not
	public static void waitForElementToBeClickable(WebElement element, int waitTime) {
		Duration time = Duration.ofSeconds(waitTime);
		new WebDriverWait(driver,time).until(ExpectedConditions.elementToBeClickable(element));
	}
	
	// Utility method for explicit wait and switching to frames
	public static void waitForFrameAndSwitchToIt(WebElement element, int waitTime) {
		Duration time = Duration.ofSeconds(waitTime);
		new WebDriverWait(driver,time).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
	}

}
