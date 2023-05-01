package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountSuccessPage {
	
	WebDriver driver;
	
	@FindBy(xpath="//h1[contains(text(),'Your Account Has Been Created!')]")
	WebElement accountSuccesspageHeading;

	public AccountSuccessPage(WebDriver driver) {
		
		this.driver=driver;	
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	
	public String retrieveAccountSuccessPageHeading() {
		String accountSuccesspageHeadingText = accountSuccesspageHeading.getText();
		return accountSuccesspageHeadingText;
	}
			
	
}
