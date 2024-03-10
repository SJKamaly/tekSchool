package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegisterationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

//Adding comment 
public class TC_001_AccountRegisterationTest extends BaseClass{
	
	@Test
	public void testAccountRegiseration() {
		
		logger.info("***** Started TC_001_AccountRegisterationTest *****");
		
		try {
		HomePage hp = new HomePage(driver);
		
		hp.clickSinginLnk();
		hp.clickCreateAccountLnk();
		
		AccountRegisterationPage arp = new AccountRegisterationPage(driver);
		
		arp.setName(randomString());
		arp.setEmail(randomString()+"@gmail.com");
		String pwd=randomAlphaNumeric();
		arp.setPassword(pwd);
		arp.setConfPassword(pwd);
		arp.clickSignUp();
		
		String confirmationText=arp.getConfirmationTxt();
		Assert.assertEquals(confirmationText, "TekSchool accepts most major credit and debit cards:");
		
		}catch(Exception e) {
			Assert.fail();
		}
		logger.info("***** Started TC_001_AccountRegisterationTest *****");
	}

}
