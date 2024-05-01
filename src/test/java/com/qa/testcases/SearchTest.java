package com.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.base.Base;
//updated ciooo
public class SearchTest extends Base {
	public SearchTest() {
		super();
	}

	public WebDriver driver;
	
	
	@BeforeMethod
	public void setup() {
		driver=initializeBrowserAndOpenApplicationUrl(prop.getProperty("browserName"));
		
		
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifySearchWithValidProduct() {
	 
		driver.findElement(By.name("search")).sendKeys("HP");;
		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		
		Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed());
		
	}
	
	@Test(priority=2)
	public void verifySearchWithInValidProduct() {
	 
		driver.findElement(By.name("search")).sendKeys("Honda");;
		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		
		String acutalSearchMessage=driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
		Assert.assertEquals(acutalSearchMessage,"There is no product that matches the search criteria.","No product in search result is not displayed");
	}
		@Test(priority=3)
		public void verifySearchWithoutAnyProduct() {
			driver.findElement(By.name("search")).sendKeys("");;
			driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
			
			String acutalSearchMessage=driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
			Assert.assertEquals(acutalSearchMessage,"There is no product that matches the search criteria.","No product in search result is not displayed");
			
			
			
			
		}
		
		
	
	
	
}
