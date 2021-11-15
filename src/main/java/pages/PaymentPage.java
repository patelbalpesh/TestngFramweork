package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import base.PageBase;

public class PaymentPage extends PageBase{
	public String strpaymentmethod;
	boolean blnStatus;
	public String strValidation ="FOLLOW US";
	
	@FindBy(xpath = "//*[@class=\"roboto-regular center ng-scope\" and @translate =\"FOLLOW_US\" ]")
	public WebElement lblValidation;

	@FindBy(xpath = "//*[@id=\"pay_now_btn_MasterCredit\"]")
	public WebElement btnPayNow;

	@FindBy(xpath = "//label[contains(text(),'Choose payment method below')]")
	public WebElement lblPaymentMethod;

	@FindBy(xpath = "//*[@id=\"orderNumberLabel\"]")
	public WebElement ordernum;

	public PaymentPage(WebDriver driver) {		
		super(driver);
	}

	public boolean fn_Validation() throws Throwable {
		String ValidationString = GetText(lblValidation);
		
		if(strValidation.equals(ValidationString)){
			return true;
		}
		return false;
	}
	
	public boolean fn_PaymentMentodMasCard() throws Throwable ,InterruptedException  {			
		strpaymentmethod = GetText(lblPaymentMethod);			
		logger.info("Payment method: " + strpaymentmethod);	
		blnStatus = Click(btnPayNow,10);		
		return blnStatus;
	}
	
	
	public void fn_OrderDetails() throws Throwable ,InterruptedException  {			
		String strOrdernumber;		
		logger.info("Order Successfully Created");
		strOrdernumber = GetText(ordernum);
		logger.info("Customer Order Number is: " +strOrdernumber );	
		
	}

}
