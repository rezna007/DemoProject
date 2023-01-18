package utility;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
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

import com.github.dockerjava.api.command.PullImageCmd;
import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LaunchBrowserUtility  {
	public static WebDriver driver;
	WaitUtility objWait=new WaitUtility();
	public static Properties prop = null;

	public void launchBrowser(String url, String browser) throws Exception {

		if (browser.equalsIgnoreCase("chrome")) {
			System.out.println(" Executing on CHROME");
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();	
						
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.out.println(" Executing on FIREFOX");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();					
		} 
		else if (browser.equalsIgnoreCase("Edge")) {
			System.out.println(" Executing on EDGE");
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();						
		} 		
		else {
			throw new Exception("The Browser Type is Undefined");
		}
		driver.manage().window().maximize();
		objWait.implicitWait(driver, 10);
		driver.get(url);
	}

	public void Closebrowser() throws InterruptedException {
		
		driver.close();
	}
	public static void configProp() {
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources"
                    + "/config.properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
