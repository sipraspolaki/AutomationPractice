package com.example.automation.utils.commonUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ExcelUtils {

	public static Object[][] readExcel(String filePath, String sheetName) throws IOException {
		FileInputStream fis = new FileInputStream(new File(filePath));
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet(sheetName);
		int lastCol = sheet.getRow(0).getLastCellNum();
		int lastRow = sheet.getLastRowNum();

		Object[][] result = new Object[lastRow - 1][lastCol];

		// Index 0 is the header row so skipping header, in XSSF row and col starts from
		// 0 index
		for (int i = 0; i < lastRow - 1; i++) {
			for (int j = 0; j < lastCol; j++) {
				if (sheet.getRow(i + 1).getCell(j).getCellType() == CellType.STRING) {
					result[i][j] = sheet.getRow(i + 1).getCell(j).getStringCellValue();
				} else if (sheet.getRow(i + 1).getCell(j).getCellType() == CellType.NUMERIC) {
					result[i][j] = sheet.getRow(i + 1).getCell(j).getNumericCellValue();
				} else if (sheet.getRow(i + 1).getCell(j).getCellType() == CellType.BOOLEAN) {
					result[i][j] = sheet.getRow(i + 1).getCell(j).getBooleanCellValue();
				} else {
					throw new IllegalArgumentException("Excel cell type is not supported for reading");
				}
			}
		}

		fis.close();
		wb.close();

		return result;
	}

	public static void writeExcel(String filePath, String sheetName, Object[][] array) throws IOException {
		FileInputStream fis = new FileInputStream(new File(filePath));
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet(sheetName);

		System.out.println("Last filled row number is " + sheet.getLastRowNum());

		int newRowIndex = sheet.getLastRowNum() + 1;
		int colLimit = array[0].length;

		for (int i = 0; i < array.length; i++) {
			//Always create row outside of inner loop which is for cell
			Row row = sheet.createRow(newRowIndex + i);
			for (int j = 0; j < colLimit; j++) {
				System.out.println("!!!!!!!!!!! " + array[i][j]);
				Cell cell = row.createCell(j);
				
				if (array[i][j] instanceof String)
					cell.setCellValue(array[i][j].toString());
				else if (array[i][j] instanceof Integer)
					cell.setCellValue((Integer) array[i][j]);
				else if (array[i][j] instanceof Boolean)
					cell.setCellValue((Boolean) array[i][j]);
				else if (array[i][j] instanceof Double)
					cell.setCellValue((Double) array[i][j]);
				else
					throw new IllegalArgumentException("Data cannot be written to excel, unsupported dat type");
			}
		}

		FileOutputStream fos = new FileOutputStream(new File(filePath));
		wb.write(fos);
		fis.close();
		fos.close();
		wb.close();

	}

}
