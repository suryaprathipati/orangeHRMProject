package in.srssprojects.execution;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import in.srssprojects.admin.KeximHomePage;

public class ParameterExecution {
	public WebDriver obj;
	public KeximHomePage keximHomePage;

	public void launch(String u, String b) {
		if (b.equalsIgnoreCase("chrome")) {
			//please provide the chrome driver executable file path 
			System.setProperty("webdriver.chrome.driver",
					"//Users//surya//Documents//Selenium//Selenium Softwares//chromedriver");
			obj = new ChromeDriver();
		} else if (b.equalsIgnoreCase("firefox")) {
			obj = new FirefoxDriver();
		} else if (b.equalsIgnoreCase("safari")) {
			obj = new SafariDriver();
		}
		obj.get(u);
	}

	// launch the application
	@BeforeTest()
	@Parameters({ "url", "br" })
	public void launchApp(String u, String b) {
		launch(u, b);
		obj.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		obj.manage().window().maximize();
		keximHomePage = new KeximHomePage(obj);
	}

	// loginMethod
	@Test(priority = 0)
	public void loginAsAdmin() {
		System.out.println(Thread.currentThread().getId());
		keximHomePage.fillUserName("Admin");
		keximHomePage.fillPassword("Admin");
		keximHomePage.clickLoginButton();
	}

}
