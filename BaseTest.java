package tests;

import java.lang.reflect.Method;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import config.BrowserStackConfig;
import config.TestApplicationConfig;
import config.TestEnvironmentConfig;
import config.TestWebDriverConfig;
import utils.ErrorHandlerUtils.ERROR;
import utils.ErrorHandlerUtils.ErrorHandler;
import utils.ExcelUtils.ExcelSetUpUtil;
import utils.TestOutputReportingUtils.ExtentTestManager;

public class BaseTest {	
	
	static TestEnvironmentConfig		TestEnvironmentParams ;	
	static TestApplicationConfig		TestApplicationParams ;
	static WebDriver					Driver ;	
	static JavascriptExecutor 			JSExecutorForTest ;
	static long 						testcasecounter ;
	static long 						teststepcounter ;
	static String 						PreviousMethodInvoked = "";
	static double   					additionaltestscounter ;
	public static ExtentTestManager 	TestLog ;
	static BrowserStackConfig 			MultipleBrowserTestConfig ;
	private static String 				RunEnvironment ;
					
	@BeforeSuite ( alwaysRun = true)
	@Parameters({"testDataInputFilePath", "runEnvironment"})
	public static void doBeforeTheTestSuiteBeginsExecuting(String InputTestDataFilePath, String Env) throws Exception
	{
		testcasecounter = 0 ;
		
		// Initialize the Extent Report to log the Test Results. This report will be common to all test batches executed.		

		ExcelSetUpUtil.getTestDataWorkbook(InputTestDataFilePath);
		RunEnvironment = Env ;
		
	}
	
	@BeforeTest ( alwaysRun = true)
	
	@Parameters({"launchUrlWithUserCredentials",
				 "applicationCode",
				 "homePageTitle",
				 "applicationURL", 
				 "browser",
				 "browserDriverEXEFilePath"})
	
	public static void doBeforeEachTestBatch ( 	String ApplicationLaunchURL,
												String ApplicationCode,
												String HomePageTitle,
												String ApplicationURL, 
												String Browser,
												String BrowserDriverPath) throws Exception {	
		
		TestEnvironmentParams 	= new TestEnvironmentConfig(Browser); 
		 
		TestApplicationParams 	= new TestApplicationConfig(ApplicationLaunchURL,
															ApplicationCode,
															HomePageTitle,
															ApplicationURL) ;
		
		TestLog 				= new ExtentTestManager() ;
		
		resetTestCaseCounter();
		
		TestLog.setTestReport(TestApplicationParams.getCurrentTestApplication());
		
		TestApplicationParams.CreateDefaultsObjectsForTest(ApplicationCode) ;	
		
		try{			
			if (RunEnvironment.equals("Local")) {
				TestWebDriverConfig		WebDriverConfig 		= new TestWebDriverConfig();
				Driver	= WebDriverConfig.setUpWebDriverForTest(Browser,BrowserDriverPath ) ;		
			}
			else
			{
				BrowserStackConfig MultipleBrowserTestConfig 	= new BrowserStackConfig() ;
				Driver = MultipleBrowserTestConfig.setUpBrowserStackAndRemoteDriver(Browser, TestApplicationParams) ;				
			}			
			
			if ( Driver == null)
			{				
				ERROR.initError(ErrorHandler.INVALIDBROWSER);
				ErrorHandler.logError();				
			}			
			JSExecutorForTest = (JavascriptExecutor)Driver;
		}
		catch ( WebDriverException e) {			
			ERROR.initError(ErrorHandler.INVALIDBROWSERFILEPATH);
			ErrorHandler.logError();				
			e.printStackTrace();
		}
		catch ( IllegalArgumentException e) {
			ERROR.initError(ErrorHandler.INVALIDBROWSERFILEPATH);
			ErrorHandler.logError();				
			e.printStackTrace();		
		}		
		catch ( NullPointerException e){			
			ERROR.initError(ErrorHandler.INVALIDBROWSER);
			ErrorHandler.logError();			
		}
		catch (Exception e) {			
			e.printStackTrace();
		}					
	}
	
	@BeforeMethod( alwaysRun = true)
	public void logTestCase(Method method, Object[]testData) {	   
		
		String TestContext = "" ;		
		String CurrentMethodName = method.getName() ;
		

			double testcasecountfordisplay ;	
			try {
				
				if (testData.length >0) {
					
					if ( PreviousMethodInvoked.equals(CurrentMethodName))
						additionaltestscounter= additionaltestscounter + 0.1 ;
					else
					{
						incrementTestCounter() ;
						additionaltestscounter = 0.1 ;
					}
					
					if (! CurrentMethodName.equals("IOFMMembershipUpgrade")) 
					{	
					
						String [] dummyobject = (String[]) testData[0] ;
						TestContext = "_" + dummyobject[2] ;
					}
					else {
						TestContext ="- Upgrade "+(String) testData[3]+ " to "+ testData[4] ;
					}
					testcasecountfordisplay = getTestCounter() + additionaltestscounter ;
					
					TestLog.setTestLogger(TestLog.getTestReport().startTest( 
							"TC#"+ testcasecountfordisplay + ": " + CurrentMethodName + TestContext ,""));					
						
				}
				else {
						incrementTestCounter() ;
						TestLog.setTestLogger(TestLog.getTestReport().startTest( 
						"TC#"+ BaseTest.getTestCounter() + ": " + CurrentMethodName +TestContext ,""));		
						
				}
				PreviousMethodInvoked = CurrentMethodName ;
			} 
			catch (Exception e) {			
				e.printStackTrace();
				TestLog.logTestStep("Error/Exception " + e.getMessage());
				assert(false) ;
		}
	}

	public static WebDriver getDriver(){
		
		return Driver ;
	}
	
	public static JavascriptExecutor getJSExecutor(){
		
		return JSExecutorForTest;
	}
	
	public static long getTestStepCounter()
	{
		return teststepcounter ;		
	}
	
	public static void resetTestStepCounter()
	{
		teststepcounter = 1 ;		
	}
	
	public static void resetTestCaseCounter()
	{
		testcasecounter = 0 ;		
	}
	
	public static void incrementTestStepCounter()
	{
		++teststepcounter ;		
	}
	
	public static long getTestCounter()
	{
		return testcasecounter ;		
	}
	
	public static void incrementTestCounter()
	{
		++testcasecounter ;		
	}
	
	@AfterTest(alwaysRun = true)
	public static void closeAndExit() throws Exception
	{
		Driver.close();
		Driver.quit();		
		//VideoCapture.stopRecording();
	}
}