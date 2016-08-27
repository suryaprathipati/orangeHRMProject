package com.orangeHRM.orangeHRM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PIMPage {
	public WebDriver driver;
	
	//add employee submenu link
	@FindBy(id="menu_pim_addEmployee")
	public WebElement addEmployeeLink;
	
	public PIMPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//click add employee link
	public void clickAddEmployeeLink(){
		this.addEmployeeLink.click();
	}
}
