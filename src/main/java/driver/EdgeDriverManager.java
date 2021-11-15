package driver;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import base.TestBase;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import utils.Constants;
public class EdgeDriverManager extends DriverManager{


	protected final Logger logger = LogManager.getLogger(this.getClass());	

	@Override
	protected WebDriver createDriver() {
		try {
			if(Constants.enableremotedriver().equals("NO")) {
				logger.info("Initializing Edge Driver");
				WebDriverManager.getInstance(DriverManagerType.EDGE).setup();	
				return new EdgeDriver();
			}
			else {
				logger.info("Edge remote driver not suppored");
				//drivers.set(WebDriverManager.edgedriver().remoteAddress(Constants.huburl()).create());					
				//return drivers.get();	
				throw new RuntimeException("Edge remote driver not suppored");				
			}	
		}
		catch(Exception e) {			
			logger.info("Initializing Edge Driver");
			WebDriverManager.getInstance(DriverManagerType.EDGE).setup();	
			return new EdgeDriver();
		}	
	}
}