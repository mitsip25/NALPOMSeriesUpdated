package com.qa.hubspot.pages;

import io.qameta.allure.Step;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.AppConstantValues;
import com.qa.hubspot.utils.Credentials;
import com.qa.hubspot.utils.ElementUtil;
import com.qa.hubspot.utils.JavaScriptUtil;

public class LoginPage extends BasePage{

	WebDriver driver;
	ElementUtil elementUtil;
	JavaScriptUtil jsUtil;
	
	//1. locators : by
		By emailId = By.id("username");
		By password = By.id("password");
		By loginBtn = By.id("loginBtn");
		By signUpLink = By.linkText("Sign up");
		By dontHaveAccText = By.xpath("//div[@class='signup-link']/i18n-string");
		By loginErrorText = By.cssSelector("h5.private-alert__title"); 
		By accountName = By.cssSelector("span.account-name");
		
	public LoginPage(WebDriver driver){
		this.driver= driver;
		elementUtil = new ElementUtil(driver);
		jsUtil = new JavaScriptUtil(driver);
	}
	
	//page Actions
	public String getPageTitle(){
		elementUtil.waitForTitlePresent(AppConstantValues.LOGIN_PAGE_TITLE);
		return elementUtil.doGetPageTitle();
	}
	
	public String getPageTitleUsingJS(){
		elementUtil.waitForTitlePresent(AppConstantValues.LOGIN_PAGE_TITLE);
		return jsUtil.getTitleByJS();
	}
	
	public boolean checkSignUpLink(){
		elementUtil.waitForElementPresent(signUpLink);
		return elementUtil.doIsDisplayed(signUpLink);
		
	}
	
	
	public HomePage doLogin(Credentials userCred){
		elementUtil.waitForElementPresent(emailId);
		elementUtil.doSendKeys(emailId, userCred.getAppUsername());
		elementUtil.doSendKeys(password, userCred.getAppPassword());
		elementUtil.doClick(loginBtn);
		
		elementUtil.waitForElementPresent(accountName);
		
		return new HomePage(driver);
	}
	

	
	public String checkDontHaveAnAccountText(){
		return elementUtil.doGetText(dontHaveAccText);
	}
	
	public boolean checkLoginErrorMessage(){
		return elementUtil.doIsDisplayed(loginErrorText);
	}

	
	
}
