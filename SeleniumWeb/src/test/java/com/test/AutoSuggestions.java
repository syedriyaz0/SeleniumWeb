package com.test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AutoSuggestions {

	
WebDriver driver;
	
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
	public void autoSuggestionList() throws InterruptedException {
		driver.get("http://www.google.com");
		
		WebElement search= driver.findElement(By.name("q"));
		search.sendKeys("selenium"); //searching
		
		List<WebElement> searchSuggestion=driver.findElements(By.xpath("//ul/li[@class='sbct']")); //storing auto suggestion in list
		
		for(WebElement eleList : searchSuggestion) {
			String suggestionText = eleList.getText();
			System.out.println(suggestionText);
			Thread.sleep(2000);
			if(suggestionText.contains("selenium testing")) {
				eleList.click();
				break;
			}
			
		}
		
	}
	
}
