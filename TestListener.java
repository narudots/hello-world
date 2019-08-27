package utils.TestOutputReportingUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.relevantcodes.extentreports.LogStatus;

import tests.BaseTest;

public class TestListener implements ITestListener, ISuiteListener, IInvokedMethodListener {

	
	// This will execute before the Suite start

	@Override
	public void onStart(ISuite arg0) {
		System.out.println(">> Automation Execution of Test Suite : "+ arg0.getName() + " Begins") ;		
	}

	// This will execute, once the Suite is finished

	@Override
	public void onFinish(ISuite arg0) {		
		System.out.println("<< Automation Execution of Test Suite : "+ arg0.getName() + " is Complete") ;		
	}	

	// This will execute before the test batch starts
	@Override
	public void onStart(ITestContext context) {
		BaseTest.resetTestStepCounter();
		System.out.println(">> Execution of Test Batch : | " + context.getName() + " |Begins");		
	}

	// This will execute after the test batch ends
	@Override
	public void onFinish(ITestContext context) {
		
		BaseTest.TestLog.getTestReport().endTest(BaseTest.TestLog.getTestLogger());
		BaseTest.TestLog.getTestReport().flush();
		
	}

	// This will execute only when a test method starts

	public void onTestStart(ITestResult arg0) {
		
		BaseTest.resetTestStepCounter();
		
		//System.out.println(">> Execution of Test : | " +  arg0.getMethod().getMethodName() + " | Begins");
		
		/*ExtentTestManager.setTestLogger(ExtentTestManager.getTestReport().startTest( 
				
				"TC#"+ BaseTest.getTestCounter()+ ":"+ arg0.getMethod().getMethodName(),""));			

		BaseTest.incrementTestCounter();*/
	}

	// This will execute only if any of the test(@Test) get skipped

	public void onTestSkipped(ITestResult arg0) {
		System.out.println("!! Execution of Test : | " +  arg0.getMethod().getMethodName() + " | Skipped");
	}

	@Override
    public void onTestSuccess(ITestResult arg0) {        
		System.out.println("** Test Result Success: Test " +  arg0.getMethod().getMethodName()+ " passed\n");		
		BaseTest.TestLog.getTestLogger().log(LogStatus.PASS, "Test Case Passed");
    }
	 
    @Override
    public void onTestFailure(ITestResult arg0) {
    	
    	System.out.println("~~ Test Result Failure: Test " +  arg0.getMethod().getMethodName() + "failed\n"); 
        
    	WebDriver webDriver = BaseTest.getDriver();

        if (webDriver != null)
        {
    	
	        //Take base64Screenshot screenshot.
	        String base64Screenshot = "data:image/png;base64,"+((TakesScreenshot)webDriver).
	                getScreenshotAs(OutputType.BASE64);
	        
	        //Extent Reports log and screenshot operations for failed tests.
	        BaseTest.TestLog.getTestLogger().log(LogStatus.FAIL, arg0.getMethod().getMethodName(),
	        BaseTest.TestLog.getTestLogger().addBase64ScreenShot(base64Screenshot));
	 
        }
        
    } 
    
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    	//Skipping for now
    }

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		//Skipping for now
		
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		//Skipping for now
		
	}
		
}