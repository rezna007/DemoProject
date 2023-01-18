package PageElements;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import utility.PageUtilities;
import utility.WaitUtility;

public class HomePageElements extends PageUtilities {
	WebDriver driver;
	LoginElements objLoginElements;
	WaitUtility objWait = new WaitUtility();

	public HomePageElements(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "/html/body/div[2]/header/a/span")
	public WebElement Homepage;
	@FindBy(xpath = "	/html/body/div[2]/header/nav/div/ul/li[2]/a/span")
	public WebElement ProfileName;
	@FindBy(xpath = "/html/body/div[2]/header/nav/div/ul/li[2]/ul/li[2]/div[2]/a")
	public WebElement SignOut;
	@FindBy(xpath = "//*[@id=\"step-0\"]/div[3]/button[3]")
	public WebElement EndTour;

	public void signout() throws InterruptedException {
		clickOnElement(ProfileName);		
		objWait.waitSleep(2000);		
		clickOnElement(SignOut);
	}

	public void endtourclick() throws InterruptedException {
		clickOnElement(EndTour);
		objWait.waitSleep(2000);
	}

	public boolean isHomePageLoaded() throws InterruptedException {

		objWait.waitForElementTobeVisible(driver, Homepage, 15);
		return isElementDisplayed(Homepage);
	

	}

}
