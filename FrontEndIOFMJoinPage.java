package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import tests.BaseTest;
import utils.WebDriverControlUtils.WaitUtil;

public class FrontEndIOFMJoinPage extends FrontEndCommonPage{

	public FrontEndIOFMJoinPage(WebDriver CurrentTestDriver) {
		super(CurrentTestDriver);
		PageFactory.initElements(CurrentTestDriver, this);
	}
	
	public static String PageUrlExtn = "/join";
	public static String PageTitle ="Join Today | Institute of Finance & Management" ;

	//==================================JOIN PROFESSIONAL BUTTON====================================//
	
	@FindBy(how = How.XPATH, using = 
			"//thead//a[@class='joinBtn tealBtn join-professional'][contains(text(),'Join Today')]")
	@CacheLookup
	private WebElement JoinProfessional_Button;
	
	//====================================JOIN BUSINESS BUTTON======================================//
	
	@FindBy(how = How.XPATH, using = 
			"//thead//a[@class='joinBtn tealBtn join-business'][contains(text(),'Join Today')]")
	@CacheLookup
	private WebElement JoinBusiness_Button;
	
	//===================================JOIN ENTERPRISE BUTTON=====================================//
	
	@FindBy(how = How.XPATH, using = 
			"//thead//a[@class='joinBtn tealBtn join-enterprise'][contains(text(),'Join Today')]")
	@CacheLookup
	private WebElement JoinEnterprise_Button;
	
	//======================================JOIN STARTER LINK=======================================//
	
	@FindBy(how = How.XPATH, using = "//a[@href='/membership/starter']")
	@CacheLookup
	private WebElement JoinStarter_Link ;
		
	//************************************************************************************************
	// addMembershipProductToCart() 
	// 1) Add the appropriate membership to Cart
	// 2) If Starter membership, 2 extra screens to navigate before product is added to Cart
	//************************************************************************************************
		
	public boolean addMembershipProductToCart( String MembershipType) throws Exception{
		
		WebElement	MembershipToSelect		= 	null;	
		String TargetPageTitle 				= 	null;
			
		try {
			
			switch (MembershipType){
						
			case "Starter" 			: 	BaseTest.TestLog.logTestStep("Launch Starter Join Page");
													MembershipToSelect 	= 	JoinStarter_Link ; 
													TargetPageTitle 	=	FrontEndIOFMStarterMembershipJoinPage.PageTitle ;
													break ;
			case "Professional" :  	BaseTest.TestLog.logTestStep("Add Membership Product to Cart");
													MembershipToSelect 	= 	JoinProfessional_Button ; 
													TargetPageTitle 	=	FrontEndShoppingCartPage.PageTitle ;
													break ;													
			case "Business"		: 	BaseTest.TestLog.logTestStep("Add Membership Product to Cart");
													MembershipToSelect 	= 	JoinBusiness_Button ; 
													TargetPageTitle 	=	FrontEndShoppingCartPage.PageTitle ;
													break ;
			case "Enterprise"		: 	BaseTest.TestLog.logTestStep("Add Membership Product to Cart");
													MembershipToSelect 	= 	JoinEnterprise_Button ; 
													TargetPageTitle 	=	FrontEndShoppingCartPage.PageTitle ;																	
			}
			
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long)1000);
			
			//BaseTest.getJSExecutor().executeScript("window.scrollBy(0,1800)");
			//WaitUtil.waitFor(Driver, "THREAD_SLEEP",(long) 1000);
			
			MembershipToSelect.click();	
			
			if (!MembershipType.equals("Starter")) {
				
				WaitUtil.waitFor(Driver, "THREAD_SLEEP",(long)1000);			
				// Capture the screenshot for logging	
				BaseTest.TestLog.captureScreenShot("Shopping Cart Page | After Adding Membership Product");
				
			}
			if(Driver.getTitle().contains(TargetPageTitle)){				
				return true ;				
			}
			else return false ;			
		} 
		catch (Exception e) {
			throw e;		
		}				
	}		 
}
