package org.xe.conversion.uat.testcases;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.xe.conversion.uat.base.TestBase;
import org.xe.conversion.uat.pages.ChartTab;
import org.xe.conversion.uat.pages.ConvertTab;
import org.xe.conversion.uat.pages.HomePage;
import org.xe.conversion.uat.utils.CustomAssertion;
import org.xe.conversion.uat.utils.CustomException;
import org.xe.listeners.SystemOutToLog4j;


import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.xe.conversion.uat.utils.ReadXlData;

public class XeConversionTestBase extends TestBase {
	HomePage homePage;
	ConvertTab convertTab;
	ChartTab chartTab;
	Object[][] xeWebOfflineData;
	ReadXlData readxlData;
	Object[][] xeSiteData;
	
	public XeConversionTestBase() {
		super();
	}
	
	private CustomAssertion Assert=new CustomAssertion();
	
	@SuppressWarnings("static-access")
	@BeforeSuite
	public void setup() throws CustomException, IOException {
		SystemOutToLog4j.enableForClass(this.getClass());
		initializationWebDriver();
		homePage=new HomePage();
		convertTab=new ConvertTab();
		chartTab=new ChartTab();
		xeSiteData=readxlData.getXESiteData("XESiteData.xlsx", "Sheet1");
	}
	
	@Test(priority = 1, enabled=true, description = "Verify the XE Currency Conversion Application Launched" , groups = "launch" )
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Description: Verify the XE Currency Conversion Application default langing page is ConvertTab")
	@Story("Story Name: Verify XE Application is opening")
	public void areWeInXeHomePage() {
		Assert.assertEquals(homePage.waitForHomePageToLoad(),true,"XE Conversion home page should be loaded");
		Assert.assertEquals(convertTab.waitForConvertTabToLoad(), true,"Covert Tab should be the default the tab in home page");
	}
	
	@SuppressWarnings("static-access")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description: Verify currency conversion functionality")
	@Story("Story Name: Currency Conversion Core")
	@Test(priority = 2, enabled=true, dataProvider = "currencyConversionData" ,description = "Verify currency conversion functionality" , groups ="core conversion")
	public void verifyCurrencyConversion(String Amount,String fromCurrencyShortName,  String toCurrencyShortName) {
		HashMap<String, String> fromCurrencyDetails = readxlData.getXEDescriptionDetails(xeSiteData, fromCurrencyShortName);
		HashMap<String, String> toCurrencyDetails = readxlData.getXEDescriptionDetails(xeSiteData, toCurrencyShortName);
		
		homePage.navigateToConvertTab();
		convertTab.waitForConvertTabToLoad();
		
		String convertedAmt = convertTab.setAmount(Amount);
		convertTab.clickFromCurrency();
		convertTab.setFromCurrency(fromCurrencyDetails.get("fullName"));
		convertTab.clickToCurrency();
		convertTab.setToCurrency(toCurrencyDetails.get("fullName"));
		convertTab.submitConvert();		
		
		Assert.assertEquals(convertTab.getFromCurrencyConvertedResult().equalsIgnoreCase(convertedAmt +" "+fromCurrencyDetails.get("namePlural")+" ="), true, "Verify whether provided amount ["+convertedAmt +" "+fromCurrencyDetails.get("namePlural")+" =] is displayed perfectly in from currency converted section");
		Assert.assertEquals(convertTab.getToCurrencyConvertedResult().toLowerCase().contains(toCurrencyDetails.get("namePlural").toLowerCase()), true, "Verify whether converted - [to currency section] is having plural name ["+toCurrencyDetails.get("namePlural")+"] of given currency");
		
		if(Float.parseFloat(convertedAmt)>1) {
			Assert.assertEquals(convertTab.getFirstUnitRate().contains("1 "+fromCurrencyDetails.get("shortName")+" ="), true, "Verify whether application displays unit rates for from currency in converted section");
			Assert.assertEquals(convertTab.getSecoundUnitRate().contains("1 "+toCurrencyDetails.get("shortName")+" ="), true, "Verify whether application displays unit rates for to currency in converted section");
		}
		else {
			Assert.assertEquals(convertTab.getFirstUnitRate().contains("1 "+toCurrencyDetails.get("shortName")+" ="), true, "Verify whether application displays unit rates for to currency in converted section");
		}
		
		homePage.navigateToChartTab();
		Assert.assertEquals(chartTab.waitTillChartTabLoading(),true,"wait for the Chart Tab to be load");
		Assert.assertEquals(chartTab.getFromCurrencyListValue().trim().equalsIgnoreCase(fromCurrencyDetails.get("shortName") + " – " + fromCurrencyDetails.get("fullName")),true,"verify whether selected from currency shown in listbox");
		Assert.assertEquals(chartTab.getToCurrencyListValue().trim().equalsIgnoreCase(toCurrencyDetails.get("shortName") + " – " + toCurrencyDetails.get("fullName")),true,"verify whether selected from currency shown in listbox");

		chartTab.clickOnViewChart();
		Assert.assertEquals(chartTab.waitForChartToLoad(),true,"wait for chart to load");
		
		Assert.assertEquals(chartTab.getChartTopTitle().trim().equalsIgnoreCase(fromCurrencyDetails.get("shortName") + " to " + toCurrencyDetails.get("shortName") + " Chart"),true,"verify whether chart title as [From ShortName] to [To ShortName]");
		Assert.assertEquals(chartTab.getLiveUnitRate().trim().startsWith("1 "+fromCurrencyDetails.get("shortName")),true,"Verify Live Unit Rate Starts with from currency " + toCurrencyDetails.get("shortName"));
		Assert.assertEquals(chartTab.getLiveUnitRate().trim().endsWith(" "+toCurrencyDetails.get("shortName")),true,"Verify Live Unit Rates ends with to curreny short name");
		
			
	}
	
	@SuppressWarnings("static-access")
	@DataProvider
	public Object[][] currencyConversionData(Method m) throws IOException
	{
		return  readxlData.getTestData("verifyCurrencyConversionInputDataSet.xlsx", "Sheet1");						
	}
	
	@AfterSuite
	public void tearDown() {
		driver.quit();
	}
}	
