package com.qa.hubspot.testcases;


import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.listeners.TestAllureListener;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.utils.AppConstantValues;
import com.qa.hubspot.utils.Credentials;

@Listeners({TestAllureListener.class})
@Epic("Epic - 101 : Create login page features")
@Feature("US - 501 : Create test for login page on hubspot")
@Story("Story: User wants to Create login page features")

public class LoginPageTest {
	
	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	Credentials userCred;
	

	@BeforeTest(alwaysRun=true)
	@Parameters(value={"browser"})
	public void setUp(String browser){
		basePage = new BasePage();
		prop = basePage.initProperties();
		//for parallel execution
		String browserName=null;
		
		if(browser.equals(null)){
			browserName = prop.getProperty("browser");
		}else{
			browserName=browser;
		}
		//String browserName=  prop.getProperty("browser");
		driver  = basePage.initDriver(browserName);
		driver.get(prop.getProperty("url"));
		
		loginPage = new LoginPage(driver);
		userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	@Test(priority=1)
	@Description("Verify login page title test.")
	@Severity(SeverityLevel.NORMAL)
	public void verifyLoginPageTitleTest(){
		
		String title = loginPage.getPageTitle();
		//String title = loginPage.getPageTitleUsingJS();
		System.out.println("login page title is " + title);
		Assert.assertEquals(title, AppConstantValues.LOGIN_PAGE_TITLE);
	}
	
	
	@Test(priority=2, groups ="sanity")
	@Description("Verify sign up link.")
	@Severity(SeverityLevel.CRITICAL)
	public void verifySignUpLink(){
		boolean b= loginPage.checkSignUpLink();
		Assert.assertTrue(b);
	}
	
	
	@Test(priority=3)
	@Description("Verify DontHaveAnAccount Text ")
	@Severity(SeverityLevel.NORMAL)
	public void verifyDontHaveAnAccountTextTest(){
		String text = loginPage.checkDontHaveAnAccountText();
		System.out.println("dont have an account text is : " + text);
		Assert.assertEquals(text, AppConstantValues.LOGIN_PAGE_DONTHAVEANACCOUNTTEXT);
	}
	


	@Test(priority=5)
	@Description("Verify Login Test")
	@Severity(SeverityLevel.BLOCKER)
	public void loginTest() {
		HomePage homepage = loginPage.doLogin(userCred);
		String accountName =homepage.getLoggedInUserName();
		
		Assert.assertEquals(accountName, AppConstantValues.USER_ACCOUNTNAME);
	
	}
	
//Negative scenario
	@DataProvider
	public Object [][] getLoginInvalidData(){
		Object [][] data = {{"test12@gmail.com", "test123"},
							{"test2@gmail.com", " "},
							{" ", "test12345"},
							{"test", "test"},
							{" ", " "}
							};
		return data;
	}
	
	
	@Test(priority=6,dataProvider = "getLoginInvalidData",enabled =false)
	@Description("Verify login test with invalid Test cases.")
	@Severity(SeverityLevel.BLOCKER)
	public void login_InvalidTestCases(String username, String password){
		userCred.setAppUsername(username);
		userCred.setAppPassword(password);
		loginPage.doLogin(userCred);
		
		Assert.assertTrue(loginPage.checkLoginErrorMessage());
		
	}
	
	
	
	@AfterTest(alwaysRun=true)
	public void tearDown(){
		driver.quit();
	}
	
	
	
	
	
	
	
	
}
