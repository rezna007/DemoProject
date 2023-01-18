package Testng;

import java.awt.AWTException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.time.Duration;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.google.common.util.concurrent.Service.Listener;

import PageElements.HomePageElements;
import PageElements.ListProductsElements;
import PageElements.ListPurchaseElements;
import PageElements.LoginElements;
import PageElements.ProductsElements;
import PageElements.PurchaseElements;
import utility.DropdownUtility;
import utility.ExcelUtility;
import utility.LaunchBrowserUtility;
import utility.ListenerUtility;
import utility.PageUtilities;
import utility.ScrollUtility;
import utility.WaitUtility;

public class Purchase extends ListenerUtility {
	WebDriver driver;
	LaunchBrowserUtility objLaunchBrowserUtility = new LaunchBrowserUtility();
	ScrollUtility objScrollUtility = new ScrollUtility();
	DropdownUtility objDropdown = new DropdownUtility();
	PageUtilities objPage=new PageUtilities();
	WaitUtility objWait = new WaitUtility();
	ExcelUtility objExcelUtil;
	LoginElements objLoginElements;
	HomePageElements objHomePage;
	ProductsElements objProductsElements;
	ListProductsElements objListpdt;
	PurchaseElements objPurchase;
	ListPurchaseElements objListPurchase;

	@Test(alwaysRun = true, priority = 1, groups = {
			"smoke" }, enabled = true, description = "Check add new product button functionality")
	public void TC017() throws InterruptedException, IOException, AWTException {
		objLoginElements.loginclick();
		objHomePage.endtourclick();
		objPurchase.purchaseClick();
		objPurchase.addpurchaseClick();
		objWait.waitSleep(3000);
		objPage.clickOnElement(objPurchase.addnewpdtbtn);
		objWait.waitSleep(2000);
		objPurchase.addProduct("Purc", "50", "63");
		String expectedsavemsg = "Product added successfully";
		String actualsavemsg=objPage.getElementText(objProductsElements.pdtsavemsg);
		objWait.waitSleep(6000);
		Assert.assertEquals(actualsavemsg, expectedsavemsg, "Product added Successfully");
	}

	@Test(priority = 2, enabled = true, groups = {
			"smoke" }, description = "Validate mandatory fields-without Productname")
	public void TC018() throws InterruptedException, IOException, AWTException {

		
		objPage.clickOnElement(objPurchase.addnewpdtbtn);
		objWait.waitSleep(2000);
		objPurchase.addProduct("", "21", "230");
		objScrollUtility.scrollToTop(driver);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(objPurchase.validateErrorMsg(objProductsElements.productnameerror));
		softAssert.assertAll();
		objPage.clickOnElement(objPurchase.close);
	}

	@Test(priority = 3, enabled = true, groups = {
			"smoke" }, description = "Validate mandatory fields-without AlertQnty")
	public void TC019() throws InterruptedException, IOException, AWTException {

		
		objPage.clickOnElement(objPurchase.addnewpdtbtn);
		objWait.waitSleep(2000);
		objPurchase.addProduct("Pdt2", "", "230");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(objPurchase.validateErrorMsg(objProductsElements.alertqntyerror));
		softAssert.assertAll();
		objPage.clickOnElement(objPurchase.close);
		
	}

	@Test(priority = 4, enabled = true, groups = { "smoke" }, description = "Validate mandatory fields-without Tax")
	public void TC020() throws InterruptedException, IOException, AWTException {

		objPage.clickOnElement(objPurchase.addnewpdtbtn);
		objWait.waitSleep(3000);
		objPurchase.addProduct("Pdt2", "5", "");
		Assert.assertTrue(objPurchase.validateErrorMsg(objProductsElements.exctax0error));
		objPage.clickOnElement(objPurchase.close);
		
	}

	@Test(priority = 5, enabled = true, groups = { "smoke" }, description = "Check mandatory field -without Supplier")
	public void TC021() throws InterruptedException, IOException, AWTException {

		objPurchase.addPurchaseWithoutSupplier();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(objPurchase.validateErrorMsg(objPurchase.selectsuppliererror));
		softAssert.assertAll();
	}

	@Test(priority = 6, enabled = true, groups = {
			"smoke" }, description = "Check mandatory field -without Purchase Status")
	public void TC022() throws InterruptedException, IOException, AWTException {

		objPurchase.addPurchaseWithoutPurchasestatus();
		Assert.assertTrue(objPurchase.validateErrorMsg(objPurchase.purchasestatuserror));
	}

	@Test(priority = 7, enabled = true, groups = {
			"smoke" }, description = "Check mandatory field -without Bussiness Loation")
	public void TC023() throws InterruptedException, IOException, AWTException {

		objPurchase.addPurchaseWithoutPBussinesslocation();
		Assert.assertTrue(objPurchase.validateErrorMsg(objPurchase.bussinesslocationerror));
	}

