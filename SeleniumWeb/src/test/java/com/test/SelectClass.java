package com.test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SelectClass {

	
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
	public void selectFromList() throws InterruptedException {
		driver.get("https://www.seleniumeasy.com/test/");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		WebElement popUp=driver.findElement(By.xpath("//a[@title='Close']"));
		wait.until(ExpectedConditions.visibilityOf(popUp));
		
		if(popUp.isDisplayed()) {
			popUp.click();
		}
		
		List<WebElement> list = driver.findElements(By.xpath("//li/a[@class='dropdown-toggle']"));
		
		wait.until(ExpectedConditions.visibilityOf(list.get(5)));
		
		list.get(5).click();
		
		List<WebElement> dropList=driver.findElements(By.xpath("//li[@class='dropdown open']/ul"));
		
		for(WebElement eleList : dropList) {
			String listText = eleList.getText();
			System.out.println(listText);
			
				if(listText.contains("JQuery List Box")) {
					Thread.sleep(3000);
					eleList.click();
					break;
				}else {
					System.out.println("Unable to click");
				}
			}
		
		//Transition to another page : jquery list box
		
		List<WebElement> jqueryList = driver.findElements(By.xpath("//select[@class='form-control pickListSelect pickData']/option"));
		
		Actions action = new Actions(driver);
		action.keyDown(Keys.CONTROL)
			  .click(jqueryList.get(0))
			  .click(jqueryList.get(2))
			  .click(jqueryList.get(3))
			  .click(jqueryList.get(5))
			  .keyUp(Keys.CONTROL)
			  .build().perform();
		
		List<WebElement> buttonList = driver.findElements(By.xpath("//div[@class='col-sm-2 pickListButtons']/button"));
		buttonList.get(0).click(); //clicking add button
	
	}
	
}
