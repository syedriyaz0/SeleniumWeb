package com.test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.objectRepository.BootStrapObjects;
import com.util.Utilities;

import resources.Base;



public class TestBootStrapDropDown extends Base {

	
	@Test
	public void bootStrapDropDown() throws Exception {
		
		
		Utilities util = new Utilities(driver);
		WebElement clickBtn=driver.findElement(By.xpath("//button[@id='bootstrapmenu']"));
		util.getScrollToXpathElement(clickBtn, "Boot strap button");
		clickBtn.click();
		
		
		List<WebElement> dropDownList=driver.findElements(By.xpath("//ul[@class='dropdown-menu']//li"));
		
		for(WebElement ele : dropDownList) {
			String listName=ele.getText();
			System.out.println(listName);
			
			if (listName.equalsIgnoreCase("contact us")) {
				ele.click();
				break;
			}
		}
		WebElement contactUs= driver.findElement(By.xpath("//h3[contains(text(),'CONTACT US')]"));
		util.getScrollToXpathElement( contactUs, "Contact us");
		driver.navigate().back();
	}
	
}
