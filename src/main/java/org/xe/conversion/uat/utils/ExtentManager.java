package org.xe.conversion.uat.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;



public class ExtentManager {
    private static ExtentReports extent;
    private static String reportFileName = "Test-Automaton-Report" + (new SimpleDateFormat(" dd-MMM-yyyy HH-mm-ss")).format(new Date()) +".html";
    private static String fileSeperator = System.getProperty("file.separator");
    private static String reportFilepath = System.getProperty("user.dir") +fileSeperator+ "TestReport";
    private static String reportFileLocation =  reportFilepath +fileSeperator+ reportFileName;
    
    public static ExtentReports extentReportGenerator()
    {
		 String fileName = getReportPath(reportFilepath);
		 ExtentSparkReporter reporter = new ExtentSparkReporter(fileName);
		 
		 reporter.config().setDocumentTitle("Automation is simple");
		// reporter.config().enableTimeline(true);
		 reporter.config().setEncoding("ISO-8859-1");
		 reporter.config().setProtocol(Protocol.HTTPS);
		 reporter.config().setReportName("XE Conversion - TecTest");
		 reporter.config().setTheme(Theme.DARK);
		 //reporter.config().setOfflineMode(true);
		 //reporter.config().enableTimeline(true);
		 reporter.config().setTimelineEnabled(true);
		 //reporter.config().setResourceCDN(fileName);
		 
		 
		 reporter.viewConfigurer().viewOrder()
		    .as(new ViewName[] { 
			   ViewName.DASHBOARD, 
			   ViewName.TEST, 
			   ViewName.CATEGORY, 
			   ViewName.AUTHOR, 
			   ViewName.DEVICE, 
			   ViewName.EXCEPTION, 
			   ViewName.LOG,
			})
		  .apply();		 
		 extent = new ExtentReports();
		 extent.attachReporter(reporter);
		 extent.setSystemInfo("Author", "Premkumar R");
		 extent.setSystemInfo("Mail", "premkumarrtech@gmail.com");
		 extent.setSystemInfo("Phone", "+91-9566144635");
		 return extent;
     
    }

 
       
    //Create the report path
    private static String getReportPath (String path) {
    	File testDirectory = new File(path);
        if (!testDirectory.exists()) {
        	if (testDirectory.mkdir()) {
                System.out.println("Directory: " + path + " is created!" );
                return reportFileLocation;
            } else {
                System.out.println("Failed to create directory: " + path);
                return System.getProperty("user.dir");
            }
        } else {
            System.out.println("Directory already exists: " + path);
        }
		return reportFileLocation;
    }
 
}