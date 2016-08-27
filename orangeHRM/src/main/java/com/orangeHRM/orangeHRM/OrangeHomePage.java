package com.orangeHRM.orangeHRM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrangeHomePage {
	public WebDriver driver;
	
	//username
	@FindBy(id="txtUsername")
	public WebElement username;
	
	//password
	@FindBy(id="txtPassword")
	public WebElement password;
	
	//login button
	@FindBy(id="btnLogin")
	public WebElement loginButton;
	
	public OrangeHomePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//fill user name
	public void fillUserName(String uname){
		this.username.sendKeys(uname);
	}
	
	//fill password
	public void fillPassword(String pwd){
		this.password.sendKeys(pwd);
	}
	
	//click on login button
	public void clickLoginButton(){
		this.loginButton.click();
	}
	
	//login method
	public void login(){
		fillUserName("Admin");
		fillPassword("admin");
		clickLoginButton();
	}

}
