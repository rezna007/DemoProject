package utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ScrollUtility {
	WebDriver driver;
	
	public void scrollToElement(WebElement element) throws InterruptedException {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	public void scrollpixel(int a, int b, WebDriver driver) throws InterruptedException {

		((JavascriptExecutor) driver).executeScript("javascript:window.scrollBy(" + a + "," + b + ")");
	}
	
	public void scrollToTop(WebDriver driver) throws InterruptedException {

		((JavascriptExecutor) driver).executeScript("window.scrollTo(document.body.scrollHeight, 0)");
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	}
}
