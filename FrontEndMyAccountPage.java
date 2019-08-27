package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import tests.BaseTest;
import utils.ErrorHandlerUtils.TestException;
import utils.WebDriverControlUtils.WaitUtil;

public class FrontEndMyAccountPage extends FrontEndCommonPage{	

	public static final String PageUrlExtn 	= "/my-account";											
	public static final String PageTitle  		= "My Account" ;
	
	@FindBy(how = How.XPATH, using = "//h3[@id='subscriptions']")
	@CacheLookup
	private WebElement Subscriptions_Header;
	
	@FindBy(how = How.XPATH, using = "//h3[contains(text(),'Memberships')]")
	@CacheLookup
	private WebElement Membership_Header;
	
	@FindBy(how = How.XPATH, using = "//h3[contains(text(),'User Details')]")
	@CacheLookup
	private WebElement User_Header;
	
	@FindBy(how = How.XPATH, using = "//h3[contains(text(),'Payment Methods')]")
	@CacheLookup
	private WebElement PaymentMethod_Header;
	
	
	@FindBy(how = How.XPATH, using = "//h3[contains(text(),'Order History')]")
	@CacheLookup
	private WebElement Orders_Header;	
	
	@FindBy(how = How.LINK_TEXT , using = "Upgrade & Join Today")
	@CacheLookup
	private WebElement UpgradeLinkForStarter_Button;
	
	@FindBy(how = How.LINK_TEXT , using = "Upgrade")
	@CacheLookup
	private WebElement UpgradeLinkForOthers_Button;
	
	@FindBy(how = How.XPATH , using = "//strong[contains(text(),'IOFM Membership - Business')]")
	@CacheLookup
	private WebElement BusinessUpgrade_Option;
	
	@FindBy(how = How.XPATH , using = "//strong[contains(text(),'IOFM Membership - Enterprise')]")
	@CacheLookup
	private WebElement EnterpriseUpgrade_Option;
	
	@FindBy(how = How.XPATH , using = "//div[@class='modal-footer']//button[2]")
	@CacheLookup
	private WebElement PayAndChange_Button ;
	
	private By SubscriptionChange_Form = By.xpath("//div[@class='subscription-change-form ng-scope ng-isolate-scope']") ;
	
	private By AlterDismissOk_Button = By.xpath("//button[@ng-click='alertCtl.dismiss()']");
	
	public FrontEndMyAccountPage(WebDriver CurrentTestDriver) {
		super(CurrentTestDriver);
		PageFactory.initElements(CurrentTestDriver, this);
	}	
	public boolean viewSubscriptions() throws InterruptedException {		
		
		WaitUtil.waitFor(Driver, "THREAD_SLEEP",(long)1000);			
		BaseTest.TestLog.logTestStep("Subscription Details on My Account Page");		
		
		//Scroll down to get the Subscription section in view
		BaseTest.getJSExecutor().executeScript("arguments[0].scrollIntoView(true);",Subscriptions_Header);
		
		WaitUtil.waitFor(Driver, "THREAD_SLEEP",(long)1000);			
		
		BaseTest.TestLog.captureScreenShot("Subscription Section");
		
		WaitUtil.waitFor(Driver, "THREAD_SLEEP",(long)1000);	
		return true;			
		
	}
	public boolean viewOrders() throws InterruptedException {		
		
		WaitUtil.waitFor(Driver, "THREAD_SLEEP",(long)1000);			
		BaseTest.TestLog.logTestStep("Order Details on My Account Page");		
		
		//Scroll down to get the Subscription section in view
		BaseTest.getJSExecutor().executeScript("arguments[0].scrollIntoView(true);",Orders_Header);
		
		WaitUtil.waitFor(Driver, "THREAD_SLEEP",(long)1000);			
		
		BaseTest.TestLog.captureScreenShot("Order  Section");
		
		WaitUtil.waitFor(Driver, "THREAD_SLEEP",(long)1000);	
		return true;				
		
	}	
	public boolean viewUserAndAddressDetails() throws InterruptedException {		
		
		WaitUtil.waitFor(Driver, "THREAD_SLEEP",(long)1000);			
		BaseTest.TestLog.logTestStep("User and Address Details on My Account Page");		
		
		//Scroll down to get the Subscription section in view
		BaseTest.getJSExecutor().executeScript("arguments[0].scrollIntoView(true);",User_Header);
		
		WaitUtil.waitFor(Driver, "THREAD_SLEEP",(long)1000);			
		
		BaseTest.TestLog.captureScreenShot("User and Address Section");
		
		WaitUtil.waitFor(Driver, "THREAD_SLEEP",(long)1000);	
		return true;				
		
	}	
	
