package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import tests.BaseTest;
import tests.CommonTestLibrary;
import utils.WebDriverControlUtils.WaitUtil;

public class FrontEndCheckoutConfirmationPage extends FrontEndCommonPage{	

	public static final String PageUrlExtn 	= "/store/checkout/confirmation";											
	public static final String PageTitle  		= "Checkout - Confirm Order" ;
	
	public FrontEndCheckoutConfirmationPage(WebDriver CurrentTestDriver) {
		super(CurrentTestDriver);
		PageFactory.initElements(CurrentTestDriver, this);
	}
	
	//=====================================ELEMENT LOCATORS======================================//
	
	@FindBy(how = How.XPATH, using = "//button[@id='placeOrderConfirmButton']")
	@CacheLookup
	private WebElement PlaceOrderConfirm_Button;
	
	public boolean confirmOrder() throws Exception {
	
		try {
				
				WaitUtil.waitFor(Driver, "THREAD_SLEEP",(long)1000);			
				BaseTest.TestLog.logTestStep("Confirm and Place Order");
				
				//Scroll down to get Continue button in view
				BaseTest.getJSExecutor().executeScript("window.scrollBy(0,500)");		
				WaitUtil.waitFor(Driver, "THREAD_SLEEP", (long)1000);
				
				PlaceOrderConfirm_Button.click();	
				
				WaitUtil.waitFor(Driver, "THREAD_SLEEP", (long)1000);				
				CommonTestLibrary.acceptCookies();
				
				if( Driver.getTitle().contains(FrontEndOrderSuccessPage.PageTitle )){
					BaseTest.TestLog.logTestStep("Product Purchase Successful");					
					return true ;							
				}
				else return false ;		
		}
		catch (Exception e) {
			throw e ;			
		}
	}		
}	 	