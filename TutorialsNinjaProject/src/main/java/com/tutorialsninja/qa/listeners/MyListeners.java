package com.tutorialsninja.qa.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class MyListeners implements ITestListener{
	
	@Override
	public void onStart(ITestContext context) {
	System.out.println("Execution of Project test started");	
	}

	@Override
	public void onTestStart(ITestResult result) {
	
		String testName = result.getName();
		System.out.println(testName+ "Started Executing");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		String testName = result.getName();
		System.out.println(testName+ "Got successfully executed");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getName();
		System.out.println(testName+"Got Failed");
		System.out.println(result.getThrowable());//this will give all the details of fail TCs
	
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testName = result.getName();
		System.out.println(testName+"Got skipped");
		System.out.println(result.getThrowable());//this will give all the details of skipped TCs
	}


	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Finished executing project test");
		
	}

	
}
