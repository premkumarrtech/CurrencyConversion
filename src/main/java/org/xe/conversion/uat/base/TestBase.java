package org.xe.conversion.uat.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.xe.conversion.uat.utils.CustomException;
import org.xe.listeners.SystemOutToLog4j;
import org.xe.listeners.WebEventListener;

public class TestBase {
	public static WebDriver driver;
	public static WebDriverWait driverWait;
	public static JavascriptExecutor jsDriver; 
	public static String browserName; 
	public static String activeScreen="";
	public static String previousActiveScreen="-";
	public static EventFiringWebDriver eventFiringDriver;
	public static WebEventListener eventListenerForDriver;
	public static WebElement lastExceptionElement;
	public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();
	
	public Properties prop;
	
	public TestBase() {
		try 
		{			
			prop = new Properties();
			File file =new File("../CurrencyConversion/src/main/resources/config.properties");
			FileInputStream ip = new FileInputStream(file);
			prop.load(ip);			
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void initializationWebDriver() throws CustomException {
		SystemOutToLog4j.enableForClass(this.getClass());
		browserName = prop.getProperty("browser");
		if(browserName.toLowerCase().trim().equals("chrome"))
		{
			HashMap<String, Object> prefs = new HashMap<String, Object>();
			File file =new File("../CurrencyConversion/src/main/resources/chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath().replace("/", "\\"));
			ChromeOptions options = new ChromeOptions();                        
			options.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));			
			//prefs.put("download.default_directory",new File("../RegressionSuite/target").getCanonicalPath());   
			prefs.put("profile.default_content_settings.popups", 0);
			options.setExperimentalOption("prefs", prefs);			
            options.setAcceptInsecureCerts(true);
            options.setCapability("applicationCacheEnabled", false);
            driver = new ChromeDriver(options);	
            
    		driver.manage().deleteAllCookies();
    		driver.manage().window().maximize();		
    		driver.manage().timeouts().pageLoadTimeout(Long.parseLong(prop.getProperty("pageLoadTimeOut")), TimeUnit.SECONDS);
    		driver.manage().timeouts().implicitlyWait(Long.parseLong(prop.getProperty("implicitTimeOut")), TimeUnit.SECONDS);
    		driverWait = new WebDriverWait(driver,(Long.parseLong(prop.getProperty("pageLoadTimeOut"))));
    		jsDriver=(JavascriptExecutor)driver;
    		eventFiringDriver = new EventFiringWebDriver(driver);
    		eventListenerForDriver = new WebEventListener();
    		eventFiringDriver.register(eventListenerForDriver);
    		driver = eventFiringDriver;
    		driver.get(prop.getProperty("url"));
    		tdriver.set(driver);
		}
		else
			throw new CustomException("This framework only supports chrome as of now. since, the testcases are designed to support import and export of file dialogs features only from chrome browser");
	}
	
	public static synchronized WebDriver getDriver() {
		return tdriver.get();
	}
}
