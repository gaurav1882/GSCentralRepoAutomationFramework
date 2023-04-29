package com.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.BaseClass;

public class Search extends BaseClass{
	
	WebDriver driver;

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@BeforeMethod
	public void setUp() {
		driver = initializeBrowserAndOpenApplicationURL("Chrome");
	}
	@Test(priority=1)
	public void verifySearchWithValidProduct(){
		
		driver.findElement(By.xpath("//header/div[1]/div[1]/div[2]/div[1]/input[1]")).sendKeys("HP");
		driver.findElement(By.xpath("//header/div[1]/div[1]/div[2]/div[1]/span[1]/button[1]")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//a[contains(text(),'HP LP3065')]")).isDisplayed());
}
	@Test(priority=2)
	public void verifySearchWithInValidProduct(){
		
		driver.findElement(By.xpath("//header/div[1]/div[1]/div[2]/div[1]/input[1]")).sendKeys("Honda");
		driver.findElement(By.xpath("//header/div[1]/div[1]/div[2]/div[1]/span[1]/button[1]")).click();
		String actualSearchMessage = driver.findElement(By.xpath("//p[contains(text(),'There is no product that matches the search criter')]")).getText();
		Assert.assertEquals(actualSearchMessage, "There is no product that matches the search criteria.", "The actual search message is not displayed");
	}
		
	@Test(priority=3)
	public void verifySearchWithNoProductName() {
		
		driver.findElement(By.xpath("//header/div[1]/div[1]/div[2]/div[1]/input[1]")).sendKeys("");
		driver.findElement(By.xpath("//header/div[1]/div[1]/div[2]/div[1]/span[1]/button[1]")).click();
		String actualSearchMessage = driver.findElement(By.xpath("//p[contains(text(),'There is no product that matches the search criter')]")).getText();
		Assert.assertEquals(actualSearchMessage, "There is no product that matches the search criteria.", "The actual search message is not displayed");
			
		}
}
