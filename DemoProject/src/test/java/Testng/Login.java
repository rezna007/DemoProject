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
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import utility.ExcelUtility;
import utility.LaunchBrowserUtility;
import utility.ListenerUtility;
import utility.WaitUtility;

public class Login extends ListenerUtility{
	WebDriver driver;
	LaunchBrowserUtility objLaunchBrowserUtility = new LaunchBrowserUtility();
	LoginElements objLoginElements;
	WaitUtility ObjWait;
	HomePageElements objHomePage;
	ExcelUtility objExcelUtil;

	@Test(priority = 0, enabled = true, groups= {"Login"},description = "Verifying Valid Login Scenario")
	@Severity(SeverityLevel.BLOCKER)
	@Description("Test Case Description: Verify login page with valid credetials in Logi Page ")
	@Story("Story Name: To check login with valid Credentials")
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

	@Test(priority = 1, enabled = true,groups= {"Login"}, description = "Verifying Invalid Login Scenario")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description: verify login into application with correct credentials")
	@Story("Story Name: To check login with invalid credentials")
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
