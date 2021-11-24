package org.xe.conversion.uat.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.xe.conversion.uat.base.TestBase;

import io.qameta.allure.Step;

public class HomePage extends TestBase{	
	@FindBy(xpath = "//div[contains(@class, 'hero-container__HeadingContainer') and ./h1[contains(.,\"The World's Trusted Currency Authority\")]]") WebElement xeHomePageHeading;
	
	@FindBy(xpath ="//span[@class='tab-text' and .='Charts']/parent::a") WebElement ChartTab;
	
	@FindBy(xpath ="//span[@class='tab-text' and .='Convert']/parent::a") WebElement ConvertTab;
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	@Step("Wait for XE Application page is getting opened")
	public boolean waitForHomePageToLoad() {
		activeScreen=this.getClass().getName();
		driverWait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
		try{
			driverWait.until(ExpectedConditions.visibilityOf(xeHomePageHeading));
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	
	@Step("navigate to chart tab")
	public void navigateToChartTab() {
		activeScreen=this.getClass().getName();
		driverWait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
		ChartTab.click();
	}
	
	
	
	@Step("navigate convert tab")
	public void navigateToConvertTab() {
		activeScreen=this.getClass().getName();
		driverWait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
		Actions action = new Actions(driver).click(ConvertTab);
		action.build().perform();
	}
}
