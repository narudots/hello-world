package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import coreapplicationclassesandinterfaces.User;
import tests.BaseTest;
import tests.CommonTestLibrary;
import utils.ErrorHandlerUtils.TestException;
import utils.WebDriverControlUtils.RetryUtil;
import utils.WebDriverControlUtils.WaitUtil;

public class FrontEndCommonPage extends Page{
	
	public static final String PageUrl = "";
	public static final String PageTitle ="";
	
	private User UserDetails ;
			
	//=====================================LOGIN FORM POPUP=========================================//

	private By LoginForm = By.xpath("//div[@id='loginFormContainer']/form[@class='form-horizontal']");
		
	//=====================================LOGIN LINK===============================================//
			
	@FindBy(how = How.CLASS_NAME, using = "login-btn")
	@CacheLookup
	
	private WebElement LoginOnTopNav_Link;
	
	private By LoginOnTopNav_LinkLocator = By.className("login-btn") ;
	
	//=====================================LOGIN BUTTON ON LOGIN FORM===============================//
	
	@FindBy(how = How.XPATH, 
			using = "//button[contains(text(),'Login')]")
	@CacheLookup	
	
	private WebElement LoginOnPopup_Button;
	
	//=====================================EMAIL====================================================//	
	
	@FindBy(how = How.ID, using = "email")
	@CacheLookup	
	
	private WebElement EmailId_TextBox;

	//=====================================PASSWORD=================================================//
	
	@FindBy(how = How.ID, using = "password")
	@CacheLookup
	
	private WebElement Password_TextBox;

	//=====================================LOGOUT===================================================//
	
	private By Logout_Button = By.className("logout-btn");
	private By LOGOUT_Link	 = By.linkText("LOGOUT");
	private By Logout_Link 	 = By.linkText("Logout");
	
	//=====================================ERROR & INFO ============================================//
	
	private By loginErrorMsg = By.xpath
	("//strong[contains(text(),'These credentials do not match our records.')]") ;
	
	//=====================================JOIN BUTTON==============================================//
	
	@FindBy(how = How.XPATH, 	
			using = "//a[@href='https://iofm-staging.divcomstaging.com/join']")
	@CacheLookup
	
	//=====================================MY Account BUTTON==============================================//
	
	private WebElement JoinOnTopNav_Button;	
	
	private By MyAccount_Button = By.xpath("//a[@class='myAccountBtn']");
	private By MyAccount_Link 	= By.xpath("//div[contains(@class,'memberMenuCol')]//div//a[contains(text(),'My account')]");
	private By MyAccount_Link2 	= By.xpath("//a[contains(text(),'My Account')]");
	//=====================================Constructors=============================================//
	
	public FrontEndCommonPage(WebDriver CurrentTestDriver){		
		super(CurrentTestDriver);		
		PageFactory.initElements(this.Driver, this);		 
	}	
	public FrontEndCommonPage(WebDriver CurrentTestDriver, User UserObj){		
		super(CurrentTestDriver);		
		PageFactory.initElements(this.Driver, this);		
		setObjectsNeededForThisScreen(UserObj);
	}
	//========================================Methods===============================================//
	
	private void setObjectsNeededForThisScreen(	User UserObj ) { 									
		this.UserDetails = UserObj ;		
	}	
	
	//************************************************************************************************
	// clickLogin() - Invoke to click the login button on top of the pages
	//************************************************************************************************	
	
