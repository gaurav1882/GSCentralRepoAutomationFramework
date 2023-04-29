package com.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.BaseClass;
import com.tutorialsninja.qa.utils.Utilities;

public class Login extends BaseClass{
	WebDriver driver;

	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenApplicationURL("Chrome");
		driver.findElement(By.xpath("//span[contains(text(),'My Account')]")).click();
		driver.findElement(By.linkText("Login")).click();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifyLoginValidCredentails() {

		driver.findElement(By.id("input-email")).sendKeys("amotooricap9@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(
				By.xpath("//body/div[@id='account-login']/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/input[1]"))
				.click();
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
	}

	@Test(priority = 2)
	public void verifyLoginInvalidCredentails() {

		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-password")).sendKeys("1254345");
		driver.findElement(
				By.xpath("//body/div[@id='account-login']/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/input[1]"))
				.click();
		String actualWarningMessage = driver.findElement(By.xpath("//body/div[@id='account-login']/div[1]")).getText();
		System.out.println(actualWarningMessage + "***********");
		String expectedWarnignMessage = "Warning: No match for E-Mail Address and/or Password.";

		Assert.assertTrue(actualWarningMessage.contains(expectedWarnignMessage), "Expected message is not displayed");
	}

	@Test(priority = 3)
	public void verifyLoginInvalidEmailAndValidPassword() {

		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(
				By.xpath("//body/div[@id='account-login']/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/input[1]"))
				.click();
		String actualWarningMessage = driver.findElement(By.xpath("//body/div[@id='account-login']/div[1]")).getText();
		System.out.println(actualWarningMessage + "***********");
		String expectedWarnignMessage = "Warning: No match for E-Mail Address and/or Password.";

		Assert.assertTrue(actualWarningMessage.contains(expectedWarnignMessage), "Expected message is not displayed");
	}

	@Test(priority = 4)
	public void verifyLoginValidEmailAndInvalidPassword() {
		driver.findElement(By.id("input-email")).sendKeys("amotooricap9@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("12lkjljklj345");
		driver.findElement(
				By.xpath("//body/div[@id='account-login']/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/input[1]"))
				.click();
		String actualWarningMessage = driver.findElement(By.xpath("//body/div[@id='account-login']/div[1]")).getText();
		System.out.println(actualWarningMessage + "***********");
		String expectedWarnignMessage = "Warning: No match for E-Mail Address and/or Password.";

		Assert.assertTrue(actualWarningMessage.contains(expectedWarnignMessage), "Expected message is not displayed");
	}

	@Test(priority = 5)
	public void verifyWithoutLoginCredentials() {

		driver.findElement(By.id("input-email")).sendKeys("");
		driver.findElement(By.id("input-password")).sendKeys("");
		driver.findElement(
				By.xpath("//body/div[@id='account-login']/div[1]/div[1]/div[1]/div[2]/div[1]/form[1]/input[1]"))
				.click();
		String actualWarningMessage = driver.findElement(By.xpath("//body/div[@id='account-login']/div[1]")).getText();
		System.out.println(actualWarningMessage + "***********");
		String expectedWarnignMessage = "Warning: No match for E-Mail Address and/or Password.";

		Assert.assertTrue(actualWarningMessage.contains(expectedWarnignMessage), "Expected message is not displayed");
	}

}
