package com.example.automation.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class ContainerizeExecution implements TestExecutionStrategy {

	@Override
	public WebDriver setupDriver(String browserName) {
		// TODO Auto-generated method stub
		switch (browserName.toLowerCase()) {
		case "chrome":
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--incognito");
			chromeOptions.addArguments("--headless");
			chromeOptions.addArguments("--disable-gpu");
			chromeOptions.addArguments("--no-sandbox");
			chromeOptions.addArguments("--disable-dev-shm-usage");
			chromeOptions.addArguments("--remote-allow-origins=*");
			chromeOptions.addArguments("--user-data-dir=/tmp/chrome-user-data-" + System.currentTimeMillis());
			return new ChromeDriver();
		case "firefox":
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.addArguments("--incognito");
			firefoxOptions.addArguments("--headless");
			firefoxOptions.addArguments("--disable-gpu");
			firefoxOptions.addArguments("--no-sandbox");
			firefoxOptions.addArguments("--disable-dev-shm-usage");
			firefoxOptions.addArguments("--remote-allow-origins=*");
			firefoxOptions.addArguments("--user-data-dir=/tmp/chrome-user-data-" + System.currentTimeMillis());
			return new FirefoxDriver();
		case "edge":
			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.addArguments("--incognito");
			edgeOptions.addArguments("--headless=new");
			edgeOptions.addArguments("--disable-gpu");
			edgeOptions.addArguments("--no-sandbox");
			edgeOptions.addArguments("--disable-dev-shm-usage");
			edgeOptions.addArguments("--remote-allow-origins=*");
			edgeOptions.addArguments("--user-data-dir=/tmp/chrome-user-data-" + System.currentTimeMillis());
			return new EdgeDriver();
		default:
			return null;
		}
	}

}
