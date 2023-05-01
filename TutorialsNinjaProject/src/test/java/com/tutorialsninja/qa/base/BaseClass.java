package com.tutorialsninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorialsninja.qa.utils.Utilities;

public class BaseClass {
	WebDriver driver;
	public Properties prop;
	public Properties dataProp;
	
	//Loading Properties file
	//loadPropertiesFile() This method can also be used for loading properties file
	//public BaseClass() //Base constructor to load the Properties file
	public BaseClass()
	{
		
		
		dataProp = new Properties();
		File dataPropFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutrialsninja\\qa\\testdata\\testdata.properties");
		try {
		FileInputStream dataFis2 = new FileInputStream(dataPropFile);
		dataProp.load(dataFis2);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		prop = new Properties();
		File propFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
		try {
			FileInputStream fis = new FileInputStream(propFile);
			prop.load(fis);
		}catch(Throwable e) {
			e.printStackTrace();
		}
	}	
	public WebDriver initializeBrowserAndOpenApplicationURL(String browserName) {
	
			//use ignorecase if the browser name is mentioned in Upper/lower case
			if (browserName.equalsIgnoreCase("Chrome")) {
				driver = new ChromeDriver();
			} else if (browserName.equalsIgnoreCase("Firefox")) {
				driver = new FirefoxDriver();
			} else if (browserName.equalsIgnoreCase("Edge")) {
				driver = new EdgeDriver();
			}
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_WAIT_TIME));
			driver.get(prop.getProperty("url"));
			return driver;	
	}
}
