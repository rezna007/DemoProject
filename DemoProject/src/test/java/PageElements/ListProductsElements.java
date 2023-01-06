package PageElements;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utility.DropdownUtility;
import utility.ExcelUtility;
import utility.WaitUtility;

public class ListProductsElements {
	WebDriver driver;
	ExcelUtility objExcelUtil;
	DropdownUtility objDropdown;
	WaitUtility objWait;
	ProductsElements objProductsElements;
	
	public ListProductsElements(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[@id=\"tour_step5\"]/ul/li[1]/a")
	public WebElement listproductstab;
	

	@FindBy(xpath = "//*[@id=\"product_table\"]")
	public WebElement listewebtable;
	
	@FindBy(xpath = "//*[@id=\"product_table_filter\"]/label/input")
	public WebElement searchproduct;

	@FindBy(xpath= "//*[@id=\"product_table\"]/thead/tr[i]/th[3]")//public WebElement productcolumn.Value of i changes in the for loop
	public List<WebElement> productcolumn;
	
	@FindBy(xpath= "/html/body/div[2]/div[1]/section[2]/div[2]/div/div/div/div[1]/div/div/table/tbody/tr/td")
	public WebElement nomatchfoundmsg;
	
	// check box of the item to be deleted here:01Apple
	@FindBy(xpath= "//div[@class='table-responsive']/div/table/tbody/tr[5]/td/input") 
	public WebElement chkbox01Apple;	
	@FindBy(xpath= "//*[@id=\"product_table\"]/tbody/tr[5]/td[3]")
	public WebElement deleteitem01Apple; 
	
	
	@FindBy(xpath= "//*[@id=\"delete-selected\"]")
	public WebElement deleteselectedBtn; 
	@FindBy(xpath= "//button[contains(@class,'swal-button swal-button--confirm')]")
	public WebElement okBtn;
	@FindBy(xpath= "//div[text()='Deleted Successfully']")
	public WebElement deletesuccessmsg;
	@FindBy(xpath= "//*[@id=\"product_table_length\"]/label/select")
	public WebElement showdropdown;
	
	@FindBy(xpath= "//*[@id=\"product_list_tab\"]/a")
	public WebElement Addbtn;
	
	
	@FindBy(xpath= "//*[@id=\"select2-product_list_filter_unit_id-container\"]")
	public WebElement unitfilter;
	@FindBy(xpath= "//ul[@id='select2-product_list_filter_unit_id-results']/li[6]")
	public WebElement unitfilterpairs;
	
	
	
	public void listpdtClick() throws InterruptedException {

		listproductstab.click();
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
	}

	public void searchProduct(String searchPdt) throws InterruptedException {
		
		for (int i = 0; i < productcolumn.size(); i++) {
			String pdtname = productcolumn.get(i).getText();
			if (searchPdt.equals(pdtname)) 
			{
				Assert.assertTrue(true);				
		 System.out.println("Product Found");
				break;
			}
	}		
	}
	public void deleteitem() throws InterruptedException
	{
		
		chkbox01Apple.click();
		deleteselectedBtn.click();
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		okBtn.click();
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

	}
	public String rowsize()
	{
		List <WebElement> rows = driver.findElements(By.xpath("//*[@id=\"product_table\"]/tbody/tr/td[1]")); 
		int rowsize=rows.size();
		return String.valueOf(rowsize);
      
	}
	public void listPdtFilter() throws InterruptedException {
		
		unitfilter.click();
		unitfilterpairs.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		List <WebElement> currentstock=driver.findElements(By.xpath("//*[@id=\"product_table\"]/thead/tr[i]/th[5]"));
		for(int i =1;i<=currentstock.size();i++ ) {
			 // WebElement unitvalue = driver.findElement(By.xpath("//table[@class='dataTable']/tbody/tr[\"+ i + \"]/td/a"));
			  String crnntstock= currentstock.get(i).getText();
			  if(crnntstock.contains("Piece")) {
				  Assert.assertTrue(true);
			  }
		}		  
	}
}








	