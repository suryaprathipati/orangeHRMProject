package projectutilities.utils;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshots {
	
	private final static String folderPath = ".//screenshots//";

	// get current system date
	public static String getDate() {
		DateFormat d = new SimpleDateFormat("hh_mm_ss dd_MMM_yy");
		Date dt = new Date();
		String date = d.format(dt);
		return date;
	}

	// get screenshot of the web page
	/**
	 * This accepts fileName(no need to provide the folder path), captures the screenshot
	 * and it will store the image in project folder
	 * @param fileName
	 * @param driver
	 */
	public static void screenCapture(String fileName, WebDriver driver) {
		try {
			File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(f, new File(folderPath+fileName + " " + getDate()+".png"));
		} catch (Exception e) {

		}
	}

	// take alert screen shot
	/**
	 * This accepts fileName(no need to provide the folder path), captures the screenshot
	 * and it will store the image in project folder
	 * @param fileName
	 * @param driver
	 */
	public static void alertScreenCapture(String fileName) {
		try {
			Robot r = new Robot();
			Rectangle rc = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			BufferedImage bi = r.createScreenCapture(rc);
			ImageIO.write(bi, "png", new File(folderPath+fileName + " " + getDate()+".png"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * This accepts fileName(no need to provide the folder path), height and width of the screen, 
	 * captures the screenshot and it will store the image in project folder
	 * @param fileName
	 * @param driver
	 */
	public static void alertScreenCapture(String fileName, int h, int w) {
		try {
			Robot r = new Robot();
			Rectangle rc = new Rectangle(0,0,w,h);
			BufferedImage bi = r.createScreenCapture(rc);
			ImageIO.write(bi, "png", new File(folderPath+fileName + " " + getDate()+".png"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
