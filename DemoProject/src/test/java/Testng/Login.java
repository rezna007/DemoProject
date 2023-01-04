package Testng;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import PageElements.HomePageElements;
import PageElements.LoginElements;
import io.opentelemetry.semconv.trace.attributes.SemanticAttributes.FaasTriggerValues;
import utility.ExcelUtility;
import utility.LaunchBrowserUtility;
import utility.ListenerUtility;
import utility.WaitUtility;

public class Login {
	WebDriver driver;
	LaunchBrowserUtility objLaunchBrowserUtility = new LaunchBrowserUtility();
	LoginElements objLoginElements;
	WaitUtility ObjWait;
	HomePageElements objHomePage;
	ExcelUtility objExcelUtil;

	@Test(priority = 0, enabled = true, groups= {"Login"},description = "Valid Login Scenario")
	public void TC001() throws InterruptedException, IOException {

		objLoginElements.loginclick();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		objHomePage.endtourclick();
		if (objHomePage.isHomePageLoaded()) {
			Assert.assertTrue(true);
			System.out.println("LoggedIn Successfully");
			objHomePage.signout();
		} else {
			System.out.println("LogIn Failed");

		}

	}

	@Test(priority = 1, enabled = true,groups= {"Login"}, description = "Invalid Login Scenario")
	public void TC002() throws InterruptedException, IOException {

		objLoginElements.invalidloginclick();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		if (objLoginElements.getTextOfInvalidLoginError().contains("These credentials do not match our records.")) {
			SoftAssert sast = new SoftAssert();
			sast.assertTrue(true);
			System.out.println("Invalid Credentials");
			sast.assertAll();
		} else {
			Assert.assertTrue(false, "Logged in with invalid credentials");
			
		}
	}

	@BeforeTest
	@Parameters("browser")
	public void beforeTest(String browser) throws Exception {

		objExcelUtil = new ExcelUtility();
		String url = objExcelUtil.readData(1, 0);
		objLaunchBrowserUtility.launchBrowser(url, browser);
		this.driver = objLaunchBrowserUtility.driver;
		objLoginElements = new LoginElements(driver);
		objHomePage = new HomePageElements(driver);
		

	}

	@AfterTest
	public void afterTest() throws InterruptedException {
		objLaunchBrowserUtility.Closebrowser();
	}

}
