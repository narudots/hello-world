package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import tests.BaseTest;
import utils.WebDriverControlUtils.WaitUtil;

public class FrontEndCommonAddToCartPage extends FrontEndCommonPage{

	// Both are set to null since this is a common page and differs for each product
	
	public static String PageUrlExtn	= "";
	public static String PageTitle 		= "";
	
	//=====================================ADD TO CART BUTTON=======================================//
	
	@FindBy(how = How.NAME, using = "add-to-cart")
	@CacheLookup
	private WebElement AddToCart_Button;
	
	//====================================PAGE ELEMENTS INITIALIZATION==============================//
	
	public FrontEndCommonAddToCartPage(WebDriver CurrentTestDriver){
		 
		super(CurrentTestDriver);
		PageFactory.initElements(CurrentTestDriver, this);
		 
	} 
	public boolean addToCart() throws Exception{
		
		try {
			
			WaitUtil.waitFor(Driver, "THREAD_SLEEP",(long)1000);
			
			BaseTest.TestLog.logTestStep("Add Starter Membership Product to Cart");
			AddToCart_Button.click();	
			
			WaitUtil.waitFor(Driver, "THREAD_SLEEP",(long)1000);
			
			 // Capture the screenshot for logging	
			BaseTest.TestLog.captureScreenShot("Shopping Cart Page | After Adding Product");
			
			if( Driver.getTitle().contains(FrontEndShoppingCartPage.PageTitle )){
				return true ;							
			}
			else return false ;		
		}
		catch (Exception e) {
			throw e ;			
		}
				
	}	 
}
