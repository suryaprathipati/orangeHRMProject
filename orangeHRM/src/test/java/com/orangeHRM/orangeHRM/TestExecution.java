package com.orangeHRM.orangeHRM;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TestExecution {
	public WebDriver driver;
	OrangeHomePage orangeHomePageObj;
	AdminHomePage adminHomePageObj;

	@Given("^user is in Orange HRM home page$")
	public void user_is_in_Orange_HRM_home_page() throws Throwable {
		driver = new FirefoxDriver();
		driver.get("http://opensource.demo.orangehrmlive.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		orangeHomePageObj = new OrangeHomePage(driver);
		adminHomePageObj = new AdminHomePage(driver);
	}

	@When("^user entered valid user name and valid password$")
	public void user_entered_valid_user_name_and_valid_password() throws Throwable {
		orangeHomePageObj.fillUserName("Admin");
		orangeHomePageObj.fillPassword("admin");
	}

	@When("^user click on login link$")
	public void user_click_on_login_link() throws Throwable {
		orangeHomePageObj.clickLoginButton();
	}

	@Then("^he can visit dashboard page$")
	public void he_can_visit_dashboard_page() throws Throwable {
		String actTitle = driver.getTitle();
		Assert.assertTrue(actTitle.contains("OrangeHRM"));
	}

	@Then("^he can visit welcome text$")
	public void he_can_visit_welcome_text() throws Throwable {
		String actText = driver.findElement(By.tagName("body")).getText();
		Assert.assertTrue(actText.contains("Welcome Admin"));
	}

	@Then("^user can do logout$")
	public void user_can_do_logout() throws Throwable {
//		adminHomePageObj.clickWelcomeLink();
//		adminHomePageObj.clickLogoutLink();
	}

	@Then("^Close the application$")
	public void close_the_application() throws Throwable {
		driver.close();
	}

}
