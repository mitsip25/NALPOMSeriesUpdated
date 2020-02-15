package com.qa.hubspot.testcases;


import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.listeners.TestAllureListener;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.utils.AppConstantValues;
import com.qa.hubspot.utils.Credentials;
@Listeners({TestAllureListener.class})
@Epic("Epic - 102 :Create Home page features")
@Feature("US - 502 : Create test for home page on hubspot")
public class HomePageTest {

	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	HomePage homepage;
	Credentials userCred;

	@BeforeTest(alwaysRun=true)
	@Parameters(value= {"browser"})
	public void setUp(String browser){

		basePage = new BasePage();
		prop = basePage.initProperties();
		//for parallel execution
		String browserName=null;
		
		if(browser.equals(null)){
			browserName = prop.getProperty("browser");
		}else{
			browserName= browser;
		}
		
		driver  = basePage.initDriver(browserName);
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		//we have to log in into application to proceed for homepage or some other pages.
		homepage = loginPage.doLogin(userCred);

	}

	@Test(priority=1,groups="sanity")
	@Description("Verify home page title test.")
	@Severity(SeverityLevel.NORMAL)
	public void verifyHomePageTitleTest(){
		String title = homepage.getHomePageTitle();
		System.out.println("home page title is :" +title);
		Assert.assertEquals(title, AppConstantValues.HOME_PAGE_TITLE);
	}

	@Test(priority=2)
	@Description("Verify home page header test.")
	@Severity(SeverityLevel.NORMAL)
	public void verifyHomePageHeaderTest(){
		String header = homepage.getHomePageHeader();
		System.out.println("home page header is : " +header);
		Assert.assertEquals(header, AppConstantValues.HOME_PAGE_HEADER);
	}

	@Test(priority=3)
	@Description("Verify logged in username test.")
	@Severity(SeverityLevel.BLOCKER)
	public void verifyLoggedInUserNameTest(){
		String user = homepage.getLoggedInUserName();
		System.out.println("logged in user name :" +user);
		Assert.assertEquals(user, AppConstantValues.USER_ACCOUNTNAME);
	}


	@AfterTest(alwaysRun=true)
	public void tearDown(){
		driver.quit();
	}
















}
