package com.tutorialsninja.qa.utils;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	
	public static void generateExtentReport() {
		
		ExtentReports extentReport = new ExtentReports();
		
		File extentReportFile = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
		
		//Below are extent report configurations
		
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("Tutorials Ninja Test Automation Results report");
		sparkReporter.config().setDocumentTitle("TN Automation Project");
		sparkReporter.config().setTimeStampFormat("dd/MM/YYYY hh:mm:ss");
		extentReport.attachReporter(sparkReporter);
		extentReport.setSystemInfo("Application URL,");
		
		
		
	}

}
