package com.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.base.Base;

import qa.utils.Utils;

public class LoginTest extends Base{
	
	public LoginTest() {
		super();
	}
	
	public WebDriver driver;
	
	@BeforeMethod
	public void setup()
	{

		driver=initializeBrowserAndOpenApplicationUrl(prop.getProperty("browserName"));
		
		
		
		
			driver.findElement(By.xpath("//span[text()='My Account']")).click();
			driver.findElement(By.linkText("Login")).click();
	}
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
		
	}
	
	@Test(priority=1,dataProvider="supplyTestData")
	public void VerifyWithLoginValidCredentials(String email,String password) {
		
		
	
		driver.findElement(By.id("input-email")).sendKeys(email);
		driver.findElement(By.id("input-password")).sendKeys(password);
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed(),"Edit Your Account Information option is not displayed");
		
		
	}
	@DataProvider
	public Object[][] supplyTestData() {
		
		Object[] [] data= {{"semaskarshubham@rocketmail.com","12345"},
				{"semaskarshubham94@gmail.com","12345"},
				{"semaskarshubham@gmail.com","12345"}};
		 return data;
	}
		
		@Test(priority=2)
		public void VerifyWithLoginInvalidCredentials() {
			
			
		
			driver.findElement(By.id("input-email")).sendKeys(Utils.generateEmailWithTimeStamp());
			driver.findElement(By.id("input-password")).sendKeys(dataProp.getProperty("invalidPassword"));
			driver.findElement(By.xpath("//input[@value='Login']")).click();
			
			String actualWarningMessage= driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
			String expectedWarningMessage=dataProp.getProperty("emailPasswordNoMatchWarning");
			Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected Warning message is not displayed");
			
	}
		
		@Test(priority=3)
		public void verifyLoginWithInValidEmailAndValidPassword() {
			

			
			driver.findElement(By.id("input-email")).sendKeys(Utils.generateEmailWithTimeStamp());
			driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
			driver.findElement(By.xpath("//input[@value='Login']")).click();
			
			String actualWarningMessage= driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
			String expectedWarningMessage="Warning: No match for E-Mail Address and/or Password.";
			Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected Warning message is not displayed");
		}
		@Test(priority=4)
		public void verifyLoginWithValidEmailAndInValidPassword() {
			

			
			driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
			driver.findElement(By.id("input-password")).sendKeys(dataProp.getProperty("invalidPassword"));
			driver.findElement(By.xpath("//input[@value='Login']")).click();
			
			String actualWarningMessage= driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
			String expectedWarningMessage=dataProp.getProperty("emailPasswordNoMatchWarning");
			Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected Warning message is not displayed");
			
			
		}
		
		
			@Test(priority=5)
			public void verifyLoginWithoutProvidingCredentials() {
		
				

				
				
				driver.findElement(By.xpath("//input[@value='Login']")).click();
				
				String actualWarningMessage= driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
				String expectedWarningMessage="Warning: No match for E-Mail Address and/or Password.";
				Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected Warning message is not displayed");
				
				 		
		}
		
		
		
		
}
