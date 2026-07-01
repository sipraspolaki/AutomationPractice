package com.example.automation.utils.commonUtils;
import java.io.File;
import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviderClass {
	
	String filePath = System.getProperty("user.dir")+ File.separator+"test-data-files"+File.separator+"TestData.xlsx";
	String sheetName = "RepositoryNames";
	
	@DataProvider(name = "excelDataProvider")
	public Object [][] excelDataProvider() throws IOException{
		return ExcelUtils.readExcel(filePath, sheetName);
	}

}
