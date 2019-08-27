package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.IFactoryAnnotation;

import coreapplicationclassesandinterfaces.Address;
import coreapplicationclassesandinterfaces.PaymentMethod;
import coreapplicationclassesandinterfaces.User;
import tests.BaseTest;
import tests.CommonTestLibrary;
import utils.ErrorHandlerUtils.TestException;
import utils.WebDriverControlUtils.WaitUtil;


public class FrontEndSelectPaymentProfilePage extends FrontEndCommonPage{
	
	public static final String PageUrlExtn = "/store/checkout/payment-customer-profile";
	public static final String PageTitle = "Checkout - Select Payment Profile";
	private String zeroDollarString = "$0.00" ;
	
	private User UserDetails ;
	private Address BillingAddressDetails ;
	private PaymentMethod PaymentDetails ;
	private boolean BillingSameAsShippingFlag ;
	
	@FindBy(how = How.XPATH, using = "//div[@id='paymentProfileSPCForm']//input[@id='first_name']")
	private WebElement FirstName_TextBox;

	@FindBy(how = How.XPATH, using = "//div[@id='paymentProfileSPCForm']//input[@id='last_name']")
	private WebElement LastName_TextBox;
	
	@FindBy(how = How.XPATH, using = "//div[@id='paymentProfileSPCForm']//input[@id='address_1']")
	private WebElement AddressLine1_TextBox;		

	@FindBy(how = How.XPATH, using = "//div[@id='paymentProfileSPCForm']//input[@id='address_2']")
	private WebElement AddressLine2_TextBox;		
	
	@FindBy(how = How.XPATH, using = "//div[@id='paymentProfileSPCForm']//input[@id='city']")
	private WebElement City_TextBox;	

	@FindBy(how = How.XPATH, using = "//div[@id='paymentProfileSPCForm']//select[@id='state']")
	private WebElement State_Dropdown;
	
	@FindBy(how = How.XPATH, using = "//div[@id='paymentProfileSPCForm']//input[@id='state']")
	private WebElement State_TextBox;

	@FindBy(how = How.XPATH, using = "//div[@id='paymentProfileSPCForm']//select[@id='country']")
	private WebElement Country_Dropdown;
	
	@FindBy(how = How.XPATH, using = "//div[@id='paymentProfileSPCForm']//input[@id='postal_code']")
	private WebElement PostalCode_TextBox;		
	
	@FindBy(how = How.XPATH, using = "//div[@id='paymentProfileSPCForm']//input[@id='phone']")
	private WebElement Phone_TextBox;		
	
	@FindBy(how = How.XPATH, using = "//div[@id='paymentProfileSPCForm']//input[@id='card-number']")
	private WebElement CardNumber_TextBox;	

	@FindBy(how = How.XPATH, using = "//div[@id='paymentProfileSPCForm']//select[@id='exp-month']")
	private WebElement ExpMonth_Dropdown;

	@FindBy(how = How.XPATH, using = "//div[@id='paymentProfileSPCForm']//select[@ng-model='editCtl.data.exp_year']")
	private WebElement ExpYear_Dropdown;
	
	@FindBy(how = How.XPATH, using = "//div[@id='paymentProfileSPCForm']//input[@id='cvv']")
	private WebElement CVV_TextBox;	
	
	@FindBy(how = How.XPATH, using = "//div[@id='paymentProfileSPCForm']//button[@type='submit']")
	private WebElement Save_Button;			
	
	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Continue')]")
	private WebElement Continue_Button;
	
	@FindBy(how = How.XPATH, using = "//a[@class='close']")
	private WebElement Close_Button;	
	
	@FindBy(how = How.XPATH, using = "//ul[@class='payment-method-list ng-scope']//li[2]//label[1]")
	private WebElement PayLater_Option;	
	
	@FindBy(how = How.XPATH, using = "//a[@id='createPaymentMethod']")
	private WebElement AddNewPaymentMethod_Link;
	
	@FindBy(how = How.XPATH, 
	using = "//div[contains(@class,'confirmDialog')]//div[contains(@class,'panel-footer')]/button[2]")
	private WebElement YesInConfirmDialog_button;
	
	
	private By grandTotal_Text = By.xpath(("//div[@class='checkout__cart-grand-total']//div[contains(@ng-if,'ctrl.cart.grandTotal')]/strong"));

	//=====================================ALT BUTTONS============================================//
	
	private By VerifiedAddress_Button =	By.linkText("Use Verified Address") ;	
	private By UseAddressAsUsed_Button = By.linkText("Use Address As Entered") ;	
	private By SaveAddress_Button = By.partialLinkText("Save Addre") ;
	
	//======================================ERROR MESG============================================//
	
	private By DuplicateProfile_Error = By.xpath("//span[contains(., 'E00039')]");

