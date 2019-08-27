package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import utils.WebDriverControlUtils.WaitUtil;

public class BackEndCommonPage extends Page{

	private WebDriver Driver ;
	static String PageUrlExtn = "";
	static String PageTitle ="";
	
	public static String getPageTitle(){		
		return PageTitle;
	}
	
	//==============================================================================================//
	
	@FindBy(how = How.XPATH, using 	= "//a[contains(text(),'Shop')]")	
	private WebElement Shop_Link;
	
	@FindBy(how = How.XPATH, using 	= "//a[contains(text(),'Products')]")	
	private WebElement Products_Link;	
	
	@FindBy(how = How.XPATH, using 	= " //a[contains(text(),'Logout')]")	
	private WebElement BackendLogout_Link;
	
	@FindBy(how = How.XPATH, using 	= "/html/body/nav/div/div/div/ul[2]/li[2]/a")
	private WebElement AdminManagement_DropDown;
	
	@FindBy(how = How.XPATH, using 	= "//a[contains(@can,'create.products')]")
	private WebElement AddProducts_Button;		
	
	@FindBy(how = How.XPATH, using 	= "//a[contains(@can,'create.orders')]")
	private WebElement AddOrder_Button;		
	
	@FindBy(how = How.XPATH, using	= "//a[contains(@can,'create.subscriptions')]")
	private WebElement AddSubscription_Button;	
	
	@FindBy(how = How.XPATH, using 	= "//a[contains(text(),'Orders')]")	
	private WebElement Orders_Link;	
	
	@FindBy(how = How.XPATH, using 	= "//a[contains(text(),'Subscriptions')]")	
	private WebElement Subscriptions_Link;	
	
	private By ProductName_Textbox 	= By.xpath("//input[@placeholder='Enter a product name']");
	
	private By OrderForm_Header 	= By.xpath("//div[contains(@class,\"order-editor\")]//div[contains(@class,\"panel-heading\")]");
	
	private By SubscriptionForm_Header 
									= By.xpath("//div[contains(@class,\"modify-subscription\")]//div[contains(@class,\"panel-heading\")]");
	
	//==============================================================================================//
	
	public BackEndCommonPage(WebDriver CurrentTestDriver){
		 
		super(CurrentTestDriver);
		this.Driver = CurrentTestDriver ;
		PageFactory.initElements(this.Driver, this);
		 
	}
	
	//************************************************************************************************
	// logout - Invoke to logout from the Admin site using the logout from the drop-down menu
	//************************************************************************************************
	
	public void logout() 
	{
		
		try {
			
			WaitUtil.waitFor(Driver, "THREAD_SLEEP", (long)3000);
			
			// Click the User Name drop down to make the Logout link visible
			AdminManagement_DropDown.click();
			
			WaitUtil.waitForWebElementToBeVisible(Driver,AdminManagement_DropDown, (long)3);	
			
			//Click the logout link from the drop down
			BackendLogout_Link.click();			
			
		} 
		catch (InterruptedException e) {
			//do nothing
		}
		
	}	
	
	//************************************************************************************************
	// openAddProductForm - Opens the generic Add Product form (Goto Shop, Select Product and 
	// click Add product)
	//************************************************************************************************
	 
	public boolean openAddProductForm() {
		
		try {
			
			WaitUtil.waitFor(Driver, "THREAD_SLEEP", (long)3000);
			
			// Hover mouse over the shop link
		
			Actions action = new Actions(Driver);		 
		    action.moveToElement(Shop_Link).build().perform();		    
			WaitUtil.waitFor(Driver, "THREAD_SLEEP", (long)2000);
		 
		    Products_Link.click();		    
			WaitUtil.waitFor(Driver, "THREAD_SLEEP", (long)1000);			
					
			AddProducts_Button.click();
			WaitUtil.waitFor(Driver, "THREAD_SLEEP", (long)1000);
			
			List <WebElement> ProductNameTextBox = Driver.findElements(ProductName_Textbox) ;
			
			if (ProductNameTextBox.size() > 0)
			{
				return true ;
			}
			else return false ;
		} 
		catch (Exception e) {
			
			System.out.println("Exception occcured in BackEndCommonPage : openAddProductForm ") ;
			e.printStackTrace();
			return false ;
			
		}
	}
	
	//************************************************************************************************
	// openAddProductForm - Opens the generic Add Order form (Goto Shop, Select Order and 
	// click Add Order)
	//************************************************************************************************
	 
	public boolean openAddOrderForm() {
		
		try {
			
			WaitUtil.waitFor(Driver, "THREAD_SLEEP", (long)3000);
			
			// Hover mouse over the shop link
		
			Actions action = new Actions(Driver);		 
		    action.moveToElement(Shop_Link).build().perform();		    
			WaitUtil.waitFor(Driver, "THREAD_SLEEP", (long)3000);
		 
		    Orders_Link.click();		    
			WaitUtil.waitFor(Driver, "THREAD_SLEEP", (long)3000);			
					
			AddOrder_Button.click();
			WaitUtil.waitFor(Driver, "THREAD_SLEEP", (long)5000);
			
			List <WebElement> orderFormNameWebElementsNameTextBox = Driver.findElements(OrderForm_Header) ;
			
			if (orderFormNameWebElementsNameTextBox.size() > 0)
			{
				return true ;
			}
			else return false ;
		} 
		catch (Exception e) {
			
			System.out.println("Exception occcured in BackEndCommonPage : openAddProductForm ") ;
			e.printStackTrace();
			return false ;
			
		}
	}
	
	//************************************************************************************************
	// openAddSubscriptionForm - Opens the generic Add Subscription form (Goto Shop, Select Subscription
	// and click Add Subscription)
	//************************************************************************************************
	 
	public boolean openAddSubscriptionForm() {
		
		try {
			
			WaitUtil.waitFor(Driver, "THREAD_SLEEP", (long)3000);
			
			// Hover mouse over the shop link
		
			Actions action = new Actions(Driver);		 
		    action.moveToElement(Shop_Link).build().perform();		    
			WaitUtil.waitFor(Driver, "THREAD_SLEEP", (long)3000);
		 
		    Subscriptions_Link.click();		    
			WaitUtil.waitFor(Driver, "THREAD_SLEEP", (long)3000);			
					
			AddSubscription_Button.click();
			WaitUtil.waitFor(Driver, "THREAD_SLEEP", (long)5000);
			
			List <WebElement> subscriptionFormHeaderWebElements = Driver.findElements(SubscriptionForm_Header) ;
			
			if (subscriptionFormHeaderWebElements.size() > 0)
			{
				return true ;
			}
			else return false ;
		} 
		catch (Exception e) {
			
			System.out.println("Exception occcured in BackEndCommonPage : openAddProductForm ") ;
			e.printStackTrace();
			return false ;
			
		}
	}
}
