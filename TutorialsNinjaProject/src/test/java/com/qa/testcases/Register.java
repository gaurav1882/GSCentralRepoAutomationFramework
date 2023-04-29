package com.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.BaseClass;
import com.tutorialsninja.qa.utils.Utilities;

public class Register extends BaseClass{

	WebDriver driver;

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenApplicationURL("Chrome");
		driver.findElement(By.xpath("//span[contains(text(),'My Account')]")).click();
		driver.findElement(By.linkText("Register")).click();
	}

	@Test
	public void verifyRegisteringAccountMandatoryFields() {

		driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys("Gaurav");
		driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys("Srivastava");
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys("9990001379");
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@id='input-confirm']")).sendKeys("12345");
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//body/div[@id='account-register']/div[1]/div[1]/form[1]/div[1]/div[1]/input[2]"))
				.click();

		String actualSuccessHeading = driver
				.findElement(By.xpath("//h1[contains(text(),'Your Account Has Been Created!')]")).getText();
		Assert.assertEquals(actualSuccessHeading, "Your Account Has Been Created!", "Account not Created Successfully");
	}

	@Test(priority = 2)
	public void verifyRegisterAccountProvidingAllFields() {
		driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys("Gaurav");
		driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys("Srivastava");
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys("9990001379");
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@id='input-confirm']")).sendKeys("12345");
		driver.findElement(By.xpath(
				"//body/div[@id='account-register']/div[1]/div[1]/form[1]/fieldset[3]/div[1]/div[1]/label[1]/input[1]"))
				.click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//body/div[@id='account-register']/div[1]/div[1]/form[1]/div[1]/div[1]/input[2]"))
				.click();

		String actualSuccessHeading = driver
				.findElement(By.xpath("//h1[contains(text(),'Your Account Has Been Created!')]")).getText();
		Assert.assertEquals(actualSuccessHeading, "Your Account Has Been Created!", "Account not Created Successfully");
	}

	@Test(priority = 3)
	public void verifyRegisterAccountWithExistingEmailAddress() {
		driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys("Gaurav");
		driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys("Srivastava");
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("amotooricap9@gmail.com");
		driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys("9990001379");
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@id='input-confirm']")).sendKeys("12345");
		driver.findElement(By.xpath(
				"//body/div[@id='account-register']/div[1]/div[1]/form[1]/fieldset[3]/div[1]/div[1]/label[1]/input[1]"))
				.click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//body/div[@id='account-register']/div[1]/div[1]/form[1]/div[1]/div[1]/input[2]"))
				.click();
		String actualWarning = driver.findElement(By.xpath("//body/div[@id='account-register']/div[1]")).getText();
		Assert.assertTrue(actualWarning.contains("Warning: E-Mail Address is already registered!"),
				"Warning message is not displayed");
	}

	@Test(priority = 4)
	public void registeringAccountWithoutFillingDetails() {
		driver.findElement(By.xpath("//body/div[@id='account-register']/div[1]/div[1]/form[1]/div[1]/div[1]/input[2]"))
				.click();

		String actualPrivacyPolicyWarning = driver.findElement(By.xpath("//body/div[@id='account-register']/div[1]"))
				.getText();
		Assert.assertTrue(actualPrivacyPolicyWarning.contains("Warning: You must agree to the Privacy Policy!"),
				"Privacy Warning message is not displayed");

		String actualFirstNameWarning = driver
				.findElement(By.xpath("//div[contains(text(),'First Name must be between 1 and 32 characters!')]"))
				.getText();
		Assert.assertEquals(actualFirstNameWarning, "First Name must be between 1 and 32 characters!",
				"First name warning mesage is not displayed");

		String actualLastNameWarning = driver
				.findElement(By.xpath("//div[contains(text(),'Last Name must be between 1 and 32 characters!')]"))
				.getText();
		Assert.assertEquals(actualLastNameWarning, "Last Name must be between 1 and 32 characters!",
				"First name warning mesage is not displayed");

		String actualEmailWarning = driver
				.findElement(By.xpath("//div[contains(text(),'E-Mail Address does not appear to be valid!')]"))
				.getText();
		Assert.assertEquals(actualEmailWarning, "E-Mail Address does not appear to be valid!",
				"Email warning mesage is not displayed");

		String actualTelephoneWarning = driver
				.findElement(By.xpath("//div[contains(text(),'Telephone must be between 3 and 32 characters!')]"))
				.getText();
		Assert.assertEquals(actualTelephoneWarning, "Telephone must be between 3 and 32 characters!",
				"Actual telephone warning mesage is not displayed");

		String actualPasswordWarning = driver
				.findElement(By.xpath("//div[contains(text(),'Password must be between 4 and 20 characters!')]"))
				.getText();
		Assert.assertEquals(actualPasswordWarning, "Password must be between 4 and 20 characters!",
				"Password warning mesage is not displayed");

	}
}
