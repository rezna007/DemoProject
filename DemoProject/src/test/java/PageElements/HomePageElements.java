package PageElements;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import utility.WaitUtility;

public class HomePageElements {
	WebDriver driver;
	WaitUtility ObjWait;

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
		ProfileName.click();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		SignOut.click();
	}
	public void endtourclick() throws InterruptedException {
		EndTour.click();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}
	public boolean isHomePageLoaded() throws InterruptedException {
		
		return Homepage.isDisplayed();
		
	}
	
}
