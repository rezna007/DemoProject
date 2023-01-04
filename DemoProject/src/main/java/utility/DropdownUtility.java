package utility;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropdownUtility {
	public Select objselect;

	public void dropdownbytext(WebElement dropobj, String value) throws InterruptedException {
		objselect = new Select(dropobj);
		objselect.selectByVisibleText(value);

	}

	public void dropdownbyvalue(WebElement dropobj, String value) throws InterruptedException {
		objselect = new Select(dropobj);
		objselect.selectByValue(value);

	}

	public void dropdownbyindex(WebElement dropobj, int index) throws InterruptedException {
		objselect = new Select(dropobj);
		objselect.selectByIndex(index);
	}

	public void deselectByValue(WebElement dropobj, String value) throws InterruptedException {
		objselect.deselectByValue(value);
	}

	public void deselectAll(WebElement dropobj) throws InterruptedException {
		objselect.deselectAll();
	}
}
