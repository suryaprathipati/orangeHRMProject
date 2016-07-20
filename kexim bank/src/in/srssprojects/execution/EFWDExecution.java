package in.srssprojects.execution;


import java.util.concurrent.TimeUnit;


import org.openqa.selenium.WebDriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import in.srssprojects.admin.AdminHomePage;
import in.srssprojects.admin.KeximHomePage;
import in.srssprojects.utils.EventListener;

public class EFWDExecution {
	/**
	 * TO WORK WITH EVENTFIRING WEBDRIVER 1. create a class EventListener which
	 * will implement WebDriverEventListener interface 2. In the execution class
	 * like this create an object for EventFiringWebDriver class and our
	 * EventListerner class. 3. Register our listener class object to event
	 * firing web driver class object.
	 * 
	 * Remember to verify emailable report file to check the log file
	 */
	public WebDriver driver;
	public EventFiringWebDriver obj;
	public EventListener eListener;
	public KeximHomePage keximHomePage;
	public AdminHomePage adminHomePage;

	// launch the application
	@BeforeTest()
	public void launchApp(String u, String b) {
		driver = new FirefoxDriver();
		obj = new EventFiringWebDriver(driver);
		eListener = new EventListener();
		obj.register(eListener);
		obj.get("http://www.srssprojects.in");
		obj.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		obj.manage().window().maximize();
		keximHomePage = new KeximHomePage(obj);
		adminHomePage = new AdminHomePage(obj);

	}

	// loginMethod
	@Test(priority = 0)
	public void loginAsAdmin() {
		keximHomePage.fillUserName("Admin");
		keximHomePage.fillPassword("Adminn");
		keximHomePage.clickLoginButton();
	}

}
