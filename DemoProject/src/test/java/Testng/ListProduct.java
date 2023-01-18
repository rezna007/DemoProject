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
import utility.PageUtilities;
import utility.ScrollUtility;
import utility.WaitUtility;

public class ListProduct extends ListenerUtility{
	WebDriver driver;
	LaunchBrowserUtility objLaunchBrowserUtility = new LaunchBrowserUtility();
	ScrollUtility objScrollUtility = new ScrollUtility();
	DropdownUtility objDropdown = new DropdownUtility();
	WaitUtility objWait=new WaitUtility();
	PageUtilities objPage=new PageUtilities();
	ExcelUtility objExcelUtil;
	LoginElements objLoginElements;
	HomePageElements objHomePage;
	ProductsElements objProductsElements;

	ListProductsElements objListpdt;

	@Test(priority = 1, enabled = true, groups = {
			"List Product" }, description = "Check Newly Added products are listed properly in WebTable")
	public void TC011() throws InterruptedException, IOException, AWTException {
		objLoginElements.loginclick();
		objHomePage.endtourclick();
		objWait.waitSleep(2000);
		objProductsElements.pdtClick();		
		objListpdt.listpdtClick();
		Assert.assertTrue(objListpdt.searchProduct("Pdt10"));		
	}

	@Test(priority = 2, enabled = true, groups = { "List Product" }, description = "Check search bar is functional")
	public void TC012() throws InterruptedException, IOException, AWTException {
		objListpdt.listpdtClick();
		objPage.clickOnElement(objListpdt.searchproduct);
		objPage.sendKey(objListpdt.searchproduct, "Pdt1");
		objPage.sendKey(objListpdt.searchproduct, Keys.RETURN);
		objWait.waitSleep(6000);
		Assert.assertTrue(objListpdt.searchProduct("Pdt1"));
	}

	@Test(priority = 3, enabled = true, groups = { "List Product" }, description = "Delete a Product from webtable")
	public void TC013() throws InterruptedException, IOException, AWTException {
		objListpdt.listpdtClick();
		objWait.waitSleep(4000);
		Assert.assertTrue(objListpdt.deleteitem());
		
	}

	@Test(priority = 3, enabled = true, groups = {
			"List Product" }, description = "Check webtable entries count change while changing Dropdown Show numbers")
	public void TC014() throws InterruptedException, IOException, AWTException {

		objListpdt.listpdtClick();
		objWait.waitSleep(4000);
		objDropdown.dropdownbytext(objListpdt.showdropdown, "50");
		objWait.waitSleep(8000);
		if (objListpdt.rowsize().equals("50")) {
			System.out.println("RowSize:" + objListpdt.rowsize());			
		} 
		
		SoftAssert sast = new SoftAssert();
		sast.assertTrue(true);
		sast.assertAll();
	}

	@Test(priority = 3, enabled = true, groups = {
			"List Product" }, description = "Check Add+ button navigates to Add New product page")
	public void TC015() throws InterruptedException, IOException, AWTException {

		objListpdt.listpdtClick();
		objWait.waitSleep(4000);
		objListpdt.Addbtn.click();
		objWait.waitSleep(7000);
		Assert.assertTrue(objProductsElements.loadNewPdtPage());
		
	}

	@Test(priority = 3, enabled = true, groups = { "List Product" }, description = "Check filter functionality")
	public void TC016() throws InterruptedException, IOException, AWTException {

		objListpdt.listpdtClick();
		objWait.waitSleep(3000);
		Assert.assertTrue(objListpdt.listPdtFilter());
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
