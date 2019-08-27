package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import coreapplicationclassesandinterfaces.Address;
import coreapplicationclassesandinterfaces.Order;
import coreapplicationclassesandinterfaces.User;
import tests.BaseTest;
import utils.WebDriverControlUtils.WaitUtil;

public class BackEndOrderPage extends Page{

	private WebDriver 	Driver ;
	private Order 		OrderObj ;
	private String		CustomerFirstName ;
	private String		CustomerLastName ;
	static 	String 		PageUrlExtn = "";
	static	String 		PageTitle =	"";
	
	public static String getPageTitle(){		
		return PageTitle;
	}
	
	// Order Details tab
	@FindBy(how = How.XPATH, using = "//input[@placeholder='Search For A Customer']")
	private WebElement CustomerName_Search;	
	
	@FindBy(how = How.XPATH, using = "//select[contains(@ng-model,'editCtl.data.order_status')]")
	private WebElement OrderStatus_Select;	
	
	@FindBy(how = How.XPATH, using = "//input[contains(@ng-model,'editCtl.data.email_disabled')]")
	private WebElement EmailDisabled_Checkbox;
	
	private By CustomerName_DropDown = By.xpath("//lv-customer-search[@placeholder='Search For A Customer']//li[1]") ;
	
	//Address Tab - Shipping Address Section	
	
	private By ShippingAddress_Section = By.xpath("//div[contains(@class,'tab-pane ng-scope active')]//fieldset[1]//div[@class='form-group']");
	
	//Address Tab - Billing Address Section

	private By BillingAddress_Section = By.xpath("//div[contains(@class,'tab-pane ng-scope active')]//fieldset[2]//div[@class='form-group']");
	
	private By TextField_Path = By.xpath(".//input") ;
	
	private By SelectField_Path = By.xpath(".//select") ;
	
	private By ShippingState_Select = By.xpath("//div[@ng-show='editCtl.data.shipping_country']//select[@ng-model='editCtl.data.shipping_state']");
	
	private By ShippingState_TextBox = By.xpath("//div[@ng-show='editCtl.data.shipping_country']//input[@ng-model='editCtl.data.shipping_state']");
	
	private By BillingState_Select = By.xpath("//div[@ng-show='editCtl.data.billing_country']//select[@ng-model='editCtl.data.billing_state']");
	
	private By BillingState_TextBox = By.xpath("//div[@ng-show='editCtl.data.billing_country']//input[@ng-model='editCtl.data.billing_state']");
	
	@FindBy(how = How.XPATH, using 	= "//div[contains(@class,'panel-footer')]//button[3]")
	private WebElement SaveAndClose_Button;		
	
	@FindBy( how=How.XPATH, using 	= "//form[contains(@name,'frmData')]//ul[contains(@class,'nav nav-tabs')]//li[2]/a")
	private WebElement AddressTab_LinkElement ;
	
	@FindBy( how=How.XPATH, using 	= "//form[contains(@name,'frmData')]//ul[contains(@class,'nav nav-tabs')]//li[3]/a")
	private WebElement OrderItemsTab_LinkElement ;
		
	// Order items tab
	@FindBy(how = How.XPATH, using = "//input[contains(@placeholder,'Search for Products')]")
	private WebElement OrderItem_Search;
	
	private By OrderItem_DropDown = By.xpath("//lv-product-search[contains(@placeholder,'Search for Products')]//li[1]") ;
	
	//********************************************************************************************
	// Constructors
	//********************************************************************************************
	
	public BackEndOrderPage(WebDriver CurrentTestDriver){		 
		super(CurrentTestDriver);
		this.Driver = CurrentTestDriver ;
		PageFactory.initElements(this.Driver, this);
	}
	
	public BackEndOrderPage(WebDriver CurrentTestDriver, Order newOrder, User Customer) {
		super(CurrentTestDriver);
		this.Driver = CurrentTestDriver ;
		PageFactory.initElements(this.Driver, this);
		setObjectsForThisPage(newOrder, Customer);
	}

	//********************************************************************************************
	// Internal methods
	//********************************************************************************************
	
