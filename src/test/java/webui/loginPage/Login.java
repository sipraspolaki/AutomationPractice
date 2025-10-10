package webui.loginPage;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.example.automation.base.BaseFactory;
import com.example.automation.pages.LandingPage;
import com.example.automation.pages.LoginPage;

import io.qameta.allure.Allure;

@Test(groups = {"web"})
public class Login extends BaseFactory {

    LoginPage loginPage;
    LandingPage landingPage;
   
    private static final Logger log = LogManager.getLogger(Login.class);
	
	@BeforeClass
	public void setUp() throws IOException {
		loginPage = new LoginPage();
        landingPage = new LandingPage();
		loginPage.appLogin(prop.getProperty("url"),prop.getProperty("username"), prop.getProperty("password"));
		log.info("login successfull");
		Allure.step("Login completed");
	}

    @Test
    void validateLogin() throws IOException, InterruptedException{
        String actualTitle = loginPage.getTitle();
        log.info("Page title: " + actualTitle);
        int actualProductCount = landingPage.getInventoryItemCount(); // Ensure inventory items are loaded
        assertTrue(actualProductCount > 0, "No inventory items found as actualProductCount is: " + actualProductCount );
        log.info("Login test passed with product count as: "+ actualProductCount);
        Allure.step("Login validated successfuly");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
        log.info("Browser closed successfully");
        Allure.step("Browser Closed");
    }
    
}
