package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.TestBase;

public class Google extends TestBase{
	@Test
	
	public void test() throws InterruptedException {			
		getDriver().navigate().to("https://www.google.com/");
		System.out.println("Browser title is " + getDriver().getTitle());
		WebElement element = getDriver().findElement(By.name("q"));
		element.sendKeys("Selenium WebDriver"); 
		element.submit();
		Thread.sleep(5000);			
	}	
}