package com.inetBankingV1.testCases;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetBankingV1.utilities.ReadConfig;

public class BaseClass {

	ReadConfig readconfig = new ReadConfig();
	public static WebDriver driver;
	public String baseurl= readconfig.getApplicationURL();
	String username= readconfig.getUsername();
	String password = readconfig.getPassword();
	public static Logger logger;
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String br){
		
		logger=Logger.getLogger("eBanking");
		PropertyConfigurator.configure("log4j.properties");
		
		if(br.equals("chrome"))
		{
		System.setProperty("webdriver.chrome.driver", readconfig.getChromePath());
		driver = new ChromeDriver();
		logger.info("Lauching chrome browser");
		}
		else if(br.equals("firefox"))
		{
		System.setProperty("webdriver.gecko.driver", readconfig.getFirefoxPath());
		driver = new FirefoxDriver();
		logger.info("Launching firefox browser");
		}
		else if(br.equals("ie"))
		{
		System.setProperty("webdriver.ie.driver", readconfig.getIEPath());
		driver = new InternetExplorerDriver();
		logger.info("launching ie browser");
		}
		
		logger.info("Driver is intantiated");
		
		
	}
	
	
	@AfterClass
	public void tearDown(){
		driver.quit();
		logger.info("Driver is closed");
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir")+"/Screenshots/" +tname +".png");
		FileUtils.copyFile(source,target);
		System.out.println("Screenshot taken");
		
	}
}
