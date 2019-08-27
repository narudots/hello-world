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
import coreapplicationclassesandinterfaces.BackendSubscription;
import coreapplicationclassesandinterfaces.Order;
import coreapplicationclassesandinterfaces.User;
import tests.BaseTest;
import utils.WebDriverControlUtils.WaitUtil;

public class BackEndSubscriptionPage extends Page{

	private WebDriver 					Driver ;
	private BackendSubscription 		SubscriptionObj ;
	private String						CustomerName ;
	static 	String 						PageUrlExtn = "";
	static	String 						PageTitle =	"";
	
	public static String getPageTitle(){		
		return PageTitle;
	}
	
	// Order Details tab
	@FindBy(how = How.XPATH, using = "//input[@placeholder='Search For A Customer']")
	private WebElement CustomerName_Search;	
	
	@FindBy(how = How.XPATH, using = "//input[@placeholder='Search For A Product']")
	private WebElement Product_Search;	
	
	@FindBy(how = How.XPATH, using = "//select[contains(@ng-model,'editCtl.data.subscription_status')]")
	private WebElement SubscriptionStatus_Dropdown;
	
	@FindBy(how = How.XPATH, using = "//select[contains(@ng-model,'editCtl.data.subscription_period')]")
	private WebElement SubscriptionPeriod_Dropdown;
	
	@FindBy(how = How.XPATH, using = "//input[contains(@ng-model,'editCtl.data.subscription_length')]")
	private WebElement SubscriptionLength_TextBox;
	
	@FindBy(how = How.XPATH, using = "//input[contains(@ng-model,'editCtl.data.subscription_grandfathered')]")
	private WebElement Grandfathered_Checkbox;
	
	@FindBy(how = How.XPATH, using = "//input[contains(@ng-model,'editCtl.data.autorenew')]")
	private WebElement AutoRenew_Checkbox;
	
	@FindBy(how = How.XPATH, using = "//input[contains(@ng-model,'editCtl.data.starts_at')]")
	private WebElement Starts_DatePicker;
	
	@FindBy(how = How.XPATH, using = "//input[contains(@ng-model,'editCtl.data.expires_at')]")
	private WebElement Ends_DatePicker;
	
	private By CustomerName_DropDown = By.xpath("//lv-customer-search[@placeholder='Search For A Customer']//li[1]") ;
	
	@FindBy(how = How.XPATH, using 	= "//div[contains(@class,'panel-footer')]//button[3]")
	private WebElement SaveAndClose_Button;		
	
	private By ProductName_DropDown = By.xpath("//lv-product-search[@placeholder='Search For A Product']//li[1]") ;
	
	//********************************************************************************************
	// Constructors
	//********************************************************************************************
	
	public BackEndSubscriptionPage(WebDriver CurrentTestDriver){		 
		super(CurrentTestDriver);
		this.Driver = CurrentTestDriver ;
		PageFactory.initElements(this.Driver, this);
	}
	
	public BackEndSubscriptionPage(WebDriver CurrentTestDriver, BackendSubscription newSubscription, User Customer) {
		super(CurrentTestDriver);
		this.Driver = CurrentTestDriver ;
		PageFactory.initElements(this.Driver, this);
		setObjectsForThisPage(newSubscription, Customer);
	}

	//********************************************************************************************
	// Internal methods
	//********************************************************************************************

	private void setObjectsForThisPage(BackendSubscription newSubscription, User Customer) {
		
		this.SubscriptionObj = newSubscription ;		
		CustomerName = Customer.getUserFirstName()+Customer.getUserLastName() ;
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
	
	public boolean enterSubscriptionDetails() throws Exception {
	
		// Capture the screenshot for logging			
		BaseTest.TestLog.captureScreenShot("Subscription Form - Before Entering Details ");
		
		try {
			
			// Customer Name		
			
			String 	SearchStringName	= 	this.CustomerName.replaceAll("\\s","") ;
			String 	SearchNameSubstring ;
			int 	letterIndex 		= 	0;
			int 	SearchNameLength 	= 	SearchStringName.length() ;		

			while(letterIndex+5 < SearchNameLength)
			{
				SearchNameSubstring 	= 	SearchStringName.substring(letterIndex,letterIndex+5) ;
				letterIndex				=	letterIndex+5 ;
						
				WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 5000 );	
				CustomerName_Search.sendKeys(SearchNameSubstring);
				WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 5000 );	
			
				List <WebElement> autoNameSuggestions = Driver.findElements(CustomerName_DropDown) ;
			
				if (autoNameSuggestions.size() > 0)
				{					
					autoNameSuggestions.get(0).click();
					break ;
				}					
			}
			
			// Product Name		
			
			SearchStringName	= 	this.SubscriptionObj.getProductName().replaceAll("\\s","") ;
			letterIndex 		= 	0;
			SearchNameLength 	= 	SearchStringName.length() ;		

			while(letterIndex+5 < SearchNameLength)
			{
				SearchNameSubstring 	= 	SearchStringName.substring(letterIndex,letterIndex+5) ;
				letterIndex				=	letterIndex+5 ;
						
				WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 5000 );	
				Product_Search.sendKeys(SearchNameSubstring);
				WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 5000 );	
			
				List <WebElement> autoNameSuggestions = Driver.findElements(ProductName_DropDown) ;
			
				if (autoNameSuggestions.size() > 0)
				{					
					autoNameSuggestions.get(0).click();
					break ;
				}					
			}
			
			// Status
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long)1000);		
			Select Status_dropdown = 	new Select(SubscriptionStatus_Dropdown) ;		
			Status_dropdown.selectByVisibleText(this.SubscriptionObj.getStatus());		

			// Period
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long)1000);		
			Select Period_dropdown = 	new Select(SubscriptionPeriod_Dropdown) ;		
			Period_dropdown.selectByVisibleText(this.SubscriptionObj.getPeriod());	
			
			//Length
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long)1000);		
			SubscriptionLength_TextBox.sendKeys(this.SubscriptionObj.getLength());
			
			/*Select Length_dropdown = 	new Select(SubscriptionLength_Dropdown) ;		
			Length_dropdown.selectByVisibleText(this.SubscriptionObj.getLength());	*/
			
			// Grandfathered pricing 
			if ( this.SubscriptionObj.getGrandfatheredOption().toLowerCase().equals("yes")) {
				WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 1000 );	
				Grandfathered_Checkbox.click();
			}	
			
			// Auto Renew
			if ( this.SubscriptionObj.getAutoRenewOption().toLowerCase().equals("yes")) {
				WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 1000 );	
				AutoRenew_Checkbox.click();
			}			
			
			// Starts
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long)1000);		
			Starts_DatePicker.sendKeys(this.SubscriptionObj.getSubscriptionStartDate());		

			// Expires
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long)1000);		
			Ends_DatePicker.sendKeys(this.SubscriptionObj.getSubscriptionExpiryDate());
									
			return true ;			
			
		} catch (Exception e) {
			System.out.println("Exception Occured in Backendsubscriptionpage.java : enterSubscriptionDetails");
			System.out.println(e.getMessage());
			return false ;
		}
	}	
}