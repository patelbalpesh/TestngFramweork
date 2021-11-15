
package driver;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import utils.WebEventListener;

public abstract class DriverManager {
	protected final Logger logger = LogManager.getLogger(this.getClass());	
	protected ThreadLocal<WebDriver> drivers = new ThreadLocal<>();
	protected abstract WebDriver createDriver();
	
	public void quitDriver() {
		if (null != drivers.get()) {
			try {
				drivers.get().quit(); // First quit WebDriver session gracefully
				drivers.remove(); // Remove WebDriver reference from the ThreadLocal variable.
			} catch (Exception e) {
				logger.error("Unable to gracefully quit WebDriver.", e); // We'll replace this with actual Loggers later - don't worry !
			}
			
		}
	}
	
	public WebDriver getDriver() {
		if (null == drivers.get()) {			
			EventFiringWebDriver e_driver = new EventFiringWebDriver(this.createDriver());
			WebEventListener eventListen = new WebEventListener();
			e_driver.register(eventListen);		
			drivers.set(e_driver);			
		}
		drivers.get().manage().timeouts().implicitlyWait(1L, TimeUnit.SECONDS);		
		return drivers.get();
	}
}