package com.example.automation.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class HeadlessExecution implements TestExecutionStrategy {

	@Override
	public WebDriver setupDriver(String browserName) {
		// TODO Auto-generated method stub
		switch (browserName.toLowerCase()) {
		case "chrome":
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--headless");
			chromeOptions.addArguments("--incognito");
			chromeOptions.addArguments("--disable-gpu");
			return new ChromeDriver(chromeOptions);
		case "firefox":
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.addArguments("--headless=new");
			firefoxOptions.addArguments("--incognito");
			firefoxOptions.addArguments("--disable-gpu");
			return new FirefoxDriver(firefoxOptions);
		case "edge":
			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.addArguments("--headless=new");
			edgeOptions.addArguments("--incognito");
			edgeOptions.addArguments("--disable-gpu");
			return new EdgeDriver(edgeOptions);
		default:
			return null;
		}
	}
}
