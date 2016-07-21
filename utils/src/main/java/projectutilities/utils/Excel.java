package projectutilities.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class Excel {
	private static Workbook book;
	private static Sheet sh;
	private static WritableWorkbook wbook;
	public static WritableSheet wsh;

	// to create readable excel connection
	/**
	 * This accepts filename (provide complete file path including file extension), sheet name
	 * to create a connection to that excel file
	 * so we can perform read operation
	 * @param filename
	 * @param sheetName
	 */
	public static void setExcel(String filename, String sheetName) {
		try {
			FileInputStream fi = new FileInputStream(filename);
			book = Workbook.getWorkbook(fi);
			sh = book.getSheet(sheetName);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// to create readable excel connection
	/**
	 * This accepts filename (provide complete file path including file extension), sheet number
	 * to create a connection to that excel file
	 * so we can perform read operation
	 * @param filename
	 * @param sheetName
	 */
	public static void setExcel(String filename, int sheetnum) {
		try {
			FileInputStream fi = new FileInputStream(filename);
			book = Workbook.getWorkbook(fi);
			sh = book.getSheet(sheetnum);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// to create writable excel conneciton
	/**
	 * This accepts input filename (provide complete file path including file extension), output filename, sheet name
	 * to create a connection to that excel file
	 * so we can perform read operation from input excel and write operation on ouptu excel
	 * @param filename
	 * @param sheetName
	 */
	public static void setOutputExcel(String ifilename, String ofilename, String sheetname) {
		try {
			FileInputStream fi = new FileInputStream(ifilename);
			book = Workbook.getWorkbook(fi);
			FileOutputStream fo = new FileOutputStream(ofilename);
			wbook = Workbook.createWorkbook(fo);
			wsh = wbook.getSheet(sheetname);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// read data
	/**
	 * This accepts row number and column number from 
	 * which we are going to read the data and returns that data
	 * @param rnum
	 * @param cnum
	 * @return
	 */
	public static String readData(int rnum, int cnum) {
		return sh.getCell(cnum, rnum).getContents();
	}

	// writedata
	/**
	 * This accepts row number, column number and String data which we want to 
	 * write to the excel cell
	 * @param result
	 * @param rnum
	 * @param cnum
	 */
	public static void writedata(String result, int rnum, int cnum) {
		try {
//			Label l = new Label(cnum, rnum, result);
//			wsh.addCell(l);
			wsh.addCell(new Label(cnum, rnum, result));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// writedata
	/**
	 * This accepts row number, column number and int data which we want to 
	 * write to the excel cell
	 * @param result
	 * @param rnum
	 * @param cnum
	 */
	public static void writedata(int result, int rnum, int cnum) {
		try {
//			Number n = new Number(cnum, rnum, result);
//			wsh.addCell(n);
			wsh.addCell(new Number(cnum, rnum, result));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// row count
	/**
	 * @return
	 * returns number of filled rows
	 */
	public static int getRowCount() {
		return sh.getRows();
	}
	
	//columng count
	/**
	 * @return
	 * returns number of filled columns
	 */
	public static int getColumnCount(){
		return sh.getColumns();
	}
	
	//save workbook
	/**
	 * save the modifications to the workbook
	 */
	public static void saveWorkbook(){
		try {
			wbook.write();
			wbook.close();
			book.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


}
