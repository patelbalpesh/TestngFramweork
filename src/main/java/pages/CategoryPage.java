package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import base.PageBase;

public class CategoryPage extends PageBase{

	public String strCategory;
	public boolean blnStatus;
	public String strValidation ="FOLLOW US";

	@FindBy(xpath = "//*[@class=\"roboto-regular center ng-scope\" and @translate =\"FOLLOW_US\" ]")
	public WebElement lblValidation;

	@FindBy(xpath = "//*[@id=\"speakersImg\"]")
	public WebElement imgSpeakerCat;

	@FindBy(xpath = "//*[@id=\"tabletsImg\"]")
	public WebElement imgTabletCat;

	@FindBy(xpath = "//*[@id=\"laptopsImg\"]")
	public WebElement imgLaptopCat;

	@FindBy(xpath = "//*[@id=\"miceImg\"]")
	public WebElement imgMiceCat;

	@FindBy(xpath = "//*[@id=\"headphonesImg\"]")
	public WebElement imgHeadPhoneCat;

	@FindBy(xpath = "//*[@id=\"img-special-offer\"]/figure/img")
	public WebElement imgSpecialCat;


	public CategoryPage(WebDriver driver) {	
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

	public boolean fn_SpeakerCat() throws Throwable {	
		blnStatus=fn_Validation();		
		logger.info("clicked on PayNow button");
		Assert.assertTrue( blnStatus, "Unable to pass validation string FOLLOW US");
		logger.info("validation passed found string FOLLOW US");		
		strCategory=GetAttribute(imgSpeakerCat,"aria-label");
		logger.info("Category Selected: " + strCategory);
		blnStatus=Click(imgSpeakerCat,10);		
		return true;
	}

	public void fn_TabletCat() {
		imgTabletCat.click();
		
	}

	public void fn_Laptop() {
		imgLaptopCat.click();
	}

	public boolean fn_HeadPhoneCat() throws InterruptedException ,Throwable   {	
				
		strCategory = GetAttribute(imgHeadPhoneCat,"aria-label");
		blnStatus = Click(imgHeadPhoneCat,10);				
		Assert.assertTrue( blnStatus, "Unable to click HeadPhone category image");		
		logger.info("Clicked on HeadPhone category image");
		return blnStatus;
	}

	public void fn_MiceCat() throws Throwable {
		blnStatus = Click(imgMiceCat,10);
		Assert.assertTrue( blnStatus, "Unable to click mice category image");
		logger.info("Clicked on mice category image");
	}

	public void fn_SpecialCat() throws Throwable {
		blnStatus = Click(imgSpecialCat,10);	
		Assert.assertTrue( blnStatus, "Unable to click on Special Category image");
		logger.info("Clicked on Special Category image");
	}
}
