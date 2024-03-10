package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

	public HomePage(WebDriver driver) {
		super(driver);
		
	}
	@FindBy(xpath="//a[@id='signinLink']")WebElement lnkSingin;
	@FindBy(xpath="//a[@id='newAccountBtn']")WebElement lnkCreateAccount;
	
	
	public void clickSinginLnk() {
		lnkSingin.click();
	}
	public void clickCreateAccountLnk() {
		lnkCreateAccount.click();
	}

}
