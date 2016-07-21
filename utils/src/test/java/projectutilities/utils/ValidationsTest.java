package projectutilities.utils;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ValidationsTest {
	public WebDriver driver;

	@BeforeTest
	public void launchApp() {
		driver = new FirefoxDriver();
		driver.get("http://www.srssprojects.in");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void verify() {
		boolean result = Validations.isTitlePresent("KEXIM BANK", driver)
				&& Validations.isTextPresent("Welcome to KEXIM Bank", driver);
		Assert.assertTrue(result, "verifying launch funcitonality:  ");
		Reporter.log("verify fuctionality passed....", true);
	}

	public void select(String option, WebElement element) {
		Select s = new Select(element);
		String o = null;
		boolean flag = false;
		ArrayList<WebElement> alist = new ArrayList<WebElement>(s.getOptions());
		for (WebElement a : alist) {
			if (a.getText().equalsIgnoreCase(option)) {
				o = a.getText();
				flag = true;
				break;
			}
		}
		if (flag == true)
			s.selectByValue(o);
		else
			System.out.println("no such optinon");
	}

	@Test(priority = 1)
	public void login() {
		// System.out.println(driver.findElement(By.xpath("//select[@id='drlist']")).getText());
		select("adcb", driver.findElement(By.id("drlist")));
		driver.findElement(By.id("txtuId")).sendKeys("Admin");
	}
}
