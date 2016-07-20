package in.srssprojects.execution;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import in.srssprojects.admin.AdminHomePage;
import in.srssprojects.admin.BranchCreationPage;
import in.srssprojects.admin.BranchDetailsPage;
import in.srssprojects.admin.BranchUpdationPage;
import in.srssprojects.admin.EmployeeCreationPage;
import in.srssprojects.admin.EmployeeDetailsPage;
import in.srssprojects.admin.KeximHomePage;
import in.srssprojects.admin.RoleCreationPage;
import in.srssprojects.admin.RoleDetailsPage;
import in.srssprojects.admin.RolesUpdatePage;

import in.srssprojects.utils.Validations;

public class GridExecution {
	
	public WebDriver driver;
	public RemoteWebDriver obj;
	public DesiredCapabilities caps;
	public KeximHomePage keximHomePage;
	public AdminHomePage adminHomePage;
	public BranchDetailsPage branchDetailsPage;
	public BranchCreationPage branchCreationPage;
	public BranchUpdationPage branchUpdatePage;
	public EmployeeDetailsPage employeeDetailsPage;
	public EmployeeCreationPage employeeCreationPage;
	public RoleDetailsPage roleDetailsPage;
	public RoleCreationPage roleCreationPage;
	public RolesUpdatePage roleUpdationPage;

	public void gridLaunch(String nodeUrl, String b) {
		if (b.equalsIgnoreCase("firefox")) {
			caps = new DesiredCapabilities();
			caps = DesiredCapabilities.firefox();
			caps.setBrowserName("firefox");
		} else if (b.equalsIgnoreCase("chrome")) {
			caps = new DesiredCapabilities();
			caps = DesiredCapabilities.chrome();
			caps.setBrowserName("chrome");
		} else if (b.equalsIgnoreCase("safari")) {
			caps = new DesiredCapabilities();
			caps = DesiredCapabilities.safari();
			caps.setBrowserName("safari");
		}
		try {
			obj = new RemoteWebDriver(new URL(nodeUrl), caps);
			obj.get("http://gmail.com");
		} catch (Exception e) {

		}
	}

	// launch the application
	@BeforeTest()
	@Parameters({ "nodeURL", "br" })
	public void launchApp(String u, String b) {
		gridLaunch(u, b);
		obj.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		obj.manage().window().maximize();
		keximHomePage = new KeximHomePage(obj);
		adminHomePage = new AdminHomePage(obj);
		branchDetailsPage = new BranchDetailsPage(obj);
		branchCreationPage = new BranchCreationPage(obj);
		branchUpdatePage = new BranchUpdationPage(obj);
		employeeDetailsPage = new EmployeeDetailsPage(obj);
		roleDetailsPage = new RoleDetailsPage(obj);
		roleCreationPage = new RoleCreationPage(obj);
		roleUpdationPage = new RolesUpdatePage(obj);
	}

	// loginMethod
	@Test(priority = 0)
	public void loginAsAdmin() {
		System.out.println(Thread.currentThread().getId());
		keximHomePage.fillUserName("Admin");
		keximHomePage.fillPassword("Adminn");
		keximHomePage.clickLoginButton();
		obj.switchTo().alert().accept();
		boolean result = Validations.isURLContains(obj, "adminflow")
				&& Validations.isTextPresent(obj, "Welcome to Admin");
		Assert.assertTrue(result);
		Reporter.log("Login successfull");
	}

	
}
