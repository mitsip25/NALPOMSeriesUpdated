package com.qa.hubspot.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {

	WebDriver driver;
	JavascriptExecutor js;
	//ElementUtil elementUtil; 
	public JavaScriptUtil(WebDriver driver){
		this.driver = driver;
		js = (JavascriptExecutor) driver;
		//elementUtil = new ElementUtil(driver);
	}

	/**
	 * This method is used to refresh the page using JavaSriptExecutor.
	 */
	public void refreshBrowserByJS(){
		js = (JavascriptExecutor) driver;
		js.executeScript("history.go(0)");
	}
	/**
	 * This method is used to create the alert using JavaSriptExecutor.
	 * @param message
	 */
	public void genrateAlertByJS(String message){
		js = (JavascriptExecutor) driver;
		js.executeScript("alert('"+ message+"')");
	}
	/**
	 *  This method is used to click on specific web element using JavaSriptExecutor.
	 * @param locator
	 */
	public void clickElementByJS(WebElement element){
		//WebElement element = elementUtil.getElement(locator);
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	/**
	 * This method is used to enter value with id using JavaSriptExecutor.
	 * @param id
	 * @param value
	 */
	public void sendkeysUsingJSWithId(String id, String value){
		js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('" +id+ "').value='" +value +"'");
	}

	/**
	 * This method is used to enter list of value with name using JavaSriptExecutor.
	 * @param name
	 * @param value
	 */
	public void sendkeysUsingJSWithMultipleNames(String name , String value){
		js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementsByName('" + name + "').value='"+value+"'");
	}

	/**
	 * This method is used to enter  value with WebElement using JavaSriptExecutor.
	 * @param locator
	 * @param value
	 */
	public void sendkeysUsingJSWithWebElement(WebElement element, String value ){
		//WebElement element = elementUtil.getElement(locator);
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].value='"+value+"';", element);
	}

	/**
	 *  This method is used to get the browser information in your system using JavaSriptExecutor.
	 * @return uAgent
	 */
	public String getBrowserInfoByJS(){
		js = (JavascriptExecutor) driver;
		String uAgent =js.executeScript("return navigator.userAgent").toString();
		return uAgent;
	}

	/**
	 * This method is used to get the inner page text on the web page using JavaSriptExecutor.
	 * @return pageText
	 */
	public String getInnerTextByJS(){
		js = (JavascriptExecutor) driver;
		String pageText = js.executeScript("return document.documentElement.innerText;").toString();
		return pageText;
	}

	/**
	 * This method is used to get the title of the web page using JavaSriptExecutor.
	 * @return title
	 * you can also get  domain; name or URL; by JS 
	 */
	public String getTitleByJS(){
		js = (JavascriptExecutor) driver;
		String title = js.executeScript("return document.title;").toString();
		return title;
	}

	/**
	 * This method is used to draw the border of specific web element using JavaSriptExecutor.
	 * You can use it to highlight specific element or when there is bug you can draw border and then take screenshot.
	 * @param locator
	 */
	public void drawBorderByJS(WebElement element){
		//WebElement element = elementUtil.getElement(locator);
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='3px solid red'", element);	
	}

	/**
	 * This method is used to flash on the web elements. 
	 * We can use this method while presentation.
	 * @param locator
	 */
	public void flash(WebElement element ){
		//WebElement element = elementUtil.getElement(locator);
		String bgColor =element.getCssValue("background-color");
		for(int i=0; i<10; i++){
			changeColor("green", element );
			changeColor( bgColor,element);
		}
	}

	/**
	 *  This method is used to change background color of specific web element using JavaSriptExecutor. 
	 * @param color
	 * @param locator
	 */
	private void changeColor(String color ,WebElement element){
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.backgroundColor='"+ color + "';", element);
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {

		}
	}

	//Scrolling
	/**
	 * This method is used to scroll page down completly using JavaSriptExecutor.
	 */
	public void scrollPageDown(){
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}

	/**
	 * This method is used to scroll upto the specific element is visible or clickable etc  using JavaSriptExecutor.
	 * @param locator
	 */
	public void scrollIntoView(WebElement element){
		//WebElement element = elementUtil.getElement(locator);
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	/**
	 * This method is used to scroll up upto specified pixels using JavaSriptExecutor.
	 * @param pixels like 250 , 500 ,1000 etc
	 */
	public void scrollUp(int pixels){
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,"+pixels +")");

	}
	/**
	 * This method is used to scroll down upto specified pixels using JavaSriptExecutor.
	 * @param pixels like 250 , 500 ,1000 etc
	 */
	public void scrollDown(int pixels){
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-"+pixels +")");

	}

	/**
	 * This method is used to scroll down upto specified pixels using JavaSriptExecutor.
	 * @param pixels like 250 , 500 ,1000 etc
	 */
	public void scrollHorizontal(int pixels){
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy("+pixels+",0)");
	}






}
