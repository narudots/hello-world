package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import tests.BaseTest;
import tests.CommonTestLibrary;
import utils.WebDriverControlUtils.WaitUtil;

public class FrontEndShoppingCartPage extends FrontEndCommonPage{
	
	public static String PageUrlExtn = "/store/cart";
	public static String PageTitle = "Shopping Cart";
	
	//=====================================ELEMENT LOCATORS============================================//
	
	@FindBy(how = How.XPATH, using = "//input[@name='discount_code']")
	@CacheLookup
	private WebElement DiscountCode_TextBox;

	@FindBy(how = How.XPATH, using = "//button[@class='btn btn-default']")
	@CacheLookup
	private WebElement DiscountApply_Button;
	
	@FindBy(how = How.CLASS_NAME, using = "table")
	@CacheLookup
	private WebElement ProductDetails_Table;
	
	@FindBy(how = How.PARTIAL_LINK_TEXT, using = "Checkout")
	@CacheLookup
	private WebElement Checkout_button;
	
	private By TrashIcons = By.xpath("//tr[@class='cart-item']//td[@class='delete-icon hidden-xs']//a[1]") ;
	
	//======================================CONSTRUCTORS=============================================//
	
	public FrontEndShoppingCartPage(WebDriver CurrentTestDriver) {
		super(CurrentTestDriver);
		PageFactory.initElements(CurrentTestDriver, this);		
	}
	
	public boolean checkout() throws InterruptedException {
		
		scrollDown(400);
		WaitUtil.waitFor(Driver, "THREAD_SLEEP",(long) 1000);
		
		CommonTestLibrary.acceptCookies();
		
		BaseTest.TestLog.logTestStep("Checkout the Product in Shopping Cart");
		Checkout_button.click();
		WaitUtil.waitFor(Driver, "THREAD_SLEEP",(long) 2000);
			
		CommonTestLibrary.acceptCookies();
		
		if ( Driver.getTitle().contains(FrontEndNewUserProfilePage.PageTitle))
				return true;
		else 
			return false;		
	}
	public boolean deleteAllInCart() throws InterruptedException {
		
		BaseTest.TestLog.logTestStep("Clear all Products in Shopping Cart");
		
		List < WebElement> TrashIconsElements = Driver.findElements(TrashIcons) ;
		
		for (int i = 0; i<TrashIconsElements.size(); i++)
		{
			
			WaitUtil.waitFor(Driver, "THREAD_SLEEP",(long) 2000);			
			TrashIconsElements.get(0).click();			
			WaitUtil.waitFor(Driver, "THREAD_SLEEP",(long) 3000);
		}	
		CommonTestLibrary.acceptCookies();		
		return true;		
	}
	
}
