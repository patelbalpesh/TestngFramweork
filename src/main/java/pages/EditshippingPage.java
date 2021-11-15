package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.PageBase;

public class EditshippingPage extends PageBase {

	public boolean blnStatus;
	
	@FindBy(xpath = "//*[@id=\"userSection\"]/div[1]/div[2]/a")
	public WebElement btnEditShipping;

	@FindBy(xpath = "//*[@id=\"userDetailsEditMode\"]/sec-form/div[3]/sec-view[1]/div/select")
	public WebElement sddSelectCountry;
	
	@FindBy(xpath = "//*[@id=\"next_btnundefined\"]")
	public WebElement btnnext;
	
	public EditshippingPage(WebDriver driver) {			
		super(driver);
	}
	
	public PaymentPage EditShipping() throws Throwable {		
		Click(btnEditShipping, 1);
		Thread.sleep(2000);
		SelectDropdownText(sddSelectCountry, "United States");
		Click(btnnext, 1);
		return new PaymentPage(pageDriver);		
	}	
}
