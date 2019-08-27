package tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import coreapplicationclassesandinterfaces.*;
import pages.BackEndCommonPage;
import pages.BackEndOrderPage;
import pages.BackEndProductPage;
import pages.BackEndSubscriptionPage;
import pages.FrontEndCommonAddToCartPage;
import pages.FrontEndCommonPage;
import pages.FrontEndHomePage;
import pages.FrontEndIOFMJoinPage;
import pages.FrontEndIOFMStarterMembershipJoinPage;
import pages.FrontEndMyAccountPage;
import pages.FrontEndNewUserProfilePage;
import pages.FrontEndOrderSuccessPage;
import pages.FrontEndSPCPage;
import pages.FrontEndSelectPaymentProfilePage;
import pages.FrontEndShippingAddressPage;
import pages.FrontEndShoppingCartPage;
import utils.ErrorHandlerUtils.TestException;
import utils.ExcelUtils.TestDataInput;
import utils.WebDriverControlUtils.NavigationUtil;
import utils.WebDriverControlUtils.WaitUtil;

public class CommonTestLibrary extends BaseTest{		
	
	//**********************************************************************************************
	// THIS METHOD RETURNS THE CURRENT PAGE TITLE
	//**********************************************************************************************
	
	public static String getCurrentPageTitle() {		
		return Driver.getTitle() ;				
	}	
	
	//**********************************************************************************************
	// THIS METHOD LAUNCHES THE DEFAULT PRODUCT FOR THE CURRENT APPLICATION
	//**********************************************************************************************
	
	public static boolean loginAsAdmin() throws Exception{
		
		try {
			TestLog.logTestStep("Login as Admin");				
			User 	UserObjForThisTest 	= new User() ;
		
			String [] ANewUserDetails = 
							TestDataInput.getFirstRowInSheetMatchingApplCodeAndTestDataType(
				 			User.TestDataSheetName
							, TestApplicationParams.getCurrentTestApplication()
							, "Admin") ;

			UserObjForThisTest.setAttributes(ANewUserDetails);			
			FrontEndCommonPage loginPage = new FrontEndCommonPage(Driver,UserObjForThisTest) ;
			
			if ( loginPage.clickLogin())
			{
				return loginPage.login(BackEndCommonPage.getPageTitle()) ;
			}
			else return false ;
		} 
		catch ( Exception e) {
			throw e;
		}		
	}
	
	//**********************************************************************************************
	// THIS METHOD IS USED TO ADD NEW SUBSCRIPTIONS FROM BACKEND
	//**********************************************************************************************
	
	public static boolean addSubscriptionInBackend(BackendSubscription NewSubscription) {
		
		try {
			
			TestLog.logTestStep("Enter Subscription Details via the Backend Add Subscription Form");
			
			if( new BackEndCommonPage(Driver).openAddSubscriptionForm()) {
				
				BackEndSubscriptionPage Page = new BackEndSubscriptionPage(	Driver,
																			NewSubscription,
																			TestApplicationParams.getDefaultUser());				
				
				if( Page.enterSubscriptionDetails()) 					
					return Page.clickSaveAndClose() ;
				else 
					return false ;
			}
			else 
				return false ;		
		}
		catch (Exception e) {
			return false ;
		}		
	}
	
	//**********************************************************************************************
	// THIS METHOD IS USED TO ADD NEW ORDERS FROM BACKEND
	//**********************************************************************************************

	public static boolean addNewOrder(Order NewOrder) throws Exception
	{
		try {
			
			TestLog.logTestStep("Enter Order Details via the Backend Add Order Form");
			
			if( new BackEndCommonPage(Driver).openAddOrderForm()) {
				
				BackEndOrderPage OrderPage = new BackEndOrderPage( 	Driver,
																	NewOrder,
																	TestApplicationParams.getDefaultUser());				
				
				if( OrderPage.enterOrderDetails()) {					
					
					if( OrderPage.enterAddresses(	TestApplicationParams.getDefaultShippingAddress(), 
													TestApplicationParams.getDefaultBillingAddress()))
						if (OrderPage.selectOrderItems())
							return OrderPage.clickSaveAndClose() ;
						else 
							return false ;
					else 
						return false ;					
				}
				else 
					return false ;
			}
			else 
				return false ;
		
		}
		catch (Exception e) {
			return false ;
		}		
	}
	
	//**********************************************************************************************
	// THIS METHOD IS USED TO ADD NEW PRODUCTS FROM BACKEND
	//**********************************************************************************************