	public boolean clickLogin() throws TestException, InterruptedException{
		 
		// Wait until page element becomes action-able
	
		try {
			
			WaitUtil.waitFor(Driver,"IMPLICIT", (long) 5 );
			WaitUtil.waitForWebElementToBeVisible(Driver,LoginOnTopNav_Link, (long)5);			
			RetryUtil.retryingFindClick(LoginOnTopNav_Link) ;

			WaitUtil.waitFor(Driver, "THREAD_SLEEP",(long)1000);	
			
			List<WebElement> Popup = this.Driver.findElements(By.xpath("//div[contains(@id,'evergage-tooltip')]/div/a")) ;	
			
			if ( Popup.size() > 0)
				Popup.get(0).click();
			
			//Check if the Login form/Pop-up in loaded when login is clicked. If not, raise error.				
			
			boolean LoginFormLoaded = RetryUtil.retryingFindClick(Driver, LoginForm) ;	
			
			if( LoginFormLoaded ){
				
				 // Capture the screenshot for logging	
				BaseTest.TestLog.captureScreenShot("Home Screen - |Login Form| launched");

				return true;
			}
			else throw new TestException("Login Form Not Found") ;	
			
		} catch (NoSuchElementException e) {
			
			throw new TestException("Login Link/Button Not found\n" + e.getMessage()) ;
			
		} catch (TimeoutException e) {
			
			throw new TestException("Login Button/Link did not load within the given time\n" + e.getMessage()) ;
		}
	}
	
	//************************************************************************************************
	// login - Invoke to login into the application with credentials
	//************************************************************************************************	
	
	public boolean login(String DestinationPage) throws TestException, InterruptedException{		
			 
		try {

			WaitUtil.waitFor(this.Driver, "IMPLICIT", (long) 2 );
			
			// Page Element Action - Clear the email id and password text boxes before entering the credentials
			
			EmailId_TextBox.clear();
			Password_TextBox.clear();
			
			// Page Element Action -  Enter emailId, password and click login

			EmailId_TextBox.sendKeys( this.UserDetails.getUserName());			 
			Password_TextBox.sendKeys( this.UserDetails.getUserPassword() );	

			 // Capture the screenshot for logging	
			BaseTest.TestLog.captureScreenShot("Home Screen - User Name/Password before Login click");
			
			LoginOnPopup_Button.click();
			 
			WaitUtil.waitFor(this.Driver, "IMPLICIT", (long) 2 );
			
			CommonTestLibrary.acceptCookies() ;
			
			List <WebElement> errorMessagElements = this.Driver.findElements(loginErrorMsg) ;
			
			if ( errorMessagElements.size() > 0)
			{
				if ( this.UserDetails.isInvalid())
				{
					 // Capture the screenshot for logging	
					BaseTest.TestLog.captureScreenShot("Home Screen - |Invalid Login| Error");
					
					return true ;
				}
				else
					throw new TestException("Invalid Login Credentials") ;
			}	
			else
			{
			// If the login credentials are invalid, then an error message is displayed on the top of the  
			// login page. 
			// Validate that the page re-directs on login are correct based on the member type
			
				String DestinationPageTitle = this.Driver.getTitle() ;
			 
				if ((	this.UserDetails.isUserAdmin() &
						DestinationPageTitle.contains( BackEndHomePage.PageTitle)) ||
						( 	(this.UserDetails.isUserMember() || 
							 this.UserDetails.isUserNonMember())& 									
							 DestinationPageTitle.contains( DestinationPage))){
				
					// Capture the screenshot for logging	
					BaseTest.TestLog.captureScreenShot("Home Screen - User Name/Password After Login click");
				
					return true;
				
				}
			
				else throw new TestException("Incorrect Behavior") ;
			}
		} 
		catch( NoSuchElementException Ne){
			throw new TestException("Incorrect Behavior. Expected Element Not found"+Ne.getMessage()) ;
		}
		
		catch (TestException Te){
			throw Te ;
		} 
				
	 }
	
	//************************************************************************************************
	// logout - Invoke to logout from the application using the logout link
	//************************************************************************************************
	
