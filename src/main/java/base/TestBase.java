
package base;

import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import driver.DriverManager;
import driver.DriverFactory;
import utils.Constants;
import utils.ExcelLibraries;
import utils.Reporter;

public class TestBase {	
	protected final Logger logger = LogManager.getLogger(this.getClass());		
	public static DriverManager driverManager;

	@BeforeSuite
	public void extentFileCreation() throws InterruptedException, IOException  {
		
	}
	
	@BeforeMethod
	protected void setup() {
		try {
			if (Constants.getBrowserName().equals("CHROME") ||Constants.getBrowserName().equals("EDGE")|| Constants.getBrowserName().equals("FIREFOX")) {
				logger.info("initializeDriverManager");
				initializeDriverManager(Constants.getBrowserName());
			}	
			else {
				logger.info("initializeDriverManager");
				initializeDriverManager("CHROME");
			}
		} catch (Exception e) {
			logger.info("initializeDriverManager");
			initializeDriverManager("CHROME");
		}
		
		try {		
			driverManager.getDriver().navigate().to(Constants.getContextUrl());	
			driverManager.getDriver().manage().window().maximize();			
		}
		catch(org.openqa.selenium.WebDriverException wde) {
			logger.debug("Web Driver exception occured" + wde.toString());
			driverManager.quitDriver();	
			System.exit(1);
		}	
	}

	private synchronized void initializeDriverManager(String driverName) {
		logger.info("Retreiving Driver");
		if (null == driverManager) {			
			driverManager = DriverFactory.valueOf(driverName).getDriverManager();
		} else {			
			driverManager.getDriver();
		}
	}

	@AfterMethod
	protected void cleanUp() throws Throwable {		
		logger.info("Cleaning up the Driver");
		driverManager.quitDriver();		
	}

	public  WebDriver getDriver() {
		return driverManager.getDriver();
	}
}