	public static boolean addNewProduct(Product ProductToBeAdded) throws Exception
	{
		try {
			if( new BackEndCommonPage(Driver).openAddProductForm()) {
				
				BackEndProductPage ProductPage = new BackEndProductPage(Driver,ProductToBeAdded);
				
				if( ProductPage.enterCommonProductFields()) {
					
					String typeOfProductBeingAdded = ProductToBeAdded.getProductType().toLowerCase();
					
					if (typeOfProductBeingAdded.contains("webex")) {
						
						Webex 	WebexObjThisTest 	= 	new Webex() ;
						
						String [] WebexDetails 		= 	TestDataInput.getFirstRowInSheetMatchingApplCodeAndTestDataType(
											 			Webex.TestDataSheetName
														, TestApplicationParams.getCurrentTestApplication()
														, "Webex") ;
							
						WebexObjThisTest.setAttributes(WebexDetails);
						
						if( ProductPage.enterWebexDetails(WebexObjThisTest )) 
							return ProductPage.clickSaveAndClose() ;
						else 
							return false ;
					}
					else if (typeOfProductBeingAdded.contains("e-learning")) {
						
						ELearningProduct ElearningObjThisTest 	= 	new ELearningProduct() ;
						
						String [] ElearningDetails 			= 	TestDataInput.getFirstRowInSheetMatchingApplCodeAndTestDataType(
														 		ELearningProduct.TestDataSheetName
																, TestApplicationParams.getCurrentTestApplication()
																, "Elearning") ;
							
						ElearningObjThisTest.setAttributes(ElearningDetails);
						
						if( ProductPage.enterELearningDetails(ElearningObjThisTest)) 
							return ProductPage.clickSaveAndClose() ;
						else 
							return false ;
					}
					else if (typeOfProductBeingAdded.contains("subscription")) {
						
						Subscription SubscriptionObjThisTest 	= 	new Subscription() ;
						
						String [] SubscripionDetails 				= 	TestDataInput.getFirstRowInSheetMatchingApplCodeAndTestDataType(
														 				Subscription.TestDataSheetName
														 				, TestApplicationParams.getCurrentTestApplication()
														 				, "Subscription") ;
							
						SubscriptionObjThisTest.setAttributes(SubscripionDetails);
						
						if( ProductPage.enterSubscriptionDetails(SubscriptionObjThisTest))
							return ProductPage.clickSaveAndClose() ;
						else 
							return false ;
					}
					else if (typeOfProductBeingAdded.contains("bundle")) {
						
						Bundle BundleObjThisTest 	= 	new Bundle() ;
						
						String [] BundleDetails 	= 	TestDataInput.getFirstRowInSheetMatchingApplCodeAndTestDataType(
											 			Bundle.TestDataSheetName
											 			, TestApplicationParams.getCurrentTestApplication()
											 			, "bundle") ;
							
						BundleObjThisTest.setAttributes(BundleDetails);
						
						if( ProductPage.enterBundleDetails(BundleObjThisTest))
							return ProductPage.clickSaveAndClose() ;
						else 
							return false ;
					}
					else if (typeOfProductBeingAdded.contains("group access")) {
						
						GroupAccess GroupAccessObjThisTest 	= 	new GroupAccess() ;
						
						String [] GroupAccessDetails 		= 	TestDataInput.getFirstRowInSheetMatchingApplCodeAndTestDataType(
											 					GroupAccess.TestDataSheetName
											 					, TestApplicationParams.getCurrentTestApplication()
											 					, "Group Access") ;
							
						GroupAccessObjThisTest.setAttributes(GroupAccessDetails);
						
						if( ProductPage.enterGroupAccessDetails(GroupAccessObjThisTest))
							return ProductPage.clickSaveAndClose() ;
						else 
							return false ;
					}
					else
						return ProductPage.clickSaveAndClose();
				} 
				else 
					return false ;
			}
			else 
				return false ;
		} catch (Exception e) {
			return false ;
		}		
	}
	
	//**********************************************************************************************
	// THIS METHOD LAUNCHES THE DEFAULT PRODUCT FOR THE CURRENT APPLICATION
	//**********************************************************************************************
	
