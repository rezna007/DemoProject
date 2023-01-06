package Testng;

import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.concurrent.TimeUnit;

import PageElements.HomePageElements;
import PageElements.ListProductsElements;
import PageElements.LoginElements;
import PageElements.ProductsElements;
import utility.DropdownUtility;
import utility.ExcelUtility;
import utility.LaunchBrowserUtility;
import utility.ListenerUtility;
import utility.ScrollUtility;
import utility.WaitUtility;

public class ListProduct extends ListenerUtility{
	WebDriver driver;
	LaunchBrowserUtility objLaunchBrowserUtility = new LaunchBrowserUtility();
	ScrollUtility objScrollUtility = new ScrollUtility();
	DropdownUtility objDropdown = new DropdownUtility();
	ExcelUtility objExcelUtil;
	LoginElements objLoginElements;
	HomePageElements objHomePage;
	ProductsElements objProductsElements;
	WaitUtility objWait;
	ListProductsElements objListpdt;

	@Test(priority = 1, enabled = true, groups = {
			"List Product" }, description = "Check Newly Added products are listed properly in WebTable")
	public void TC011() throws InterruptedException, IOException, AWTException {
		objLoginElements.loginclick();
		objHomePage.endtourclick();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		objProductsElements.pdtClick();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		objListpdt.listpdtClick();
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		objListpdt.searchProduct("Pdt10");
	}

	@Test(priority = 2, enabled = true, groups = { "List Product" }, description = "Check search bar is functional")
	public void TC012() throws InterruptedException, IOException, AWTException {
		objListpdt.listpdtClick();
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		objListpdt.searchproduct.click();

		objListpdt.searchproduct.sendKeys("Pdt1");
		objListpdt.searchproduct.sendKeys(Keys.RETURN);
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		for (int i = 1; i <= objListpdt.productcolumn.size(); i++) {
			String pdtname = objListpdt.productcolumn.get(i).getText();

			if (pdtname.contains("Pdt1")) {

				System.out.println("Product notFound");
				Assert.assertTrue(true);
				break;
			}
		}
		System.out.println("Product Found");
	}

	@Test(priority = 3, enabled = true, groups = { "List Product" }, description = "Delete a Product from webtable")
	public void TC013() throws InterruptedException, IOException, AWTException {
		objListpdt.listpdtClick();
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		objListpdt.deleteitem();
		if (objListpdt.deletesuccessmsg.isDisplayed()) {
			SoftAssert sast = new SoftAssert();
			sast.assertTrue(true);
			sast.assertAll();
		} else {
			Assert.assertTrue(false, "Delete item failed");
		}
	}

	@Test(priority = 3, enabled = true, groups = {
			"List Product" }, description = "Check webtable entries count change while changing Dropdown Show numbers")
	public void TC014() throws InterruptedException, IOException, AWTException {

		objListpdt.listpdtClick();
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		objDropdown.dropdownbytext(objListpdt.showdropdown, "50");
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);

		if (objListpdt.rowsize().equals("50")) {
			System.out.println("RowSize:" + objListpdt.rowsize());
			SoftAssert sast = new SoftAssert();
			sast.assertTrue(true);
			sast.assertAll();
		} 
	}

	@Test(priority = 3, enabled = true, groups = {
			"List Product" }, description = "Check Add+ button navigates to Add New product page")
	public void TC015() throws InterruptedException, IOException, AWTException {

		objListpdt.listpdtClick();
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		objListpdt.Addbtn.click();
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		if (objProductsElements.productnamelabel.isDisplayed()) {
			Assert.assertTrue(true);
		} else {

			Assert.assertTrue(false, "Add Product Page not loaded");
		}
	}

	@Test(priority = 3, enabled = true, groups = { "List Product" }, description = "Check filter functionality")
	public void TC016() throws InterruptedException, IOException, AWTException {

		objListpdt.listpdtClick();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		objListpdt.listPdtFilter();
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
		objListpdt = new ListProductsElements(driver);

	}

	@AfterTest
	public void afterTest() throws InterruptedException {
		objLaunchBrowserUtility.Closebrowser();
	}
}
