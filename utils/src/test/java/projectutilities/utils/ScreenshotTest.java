package projectutilities.utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ScreenshotTest {
	public WebDriver driver;
	public static int h,w;
	public String folderPath = "//Users//surya//Documents//";
	@BeforeTest
	public void launch(){
		driver = new FirefoxDriver();
		driver.get("http://www.srssprojects.in");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		h = driver.manage().window().getSize().getHeight();
		w = driver.manage().window().getSize().getWidth();
	}
	
	@Test
	public void login() throws InterruptedException{
		driver.findElement(By.id("txtuId")).sendKeys("Admin");
		driver.findElement(By.id("txtPword")).sendKeys("Admi");
		driver.findElement(By.id("login")).click();
		Thread.sleep(3000);
		Screenshots.alertScreenCapture(folderPath+"login",h,w);
	}

}
