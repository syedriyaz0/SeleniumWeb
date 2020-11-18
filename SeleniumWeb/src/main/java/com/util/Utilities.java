package com.util;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.MediaEntityBuilder;


import resources.Base;



public class Utilities {

	public WebDriver driver=Base.driver;
	
	//constructor
	public Utilities(WebDriver driver) {
		this.driver=driver;
	}
	
	
	public void getExplicitWaitTime(WebElement locator, int seconds) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy((By) locator));
		
	}
	
	public void getVerticalScroll(int horizontal, int vertical ) throws Exception {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scroll("+horizontal+","+vertical+"");
	}
	
	public void getScrollToXpathElement(WebElement locator, String eleName) throws Exception {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true)",locator);
		} 
		
	
	
	
	/**
	 * Used to navigate to application
	 * @param url-- Application URL
	 */
	public void navigateToApplication(String url) {
		
		try {
			driver.get(url);
			Base.childTest.pass("Navigated to URL: "+url);
		}catch(Exception e) {
			Base.childTest.fail("Unable to navigate to URL: "+url);
		}
		
	}
	
	/**
	 * Used perform click on buttons, links, radio buttons, check boxes action and with screen shot on fail to report 
	 * @param locator -- Declare element here, get it from Object Repository
	 * @param eleName -- It will mention element Name in reports
	 */
	//Click Action method with screen shot on test fail
	public void click(By locator, String eleName) throws Exception {
		try {
			driver.findElement(locator).click();
			Base.childTest.pass("Performed click action on :"+eleName);
		} catch (Exception e) {
			Base.childTest.fail("Unable to perform click action on :"+eleName,
				MediaEntityBuilder.createScreenCaptureFromBase64String(screenShot()).build());//screenShot parameter is from below string screenshot method
			//Below will return the info of testCase failure
			Base.childTest.info(e);
			throw e;
		}
	}
	
	/**
	 * Used to perform type action in text boxes and in text area
	 * @param locator -- Get it from Object repository
	 * @param testData -- HardCode or Get it form Excel Sheet
	 * @param eleName -- Name of the element
	 * @throws Exception
	 */
	//Type Action method with screen shot on test fail
	public void type(By locator, String testData, String eleName) throws Exception {
		try {
			driver.findElement(locator).sendKeys(testData);
			Base.childTest.pass("Performed type action on: "+eleName+" With Data : "+testData);
		} catch (Exception e) {
			Base.childTest.fail("Unable to perform type action on :"+eleName +" With Data: "+testData,
					MediaEntityBuilder.createScreenCaptureFromBase64String(screenShot()).build());//screenShot parameter is from below string screenshot method
			//Below will return the info of testCase failure
			Base.childTest.info(e);
			throw e;
		}
	}
	
	/**
	 * use to perform mouse hover
	 * @param locator -- Get it from Object repository
	 * @param eleName -- Name of the element
	 * @throws Exception
	 */
	//Mouse hovering method
	public void mouseHover(By locator, String eleName) throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			Actions a= new Actions(driver);
			WebElement we=driver.findElement(locator);
			a.moveToElement(we).perform();
			Base.childTest.pass("Performed mouse hover action on: "+eleName);
		} catch (Exception e) {
			Base.childTest.fail("Unable to mouse hover action on :"+eleName ,
					MediaEntityBuilder.createScreenCaptureFromBase64String(screenShot()).build());//screenShot parameter is from below string screenshot method
			//Below will return the info of testCase failure
			Base.childTest.info(e);
			throw e;
		}
		
	}
	
	//This will take the screen shot and return it
	public String screenShot() {
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
		
	}


	
	
}
