package com.qa.hubspot.base;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

public class BasePage {

	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionManager;
	
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();
	
	public static synchronized WebDriver getDriver(){
		return tldriver.get();
	}
	
	
	//public static boolean highligtElement;

	/**
	 * 
	 * @param browserName
	 * @return WebDriver
	 */

	public WebDriver initDriver(String browserName){

		//highligtElement = prop.getProperty("highlight").equals("yes") ? true : false;

		System.out.println("browser name is: "+ browserName);
		optionManager = new OptionsManager(prop);
		
		if(browserName.equals("chrome")){
			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver(optionManager.getChromeOptions());
			
			tldriver.set(new ChromeDriver(optionManager.getChromeOptions()));
			
			
			
//			if(prop.getProperty("headless").equalsIgnoreCase("yes")){
//				ChromeOptions co = new ChromeOptions();
//				co.addArguments("--headless");
//				driver = new ChromeDriver(co);
//			}else{
//				driver = new ChromeDriver();
//			}


		}else if(browserName.equals("firefox")){
			WebDriverManager.firefoxdriver().setup();
			//driver = new FirefoxDriver(optionManager.getFirefoxOptions());
			
			tldriver.set(new FirefoxDriver(optionManager.getFirefoxOptions()));
			
//			if(prop.getProperty("headless").equalsIgnoreCase("yes")){
//				FirefoxOptions fo = new FirefoxOptions();
//				fo.addArguments("--headless");
//				driver = new FirefoxDriver(fo);
//			}else{
//				driver = new FirefoxDriver();
//			}


		}else if(browserName.equals("safari")){
			WebDriverManager.getInstance(SafariDriver.class).setup();
			//driver = new SafariDriver();
			tldriver.set(new SafariDriver());
			
		}else{
			System.out.println("browser name "+browserName+" is not found, please pass the correct browser");
		}
		
		 //driver.manage().deleteAllCookies();
		 getDriver().manage().deleteAllCookies();
		
		 //driver.manage().window().maximize();
		 getDriver().manage().window().maximize();
		
		//return driver;
		return getDriver();
	}

	/**
	 * This method is used to initialized the properties file.
	 * @return Properties
	 */
	public Properties initProperties() {

		prop = new Properties();
		String path = null;
		String env = null;
		try {
			env = System.getProperty("env");

			if(env.equals("qa")){
				path="./src/main/java/com/qa/hubspot/config/qa.config.properties";
			}else if(env.equals("stg")){
				path="./src/main/java/com/qa/hubspot/config/stg.config.properties";
			}
		}catch(Exception e){
			path = "./src/main/java/com/qa/hubspot/config/config.properties";
		}

		try {
			FileInputStream fis = new FileInputStream(path);
			prop.load(fis);//read the props from prop file

		} catch (FileNotFoundException e) {
			System.out.println("some issue with config properties...Please correct your config");
			System.out.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;

	}


	public synchronized String getScreenshot(){
		
		File src =((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		
		String storePath =System.getProperty("user.dir")+ "/Screenshots/" + System.currentTimeMillis()+".png";
		
		File dest = new File(storePath);
		
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			System.out.println("screenshot captured failed..");
		}
		
		return storePath;
		
	}
	


}
