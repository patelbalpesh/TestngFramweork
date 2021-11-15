package utils;


import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import base.TestBase;

public class Reporter {

	static ExtentTest logger, childLogger, parentLogger;
	static ThreadLocal<ExtentTest> extentThread = new ThreadLocal<ExtentTest>();
	static ThreadLocal<ExtentReports> extentReporterThread = new ThreadLocal<ExtentReports>();
	static ExtentSparkReporter htmlReporter;
	static ExtentReports extent;
	static String extentFolderPath = TestUtil.reportFolderPath+"\\Reports"+TestUtil.getTimeStamp()+"\\testReport.html";
	static int iStepNumber = 1;
	static Logger logger1 = Logger.getLogger(Reporter.class.getName());
	
	public static void createReportFile(String reportTitle) throws IOException   {			
		htmlReporter = new ExtentSparkReporter(TestUtil.CreateFile("Test.html") );		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("OS", "Windows 10");
		extent.setSystemInfo("Browser", "Chrome");
		htmlReporter.config().setDocumentTitle(reportTitle+" Extent Report");
		htmlReporter.config().setReportName(reportTitle+" Test Report");
		htmlReporter.config().setTheme(Theme.STANDARD);	  
	}

	public static void updateReportName(String Step_details) {
		createReportName(Step_details);
	}

	public synchronized ExtentReports getInstance() {	
		return extentReporterThread.get();
	}

	public static ExtentTest createReportName(String step_details){
		logger = extent.createTest(step_details+"</b>"+"System Name - "+ System.getenv("COMPUTERNAME"));
		extentThread.set(logger);
		iStepNumber =1;
		return extentThread.get();	
	} 

	public synchronized ExtentTest getTest() {
		return extentThread.get();
	}

	public static void ReportEvent(String description, String expectedStep, String actualStep, String status) throws Throwable{		
		String ReportStatus = "<b>Step Number "+iStepNumber+"<br>Description :</b> "+description+"<br><b>Expected :</b> "+expectedStep+"<br><b>Actual :</b> "+actualStep;
		String strLogstep="Step Number: "+ iStepNumber+" Description : " + description + " Expected Result: "+ expectedStep +" Actual Result :  "+ actualStep;
		
		logger1.info(strLogstep);
		if (Constants.getHTMLReportStatus().equalsIgnoreCase("TRUE")) {
			if (Constants.getExcelReportStatus().equalsIgnoreCase("TRUE")) {
				ExcelLibraries.fExcelReporter(description, actualStep, expectedStep, status, TestUtil.getCurrentDate());
			}	
			try{
				if(status.equalsIgnoreCase("PASS")){	
					extentThread.get().log(Status.PASS, ReportStatus);				
					extentThread.get().addScreenCaptureFromPath(TestUtil.getScreenshot(TestBase.driverManager.getDriver(),"PASS"));
					flush();
				}
				else{
					extentThread.get().log(Status.FAIL, ReportStatus);
					extentThread.get().addScreenCaptureFromPath(TestUtil.getScreenshot(TestBase.driverManager.getDriver(),"Fail"));
					flush();				
				}		
			}catch(Exception e){
				e.printStackTrace();
			}
			iStepNumber = iStepNumber + 1;
		}else {
			if (Constants.getExcelReportStatus().equalsIgnoreCase("TRUE")) {
				ExcelLibraries.fExcelReporter(description, actualStep, expectedStep, status, TestUtil.getCurrentDate());
				iStepNumber = iStepNumber + 1;
			}

		}
	}

	public static void skipReportEvent() throws Throwable{			
		String ReportStatus = "<b>Step Number "+iStepNumber+"<br>Description :The Test has been Skipped ";
		if (Constants.getHTMLReportStatus().equalsIgnoreCase("TRUE")) {
			if (Constants.getExcelReportStatus().equalsIgnoreCase("TRUE")) {
				ExcelLibraries.fExcelReporter("The test has been skipped", "", "", "Skipped", TestUtil.getCurrentDate());
			}
			try{					
				extentThread.get().log(Status.SKIP, ReportStatus);
				extentThread.get().addScreenCaptureFromPath(TestUtil.getScreenshot(TestBase.driverManager.getDriver(),"Skip"));
				flush();			
			}catch(Exception e){
				e.printStackTrace();
			}
			iStepNumber = iStepNumber + 1;
		} else {
			if (Constants.getExcelReportStatus().equalsIgnoreCase("TRUE")) {
				ExcelLibraries.fExcelReporter("The test has been skipped", "", "", "Skipped", TestUtil.getCurrentDate());
				iStepNumber = iStepNumber + 1;
			}
		}		
	}
	public static void flush(){
		extent.flush();
	}
}
