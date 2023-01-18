package PageElements;

import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import utility.DropdownUtility;
import utility.PageUtilities;
import utility.WaitUtility;

public class PurchaseElements extends PageUtilities {
	WebDriver driver;
	WaitUtility objWait= new WaitUtility();
	
public PurchaseElements(WebDriver driver) 
{
	this.driver = driver;
	PageFactory.initElements(driver, this);
}

@FindBy(xpath = "//*[@id=\"tour_step6_menu\"]")
public WebElement purchase;
@FindBy(xpath = "//*[@id=\"tour_step6\"]/ul/li[2]/a")
public WebElement addpurchase;

@FindBy(xpath = "//div[@class='input-group']/span[2]")
public WebElement supplier;
@FindBy(xpath = "//input[@type='search']")
public WebElement suppliersearch;
@FindBy(xpath = "//ul[@class='select2-results__options']/li[1]")  //veena - newcart (CO0062) 
public WebElement selectsupplier; 
@FindBy(xpath = " //div[@class='input-group']/span[2]/span[1]/span")  //Please select
public WebElement selectsupplier1; 
@FindBy(xpath = "//*[@id=\"supplier_id-error\"]")
public WebElement selectsuppliererror;

@FindBy(id = "select2-status-container")
public WebElement purchasestatus;
@FindBy(xpath= " //ul[@class='select2-results__options']/li[2]")
public WebElement selectpurchsestatus; //received
@FindBy(xpath= " //ul[@class='select2-results__options']/li[1]")
public WebElement selectpurchsestatus1; //please select
@FindBy(xpath = "//*[@id=\"status-error\"]")
public WebElement purchasestatuserror;

@FindBy(xpath= "//*[@id=\"select2-location_id-container\"]")
public WebElement bussinesslocation;
@FindBy(xpath= "//span[@class='select2-search select2-search--dropdown']//input[1]")
public WebElement bussinesslocationsearch;
@FindBy(xpath= "//ul[@class='select2-results__options']/li[2]")
public WebElement selectbussinesslocation; //Demo Company (BL0001)
@FindBy(xpath= "//ul[@class='select2-results__options']/li[1]")
public WebElement selectbussinesslocation1; //please select
@FindBy(xpath= "//*[@id=\"location_id-error\"]")
public WebElement bussinesslocationerror;

@FindBy(xpath= "//*[@id=\"add_purchase_form\"]/div[2]/div/div[1]/div[2]/div/button")
public WebElement addnewpdtbtn;
@FindBy(xpath = "//*[@id=\"name\"]")
public WebElement productname;
@FindBy(id= "select2-unit_id-container")
public WebElement unit;
@FindBy(xpath= "//ul[@class='select2-results__options']/li[6]")
public WebElement selectunit;
@FindBy(xpath = "//*[@id=\"alert_quantity\"]")
public WebElement alertqnty;
@FindBy(xpath = "//*[@id=\"upload_image\"]")
public WebElement browse;
@FindBy(xpath = "//*[@id=\"single_dpp\"]")
public WebElement exctax0;
@FindBy(xpath = "//*[@id=\"single_dpp_inc_tax\"]")
public WebElement inctax;
@FindBy(xpath = "//*[@id=\"single_dsp\"]")
public WebElement exctax1;
@FindBy(xpath = "//*[@id=\"submit_quick_product\"]")
public WebElement save;
@FindBy(xpath = "//*[@id=\"quick_add_product_form\"]/div[3]/button[2]")
public WebElement close;
@FindBy(xpath ="//*[@id=\"toast-container\"]/div/div")
public WebElement pdtsavemsg;

@FindBy(xpath= "//div[@class='box-body payment_row']/div/div/div[1]/div/input")
public WebElement amount;
@FindBy(xpath= "//*[@id=\"submit_purchase_form\"]")
public WebElement savePurchase;
@FindBy(xpath= "//div[@class='toast toast-success']//div[1]")
public WebElement savePurchasemsg;

public void purchaseClick()
{
	clickOnElement(purchase);	
}
public void addpurchaseClick()
{
	clickOnElement(addpurchase);	
}
public void addProduct(String name,String qnty,String tax) throws InterruptedException, IOException, AWTException {	

	sendKey(productname,name);	
	objWait.waitSleep(2000);
	unitDetails();	
	objWait.waitSleep(6000);
	alertQnty(qnty);
	objWait.waitSleep(3000);
	taxDetails(tax);
	save();
	objWait.waitSleep(6000);
}
public void addPurchase() throws InterruptedException
{ 
	selectSupplier(); 
	selectPurchasestatus() ;
	selectBussinesslocation();
	sendKey(amount,"250");
	clickOnElement(savePurchase);
}
public void addPurchaseWithoutSupplier() throws InterruptedException
{ 
	selectPurchasestatus() ;
	selectBussinesslocation();
	sendKey(amount,"158");
	clickOnElement(savePurchase);
	objWait.waitSleep(2000);
}

public void addPurchaseWithoutPurchasestatus() throws InterruptedException
{ 	
	selectSupplier() ;
	clickOnElement(purchasestatus);
	objWait.waitSleep(3000);
	clickOnElement(selectpurchsestatus1);
	selectBussinesslocation();
	sendKey(amount,"458");
	clickOnElement(savePurchase);
	objWait.waitSleep(3000);
}
public void addPurchaseWithoutPBussinesslocation() throws InterruptedException
{ 
	selectSupplier() ;
	selectPurchasestatus() ;
	clickOnElement(bussinesslocation);
	clickOnElement(bussinesslocationsearch);
	objWait.waitSleep(3000);
	clickOnElement(selectbussinesslocation1);
	objWait.waitSleep(3000);
	sendKey(amount,"236");
	clickOnElement(savePurchase);
	objWait.waitSleep(2000);
}
public void selectSupplier() throws InterruptedException
{
clickOnElement(supplier);
objWait.waitSleep(2000);
clickOnElement(suppliersearch);
sendKey(suppliersearch,"V");
objWait.waitSleep(6000);
clickOnElement(selectsupplier);
objWait.waitSleep(3000);
}
public void selectPurchasestatus() throws InterruptedException
{
	clickOnElement(purchasestatus);
	objWait.waitSleep(3000);
	clickOnElement(selectpurchsestatus);	
}
public void selectBussinesslocation() throws InterruptedException
{
	clickOnElement(bussinesslocation);
	clickOnElement(bussinesslocationsearch);
	objWait.waitSleep(3000);
	clickOnElement(selectbussinesslocation);
	objWait.waitSleep(3000);
}
public void unitDetails()
{
	clickOnElement(unit);
	clickOnElement(selectunit);
}
public void alertQnty(String qnty)
{ 
	sendKey(alertqnty,qnty);
	
}
public void taxDetails(String tax) throws InterruptedException
{	
	sendKey(exctax0,tax);
	sendKey(inctax,tax);
	sendKey(exctax1,tax);	
}
public void save()
{
	clickOnElement(save);	
}
public boolean validateErrorMsg(WebElement element) throws InterruptedException
{
	if (getElementText(element).equals("This field is required.")) {
		
		System.out.println("Manadatory field validation message shown");
		return true;
	} 
	return false;
}

}