package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.AppConstantValues;
import com.qa.hubspot.utils.ElementUtil;

public class HomePage extends BasePage {
	WebDriver driver;
	ElementUtil elementUtil;

	By header = By.cssSelector("h1.private-page__title");
	By accountName = By.cssSelector("span.account-name ");

	By contactsMenu =By.id("nav-primary-contacts-branch");
	By contactsSubMenu= By.id("nav-secondary-contacts");
	
	public HomePage(WebDriver driver){
		this.driver=driver;
		elementUtil = new ElementUtil(driver);
	}

	public String getHomePageTitle(){
		elementUtil.waitForTitlePresent(AppConstantValues.HOME_PAGE_TITLE);
		return elementUtil.doGetPageTitle();
	}

	public String getHomePageHeader(){
		return elementUtil.doGetText(header);
	}

	public String getLoggedInUserName(){
		return elementUtil.doGetText(accountName);
	}


	public ContactsPage goToContactsPage(){
		elementUtil.waitForElementPresent(contactsMenu);
		elementUtil.doClick(contactsMenu);
	
		elementUtil.waitForElementPresent(contactsSubMenu);
		elementUtil.doClick(contactsSubMenu);
		
		return new ContactsPage(driver);
	}


	
	
	
	
	
	



}