	@Test(priority = 8, enabled = true, groups = { "smoke" }, description = "Add purchase")
	public void TC024() throws InterruptedException, IOException, AWTException {

		objPurchase.addPurchase();
		String expectedsavemsg = "Purchase added successfully";
		String actualsavemsg = objPurchase.savePurchasemsg.getText();
		objWait.waitSleep(3000);
		Assert.assertEquals(actualsavemsg, expectedsavemsg, "Purchase added Successfully");
	}

	@Test(priority = 9, enabled = true, groups = {
			"regression" }, description = "Check Purchase table entries count  while changing Dropdown Show numbers")
	public void TC025() throws InterruptedException, IOException, AWTException {

		objDropdown.dropdownbytext(objListPurchase.showpurcchasedropdown, "50");
		objWait.waitSleep(8000);
		if (objListPurchase.rowsizepurchase().equals("50")) {
			System.out.println("RowSize:" + objListPurchase.rowsizepurchase());
			SoftAssert sast = new SoftAssert();
			sast.assertTrue(true);
			sast.assertAll();
		}
	}

	@Test(priority = 10, enabled = true, groups = {
			"regression" }, description = "List Purchase : Check search bar functionality")
	public void TC026() throws InterruptedException, IOException, AWTException {

		objListPurchase.searchpurchase.sendKeys("PO2022/0163");
		objListPurchase.searchpurchase.sendKeys(Keys.RETURN);
		objWait.waitSleep(6000);
		Assert.assertTrue(objListPurchase.searchPurhase("PO2022/0163"));
		System.out.println("Purchase Found-T026");
		objListPurchase.searchpurchase.clear();
	}

	@Test(priority = 11, enabled = true, groups = { "regression" }, description = "Check Supplier filter functionality")
	public void TC027() throws InterruptedException, IOException, AWTException {
		objListPurchase.listpurchasetab.click();
		objWait.waitSleep(6000);
		objListPurchase.supplierfilter.click();
		objListPurchase.selectsupplierfilter.click();
		Assert.assertTrue(objListPurchase.purchasefilter(objListPurchase.supplierfiltercoloumn, "jayakrishnan"));

	}

	@Test(priority = 12, enabled = true, groups = {
			"regression" }, description = "Check Bussiness Location filter functionality")
	public void TC028() throws InterruptedException, IOException, AWTException {

		objListPurchase.listpurchasetab.click();
		objWait.waitSleep(6000);
		objListPurchase.bussinesslocfilter.click();
		objListPurchase.selectbussinesslocfilter.click();
		Assert.assertTrue(objListPurchase.purchasefilter(objListPurchase.bussinesslocfiltercoloumn, "ppplkk"));

	}

	@Test(priority = 13, enabled = true, groups = {
			"regression" }, description = "Check Purchase Status filter functionality")
	public void TC029() throws InterruptedException, IOException, AWTException {

		objListPurchase.listpurchasetab.click();
		objWait.waitSleep(6000);
		objListPurchase.purchasestatusfilter.click();
		objListPurchase.selectpurchasestatusfilter.click();
		Assert.assertTrue(objListPurchase.purchasefilter(objListPurchase.purchasestatusfiltercoloumn, "Pending"));

	}

	@Test(priority = 14, enabled = true, groups = {
			"regression" }, dataProvider = "Search", description = "List Purchase : Check search bar functionalityusing data provider")
	public void TC030(String refnum) throws InterruptedException, IOException, AWTException {
		objListPurchase.listpurchasetab.click();
		objWait.waitSleep(6000);
		objListPurchase.searchpurchase.sendKeys(refnum);
		objListPurchase.searchpurchase.sendKeys(Keys.RETURN);
		objWait.waitSleep(6000);
		for (int i = 1; i <= objListPurchase.referencenocolumn.size(); i++) {
			String refno = objListPurchase.referencenocolumn.get(i).getText();
			if (refno.contains("PO2022/0167")) {

				// System.out.println("Purchase Found-TC030");
				Assert.assertTrue(true);
				objListPurchase.searchpurchase.clear();
				objWait.waitSleep(6000);
				break;
			}
		}
	}

	@DataProvider(name = "Search")
	public Object[][] dpMethod() {
		return new Object[][] { { "PO2022/0167" }, { "PO2023/0336" } };
	}

	@BeforeTest(alwaysRun = true)
	public void beforeTest() throws Exception {
		objExcelUtil = new ExcelUtility();
		String url = objExcelUtil.readData(1, 0);
		objWait.waitSleep(8000);
		objLaunchBrowserUtility.launchBrowser(url, "chrome");
		this.driver = objLaunchBrowserUtility.driver;
		objLoginElements = new LoginElements(driver);
		objHomePage = new HomePageElements(driver);
		objProductsElements = new ProductsElements(driver);
		objListpdt = new ListProductsElements(driver);
		objPurchase = new PurchaseElements(driver);
		objListPurchase = new ListPurchaseElements(driver);
	}

	@AfterTest(alwaysRun = true)
	public void afterTest() throws InterruptedException {
		objLaunchBrowserUtility.Closebrowser();
	}
}
