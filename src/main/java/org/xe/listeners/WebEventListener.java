package org.xe.listeners;
/*************************************** PURPOSE **********************************

 - This class implements the WebDriverEventListener, which is included under events.
 The purpose of implementing this interface is to override all the methods and define certain useful  Log statements 
 which would be displayed/logged as the application under test is being run.

 Do not call any of these methods, instead these methods will be invoked automatically
 as an when the action done (click, findBy etc). 

 */

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.xe.conversion.uat.base.TestBase;
import org.xe.conversion.uat.utils.ExtentTestManager;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import io.appium.java_client.events.api.general.AppiumWebDriverEventListener;
import io.qameta.allure.Attachment;


public class WebEventListener extends TestBase implements WebDriverEventListener,AppiumWebDriverEventListener {

	public WebEventListener(){
		SystemOutToLog4j.enableForClass(this.getClass());
	}
	public void beforeNavigateTo(String url, WebDriver driver) {
		SystemOutToLog4j.enableForClass(this.getClass());
		System.out.println("Before navigating to: '" + url + "'");
		
		try {
			//ExtentTestManager.getTest().log(Status.INFO, "Before navigating to: '" + url + "'");
		}
		catch(Exception e) {
			
		}
	}

	public void afterNavigateTo(String url, WebDriver driver) {
		SystemOutToLog4j.enableForClass(this.getClass());
		System.out.println("Navigated to:'" + url + "'");
		try {
			ExtentTestManager.getTest().log(Status.PASS,"Navigated to:'" + url + "'");
			allureSaveTextLog("Navigated to:'" + url + "'");
		}
		catch(Exception e) {
			
		}
			
	}

	public void beforeChangeValueOf(WebElement element, WebDriver driver) {
		SystemOutToLog4j.enableForClass(this.getClass());
		System.out.println("Value of the:" + element.toString().replaceAll("Driver:?.*?[)]] ->", "Driver] ->") + " before any changes made");		
		try {
			//ExtentTestManager.getTest().log(Status.INFO, "Value of the:" + element.toString().replaceAll("Driver:?.*?[)]] ->", "Driver] ->") + " before any changes made");
		}
		catch(Exception e) {
			
		}
		
	}

