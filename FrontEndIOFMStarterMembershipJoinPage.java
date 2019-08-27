package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import tests.BaseTest;
import utils.WebDriverControlUtils.WaitUtil;

public class FrontEndIOFMStarterMembershipJoinPage extends FrontEndCommonPage{
	
	public static String PageUrlExtn 	= "/membership/starter";
	public static String PageTitle			 = "Starter Membership";	
			
	//************************************JOIN TODAY BUTTON**************************************//
	
	@FindBy(how = How.XPATH, using = "//a[@href='/store/products/9a6dbfc8-2fdb-4281-97a4-c572d3cdfb41']")
	@CacheLookup		
	private WebElement JoinToday_Button;	
	
	//****************************************CONSTRUCTOR****************************************//	
	public FrontEndIOFMStarterMembershipJoinPage(WebDriver CurrentTestDriver) {
			super(CurrentTestDriver);
			PageFactory.initElements(CurrentTestDriver, this);
	}	
	
	//*******************************************METHODS*****************************************//
	public boolean launchStarterProductPage() throws Exception{		 
		try {			
			
				WaitUtil.waitFor(Driver, "THREAD_SLEEP",(long)1000);
				
				BaseTest.TestLog.logTestStep("Launch Starter Product Page");
				JoinToday_Button.click();			
				
				if( Driver.getTitle().contains(FrontEndShoppingCartPage.PageTitle)){					
					return true ;			
			}		
			else {
				return false ;
			}
		} 
		catch (Exception e) {
			throw e ;			
		}
	}	 
}
