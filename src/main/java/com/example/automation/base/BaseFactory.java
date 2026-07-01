package com.example.automation.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class BaseFactory {

	public static WebDriver driver;
	public static Properties prop;
	private static String propertyFile = System.getProperty("user.dir") + File.separator + "profiles" + File.separator
			+ "dev.properties";

	private static final Logger log = LogManager.getLogger(BaseFactory.class);

	@Parameters("browser")
	@BeforeTest(groups = { "web" })
	public void driverInitialization(String browser) {

		String testExecutionStrategy = prop.getProperty("testExecutionStrategy").toLowerCase().trim();

		DriverContext driverContext = null;
		// le -> local execution, he -> headless execution , ce -> containerize
		// execution
		switch (testExecutionStrategy) {
		case "le":
			driverContext = new DriverContext(new LocalExecution());
			driver = driverContext.getWebDriver(browser);
			log.info("Local mode execution started");
			break;
		case "he":
			driverContext = new DriverContext(new HeadlessExecution());
			driver = driverContext.getWebDriver(browser);
			log.info("Headless mode execution started");
			break;
		case "ce":
			driverContext = new DriverContext(new ContainerizeExecution());
			driver = driverContext.getWebDriver(browser);
			log.info("Containerize mode execution started");
			break;
		default:
			// driver = null;
			log.error("Test Execution Strategy is not matching expected values le,ce or he");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@BeforeSuite(groups = { "web" , "api"})
	public void setupAPITestConfiguration() {
		try {
			prop = new Properties();
			prop.load(new FileInputStream(propertyFile));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
