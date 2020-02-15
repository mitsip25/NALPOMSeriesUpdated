package com.qa.hubspot.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	
	
	public static Workbook book;
	public static Sheet sheet;
	
	
	public static String TESTDATA_SHEET_PATH= "C://SeleniumTraining//JavaLearning//NaveenAutomationLabsPOMSeries//src//main//java//com//qa//hubspot//testData//CreateContactDataHubSpot.xlsx";
	
	
	public static Object[][] getTestData(String sheetName){
		
		try {
			FileInputStream fis = new FileInputStream(TESTDATA_SHEET_PATH);
			 book = WorkbookFactory.create(fis);
			 sheet = book.getSheet(sheetName);
			 
			 //Object[][] data = new Object[5][4]; //5 rows and 4 column
			Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			 //so for 2d array 2 for loop :1 for rows 1 for column
			 for(int i =0; i<sheet.getLastRowNum();i++){
				 
				 for(int j=0; j<sheet.getRow(0).getLastCellNum();j++){
					 
					 //My data starts from 2nd row so i+1 :2nd row.1st column and whatever the value we r getting convert totoString();
					 data[i][j]= sheet.getRow(i+1).getCell(j).toString();
					
					 
				 }
			 }
			 
			 return data;
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
		
	}
	
}
