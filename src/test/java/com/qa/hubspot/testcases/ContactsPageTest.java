package com.qa.hubspot.testcases;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

import java.util.ArrayList;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.listeners.TestAllureListener;
import com.qa.hubspot.pages.ContactsPage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.utils.AppConstantValues;
import com.qa.hubspot.utils.Credentials;
import com.qa.hubspot.utils.ExcelUtil;


//@Listeners({TestAllureListener.class})
@Epic("Epic - 103 :Create Contacts page features")
@Feature("US - 503 : Create test for contacts page on hubspot")
public class ContactsPageTest {
	
	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	Credentials userCred;
	HomePage homePage;
	ContactsPage contactsPage; 
	
	
	@BeforeMethod
	@Parameters(value= {"browser"})
	public void setUp(String browser){
		
		basePage = new BasePage();
		prop = basePage.initProperties();
		String browserName=null;
		if(browser.equals(null)){
			browserName =prop.getProperty("browser");
		}else{
			browserName = browser;
		}

		driver =basePage.initDriver(browserName);
		driver.get(prop.getProperty("url"));
		
		loginPage= new LoginPage(driver);
		userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		homePage = loginPage.doLogin(userCred);
		contactsPage =homePage.goToContactsPage();

	}
	
	@Test(priority=1)
	@Description("Verify contact page title test.")
	@Severity(SeverityLevel.NORMAL)
	public void verifyContactsPageTitle(){
		String title =contactsPage.getContactsPageTitle();
		System.out.println("Contacts page title: "+title);
		Assert.assertEquals(title, AppConstantValues.CONTACTS_PAGE_TITLE);
	
	}
	
	
	@DataProvider
	public Object[][] getContactsTestData(){
		Object[][] data = ExcelUtil.getTestData(AppConstantValues.CONTACTS_SHEET_NAME);
		return data;
	
	}
	
	
	@Test(priority=2, dataProvider="getContactsTestData")
	@Description("Create contacts test.")
	@Severity(SeverityLevel.CRITICAL)
	public void createContactsTest(String email, String firstname, String lastname, String jobtitle){
		
		contactsPage.createContacts(email,firstname,lastname,jobtitle);
		
		//contactsPage.createContacts("arey@gmail.com","arey","maan","labor");
		
		String aboutThisContactText = contactsPage.checkAboutThisContactText();
		System.out.println("Text checked :: "+aboutThisContactText);
		Assert.assertEquals(aboutThisContactText, AppConstantValues.CONTACTS_TEXT);
		
	}
	
	@Test(priority=3,enabled = false)
	@Description("Delete contacts test.")
	@Severity(SeverityLevel.CRITICAL)
	public void deleteContactTest(){
		
		contactsPage.deleteContact("y x");


	}
	

	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
	
	
	
	
	
}
