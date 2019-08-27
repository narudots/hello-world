package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import coreapplicationclassesandinterfaces.Address;
import coreapplicationclassesandinterfaces.PaymentMethod;
import pages.FrontEndCommonPage;
import utils.ErrorHandlerUtils.TestException;
import utils.ExcelUtils.TestDataInput;
import utils.WebDriverControlUtils.WaitUtil;

public class BillingAddressAndPaymentProfileValidations extends BaseTest{		
	
	//************************************************************************************************
	// ** Internal method **
	//************************************************************************************************
	
	private static boolean launchAndValidateDifferentBillingAddresses(String[] BillingAddressDetailsInArray) throws Exception {
		
		try {
		
			Address NewBillingAddressObj 	= new Address() ;
			NewBillingAddressObj.setAttributes(BillingAddressDetailsInArray);
	
			// Navigate required pages to reach test screen
			
			CommonTestLibrary.launchDefaultProduct() ;

			return (CommonTestLibrary.purchaseProduct(	TestApplicationParams.getDefaultUser(), 
																						TestApplicationParams.getDefaultShippingAddress(),
																						NewBillingAddressObj, 
																						false, 
																						TestApplicationParams.getDefaultPaymentMethod(),
																						TestApplicationParams.getDefaultDiscount(), 
																						"Address")) ;
			}
			catch ( Exception e) { throw e ;}																									
	}		
	
	//************************************************************************************************
	// ** Internal method **
	//************************************************************************************************
	
	private static boolean launchAndValidateDifferentPaymentMethods(String[] PaymentDetailsInArray) throws Exception {
		
		try {
		
			PaymentMethod NewPaymentMethodObj 	= new PaymentMethod() ;
			NewPaymentMethodObj.setAttributes(PaymentDetailsInArray);
	
			// Navigate required pages to reach test screen
			
			CommonTestLibrary.launchDefaultProduct() ;

			return (CommonTestLibrary.purchaseProduct(	TestApplicationParams.getDefaultUser(), 
																						TestApplicationParams.getDefaultShippingAddress(),
																						TestApplicationParams.getDefaultBillingAddress(), 
																						true, 
																						NewPaymentMethodObj,
																						TestApplicationParams.getDefaultDiscount(), 
																						"Address")) ;
			}
			catch ( Exception e) { throw e ;}																									
	}		
	
	//************************************************************************************************
	// ** TEST 1 : CheckAddNewBillingAddresses **
	//************************************************************************************************
	
	@Test( dataProvider="AddressValidationTestData", groups ={"BillingAddressValidations"})

	public static void AddNewBillingAddress(String[] BillingAddressDetailsInArray){
		
		try {
			
			Assert.assertEquals( launchAndValidateDifferentBillingAddresses(BillingAddressDetailsInArray) ,true) ;				
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long)200);	
			Assert.assertEquals(new FrontEndCommonPage(Driver).logout(),true) ;	
			
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

	//************************************************************************************************
	// ** TEST 2 : ValidateDifferentAddressSaveOptions **
	//************************************************************************************************
	
	@Test(  dataProvider="AddressSaveOptionTestData", groups ={	"BillingAddressValidations"})
	
	public static void AddressSaveOptions(String[] BillingAddressArray){
		
		try {
			
			Assert.assertEquals( launchAndValidateDifferentBillingAddresses(BillingAddressArray) ,true) ;				
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long)200);	
			Assert.assertEquals(new FrontEndCommonPage(Driver).logout(),true) ;	
			
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

	//************************************************************************************************
	// ** TEST  3 : CreditCardAcceptedAsPaymentMethod
	//************************************************************************************************
	
	@Test( dataProvider = "CreditCardTestData", groups ={"PaymentMethodValidations"})
	
	public static void CreditCardPayment( String[] CreditCardDetailsInArray){

		try {			
					
			Assert.assertEquals( launchAndValidateDifferentPaymentMethods(CreditCardDetailsInArray),true) ;				
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long)200);	
			Assert.assertEquals(new FrontEndCommonPage(Driver).logout(),true) ;	
					
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
	//************************************************************************************************
	// ** TEST 4 : PayLaterAcceptedAsPaymentMethod
	//************************************************************************************************
	
	@Test(  groups ={"PaymentMethodValidation"})
	
	public static void PayLaterOption(){

		try {					
						
			PaymentMethod PaymentMethodObj 	= 	new PaymentMethod() ;
			
			PaymentMethodObj.setPaymentMethod("PayLater");
			
			CommonTestLibrary.launchDefaultProduct() ;

			Assert.assertEquals(CommonTestLibrary.purchaseProduct(	TestApplicationParams.getDefaultUser(), 
																						TestApplicationParams.getDefaultShippingAddress(),
																						TestApplicationParams.getDefaultBillingAddress(), 
																						true, 
																						PaymentMethodObj,
																						TestApplicationParams.getDefaultDiscount(), 
																						"Address"), true) ;
			
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long)200);	
			Assert.assertEquals(new FrontEndCommonPage(Driver).logout(),true) ;		
			
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
	
