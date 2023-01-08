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
import utility.WaitUtility;

public class ForgotPwd extends ListenerUtility{
	WebDriver driver;
	LaunchBrowserUtility objLaunchBrowserUtility = new LaunchBrowserUtility();
	ExcelUtility objExcelUtil;
	LoginElements objLoginElements;
	WaitUtility ObjWait;
	HomePageElements objHomePage;

	@Test(priority = 0, enabled = true, groups = {
			"ForgotPwd" }, description = "Forgotpwd Nonreg EmailId Scenario")
	public void TC003() throws InterruptedException {

		objLoginElements.forgotpwdclick(); 
		objLoginElements.EmailReset.sendKeys("rezna@gmail.com");
		objLoginElements.sendpwdreset();
		String actualmsgString = objLoginElements.InvalidUserEmail.getText();
		if (actualmsgString.contains("We can't find a user with that e-mail address.")) {
			SoftAssert sast = new SoftAssert();
			sast.assertTrue(true);
			sast.assertAll();
		} else {
			Assert.assertTrue(false);
		}
		
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
	}

	@Test(priority = 1, enabled = true, groups = {
			"ForgotPwd" }, description = "Forgotpwd Invalid EmailId Format Scenario")
	public void TC004() throws InterruptedException {

		objLoginElements.EmailReset.sendKeys("rezna-gmail/com");
		objLoginElements.sendpwdreset();
		String tooltipmsgString = "Please include an '@' in the email address.";
		String tooltipText = objLoginElements.EmailReset.getAttribute("title");// get tooltip text
		if (tooltipmsgString.contains(tooltipText)) {
			Assert.assertTrue(true);
		}
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
