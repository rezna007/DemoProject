package PageElements;

import static org.testng.Assert.assertTrue;

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
import utility.PageUtilities;
import utility.WaitUtility;

public class ListProductsElements extends PageUtilities {
	WebDriver driver;
	ExcelUtility objExcelUtil;
	DropdownUtility objDropdown;
	WaitUtility objWait = new WaitUtility();
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
	@FindBy(xpath = "//*[@id=\"product_table\"]/thead/tr[i]/th[3]") // public WebElement productcolumn.Value of i
																	// changes in the for loop
	public List<WebElement> productcolumn;
	@FindBy(xpath = "/html/body/div[2]/div[1]/section[2]/div[2]/div/div/div/div[1]/div/div/table/tbody/tr/td")
	public WebElement nomatchfoundmsg;
	// check box of the item to be deleted here:01Apple
	@FindBy(xpath = "//div[@class='table-responsive']/div/table/tbody/tr[6]/td/input")
	public WebElement chkbox01Apple;
	@FindBy(xpath = "//div[@id=\"product_table_wrapper\"]/table/tbody/tr[6]/td[3]")
	public WebElement deleteitem01Apple;
	// *[@id=\"product_table\"]/tbody/tr[6]/td[3]
	@FindBy(xpath = "//*[@id=\"delete-selected\"]")
	public WebElement deleteselectedBtn;
	@FindBy(xpath = "//button[contains(@class,'swal-button swal-button--confirm')]")
	public WebElement okBtn;
	@FindBy(xpath = "//div[text()='Deleted Successfully']")
	public WebElement deletesuccessmsg;
	@FindBy(xpath = "//*[@id=\"product_table_length\"]/label/select")
	public WebElement showdropdown;
	@FindBy(xpath = "//*[@id=\"product_list_tab\"]/a")
	public WebElement Addbtn;
	@FindBy(xpath = "//*[@id=\"select2-product_list_filter_unit_id-container\"]")
	public WebElement unitfilter;
	@FindBy(xpath = "//ul[@id='select2-product_list_filter_unit_id-results']/li[6]")
	public WebElement unitfilterpairs;

	public void listpdtClick() throws InterruptedException {

		clickOnElement(listproductstab);
		objWait.waitSleep(6000);
	}

	public boolean searchProduct(String searchPdt) throws InterruptedException {
		for (int i = 0; i < productcolumn.size(); i++) {
			String pdtname = productcolumn.get(i).getText();
			if (searchPdt.equals(pdtname)) {
				System.out.println("Product Found");
				break;
			}
		}
		return true;
	}

	public boolean deleteitem() throws InterruptedException {
		clickOnElement(chkbox01Apple);
		clickOnElement(deleteselectedBtn);
		objWait.waitSleep(4000);
		clickOnElement(okBtn);
		objWait.waitSleep(4000);
		if(isElementDisplayed(deletesuccessmsg)) {
			System.out.println("Item deleted sucessfully");
		}
		return true;
	}

	public String rowsize() {
		List<WebElement> rows = driver.findElements(By.xpath("//*[@id=\"product_table\"]/tbody/tr/td[1]"));
		int rowsize = rows.size();
		return String.valueOf(rowsize);
	}

	public boolean listPdtFilter() throws InterruptedException {

		clickOnElement(unitfilter);
		clickOnElement(unitfilterpairs);// unit filter -pieces
		objWait.waitSleep(5000);
		List<WebElement> currentstock = driver.findElements(By.xpath("//*[@id=\"product_table\"]/thead/tr[i]/th[5]"));
		for (int i = 1; i <= currentstock.size(); i++) {
			String crnntstock = currentstock.get(i).getText();
			if (crnntstock.contains("Piece")) {
				System.out.println("Product page -Filter Functionality Working");
			}
		}
		return true;
	}
}
