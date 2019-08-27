package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class FrontEndOrderSuccessPage extends FrontEndCommonPage{	

	public static final String PageUrlExtn 	= "/store/checkout/thankyou";											
	public static final String PageTitle  		= "Thank You" ;	
		
	public FrontEndOrderSuccessPage(WebDriver CurrentTestDriver) {
		super(CurrentTestDriver);
		PageFactory.initElements(CurrentTestDriver, this);
	}
	
}	 
	