package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import base.TestBase;

public class TestUtil {
	static String rootdir;
	public static String brow;
	public static String reportFolderPath = System.getProperty("user.dir") + "\\target\\TestReports";
	static File file;
	
	static Logger logger = Logger.getLogger(TestUtil.class.getName());
	
	
	
	public static String getBrowserVersion() throws IOException {
		try {
			Runtime rt = Runtime.getRuntime();
			try {
				rootdir = System.getProperty("user.dir");
				rt.exec("cmd  /K \"dir /B/AD \"C:/Program Files (x86)/Google/Chrome/Application/\"|findstr /R /C:\"^[0-9].*\\..*[0-9]$\" > "+ rootdir +"\\version.txt\"");
				brow = getVersion();
			} 
			catch (IOException e) {
				rootdir = System.getProperty("user.dir");
				rt.exec("cmd  /K \"dir /B/AD \"C:/Program Files/Google/Chrome/Application/\"|findstr /R /C:\"^[0-9].*\\..*[0-9]$\" > "+ rootdir +"\\version.txt\"");
				brow = getVersion();
				e.printStackTrace();
			}
			return brow.substring(0, brow.length() - 4);
		}
		catch(Exception e){
			brow = e.toString();
			return brow;
		}
	}

	//-------------------------------WebElementParsing-------------------------------------------------------------

	public static String WebElementParser(String myString) {

		String pattern = "(.*)(->)(.*)(])";
		//System.out.println(myString.replaceAll(pattern, "$3"));
		return myString.replaceAll(pattern, "$3");
	}


	//--------------------Return Stored value of Chrome Browser Version----------------------------
	public static String getVersion() {
		String data = "";
		try {
			File myObj = new File(rootdir+"/version.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				data = myReader.nextLine();
				break;
			}
			myReader.close();
			return data;
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return null;
	}

	public String  currenttime() {
		String strnow;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss:mm");  
		LocalDateTime now = LocalDateTime.now();  
		strnow = now.toString();
		return strnow;
	}
	
	public static String getCurrentDate()
	{
		Date date = new Date();  
		SimpleDateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy");  
		String strDate = dateformat.format(date); 
		return strDate;
	}

	public static String getTimeStamp()
	{
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy-hh-mm-ss");
		String time = dateFormat.format(now);
		return time.replace("-", "");
	}


	//-------------------------------------------TimeStamp Function----------------------------------	
	public static String getAttendenceTime(int hour)
	{
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(new Date());              
		cal.add(Calendar.HOUR_OF_DAY, hour);  
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = dateFormat.format(cal.getTime());
		return time;
	}


	//---------------------------------Function For Current Date---------------------------------		
	public static String getLeaveDate(int date)
	{
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, date);
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");  
		String strDate = dateformat.format( cal.getTime()); 
		return strDate;
	}

	//---------------------------------------Select Item--------------------------------------------
	public static void selectItem(WebElement element,int LeaveFormat) {
		Select Leave = new Select(element);
		Leave.selectByIndex(LeaveFormat);
	}


	//---------------------------------------Select ItemByVisibleText --------------------------------------------
	public static void selectItemByVisibleText(WebElement element,String visibleText) {
		Select list = new Select(element);
		list.selectByVisibleText(visibleText);
	}

	//------------------------get date by format---------------------------------------------------
	public static String getDatebyFormat(String dateFormat ,int addToDate)
	{
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, addToDate);
		SimpleDateFormat dateformat = new SimpleDateFormat(dateFormat);  
		String strDate = dateformat.format( cal.getTime()); 
		return strDate;
	}

	

	public static String getScreenshot(WebDriver driver, String Status) throws Exception {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		//TakesScreenshot ts = (TakesScreenshot) driver;
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/TestsScreenshots/"+Status+dateName+".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);		
		logger.info("Saving screen shot " +destination );
		
		return destination;
	}

	public static String CreateFile(String Filename) throws IOException {

		Path path = Paths.get(reportFolderPath);					
		//System.out.println("Check if report folder path exist: " + Files.exists(path));
		File theDir = new File(reportFolderPath);
		if (!theDir.exists()){
			theDir.mkdirs();
		}
		
		File thescDir = new File(System.getProperty("user.dir")+"\\TestsScreenshots");
		if (!thescDir.exists()){
			thescDir.mkdirs();
		}

		if (Filename.contains(".html")) {

			file = new File(reportFolderPath+"\\HTMLReport"+getTimeStamp()+"\\"+Filename);
		}

		else if(Filename.contains(".log")) {
			file = new File(reportFolderPath+"\\LOgReport"+getTimeStamp()+"\\"+Filename);
		}

		if (file.getParentFile().mkdir()) {
			file.createNewFile();
			return file.getPath();
		} else {
			throw new IOException("Failed to create directory " + file.getParent());
		}
	}
}

