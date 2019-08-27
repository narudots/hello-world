package config;

import coreapplicationclassesandinterfaces.Address;
import coreapplicationclassesandinterfaces.Discount;
import coreapplicationclassesandinterfaces.PaymentMethod;
import coreapplicationclassesandinterfaces.Product;
import coreapplicationclassesandinterfaces.User;
import pages.FrontEndHomePage;
import utils.ExcelUtils.TestDataInput;

public class TestApplicationConfig {

	private String 						ApplicationTestURL;
	private String 						ApplicationCode;
	private User						DefaultUser ;
	private Product					DefaultProduct ;
	private Address					DefaultBillingAddress ;
	private Address					DefaultShippingAddress ;
	private PaymentMethod	DefaultPaymentMethod ;
	private Discount					DefaultDiscount ;
		
	public TestApplicationConfig(	 String LaunchURL
															,String ApplicationCode
															,String HomePageTitle
															,String HomePageURL ){
	
		this.ApplicationTestURL			= 	LaunchURL ;
		this.ApplicationCode 			= 	ApplicationCode ;
		
		FrontEndHomePage.setPageTitleAndURL(HomePageTitle, HomePageURL);		
	}	
	
	public String getCurrentTestApplication()
	{
		return this.ApplicationCode ;			
	}
	public String getInitialTestURL()
	{
		return this.ApplicationTestURL ;
	}
	public User getDefaultUser()
	{
		return this.DefaultUser ;
	}
	public Product getDefaultProduct()
	{
		return this.DefaultProduct ;
	}
	public Address getDefaultBillingAddress()
	{
		return this.DefaultBillingAddress ;
	}
	public Address getDefaultShippingAddress()
	{
		return this.DefaultShippingAddress ;
	}	
	public PaymentMethod getDefaultPaymentMethod()
	{
		return this.DefaultPaymentMethod ;
	}	
	public Discount getDefaultDiscount()
	{
		return this.DefaultDiscount ;
	}	

	public void CreateDefaultsObjectsForTest(String ApplCode) throws Exception {
		
		try {
			// Create Default Test User
			
			DefaultUser = new User() ;
			
			String [] DefaultUserTestData = TestDataInput.getFirstRowInSheetMatchingApplCodeAndTestDataType(
											User.TestDataSheetName,
											ApplCode,
											"Default") ;
			
			DefaultUser.setAttributes(DefaultUserTestData);

			// Create Default Test Product
			
			DefaultProduct = new Product() ;
			
			String [] DefaultProductTestData = 	TestDataInput.getFirstRowInSheetMatchingApplCodeAndTestDataType(
												Product.TestDataSheetName,
												ApplCode,
												"Default") ;
			
			DefaultProduct.setAttributes(DefaultProductTestData);
			
			// Create Default Test Billing Address
			
			DefaultBillingAddress = new Address() ;
			
			String [] DefaultBillingAddressTestData = 	TestDataInput.getFirstRowInSheetMatchingApplCodeAndTestDataType(
														Address.TestDataSheetName,
														ApplCode,
														"DefaultBillingAddress") ;
			
			DefaultBillingAddress.setAttributes(DefaultBillingAddressTestData);
			
			// Create Default Test Shipping Address
			
			DefaultShippingAddress = new Address() ;
			
			String [] DefaultShippingAddressTestData = 	TestDataInput.getFirstRowInSheetMatchingApplCodeAndTestDataType(
														Address.TestDataSheetName,
														ApplCode,
														"DefaultShippingAddress") ;
			
			DefaultShippingAddress.setAttributes(DefaultShippingAddressTestData);		
			
			// Create Default Test Payment Method
			
			DefaultPaymentMethod = new PaymentMethod() ;
			
			String [] DefaultPaymentMethodTestData = 	TestDataInput.getFirstRowInSheetMatchingApplCodeAndTestDataType(
														PaymentMethod.TestDataSheetName,
														ApplCode,
														"Default") ;
			
			DefaultPaymentMethod.setAttributes(DefaultPaymentMethodTestData);			
			
			DefaultDiscount = new Discount() ;
			
			String [] DefaultDiscountTestData = 	TestDataInput.getFirstRowInSheetMatchingApplCodeAndTestDataType(
					Discount.TestDataSheetName,
					ApplCode,
					"Default") ;

			DefaultDiscount.setAttributes(DefaultDiscountTestData);			
	
		} catch (Exception e) {
		
			throw e;
		}
				
	}
}