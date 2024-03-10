package utilities;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import testBase.BaseClass;

public class ExtentReportManager implements ITestListener{

	public ExtentSparkReporter sparkReport; 
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext context) {
		
		String timeStamp= new SimpleDateFormat("yyyymmddhhmmss").format(new Date());
		repName = "Test-Report-"+ timeStamp + " .html";
		
		sparkReport = new ExtentSparkReporter(".\\reports\\"+repName);
		
		sparkReport.config().setDocumentTitle("Opencart Automation Report");
		sparkReport.config().setReportName("Opencart Functional Testing");
		sparkReport.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		
		extent.attachReporter(sparkReport);
		extent.setSystemInfo("Application Name ", "Open Cart");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Tester", "Name");
		extent.setSystemInfo("Operation system", System.getProperty("os.name"));
		extent.setSystemInfo("Browser name", System.getProperty("user.name"));
		
	}
	public void onTestSuccess(ITestResult result) {
		test=extent.createTest(result.getName());
		test.log(Status.PASS, "Test case passed is: "+ result.getName());
		
	}
	public void onTestFailure(ITestResult result) {
		test=extent.createTest(result.getName());
		test.log(Status.FAIL, "Test case failed is: "+ result.getName());
		test.log(Status.FAIL, "Test case failure casue is: "+ result.getThrowable());
		
		try {
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		}catch (Exception e) {
			e.getStackTrace();
		}
		
	}
	public void onTestSkipped(ITestResult result ) {
		test=extent.createTest(result.getName());
		test.log(Status.SKIP, "Test case skipped is: "+ result.getName());
		
		
	}
	public void onFinish(ITestContext context) {
		extent.flush();
		
		/*
		 * try { URL url = new
		 * URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
		 * 
		 * // Create the email message 
		 * ImageHtmlEmail email = new ImageHtmlEmail();
		 * email.setDataSourceResolver(new DataSourceUrlResolver(url));
		 * email.setHostName("smtp.googlemail.com"); 
		 * email.setSmtpPort(465);
		 * email.setAuthenticator(new DefaultAuthenticator("pavanoltraining@gmail.com","password")); 
		 * email.setSSLOnConnect(true);
		 * email.setFrom("pavanoltraining@gmail.com"); //Sender
		 * email.setSubject("Test Results");
		 * email.setMsg("Please find Attached Report....");
		 * email.addTo("pavankumar.busyqa@gmail.com"); //Receiver 
		 * email.attach(url, "extent report", "please check report..."); 
		 * email.send(); // send the email 
		 * }
		 * catch(Exception e) { e.printStackTrace(); }
		 */
		
	}
}
