package org.xe.conversion.uat.utils;
import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {
	static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
	static ExtentReports extent = ExtentManager.extentReportGenerator();

	public static synchronized ExtentTest getTest() {
		return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
	}

	public static synchronized void endReport() {
		extent.flush();
	}

	public static synchronized ExtentTest startTest(String testName) {
		ExtentTest test = extent.createTest(testName);
		test.assignCategory("");
		extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
		return test;
	}

	public static synchronized ExtentTest startTest(String testName, String[] groups) {
		ExtentTest test = extent.createTest(testName);
		test.assignCategory(groups);
		extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
		return test;
	}
	
	public static synchronized ExtentTest startTest(String testName, String[] groups, String description) {
		
		ExtentTest test;
		if(!description.trim().equals(""))
			test = extent.createTest(description);
		else
			test = extent.createTest(testName);
		
		test.assignCategory(groups);
		extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
		return test;
	}
}