package org.xe.conversion.uat.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.xe.conversion.uat.base.TestBase;

import io.qameta.allure.Step;

public class ChartTab extends TestBase {

	@FindBy(xpath = "//div[@id=\"midmarketFromCurrency-descriptiveText\"]") WebElement selectedFromCurrency;
	
	@FindBy(xpath = "//div[@id=\"midmarketToCurrency-descriptiveText\"]") WebElement selectedToCurrency;
	
	@FindBy(xpath = "//button[text()='View chart']") WebElement viewChart;
	
	@FindBy(xpath ="//main/.//h1[contains(@Class,'heading__Heading1')]") WebElement chartTopTitle;
	
	@FindBy(xpath ="//main/.//h1[contains(@Class,'heading__Heading1')]/following::p[1]") WebElement chartSecondTitle;
	
	@FindBy(xpath ="//main/.//span[contains(@Class,'live-circle__LiveCircle')]/following::p[1]") WebElement liveRateInUnit;
	
	@FindBy(css = "g.recharts-layer.recharts-cartesian-axis.recharts-xAxis.xAxis > g > g:nth-child(1) > text > tspan") WebElement firstItemInXAxisLegand;
	
	@FindBy(css = "g.recharts-layer.recharts-cartesian-axis.recharts-xAxis.xAxis > g > g:last-child > text > tspan") WebElement lastItemInXAxisLegand;
	
	public ChartTab() {
		PageFactory.initElements(driver, this);
	}
	
	@Step("Is Chart Tab Loaded")
	public boolean waitTillChartTabLoading() {
		activeScreen=this.getClass().getName();
		try{
			driverWait.until(ExpectedConditions.visibilityOf(selectedFromCurrency));
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	@Step("Get selected from currency")
	public String getFromCurrencyListValue() {
		return selectedFromCurrency.getText();
		
	}
	
	@Step("Get selected to currency")
	public String getToCurrencyListValue() {
		return selectedToCurrency.getText();
	}
	
	@Step("Click on view chart")
	public void clickOnViewChart() {
		viewChart.click();
	}
	
	@Step("Wait for the chart to load")
	public boolean waitForChartToLoad() {
		try{
			driverWait.until(ExpectedConditions.visibilityOf(chartTopTitle));
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	@Step("get chart top title")
	public String getChartTopTitle() {
		return chartTopTitle.getText();
	}
	
	@Step("get chart second title")
	public String getChartSecondTitle() {
		return chartSecondTitle.getText();
	}
	
	@Step("get live unit rate")
	public String getLiveUnitRate() {
		return liveRateInUnit.getText();
	}
	
	@Step("get 1st X-Axis Legend In Chart")
	public String get1stXAxisLegendInChart() {
		return firstItemInXAxisLegand.getText();
	}
	
	@Step("get last X-Axis Legend In Chart")
	public String getLastXAxisLegendInChart() {
		return lastItemInXAxisLegand.getText();
	}
}