	private boolean enterAddressFields(Address AddressDetails, By Path) {
		
		try {			
			
			// Capture the screenshot for logging			
			BaseTest.TestLog.captureScreenShot(" Address Form - Before Entering Details ");
			
			List <WebElement> addressFields = this.Driver.findElements(Path) ;
			
			if (addressFields.size() > 0) {
				
				addressFields.get(0).findElement(TextField_Path).sendKeys(CustomerFirstName);
				WaitUtil.waitFor(this.Driver, "THREAD_SLEEP", (long)1000);
				
				addressFields.get(1).findElement(TextField_Path).sendKeys(CustomerLastName);
				WaitUtil.waitFor(this.Driver, "THREAD_SLEEP", (long)1000);
				
				addressFields.get(2).findElement(TextField_Path).sendKeys(AddressDetails.getPhone());
				WaitUtil.waitFor(this.Driver, "THREAD_SLEEP", (long)1000);
				
				addressFields.get(3).findElement(TextField_Path).sendKeys(AddressDetails.getAddressLine1());
				WaitUtil.waitFor(this.Driver, "THREAD_SLEEP", (long)1000);
				
				addressFields.get(4).findElement(TextField_Path).sendKeys(AddressDetails.getAddressLine2());
				WaitUtil.waitFor(this.Driver, "THREAD_SLEEP", (long)1000);
				
				addressFields.get(5).findElement(TextField_Path).sendKeys(AddressDetails.getCity());
				WaitUtil.waitFor(this.Driver, "THREAD_SLEEP", (long)1000);	
				
				// AddressField - Country
				WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 200 );			
				Select CountryName_dropdown = 	new Select(addressFields.get(6).findElement(SelectField_Path)) ;				
				CountryName_dropdown.selectByVisibleText(AddressDetails.getCountry());					
				
				WaitUtil.waitFor(this.Driver, "THREAD_SLEEP", (long)1000);
				addressFields.get(7).findElement(TextField_Path).sendKeys(AddressDetails.getPostalCode());
				WaitUtil.waitFor(this.Driver, "THREAD_SLEEP", (long)1000);
				
				return true ;
			}
			
			else 
				return false ;
		
		}
		catch (Exception e) {
			System.out.println("Exception Occured in Backendorderpage.java : enterAddressFields");
			System.out.println(e.getMessage());
			return false ;
		}
		
	}

	private void setObjectsForThisPage(Order newOrder, User Customer) {
		
		this.OrderObj = newOrder ;		
		CustomerFirstName = Customer.getUserFirstName() ;
		CustomerLastName  = Customer.getUserLastName() ;
	}	
	
	//********************************************************************************************
	// Public methods
	//********************************************************************************************
	
	public boolean clickSaveAndClose() throws InterruptedException {
		
		BaseTest.TestLog.logTestStep("Clicking Save and Close");
		SaveAndClose_Button.click() ;
		WaitUtil.waitFor(Driver, "THREAD_SLEEP",(long)10000);
		return true ;
		
	}
	
	public boolean enterOrderDetails() throws Exception {
	
		// Capture the screenshot for logging			
		BaseTest.TestLog.captureScreenShot("Order Form - Before Entering Details ");
		
		try {
			
			// Customer Name		
			
			String 	CustomerName		= 	CustomerFirstName.replaceAll("\\s","") ;
			String 	CustomerNameSubstring ;
			int 	letterIndex 		= 	0;
			int 	CustomerNameLength 	= 	CustomerName.length() ;		

			while(letterIndex+5 < CustomerNameLength)
			{
				CustomerNameSubstring 	= 	CustomerName.substring(letterIndex,letterIndex+5) ;
				letterIndex				=	letterIndex+5 ;
						
				WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 5000 );	
				CustomerName_Search.sendKeys(CustomerNameSubstring);
				WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 5000 );	
			
				List <WebElement> autoProductSuggestions = Driver.findElements(CustomerName_DropDown) ;
			
				if (autoProductSuggestions.size() > 0)
				{					
					autoProductSuggestions.get(0).click();
					break ;
				}					
			}
			
			// Order Status
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long)1000);		
			Select OrderStatus_dropdown = 	new Select(OrderStatus_Select) ;		
			OrderStatus_dropdown.selectByVisibleText(this.OrderObj.getOrderStatus());		
			
			// Email Disabled 
			if ( this.OrderObj.getStatusChangeEmailsDisabledOption().toLowerCase().equals("yes")) {
				WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 1000 );	
				EmailDisabled_Checkbox.click();
			}				
			
			return true ;			
			
		} catch (Exception e) {
			System.out.println("Exception Occured in Backendorderpage.java : enterOrderDetails");
			System.out.println(e.getMessage());
			return false ;
		}
	}

	public boolean enterAddresses(	Address defaultShippingAddress, 
									Address defaultBillingAddress) {		
		
		try {
			
			WaitUtil.waitFor(this.Driver, "THREAD_SLEEP", (long)1000);
			
			AddressTab_LinkElement.click();
			
			if ( enterAddressFields(defaultShippingAddress, ShippingAddress_Section)) {
				
				// AddressField - State			
				if( defaultShippingAddress.getCountry().equals("United States"))
				{
					// For Domestic addresses, state is required and it is a drop down				
					WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 2000 );
					Select StateName_Dropdown = 	new Select(this.Driver.findElement(ShippingState_Select)) ;		
					StateName_Dropdown.selectByVisibleText(defaultShippingAddress.getState());	
				}			
				else
				{					
					WaitUtil.waitFor(this.Driver, "THREAD_SLEEP", (long)1000);
					// For International addresses, state is not required and it is a text box
					this.Driver.findElement(ShippingState_TextBox).sendKeys(defaultShippingAddress.getState());				
				} 
				
				WaitUtil.waitFor(this.Driver, "THREAD_SLEEP", (long)1000);
				scrollDown(500);				
				WaitUtil.waitFor(this.Driver, "THREAD_SLEEP", (long)1000);
				
				if( enterAddressFields(defaultBillingAddress, BillingAddress_Section)) {
					
					// AddressField - State			
					if( defaultBillingAddress.getCountry().equals("United States"))
					{
						// For Domestic addresses, state is required and it is a drop down				
						WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 2000 );
						Select StateName_Dropdown = 	new Select(this.Driver.findElement(BillingState_Select)) ;		
						StateName_Dropdown.selectByVisibleText(defaultBillingAddress.getState());	
					}			
					else
					{					
						WaitUtil.waitFor(this.Driver, "THREAD_SLEEP", (long)1000);
						// For International addresses, state is not required and it is a text box
						this.Driver.findElement(BillingState_TextBox).sendKeys(defaultBillingAddress.getState());				
					} 
					
					return true ;
				}
				else return false ;
			}				
			else {
				return false ;
			}
			
		}
		catch (Exception e) {
			System.out.println("Exception Occured in Backendorderpage.java : enterAddresses");
			System.out.println(e.getMessage());
			return false ;
		}
	}

	public boolean selectOrderItems() {		

		try {
		
			WaitUtil.waitFor(this.Driver, "THREAD_SLEEP", (long)1000);			
			OrderItemsTab_LinkElement.click();
			
			// Capture the screenshot for logging			
			BaseTest.TestLog.captureScreenShot("Add Order Items - Before Entering Details ");					
			
			// Order Item 1
			
			String 	ItemName			= 	this.OrderObj.getOrder1Name().replaceAll("\\s","") ;
			String 	ItemNameSubstring ;
			int 	letterIndex 		= 	0;
			int 	ItemNameLength 		= 	ItemName.length() ;		
			
			if (!ItemName.equals("NotUsed")) {					
			
				while(letterIndex+5 < ItemNameLength)
				{
					ItemNameSubstring 		= 	ItemName.substring(letterIndex,letterIndex+5) ;
					letterIndex				=	letterIndex+5 ;
							
					WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 5000 );	
					OrderItem_Search.sendKeys(ItemNameSubstring);
					WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 5000 );	
				
					List <WebElement> autoProductSuggestions = Driver.findElements(OrderItem_DropDown) ;
				
					if (autoProductSuggestions.size() > 0)
					{					
						autoProductSuggestions.get(0).click();
						break ;
					}					
				}
			}
			
			// Order Item 2
			
			ItemName			= 	this.OrderObj.getOrder2Name().replaceAll("\\s","") ;
			letterIndex 		= 	0;
			ItemNameLength 		= 	ItemName.length() ;		
			
			if (!ItemName.equals("NotUsed")) {					
			
				while(letterIndex+5 < ItemNameLength)
				{
					ItemNameSubstring 		= 	ItemName.substring(letterIndex,letterIndex+5) ;
					letterIndex				=	letterIndex+5 ;
							
					WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 5000 );	
					OrderItem_Search.sendKeys(ItemNameSubstring);
					WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 5000 );	
				
					List <WebElement> autoProductSuggestions = Driver.findElements(OrderItem_DropDown) ;
				
					if (autoProductSuggestions.size() > 0)
					{					
						autoProductSuggestions.get(0).click();
						break ;
					}					
				}
			}
						
			// Order Item 3
			
			ItemName			= 	this.OrderObj.getOrder3Name().replaceAll("\\s","") ;
			letterIndex 		= 	0;
			ItemNameLength 		= 	ItemName.length() ;		
			
			if (!ItemName.equals("NotUsed")) {					
			
				while(letterIndex+5 < ItemNameLength)
				{
					ItemNameSubstring 		= 	ItemName.substring(letterIndex,letterIndex+5) ;
					letterIndex				=	letterIndex+5 ;
							
					WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 5000 );	
					OrderItem_Search.sendKeys(ItemNameSubstring);
					WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 5000 );	
				
					List <WebElement> autoProductSuggestions = Driver.findElements(OrderItem_DropDown) ;
				
					if (autoProductSuggestions.size() > 0)
					{					
						autoProductSuggestions.get(0).click();
						break ;
					}					
				}
			}						
			
			return true ;
			
		}
		catch (Exception e) {
			System.out.println("Exception Occured in Backendorderpage.java : enterAddresses");
			System.out.println(e.getMessage());
			return false ;
		}
	}
	
}