	public static boolean launchDefaultProduct() throws Exception{
		
		TestLog.logTestStep("Initiate default product checkout");
		
		try {
				return (NavigationUtil.navigate( Driver, TestApplicationParams.getDefaultProduct().getProductCheckoutLink(),
						 								 FrontEndHomePage.getPageUrl()+FrontEndNewUserProfilePage.PageUrl) );	
		} 
		catch ( TestException Te)
		{
			if (Te.getExceptionName().equals("INTENDED PAGE WAS NOT LAUNCHED")) {
				
				emptyShoppingCart() ;
				return (NavigationUtil.navigate( Driver, TestApplicationParams.getDefaultProduct().getProductCheckoutLink(),
						 FrontEndHomePage.getPageUrl()+FrontEndNewUserProfilePage.PageUrl) );	
				
			}
			else throw Te ;
		}
		catch ( Exception e) {
			throw e;
		}		
	}
	
	//**********************************************************************************************
	// THIS METHOD COMPLETES THE PRODUCT PURCHASE AFTER FILLING OUT THE CHECKOUT PAGE  
	//**********************************************************************************************
	
	public static boolean purchaseProduct(	 User 	 		TestUser, 
											 Address 		TestShippingAddress,
											 Address 		TestBillingAddress,
											 boolean 		BillingAddressSameAsShipping,
											 PaymentMethod 	TestPayment,
											 Discount 	   	TestDiscount,
											 String 	   	EntityUnderTest) throws Exception {		
		try {			

			if (EntityUnderTest.equals("User"))			
			{
				String testDataTypeString = TestUser.getTypeOfTestData() ;
				
				if (testDataTypeString.equals("NewUser")) 
				{				
					if (!enterUserDetails(TestUser)) 
						return false ; 
				
					if (!enterShippingAddressDetails(TestUser, TestShippingAddress, BillingAddressSameAsShipping)) 
						return false ;
					
					if (!enterBillingPaymentDetails(TestUser, TestBillingAddress, TestPayment,BillingAddressSameAsShipping))	
						return false ; 								
				}
				else
				{
					if ( TestUser.getTypeOfTest().equals("EditUserTest")) {
						if(!new FrontEndNewUserProfilePage(Driver,TestUser).editUser())
							return false ;
					}
					else if ( TestUser.getTypeOfTest().equals("ExistingUserTest")){					
						if(!new FrontEndNewUserProfilePage(Driver,TestUser).signInAsExistingUser())
							return false ;
					}
				}	
			}		
			
			else {
				
				if (!enterUserDetails())
					return false ;
				
				if (!enterShippingAddressDetails(TestUser, TestShippingAddress, BillingAddressSameAsShipping)) 
					return false ;
				
				if (!enterBillingPaymentDetails(TestUser, TestBillingAddress, TestPayment,BillingAddressSameAsShipping))	
					return false ; 	
			}	

			if (!enterDiscount(TestDiscount)) 
				return false ; 	
			
			if (!new FrontEndSPCPage(Driver).clickBuy()) 
				return false ;
			
			if (!new FrontEndOrderSuccessPage(Driver).launchMyAccountPage() ) 
				return false ;
			
			FrontEndMyAccountPage  myAccountPage = new FrontEndMyAccountPage(Driver) ;
			
			switch (EntityUnderTest)
			{
				case "User" : 
				case "Address" : myAccountPage.viewUserAndAddressDetails() ; break ;
				case "Payment" : myAccountPage.viewPaymentMethod() ; break ;
				case "Product" : 
				case "Discount": myAccountPage.viewOrders(); break ;
				case "Membership": myAccountPage.viewSubscriptions(); 
			
			}			
			return true;					
		}			
		
		catch (Exception e) {
			e.printStackTrace();
			TestLog.logTestStep("Error/Exception " + e.getMessage());
			return false ;
		}			
	}

	//**********************************************************************************************
	// THIS METHOD USES A RANDOM USER RECORD TO FILL THE USER SECTION OF THE CHECKOUT SCREEN
	//**********************************************************************************************

	public static boolean enterUserDetails() throws Exception{		
				
		try {
			
			TestLog.logTestStep("Enter User Details(Section 1) in checkout and Submit");	
		
			User 	NewUserObjForThisTest 	= new User() ;
		
			String [] ANewUserDetails = 
							TestDataInput.getFirstRowInSheetMatchingApplCodeAndTestDataType(
				 			User.TestDataSheetName
							, TestApplicationParams.getCurrentTestApplication()
							, "NewUser") ;

			NewUserObjForThisTest.setAttributes(ANewUserDetails);		
			
			FrontEndNewUserProfilePage	CustomerCreationPage = 	new FrontEndNewUserProfilePage(
																Driver, 
																NewUserObjForThisTest);
			
			TestLog.logTestStep("Enter Default User Values and Submit");					
			return CustomerCreationPage.createNewCustomer();
			
		} 
		catch ( Exception e) { throw e;}		
	}
	
