package com.example.automation.base;

import org.openqa.selenium.WebDriver;

public class DriverContext {
	private TestExecutionStrategy testExecutionStrategy;

	public DriverContext(TestExecutionStrategy testExecutionStrategy) {
		this.testExecutionStrategy = testExecutionStrategy;
	}

	public void setExecutionStrategy(TestExecutionStrategy testExecutionStrategy) {
		this.testExecutionStrategy = testExecutionStrategy;
	}

	public WebDriver getWebDriver(String browserName) {
		return testExecutionStrategy.setupDriver(browserName);
	}

}
