package com.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.BaseClass;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTest extends BaseClass {
	public WebDriver driver;
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;

	public RegisterTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		registerPage = homePage.selectRegisterOption();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifyRegisteringAccountMandatoryFields() {
		
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		registerPage.enterTelephone(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterPasswordConfirm(prop.getProperty("validPassword"));
		registerPage.selectPrivacyPolicy();
		accountSuccessPage = registerPage.clickOnContinueButton();
		String actualSuccessHeading = accountSuccessPage.retrieveAccountSuccessPageHeading();
		Assert.assertEquals(actualSuccessHeading, dataProp.getProperty("accountSuccesfullyCreateHeading"),"Account not Created Successfully");
	}

	@Test(priority = 2)
	public void verifyRegisterAccountProvidingAllFields() {
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		registerPage.enterTelephone(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterPasswordConfirm(prop.getProperty("validPassword"));
		registerPage.selectYesNewsLetterOption();
		registerPage.selectPrivacyPolicy();
		accountSuccessPage = registerPage.clickOnContinueButton();
		String actualSuccessHeading = accountSuccessPage.retrieveAccountSuccessPageHeading();
		Assert.assertEquals(actualSuccessHeading,dataProp.getProperty("accountSuccesfullyCreateHeading"),"Account not Created Successfully");
	}

	@Test(priority = 3)
	public void verifyRegisterAccountWithExistingEmailAddress() {
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmailAddress(prop.getProperty("validEmail"));
		registerPage.enterTelephone(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterPasswordConfirm(prop.getProperty("validPassword"));
		registerPage.selectYesNewsLetterOption();
		registerPage.selectPrivacyPolicy();
		registerPage.clickOnContinueButton();
		
		String actualWarning = registerPage.retrieveDuplicateEmailAddressWarning();
		Assert.assertEquals(actualWarning, (dataProp.getProperty("duplicateEmailWarning")),"Warning message regarding duplicate email is not displayed");
	}

	@Test(priority = 4)
	public void registeringAccountWithoutFillingDetails() {
		registerPage.clickOnContinueButton();
		
		String actualPrivacyPolicyWarning =(registerPage.retrievePrivacyPolicyWarning());
		Assert.assertTrue(actualPrivacyPolicyWarning.contains(dataProp.getProperty(("privacyPolicyWarning"))),"Privacy Warning message is not displayed");
		
		String actualFirstNameWarning = registerPage.retrieveFirstNameWarningMessage();
		Assert.assertEquals(actualFirstNameWarning, dataProp.getProperty("firstNameWarning"),"First name warning mesage is not displayed");

		String actualLastNameWarning = registerPage.retrieveLastNameWarningMessage();
		Assert.assertEquals(actualLastNameWarning, dataProp.getProperty("lastNameWarning"),"First name warning mesage is not displayed");

		String actualEmailWarning = registerPage.retrieveEmailWarningMessage();
		Assert.assertEquals(actualEmailWarning, dataProp.getProperty("emailWarning"),"Email warning mesage is not displayed");

		String actualTelephoneWarning = registerPage.retrieveTelephoneWarningMessage();
		Assert.assertEquals(actualTelephoneWarning, dataProp.getProperty("telephoneWarning"),"Actual telephone warning mesage is not displayed");

		String actualPasswordWarning = registerPage.retrievePasswordWarningMessage();
		Assert.assertEquals(actualPasswordWarning, dataProp.getProperty("passwordWarning"),"Password warning mesage is not displayed");

	}
}
