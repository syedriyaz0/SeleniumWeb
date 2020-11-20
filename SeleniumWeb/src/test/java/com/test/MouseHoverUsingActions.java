package com.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MouseHoverUsingActions {

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
	public void mouseHover() {
		driver.get("https://s1.demo.opensourcecms.com/wordpress/wp-login.php");
		//creating actions class object and at end always use .build() .perform() methods 
		Actions action = new Actions(driver);
		WebElement userName = driver.findElement(By.id("user_login"));
		WebElement password = driver.findElement(By.id("user_pass"));
		WebElement submit = driver.findElement(By.id("wp-submit"));
		action.sendKeys(userName, "opensourcecms").build().perform();
		action.sendKeys(password, "opensourcecms").build().perform();
		action.click(submit).build().perform();
		
		WebElement dashboard = driver.findElement(By.linkText("Dashboard"));
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(dashboard));
	
		WebElement menuHover= driver.findElement(By.id("wp-admin-bar-my-account"));
		action.moveToElement(menuHover).build().perform();
		
		WebElement logOut = driver.findElement(By.xpath("//li[@id='wp-admin-bar-logout']/a[@class='ab-item' ]"));
		wait.until(ExpectedConditions.visibilityOf(logOut));
		action.click(logOut).build().perform();
		
	}
	
	
}
