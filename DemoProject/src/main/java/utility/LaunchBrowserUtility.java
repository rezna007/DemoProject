package utility;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LaunchBrowserUtility {
	public WebDriver driver;

	public void launchBrowser(String url, String browser) throws Exception {

		if (browser.equalsIgnoreCase("chrome")) {
			System.out.println(" Executing on CHROME");
			System.setProperty("webdriver.chrome.driver", "E:\\Libraries\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.get(url);
		} else if (browser.equalsIgnoreCase("firefox")) {
			 System.out.println(" Executing on FIREFOX");
			System.setProperty("webdriver.gecko.driver", "E:\\Libraries\\geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.get(url);
			// driver.navigate().to("url");
		} 
		else if (browser.equalsIgnoreCase("Edge")) {
			 System.out.println(" Executing on EDGE");
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.get(url);
			
		} 
		
		else {
			throw new Exception("The Browser Type is Undefined");
		}
	}

	public void Closebrowser() throws InterruptedException {
		
		driver.close();
	}

}
