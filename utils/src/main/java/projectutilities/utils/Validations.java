package projectutilities.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Validations {

	// based on the text present in the page
	public static boolean isTextPresent(String expResult, WebDriver driver) {
		String actResult = driver.findElement(By.tagName("body")).getText();
		if (actResult.contains(expResult)) {
			return true;
		} else {
			return false;
		}
	}

	// Based on the title of the page
	public static boolean isTitlePresent(String expResult, WebDriver driver) {
		String actResult = driver.getTitle();
		if (actResult.contains(expResult)) {
			return true;
		} else {
			return false;
		}
	}

	// Based on the url of the page

	public static boolean isUrlContains(String expResult, WebDriver driver) {
		String actResult = driver.getCurrentUrl();
		if (actResult.contains(expResult)) {
			return true;
		} else {
			return false;
		}
	}

	// Based on the element of the page
	public static boolean isElementPresent(WebElement element, WebDriver driver) {
		try {
			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	
	//based on the element
	public static boolean isElementPresent(By locValue, WebDriver driver){
		try {
			return driver.findElement(locValue).isDisplayed();
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
}
