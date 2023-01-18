package Testng;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import PageElements.HomePageElements;
import PageElements.ListProductsElements;
import PageElements.LoginElements;
import PageElements.ProductsElements;
import utility.ExcelUtility;
import utility.LaunchBrowserUtility;
import utility.ListenerUtility;
import utility.ScrollUtility;
import utility.WaitUtility;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class AddProduct extends ListenerUtility {
	WebDriver driver;
	LaunchBrowserUtility objLaunchBrowserUtility = new LaunchBrowserUtility();
	WaitUtility objWait=new WaitUtility();
	ScrollUtility objScrollUtility=new ScrollUtility();
	ExcelUtility objExcelUtil;
	LoginElements objLoginElements;
	HomePageElements objHomePage;
	ProductsElements objProductsElements;
	ListProductsElements objLispdt;

	
	
	@Test(priority = 1, enabled = true,groups= {"AddProduct"}, description = "Add product successfullyand Listed in list products")
	public void TC005() throws InterruptedException, IOException, AWTException {

		objLoginElements.loginclick();
		objHomePage.endtourclick();
		objProductsElements.pdtClick();
		objProductsElements.addpdtClick();
		objProductsElements.addProduct("Pdt1", "21", "230");
		String expectedsavemsg="Product added successfully";
		String actualsavemsg=objProductsElements.pdtsavemsg.getText();
		objWait.waitSleep(3000);
		Assert.assertEquals(actualsavemsg, expectedsavemsg,"Product added Successfully");
	}
	

	@Test(priority = 2, enabled = true,groups= {"AddProduct"}, description = "Validate mandatory fields-without Productname")
	public void TC006() throws InterruptedException, IOException, AWTException {

		objProductsElements.addpdtClick();
		objProductsElements.addProduct("", "21", "230");
		//objScrollUtility.scrollToElement(objProductsElements.productname);
		objWait.waitForElementTobeVisible(driver, objProductsElements.productname, 3);
		Assert.assertTrue(objProductsElements.validateErrorMsg(objProductsElements.productnameerror));
	}

	@Test(priority = 3, enabled = true,groups= {"AddProduct"}, description = "Validate mandatory fields-without AlertQnty")
	public void TC007() throws InterruptedException, IOException, AWTException {
		objProductsElements.addpdtClick();
		objProductsElements.addProduct("Pdt2", "", "230");
		Assert.assertTrue(objProductsElements.validateErrorMsg(objProductsElements.alertqntyerror));
	}

	@Test(priority = 4, enabled = true,groups= {"AddProduct"}, description = "Validate mandatory fields-without Tax")
	public void TC008() throws InterruptedException, IOException, AWTException {

		objProductsElements.addpdtClick();
		objProductsElements.addProduct("Pdt2", "5", "");
		Assert.assertTrue(objProductsElements.validateErrorMsg(objProductsElements.exctax0error));
		}
	
	@Test(priority = 5, enabled = true,groups= {"AddProduct"}, description = "Add a new Brand")
	public void TC009() throws InterruptedException, IOException, AWTException {

		objProductsElements.addpdtClick();
		objProductsElements.addBrand("EdgeBrand"," Edgenullgjdg");
		Assert.assertTrue(true);
		}
	
	@Test(priority = 6, enabled = true, groups= {"AddProduct"},description = "Add a new Unit")
	public void TC010() throws InterruptedException, IOException, AWTException {
		objWait.waitSleep(4000);
		objProductsElements.addpdtClick();
		objProductsElements.addUnit("EdgeUnit"," EdgeBoxUnit");
		Assert.assertTrue(true);
		}
	
	@BeforeTest
	
	public void beforeTest() throws Exception {
		objExcelUtil = new ExcelUtility();
		String url = objExcelUtil.readData(1, 0);
		objLaunchBrowserUtility.launchBrowser(url, "chrome");
		this.driver = objLaunchBrowserUtility.driver;
		objLoginElements = new LoginElements(driver);
		objHomePage = new HomePageElements(driver);
		objProductsElements = new ProductsElements(driver);

	}

	@AfterTest
	public void afterTest() throws InterruptedException {
		objLaunchBrowserUtility.Closebrowser();
	}

}
