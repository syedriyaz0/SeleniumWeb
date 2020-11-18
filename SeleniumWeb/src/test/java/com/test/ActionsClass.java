package com.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ActionsClass  {
	
	public WebDriver driver;
	public String url = "http://www.google.com";
	
	@BeforeClass
	public void setup( ) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(url);
	}
	
	
	
	@Test(priority=1, groups= {"Action"})
	public void searchWithKeyboardAction( ) throws InterruptedException {
		
		//creating actions class object
		Actions action = new Actions(driver);
		WebElement search=driver.findElement(By.name("q"));
		action.keyDown(search,Keys.SHIFT)
				.sendKeys("selenium")
				.keyUp(search, Keys.SHIFT)
				.keyDown(search, Keys.CONTROL).sendKeys("x")
				.keyDown(search, Keys.CONTROL).sendKeys("v")
				.build()
				.perform();
		
		Thread.sleep(4000);
		driver.close();
		
	}
	
	
	@Test(priority=2)
	public void mouseActions() {
		//creating actions class object
		Actions action = new Actions(driver);
		
		
		
	}
	
	
	//@Test(groups={"ChromeBrowserSpeedTest"})
	public void actionClass() {
		
		WebElement search=driver.findElement(By.name("q"));
		search.sendKeys("Speed test".toUpperCase());
		search.sendKeys(Keys.ENTER);
		
		WebElement speedBtn=driver.findElement(By.xpath("//div[contains(text(),'RUN SPEED TEST')]"));
		speedBtn.click();
		
		WebElement testAgainBtn=driver.findElement(By.xpath("//div[contains(text(),'TEST AGAIN')]"));
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(testAgainBtn));
		
		WebElement closeBtn = driver.findElement(By.xpath("//div[@class='bYhoud']/child::*[2]"));
		try {
			closeBtn.click();
			System.out.println("Successfully closed");
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			System.out.println("Unable to close the pop up");
		}
		
		
	}

}
