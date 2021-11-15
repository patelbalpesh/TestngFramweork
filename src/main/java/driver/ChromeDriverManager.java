package driver;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import utils.Constants;
public class ChromeDriverManager extends DriverManager{

	protected final Logger logger = LogManager.getLogger(this.getClass());	
	@Override
	protected WebDriver createDriver() {
		try {
			if(Constants.enableremotedriver().equals("NO")) {			
				logger.info("Initializing Chrome Driver");
				WebDriverManager.getInstance(DriverManagerType.CHROME).setup();					
				return new ChromeDriver(getChromeOptions());
			}
			else {	
				logger.info("Initializing Chrome Remote Driver");
				drivers.set(WebDriverManager.chromedriver().remoteAddress(Constants.huburl()).create());						
				return drivers.get();				
			}
		}
		catch(Exception e) {
			
			logger.info("Initializing Chrome");
			WebDriverManager.getInstance(DriverManagerType.CHROME).setup();					
			return new ChromeDriver(getChromeOptions());
		}
	}

	private ChromeOptions getChromeOptions() {
		// A few valid Options for Chrome, showcase purpose.
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		options.addArguments("--start-maximized");
		options.addArguments("--disable-features=EnableEphemeralFlashPermission");
		options.addArguments("--disable-infobars");
		return options;
	}










}
