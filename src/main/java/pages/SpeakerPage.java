package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.PageBase;

public class SpeakerPage extends PageBase {

	public String strspeakername;
	public String strspeakerprice;
	boolean blnStatus;

	@FindBy(xpath = "//*[@id=\"20\"]")
	public WebElement Bosebluetooth;

	@FindBy(xpath = "//*[@id=\"20\"]/following-sibling::p[1]/a[1]")
	public WebElement speakername;

	@FindBy(xpath = "//*[@id=\"20\"]/following-sibling::p[1]/a[1]")
	public WebElement speakerprice;

	@FindBy(xpath = "//*[@id=\"25\"]")
	public WebElement Bosewireless;

	@FindBy(xpath = "//*[@id=\"24\"]")
	public WebElement HPRoarMini;

	@FindBy(xpath = "//*[@id=\"21\"]")
	public WebElement HPRoarplus;

	@FindBy(xpath = "//*[@id=\"22\"]")
	public WebElement HPRoarwireless;

	@FindBy(xpath = "//*[@id=\"19\"]")
	public WebElement HPS9500;

	@FindBy(xpath = "//*[@id=\"23\"]")
	public WebElement LogitechX100;

	public SpeakerPage(WebDriver driver) {	
		super(driver);
	}			
		
	public AddtocartPage fn_SelectSpeaker() throws Throwable {
		Bosebluetooth.click();
		strspeakername = speakername.getText();
		strspeakerprice = speakerprice.getText();				
		return new AddtocartPage(pageDriver);
	}	
}

