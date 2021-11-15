
package base;

import java.util.NoSuchElementException;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Constants;
import utils.TestUtil;
public class PageBase 
{
	protected static WebDriver pageDriver;
	protected WebDriverWait wait;	
	public JavascriptExecutor executor ;	
	protected final Logger logger = LogManager.getLogger(this.getClass());

	public PageBase(WebDriver driver) {
		PageBase.pageDriver = driver;		
		executor = (JavascriptExecutor) pageDriver;
		wait = new WebDriverWait(pageDriver, Constants.iTimeOut, Constants.iPolling);
		PageFactory.initElements(new AjaxElementLocatorFactory(PageBase.pageDriver, Constants.iTimeOut), this);		
	}

	public void waitForPageLoad() {
		wait.until((ExpectedCondition<Boolean>) pbDriver -> ((JavascriptExecutor) pbDriver).executeScript("return document.readyState").equals("complete"));
	}

	public void waitforDriver() throws InterruptedException {
		int count =0; 
		if((Boolean) executor.executeScript("return window.jQuery != undefined") && (Boolean) executor.executeScript("return $('.spinner').is(':visible') == false") ){ 
			while(!(Boolean) executor.executeScript("return jQuery.active == 0")){ 	
				Thread.sleep(4000); 
				if(count>4) break; count++; 
			} 
		}   
	}

	//*******************************************************************
	//Java script function for performing operation on WebElement
	//*******************************************************************

	public void mouseHover(WebElement element) throws Throwable {
		Actions action = new Actions(pageDriver);
		Thread.sleep(1000);
		action.moveToElement(element).perform();
	}

	public void moveToElementnclick(WebElement element) {
		Actions actions = new Actions(pageDriver); 
		actions.moveToElement(element).click().build().perform(); 
	}

	public void moveToElement(WebElement element) {
		Actions actions = new Actions(pageDriver); 
		actions.moveToElement(element).perform(); 
	}

	public void jsExecutorClickOn(WebElement element){
		((JavascriptExecutor) pageDriver).executeScript("arguments[0].click();",element);
	}

	public void jsExecutorscrollIntoView(WebElement element){
		((JavascriptExecutor) pageDriver).executeScript("arguments[0].scrollIntoView();",element);
	}

	//***********************************************************************************************
	//Custom functions to perform operations on Web Elements 
	//***********************************************************************************************

	public boolean Click(WebElement pageElement, int retryCount) throws Throwable {
		boolean status;
		int Count=retryCount;
		status=true;
		String strElement = pageElement.toString();
		do {
			try {
				logger.info("Looking for WebElement to click: "+ TestUtil.WebElementParser(strElement));	
				wait.until(ExpectedConditions.visibilityOf(pageElement));
				wait.until(ExpectedConditions.elementToBeClickable(pageElement));
				pageElement.click();
				logger.info("Successfully Clicked WebElement: "+ TestUtil.WebElementParser(strElement));
				if(Constants.getHTMLReportStatus().equalsIgnoreCase("False")) {
					TestUtil.getScreenshot(pageDriver,"pass");
				}
				status = false;
				return true;
			}
			catch(NoSuchElementException nsex) {
				Count=Count-1;
				status= false;
				logger.error("No Such Element Exception occurred: " + nsex.getMessage());
			}
			catch (StaleElementReferenceException sere) {
				Count=Count-1;
				logger.error("Stale Element Exception occurred: " + sere.getMessage());				
			}	
			catch (ElementClickInterceptedException ecix) {
				Count=Count-1;
				logger.error("Element Click Intercepted Exception: " + ecix.getMessage());				
			}
			catch(TimeoutException toe) {
				Count=Count-1;
				status = false;				
				logger.error("Timeout Exception Occurred: " + toe.getMessage());
				toe.printStackTrace();				
			}		
			catch(Throwable e) {
				Count=Count-1;
				logger.error("Throwable Exception Occurred: "+ e.toString());
				status = false;
			}
			if (Count<1) {
				status=false;
			}				
		} while(status);
		return false;
	}

	//***********************************************************************************************	

