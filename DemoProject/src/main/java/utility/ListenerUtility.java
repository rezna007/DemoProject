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

import com.google.common.io.Files;

public class ListenerUtility {
	public WebDriver driver;
	
	
	@AfterMethod
    public void tearDown(ITestResult iTestResult) throws IOException {
        if (iTestResult.FAILURE == iTestResult.getStatus()) {
        	takescreenshot(iTestResult.getName());
        }
        driver.quit();
    }
	public String takescreenshot(String name) throws IOException {

		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	    TakesScreenshot ts = (TakesScreenshot) this.driver;
	    File source = ts.getScreenshotAs(OutputType.FILE);
	    String destination = "E:\\EclipseWS_Automation\\SeleniumAutomation\\src\\test\\resources\\screenshots"
			+name+ dateName + ".png";
	    System.out.println(destination);
	    File finalDestination = new File(destination);
	    FileHandler.copy(source, finalDestination);
	    return destination;
	}
	public void screenshots() throws IOException {

		Date d = new Date();
		// System.out.println(d.toString());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		String date = sdf.format(d);
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File path = new File("E:\\EclipseWS_Automation\\SeleniumAutomation\\src\\test\\resources\\screenshots\\testname"
				+ date + ".png");
		Files.copy(screenshotFile, path);
	}

}
