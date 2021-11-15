
package utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

public class WebEventListener  implements WebDriverEventListener {
	TestUtil objTEst = new TestUtil();
	String StrElementerror = "";
	int count = 0;
	final Logger logger = LogManager.getLogger(this.getClass());
	
	public void beforeNavigateTo(String url, WebDriver driver) {		
		logger.debug("Before navigating to: '" + url + "'");
	}

	public void afterNavigateTo(String url, WebDriver driver) {
		logger.debug("Navigated to:'" + url + "'");
	}

	public void beforeChangeValueOf(WebElement element, WebDriver driver) {
		logger.debug("Value of the:" + element.toString() + " before any changes made");
	}

	public void afterChangeValueOf(WebElement element, WebDriver driver) {
		logger.debug("Element value changed to: " + element.toString());
	}

	public void beforeClickOn(WebElement element, WebDriver driver) {
		logger.debug("Trying to click on: " + element.toString());
	}

	public void afterClickOn(WebElement element, WebDriver driver) {
		logger.debug("Clicked on: " + element.toString());
	}

	public void beforeNavigateBack(WebDriver driver) {
		logger.debug("Navigating back to previous page");
	}

	public void afterNavigateBack(WebDriver driver) {
		logger.debug("Navigated back to previous page");
	}

	public void beforeNavigateForward(WebDriver driver) {
		logger.debug("Navigating forward to next page");
	}

	public void afterNavigateForward(WebDriver driver) {
		logger.debug("Navigated forward to next page");
	}

	public void onException(Throwable error, WebDriver driver) {
		String strError =error.toString() ;	

		if(StrElementerror.equals(strError)) {			
		}
		else {
			count = 0;		
		}		
		if(StrElementerror.equals(error.toString())) {			
		}		
		else {		
			try {
				if(count==0) {
					TestUtil.getScreenshot(driver, "error");
					StrElementerror =strError;
					count = count +1;
				}
			}  catch (Throwable e) {
				// TODO Auto-generated catch block
				System.out.println("Error =  " + e.toString() );
				e.printStackTrace();
			}
			StrElementerror =strError;
		}
	}	

	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		logger.debug("Trying to find Element By : " + by.toString());
	}

	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		logger.debug("Found Element By : " + by.toString());
	}
	/*
	 * non overridden methods of WebListener class
	 */
	public void beforeScript(String script, WebDriver driver) {
	}

	public void afterScript(String script, WebDriver driver) {
	}

	public void beforeAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void afterAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void afterAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void beforeAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void beforeNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void afterNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		// TODO Auto-generated method stub

	}

	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		// TODO Auto-generated method stub

	}

	public <X> void afterGetScreenshotAs(OutputType<X> arg0, X arg1) {
		// TODO Auto-generated method stub

	}

	public void afterGetText(WebElement arg0, WebDriver arg1, String arg2) {
		// TODO Auto-generated method stub

	}

	public void afterSwitchToWindow(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

	}

	public <X> void beforeGetScreenshotAs(OutputType<X> arg0) {
		// TODO Auto-generated method stub

	}

	public void beforeGetText(WebElement arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

	}

	public void beforeSwitchToWindow(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub

	}


}