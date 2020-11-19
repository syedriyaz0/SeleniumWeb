package com.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ToolTipAction {

	
	public WebDriver driver;
	
	
	@BeforeMethod
	public void setup( ) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
	
	@Test
	public void toolTipAction() throws InterruptedException {
		driver.get("https://demoqa.com/tool-tips");
		//creating actions class object and at end always use .build() .perform() methods 
		Actions action = new Actions (driver);
		WebElement toolText=driver.findElement(By.id("toolTipButton"));
		action.moveToElement(toolText).perform();;
		Thread.sleep(3000);
		String getText=toolText.getText();//("aria-describedby");
		System.out.println(getText);
	}
	
	
}
