package com.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AlertsHandling {

	
public WebDriver driver;
	
//JavaScript alerts has 3 types 1)simple alert, 2)confirmation alert, 3)prompt alert
	
	@BeforeMethod
	public void setup( ) {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	//Simple Alert
	@Test
	public void simpleAlert() throws InterruptedException {
		driver.get("https://www.automationtestinginsider.com/2019/08/textarea-textarea-element-defines-multi.html");
		
		//Simple Alert
		WebElement simpleAlert = driver.findElement(By.id("simpleAlert"));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView(true)",simpleAlert);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(simpleAlert));
		simpleAlert.click();
		//creating alert instance variable
		Alert alert=driver.switchTo().alert();
		String simpleAlertText = alert.getText();
		System.out.println("The alert text: "+simpleAlertText);
		alert.accept();
	}
	
	//Confirmation alert
	@Test
	public void confirmAlert() {
		driver.get("https://www.automationtestinginsider.com/2019/08/textarea-textarea-element-defines-multi.html");
		WebElement confirmationAlert = driver.findElement(By.id("confirmationAlert"));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].scrollIntoView(true)",confirmationAlert);
		confirmationAlert.click();
		Alert alert=driver.switchTo().alert();
		String confirmationAlertText = alert.getText();
		System.out.println("The alert text: "+confirmationAlertText);
		alert.accept();
	}
	
	//Prompt Alert
		@Test
		public void promptAlert() throws InterruptedException {
			driver.get("https://www.automationtestinginsider.com/2019/08/textarea-textarea-element-defines-multi.html");
			Thread.sleep(3000);
			WebElement promptAlert = driver.findElement(By.id("promptAlert"));
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("arguments[0].scrollIntoView(true)",promptAlert);
			Thread.sleep(2000);
			promptAlert.click();
			Alert alert=driver.switchTo().alert();
			
			String promptAlertText = alert.getText();
			System.out.println("The alert text: "+promptAlertText);
			
			promptAlert.sendKeys("hello");
			Thread.sleep(3000);
			alert.accept();
		}
	
	public static boolean isAlertPresent(WebDriver aDriver) {
		try {
			Alert alert = aDriver.switchTo().alert();
			alert.accept();
			
		} catch (NoAlertPresentException e) {
			
		}
		return false;
	}
	
	
}
