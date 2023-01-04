package PageElements;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utility.DropdownUtility;

public class ListPurchaseElements {
	WebDriver driver;
	ProductsElements objProductsElements;
	DropdownUtility objDropdown;


public ListPurchaseElements(WebDriver driver) 
{
	this.driver = driver;
	PageFactory.initElements(driver, this);
}

@FindBy(xpath = "//*[@id=\"tour_step6\"]/ul/li[1]/a")
public WebElement listpurchasetab;

@FindBy(xpath = "//div[@id='purchase_table_filter']/label/input")
public WebElement searchpurchase;
@FindBy(xpath= "//*[@id=\"purchase_table\"]/thead/tr[i]/th[2]")//Referenece column.Value of i changes in the for loop.
public List<WebElement> referencenocolumn;
@FindBy(xpath = "//*[@id=\"purchase_table_length\"]/label/select")
public WebElement showpurcchasedropdown;

@FindBy(xpath= "//*[@id=\"select2-purchase_list_filter_supplier_id-container\"]")
public WebElement supplierfilter;
@FindBy(xpath= " //ul[@class='select2-results__options']/li[2]")
public WebElement selectsupplierfilter;  //jayakrishnan - JK(CD)
@FindBy(xpath= "//*[@id=\"purchase_table\"]/thead/tr[i]/th[4] ")
public List <WebElement> supplierfiltercoloumn;

@FindBy(xpath= "//*[@id=\"select2-purchase_list_filter_location_id-container\"]")
public WebElement bussinesslocfilter;
@FindBy(xpath= "//ul[@class='select2-results__options']/li[9]")
public WebElement selectbussinesslocfilter;  //ppplkk (CA026359)
@FindBy(xpath= "//*[@id=\"purchase_table\"]/thead/tr[i]/th[3]")
public List <WebElement> bussinesslocfiltercoloumn;

@FindBy(xpath= "//*[@id=\"select2-purchase_list_filter_status-container\"]")
public WebElement purchasestatusfilter;
@FindBy(xpath= "  //ul[@class='select2-results__options']/li[3]")
public WebElement selectpurchasestatusfilter;  //Pending
@FindBy(xpath= "//*[@id=\"purchase_table\"]/thead/tr[i]/th[5]")
public List <WebElement> purchasestatusfiltercoloumn;

public String rowsizepurchase()
{
	List <WebElement> rows = driver.findElements(By.xpath("//*[@id=\"purchase_table\"]/tbody/tr/td[1]")); 
	int rowsize=rows.size();
	return String.valueOf(rowsize);
  
}
//public void listSupplierFilter() throws InterruptedException {
//	
//	supplierfilter.click();
//	selectsupplierfilter.click();
//	Thread.sleep(5000);
//	//List <WebElement> supplierfilter=driver.findElements(By.xpath("//*[@id=\"purchase_table\"]/thead/tr[i]/th[4]"));
//	for(int i =1;i<=supplierfiltercoloumn.size();i++ ) {
//		  String supplierString= supplierfiltercoloumn.get(i).getText();
//		  if(supplierString.contains("jayakrishnan")) {
//			  Assert.assertTrue(true);
//		  }
//	}		  
//}
public void purchasefilter(List<WebElement> element,String comparestring)
{
	
	for(int i =1;i<=((List<WebElement>) element).size();i++ ) {
		  String searchstring=((List<WebElement>) element).get(i).getText();
		  if(searchstring.contains(comparestring)) {
			  Assert.assertTrue(true);
		  }
	}		  
}
}


