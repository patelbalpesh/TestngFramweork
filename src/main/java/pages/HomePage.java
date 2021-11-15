package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import base.PageBase;

public class HomePage extends PageBase{

	public boolean blnStatus;
	public String strusername; 
	public String struname="alpesh";
	public String struname1="alpesh1";
	public String strValidation ="FOLLOW US";

	String strxpathprice= "//li/img[@id=\"15\"]//following-sibling::p[2]/a[1]";

	@FindBy(xpath = "//*[@id=\"menuUser\"]")
	public WebElement MenuUser;

	@FindBy(xpath = "//*[@id=\"menuUser123\"]")
	public WebElement wemenuuser1;

	@FindBy(xpath = "//input[@name=\"username\"]")
	public WebElement txtUserName;

	@FindBy(xpath = "//input[@name=\"password\"]")
	public WebElement txtPassword;

	@FindBy(xpath = "//*[@id=\"sign_in_btnundefined\" and @class=\"sec-sender-a ng-scope\"]")
	public WebElement btnSignIn;

	@FindBy(xpath = "//*[@class=\"hi-user containMiniTitle ng-binding\"]")
	public WebElement weMenuUserLink;

	@FindBy(xpath = "//*[@class=\"roboto-regular center ng-scope\" and @translate =\"FOLLOW_US\" ]")
	public WebElement lblValidation;

	public  HomePage(WebDriver driver){	
		super(driver);	
	}	

	public boolean  fn_UserMenuClick() throws Throwable  {	
		logger.debug("User is about to login");					
		blnStatus=Click(MenuUser,0);			
		return blnStatus;
	}	
		
	public void fn_ValidateLaunch() throws Throwable {
		MenuUser.isDisplayed();
	}

	public boolean fn_Validation() throws Throwable {
		String ValidationString = GetText(lblValidation);
		if(strValidation.equals(ValidationString)){			
			return false;
		}else {			
			return true;
		}
	}

	public  boolean fn_UserLogin() throws Throwable  {			
		blnStatus = TypeInto(txtUserName, struname);
		if(!blnStatus) 
		{
			return blnStatus;
		}	
		logger.info("Entered UserName in the username field");
		blnStatus = TypeInto(txtPassword, "iDeliver1");
		if(!blnStatus) 
		{
			return blnStatus;
		}	
		Assert.assertTrue( blnStatus, "Unable to entered password in the password field");
		logger.info("Entered password in the password field");
		
		blnStatus=Click(btnSignIn,5);
		if(!blnStatus) 
		{
			return blnStatus;
		}	
		logger.info("Clicked on Signin button");
		return blnStatus;
	}
}