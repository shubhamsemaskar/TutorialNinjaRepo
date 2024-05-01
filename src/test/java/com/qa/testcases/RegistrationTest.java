package com.qa.testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.base.Base;

import qa.utils.Utils;

public class RegistrationTest extends Base {
	public RegistrationTest() {
		super();
	
}
	
	
	public WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		

		driver=initializeBrowserAndOpenApplicationUrl(prop.getProperty("browserName"));
			driver.findElement(By.xpath("//span[text()='My Account']")).click();
			driver.findElement(By.linkText("Register")).click();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	
	
	@Test(priority=1)
	public void verifyRegisteringAnAccountWithMandatoryFields() {
	
	driver.findElement(By.id("input-firstname")).sendKeys("shubham");
	driver.findElement(By.id("input-lastname")).sendKeys("semaskar");
	driver.findElement(By.id("input-email")).sendKeys(Utils.generateEmailWithTimeStamp());
	driver.findElement(By.id("input-telephone")).sendKeys("1234567");
	driver.findElement(By.id("input-password")).sendKeys("12345");
	driver.findElement(By.id("input-confirm")).sendKeys("12345");
	driver.findElement(By.name("agree")).click();
	driver.findElement(By.xpath("//input[@value='Continue']")).click();
	
	String actualSuccessHeading=driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
	Assert.assertEquals(actualSuccessHeading,"Your Account Has Been Created!","Account Success page is not displayed ");
			
	driver.quit();
	}
	
	@Test(priority=2)
	public void verifyRegisteringAccountByProvidingAllFields() {
		
		driver.findElement(By.id("input-firstname")).sendKeys("shubham");
		driver.findElement(By.id("input-lastname")).sendKeys("semaskar");
		driver.findElement(By.id("input-email")).sendKeys(Utils.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("1234567");
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String actualSuccessHeading=driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		Assert.assertEquals(actualSuccessHeading,"Your Account Has Been Created!","Account Success page is not displayed ");
				
		driver.quit();
	}
	  
	@Test(priority=3)
	public void verifyRegisteringAccountWithExistingEmailAddress() {
		
		driver.findElement(By.id("input-firstname")).sendKeys("shubham");
		driver.findElement(By.id("input-lastname")).sendKeys("semaskar");
		driver.findElement(By.id("input-email")).sendKeys("semaskarshubham94@gmail.com");
		driver.findElement(By.id("input-telephone")).sendKeys("1234567");
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String actualWarning=driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		Assert.assertTrue(actualWarning.contains("Warning: E-Mail Address is already registered!"),"Warning Message regarding duplicate email is not displayed");	
		driver.quit();
		
	}
		@Test (priority=4)
		public void verifyRegisteringAccountWithoutFillingAnyDetails() {
			
			
			driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
			String actualPrivacyPolicyWarning=driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
			Assert.assertTrue(actualPrivacyPolicyWarning.contains("Warning: You must agree to the Privacy Policy!"),"Privacy Policy Warning message is not displayed");
			 
			driver.quit();
		}
		
		
	
	
	
}
