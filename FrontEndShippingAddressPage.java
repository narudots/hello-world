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
import coreapplicationclassesandinterfaces.Address;
import coreapplicationclassesandinterfaces.User;
import tests.BaseTest;
import utils.ErrorHandlerUtils.TestException;
import utils.WebDriverControlUtils.WaitUtil;

public class FrontEndShippingAddressPage extends FrontEndSPCPage{

	private User UserDetails ;
	private Address ShippingAddressDetails ;
	private boolean UseAsBillingAddressFlag ;
	
	//==================================PAGE ELEMENT LOCATORS====================================//
	
	@FindBy(how = How.XPATH, using = "//div[@id='addressForm']//input[@id='first_name']")
	private WebElement FirstName_TextBox;

	@FindBy(how = How.XPATH, using = "//div[@id='addressForm']//input[@id='last_name']")
	private WebElement LastName_TextBox;
	
	@FindBy(how = How.XPATH, using = "//div[@id='addressForm']//input[@id='address_1']")
	private WebElement AddressLine1_TextBox;		

	@FindBy(how = How.XPATH, using = "//div[@id='addressForm']//input[@id='address_2']")
	private WebElement AddressLine2_TextBox;		
	
	@FindBy(how = How.XPATH, using = "//div[@id='addressForm']//input[@id='city']")
	private WebElement City_TextBox;	

	@FindBy(how = How.XPATH, using = "//div[@id='addressForm']//select[@id='state']")
	private WebElement State_Dropdown;

	@FindBy(how = How.XPATH, using = "//div[@id='addressForm']//input[@id='state']")
	private WebElement State_TextBox;
	
	@FindBy(how = How.XPATH, using = "//div[@id='addressForm']//select[@id='country']")
	private WebElement Country_Dropdown;
	
	@FindBy(how = How.XPATH, using = "//div[@id='addressForm']//input[@id='postal_code']")
	private WebElement PostalCode_TextBox;		
	
	@FindBy(how = How.XPATH, using = "//div[@id='addressForm']//input[@id='phone']")
	private WebElement Phone_TextBox;		
	
	@FindBy(how = How.XPATH, using = "//button[@type='submit']")
	private WebElement Save_Button;			
	
	@FindBy(how = How.XPATH, using = "//section[2]/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/div[2]/div[1]/button[1]")
	private WebElement Continue_Button;
	
