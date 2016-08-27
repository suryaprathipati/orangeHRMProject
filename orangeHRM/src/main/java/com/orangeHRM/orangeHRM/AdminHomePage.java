package com.orangeHRM.orangeHRM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AdminHomePage {
	public WebDriver driver;
	
	//Admin main menu link
	@FindBy(id="menu_admin_viewAdminModule")
	public WebElement adminLink;
	
	//PIM main menu link
	@FindBy(id="menu_pim_viewPimModule")
	public WebElement PIMLink;
	
	//add user button
	@FindBy(id="btnAdd")
	public WebElement addButton;
	
	//welcome link
	@FindBy(how = How.XPATH,  using="//a[@id='welcome']")
	public WebElement welcomeLink;
	
	@FindBy(how = How.LINK_TEXT, using="Logout")
	public WebElement logoutLink;
	
	public AdminHomePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//click on admin link
	public void clickAdminLink(){
		this.adminLink.click();
	}
	
	//click on PIM link
	public void PIMLink(){
		this.PIMLink.click();
	}
	
	//click on add button
	public void clickAddButton(){
		this.addButton.click();
	}
	
	//click on welcome link
	public void clickWelcomeLink(){
		this.welcomeLink.click();
	}
	
	//click on logout link
	public void clickLogoutLink(){
		this.logoutLink.click();
	}
	
	
}
