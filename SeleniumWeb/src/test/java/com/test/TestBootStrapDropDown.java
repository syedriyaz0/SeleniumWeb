package com.test;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.pageObjectFactory.PageObjectFactoryBootStrap;
import com.util.Utilities;

import resources.Base;



public class TestBootStrapDropDown extends Base {

	
	@Test
	public void bootStrapDropDown() throws Exception {
		
		PageObjectFactoryBootStrap pbs = new PageObjectFactoryBootStrap(driver);
		Utilities util = new Utilities(driver);
		
		util.getScrollToXpathElement(pbs.clickBtn, "Boot strap button");
		pbs.clickBtn.click();
		util.getExplicitWaitTime(pbs.clickBtn, 10);
		
		for(WebElement ele : pbs.dropDownList) {
			Thread.sleep(2000);
			String listName=ele.getText();
			System.out.println(listName);
			if (listName.equalsIgnoreCase("Contact us")) {
				ele.click();
				break;
			}
		}
		
		util.getScrollToXpathElement(pbs.contactUs, "Contact us");
		driver.navigate().back();
	}
	
}
