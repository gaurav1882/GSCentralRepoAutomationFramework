package com.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.BaseClass;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

public class SearchTest extends BaseClass {
	public SearchTest() {
		super();
	}
	public WebDriver driver;
	SearchPage searchPage;

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
	}
	@Test(priority = 1)
	public void verifySearchWithValidProduct() {
		HomePage homePage = new HomePage(driver);
		homePage.enterProductInSearch(dataProp.getProperty("validProduct"));
		searchPage = homePage.clickOnSearchButton();
		Assert.assertTrue(searchPage.displayStatusOfValidProduct(), "Valid Product HP is not displayed");
	}
	@Test(priority = 2)
	public void verifySearchWithInValidProduct() {
		
		HomePage homePage = new HomePage(driver);
		homePage.enterProductInSearch(dataProp.getProperty("invalidProduct"));
		searchPage = homePage.clickOnSearchButton();
		
		String actualSearchMessage = searchPage.retrieveNoProductMessageText();
		Assert.assertEquals(actualSearchMessage,dataProp.getProperty("noProductTextInSearchResults"), "The actual search message is not displayed");
		//removed dataProp.getProperty to fail this TC intentionally above, just gave the xpath.
	}

	@Test(priority = 3,dependsOnMethods={"verifySearchWithInValidProduct"})
	public void verifySearchWithNoProductName() {
		HomePage homePage = new HomePage(driver);
		searchPage = homePage.clickOnSearchButton();
		String actualSearchMessage = searchPage.retrieveNoProductMessageText();
		Assert.assertEquals(actualSearchMessage, dataProp.getProperty("noProductTextInSearchResults"), "The actual search message is not displayed");
	}
}
