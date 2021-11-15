package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import base.PageBase;

public class AddtocartPage extends PageBase{

	String steCheckoutvalue;	
	boolean blnStatus;
	public String strValidation ="FOLLOW US";

	@FindBy(xpath = "//*[@class=\"roboto-regular center ng-scope\" and @translate =\"FOLLOW_US\" ]")
	public WebElement lblValidation;

	@FindBy(xpath = "//*[@name=\"save_to_cart\"]")
	public WebElement btnAddToCart;

	@FindBy(xpath = "//*[@id=\"checkOutPopUp\"]")
	public WebElement btnCheckOut;

	@FindBy(xpath = "//*[@id=\"next_btn\"]")
	public WebElement btnNext;
	
	@FindBy(xpath = "//*[@id=\"menuCart\"]")
	public WebElement btnMenuCart;
		
	@FindBy(xpath = "//*[@id=\"userSection\"]/div[1]/div[2]/a")
	public WebElement btnEditShipping;

	@FindBy(xpath = "//*[@id=\"userDetailsEditMode\"]/sec-form/div[3]/sec-view[1]/div/select")
	public WebElement sddSelectCountry;
		
	public AddtocartPage(WebDriver driver) {
		super(driver);
	}

	public boolean fn_Validation() throws Throwable {
		String ValidationString = GetText(lblValidation);
		if(strValidation.equals(ValidationString)){
			return true;
		}else {
			return false;
		}
	}
	
	public boolean fn_AddToCart() throws Throwable   {
		blnStatus=fn_Validation();
		logger.info("validation passed found string FOLLOW US");
		
		blnStatus = Click(btnAddToCart,10);	
		if(!blnStatus) {
			logger.info("Unable to clicked on Add to Cart button");
			return blnStatus;
		}
		logger.info("Clicked on Add to Cart button");
				
		steCheckoutvalue = GetText(btnCheckOut);		
		blnStatus = Click(btnCheckOut,10);
		if(!blnStatus) {
			logger.info("Unable to clicked on Checkout button");
			return blnStatus;
		}
		logger.info("Clicked on  Checkout button");
		
		logger.info("Checkout Total is: " + steCheckoutvalue);
				
		blnStatus = Click(btnNext,10);	
		if(!blnStatus) {
			logger.info("Unable to clicked on Next button");
			return blnStatus;
		}
		logger.info("clicked on Next button");			
		return blnStatus;		
	}
}