	public boolean DoubleClick(WebElement pageElement, int retryCount) throws Throwable {
		boolean status;
		int Count=retryCount;
		status=true;
		String strElement = pageElement.toString();
		do {
			try {
				logger.info("Double clicking WebElement: "+ TestUtil.WebElementParser(strElement));
				Actions actions = new Actions(pageDriver);
				wait.until(ExpectedConditions.visibilityOf(pageElement));
				actions.doubleClick(pageElement);
				logger.info("Successfully double clicked WebElement: "+ TestUtil.WebElementParser(strElement));
				if(Constants.getHTMLReportStatus().equalsIgnoreCase("False")) {
					TestUtil.getScreenshot(pageDriver,"pass");
				}
				status = false;
				return true;
			}
			catch(NoSuchElementException nsex) {
				status = false;
				Count=Count-1;
				logger.error("No Such Element Exception occurred: " + nsex.getMessage());
			}
			catch (StaleElementReferenceException sere) {
				Count=Count-1;
				logger.error("Stale Element Exception occurred: " + sere.getMessage());
			}
			catch (ElementClickInterceptedException ecix) {
				Count=Count-1;
				logger.error("Element Click Intercepted Exception: " + ecix.getMessage());
			}
			catch(TimeoutException toe) {
				status = false;
				Count=Count-1;
				logger.error("Timeout Exception Occurred: " + toe.getMessage());
				toe.printStackTrace();
			}
			catch(Throwable e) {
				Count=Count-1;
				logger.error("Throwable Exception Occurred: "+ e.toString());
				status = false;
			}
			if (Count<1) {
				status=false;
			}
		} while(status);
		return false;
	}

	//***********************************************************************************************	

	public boolean TypeInto(WebElement pageElement, String strvalue) throws Throwable {	
		String strElement = pageElement.toString();
		try {
			logger.info("Typing  " + strvalue + " into WebElement: "+ TestUtil.WebElementParser(strElement));
			wait.until(ExpectedConditions.visibilityOf(pageElement));
			wait.until(ExpectedConditions.elementToBeClickable(pageElement));
			pageElement.clear();
			pageElement.sendKeys(strvalue);
			logger.info("Successfully Typed " +strvalue + " into WebElement: "+ TestUtil.WebElementParser(strElement));
			if(Constants.getHTMLReportStatus().equalsIgnoreCase("False")) {
				TestUtil.getScreenshot(pageDriver,"pass");
			}
			return true;
		}
		catch(NoSuchElementException ne) {
			logger.error("No Such Element Exception occurred: " + ne.getMessage());
			return false;
		}
		catch (StaleElementReferenceException ex) {
			logger.error("Stale Element Exception occurred: " + ex.getMessage());
			return false;
		}		
		catch (ElementClickInterceptedException ecix) {		
			logger.error("Element Click Intercepted Exception: " + ecix.getMessage());
			return false;	
		}
		catch(TimeoutException te) {
			logger.error("Timeout Exception Occurred: " + te.getMessage());
			te.printStackTrace();
			return false;
		}
		catch(Throwable e) {
			logger.error("Throwable Exception Occurred: " + e.getMessage());
			return false;
		}			
	}

	//***********************************************************************************************

	public boolean TypeInto(WebElement pageElement, String strvalue, Keys keyType) throws Throwable {	
		String strElement = pageElement.toString();
		try {
			logger.info("Typing  " + strvalue + " into WebElement: "+ TestUtil.WebElementParser(strElement));
			wait.until(ExpectedConditions.visibilityOf(pageElement));
			wait.until(ExpectedConditions.elementToBeClickable(pageElement));
			pageElement.clear();
			pageElement.sendKeys(strvalue, keyType);
			logger.info("Successfully Typed " +strvalue + " into WebElement: "+ TestUtil.WebElementParser(strElement));
			if(Constants.getHTMLReportStatus().equalsIgnoreCase("False")) {
				TestUtil.getScreenshot(pageDriver,"pass");
			}
			return true; 
		}
		catch(NoSuchElementException ne) {
			logger.error("No Such Element Exception occurred: " + ne.getMessage());
			return false;
		}
		catch (StaleElementReferenceException ex) { // retry once
			logger.error("Stale Element Exception occurred: " + ex.getMessage());
			return false;
		}		
		catch (ElementClickInterceptedException ecix) {		
			logger.error("Element Click Intercepted Exception: " + ecix.getMessage());
			return false; 			
		}
		catch(TimeoutException te) {
			logger.error("Timeout Exception Occurred: " + te.getMessage());
			te.printStackTrace();
			return false;
		}
		catch(Throwable e) {
			logger.error("Throwable Exception Occurred: " + e.getMessage());
			return false;	
		}			
	}