	@FindBy(how = How.XPATH, using = "//a[@class='close']")
	private WebElement Close_Button;	
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'confirmWindowClass')]//button[2]")
	private WebElement ConfirmDialogueYes_Button;		
	
	@FindBy(how = How.XPATH, using = "//a[@id='createPaymentMethod']")
	private WebElement AddNewShippingAddress_Link;
	
	@FindBy(how = How.XPATH, using = "//div[@id='reviewShippingAddress']//button[contains(text(),'Edit')]")
	private WebElement EditShippingAddress_Link;
	
	//ALT BUTTONS
	private WebElement SameAsBilling_Checkbox ;
	private By VerifiedAddressLocator =	By.linkText("Use Verified Address") ;	
	private By UseAddressAsUsedLocator = By.linkText("Use Address As Entered") ;	
	private By SaveAddressLocator = By.partialLinkText("Save Addre") ;	
	private By SameAsBillingLocator =By.xpath("//section[@id='checkoutStepTwo']//input[@id='useThisShippingAddress']" );	
	
	//======================================Constructors=========================================//
	
	public FrontEndShippingAddressPage(	WebDriver CurrentTestDriver){
		
		super(CurrentTestDriver) ;
		PageFactory.initElements(this.Driver, this);
		 
	}

	public FrontEndShippingAddressPage(	WebDriver CurrentTestDriver, 
										User UserObj, 
										Address AddressObj,
										boolean SameAsBillingFlag){
		super(CurrentTestDriver) ;
		PageFactory.initElements(this.Driver, this);

		setObjectsNeededForThisScreen(	UserObj, 
										AddressObj,SameAsBillingFlag);
	}

	//===================================Private Methods===========================================//
	
	private void clearSameAsBillingCheckbox() throws InterruptedException {
		
		WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 100 );				
		if ( Driver.findElements(SameAsBillingLocator).size()>0)
		{
			SameAsBilling_Checkbox = Driver.findElement(SameAsBillingLocator) ;
			
			if(SameAsBilling_Checkbox.isSelected())
				SameAsBilling_Checkbox.click();
		}
	}	
	
	private void fillAddressSectionOnScreen() throws Exception{

		try {

			
			AddressLine1_TextBox.sendKeys(this.ShippingAddressDetails.getAddressLine1());

			// AddressField - Address Line 2
			AddressLine2_TextBox.sendKeys(this.ShippingAddressDetails.getAddressLine2());

			// AddressField - City
			City_TextBox.sendKeys(this.ShippingAddressDetails.getCity());

			// AddressField - Postal Code
			PostalCode_TextBox.sendKeys(this.ShippingAddressDetails.getPostalCode());

			// AddressField - Phone
			Phone_TextBox.sendKeys(this.ShippingAddressDetails.getPhone());

			// AddressField - Country
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 200 );			
			Select CountryName_dropdown = 	new Select(Country_Dropdown) ;				
			CountryName_dropdown.selectByVisibleText(this.ShippingAddressDetails.getCountry());	

			// AddressField - State			
			if( this.ShippingAddressDetails.getCountry().equals("United States"))
			{
				// For Domestic addresses, state is required and it is a drop down				
				WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 200 );
				Select StateName_Dropdown = 	new Select(State_Dropdown) ;		
				StateName_Dropdown.selectByVisibleText(this.ShippingAddressDetails.getState());	
			}			
			else
			{
				// For International addresses, state is not required and it is a text box
				State_TextBox.sendKeys(this.ShippingAddressDetails.getState());				
			}			
		}
		catch(Exception e){ throw e ;}
	}	
	
	private void fillUserSectionOnScreen() throws Exception{
		
		try {

		// UserField - First Name
		WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 200 );				
		FirstName_TextBox.sendKeys(this.UserDetails.getUserFirstName());			

		// UserField - Last Name
		LastName_TextBox.sendKeys(this.UserDetails.getUserLastName());

		}
		catch(Exception e){ throw e ;}
	}

	private void saveAddress() throws Exception{

		try {
					
		    // Capture the screenshot of the screen before save is clicked			
			BaseTest.TestLog.captureScreenShot("Shipping Address Screen - Before clicking |Continue|");
				
			scrollDown(400);	
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 100 );	
			Continue_Button.click();	
			
			// Based on the zipcode, three types of address save options are shown
			// Below conditions handle each one of these

			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 200 );	

			if( this.ShippingAddressDetails.getCountry().equals("United States"))
			{
				// Address Save Option 1 - Use Address As Entered

				if (this.ShippingAddressDetails.getTypeOfTestData().equals("UseAddressAsEnteredOption"))
				{
					if ( Driver.findElements(UseAddressAsUsedLocator).size() != 0 )
					{
					        // Capture the screenshot of the screen 		
						BaseTest.TestLog.captureScreenShot("Shipping Address Screen - Before Clicking |Use Address As Used|");					
							
						Driver.findElement(UseAddressAsUsedLocator).click();
					}
					else
						throw new TestException("Use Address As Entered Option is not displayed");
				}	

				// Address Save Option 2 - Force Save Address

				else if (this.ShippingAddressDetails.getTypeOfTestData().equals("ForceSaveAddress"))
				{
					if ( Driver.findElements(SaveAddressLocator).size() != 0 )
					{
				       	// Capture the screenshot of the screen 		
						BaseTest.TestLog.captureScreenShot("Shipping Address Screen - Before Clicking |Save Address|");
							
						Driver.findElement(SaveAddressLocator).click();
					
					}
					else
						throw new TestException("Force Save Address Option is not displayed");
				}

				// Address Save Option 3 - Verified Address

				else 
				{
					if ( Driver.findElements(VerifiedAddressLocator).size() != 0 )
					{
				       	// Capture the screenshot screen 		
						BaseTest.TestLog.captureScreenShot("Shipping Address Screen - Before Clicking |Verified Address|");
							
						Driver.findElement(VerifiedAddressLocator).click();
					}
						
				}
				
			}	
		}
		catch(Exception e) { throw e ; }
	}
	
	private void setObjectsNeededForThisScreen(	User UserObj, Address AddressObj, boolean SameAsBillingFlag ) {
			this.UserDetails = UserObj ;
			this.ShippingAddressDetails = AddressObj ;
			this.UseAsBillingAddressFlag = SameAsBillingFlag ;
	}

	//===================================Public Methods===========================================//
	
	public boolean clickAddNewShippingAddress() throws Exception{
		
		WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 200 );	
		
		try {
			
	        // Capture the screenshot screen 		
			BaseTest.TestLog.captureScreenShot(
			"Shipping Address Home Screen - Before Clicking |Add New Shipping Address|");
			
			AddNewShippingAddress_Link.click();
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 100 );	
			return true;
			
		} catch (Exception e) {
			
			throw e;
		}
		
	}
	
	public boolean closeForm() throws InterruptedException{
		
		WaitUtil.waitFor(super.Driver, "IMPLICIT", (long) 10 );
		Close_Button.click();
		
		WaitUtil.waitForWebElementToBeVisible(super.Driver, ConfirmDialogueYes_Button, (long) 10 );
		ConfirmDialogueYes_Button.click();
		
		return true;
		
	}
	
	public boolean createNewShippingAddress() throws Exception{
		
		try {			

			fillUserSectionOnScreen() ;
			fillAddressSectionOnScreen() ;
			if (!this.UseAsBillingAddressFlag ) clearSameAsBillingCheckbox();			
			saveAddress();
			
		} catch (NoSuchElementException e) {
			throw new TestException("Element not found\n" + e.getMessage());			
		} catch (TimeoutException e) {
			throw new TestException("Elements on page did not load within the given time\n" + e.getMessage()) ;
		} catch ( TestException e ){
			throw e ;
		} catch ( Exception e){
			System.out.println("Exception in Shipping Address page:"+ e.getMessage());
			throw e;
		}
		return true ;
	}

	public boolean selectShippingSameAsBilling() throws Exception {
		
		try {
			
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 200 );	
			
			if ( Driver.findElements(SameAsBillingLocator).size()>0)
			{
				SameAsBilling_Checkbox = Driver.findElement(SameAsBillingLocator) ;
			
				if (!SameAsBilling_Checkbox.isSelected())
					SameAsBilling_Checkbox.click();
			
				Save_Button.click();
				WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 200 );	
				return true;
			}
			else throw new TestException("No Same As Billing Checkbox found") ;
		} catch (Exception e) {
			throw e ;
		}
	}	

}