	public void afterChangeValueOf(WebElement element, WebDriver driver) {
		SystemOutToLog4j.enableForClass(this.getClass());
		try {
			System.out.println("Element value changed to: " + element.toString().replaceAll("Driver:?.*?[)]] ->", "Driver] ->")+ "<br />ScreenName: " + activeScreen.split("[.]")[activeScreen.split("[.]").length-1]);
			ExtentTestManager.getTest().log(Status.PASS, "Element value changed to: " + element.toString().replaceAll("Driver:?.*?[)]] ->", "Driver] ->")  + "<br />ScreenName: " + activeScreen.split("[.]")[activeScreen.split("[.]").length-1], MediaEntityBuilder.createScreenCaptureFromBase64String(((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64)).build());
			allureSaveScreenshotPNG(driver,"Element value changed to: " + element.toString().replaceAll("Driver:?.*?[)]] ->", "Driver] ->")  + "in Screen - " + activeScreen.split("[.]")[activeScreen.split("[.]").length-1]);
		}
		catch(Exception e) {
			
		}		
		
	}

	public void beforeClickOn(WebElement element, WebDriver driver) {
		SystemOutToLog4j.enableForClass(this.getClass());
		try {
			lastExceptionElement=element;
			System.out.println("Trying to click on: " + element.toString().replaceAll("Driver:?.*?[)]] ->", "Driver] ->") + "<br />ScreenName: " + activeScreen.split("[.]")[activeScreen.split("[.]").length-1] );
			try{
				ExtentTestManager.getTest().log(Status.INFO, "Trying to click on: " + element.toString().replaceAll("Driver:?.*?[)]] ->", "Driver] ->")  + "<br />ScreenName: " + activeScreen.split("[.]")[activeScreen.split("[.]").length-1] + "<br /> ElementText (or) Name: " + ((element.getText().length() <1) ? element.getAttribute("textContent") : element.getText()) , MediaEntityBuilder.createScreenCaptureFromBase64String(((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64)).build());
				allureSaveScreenshotPNG(driver,"Trying to click on: " + element.toString().replaceAll("Driver:?.*?[)]] ->", "Driver] ->")  + " | ScreenName: " + activeScreen.split("[.]")[activeScreen.split("[.]").length-1] + ", ElementText (or) Name: " + ((element.getText().length() <1) ? element.getAttribute("textContent") : element.getText()));
			}
			catch(Exception e) {
				ExtentTestManager.getTest().log(Status.INFO, "Trying to click on: " + element.toString().replaceAll("Driver:?.*?[)]] ->", "Driver] ->")  + "<br />ScreenName: " + activeScreen.split("[.]")[activeScreen.split("[.]").length-1] + "<br /> ElementText (or) Name: " + element.getText(), MediaEntityBuilder.createScreenCaptureFromBase64String(((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64)).build());
				allureSaveScreenshotPNG(driver,"Trying to click on: " + "Trying to click on: " + element.toString().replaceAll("Driver:?.*?[)]] ->", "Driver] ->")  + ", ScreenName: " + activeScreen.split("[.]")[activeScreen.split("[.]").length-1] + ", ElementText (or) Name: " + element.getText());
			}				
		}
		catch(Exception e) {
			
		}	
		
	}

	public void afterClickOn(WebElement element, WebDriver driver) {
		SystemOutToLog4j.enableForClass(this.getClass());
		try {
				lastExceptionElement=element;
				System.out.println("Clicked on: " + element.toString().replaceAll("Driver:?.*?[)]] ->", "Driver] ->")  + "<br />ScreenName: " + activeScreen.split("[.]")[activeScreen.split("[.]").length-1]);
				try{
					ExtentTestManager.getTest().log(Status.PASS, "Clicked on: " + element.toString().replaceAll("Driver:?.*?[)]] ->", "Driver] ->")  + "<br />ScreenName: " + activeScreen.split("[.]")[activeScreen.split("[.]").length-1]  + "<br /> ElementText (or) Name: " + ((element.getText().length() <1) ? element.getAttribute("textContent") : element.getText()) , MediaEntityBuilder.createScreenCaptureFromBase64String(((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64)).build());
					allureSaveScreenshotPNG(driver,"Clicked on: " + element.toString().replaceAll("Driver:?.*?[)]] ->", "Driver] ->")  + ", ScreenName: " + activeScreen.split("[.]")[activeScreen.split("[.]").length-1]  + ", ElementText (or) Name: " + ((element.getText().length() <1) ? element.getAttribute("textContent") : element.getText()));
				}
				catch(Exception e) {					
					ExtentTestManager.getTest().log(Status.PASS, "Clicked on: " + element.toString().replaceAll("Driver:?.*?[)]] ->", "Driver] ->")  + "<br />ScreenName: " + activeScreen.split("[.]")[activeScreen.split("[.]").length-1]  + "<br /> ElementText (or) Name: " + element.getText(), MediaEntityBuilder.createScreenCaptureFromBase64String(((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64)).build());
					allureSaveScreenshotPNG(driver,"Clicked on: " + element.toString().replaceAll("Driver:?.*?[)]] ->", "Driver] ->")  + ", ScreenName: " + activeScreen.split("[.]")[activeScreen.split("[.]").length-1]  + ", ElementText (or) Name: " + element.getText());
					
				}		
		}
		catch(Exception e) {
			
		}
		
	}

	public void beforeNavigateBack(WebDriver driver) {
		SystemOutToLog4j.enableForClass(this.getClass());
		System.out.println("Navigating back to previous page");
		try {
			//ExtentTestManager.getTest().log(Status.INFO,"Navigating back to previous page");
		}
		catch(Exception e) {
			
		}		
	}

	public void afterNavigateBack(WebDriver driver) {
		SystemOutToLog4j.enableForClass(this.getClass());
		System.out.println("Navigated back to previous page");
		try {
			ExtentTestManager.getTest().log(Status.PASS,"Navigated back to previous page");
			allureSaveTextLog("Navigated back to previous page");
		}
		catch(Exception e) {
			
		}		
	}

	public void beforeNavigateForward(WebDriver driver) {
		SystemOutToLog4j.enableForClass(this.getClass());
		System.out.println("Navigating forward to next page");
		try {
			//ExtentTestManager.getTest().log(Status.INFO,"Navigating forward to next page");
		}
		catch(Exception e) {
			
		}	
	}

	public void afterNavigateForward(WebDriver driver) {
		SystemOutToLog4j.enableForClass(this.getClass());
		System.out.println("Navigated forward to next page");
		try {
			ExtentTestManager.getTest().log(Status.PASS,"Navigated forward to next page");
			allureSaveTextLog("Navigated forward to next page");
		}
		catch(Exception e) {
			
		}		
	}

	public void onException(Throwable error, WebDriver driver) {
		SystemOutToLog4j.enableForClass(this.getClass());
		
		try {
			if(error.getLocalizedMessage().toString().toLowerCase().contains("element click intercepted")) {
				try {
					/*
					 * For time being hard coded, to handle the dynamic popup. .. Have betterment solution. not implemented as of now
					 * */
					JavascriptExecutor jsDriver; 
					jsDriver=(JavascriptExecutor)driver;		 
					jsDriver.executeScript("document.querySelectorAll(\"[id*='yie-close-button']\")[0].click();");
					lastExceptionElement.click();
				}
				catch(Exception e) {
					
				}
			}				
			else if(!error.getLocalizedMessage().toString().toLowerCase().contains("stale element reference:") && !error.getLocalizedMessage().toString().toLowerCase().contains("cannot read properties of undefined (reading 'click')") )
				{
					//ExtentTestManager.getTest().log(Status.FAIL, "Exception occured: " + error);	
					System.out.println("Exception occured: " + error);
					ExtentTestManager.getTest().log(Status.FAIL, "Exception occured: " + error, MediaEntityBuilder.createScreenCaptureFromBase64String(((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64)).build());
					allureSaveScreenshotPNG(driver,"Clicked on: " + "Exception occured: " + error);
				}
		}
		catch(Exception e) {
			
		}			
		
		/*try {
			LoggerUtils.takeScreenshotAtEndOfTest(driver);
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}

	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		try {
			/*
			 * For time being hard coded, to handle the dynamic popup. .. Have betterment solution. not implemented as of now
			 * */
			JavascriptExecutor jsDriver; 
			jsDriver=(JavascriptExecutor)driver;		 
			jsDriver.executeScript("document.querySelectorAll(\"[id*='yie-close-button']\")[0].click();");
			lastExceptionElement=element;
		}
		catch(Exception e) {}
		
		SystemOutToLog4j.enableForClass(this.getClass());
		try {
			System.out.println("Trying to find Element By : " + by.toString() + "<br />ScreenName: " + activeScreen.split("[.]")[activeScreen.split("[.]").length-1]);
		}
		catch(Exception e) {
			
		}			
		
	}

	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		SystemOutToLog4j.enableForClass(this.getClass());
		try {
			System.out.println("Found Element By : " + by.toString() + "<br />ScreenName: " + activeScreen.split("[.]")[activeScreen.split("[.]").length-1]);
			//ExtentTestManager.getTest().log(Status.PASS, "Found Element By : " + by.toString().replaceAll("Driver:?.*?)] ->", ""));
			if(!activeScreen.split("[.]")[activeScreen.split("[.]").length-1].trim().equalsIgnoreCase(previousActiveScreen))
				try{					
					ExtentTestManager.getTest().log(Status.PASS, "Found Element By : " + by.toString()+ "<br />ScreenName: " + activeScreen.split("[.]")[activeScreen.split("[.]").length-1] + "<br /> ElementText (or) Name: " +   ((element.getText().length() <1) ? element.getAttribute("textContent") : element.getText()), MediaEntityBuilder.createScreenCaptureFromBase64String(((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64)).build());
					allureSaveScreenshotPNG(driver,"Found Element By : " + by.toString()+ ", ScreenName: " + activeScreen.split("[.]")[activeScreen.split("[.]").length-1] + ", ElementText (or) Name: " +   ((element.getText().length() <1) ? element.getAttribute("textContent") : element.getText()));
				}
				catch(Exception e){
					ExtentTestManager.getTest().log(Status.PASS, "Found Element By : " + by.toString()+ "<br />ScreenName: " + activeScreen.split("[.]")[activeScreen.split("[.]").length-1] + "<br /> ElementText (or) Name: " +   element.getText());
					allureSaveScreenshotPNG(driver,"Found Element By : " + by.toString()+ ", ScreenName: " + activeScreen.split("[.]")[activeScreen.split("[.]").length-1] + ", ElementText (or) Name: " +   element.getText());
				}
			
			previousActiveScreen=activeScreen.split("[.]")[activeScreen.split("[.]").length-1].trim();
			lastExceptionElement=element;
			//element.getScreenshotAs(OutputType.FILE);
		}
		catch(Exception e) {
			
		}
		
	}

	/*
	 * non overridden methods of WebListener class
	 */
	public void beforeScript(String script, WebDriver driver) {
	}

	public void afterScript(String script, WebDriver driver) {
	}

	public void beforeAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void afterAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void afterAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void beforeAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void beforeNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void afterNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		// TODO Auto-generated method stub

	}

	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		// TODO Auto-generated method stub

	}

	public <X> void afterGetScreenshotAs(OutputType<X> arg0, X arg1) {
		// TODO Auto-generated method stub
		
	}

	public void afterGetText(WebElement arg0, WebDriver arg1, String arg2) {
		// TODO Auto-generated method stub
		
	}

	public void afterSwitchToWindow(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}

	public <X> void beforeGetScreenshotAs(OutputType<X> arg0) {
		// TODO Auto-generated method stub
		
	}

	public void beforeGetText(WebElement arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}

	public void beforeSwitchToWindow(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@Attachment(value = "{1}", type = "image/png")
	public byte[] allureSaveScreenshotPNG(WebDriver driver,String value) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	// Text attachments for Allure
	@Attachment(value = "{0}", type = "text/plain")
	public static String allureSaveTextLog(String message) {
		return message;
	}
}