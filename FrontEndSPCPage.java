package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import coreapplicationclassesandinterfaces.Discount;
import tests.BaseTest;
import utils.ErrorHandlerUtils.TestException;
import utils.WebDriverControlUtils.WaitUtil;

public class FrontEndSPCPage extends Page{
	
	public static final String PageUrl = "/store/checkout";
	public static final String PageTitle ="Checkout";
	
	@FindBy(how = How.LINK_TEXT, using = "‹ Continue shopping")
	@CacheLookup	
	private WebElement ContinueShopping_Link;	

	@FindBy(how = How.CLASS_NAME, using = "checkout__coupon-btn")
	@CacheLookup	
	private WebElement AddCoupon_Link;	
	
	@FindBy(how = How.XPATH, using = "//input[@name='discount_code']")
	@CacheLookup	
	private WebElement CouponCode_TextBox;		
	
	@FindBy(how = How.ID, using = "placeOrderConfirmButton")
	@CacheLookup	
	private WebElement BuyNow_button;		
	
	public FrontEndSPCPage(WebDriver CurrentTestDriver){		
		super(CurrentTestDriver);		
		PageFactory.initElements(this.Driver, this);		 
	}	
	
	//========================================Methods===============================================//
	
	public boolean goToShoppingCartPage() throws InterruptedException, TestException {
		 
		// Wait until page element becomes action-able
	
		try {			
			
			WaitUtil.waitFor(Driver, "THREAD_SLEEP",(long)200);	
			ContinueShopping_Link.click();
			WaitUtil.waitFor(Driver, "THREAD_SLEEP",(long)200);			
			
		} catch (NoSuchElementException e) {			
			throw new TestException("Continue to Shopping Link Not found\n" + e.getMessage()) ;			
		} catch (TimeoutException e) {			
			throw new TestException("Continue to Shopping Link did not load within the given time\n" + e.getMessage()) ;
		}
		return true;
	}

	public boolean applyDiscountCode(Discount DiscountDetails ) throws InterruptedException {	
		
		try {
			AddCoupon_Link.click();		
			WaitUtil.waitFor(Driver, "THREAD_SLEEP",(long)50);		
			
			CouponCode_TextBox.sendKeys(DiscountDetails.getCouponCode());
			WaitUtil.waitFor(Driver, "THREAD_SLEEP",(long)100);		
			
			return true;
		} catch (Exception e) {
			throw e ;
		}	
		
	}

	public boolean clickBuy() throws InterruptedException {
		
		try {
			WaitUtil.waitFor(Driver, "THREAD_SLEEP",(long)3000);				
			
			BaseTest.TestLog.captureScreenShot(" Before clicking Buy now");
			
			BuyNow_button.click();		
			
			WaitUtil.waitFor(Driver, "THREAD_SLEEP",(long)6000);	
			
			if ( Driver.getTitle().contains(FrontEndOrderSuccessPage.PageTitle))
				return true;
			else {
			//Sometimes additional time is needed, so including more wait time here
				WaitUtil.waitFor(Driver, "THREAD_SLEEP",(long)6000);	
				if ( Driver.getTitle().contains(FrontEndOrderSuccessPage.PageTitle))
					return true;
				else
					return false ;
			}
			
		} catch (Exception e) {
			throw e;
		}
	}
	
}
	 
