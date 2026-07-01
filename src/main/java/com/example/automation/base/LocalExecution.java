package com.example.automation.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class LocalExecution implements TestExecutionStrategy {

	@Override
/*	public WebDriver setupDriver(String browserName) {
		// TODO Auto-generated method stub
		switch (browserName.toLowerCase()) {
		case "chrome":
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			return new ChromeDriver(options);
		case "firefox":
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.addArguments("-private");
			return new FirefoxDriver(firefoxOptions);
		case "edge":
			System.setProperty("webdriver.edge.driver", ".\\drivers\\msedgedriver.exe");
			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.addArguments("--inprivate");
			return new EdgeDriver(edgeOptions);
		default:
			return null;*/
	
	public WebDriver setupDriver(String browserName) {

	    boolean isGitHub = System.getenv("GITHUB_ACTIONS") != null;

	    switch (browserName.toLowerCase()) {

	    case "chrome":
	        ChromeOptions chromeOptions = new ChromeOptions();
	        chromeOptions.addArguments("--incognito");

	        if (isGitHub) {
	            chromeOptions.addArguments("--headless=new");
	            chromeOptions.addArguments("--no-sandbox");
	            chromeOptions.addArguments("--disable-dev-shm-usage");
	            chromeOptions.addArguments("--window-size=1920,1080");
	        }

	        return new ChromeDriver(chromeOptions);

	    case "firefox":
	        FirefoxOptions firefoxOptions = new FirefoxOptions();
	        firefoxOptions.addArguments("-private");

	        if (isGitHub) {
	            firefoxOptions.addArguments("--headless");
	        }

	        return new FirefoxDriver(firefoxOptions);

	    case "edge":
	        EdgeOptions edgeOptions = new EdgeOptions();
	        edgeOptions.addArguments("--inprivate");

	        if (isGitHub) {
	            edgeOptions.addArguments("--headless=new");
	            edgeOptions.addArguments("--no-sandbox");
	            edgeOptions.addArguments("--disable-dev-shm-usage");
	            edgeOptions.addArguments("--window-size=1920,1080");
	        }

	        return new EdgeDriver(edgeOptions);

	    default:
	        throw new IllegalArgumentException(
	                "Unsupported browser: " + browserName);
	    }
	}
}
