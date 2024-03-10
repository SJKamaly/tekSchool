package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegisterationPage extends BasePage{

	public AccountRegisterationPage(WebDriver driver) {
		super(driver);
			
	}
	@FindBy(id="nameInput")WebElement txtName;
	@FindBy(xpath="//input[@id='emailInput']")WebElement txtEmail;
	@FindBy(xpath="//input[@id='passwordInput']")WebElement txtPassword;
	@FindBy(xpath="//input[@id='confirmPasswordInput']")WebElement txtConfPassword;
	@FindBy(xpath="//button[@id='signupBtn']")WebElement btnSignUp;
	
	@FindBy(xpath="//p[@class='account__payment-ad-text']")WebElement txtConfirmation;
	
	public void setName(String name) {
		txtName.sendKeys(name);
		
	}
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}
	public void setConfPassword(String confPwd) {
		txtConfPassword.sendKeys(confPwd);
	}
	public void clickSignUp() {
		btnSignUp.click();
	}
	public String getConfirmationTxt() {
		try {
		return (txtConfirmation.getText());
		}catch (Exception e) {
			return (e.getMessage());
		}
	}

}
