package com.orangeHRM.orangeHRM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddEmployeePage {
	public WebDriver driver;
	//first name
	@FindBy(id="firstName")
	public WebElement firstName;
	
	//last name
	@FindBy(id="lastName")
	public WebElement lastName;
	
	//save button
	@FindBy(id="btnSave")
	public WebElement saveButton;
	
	public AddEmployeePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//fill first name
	public void fillFirstName(String firstName){
		this.firstName.sendKeys(firstName);
	}
	
	//fill last name
	public void fillLastName(String lastName){
		this.lastName.sendKeys(lastName);
	}
	
	//click save button
	public void clickSaveButton(){
		this.saveButton.click();
	}
	
	//fill add employee form
	public void fillAddEmployeeForm(String firstName, String lastName){
		fillFirstName(firstName);
		fillLastName(lastName);
		clickSaveButton();
	}

}
