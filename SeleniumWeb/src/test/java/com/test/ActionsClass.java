package com.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ActionsClass  {
	
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
	
	
	
	@Test(priority=1, groups= {"Action"})
	public void searchWithKeyboardAction( ) throws InterruptedException {
		driver.get("http://www.google.com");
		//creating actions class object and at end always use .build() .perform() methods 
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
	public void mouseActions() throws InterruptedException {
		
		driver.get("https://www.automationtestinginsider.com/2019/08/textarea-textarea-element-defines-multi.html");
		
		//creating actions class object and at end always use .build() .perform() methods 
		Actions action = new Actions(driver);
		WebElement doubleClickBtn=driver.findElement(By.id("doubleClickBtn"));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true)",doubleClickBtn);
		//WebDriverWait wait = new WebDriverWait (driver, 5);
		//wait.until(ExpectedConditions.visibilityOf(doubleClickBtn));
		action.doubleClick(doubleClickBtn).build().perform(); //double click action performed
		
		driver.switchTo().alert().accept(); //alerts
		Thread.sleep(2000);
		//mouse right click
		WebElement rightClickBtn= driver.findElement(By.id("rightClickBtn"));
		action.contextClick(rightClickBtn).perform(); //contextClick is right click of mouse
		
	}
	
	@Test(priority=3)
	public void dragNDropAction() {
		
		driver.get("https://demoqa.com/droppable");
		
		//creating actions class object and at end always use .build() .perform() methods 
		Actions action = new Actions(driver);
		WebElement drag = driver.findElement(By.id("draggable"));
		WebElement drop = driver.findElement(By.id("droppable"));
		
		WebDriverWait wait = new WebDriverWait (driver, 10);
		wait.until(ExpectedConditions.visibilityOf(drop));
		
		//action.dragAndDrop(drag, drop).build().perform();
		//action.dragAndDropBy(drag, 260, 50).perform();
		
		//Drag N drop by using hold and move method of action class
		action.clickAndHold(drag).moveToElement(drop).release().build().perform();
		
		String text=driver.findElement(By.xpath("//div/p[text()='Dropped!']")).getText();
		try {
			Assert.assertEquals(text, "Dropped!");
			System.out.println("text matched: "+text);
		} catch (Exception e) {
			System.out.println("text did not matched");
		}
		
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
