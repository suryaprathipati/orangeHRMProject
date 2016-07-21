package projectutilities.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelPOI {

	public static Workbook book;
	public static Sheet sheet;
	public static Row row;
	public static Cell cell;
	public static FileOutputStream fos; 
	public static FileInputStream fis;
	public static File f;

	// create an excel connection to read the data
	/**
	 * This accepts a filename and sheet name and creates a connection to that file
	 * so we can perform read operations on that file
	 * please provide the complete path of the file including its name
	 * eg:-- d:/testdata/sample.xls
	 * @param fileName
	 * @param sheetName
	 */
	public static void setExcelConnection(String fileName, String sheetName) {
		try {
			f = new File(fileName);
			fis = new FileInputStream(f);		
			if (fileName.endsWith(".xlsx")) {
				book = new XSSFWorkbook(fis);
			} else if (fileName.endsWith(".xls")) {
				book = new HSSFWorkbook(fis);
			}
			sheet = book.getSheet(sheetName);
			System.out.println("excel connection succesful");
		} catch (Exception e) {
			System.out.println(e.getMessage()+"  excel connection unsuccesful");
		}
	}

	// to read data from excel file
	/**
	 * This accepts a row num, a column num to read data from the cell
	 * 
	 * @param rnum
	 * @param cnum
	 * @return
	 */
	public static String readData(int rnum, int cnum) {
		String data = null;
		try {
			row = sheet.getRow(rnum);
			cell = row.getCell(cnum);
			if (cell.getCellType() == cell.CELL_TYPE_STRING) {
				data = cell.getStringCellValue();
			} else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
				data = String.valueOf(cell.getNumericCellValue());
			}
			return data;
		} catch (Exception e) {
			return null;
		}

	}

	// to write data to excel file
	/**
	 * This accepts a row num, a column num and data to write data to the cell
	 * 
	 * @param rnum
	 * @param cnum
	 * @param data
	 */
	public static void writeData(int rnum, int cnum, String data) {
		
		try {
			row = sheet.getRow(rnum);
			cell = row.createCell(cnum);
			cell.setCellValue(data);	
		} catch (Exception e) {
			
		}

	}
	
	//to save the workbook
	/**
	 * This will save the modifications done to the excel file using writeData method
	 * to the excel file created by setExcelConnection method 
	 */
	public static void saveWorkBook(){
		try {
			fis.close();
			fos = new FileOutputStream(f);
			book.write(fos);
			fos.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// to get the no of rows
	public static int getRows() {
		try {
			return sheet.getLastRowNum() + 1;
		} catch (Exception e) {
			return 0;
		}
	}

	// to get the total no of columns
	public static int getColumns() {
		try {
			row = sheet.getRow(1);
			return row.getLastCellNum();
		} catch (Exception e) {
			return 0;
		}
	}

}
