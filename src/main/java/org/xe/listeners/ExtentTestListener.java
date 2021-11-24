package org.xe.listeners;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.xe.conversion.uat.utils.ExtentTestManager;

public class ExtentTestListener implements ITestListener {


	public void onStart(ITestContext context) {
		SystemOutToLog4j.enableForClass(this.getClass());
		System.out.println("*** Test Suite " + context.getName() + " started ***");
	}

	public void onFinish(ITestContext context) {
		SystemOutToLog4j.enableForClass(this.getClass());
		System.out.println(("*** Test Suite " + context.getName() + " ending ***"));
		//ExtentTestManager.endTest();
		ExtentTestManager.endReport();
		//extent.flush();
	}

	public void onTestStart(ITestResult result) {
		SystemOutToLog4j.enableForClass(this.getClass());
		System.out.println(("*** Running test method " + result.getMethod().getMethodName() + "..."));
		ExtentTestManager.startTest(result.getMethod().getMethodName(),result.getMethod().getGroups(),result.getMethod().getDescription() + "");
	}

	public void onTestSuccess(ITestResult result) {
		
		SystemOutToLog4j.enableForClass(this.getClass());
		System.out.println("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
		ExtentTestManager.getTest().pass("Test Passed");
	}

	public void onTestFailure(ITestResult result) {
		SystemOutToLog4j.enableForClass(this.getClass());
		
		System.out.println("*** Test execution " + result.getMethod().getMethodName() + " failed...");
		ExtentTestManager.getTest().fail("Test Failed");
	}

	public void onTestSkipped(ITestResult result) {
		SystemOutToLog4j.enableForClass(this.getClass());
		
		System.out.println("*** Test " + result.getMethod().getMethodName() + " skipped...");
		ExtentTestManager.getTest().skip("Test Skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		SystemOutToLog4j.enableForClass(this.getClass());
		
		System.out.println("*** Test failed but within percentage % " + result.getMethod().getMethodName());
	}

}