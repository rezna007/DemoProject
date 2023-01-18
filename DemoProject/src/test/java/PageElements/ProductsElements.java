package PageElements;

import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import utility.DropdownUtility;
import utility.ExcelUtility;
import utility.PageUtilities;
import utility.WaitUtility;

public class ProductsElements extends PageUtilities {
	WebDriver driver;
	ExcelUtility objExcelUtil;
	DropdownUtility objDropdown = new DropdownUtility();
	WaitUtility objWait = new WaitUtility();

	public ProductsElements(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[@id=\"tour_step5_menu\"]")
	public WebElement products;
	@FindBy(xpath = "//*[@id=\"product_add_form\"]/div[1]/div/div/div[1]/div/label")
	public WebElement productnamelabel;
	@FindBy(xpath = "//*[@id=\"tour_step5\"]/ul/li[2]/a")
	public WebElement addproducts;
	@FindBy(xpath = "//*[@id=\"name\"]")
	public WebElement productname;
	@FindBy(xpath = "//*[@id=\"name-error\"]")
	public WebElement productnameerror;
	@FindBy(id = "select2-brand_id-container")
	public WebElement brand;
	@FindBy(xpath = "//ul[@class='select2-results__options']/li[4]")
	public WebElement selectbrand;
	@FindBy(id = "select2-unit_id-container")
	public WebElement unit;
	@FindBy(xpath = "//ul[@class='select2-results__options']/li[3]")
	public WebElement selectunit;
	@FindBy(id = "select2-category_id-container")
	public WebElement category;
	@FindBy(xpath = "//ul[@class='select2-results__options']/li[3]")
	public WebElement selectcategory;
	@FindBy(id = "select2-sub_category_id-container")
	public WebElement subcategory;
	@FindBy(xpath = "//ul[@class='select2-results__options']/li[2]")
	public WebElement selectsubcategory;
	@FindBy(xpath = "//*[@id=\"alert_quantity\"]")
	public WebElement alertqnty;
	@FindBy(xpath = "//*[@id=\"alert_quantity-error\"]")
	public WebElement alertqntyerror;
	@FindBy(xpath = "//*[@id=\"upload_image\"]")
	public WebElement browse;
	@FindBy(xpath = "//*[@id=\"single_dpp\"]")
	public WebElement exctax0;
	@FindBy(xpath = "//*[@id=\"single_dpp-error\"]")
	public WebElement exctax0error;
	@FindBy(xpath = "//*[@id=\"single_dpp_inc_tax\"]")
	public WebElement inctax;
	@FindBy(xpath = "//*[@id=\"single_dsp\"]")
	public WebElement exctax1;
	@FindBy(xpath = "//*[@id=\"product_add_form\"]/div[4]/div/div/div/button[4]")
	public WebElement save;
	@FindBy(xpath = "//*[@id=\"product_add_form\"]/div[1]/div/div/div[2]/div/div/span[2]/button/i")
	public WebElement addbrand;
	@FindBy(xpath = "//input[@class='form-control' and @placeholder='Brand name']")
	public WebElement newbrand;
//	@FindBy(xpath ="//*[@id=\"description\"]")
//	public WebElement branddescription;
	@FindBy(xpath = "//button[@class='btn btn-primary' and @type='submit']")
	public WebElement savebrand;
	@FindBy(xpath = "//*[@id=\"product_add_form\"]/div[1]/div/div/div[3]/div/div/span[2]/button")
	public WebElement adduniticon;
	@FindBy(xpath = "//*[@id=\"actual_name\"]")
	public WebElement newunitname;
	@FindBy(xpath = " //*[@id=\"short_name\"]")
	public WebElement newunitshortname;
	@FindBy(xpath = "//*[@id=\"allow_decimal\"]")
	public WebElement allowDecimal;
	@FindBy(xpath = "//*[@id=\"quick_add_unit_form\"]/div[3]/button[1]")
	public WebElement saveunit;
	@FindBy(xpath = "//*[@id=\"toast-container\"]/div/div")
	public WebElement pdtsavemsg;

	public void addProduct(String name, String qnty, String tax)
			throws InterruptedException, IOException, AWTException {

		pdtName(name);
		clickOnElement(brand);
		clickOnElement(selectbrand);
		unitDetails();
		alertQnty(qnty);
		objWait.waitSleep(3000);
		sendKey(browse, "E:\\Upload.PNG");
		taxDetails(tax);
		save();
	}
	public void pdtClick() {
		clickOnElement(products);
		objWait.waitSleep(2000);
	}
	public void addpdtClick() {
		clickOnElement(addproducts);
	}
	public void pdtName(String name) throws InterruptedException {

		clickOnElement(productname);
		objWait.waitSleep(3000);
		sendKey(productname, name);
	}
	public void unitDetails() {

		clickOnElement(unit);
		clickOnElement(selectunit);
	}
	public void alertQnty(String qnty) {

		sendKey(alertqnty, qnty);
	}
	public void taxDetails(String tax) throws InterruptedException {
		sendKey(exctax0, tax);
		sendKey(inctax, tax);
		sendKey(exctax1, tax);
	}
	public void save() {
		clickOnElement(save);
	}
	public boolean validateErrorMsg(WebElement element) throws InterruptedException {

		if (getElementText(element).equals("This field is required.")) {

			System.out.println("Expected validation msg shown");
			return true;
		}
		return false;
	}
	public void addBrand(String Brandname, String Description) throws InterruptedException {
		clickOnElement(addbrand);
		clickOnElement(newbrand);
		objWait.waitSleep(4000);
		sendKey(newbrand, Brandname);
		objWait.waitSleep(3000);
		clickOnElement(savebrand);

	}

	public void addUnit(String Unitname, String Shortname) throws InterruptedException {

		clickOnElement(adduniticon);
		objWait.waitSleep(4000);
		clickOnElement(newunitname);
		objWait.waitSleep(4000);
		sendKey(newunitname, Unitname);
		clickOnElement(newunitshortname);
		objWait.waitSleep(4000);
		sendKey(newunitshortname, Shortname);
		objWait.waitSleep(4000);
		objDropdown.dropdownbytext(allowDecimal, "Yes");
		clickOnElement(saveunit);
	}

	public boolean loadNewPdtPage() {
		if (isElementDisplayed(productnamelabel)) {
			System.out.println("Displayed Add New Product Page");
			return true;
		}
		return false;
	}
}