	//************************************************************************************************
	// ** TEST 6 : AddMoreBillingPaymentMethods
	//************************************************************************************************
	
/*	@Test(  dataProvider = "CreditCardTestData", groups ={"PaymentMethodValidations"})
	
	public static void MultipleCreditCards(String[] PaymentDetailsInArray){

		try {			
					
			Address NewBillingAddressObj 	= 	TestApplicationParams.getDefaultBillingAddress() ;						
			PaymentMethod PaymentMethodObj 	= 	TestApplicationParams.getDefaultPaymentMethod() ;			
						
			User UserCreated = CommonTestLibrary.launchDefaultProductAndEnterUserDetails() ;		
			
			TestLog.logTestStep("Enter the Default Billing Address Values");			
			TestLog.logTestStep("Enter the Default Payment Method and Submit");
				
			FrontEndSelectPaymentProfilePage AddNewBillingAddressPage = 
								new FrontEndSelectPaymentProfilePage(	Driver,
																		UserCreated,
																		NewBillingAddressObj,
																		PaymentMethodObj) ;
			
			// Create a billing payment method with default address and payment data
																		
			if (AddNewBillingAddressPage.createNewBillingAddressAndPaymentDetails()){					
								
				WaitUtil.waitFor(Driver, "THREAD_SLEEP", (long)1000);
				AddNewBillingAddressPage.clickAddNewPaymentMethod() ;
				
				// Create an additional payment method
				
				PaymentMethod AdditionalPayment = new PaymentMethod() ;
				
				AdditionalPayment.setAttributes(PaymentDetailsInArray);
				
				FrontEndSelectPaymentProfilePage AdditionalBillingPaymentMethod = 
						new FrontEndSelectPaymentProfilePage(	Driver,
																UserCreated,
																NewBillingAddressObj,
																AdditionalPayment) ;
						
				Assert.assertEquals(AdditionalBillingPaymentMethod.createNewBillingAddressAndPaymentDetails(), true) ;
										
				TestLog.logTestStep("Logout from the account");
											
				AdditionalBillingPaymentMethod.logout() ;								
				WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long)1000);
				
			}	
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
	
	//************************************************************************************************
	// ** TEST 5 : ErrorOnEnteringSameCustomerProfile
	//************************************************************************************************
	
	@Test(  groups ={"PaymentMethodValidations"})
	
	public static void SameCustomerProfileErrorCheck(){

		try {			
					
			Address NewBillingAddressObj 	= 	TestApplicationParams.getDefaultBillingAddress() ;
						
			PaymentMethod PaymentMethodObj 	= 	TestApplicationParams.getDefaultPaymentMethod() ;			
						
			User UserCreated = CommonTestLibrary.launchDefaultProductAndEnterUserDetails() ;		
			
			TestLog.logTestStep("Enter the Default Billing Address Values");			
			TestLog.logTestStep("Enter the Default Payment Method and Submit");
				
			FrontEndSelectPaymentProfilePage AddNewBillingAddressPage = 
															new FrontEndSelectPaymentProfilePage(
																	Driver,
																	UserCreated,
																	NewBillingAddressObj,
																	PaymentMethodObj) ;
	
			if (AddNewBillingAddressPage.createNewBillingAddressAndPaymentDetails()){			
				
				// Trying to enter the same profile again should raise an error
				
				WaitUtil.waitFor(Driver, "THREAD_SLEEP", (long)1000);
				AddNewBillingAddressPage.clickAddNewPaymentMethod() ;
				
				Assert.assertEquals(AddNewBillingAddressPage.createNewBillingAddressAndPaymentDetails()
									, true) ;
				
				TestLog.logTestStep("Logout from the account");
											
				AddNewBillingAddressPage.logout() ;								
				WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long)1000);				
			}	
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
	}	*/
	
	//************************************************************************************************					
	// ** DATA PROVIDER 1 : DomesticAndInternationAddresses	**
	//************************************************************************************************
			
	@DataProvider ( name = "AddressValidationTestData" )
	
	public static String[][] fetchBillingAddresses(){
		
		String[][] BillingAddressTestData ;
		
		BillingAddressTestData = TestDataInput.getMatchedInputTableRowsForApplCodeAndTestDataType(
													Address.TestDataSheetName,
													TestApplicationParams.getCurrentTestApplication(),
													"AddressValidationTest");				
		return (BillingAddressTestData);
		
	}		
	
	//************************************************************************************************					
	// ** DATA PROVIDER 2 : AddressSaveOptionTestData	**
	//************************************************************************************************
		
	@DataProvider ( name = "AddressSaveOptionTestData" )
	
	public static String[][] getAddressSaveOptionTestData(){
		
		String[][] AddressSaveOptionTestData;
		
		AddressSaveOptionTestData = TestDataInput.getMatchedInputTableRowsForApplCodeAndTestDataType(
		Address.TestDataSheetName,
		TestApplicationParams.getCurrentTestApplication(),
		"AddressSaveOptionTest");
			
		return (AddressSaveOptionTestData);
		
	}	
	
	//************************************************************************************************					
	// ** DATA PROVIDER 3 :CreditCardTestData	**
	//************************************************************************************************
			
	@DataProvider ( name = "CreditCardTestData" )
	
	public static String[][] getTestDataForCreditCardValidations(){
		
		String[][] CreditCardCheckTestData ;
		
		CreditCardCheckTestData = TestDataInput.getMatchedInputTableRowsForApplCodeAndTestDataType(
													PaymentMethod.TestDataSheetName,
													TestApplicationParams.getCurrentTestApplication(),
													"CreditCardTest");
													 		
		return (CreditCardCheckTestData);
		
	}	
		
}