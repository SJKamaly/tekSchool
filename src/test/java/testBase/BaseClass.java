package testBase;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	public static WebDriver driver;
	public Logger logger;
	public ResourceBundle rb;
	
	@BeforeClass
	@Parameters("browser")
	public void setup(String br) {
		rb = ResourceBundle.getBundle("config");
		logger = LogManager.getLogger(this.getClass());
		
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("excludeSwitches", new String [] {"enable-automation"});
		if(br.equalsIgnoreCase("chrome")) {
		driver = new ChromeDriver(options);
		}else if (br.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}else {
			driver = new FirefoxDriver();
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(rb.getString("appURL"));
		driver.manage().window().maximize();
		
	}
	@AfterClass
	public void tearDown() {
		driver.quit();
		
	}
	
	public String randomString() {
		String randomAlphabetics=RandomStringUtils.randomAlphabetic(7);
		return randomAlphabetics;
	}
	public String randomNumeric() {
		String randomNumerics=RandomStringUtils.randomNumeric(10);
		return randomNumerics;
	}
	
	public String randomAlphaNumeric() {
		String randomAlphabetics=RandomStringUtils.randomAlphabetic(4);
		String randomNumerics=RandomStringUtils.randomNumeric(4);
		return randomAlphabetics+"@"+randomNumerics;
	}
	
	public String captureScreen(String tname) {
		
		String timeStamp = new SimpleDateFormat("yyyymmddhhmmss").format(new Date());
		
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sournce = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir")+"\\screenshots\\"+ tname +" "+ timeStamp + " .png";
		
		try {
			FileUtils.copyFile(sournce, new File (destination));
		}catch (Exception e) {
			e.getMessage();
		}
		return destination;
			
	}
	

}
