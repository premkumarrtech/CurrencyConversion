package org.xe.conversion.uat.pages;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.xe.conversion.uat.base.TestBase;

import io.qameta.allure.Step;

public class ConvertTab extends TestBase {
	
	@FindBy(xpath ="//input[@id='amount']") WebElement amount;
	
	@FindBy(id ="midmarketFromCurrency") WebElement fromCurrency;
	
	@FindBy(id ="midmarketToCurrency") WebElement toCurrency;
	
	@FindBy(xpath = "//li[contains(@id,'midmarketFromCurrency-option')]") List<WebElement> fromCurrencyListOptions;
	
	@FindBy(xpath = "//li[contains(@id,'midmarketToCurrency-option')]") List<WebElement> toCurrencyListOptions;
	
	@FindBy(xpath = "//button[@aria-label='Swap currencies']") WebElement swapCurrencies;
	
	@FindBy(xpath = "//button[text()='Convert']|//*[contains(@class,'result__ConvertedText')]") WebElement convertBtn; // to nullify error on convert button removal 
	
	@FindBy(xpath = "//p[contains(@class,'result__ConvertedText')]") WebElement fromCurrencyResult;
	
	@FindBy(xpath = "//p[contains(@class,'result__BigRate')]") WebElement toCurrencyResult;
	
	@FindBy(xpath ="//div[contains(@class,'unit-rates___')]/p[1]") WebElement unitRateFrom2To;
	
	@FindBy(xpath ="//div[contains(@class,'unit-rates___')]/p[2]") WebElement unitRateTo2From;
	
	public ConvertTab() {
		PageFactory.initElements(driver, this);
	}
	
	@Step("Wait for convert tab to be visible")
	public boolean waitForConvertTabToLoad() {
		activeScreen=this.getClass().getName();
		driverWait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
		try{
			driverWait.until((ExpectedCondition<Boolean>) wd -> amount.isDisplayed());
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	@Step("Keyin the value {0} in Amount Field")
	public String setAmount(String amt) {
		activeScreen=this.getClass().getName();
		//System.out.println(amt+ " 62");
		String roundedOffAmount = String.format("%.2f",Double.parseDouble(amt));
		
		//System.out.println("Given amount["+amt+"] have been converted as ["+roundedOffAmount+"]");
		amount.sendKeys(Keys.chord(Keys.CONTROL, "a"), roundedOffAmount+"") ; //type conversion applied - from BG to Str
		return roundedOffAmount;
	}
	
	@Step("Click on from currency list")
	public void clickFromCurrency() {
		activeScreen=this.getClass().getName();
		fromCurrency.click();
	}

	
	@Step("Set the from currency as {0}")
	public void setFromCurrency(String currencyShortName) {
		activeScreen=this.getClass().getName();
		fromCurrency.sendKeys(currencyShortName+Keys.ENTER);
	}
	
	@Step("Click on to currency list")
	public void clickToCurrency() {
		activeScreen=this.getClass().getName();
		toCurrency.click();
	}
	
	
	@Step("Set the to currency as {0}")
	public void setToCurrency(String currencyShortName) {
		activeScreen=this.getClass().getName();
		toCurrency.sendKeys(currencyShortName+Keys.ENTER);
	}
	
	@Step("Start the conversion")
	public void submitConvert() {
		activeScreen=this.getClass().getName();		
		convertBtn.click();
	}
	
	@Step("Get first unit rate in Result")
	public String getFirstUnitRate() {
		activeScreen=this.getClass().getName();
		return unitRateFrom2To.getText().trim();
	}

	@Step("Get second unit rate in Result")
	public String getSecoundUnitRate() {
		activeScreen=this.getClass().getName();
		return unitRateTo2From.getText().trim();
	}
	
	@Step("Get From Currency Converted Result")
	public String getFromCurrencyConvertedResult() {
		activeScreen=this.getClass().getName();
		return fromCurrencyResult.getText().trim();
	}
	
	@Step("Get To Currency Converted Result")
	public String getToCurrencyConvertedResult() {
		activeScreen=this.getClass().getName();
		return toCurrencyResult.getText().trim();
	}
}