	//**********************************************************************************************
	// THIS OVERLOADED METHOD USES THE INPUT PASSED TO FILL THE USER SECTION OF THE CHECKOUT SCREEN
	//**********************************************************************************************
	
	public static boolean enterUserDetails(User TestUser) throws Exception{		
		
		try {
			
				FrontEndNewUserProfilePage	CustomerCreationPage = 	new FrontEndNewUserProfilePage(
																Driver, 
																TestUser);
			
				TestLog.logTestStep("Enter User Details(Section 1) in checkout and Submit");					
				return CustomerCreationPage.createNewCustomer();
		} 
		catch ( Exception e) { throw e;}		
	}

	//**********************************************************************************************
	// THIS  METHOD USES THE INPUT PASSED TO FILL THE SHIPPING ADDRESS SECTION OF THE CHECKOUT SCREEN
	//**********************************************************************************************
	
	public static boolean enterShippingAddressDetails(	User TestUser,
														Address TestAddress ,
														boolean UseAsBillingAddressFlag )							
														throws Exception 
	{
		TestLog.logTestStep("Enter Shipping Address Details(Section 2) on Checkout screen");
		
		FrontEndShippingAddressPage AddNewShippingAddressPage = new FrontEndShippingAddressPage(	
																Driver,
																TestUser,
																TestAddress,
																UseAsBillingAddressFlag) ;
		try {
			
			if ( AddNewShippingAddressPage.createNewShippingAddress()) {				
				return true ;
			}
			else return false ;			
		} 
		catch (Exception e) {
			throw e;
		}		
	}		

	//**********************************************************************************************
	// THIS  METHOD USES THE INPUT PASSED TO FILL THE BILLING ADDRESS AND PAYMENT SECTION OF THE 
	// CHECKOUT SCREEN
	//**********************************************************************************************

	public static boolean enterBillingPaymentDetails(	User UserInfo,
														Address AddressInfo,
														PaymentMethod PaymentInfo,
														boolean SameAsFlag) 
														throws Exception {
		
		TestLog.logTestStep("Enter Billing Address and Payment Details( Section 3) of Checkout Page");
		
		FrontEndSelectPaymentProfilePage AddNewBillingAddressPage = 
														new FrontEndSelectPaymentProfilePage(
														Driver,
														UserInfo,
														AddressInfo, 
														PaymentInfo,
														SameAsFlag) ;
		try {

			if ( AddNewBillingAddressPage.createNewBillingAddressAndPaymentDetails()) {					
				return true ;
			}
			else {
					TestLog.logTestStep("Billing Address Entry Failure");
					return false ;
			}
		} 
		catch (Exception e) {
			throw e;
		}					
			
	}		
	
	//**********************************************************************************************
	// THIS  METHOD APPLIES THE DISCOUNT CODE PASSED
	//**********************************************************************************************

	private static boolean enterDiscount(Discount TestDiscount) throws InterruptedException {
		
		try {					
			
			TestLog.logTestStep("Enter Discount Details");		
			
			if ( !TestDiscount.getCouponCode().equals("NONE") ) 
			{
				boolean returnflag =  new FrontEndSPCPage(Driver).applyDiscountCode(TestDiscount) ;
				return returnflag ;
			}		
			return true ;			
		} catch (Exception e) {
			throw e ;
		}
	}
	
	//**********************************************************************************************
	// THIS  METHOD IS TO APPEPT THE COOKIES TERMS AND CONDITIONS
	//**********************************************************************************************
	
	public static boolean acceptCookies() throws InterruptedException
	{
		try {
			WaitUtil.waitFor(Driver, "THREAD_SLEEP", (long)1000);
			List <WebElement> CookieAccept_Button =  Driver.findElements(By.linkText("Accept Cookies"));
			
			if(CookieAccept_Button.size()>0) {
				
					if (CookieAccept_Button.get(0).isEnabled()) {
						CookieAccept_Button.get(0).click();					
						WaitUtil.waitFor(Driver, "THREAD_SLEEP", (long)1000);
					}
					
			}
		} catch (WebDriverException e) {
			System.out.println("Accept Cookies not clickable") ;
			return true ;
			}
			catch (Exception e) {
				throw e ;
			}
		
		return true;				
	}
	
