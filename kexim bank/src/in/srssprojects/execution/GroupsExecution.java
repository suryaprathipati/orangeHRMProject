package in.srssprojects.execution;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
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
import in.srssprojects.utils.DataProviderClass;
import in.srssprojects.utils.Utility;

public class GroupsExecution {
	public WebDriver obj;
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
	
	// launch the application
	@BeforeTest(groups = { "branches", "roles", "creation", "positive", "negative" })
	public void launchApp(String u, String b) {
		obj = new FirefoxDriver();
		obj.get("http://www.srssprojects.in");
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
	@Test(priority = 0, alwaysRun = true, groups = { "branches", "roles", "creation", "positive", "negative" })
	public void loginAsAdmin() {
		keximHomePage.fillUserName("Admin");
		keximHomePage.fillPassword("Adminn");
		keximHomePage.clickLoginButton();
	}

	@Test(priority = 30, groups = { "branches" })
	public void verifyBranchesButton() {
		System.out.println("branches button clicked");
		adminHomePage.clickBranchesButton();
	}

	@Test(priority = 1, dependsOnMethods = { "verifyBranchesButton" }, groups = { "branches", "positive", "creation" })
	public void branchCreationWithVData() {
		
		adminHomePage.clickBranchesButton();
		branchDetailsPage.clickNewBranchButton();
		branchCreationPage.fillBranchCreationForm("ATCBatch3", "sr nagar", "89797", "INDIA", "GOA", "GOA");
		branchCreationPage.clickSubmitButton();
		String alertText = obj.switchTo().alert().getText();
		Reporter.log(alertText, true);
		obj.switchTo().alert().accept();
	}

	@Test(priority = 2, dependsOnMethods = { "verifyBranchesButton" }, groups = { "branches", "creation", "nagative" })
	public void branchCreationWithDData() {
		adminHomePage.clickBranchesButton();
		branchDetailsPage.clickNewBranchButton();
		branchCreationPage.fillBranchCreationForm("ATCBatch3", "sr nagar", "89797", "INDIA", "GOA", "GOA");
		branchCreationPage.clickSubmitButton();
		String alertText = obj.switchTo().alert().getText();
		Reporter.log(alertText, true);
		obj.switchTo().alert().accept();
	}

	@Test(priority = 3, groups = { "branches", "negative" })
	public void branchCreationWithBlankData() {
		adminHomePage.clickBranchesButton();
		branchDetailsPage.clickNewBranchButton();
		branchCreationPage.fillBranchCreationForm("", "", "", "Select", "Select", "Select");
		branchCreationPage.clickSubmitButton();
		String alertText = obj.switchTo().alert().getText();
		Reporter.log(alertText, true);
		obj.switchTo().alert().accept();
	}

	@Test(priority = 4, groups = { "branches", "creation" })
	public void branchCreationReset() {
		adminHomePage.clickBranchesButton();
		branchDetailsPage.clickNewBranchButton();
		branchCreationPage.fillBranchCreationForm("ATCBranch", "sr nagar", "89789", "INDIA", "GOA", "GOA");
		branchCreationPage.clickResetButton();
	}

	@Test(priority = 5, groups = { "branches" })
	public void branchCreationCancel() {
		adminHomePage.clickBranchesButton();
		branchDetailsPage.clickNewBranchButton();
		branchCreationPage.clickCancelButton();
	}

	@Test(priority = 6, groups = { "branches" })
	public void bracnhCreationCancelAfterFillingTheData() {
		adminHomePage.clickBranchesButton();
		branchDetailsPage.clickNewBranchButton();
		branchCreationPage.fillBranchCreationForm("ATCBranch", "sr nagar", "89789", "INDIA", "GOA", "GOA");
		branchCreationPage.clickCancelButton();
	}

	@Test(priority = 7, groups = { "branches" })
	public void branchUpdationWithVData() {
		adminHomePage.clickBranchesButton();
		Utility.webTable(obj, By.id("DG_bankdetails"), "223", "update");
		branchUpdatePage.Address1().clear();
		branchUpdatePage.Address1().sendKeys("ameerpet");
	}

	@Test(priority = 8, groups = { "branches" })
	public void branchDeletionWithDependency() {
		adminHomePage.clickBranchesButton();
		Utility.webTable(obj, By.id("DG_bankdetails"), "103", "delete");
		obj.switchTo().alert().accept();
		String alertText = obj.switchTo().alert().getText();
		System.out.println(alertText);
		obj.switchTo().alert().accept();
	}

	@Test(priority = 9, groups = { "branches" })
	public void branchDeletionWODependency() {
		adminHomePage.clickBranchesButton();
		Utility.webTable(obj, By.id("DG_bankdetails"), "225", "delete");
		obj.switchTo().alert().accept();
	}

	@Test(priority = 10, groups = { "roles", "creation", "positive" })
	public void roleCreationWithVData() {
		adminHomePage.clickRolesButton();
		roleDetailsPage.clickNewRoleButton();
		roleCreationPage.fillRoleCreationForm("AsstManager", "assistant Manager", "E");
		roleCreationPage.clickSubmitButton();
		String alertText = obj.switchTo().alert().getText();
		obj.switchTo().alert().accept();
		System.out.println(alertText);
	}

	@Test(priority = 11, groups = { "roles" })
	public void roleCreationWithDData() {
		roleCreationWithVData();
	}

	@Test(priority = 12, groups = { "roles" })
	public void roleCreationWithBlankData() {
		adminHomePage.clickRolesButton();
		roleDetailsPage.clickNewRoleButton();
		roleCreationPage.fillRoleCreationForm("", "", "Select");
		roleCreationPage.clickSubmitButton();
		String alertText = obj.switchTo().alert().getText();
		obj.switchTo().alert().accept();
		System.out.println(alertText);
	}

	@Test(priority = 13, groups = { "roles" })
	public void roleCreationReset() {
		adminHomePage.clickRolesButton();
		roleDetailsPage.clickNewRoleButton();
		roleCreationPage.fillRoleCreationForm("AsstManager", "assistant Manager", "E");
		roleCreationPage.clickResetButton();
	}

	@Test(priority = 14, groups = { "roles" })
	public void roleCreationCancel() {
		adminHomePage.clickRolesButton();
		roleDetailsPage.clickNewRoleButton();
		roleCreationPage.fillRoleCreationForm("AsstManager", "assistant Manager", "E");
		roleCreationPage.clickCancelButton();
	}

	@Test(priority = 15, groups = { "roles" })
	public void roleUpadationWithVD() {
		adminHomePage.clickRolesButton();
		Utility.webTable(obj, By.id("DGRoles"), "153", "update");
		roleUpdationPage.fillRoleUpdateForm("Manager", "Manager", "E");
		roleUpdationPage.clickRoleUpdate();
		String alertText = obj.switchTo().alert().getText();
		obj.switchTo().alert().accept();
		System.out.println(alertText);
	}

	@Test(priority = 16, groups = { "roles" })
	public void roleUpadationWithDuplicateData() {
		adminHomePage.clickRolesButton();
		Utility.webTable(obj, By.id("DGRoles"), "153", "update");
		roleUpdationPage.fillRoleUpdateForm("AsstManager", "assistant Manager", "E");
		roleUpdationPage.clickRoleUpdate();
		String alertText = obj.switchTo().alert().getText();
		obj.switchTo().alert().accept();
		System.out.println(alertText);
	}

	@Test(priority = 17, groups = { "roles" })
	public void roleUpdationCancel() {
		adminHomePage.clickRolesButton();
		Utility.webTable(obj, By.id("DGRoles"), "152", "update");
		roleUpdationPage.fillRoleUpdateForm("Manager", "Manager", "E");
		roleUpdationPage.clickRoleUpdate();
	}

	@Test(priority = 18, groups = { "employee" })
	public void employeeCreationWVD() {
		adminHomePage.clickEmployeeButton();
		employeeDetailsPage.clickNewEmployeeButton();
		employeeCreationPage.fillEmployeeCreationForm("surya", "selenium", "Asst Manager", "ATC Batch 3");
		employeeCreationPage.clicksubmit();
		String alertText = obj.switchTo().alert().getText();
		obj.switchTo().alert().accept();
		System.out.println(alertText);
	}

	@Test(priority = 19, groups = { "employee" })
	public void employeeCreationWDD() {
		employeeCreationWVD();
	}

	@Test(priority = 20, groups = { "employee" })
	public void employeeCreationWBlankData() {
		adminHomePage.clickEmployeeButton();
		employeeDetailsPage.clickNewEmployeeButton();
		employeeCreationPage.fillEmployeeCreationForm("", "", "Select", "Select");
		employeeCreationPage.clicksubmit();
		String alertText = obj.switchTo().alert().getText();
		obj.switchTo().alert().accept();
		System.out.println(alertText);
	}

	@Test(priority = 21, groups = { "employee" })
	public void employeeCreationReset() {
		adminHomePage.clickEmployeeButton();
		employeeDetailsPage.clickNewEmployeeButton();
		employeeCreationPage.fillEmployeeCreationForm("surya", "selenium", "Asst Manager", "ATC Batch 3");
		employeeCreationPage.clickreset();
	}

	@DataProvider(name = "branches")
	public Object[][] branchData() {
		String[][] data = DataProviderClass.getExcelContents("./input.xls", "branch data");
		return data;
	}

	@Test(priority = 22, dataProvider = "branches", enabled = false)
	public void branchCreationWithMultipleData(String bname, String add1, String zcode, String country, String state,
			String city) {
		adminHomePage.clickBranchesButton();
		branchDetailsPage.clickNewBranchButton();
		branchCreationPage.fillBranchCreationForm(bname, add1, zcode, country, state, city);
		branchCreationPage.clickSubmitButton();
		String alertText = obj.switchTo().alert().getText();
		Reporter.log(alertText, true);
		obj.switchTo().alert().accept();
	}

	// banker module
	@Test(priority = 23)
	public void loginASEmployee() {
		keximHomePage.selectBranchName("ATCBatch3");
		keximHomePage.fillUserName("surya");
		keximHomePage.fillPassword("selenium");
		keximHomePage.clickLoginButton();
	}

}
