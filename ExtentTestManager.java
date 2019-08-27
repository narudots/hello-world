package utils.TestOutputReportingUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import tests.BaseTest; 

public class ExtentTestManager {
	
	private  ExtentReports DetailedTestReport;
	private  ExtentTest 	 TestResultLogger ;

	public  void setTestReport(String ApplicationCode){    	
	    
        String workingDir = System.getProperty("user.dir");
        DetailedTestReport = new ExtentReports(workingDir+"\\ExtentReports\\"+ApplicationCode+"TestResults.html", true);  
        TestResultLogger = new ExtentTest(workingDir, workingDir) ;
         
    }
	
	public  ExtentReports getTestReport(){
		
		return DetailedTestReport ;
	}
	
	public  ExtentTest getTestLogger(){
		
		return TestResultLogger ;
	}
	
	public  void logTestStep(String TestStepDescription){
		TestResultLogger.log(LogStatus.INFO, "Test Step "+ BaseTest.getTestStepCounter()+" : " + TestStepDescription);
		BaseTest.incrementTestStepCounter();
	}
	public  void logTestStepResult(boolean result, String TestStepText) 
	{
		if (result) logTestStepResultSuccess(TestStepText);
		else if (!result) logTestStepResultFailure(TestStepText);
		else logTestStep(TestStepText);
	}
	
	public  void logTestStepResultSuccess(String TestStepDescription){
		TestResultLogger.log(LogStatus.PASS, TestStepDescription);
	}
	
	public  void logTestStepResultFailure(String TestStepDescription){
		TestResultLogger.log(LogStatus.FAIL, TestStepDescription);
	}

	public  void setTestLogger(ExtentTest TestLogger) {

		TestResultLogger = TestLogger ;		
	}
	
	public  void captureScreenShot(String Scenario){
		
		WebDriver webDriver = BaseTest.getDriver();

       if (webDriver != null)
        {
    	
	        //Take base64Screenshot screenshot.
	        String base64Screenshot = "data:image/png;base64,"+((TakesScreenshot)webDriver).
	               getScreenshotAs(OutputType.BASE64);
	        
	        if ( !Scenario.equals(""))
	        	TestResultLogger.log(LogStatus.INFO, "Screen Capture  : " + Scenario);
	        TestResultLogger.log(LogStatus.INFO,
	        		getTestLogger().addBase64ScreenShot(base64Screenshot));
	 
        }
	}
}