	//**********************************************************************************************
	// THIS  METHOD IS USED TO PURCHASE IOFM SUBSCRIPTION PRODUCTS
	//**********************************************************************************************
	
	public static boolean purchaseIOFMMembershipProduct(String []ProductDetailsInArray) throws Exception 
	{
		// Step 1 - Get the membership product to be tested
		
			Product MembershipProduct 	= new Product() ;
			MembershipProduct.setAttributes(ProductDetailsInArray);
			
			FrontEndIOFMJoinPage JoinPage = new FrontEndIOFMJoinPage(Driver) ;
			
			// Step 2 - Navigate to the IOFM Join page
			
			TestLog.logTestStep("Launch IOFM Join Page");
			
			NavigationUtil.navigateOffsetFromHome( Driver,
																				FrontEndIOFMJoinPage.PageUrlExtn) ;
			
			// Step 3 - Add the membership product to cart
			
			JoinPage.addMembershipProductToCart(MembershipProduct.getTypeOfTestData());	
			
			// Step 4 - If Starter, launch the starter product page
			
			if (MembershipProduct.getTypeOfTestData().equals("Starter")) {
				
				FrontEndIOFMStarterMembershipJoinPage StarterJoinPage
															= new FrontEndIOFMStarterMembershipJoinPage(Driver) ; 
				
				StarterJoinPage.launchStarterProductPage() ;
				
				FrontEndCommonAddToCartPage StarterProductAddToCartPage 
															= new FrontEndCommonAddToCartPage (Driver) ;				
				
				// Step 4.a -Add starter product to cart
				
				StarterProductAddToCartPage.addToCart() ;		
			}
	
			// Step 5 - Click Checkout on Shopping Cart page
			
			FrontEndShoppingCartPage ShoppingCartPage = new FrontEndShoppingCartPage(Driver); 
			
			ShoppingCartPage.checkout() ;
			
			// Step 6 -  Invoke purchase product to complete purchase and launch myAccount page
			
			return purchaseProduct(	TestApplicationParams.getDefaultUser(), 
												TestApplicationParams.getDefaultShippingAddress(),
												TestApplicationParams.getDefaultBillingAddress(), 
												true, 
												TestApplicationParams.getDefaultPaymentMethod(),
												TestApplicationParams.getDefaultDiscount(), 
												"Membership" ) ;
	}
	
	//**********************************************************************************************
	// THIS  METHOD IS USED TO UPGRADE IOFM MEMBERSHIPS
	//**********************************************************************************************
	
	public static boolean upgradeMembership(String SourceMembership, String  TargetMembership) throws Exception 
	{
			FrontEndMyAccountPage	AccountPageForTest = new FrontEndMyAccountPage(Driver) ;			
			AccountPageForTest.upgradeIOFMMembership(SourceMembership, TargetMembership) ;			
			return true;			
	}
	
	//**********************************************************************************************
	// THIS  METHOD IS USED TO PURCHASE  SUBSCRIPTION PRODUCTS FOR OTHER SITES
	//**********************************************************************************************

	public static boolean purchaseMembershipProduct() throws Exception {
		
		try {
			
			FrontEndHomePage HomePage = new FrontEndHomePage(Driver) ;
			
			switch (TestApplicationParams.getCurrentTestApplication())	
			{
				case "SS" 		: HomePage.addMembershipProductToCartSS() ; break ;
				case "ASAP" 	: HomePage.addMembershipProductToCartASAP(); break ;
				case "IP"		: HomePage.addMembershipProductToCartIP();break;
				default 		: throw new TestException("CommonTestLibrary.purchaseMembershipProduct | "
														+ "Exception: Check Application Code in Testng xml") ;
			}			
			return purchaseProduct(	TestApplicationParams.getDefaultUser(), 
									TestApplicationParams.getDefaultShippingAddress(),
									TestApplicationParams.getDefaultBillingAddress(), 
									true, 
									TestApplicationParams.getDefaultPaymentMethod(),
									TestApplicationParams.getDefaultDiscount(), 
									"Membership" ) ;
			
		} catch (TestException e) {
			throw e ;
		} catch (Exception e) {
			throw e ;
		}			
		
	}

	public static boolean emptyShoppingCart() {
		
		try {
			return new FrontEndShoppingCartPage(Driver).deleteAllInCart() ;
		} catch (InterruptedException e) {
			return false ;
		}
		
	}

	
}