	public boolean logout() throws InterruptedException, TestException 
	{		
		try {				
			
			WaitUtil.waitFor(this.Driver, "IMPLICIT", (long) 10);
			
			List<WebElement> Login = this.Driver.findElements(LoginOnTopNav_LinkLocator) ;	
			
			if ( Login.size() > 0)
			{
				return true ;				
			}
			
			WaitUtil.waitFor(Driver, "THREAD_SLEEP",(long)1000);	
			
			List<WebElement> Popup = this.Driver.findElements(By.xpath("//div[contains(@id,'evergage-tooltip')]//a[contains(@title,'Close Message')]")) ;	
			
			if ( Popup.size() > 0)
				Popup.get(0).click();
			
			List<WebElement> LogoutBtn = this.Driver.findElements(Logout_Button) ;			
			
			if (LogoutBtn.size() > 0){
				
				WaitUtil.waitFor(Driver, "THREAD_SLEEP",(long)1000);	
				
				//Scroll down to get the Subscription section in view
				BaseTest.getJSExecutor().executeScript("arguments[0].scrollIntoView(false);",this.Driver.findElement(Logout_Button));
			
				WaitUtil.waitFor(this.Driver, "THREAD_SLEEP", (long)1000);
				Driver.findElement(Logout_Button).click(); 
				WaitUtil.waitFor(this.Driver, "IMPLICIT", (long) 2);
			}
			else 
			{
				List<WebElement> LogoutLink = this.Driver.findElements(Logout_Link) ;	
				if (LogoutLink.size() > 0){					
					
					//Scroll down to get the Subscription section in view
					BaseTest.getJSExecutor().executeScript("arguments[0].scrollIntoView(false);",this.Driver.findElement(Logout_Link));
					
					WaitUtil.waitFor(this.Driver, "THREAD_SLEEP", (long)1000);
					Driver.findElement(Logout_Link).click();					
					WaitUtil.waitFor(this.Driver, "THREAD_SLEEP", (long)1000);
				}
				else {
					
					List<WebElement> LOGOUT = this.Driver.findElements(LOGOUT_Link) ;	
					if (LOGOUT.size() > 0){							
					
						//Scroll down to get the Subscription section in view
						BaseTest.getJSExecutor().executeScript("arguments[0].scrollIntoView(false);",LOGOUT.get(0));
						
						WaitUtil.waitFor(this.Driver, "THREAD_SLEEP", (long)1000);
						LOGOUT.get(0).click();					
						WaitUtil.waitFor(this.Driver, "THREAD_SLEEP", (long)1000);
					}
				}
			}
			return true ;
		}
		catch( NoSuchElementException Ne){
			throw new TestException("Incorrect Behavior. Expected Element Not found"+ Ne.getMessage()) ;
		}				
	}	
	public boolean launchMyAccountPage() throws InterruptedException, TestException {		
		try {
			WaitUtil.waitFor(Driver, "THREAD_SLEEP",(long)1000);			
			BaseTest.TestLog.logTestStep("Launch My Account Page");		
			List<WebElement> MyAccountBtn = this.Driver.findElements(MyAccount_Button) ;	
			
			if (MyAccountBtn.size() > 0){
				
				WaitUtil.waitFor(Driver, "THREAD_SLEEP",(long)1000);				
				this.Driver.findElement(MyAccount_Button).click() ;
				WaitUtil.waitFor(this.Driver, "THREAD_SLEEP", (long)1000);		
				
			}
			else 
			{
				List<WebElement> MyAccountLink = this.Driver.findElements(MyAccount_Link) ;	
				if (MyAccountLink.size() > 0){					
					this.Driver.findElement(MyAccount_Link).click();					
					WaitUtil.waitFor(this.Driver, "THREAD_SLEEP", (long)1000);
				}
				else {
					
					List<WebElement> MyAccountLink2 = this.Driver.findElements(MyAccount_Link2) ;	
					if (MyAccountLink2.size() > 0){					
						MyAccountLink2.get(1).click();					
						WaitUtil.waitFor(this.Driver, "THREAD_SLEEP", (long)1000);
					}
					else {
						throw new TestException("My Account Link is not found") ;
					}
				}
			}
			
			if ( !Driver.getTitle().contains(FrontEndMyAccountPage.PageTitle)) {
				throw new TestException("My Account Page did not launch");
			}
			
			WaitUtil.waitFor(Driver, "THREAD_SLEEP",(long)1000);	
			
			List<WebElement> Popup = this.Driver.findElements(By.xpath("//div[contains(@id,'evergage-tooltip')]/div/a")) ;	
			
			if ( Popup.size() > 0)
				Popup.get(0).click();
			
			CommonTestLibrary.acceptCookies() ;
			
			return true ;
			
		} catch (TestException e) {		
			throw e;
		}
	}
}
	 
