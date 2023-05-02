package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	
	WebDriver driver;
	
	@FindBy(xpath="//a[contains(text(),'HP LP3065')]")
	private WebElement validHpProduct;
	
	@FindBy(xpath="//p[contains(text(),'There is no product that matches the search criter')]")
	private WebElement noProductMessage;
	
	public SearchPage (WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
}
	
	public boolean displayStatusOfValidProduct() {
		boolean displayStatus = validHpProduct.isDisplayed();
		return displayStatus;
	}
		public String retrieveNoProductMessageText() {
			String noProductMessageText = noProductMessage.getText();
			return noProductMessageText;
		}
		
	}
