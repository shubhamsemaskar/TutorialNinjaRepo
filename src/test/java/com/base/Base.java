
package com.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import qa.utils.Utils;

public class Base {

	WebDriver driver;
	public Properties prop;
	public Properties dataProp;
	public  Base()  {
	
	
	 prop=new Properties();
	
	File propFile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\config\\config.properties");
	
	dataProp =new Properties();
	File dataPropFile=new File (System.getProperty("user.dir")+"\\src\\main\\java\\qa\\testdata\\testdata.properties");
	
	try {
	FileInputStream dataFis=new FileInputStream(dataPropFile);
	dataProp.load(dataFis);
	}catch(Throwable  e) 
	{
		e.printStackTrace();
		
	}
	
	try {
	FileInputStream fis= new FileInputStream(propFile);
	prop.load(fis);
	}catch(Throwable  e) 
	{
		e.printStackTrace();
		
	}
	}
	
	
	
	public WebDriver initializeBrowserAndOpenApplicationUrl(String browserName) {
		

		
		if(browserName.equalsIgnoreCase("chrome"))
		{
			driver= new ChromeDriver();
			
		}else if(browserName.equalsIgnoreCase("edge")) {
			driver= new EdgeDriver();
		}
		
		
		 driver =new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utils.IMPLICIT_WAIT_TIME));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utils.PAGE_WAIT_TIME));
			driver.get("https://tutorialsninja.com/demo/");
			driver.get(prop.getProperty("url"));
			return driver;
	}
	

}
