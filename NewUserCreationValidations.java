package tests;
import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import coreapplicationclassesandinterfaces.User;
import pages.FrontEndCommonPage;
import pages.FrontEndNewUserProfilePage;
import pages.FrontEndSPCPage;
import pages.FrontEndShoppingCartPage;
import utils.ErrorHandlerUtils.TestException;
import utils.ExcelUtils.TestDataInput;
import utils.WebDriverControlUtils.WaitUtil;

public class NewUserCreationValidations extends BaseTest{
	

	//************************************************************************************************
	// ** TEST : Check if new user can be created successfully
	//************************************************************************************************
	
	@Test( dataProvider = "NewUserCreationTestData", groups ={"UserCreationValidations1"})	
		
	public static void CreateNewUser( String[] NewUserDetailsInArray){
		
		User NewUserObj  = 	new User() ;
		
		NewUserObj.setAttributes( NewUserDetailsInArray) ;		
		
		TestLog.logTestStep("Test Type :  " + NewUserObj.getTypeOfTest()
		+ " | Test Data Type: "+ NewUserObj.getTypeOfTestData()
		+ " | User Name Generated - Password : "+ NewUserObj.getUserName()+ "-"+ NewUserObj.getUserPassword() +"|");				
		
		//Test Validation Steps
		// 1) Launch URL for Customer checkout
		// 2) Call CustomerCheckoutPage :: createNewCustomer()
		// 3) Validate the test success flag for SUCCESS
		
		try {
			
			CommonTestLibrary.launchDefaultProduct() ;
			
			assertEquals(CommonTestLibrary.purchaseProduct(NewUserObj, 
																			TestApplicationParams.getDefaultShippingAddress(),
																			TestApplicationParams.getDefaultBillingAddress(), 
																			true, 
																			TestApplicationParams.getDefaultPaymentMethod(),
																			TestApplicationParams.getDefaultDiscount(), 
																			"User"), true);
			
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long)200);							
			new FrontEndCommonPage(Driver).logout() ;					
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long)500);
			
		} 
		catch ( TestException Te)
		{
			if (Te.getExceptionName().equals("INTENDED PAGE WAS NOT LAUNCHED")) {
				TestLog.logTestStep("\"Error/Exception : \" Default Product Page could not be launched. Check Default Product link in Test Data");
			}
			else TestLog.logTestStep("Error/Exception : " + Te.getExceptionName());
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

	//************************************************************************************************
	// ** TEST : Check if existing user can checkout
	//************************************************************************************************
	
	@Test( dataProvider = "ExistingUserTestData", groups ={"UserCreationValidations"})	
		
	public static void ExistingUserCheckout( String[] NewUserDetailsInArray){
		
		User NewUserObj  = 	new User() ;
		
		NewUserObj.setAttributes( NewUserDetailsInArray) ;		
		
		TestLog.logTestStep("Test Type :  " + NewUserObj.getTypeOfTest()
		+ " | Test Data Type: "+ NewUserObj.getTypeOfTestData()
		+ " | User Name Generated - Password : "+ NewUserObj.getUserName()+ "-"+ NewUserObj.getUserPassword() +"|");				
				
		try {
			
			CommonTestLibrary.launchDefaultProduct() ;
			
			assertEquals(CommonTestLibrary.purchaseProduct(NewUserObj, 
																			TestApplicationParams.getDefaultShippingAddress(),
																			TestApplicationParams.getDefaultBillingAddress(), 
																			true, 
																			TestApplicationParams.getDefaultPaymentMethod(),
																			TestApplicationParams.getDefaultDiscount(), 
																			"User"), true);
			
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long)200);							
			new FrontEndCommonPage(Driver).logout() ;					
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long)500);
			
		} 
		catch ( TestException Te)
		{
			if (Te.getExceptionName().equals("INTENDED PAGE WAS NOT LAUNCHED")) {
				TestLog.logTestStep("\"Error/Exception : \" Default Product Page could not be launched. Check Default Product link in Test Data");
			}
			else TestLog.logTestStep("Error/Exception : " + Te.getExceptionName());
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
	
	//************************************************************************************************
	// ** TEST : Check if  User Details Can be edited during checkout
	//************************************************************************************************
	
	@Test( dataProvider = "EditUserTestData", groups ={"UserCreationValidations"})	
		
	public static void EditUserDetailsAtCheckout( String[] NewUserDetailsInArray){
		
		User NewUserObj  = 	new User() ;
		
		NewUserObj.setAttributes( NewUserDetailsInArray) ;		
		
		TestLog.logTestStep("Test Type :  " + NewUserObj.getTypeOfTest()
		+ " | Test Data Type: "+ NewUserObj.getTypeOfTestData()
		+ " | User Name Generated - Password : "+ NewUserObj.getUserName()+ "-"+ NewUserObj.getUserPassword() +"|");				
			
		try {
			
			CommonTestLibrary.launchDefaultProduct() ;
			
			assertEquals(CommonTestLibrary.purchaseProduct(	NewUserObj, 
															TestApplicationParams.getDefaultShippingAddress(),
															TestApplicationParams.getDefaultBillingAddress(), 
															true, 
															TestApplicationParams.getDefaultPaymentMethod(),
															TestApplicationParams.getDefaultDiscount(), 
															"User"), true);
			
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long)200);							
			new FrontEndCommonPage(Driver).logout() ;					
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long)500);
			
		} 
		catch ( TestException Te)
		{
			if (Te.getExceptionName().equals("INTENDED PAGE WAS NOT LAUNCHED")) {
				TestLog.logTestStep("\"Error/Exception : \" Default Product Page could not be launched. Check Default Product link in Test Data");
			}
			else TestLog.logTestStep("Error/Exception : " + Te.getExceptionName());
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

	//************************************************************************************************
	// ** TEST : Check if all the required errors are thrown for blank fields
	//************************************************************************************************
	
	@Test( groups ={"UserCreationValidations"})	
		
	public static void RequiredErrorValidationOnUserDetailsSection(){

		TestLog.logTestStep("Initiate default product checkout");		
		TestLog.logTestStep("Launch New User Profile Page");
		
		//Navigate to the TestProductCheckoutURL 
		
		try {
			
			if (!CommonTestLibrary.getCurrentPageTitle().contains(FrontEndSPCPage.PageTitle) )
			{				
				CommonTestLibrary.launchDefaultProduct() ;				
			}

			FrontEndNewUserProfilePage	UserAccountSummarySection = 	new FrontEndNewUserProfilePage (	
																Driver,
																TestApplicationParams.getDefaultUser());
			
			TestLog.logTestStep("Check for errors when required/mandatory values are blank");
			
			Assert.assertEquals( UserAccountSummarySection.checkRequiredFieldErrors() , true) ;		
			
			new FrontEndShoppingCartPage(Driver).deleteAllInCart() ;
		} 		
		catch ( TestException Te)
		{
			if (Te.getExceptionName().equals("INTENDED PAGE WAS NOT LAUNCHED")) {
				TestLog.logTestStep("\"Error/Exception : \" Default Product Page could not be launched. Check Default Product link in Test Data");
			}
			else TestLog.logTestStep("Error/Exception : " + Te.getExceptionName());
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
	
	//************************************************************************************************
	// ** TEST : Check for error when weak passwords are used
	//************************************************************************************************	

	@Test( dataProvider = "PasswordStrengthTestData", groups ={	"PasswordValidations"})	
	public static void PasswordStrengthValidation( String[] NewUserDetailsInArray){
			
		User NewUserObj  = 	new User() ;		
		NewUserObj.setAttributes( NewUserDetailsInArray) ;		
		
		TestLog.logTestStep("| Test Data Type: "+ NewUserObj.getTypeOfTestData()
									+ " | Password Used: "+ NewUserObj.getUserPassword() +"|");				
				
		try {
		
			if (!CommonTestLibrary.getCurrentPageTitle().contains(FrontEndSPCPage.PageTitle) )
			{				
				CommonTestLibrary.launchDefaultProduct() ;				
			}
						
			FrontEndNewUserProfilePage	UserAccountSummarySection = new FrontEndNewUserProfilePage(Driver,
																						 																						NewUserObj);
			
			TestLog.logTestStep("Enter Field Values and Submit");
			
			Assert.assertEquals( UserAccountSummarySection.checkForWeakPasswordError() , true) ;				
						
		} 
		catch ( TestException Te)
		{
			if (Te.getExceptionName().equals("INTENDED PAGE WAS NOT LAUNCHED")) {
				TestLog.logTestStep("\"Error/Exception : \" Default Product Page could not be launched. Check Default Product link in Test Data");
			}
			else TestLog.logTestStep("Error/Exception : " + Te.getExceptionName());
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
	//************************************************************************************************
	// ** DATA PROVIDER 1 : NewUserData	**
	//************************************************************************************************
	
	@DataProvider ( name = "NewUserCreationTestData" )	
	public static String[][] fetchNewUserData(){
	
		String [][] UserCreationTestData = 	TestDataInput.getMatchedInputTableRowsForApplCodeAndTestDataType(											
											User.TestDataSheetName,
											TestApplicationParams.getCurrentTestApplication(),
											"NewUserCreationTest") ;
		return (UserCreationTestData); 	
	}
	
	//************************************************************************************************
	// ** DATA PROVIDER 2 : PasswordStrengthTestData	**
	//************************************************************************************************	
	
	@DataProvider ( name = "PasswordStrengthTestData" )	
	public static String[][] fetchDifferentPasswords(){
	
		String [][] DifferentPasswordsForStrengthTest = 	TestDataInput.getMatchedInputTableRowsForApplCodeAndTestDataType(											
															User.TestDataSheetName,
															TestApplicationParams.getCurrentTestApplication(),
															"PasswordStrengthTest") ;
		return (DifferentPasswordsForStrengthTest); 	
	}
	
	//************************************************************************************************
	// ** DATA PROVIDER 3 : ExistingUserData	**
	//************************************************************************************************
	
	@DataProvider ( name = "ExistingUserTestData" )	
	public static String[][] fetchExistingUserData(){
	
		String [][] ExistingUserTestData = 	TestDataInput.getMatchedInputTableRowsForApplCodeAndTestDataType(											
											User.TestDataSheetName,
											TestApplicationParams.getCurrentTestApplication(),
											"ExistingUserTest") ;
		return (ExistingUserTestData); 	
	}
	//************************************************************************************************
	// ** DATA PROVIDER 4 : EditUserData	**
	//************************************************************************************************
	
	@DataProvider ( name = "EditUserTestData" )	
	public static String[][] fetchEditUserData(){
	
		String [][] EditUserTestData = 	TestDataInput.getMatchedInputTableRowsForApplCodeAndTestDataType(											
										User.TestDataSheetName,
										TestApplicationParams.getCurrentTestApplication(),
										"EditUserTest") ;
		return (EditUserTestData); 	
	}
	
}