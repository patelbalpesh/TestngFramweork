package driver;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import utils.Constants;

public class FirefoxDriverManager extends DriverManager{

protected final Logger logger = LogManager.getLogger(this.getClass());	
	
	@Override
	protected WebDriver createDriver() {
		try {
			if(Constants.enableremotedriver().equals("NO")) {			
				logger.info("Initializing Firefox Driver");
				WebDriverManager.getInstance(DriverManagerType.FIREFOX).setup();	
				return new FirefoxDriver(getFirefoxOptions());
			}
			else {	
				logger.info("Initializing Firefox Remote Driver");
				drivers.set(WebDriverManager.firefoxdriver().remoteAddress(Constants.huburl()).create());					
				return drivers.get();				
			}
		}
		catch(Exception e) {
			logger.info("Initializing Firefox Driver");
			WebDriverManager.getInstance(DriverManagerType.FIREFOX).setup();	
			return new FirefoxDriver(getFirefoxOptions());
		}		
	}
	
	private FirefoxOptions getFirefoxOptions() {
		// A few valid Options for Chrome, showcase purpose.
		FirefoxOptions options = new FirefoxOptions();
		//options.addArguments("--disable-notifications");
		options.addArguments("--start-maximized");
		//options.addArguments("--disable-features=EnableEphemeralFlashPermission");
		//options.addArguments("--disable-infobars");
		return options;
	}
	
	
	
}
