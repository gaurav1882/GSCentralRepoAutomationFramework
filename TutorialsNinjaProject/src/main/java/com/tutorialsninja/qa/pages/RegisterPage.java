package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	WebDriver driver;
	
	@FindBy(xpath="//input[@id='input-firstname']")
	private WebElement firstNameField;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	private WebElement lastNameField;
	
	@FindBy(xpath="//input[@id='input-email']")
	private WebElement emailField;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	private WebElement telephoneField;
	
	@FindBy(xpath="//input[@id='input-password']")
	private WebElement passwordField;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	private WebElement passwordConfirmField;
	
	@FindBy(name="agree")
	private WebElement privacyPolicyField;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueButton;
	
	@FindBy(xpath="//body/div[@id='account-register']/div[1]/div[1]/form[1]/fieldset[3]/div[1]/div[1]/label[1]/input[1]")
	private WebElement yesNewsLetterOption;
	
	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement duplicateEmailAddressWarning;
	
	
	
	@FindBy(xpath = "//body/div[@id='account-register']/div[1]")
	private WebElement privacyPolicyWarning;
	
	@FindBy(xpath="//div[contains(text(),'First Name must be between 1 and 32 characters!')]")
	private WebElement firstNameWarningMessage;
	
	@FindBy(xpath="//div[contains(text(),'Last Name must be between 1 and 32 characters!')]")
	private WebElement lastNameWarningMessage;
	
	@FindBy(xpath="//div[contains(text(),'E-Mail Address does not appear to be valid!')]")
	private WebElement emailWarningMessage;
	
	@FindBy(xpath="//div[contains(text(),'Telephone must be between 3 and 32 characters!')]")
	private WebElement telephoneWarningMessage;
	
	@FindBy(xpath="//div[contains(text(),'Password must be between 4 and 20 characters!')]")
	private WebElement passwordWarningMessage;
	
	
	
		public RegisterPage(WebDriver driver) {
		this.driver=driver;	
		PageFactory.initElements(driver, this);
	}	
		public void enterFirstName(String firstNameText) {
			firstNameField.sendKeys(firstNameText);
		}
		
		public void enterLastName(String lastNameText) {
			lastNameField.sendKeys(lastNameText);
		}
			public void enterEmailAddress(String emailText) {
			emailField.sendKeys(emailText);
		}
		public void enterTelephone(String telephoneText) {
			telephoneField.sendKeys(telephoneText);
		}
		public void enterPassword(String passwordText) {
			passwordField.sendKeys(passwordText);
		}
		public void enterPasswordConfirm(String passwordConfirmText) {
			passwordConfirmField.sendKeys(passwordConfirmText);
		}
		
		public void selectYesNewsLetterOption() {
			yesNewsLetterOption.click();
		}
		
		public void selectPrivacyPolicy() {
			privacyPolicyField.click();
		}
		public AccountSuccessPage clickOnContinueButton() {
			continueButton.click();
			return new AccountSuccessPage(driver);
		}
		
		public String retrievePrivacyPolicyWarning() {
			String privacyPolicyWarningText =  privacyPolicyWarning.getText();
			return privacyPolicyWarningText;
		}
		
		public String retrieveFirstNameWarningMessage() {
			String firstNameWarningMessageText = firstNameWarningMessage.getText();
			return firstNameWarningMessageText;
		}
			
			public String retrieveLastNameWarningMessage() {
				String lastNameWarningMessageText = lastNameWarningMessage.getText();
				return lastNameWarningMessageText;
			}
				
			public String retrieveEmailWarningMessage() {
				String emailWarningMessageText = emailWarningMessage.getText();
				return emailWarningMessageText;
			}
				
			public String retrieveTelephoneWarningMessage() {
				String telephoneWarningMessageText = telephoneWarningMessage.getText();
				return telephoneWarningMessageText;
			}
					
				public String retrievePasswordWarningMessage() {
					String passwordWarningMessageText = passwordWarningMessage.getText();
					return passwordWarningMessageText;
				
		}
		
		public String retrieveDuplicateEmailAddressWarning() {
			String duplicateEmailAddressWarningText = duplicateEmailAddressWarning.getText();
			return duplicateEmailAddressWarningText;
			
		}
}




















