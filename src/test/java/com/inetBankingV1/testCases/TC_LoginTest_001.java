package com.inetBankingV1.testCases;

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetBankingV1.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass{
	
	 
	@Test
	public void loginTest() throws IOException{
		driver.get(baseurl);
		logger.info("URL is opened");
		LoginPage lp = new LoginPage(driver);
		logger.info("Username is" +username);
		lp.setUserName(username);
		logger.info("Username is entered");
		lp.setPassword(password);
		logger.info("Password is entered");
		lp.clickSubmit();
		logger.info("login button clicked");
		
		if(ExpectedConditions.alertIsPresent() != null){
			
			Alert alt = driver.switchTo().alert();
			alt.accept();
			logger.info("Alert is pesent and dissmissed");
		}
		
		if(driver.getTitle().equals("GTPL Bank Home Page"))
		{
			Assert.assertTrue(true);
			logger.info("Login Test is passed");
		}
			
		else
		{
			captureScreen(driver, "loginTest");
			Assert.assertTrue(false);
			logger.info("LLogin test is failed");

		}
			
	}

}
