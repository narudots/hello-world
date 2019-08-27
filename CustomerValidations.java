package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import coreapplicationclassesandinterfaces.User;
import pages.BackEndCommonPage;
import pages.BackEndHomePage;
import pages.FrontEndCommonPage;
import pages.FrontEndHomePage;
import utils.ErrorHandlerUtils.TestException;
import utils.ExcelUtils.TestDataInput;
import utils.WebDriverControlUtils.NavigationUtil;

public class CustomerValidations extends BaseTest {	

	//************************************************************************************************
	// ** TEST : checkIf_ValidUserCredentialsAreAccepted **
	//************************************************************************************************
	
	@Test( dataProvider = "UserLoginTestData", groups ={ "LoginValidations"})	
					
	public static void Login(String[] UserLoginDetailsInArray){
		
		//--------------------------------------------------------------------------------------------
		// 1) Launch HomePage and invoke FrontEndCommonPage(base class):: clickLogin
		// 2) Check for login form launch
		// 3) Call FrontEndCommonPage(base class)::login to enter the credentials and click login
		// 4) Validate the test success flag for SUCCESS/FAILURE		
		//--------------------------------------------------------------------------------------------		
	
		//Launch application home page and click on login link				
		
		try {
			
			User	UserObjForLogin  = new User() ;
			
			UserObjForLogin.setAttributes( UserLoginDetailsInArray) ;
			
			TestLog.logTestStep("Test Data Values for Test :  " 			
			+ " | Test Data Type : "+ UserObjForLogin.getTypeOfTestData()
			+ " | UserName: "+ UserObjForLogin.getUserName()
			+ " | Password: "+ UserObjForLogin.getUserPassword());
			
			TestLog.logTestStep("Launch Application");
			
			FrontEndCommonPage 	LoginPage 	= 	new FrontEndCommonPage(Driver, 
												UserObjForLogin) ;
			
			NavigationUtil.navigate(Driver, TestApplicationParams.getInitialTestURL()) ;	
			
			TestLog.logTestStep("Click Login");
			LoginPage.clickLogin() ;	
			
			TestLog.logTestStep("Enter Credentials and Click Login");

			Assert.assertEquals(true, LoginPage.login(FrontEndHomePage.getPageTitle()));
			
			if(!UserObjForLogin.isInvalid())
			{
				TestLog.logTestStep("Login Success!");
				
				if( Driver.getTitle().equals( BackEndHomePage.PageTitle))
					new BackEndCommonPage(Driver).logout();
				else
					new FrontEndCommonPage(Driver).logout();
			}
			else
				TestLog.logTestStep("Login Failed. Error Message Thrown");			
		} 
		catch ( TestException Te)
		{
			TestLog.logTestStep("Error/Exception : " + Te.getExceptionName());
			Te.printException();
			assert(false);
		}		
		catch (InterruptedException e){
			//do nothing		
		}
		catch ( Exception e)
		{
			e.printStackTrace();
			TestLog.logTestStep("Error/Exception " + e.getMessage());
			assert(false) ;
		}
	}
	
	@Test( dataProvider = "PasswordResetTestData", groups ={ "PasswordValidations"})	
	
	public static void PasswordResetTest() {
		
	}
	
	//************************************************************************************************
	// ** DATA PROVIDER 1: ValidLoginCredentials	**
	//************************************************************************************************
	
	@DataProvider ( name = "UserLoginTestData" )
	
	public static String[][] ValidUserData(){
		
		String [][] UserLoginTestData = TestDataInput.getMatchedInputTableRowsForApplCodeAndTestDataType(
										User.TestDataSheetName,
										TestApplicationParams.getCurrentTestApplication(),
										"UserLoginTest") ;				 		
		return (UserLoginTestData);
	}
}