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
import org.testng.annotations.Listeners;
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
import utility.TestAllureListener;
import utility.WaitUtility;


@Listeners({TestAllureListener.class})
public class Login extends ListenerUtility{
	WebDriver driver;
	LaunchBrowserUtility objLaunchBrowserUtility = new LaunchBrowserUtility();
	LoginElements objLoginElements;
	WaitUtility ObjWait=new WaitUtility();
	HomePageElements objHomePage;
	ExcelUtility objExcelUtil;
	
	
	@Test(priority = 0, enabled = true, groups= {"Login"},description = "Verifying Valid Login Scenario")
	@Severity(SeverityLevel.BLOCKER)
	@Description("Test Case Description: Verify login page with valid credetials in Logi Page ")
	@Story("Story Name: To check login with valid Credentials")
	public void TC000() throws InterruptedException, IOException {

		objLoginElements.loginclick();
		ObjWait.waitSleep(3000);
		objHomePage.endtourclick();
		Assert.assertTrue(objHomePage.isHomePageLoaded());
		objHomePage.signout();
	}

	@Test(priority = 1, enabled = true,groups= {"Login"}, description = "Verifying Invalid Login Scenario")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Case Description: verify login with invalid credentials and validate error message")
	@Story("Story Name: To check login with invalid credentials")
	public void TC001() throws InterruptedException, IOException {

		objLoginElements.invalidloginclick();
		ObjWait.waitSleep(1000);
		Assert.assertTrue(objLoginElements.validateErrorMsg("These credentials do not match our records."));
	}
	
	@Test(priority = 2, enabled = true, groups= {"Login"},description = "Verifying Incorrect Login for Allure report")
	@Severity(SeverityLevel.BLOCKER)
	@Description("Test Case Description: verify login with invalid credentials and validate  wrong error message")
	@Story("Story Name: To Check invalid login error msg for Allure Report")
	public void TC002() throws InterruptedException, IOException {
		
		objLoginElements.invalidloginclick();
		ObjWait.waitSleep(1000);
		Assert.assertTrue(objLoginElements.validateErrorMsg("These credentials do not match our records1234."));
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
