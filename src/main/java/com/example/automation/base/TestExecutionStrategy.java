package com.example.automation.base;

import org.openqa.selenium.WebDriver;

public interface TestExecutionStrategy {
	
	WebDriver setupDriver(String browsername);

}
