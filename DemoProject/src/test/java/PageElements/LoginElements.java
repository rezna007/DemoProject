package PageElements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utility.ExcelUtility;
import utility.WaitUtility;

public class LoginElements {
	WebDriver driver;
	ExcelUtility objExcelUtil;

	public LoginElements(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[@id=\"username\"]")
	public WebElement UsernameTxt;

	@FindBy(xpath = "//*[@id=\"password\"]")
	public WebElement PasswordTxt;

	@FindBy(xpath = "/html/body/div[3]/div/div/div/div[2]/form/div[4]/div/button")
	public WebElement LoginBtn;

	@FindBy(xpath = "/html/body/div[3]/div/div/div/div[2]/form/div[3]/div/div/label/input")
	public WebElement RememberChkBox;

	@FindBy(xpath = "/html/body/div[3]/div/div/div/div[2]/form/div[4]/div/a")
	public WebElement ForgotPwdLink;

	@FindBy(xpath = "/html/body/div[3]/div/div/div/div[2]/form/div[1]/div/span/strong")
	public WebElement Invalidloginerror;

	@FindBy(xpath = "//*[@id=\"email\"]")
	public WebElement EmailReset;

	@FindBy(xpath = "	/html/body/div[3]/div/div/div/div[2]/form/div[2]/div/button")
	public WebElement SendPwdReset;

	@FindBy(xpath = "/html/body/div[3]/div/div/div/div[2]/form/div[1]/div/span/strong")
	public WebElement InvalidUserEmail;

	public void loginclick() throws InterruptedException, IOException {
		objExcelUtil = new ExcelUtility();
		String unameString = objExcelUtil.readData(1, 1);
		String pswdString = objExcelUtil.readData(1, 2);

//		String unameString = "admin";
//		String pswdString = "123456";	
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		UsernameTxt.sendKeys(unameString);

		PasswordTxt.sendKeys(pswdString);

		RememberChkBox.click();
		LoginBtn.click();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

	}

	public void invalidloginclick() throws InterruptedException, IOException {
		objExcelUtil = new ExcelUtility();
		String unameString = objExcelUtil.readData(2, 1);
		String pswdString = objExcelUtil.readData(2, 2);
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		UsernameTxt.sendKeys(unameString);		
		PasswordTxt.sendKeys(pswdString);
		RememberChkBox.click();
		LoginBtn.click();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

	}

	public String getTextOfInvalidLoginError() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		return Invalidloginerror.getText();
	}

	public void forgotpwdclick() throws InterruptedException {
		ForgotPwdLink.click();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	public void sendpwdreset() throws InterruptedException {
		SendPwdReset.click();

	}

	public void rememberclick() throws InterruptedException {
		RememberChkBox.click();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

}