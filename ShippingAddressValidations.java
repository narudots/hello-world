package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import coreapplicationclassesandinterfaces.Address;
import pages.FrontEndCommonPage;
import utils.ErrorHandlerUtils.TestException;
import utils.ExcelUtils.TestDataInput;
import utils.WebDriverControlUtils.WaitUtil;

public class ShippingAddressValidations extends BaseTest{		

	//************************************************************************************************
	// ** Internal method **
	//************************************************************************************************
	
	private static boolean launchAndValidateDifferentShippingAddresses(String[] ShippingAddressDetailsInArray) throws Exception {
		
		try {
		
			Address NewShippingAddressObj 	= new Address() ;
			NewShippingAddressObj.setAttributes(ShippingAddressDetailsInArray);
	
			// Navigate required pages to reach test screen
			
			CommonTestLibrary.launchDefaultProduct() ;

			return (CommonTestLibrary.purchaseProduct(	TestApplicationParams.getDefaultUser(), 
																						NewShippingAddressObj,
																						TestApplicationParams.getDefaultBillingAddress(), 
																						true, 
																						TestApplicationParams.getDefaultPaymentMethod(),
																						TestApplicationParams.getDefaultDiscount(), 
																						"Address")) ;
			}
			catch ( Exception e) { throw e ;}																									
	}		
		
	//************************************************************************************************
	// ** TEST 1 : CheckAddNewShippingAddresses **
	//************************************************************************************************
	
	@Test( dataProvider="AddressValidationTestData", groups ={"ShippingAddressValidations"})

	public static void AddNewShippingAddress(String[] ShippingAddressDetailsInArray){
	
		try {			
			
			Assert.assertEquals( launchAndValidateDifferentShippingAddresses(ShippingAddressDetailsInArray) ,true) ;				
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
	// ** TEST 2 : AddressSaveOptionValidations **
	//************************************************************************************************
	
	@Test( dataProvider="AddressValidationTestData", groups ={"AddressSaveOptionTestData"})

	public static void AddressSaveOptions(String[] ShippingAddressDetailsInArray){
		
			try {			
				
				Assert.assertEquals( launchAndValidateDifferentShippingAddresses(ShippingAddressDetailsInArray) ,true) ;				
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
	// ** TEST 3 : Edit Existing Shipping Address during checkout
	//************************************************************************************************
	
	/*@Test( dataProvider="MultipleAddressValidations", groups ={"ShippingAddressValidations"})

	public static void EditShippingAddress(String[] ShippingAddressDetailsInArray){
		
		try {
			
			Assert.assertEquals( launchAndValidateDifferentShippingAddresses(ShippingAddressDetailsInArray) ,true) ;				
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
	}*/
	
	//************************************************************************************************
	// ** TEST 4 : Multiple Shipping Addresses **
	//************************************************************************************************
	
	/*@Test( dataProvider="MultipleAddressValidations", groups ={"ShippingAddressValidations"})

	public static void MultipleShippingAddresses(String[] ShippingAddressDetailsInArray){
		
		try {
			
			Address NewShippingAddressObj 	= new Address() ;
			NewShippingAddressObj.setAttributes(ShippingAddressDetailsInArray);

			// Navigate required pages to reach test screen
			
			User UserForTest = CommonTestLibrary.launchDefaultProductAndEnterUserDetails() ;	
			Address BillingAddressForTest = TestApplicationParams.getDefaultBillingAddress() ;			
			PaymentMethod PaymentMethodForTest 	= 	TestApplicationParams.getDefaultPaymentMethod() ;			
			
			CommonTestLibrary.enterBillingPaymentDetailsAndSubmit(	UserForTest,
																	BillingAddressForTest,
																	PaymentMethodForTest) ;
			
			FrontEndShippingAddressPage AddNewShippingAddressPage = 
					new FrontEndShippingAddressPage(	Driver,
														UserForTest,
														NewShippingAddressObj) ;
			// Create First Address
																			
			if (AddNewShippingAddressPage.createNewShippingAddress()){					
								
				WaitUtil.waitFor(Driver, "THREAD_SLEEP", (long)1000);
				AddNewShippingAddressPage.clickAddNewShippingAddress() ;
				
				Address AdditionalShippingAddress = TestApplicationParams.getDefaultShippingAddress() ;
				
				FrontEndShippingAddressPage AdditionalShippingAddressPage = 
						new FrontEndShippingAddressPage(	Driver,
															UserForTest,
															AdditionalShippingAddress) ;
				
				Assert.assertEquals(AddNewShippingAddressPage.createNewShippingAddress(), true) ;
				
				TestLog.logTestStep("Logout from the account");
											
				AdditionalShippingAddressPage.logout() ;								
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
	}*/
	
	//************************************************************************************************					
	// ** DATA PROVIDER 1 : DomesticAndInternationAddresses	**
	//************************************************************************************************
			
	@DataProvider ( name = "AddressValidationTestData" )
	
	public static String[][] fetchShippingAddresses(){
		
		String[][] ShippingAddressTestData ;
		
		ShippingAddressTestData = TestDataInput.getMatchedInputTableRowsForApplCodeAndTestDataType(
													Address.TestDataSheetName,
													TestApplicationParams.getCurrentTestApplication(),
													"AddressValidationTest");				
		return (ShippingAddressTestData);
		
	}	
	
	//************************************************************************************************					
	// ** DATA PROVIDER 2 : Multiple different addresses	**
	//************************************************************************************************
			
	@DataProvider ( name = "MultipleAddressValidations" )
	
	public static String[][] fetchMultipleShippingAddresses(){
		
		String[][] ShippingAddressTestData ;
		
		ShippingAddressTestData = TestDataInput.getMatchedInputTableRowsForApplCodeAndTestDataType(
													Address.TestDataSheetName,
													TestApplicationParams.getCurrentTestApplication(),
													"MultipleAddressValidations");				
		return (ShippingAddressTestData);
		
	}	
	
	//************************************************************************************************					
	// ** DATA PROVIDER 3 : Address Save Options	**
	//************************************************************************************************
			
	@DataProvider ( name = "AddressSaveOptionTestData")
	
	public static String[][] fetchShippingAddressesForAddressSaveValidation(){
		
		String[][] ShippingAddressTestData ;
		
		ShippingAddressTestData = TestDataInput.getMatchedInputTableRowsForApplCodeAndTestDataType(
													Address.TestDataSheetName,
													TestApplicationParams.getCurrentTestApplication(),
													"AddressSaveOptionTest");				
		return (ShippingAddressTestData);
		
	}	
		
}