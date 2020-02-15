package com.qa.hubspot.pages;

import io.qameta.allure.Step;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.utils.AppConstantValues;
import com.qa.hubspot.utils.ElementUtil;
import com.qa.hubspot.utils.JavaScriptUtil;

public class ContactsPage extends BasePage{

	WebDriver driver;
	ElementUtil elementUtil;
	JavaScriptUtil jsUtil ;
	
	
	public ContactsPage (WebDriver driver){
		this.driver=driver;
		elementUtil = new ElementUtil(driver);
		jsUtil = new JavaScriptUtil(driver);
	}
	
	
	By createContactBtn = By.xpath("//button[contains(@class,'-primary private-button-')]");
	By createContactsFormBtn = By.xpath("//button[contains(@class,'private-button--primary private-button--non-link')]");
	
	////button[contains(@class,'private-button--primary private-button--non-link')]/span
	By email = By.cssSelector("input#UIFormControl-7");
	By firstName = By.cssSelector("input#UIFormControl-8");
	By lastName = By.cssSelector("input#UIFormControl-10");
	By jobTitle = By.cssSelector("input#UIFormControl-14");
	
	By aboutThisContactText= By.xpath("//span[@class='word-break-break-word']//i18n-string[text()='About this contact']");
	
	//delete contacts process
	
	By contactList = By.xpath("//td[@class='Td__StyledTd-sc-17tmnn9-0 iuMTEM']/div/a/span/span");
	By deleteBtn = By.xpath("//button[contains(@class,'private-button')]//span[text()='Delete']");
	By numOfContactsToDeleteTextBox= By.xpath("//textarea[starts-with(@id,'UIFormControl-')]");
	By deleteBtnOnTextBox = By.xpath("//button[contains(@class,'private-button--destructive')]/span[text()='Delete']");
	
	public String getContactsPageTitle(){
		elementUtil.waitForTitlePresent(AppConstantValues.CONTACTS_PAGE_TITLE);
		return elementUtil.doGetPageTitle();
	}
	
	//@Step("Create new contacts with {0},{1},{2},{3}")
	public void createContacts(String mail, String fName, String lName, String jobtitle){
		
		elementUtil.waitForElementPresent(createContactBtn);
		elementUtil.doClick(createContactBtn);
		
		elementUtil.waitForElementPresent(email);
		elementUtil.doSendKeys(email, mail);
		
		
		elementUtil.doSendKeys(firstName, fName);
		
		elementUtil.doSendKeys(lastName, lName);
		
		elementUtil.doSendKeys(jobTitle	, jobtitle);
		
		
		//elementUtil.doClick(createContactsFormBtn);
		elementUtil.waitForElementPresent(createContactsFormBtn);
		jsUtil.clickElementByJS(elementUtil.getElement(createContactsFormBtn));
		
		
	}
	

	public String checkAboutThisContactText(){
		elementUtil.waitForElementPresent(aboutThisContactText);
		return elementUtil.doGetText(aboutThisContactText);
	}
	
	//UseCase: Go to Contacts page 
	//Select contacts
	//Delete Contacts
	
	public void deleteContact( String... contactName) {
	 
		elementUtil.waitForElementPresent(contactList);
		List<WebElement> list = elementUtil.getListOfElements(contactList);
		
		Integer contactsCount = contactName.length;
		
			for(int i=0; i<list.size();i++){
				String text = list.get(i).getText();
				System.out.println(text);
				
				for(int j=0; j<contactsCount;j++){
					
					if(text.equals(contactName[j])){
						
						String xpath = "//span[text()='"+contactName[j]+"']/parent::span/parent::a/parent::div"
								+ "//parent::td/preceding-sibling::td//span[@class='private-checkbox__inner']";
						By selectCheckboxes = By.xpath(xpath);
						elementUtil.doClick(selectCheckboxes);
						
						break;
					}
					
				}
				
			}
			
			elementUtil.waitForElementPresent(deleteBtn);
			elementUtil.doClick(deleteBtn);
			
			elementUtil.waitForElementPresent(numOfContactsToDeleteTextBox);
			elementUtil.doSendKeys(numOfContactsToDeleteTextBox, contactsCount.toString());
			
			elementUtil.doClick(deleteBtnOnTextBox);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
		
	
	
}
