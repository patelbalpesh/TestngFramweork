package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import base.PageBase;

public class HeadphonePage extends PageBase {
	boolean blnStatus;
	public String strheadphonename;
	public String strheadphoneprice;	
	public String strValidation ="FOLLOW US";

	@FindBy(xpath = "//*[@class=\"roboto-regular center ng-scope\" and @translate =\"FOLLOW_US\" ]")
	public WebElement lblValidation;

	@FindBy(xpath = "//*[@id=\"15\"]")
	public WebElement imgHpBeatStudio;

	@FindBy(xpath = "//*[@id=\"15\"]/following-sibling::p[1]/a[1]")
	public WebElement imgHeadPhoneName;

	@FindBy(xpath = "//*[@id=\"15\"]/following-sibling::p[2]/a[1]")
	public WebElement imgHeadPhonePrice;

	public HeadphonePage(WebDriver driver) {		
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

	public boolean fn_SelectHeadPhone() throws Throwable  {	
		strheadphonename = GetText(imgHeadPhoneName);		
		strheadphoneprice = GetText(imgHeadPhonePrice);		
		blnStatus = Click(imgHpBeatStudio,25);
		Assert.assertTrue( blnStatus, "Unable to click on headphone " + strheadphonename );		
		logger.info("Product Selected: " +strheadphonename );
		logger.info("Product Price is: " +strheadphoneprice);		
		return blnStatus;
	}
}