	//***********************************************************************************************
	public String GetText(WebElement pageElement) throws Throwable {	
		String strgettextvalue="";
		String strElement = pageElement.toString();			
		try {
			logger.info("Retrieveing  gettext() value of WebElement: "+ TestUtil.WebElementParser(strElement));
			wait.until(ExpectedConditions.visibilityOf(pageElement));
			strgettextvalue= pageElement.getText().trim();
			logger.info("Successfully retrieved gettext() value of WebElement: "+ TestUtil.WebElementParser(strElement));
			return strgettextvalue; 
		}
		catch(NoSuchElementException ne) {
			logger.error("No Such Element Exception occurred: " + ne.getMessage());
			return strgettextvalue;
		}
		catch (StaleElementReferenceException ex) { // retry once
			logger.error("Stale Element Exception occurred: " + ex.getMessage());
			return strgettextvalue;
		}		
		catch(TimeoutException te) {
			logger.error("Timeout Exception Occurred: " + te.getMessage());
			te.printStackTrace();
			return strgettextvalue;
		}
		catch(Throwable e) {
			logger.error("Throwable Exception Occurred: " + e.getMessage());
			return strgettextvalue;	
		}			
	}	

	//***********************************************************************************************

	public String GetAttribute(WebElement pageElement, String strAttribute) throws Throwable {	
		String strgetattribvalue="";
		String strElement = pageElement.toString();
		try {
			logger.info("Retrieveing Attribute " + strAttribute + " value from WebElement: "+ TestUtil.WebElementParser(strElement));
			wait.until(ExpectedConditions.visibilityOf(pageElement));			
			strgetattribvalue=pageElement.getAttribute(strAttribute);			
			logger.info("Successfully retrieved Attribute " + strAttribute + " value from WebElement: "+ TestUtil.WebElementParser(strElement));
			if(Constants.getHTMLReportStatus().equalsIgnoreCase("False")) {
				TestUtil.getScreenshot(pageDriver,"pass");
			}
			return strgetattribvalue; 
		}
		catch (StaleElementReferenceException ex) { // retry once
			logger.error("Stale Element Exception occurred: " + ex.getMessage());
			return strgetattribvalue;
		}
		catch(NoSuchElementException ne) {
			logger.error("No Such Element Exception occurred: " + ne.getMessage());
			return strgetattribvalue;
		}
		catch(TimeoutException te) {
			logger.error("Timeout Exception Occurred: " + te.getMessage());
			te.printStackTrace();
			return strgetattribvalue;
		}
		catch(Throwable e) {
			logger.error("Throwable Exception Occurred: " + e.getMessage());
			return strgetattribvalue;	
		}			
	}	

	//***********************************************************************************************

	public boolean SelectDropdownText(WebElement pageElement,String strdropdownval) throws Throwable {	
		String strElement = pageElement.toString();
		try {
			Actions actions = new Actions(pageDriver); 
			logger.info("Selecting: " + strdropdownval + "Value from Dropdown " + TestUtil.WebElementParser(strElement));
			wait.until(ExpectedConditions.visibilityOf(pageElement));
			Select dropdown= new Select(pageElement);
			dropdown.selectByVisibleText(strdropdownval);
			logger.info("Successfully Selected " + strdropdownval + "Value from Dropdown " + TestUtil.WebElementParser(strElement));
			if(Constants.getHTMLReportStatus().equalsIgnoreCase("False")) {
				TestUtil.getScreenshot(pageDriver,"pass");
			}
			return true; 
		}
		catch (StaleElementReferenceException ex) { // retry once
			logger.error("Stale Element Exception occurred: " + ex.getMessage());
			return false;
		}
		catch(NoSuchElementException ne) {
			logger.error("No Such Element Exception occurred: " + ne.getMessage());
			return false;
		}
		catch(TimeoutException te) {
			logger.error("Timeout Exception Occurred: " + te.getMessage());
			te.printStackTrace();
			return false;
		}
		catch(Throwable e) {
			logger.error("Throwable Exception Occurred: " + e.getMessage());
			return false;				
		}			
	}	

	//***********************************************************************************************
}