	//======================================Constructors=========================================//
	
	public FrontEndSelectPaymentProfilePage(WebDriver CurrentTestDriver){
		
		super(CurrentTestDriver) ;
		PageFactory.initElements(this.Driver, this);
		 
	}	
	
	public FrontEndSelectPaymentProfilePage(WebDriver CurrentTestDriver, 
																				User UserObj, 
																				Address AddressObj, 
																				PaymentMethod PaymentObj,
																				boolean SameAsflag){
			
		super(CurrentTestDriver) ;
		
		PageFactory.initElements(this.Driver, this);
		
		setObjectsNeededForThisScreen(	UserObj, 
																		AddressObj, 
																		PaymentObj,
																		SameAsflag);
			 
	}

	//=========================================Methods===========================================//
	
	private void setObjectsNeededForThisScreen(	User UserObj, 
												Address AddressObj, 
												PaymentMethod PaymentObj,
												boolean SameAsFlag){
		this.UserDetails = UserObj ;
		this.BillingAddressDetails = AddressObj ;
		this.PaymentDetails = PaymentObj ;
		this.BillingSameAsShippingFlag = SameAsFlag ;
	
	}
	
	public boolean createNewBillingAddressAndPaymentDetails() throws Exception{
		
		try {
			
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 2000 );	
			
			// Enter Billing details and Payment Info only if the cart has an valid outstanding amount. If $0 then skip the entries.
			
			if(!(this.Driver.findElement(grandTotal_Text).getText()).equals(zeroDollarString)) {
				
				// Enter the Billing Address only if the Billing Address is not the same as Shipping Address
				
				if (!this.BillingSameAsShippingFlag)
				{
					fillUserSectionOnScreen() ;
					fillAddressSectionOnScreen() ;
				}
				fillPaymentSectionOnScreen() ;
				saveAddressAndPayment();
				checkForDuplicateProfileError();
			}
			

		}catch (NoSuchElementException e) {
			throw new TestException("Element not found\n" + e.getMessage());			
		}catch (TimeoutException e) {
			throw new TestException("Elements on page did not load within the given time\n" + e.getMessage()) ;
		}catch ( TestException e ){
			throw e ;
		}catch ( Exception e){
			System.out.println("Exception in billing and payment selection page:"+ e.getMessage());
			throw e;
		}
		return true ;
	}

	public void fillUserSectionOnScreen() throws Exception{
		
		try {

		// UserField - First Name
		WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 1000 );				
		FirstName_TextBox.sendKeys(this.UserDetails.getUserFirstName());			

		// UserField - Last Name
		WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 200 );
		LastName_TextBox.sendKeys(this.UserDetails.getUserLastName());

		}
		catch(Exception e){ throw e ;}
	}

	public void fillAddressSectionOnScreen() throws Exception{

		try {

			// AddressField - Address Line 1
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 200 );
			AddressLine1_TextBox.sendKeys(this.BillingAddressDetails.getAddressLine1());

			// AddressField - Address Line 2
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 200 );
			AddressLine2_TextBox.sendKeys(this.BillingAddressDetails.getAddressLine2());

			// AddressField - City
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 200 );
			City_TextBox.sendKeys(this.BillingAddressDetails.getCity());

			// AddressField - Postal Code
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 200 );
			PostalCode_TextBox.sendKeys(this.BillingAddressDetails.getPostalCode());

			// AddressField - Phone
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 200 );
			Phone_TextBox.sendKeys(this.BillingAddressDetails.getPhone());

			// AddressField - Country
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 1000 );			
			Select CountryName_dropdown = 	new Select(Country_Dropdown) ;				
			CountryName_dropdown.selectByVisibleText(this.BillingAddressDetails.getCountry());	

			// AddressField - State			
			if( this.BillingAddressDetails.getCountry().equals("United States"))
			{
				// For Domestic addresses, state is required and it is a drop down				
				WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 1000 );
				Select StateName_Dropdown = 	new Select(State_Dropdown) ;		
				StateName_Dropdown.selectByVisibleText(this.BillingAddressDetails.getState());	
			}			
			else
			{
				// For International addresses, state is not required and it is a text box
				State_TextBox.sendKeys(this.BillingAddressDetails.getState());				
			}			
		}
		catch(Exception e){ throw e ;}
	}

	public void fillPaymentSectionOnScreen() throws Exception{

		try
		{
			if (this.PaymentDetails.getPaymentMethod().equals("CreditCard"))
			{					
				WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 1000 );			
				CardNumber_TextBox.sendKeys(this.PaymentDetails.getCardNumber());
					
				WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 200 );				
				Select 	ExpMonthSelect = new Select(ExpMonth_Dropdown) ;				
				ExpMonthSelect.selectByVisibleText(this.PaymentDetails.getExpirationMonth());	
					
				WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 1000 );				
				Select 	ExpYearSelect = new Select(ExpYear_Dropdown) ;			
				ExpYearSelect.selectByVisibleText(this.PaymentDetails.getExpirationYear());
					
				WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 1000 );				
				CVV_TextBox.sendKeys(this.PaymentDetails.getCVV());								
			}
			else if (this.PaymentDetails.getPaymentMethod().equals("PayLater")){
				WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 1000 );
				PayLater_Option.click();
			}
			
		}
		
		catch(Exception e){ throw e ;}
	}

	public void saveAddressAndPayment() throws Exception{

		try {
					
		    // Capture the screenshot of the billing payment screen before save is clicked			
			BaseTest.TestLog.captureScreenShot("Billing Payment Screen - Before clicking |Save|");
			
			scrollDown(500);
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 1000 );				
			Save_Button.click();	
			
			// Based on the zipcode, three types of address save options are shown
			// Below conditions handle each one of these

			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 1000 );	

			if( this.BillingAddressDetails.getCountry().equals("United States"))
			{
				// Address Save Option 1 - Use Address As Entered

				if (this.BillingAddressDetails.getTypeOfTestData().equals("UseAddressAsEnteredOption"))
				{
					if ( Driver.findElements(UseAddressAsUsed_Button).size() != 0 )
					{
					        // Capture the screenshot of the billing payment screen 		
						BaseTest.TestLog.captureScreenShot("Billing Payment Screen - Before Clicking |Use Address As Used|");					
							
						Driver.findElement(UseAddressAsUsed_Button).click();
					}
					else
						throw new TestException("Use Address As Entered Option is not displayed");
				}	

				// Address Save Option 2 - Force Save Address

				else if (this.BillingAddressDetails.getTypeOfTestData().equals("ForceSaveAddress"))
				{
					if ( Driver.findElements(SaveAddress_Button).size() != 0 )
					{
				       	// Capture the screenshot of the billing payment screen 		
						BaseTest.TestLog.captureScreenShot("Billing Payment Screen - Before Clicking |Save Address|");
							
						Driver.findElement(SaveAddress_Button).click();
					
					}
					else
						throw new TestException("Force Save Address Option is not displayed");
				}

				// Address Save Option 3 - Verified Address

				else 
				{
					if ( Driver.findElements(VerifiedAddress_Button).size() != 0 )
					{
				       	// Capture the screenshot of the billing payment screen 		
						BaseTest.TestLog.captureScreenShot("Billing Payment Screen - Before Clicking |Verified Address|");
							
						Driver.findElement(VerifiedAddress_Button).click();
					}
						
				}
				
			}	
		}
		catch(Exception e) { throw e ; }
	}
	
	public void checkForDuplicateProfileError() throws Exception{

		try {
			if (Driver.findElements(DuplicateProfile_Error).size() != 0)
			{
				// Capture the screenshot of the billing payment screen 		
				BaseTest.TestLog.captureScreenShot("Billing Payment Screen - |Duplicate Profile Exists| error");
				
				// Now that the error has been captured, time to close it out	
				WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 1000 );	
				Close_Button.click();

				// A confirm dialogue opens on closing out the error. Confirming yes!					
				WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 1000 );	
				YesInConfirmDialog_button.click();
							
				WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 3000 );					
				
			}
		}

		catch(Exception e){ throw e ;}
	}

	public boolean closeForm() throws InterruptedException{
		
		WaitUtil.waitFor(super.Driver, "IMPLICIT", (long) 10 );
		Close_Button.click();
		return true;
		
	}		
	
	public boolean clickAddNewPaymentMethod() throws Exception{
		
		WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 3000 );	
		
		try {
			
	        // Capture the screenshot of the billing payment screen 		
			BaseTest.TestLog.captureScreenShot(
			"Billing Payment Home Screen - Before Clicking |Add New Payment Method|");
			
			AddNewPaymentMethod_Link.click();
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 3000 );	
			return true;
			
		} catch (Exception e) {
			
			throw e;
		}
		
	}
	public boolean navigateToShippingAddress() throws Exception {
		
		try {
			
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 3000 );		
			
			scrollDown(400);
			Continue_Button.click();
			
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 1000 );		
			
			CommonTestLibrary.acceptCookies() ;
			
			String DestinationPageTitle = super.Driver.getTitle() ;				
			
			if ( DestinationPageTitle.contains(FrontEndShippingAddressPage.PageTitle))
			{					
				return true ;
			}
			else
			{
				BaseTest.TestLog.logTestStep("Shipping page not launched on click of continue");
				return false ;
			}
		} catch (Exception e) {			
			throw e;			
		}
	}
	
}