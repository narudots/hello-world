package tests;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import coreapplicationclassesandinterfaces.BackendSubscription;
import coreapplicationclassesandinterfaces.Order;
import coreapplicationclassesandinterfaces.Product;
import pages.FrontEndCommonPage;
import pages.FrontEndHomePage;
import pages.FrontEndNewUserProfilePage;
import utils.ErrorHandlerUtils.TestException;
import utils.ExcelUtils.TestDataInput;
import utils.WebDriverControlUtils.NavigationUtil;
import utils.WebDriverControlUtils.WaitUtil;

public class ProductPurchaseValidations extends BaseTest{		

	@BeforeGroups(groups = {"BackendProductPurchaseValidations"})
	public static void loginToAdminSiteBeforeOrdersAreAdded() {
		
		try {
			CommonTestLibrary.loginAsAdmin() ;	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//************************************************************************************************
	// ** TEST 1 : AddIOFMMembershipProducts **
	//************************************************************************************************
	
	@Test( dataProvider="IOFMMembershipValidationTestData", groups ={"IOFMMembershipValidations"})

	public static void IOFMMembershipProductPurchase(String[] ProductDetailsInArray){
		
		try {			
				CommonTestLibrary.purchaseIOFMMembershipProduct(ProductDetailsInArray) ;
				new FrontEndCommonPage(Driver).logout() ;
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
	// ** TEST 2 :  **MembershipProduct**
	//************************************************************************************************
	
	@Test(  groups ={"SSMembershipValidations","IPMembershipValidations","ASAPMembershipValidations"})

	public static void MembershipProductPurchase(){
		
		try {			
				CommonTestLibrary.purchaseMembershipProduct() ;
				new FrontEndCommonPage(Driver).logout() ;
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
	// ** TEST 3 :  **Purchase Products**
	//************************************************************************************************
	
	@Test( dataProvider="ProductPurchaseTestData", groups ={"ProductPurchaseValidations"})

	public static void PurchaseProducts(String[] ProductDetailsInArray){
		
		try {
			
			// Step 1 - Get the  product to be tested
			
			Product ProductForTest 	= new Product() ;
			ProductForTest.setAttributes(ProductDetailsInArray);
			
			TestLog.logTestStep("Product checkout");
			
			// Step 2 - Navigate to the  product page to be tested
			
			NavigationUtil.navigate( Driver,ProductForTest.getProductCheckoutLink(),
									 FrontEndHomePage.getPageUrl()+FrontEndNewUserProfilePage.PageUrl) ;
			
			Assert.assertEquals(CommonTestLibrary.purchaseProduct(	TestApplicationParams.getDefaultUser(), 
					TestApplicationParams.getDefaultShippingAddress(),
					TestApplicationParams.getDefaultBillingAddress(), 
					true, 
					TestApplicationParams.getDefaultPaymentMethod(),
					TestApplicationParams.getDefaultDiscount(), 
					"Product"), true) ;	
			
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
	// ** TEST 4 :  **Backend Order Additions**
	//************************************************************************************************
	
	@Test( dataProvider="BackendOrderTestData", groups ={"BackendProductPurchaseValidations1"})

	public static void AddOrderInBackend(String[] OrderDetailsInArray){
		
		try {		
			
			Order OrderForTest 	= new Order() ;
			OrderForTest.setAttributes(OrderDetailsInArray);
			
			assertEquals(true, CommonTestLibrary.addNewOrder(OrderForTest) );		
			
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
	// ** TEST 5 :  **Backend Subscription Additions**
	//************************************************************************************************
	
	@Test( dataProvider="BackendSubscriptionAddTestData", groups ={"BackendProductPurchaseValidations"})

	public static void AddSubscriptionInBackend(String[] SubscriptionDetailsInArray){
		
		try {		
			
			BackendSubscription SubscriptionForTest 	= new BackendSubscription() ;
			SubscriptionForTest.setAttributes(SubscriptionDetailsInArray);
			
			assertEquals(true, CommonTestLibrary.addSubscriptionInBackend(SubscriptionForTest) );		
			
		} 
		catch ( Exception e)
		{
			e.printStackTrace();
			TestLog.logTestStep("Error/Exception " + e.getMessage());
			assert(false) ;
		}		
	}	
	
	//************************************************************************************************					
	// ** DATA PROVIDER 1 : IOFM Membership Data	**
	//************************************************************************************************
			
	@DataProvider ( name = "IOFMMembershipValidationTestData" )
	
	public static String[][] fetchMembershipData(){
		
		String[][] IOFMMembershipData ;
		
		IOFMMembershipData = TestDataInput.getMatchedInputTableRowsForApplCodeAndTestDataType(
																		Product.TestDataSheetName,
																		TestApplicationParams.getCurrentTestApplication(),
																		"MembershipValidationTest");				
		return (IOFMMembershipData);
		
	}	
	
	//************************************************************************************************					
	// ** DATA PROVIDER 2 : Product Data	**
	//************************************************************************************************
			
	@DataProvider ( name = "ProductPurchaseTestData" )
	
	public static String[][] fetchProductData(){
		
		String[][] ProductPurchaseTestData ;
		
		ProductPurchaseTestData = TestDataInput.getMatchedInputTableRowsForApplCodeAndTestDataType(
																		Product.TestDataSheetName,
																		TestApplicationParams.getCurrentTestApplication(),
																		"ProductPurchaseTest");				
		return (ProductPurchaseTestData);
		
	}	
		
	//************************************************************************************************					
	// ** DATA PROVIDER 3 : Backend Order Data	**
	//************************************************************************************************
				
	@DataProvider ( name = "BackendOrderTestData" )
	
	public static String[][] fetchBackendOrderData(){
		
		String[][] BackendOrderData ;
		
		BackendOrderData	 = TestDataInput.getMatchedInputTableRowsForApplCodeAndTestDataType(
													Order.TestDataSheetName,
													TestApplicationParams.getCurrentTestApplication(),
													"AddBackendOrder");				
		return (BackendOrderData);
		
	}				
	
	
	//************************************************************************************************					
	// ** DATA PROVIDER 4 : Backend Subscription Data	**
	//************************************************************************************************
				
	@DataProvider ( name = "BackendSubscriptionAddTestData" )
	
	public static String[][] fetchBackendSubscriptionData(){
		
		String[][] BackendSubscriptionData ;
		
		BackendSubscriptionData	 = TestDataInput.getMatchedInputTableRowsForApplCodeAndTestDataType(
													BackendSubscription.TestDataSheetName,
													TestApplicationParams.getCurrentTestApplication(),
													"AddBackendSubsctiption");				
		return (BackendSubscriptionData);
	}		
		
	@AfterGroups(groups = {"BackendProductPurchaseValidations"})
	public static void logoutFromAdminSiteAfterProductsAdditionsAreTested(ITestContext result) {
		
		try {
			if (result.getFailedTests().size() == 0) {
				
				// Capture the screenshot for logging			
				BaseTest.TestLog.captureScreenShot("Before Logging out ");
				new FrontEndCommonPage(Driver).logout() ;
			}
				
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}