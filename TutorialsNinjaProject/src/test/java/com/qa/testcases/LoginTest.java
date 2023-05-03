package com.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.BaseClass;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;

public class LoginTest extends BaseClass {
	public LoginTest() {
		super();
	}

	public WebDriver driver;
	LoginPage loginPage;

	@BeforeMethod
	public void setUp() {
		// c; we can call this method as well or call the Super class constructor which
		// is BaseClass() in Base class
		// loadPropertiesFile();
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		loginPage = homePage.selectLoginOption();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1, dataProvider = "ValidCredentialSupplier")
	public void verifyLoginValidCredentails(String email, String password) {

		loginPage.enterEmailAddress(email);
		loginPage.enterPassword(password);
		AccountPage accountPage = loginPage.clickOnLoginButton();
		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInfo());
	}

	@DataProvider(name = "ValidCredentialSupplier")
	public Object[][] supplyTestData() {
		Object[][] data = Utilities.getTestdDataFromExcel("Login");
		return data;
	}

	@Test(priority = 2)
	public void verifyLoginInvalidCredentails() {

		loginPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginPage.clickOnLoginButton();

		String actualWarningMessage = loginPage.retrieveEmailpasswordNotMatchingWarningMessageText();
		System.out.println(actualWarningMessage + "***********");
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected message is not displayed");
	}

	@Test(priority = 3)
	public void verifyLoginInvalidEmailAndValidPassword() {
		loginPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		loginPage.enterPassword(prop.getProperty("validPassword"));
		loginPage.clickOnLoginButton();

		String actualWarningMessage = loginPage.retrieveEmailpasswordNotMatchingWarningMessageText();
		System.out.println(actualWarningMessage + "***********");
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected message is not displayed");
	}

	@Test(priority = 4)
	public void verifyLoginValidEmailAndInvalidPassword() {
		loginPage.enterEmailAddress(prop.getProperty("validEmail"));
		loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginPage.clickOnLoginButton();
		String actualWarningMessage = loginPage.retrieveEmailpasswordNotMatchingWarningMessageText();
		System.out.println(actualWarningMessage + "***********");
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected message is not displayed");
	}

	@Test(priority = 5)
	public void verifyWithoutLoginCredentials() {

		loginPage.clickOnLoginButton();
		String actualWarningMessage = loginPage.retrieveEmailpasswordNotMatchingWarningMessageText();
		System.out.println(actualWarningMessage + "***********");
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage), "Expected message is not displayed");
	}

}
