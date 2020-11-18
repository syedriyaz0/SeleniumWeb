package com.pageObjectFactory;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


public class PageObjectFactoryBootStrap {

	WebDriver driver;
	
	//Constructor with page factory
	public PageObjectFactoryBootStrap(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	
	@FindBy(xpath="//button[@id='bootstrapmenu']")
	public WebElement clickBtn;
	
	@FindBy(how=How.XPATH, using = "//h3[contains(text(),'CONTACT US')]")
	public WebElement contactUs;
	
	@FindBy(how=How.XPATH, using = "//ul[@class='dropdown-menu']//li")
	public List<WebElement> dropDownList;
	
	
	
	
	
	
}
