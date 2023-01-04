package PageElements;

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
import utility.ScrollUtility;
import utility.WaitUtility;

public class ProductsElements {
	WebDriver driver;
	ExcelUtility objExcelUtil;
	ScrollUtility objScrollUtility=new ScrollUtility();
	DropdownUtility objDropdown=new DropdownUtility();
	WaitUtility objWait;
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
	@FindBy(xpath ="//*[@id=\"alert_quantity-error\"]")
	public WebElement alertqntyerror;

	@FindBy(xpath = "//*[@id=\"upload_image\"]")
	public WebElement browse;

	@FindBy(xpath = "//*[@id=\"single_dpp\"]")
	public WebElement exctax0;	
	@FindBy(xpath ="//*[@id=\"single_dpp-error\"]")
	public WebElement exctax0error;
	@FindBy(xpath = "//*[@id=\"single_dpp_inc_tax\"]")
	public WebElement inctax;
	@FindBy(xpath = "//*[@id=\"single_dsp\"]")
	public WebElement exctax1;

	@FindBy(xpath = "//*[@id=\"product_add_form\"]/div[4]/div/div/div/button[4]")
	public WebElement save;
	
	@FindBy(xpath ="//*[@id=\"product_add_form\"]/div[1]/div/div/div[2]/div/div/span[2]/button/i")
	public WebElement addbrand;
	@FindBy(xpath ="//input[@class='form-control' and @placeholder='Brand name']")
	public WebElement newbrand;
//	@FindBy(xpath ="//*[@id=\"description\"]")
//	public WebElement branddescription;
	@FindBy(xpath ="//button[@class='btn btn-primary' and @type='submit']")
	public WebElement savebrand;
	
	@FindBy(xpath ="//*[@id=\"product_add_form\"]/div[1]/div/div/div[3]/div/div/span[2]/button")
	public WebElement adduniticon;	
	@FindBy(xpath ="//*[@id=\"actual_name\"]")
	public WebElement newunitname;	
    @FindBy(xpath =" //*[@id=\"short_name\"]")
	public WebElement  newunitshortname;  
	@FindBy(xpath ="//*[@id=\"allow_decimal\"]")
	public WebElement allowDecimal;	
	@FindBy(xpath ="//*[@id=\"quick_add_unit_form\"]/div[3]/button[1]")
	public WebElement saveunit;
	
	@FindBy(xpath ="//*[@id=\"toast-container\"]/div/div")
	public WebElement pdtsavemsg;



	public void addProduct(String name,String qnty,String tax) throws InterruptedException, IOException, AWTException {
		
		pdtName(name);		
		brand.click();
		selectbrand.click();
		unitDetails();		
//		category.click();
//		selectcategory.click();
//		subcategory.click();
//		selectsubcategory.click();
		alertQnty(qnty);
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		browse.sendKeys("E:\\Upload.PNG");
		taxDetails(tax);
		save();
		// browse.click();
		// uploadfile();	
	}

	public void pdtClick()
	{
		products.click();
	}
	public void addpdtClick()
	{
		
		addproducts.click();
	}
	public void pdtName(String name) throws InterruptedException
	{
		productname.click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		productname.sendKeys(name);
	}
	
	public void unitDetails()
	{
		unit.click();
		selectunit.click();
	}
	public void alertQnty(String qnty)
	{
		alertqnty.sendKeys(qnty);
	}
	public void taxDetails(String tax) throws InterruptedException
	{
		exctax0.sendKeys(tax);		
		inctax.sendKeys(tax);		
		exctax1.sendKeys(tax);
		
	}
	public void save()
	{
		save.click();
	}
	public void validateErrorMsg(WebElement element) throws InterruptedException
	{
		if (element.getText().equals("This field is required.")) {
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertTrue(true);
			softAssert.assertAll();
		} else {

			Assert.assertTrue(false);
		}
		
	}
	public void addBrand(String Brandname,String Description) throws InterruptedException
	{
		addbrand.click();
		newbrand.click();
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		newbrand.sendKeys(Brandname);
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		savebrand.click();
	}
	public void addUnit(String Unitname,String Shortname) throws InterruptedException
	{
		adduniticon.click();
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		newunitname.click();
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		newunitname.sendKeys(Unitname);
		newunitshortname.click();
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		newunitshortname.sendKeys(Shortname);
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		objDropdown.dropdownbytext(allowDecimal, "Yes");
		saveunit.click();
	}
	public void uploadfile() throws AWTException, InterruptedException
	{
		Robot rb = new Robot();
		 
	    // copying File path to Clipboard
		 StringSelection str = new StringSelection("E:\\Upload.PNG");
		    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
		 
		     // press Contol+V for pasting
		     rb.keyPress(KeyEvent.VK_CONTROL);
		     rb.keyPress(KeyEvent.VK_V);
		     driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		 
		    // release Contol+V for pasting
		    rb.keyRelease(KeyEvent.VK_CONTROL);
		    rb.keyRelease(KeyEvent.VK_V);
		    driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		 
		    // for pressing and releasing Enter
		    rb.keyPress(KeyEvent.VK_ENTER);
		    rb.keyRelease(KeyEvent.VK_ENTER);
		    driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
	   
	   }
	
	
	}


