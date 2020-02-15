package com.qa.hubspot.utils;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.hubspot.base.BasePage;

public class ElementUtil  {

	WebDriver driver;
	WebDriverWait wait;
	BasePage basePage;
	Properties prop;
	JavaScriptUtil jsUtil; 
	
	public ElementUtil(WebDriver driver){
		//prop= super.prop;
		this.driver=driver;
		wait = new WebDriverWait(driver, AppConstantValues.DEFAULT_TIMEOUT);
		basePage = new BasePage();
		prop = basePage.initProperties();
		jsUtil = new JavaScriptUtil(driver);
	}
	
	public String doGetPageTitle(){
		try{
		return driver.getTitle();
		}catch(Exception e){
			System.out.println("Some exception got occured while getting the title...");
		}
		
		return null;
	}
	/**
	 * This method is used to wait explicitly for element to be present on the page.
	 * @param locator
	 * @return true if element is present.
	 */
	 public boolean waitForElementPresent(By locator){
		 wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		 return true;
	 
	 }
	 
	 /**
		 * This method is used to wait explicitly for element to be visible on the page.
		 * @param locator
		 * @return true if element is visible.
		 */
	 public boolean waitForElementVisible(By locator){
		 wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		 return true;
	 }
	 
	 /**
		 * This method is used to wait explicitly for title to be present on the page.
		 * @param String title
		 * @return true if title is present.
		 */
	 public Boolean waitForTitlePresent(String title){
		 wait.until(ExpectedConditions.titleIs(title));
		 return true;
	 }
	 
	 
	 /**
		 * This method is used to wait explicitly for url to be present on the page.
		 * @param String url
		 * @return true if url is present.
		 */
	 
	 public Boolean waitForUrlPresent(String url){
		 wait.until(ExpectedConditions.urlToBe(url));
		 return true;
	 }

	/**
	 * This method is used to create web element on the basis of By locator.
	 * @param locator
	 * @return WebElement
	 */
	public WebElement getElement(By locator){
		WebElement element =null;
		try{
			
		//	if(waitForElementPresent(locator));//wait for element to be present on the page then u create element
			
			element = driver.findElement(locator);
//			if(highligtElement){
//				jsUtil.flash(element);
//			}
			if(prop.getProperty("highlight").equals("yes")){
				jsUtil.flash(element);
			}
		}catch(Exception e){
			System.out.println("Some exception got occured while creating the web element...");
		}
		return element;
	}
	/**
	 * This method is used to click on web element using By locator.
	 * @param locator
	 */
	public void doClick(By locator){
		try{
		getElement(locator).click();
		}catch(Exception e){
			System.out.println("Some exception got occured while clicking on the web element...");
		}
	}
	
	/**
	 * This method is used to send keys.
	 * @param locator
	 * @param value
	 */
	public void doSendKeys(By locator, String value){
		 
		try{
		WebElement ele = getElement(locator);
		ele.clear();
		ele.sendKeys(value);
		}catch(Exception e){
			System.out.println("Some exception got occured while entering the values in field...");
		}
		
	}
	/**
	 * This method is used to check if element is displayed or not.
	 * @param locator
	 * @return
	 */
	
	public boolean doIsDisplayed(By locator){
		try{
		return getElement(locator).isDisplayed();
		}catch(Exception e){
			System.out.println("Some exception got occured ...");
		}
		return false;
	}
	/**
	 * This method is used to get the text of webelement.
	 * @param locator
	 * @return String 
	 */
	
	public String doGetText(By locator){
		try{
			return getElement(locator).getText();
		}catch(Exception e){
			System.out.println("Some exception got occured while getting the text from a webelement...");
		}
		
		return null;
	}
	
	/**
	 * This method is used to get the List<WebElement>.
	 * @param locator
	 * @return List<WebElement>
	 */
	public List<WebElement> getListOfElements(By locator){
		List<WebElement> list = driver.findElements(locator);
		return list;
	}
	
	/**
	 * This method is used to select the option/options from drop down:
	 * 1. Single selection
	 * 2. Multi selection
	 * 3. All selection : Pass "all" or "All" or "ALL" as a value parameter.
	 * 
	 * @param By locator
	 * @param String... value
	 */
	public void selectMultipleOptionsFromDropDown(By locator,String...value) {
		List<WebElement> list = getListOfElements(locator);

		if(! value[0].equalsIgnoreCase("all")){
			for(int i=0; i<list.size();i++){
				String text = list.get(i).getText();

				for(int j=0; j<value.length;j++){

					if(text.equals(value[j])){
						list.get(j).click();
						break;
					}
				}
			}

		}else{
			for(int all=0; all<list.size();all++){
				list.get(all).click();
			}
		}



	}


	
	
	
	
	
}