	public boolean viewPaymentMethod() throws InterruptedException {		
		
		WaitUtil.waitFor(Driver, "THREAD_SLEEP",(long)1000);			
		BaseTest.TestLog.logTestStep("Payment Details on My Account Page");			
		
		//Scroll down to get the Subscription section in view
		BaseTest.getJSExecutor().executeScript("arguments[0].scrollIntoView(true);",PaymentMethod_Header);
		
		WaitUtil.waitFor(Driver, "THREAD_SLEEP",(long)1000);			
		
		BaseTest.TestLog.captureScreenShot("Payment  Section");
		
		WaitUtil.waitFor(Driver, "THREAD_SLEEP",(long)1000);	
		return true;				
		
	}	
	public boolean upgradeIOFMMembership(String SourceMembership, String TargetMembership) throws InterruptedException, TestException {
		
		if (SourceMembership.equals("Starter")) {
			
			WaitUtil.waitFor(Driver,"THREAD_SLEEP",(long)1000);			
			UpgradeLinkForStarter_Button.click();			
			WaitUtil.waitFor(Driver,"THREAD_SLEEP",(long)1000);
			
			if (Driver.getTitle().equals(FrontEndIOFMJoinPage.PageTitle)) {
				return true ;
			}
			else return false ;
		}
		else {
			
			WaitUtil.waitFor(Driver,"THREAD_SLEEP",(long)1000);			
			UpgradeLinkForOthers_Button.click();			
			WaitUtil.waitFor(Driver,"THREAD_SLEEP",(long)1000);		
			
			if (Driver.findElements(SubscriptionChange_Form).size() == 0) {
				throw new TestException("Subscription Upgrade Form Not Displayed") ;
			}
			
			if (SourceMembership.equals("Professional")) {
				
				if ( TargetMembership.equals("Business")) {
					
					WaitUtil.waitFor(Driver,"THREAD_SLEEP",(long)500);	
					BusinessUpgrade_Option.click();
					WaitUtil.waitFor(Driver,"THREAD_SLEEP",(long)1000);	
					
				}
				else if (TargetMembership.equals("Enterprise")){
					
					WaitUtil.waitFor(Driver,"THREAD_SLEEP",(long)500);	
					EnterpriseUpgrade_Option.click();
					WaitUtil.waitFor(Driver,"THREAD_SLEEP",(long)1000);	
					
				}
				else {
					new TestException("Incorrect Target Membership value") ;
				}
			}			
				
			if (SourceMembership.equals("Business")) {
			
				if (TargetMembership.equals("Enterprise")){
				
					WaitUtil.waitFor(Driver,"THREAD_SLEEP",(long)500);	
					EnterpriseUpgrade_Option.click();
					WaitUtil.waitFor(Driver,"THREAD_SLEEP",(long)1000);	
				
				}
				else {
					new TestException("Incorrect Target Membership value") ;
				}
			}
			
			WaitUtil.waitFor(Driver,"THREAD_SLEEP",(long)500);	
			
			PayAndChange_Button.click();
			WaitUtil.waitFor(Driver,"THREAD_SLEEP",(long)1000);		
			
			Driver.findElement(AlterDismissOk_Button).click() ;			
			WaitUtil.waitFor(Driver,"THREAD_SLEEP",(long)1000);	
			
			WaitUtil.waitFor(Driver, "THREAD_SLEEP",(long)1000);			
			BaseTest.TestLog.logTestStep("Inspect Membership ");		
			
			//Scroll down to get the Subscription section in view
			BaseTest.getJSExecutor().executeScript("arguments[0].scrollIntoView(true);",Membership_Header);
			
			WaitUtil.waitFor(Driver, "THREAD_SLEEP",(long)1000);			
			
			BaseTest.TestLog.captureScreenShot("Membership Section");
			
			WaitUtil.waitFor(Driver, "THREAD_SLEEP",(long)1000);	
			return true;		
			
		}	
	}	
}	 
	