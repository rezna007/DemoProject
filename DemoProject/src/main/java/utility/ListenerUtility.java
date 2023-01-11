package utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.*;

import com.google.common.io.Files;


public class ListenerUtility {
	
	public WebDriver driver;
	LaunchBrowserUtility objLaunchBrowserUtility = new LaunchBrowserUtility();
	
	
	@AfterMethod(alwaysRun =true)
    public void tearDown(ITestResult iTestResult) throws IOException {
		this.driver=objLaunchBrowserUtility.driver;
        if (iTestResult.FAILURE == iTestResult.getStatus()) {
        	saveScreenshotPNG(iTestResult.getName());
        }
        //driver.quit();
    }
	
	public String saveScreenshotPNG(String name) throws IOException {
		this.driver=objLaunchBrowserUtility.driver;
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	    TakesScreenshot ts = (TakesScreenshot) this.driver;
	    File source = ts.getScreenshotAs(OutputType.FILE);
	    String destination = "C:\\Users\\pc\\git\\DemoProject\\DemoProject\\src\\test\\resources\\ScreenShot\\TC_"
			+name+ "_"+ dateName + ".png";
	    System.out.println(destination);
	    File finalDestination = new File(destination);
	    FileHandler.copy(source, finalDestination);
	    return destination;
	}
	
	 

}
