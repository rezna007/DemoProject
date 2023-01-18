package Testng;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import PageElements.HomePageElements;
import PageElements.LoginElements;
import utility.ExcelUtility;
import utility.LaunchBrowserUtility;
import utility.ListenerUtility;
import utility.PageUtilities;
import utility.WaitUtility;

public class ForgotPwd extends ListenerUtility{
	WebDriver driver;
	LaunchBrowserUtility objLaunchBrowserUtility = new LaunchBrowserUtility();
	PageUtilities objPage=new PageUtilities();
	ExcelUtility objExcelUtil;
	LoginElements objLoginElements;
	WaitUtility ObjWait;
	HomePageElements objHomePage;

	@Test(priority = 0, enabled = true, groups = {
			"ForgotPwd" }, description = "Forgotpwd Nonreg EmailId Scenario")
	public void TC003() throws InterruptedException {

		objLoginElements.forgotpwdclick(); 
		ObjWait.implicitWait(driver, 2000);
		objPage.sendKey(objLoginElements.EmailReset, "rezna@gmail.com");
		objLoginElements.sendpwdreset();
		String actualmsgString = objLoginElements.InvalidUserEmail.getText();
		String expectedmsgString ="We can't find a user with that e-mail address.";
		Assert.assertEquals(actualmsgString, expectedmsgString, "Expected validation msg shown");
//			SoftAssert sast = new SoftAssert();
//			sast.assertEquals(actualmsgString, expectedmsgString, "Expected validation msg shown");
//			sast.assertAll();
	}

	@Test(priority = 1, enabled = true, groups = {
			"ForgotPwd" }, description = "Forgotpwd Invalid EmailId Format Scenario")
	public void TC004() throws InterruptedException {

		objPage.clearText(objLoginElements.EmailReset);
		objPage.sendKey(objLoginElements.EmailReset, "rezna@gmail.com");
		objLoginElements.sendpwdreset();
		Assert.assertTrue(objLoginElements.chktootip());					
	}
	@BeforeTest
	public void beforeTest() throws Exception {

		objExcelUtil = new ExcelUtility();
		String url = objExcelUtil.readData(1, 0);
		objLaunchBrowserUtility.launchBrowser(url, "chrome");
		this.driver = objLaunchBrowserUtility.driver;
		objLoginElements = new LoginElements(driver);
		objHomePage = new HomePageElements(driver);
		ObjWait=new WaitUtility();
	}

	@AfterTest
	public void afterTest() throws InterruptedException {
		objLaunchBrowserUtility.Closebrowser();
	}

}
