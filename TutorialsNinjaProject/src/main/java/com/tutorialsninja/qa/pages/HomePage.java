package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	
	//Objects
	@FindBy(xpath="//span[contains(text(),'My Account')]")
	private WebElement myAccountDropMenu;
	
	@FindBy(linkText="Login")
	private WebElement loginOption;
	
	@FindBy(linkText="Register")
	private WebElement registerOption;
	
	@FindBy(xpath="//header/div[1]/div[1]/div[2]/div[1]/input[1]")
	private WebElement searchBoxField;
	
	@FindBy(xpath="//header/div[1]/div[1]/div[2]/div[1]/span[1]/button[1]")
	private WebElement searchButton;
	
	public HomePage(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	
	public void clickOnMyAccount() {
		myAccountDropMenu.click();
	}
	public LoginPage selectLoginOption() {
		loginOption.click();
		return new LoginPage(driver);
	}
	
	public RegisterPage selectRegisterOption() {
		registerOption.click();
		return new RegisterPage(driver);
		
	}
		public void enterProductInSearch(String productText) {
			searchBoxField.sendKeys(productText);
		
	}
		public SearchPage clickOnSearchButton() {
			searchButton.click();
			return new SearchPage(driver);
			
		}

}
