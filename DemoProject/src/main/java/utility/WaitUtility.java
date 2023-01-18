package utility;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.beust.jcommander.JCommander.Builder;
import com.google.common.base.Function;

public class WaitUtility {
	WebDriver driver;

	public void implicitWait(WebDriver driver, long i) {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(i));
	}

	 public WebElement waitForElementTobeVisible(WebDriver driver, WebElement elementToBeLoaded, long i) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(i));
	        WebElement element = wait.until(ExpectedConditions.visibilityOf(elementToBeLoaded));
	        return element;
	    }

	    public WebElement waitForElemntTobeClickable(WebDriver driver, WebElement elementToBeLoaded, Duration Time) {
	        WebDriverWait wait = new WebDriverWait(driver, Time);
	        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(elementToBeLoaded));
	        return element;
	    }

	    public WebDriver waitForElementToLoad(WebDriver driver, WebElement element, String text) {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	        wait.until(ExpectedConditions.textToBePresentInElementValue(element, text));
	        return (driver);
	    }
	    

	    
	    public void fluentWait(WebDriver driver, WebElement element) {
			Wait<WebDriver> fwait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofMillis(5000))
					.pollingEvery(Duration.ofMillis(250)).ignoring(NoSuchElementException.class);
			fwait.until(ExpectedConditions.visibilityOf(element));
		}

	    public void waitSleep(long duration) {
	        try {
	            Thread.sleep(duration);
	        	
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }

//	    public void fluentWait(WebDriver driver,long i,long j)
//	    {
//	    	Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(i))
//					.pollingEvery(Duration.ofSeconds(j)).ignoring(NoSuchElementException.class);
//			WebElement ele= wait.until(new Function<WebDriver, WebElement>() {
//
//				public WebElement apply(WebDriver driver) {
//					//in this method defined our own subjected conditions for which we need to wait for  					
//					return driver.findElement(By.xpath(""));
//				}
//			});
//	    }	
}