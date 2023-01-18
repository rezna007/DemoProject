package PageElements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.testng.Assert.assertTrue;

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

import io.qameta.allure.Step;
import utility.ExcelUtility;
import utility.PageUtilities;
import utility.WaitUtility;

public class LoginElements extends PageUtilities{
	WebDriver driver;
	ExcelUtility objExcelUtil;
	HomePageElements objHomePageElement;
	WaitUtility objWait = new WaitUtility();

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

	@Step("Login with valid username and password step...")
	public void loginclick() throws InterruptedException, IOException {
		objExcelUtil = new ExcelUtility();
		String unameString = objExcelUtil.readData(1, 1);
		String pswdString = objExcelUtil.readData(1, 2);
		objWait.waitSleep(8000);
		sendKey(UsernameTxt, unameString);
		sendKey(PasswordTxt, pswdString);
		clickOnElement(RememberChkBox);
		clickOnElement(LoginBtn);
		//UsernameTxt.sendKeys(unameString);
		//PasswordTxt.sendKeys(pswdString);
//		RememberChkBox.click();
//		LoginBtn.click();
		objWait.waitSleep(2000);
	}

	@Step("Login with invalid username and password step...")
	public void invalidloginclick() throws InterruptedException, IOException {
		objExcelUtil = new ExcelUtility();
		String unameString = objExcelUtil.readData(2, 1);
		String pswdString = objExcelUtil.readData(2, 2);
		objWait.waitSleep(3000);
		sendKey(UsernameTxt, unameString);
		sendKey(PasswordTxt, pswdString);
		clickOnElement(RememberChkBox);
		clickOnElement(LoginBtn);
		objWait.waitSleep(2000);
	}

	@Step("Getting validation error message step...")
	public String getTextOfInvalidLoginError() throws InterruptedException {
		objWait.waitSleep(2000);
		return Invalidloginerror.getText();
	}

	public void forgotpwdclick() throws InterruptedException {
		clickOnElement(ForgotPwdLink);
		objWait.waitSleep(2000);
	}

	public void sendpwdreset() throws InterruptedException {
		
		clickOnElement(SendPwdReset);
		
	}

	public void rememberclick() throws InterruptedException {
		
		clickOnElement(RememberChkBox);
		objWait.waitSleep(2000);
	}

	public boolean chktootip() // forgot password -reset password
	{
		String tooltipText = EmailReset.getAttribute("title");// get tooltip text
		System.out.println(tooltipText);
		String tooltipmsgString = "Please include an '@' in the email address.";
		if (tooltipmsgString.contains(tooltipText)) {
			System.out.println("Tooltip text is as expected- Assert passed");
		}
		return true;
	}

	public boolean validateErrorMsg(String expected) throws InterruptedException {
		String actualMsgString = getTextOfInvalidLoginError();
		System.out.println(actualMsgString);
		System.out.println(expected);
		if (actualMsgString.equals(expected)) {

			System.out.println("Expected Invalid Login Error msg shown");
			return true;
		}
		
		return false;
	}

}