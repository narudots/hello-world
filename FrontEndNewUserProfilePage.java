package pages;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import coreapplicationclassesandinterfaces.User;
import tests.BaseTest;
import tests.CommonTestLibrary;
import utils.ErrorHandlerUtils.TestException;
import utils.WebDriverControlUtils.WaitUtil;

public class FrontEndNewUserProfilePage extends FrontEndSPCPage{
	
	private User	UserDetails ;
	
	@FindBy(how = How.ID, using = "first_name")
	@CacheLookup
	private WebElement FirstName_TextBox;

	@FindBy(how = How.ID, using = "last_name")
	@CacheLookup
	private WebElement LastName_TextBox;
	
	@FindBy(how = How.ID, using = "email")
	@CacheLookup
	private WebElement Email_TextBox;		
	
	@FindBy(how = How.ID, using = "phone")
	@CacheLookup
	private WebElement Phone_TextBox;		

	@FindBy(how = How.ID, using = "password")
	@CacheLookup
	private WebElement Password_TextBox;		
	
	@FindBy(how = How.ID, using = "password_confirmation")
	@CacheLookup
	private WebElement ConfirmPassword_TextBox;	

	@FindBy(how = How.ID, using = "company")
	@CacheLookup
	private WebElement Company_TextBox;		
	
	@FindBy(how = How.ID, using = "job_title")
	@CacheLookup
	private WebElement JobTitle_TextBox;		
	
	@FindBy(how = How.XPATH, using = "//select[@name='industry']")
	@CacheLookup
	private WebElement Industry_Dropdown;		
	
	@FindBy(how = How.ID, using = "customerSubmit")
	@CacheLookup
	private WebElement Continue_Button;			
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'sign in')]")
	@CacheLookup
	private WebElement SignIn_Link;		

	@FindBy(how = How.XPATH, using = "//section[@id='checkoutStepOne']//div[@id='accountInfo']//button[contains(text(),'Edit')]")
	private WebElement UserInfoEdit_Button;			
	
	//User Info Edit - Form Elements
	
	@FindBy(how = How.XPATH, using = "/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/input[1]")
	private WebElement UserInfoEditForm_FirstName_TextBox;	
	
	@FindBy(how = How.XPATH, using = "/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[2]/div[1]/input[1]")
	private WebElement UserInfoEditForm_LastName_TextBox;	
	
	@FindBy(how = How.XPATH, using = "/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[3]/div[1]/input[1]")
	private WebElement UserInfoEditForm_Email_TextBox;	
	
	@FindBy(how = How.XPATH, using = "/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[5]/div[1]/input[1]")
	private WebElement UserInfoEditForm_CompanyName_TextBox;	

	@FindBy(how = How.XPATH, using = "/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[2]/button[1]")
	private WebElement UserInfoEditForm_Save_Button;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Close')]")
	private WebElement UserInfoEditForm_Close_Button;
	
	@FindBy(how = How.XPATH, using = "//div[@id='accountInfo']/div[2]")
	private WebElement UserInfoSection_Name_Field;
	
	//=====================================ERROR MESSAGE============================================//
	
	private By FirstNameRequired_Error =
		By.xpath("//div[@class='form-group required has-error has-danger']/label[@for='first_name']") ;
	
	private By LastNameRequired_Error = 
		By.xpath("//div[@class='form-group required has-error has-danger']/label[@for='last_name']") ;
	
	private By InvalidEmail_Error = 
		By.xpath("//div[@class='form-group required has-error has-danger']/label[@for='email']") ;
	
	private By PasswordField_Error =
			By.xpath("//div[@class='form-group required has-error has-danger']/label[@for='password']") ;
		
	private By ConfirmPasswordField_Error = 
			By.xpath("//li[contains(text(),'Password does not match.')]") ;
		
	private By IndustryRequired_Error = 
			By.xpath("//li[contains(text(),'Please select an item in the list.')]") ;
	
	private By CompanyRequired_Error = 
			By.xpath("//div[@class='form-group required has-error has-danger']/label[@for='company']") ;
	
	private By EmailAlreadyTakenError = By.xpath("//*[@id='create-account']/div[4]/div/span");
	
	private By WeakPasswordErrorShow = By.xpath("//ul[@class='list-unstyled form-error']");
	
	private By WeakPasswordErrorNoShow = By.xpath("//ul[@class='list-unstyled form-error ng-hide']") ;
	
	//=====================================Constructors===========================================//
	
	public FrontEndNewUserProfilePage(WebDriver CurrentTestDriver){		 
		super(CurrentTestDriver);
		PageFactory.initElements(CurrentTestDriver, this);		 
	}
	
	public FrontEndNewUserProfilePage(WebDriver CurrentTestDriver, User UserObj) {
		super(CurrentTestDriver);
		PageFactory.initElements(CurrentTestDriver, this);		
		setObjectsNeededForThisScreen(UserObj);			
	}
	
	//================================Private Methods=============================================//
	
	private void setObjectsNeededForThisScreen(	User UserObj){
		this.UserDetails = UserObj ;
	}
	private void clearAllFields() throws InterruptedException {
		
		WaitUtil.waitFor(Driver, "IMPLICIT", (long) 10 );
		WaitUtil.waitForWebElementToBeVisible(Driver,FirstName_TextBox, (long)10);

		FirstName_TextBox.clear();
		LastName_TextBox.clear();
		Email_TextBox.clear();
		Password_TextBox.clear();
		ConfirmPassword_TextBox.clear();
		Company_TextBox.clear();
		JobTitle_TextBox.clear();		
		
		//reset Industry
		WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long)20);
		Select IndustryName_dropdown = 	new Select(Industry_Dropdown) ;		
		IndustryName_dropdown.selectByVisibleText("-- Select an Industry --");		
	}
	private void enterFields() throws InterruptedException {
		
		// UserField - First Name
		WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 100 );	
		FirstName_TextBox.sendKeys(this.UserDetails.getUserFirstName());			

		// UserField - Last Name
		LastName_TextBox.sendKeys(this.UserDetails.getUserLastName());
					
		// UserField - Email
		Email_TextBox.sendKeys(this.UserDetails.getUserEmailId());	
		
		//Phone for ASAP
		
		WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 20 );	
		
		if ( this.UserDetails.getApplicationID().toLowerCase().equals("asap"))
			Phone_TextBox.sendKeys(this.UserDetails.getPhoneNumber());			 
		
		// UserField - Password
		WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 20 );				
		Password_TextBox.clear();
		Password_TextBox.sendKeys(this.UserDetails.getUserPassword());
					
		// UserField - Confirm Password
		WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 20 );	
		ConfirmPassword_TextBox.clear();
		ConfirmPassword_TextBox.sendKeys(this.UserDetails.getUserPassword());
		
		// UserField - Company
		Company_TextBox.sendKeys(this.UserDetails.getUserCompany());
		
		// UserField - Job Title
		JobTitle_TextBox.sendKeys(this.UserDetails.getUserJobTitle());

        // Capture the screenshot for logging			
		BaseTest.TestLog.captureScreenShot("User Profile Section - Before clicking |Continue|");
		
		//Since the page is long. To avoid elements being hidden when clicked, use the below scrolling
		
		scrollDown(500);

		WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long)100);
		Select IndustryName_dropdown = 	new Select(Industry_Dropdown) ;		
		IndustryName_dropdown.selectByVisibleText(this.UserDetails.getUserIndustry());		
		
		WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long)20);
		
        // Capture the screenshot for logging			
		BaseTest.TestLog.captureScreenShot("User Profile Section Contd- Before clicking |Continue|");

	}	
	private void submitEnteredPageInformation() throws InterruptedException
	{
		// Capture the screenshot for logging			
		BaseTest.TestLog.captureScreenShot("User Profile Section - Before clicking |Continue|");

		scrollDown(500);
		CommonTestLibrary.acceptCookies() ;
		Continue_Button.click();		

		WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long)100);		
	}	

	//====================================Public Methods========================================//

	public boolean createNewCustomer() throws InterruptedException, TestException{		
			
		try {
			
			clearAllFields() ;			
			enterFields();			
			submitEnteredPageInformation();
			
			if (Driver.findElements(EmailAlreadyTakenError).size() != 0) 
			{
				//Capture the screenshot for logging		
				BaseTest.TestLog.captureScreenShot("User Profile Section - |Email Already Taken| error");
				throw new TestException("Email is already taken") ;
			}	
			
			CommonTestLibrary.acceptCookies() ;		
					
		} 
		catch (NoSuchElementException e) {
			throw new TestException("Element not found\n" + e.getMessage());			
		} 
		catch (TimeoutException e) {
			throw new TestException("Elements on page did not load within the given time\n" + e.getMessage()) ;		
		}
		catch ( Exception e){ throw e ; }
		return true;
	}
	public boolean checkRequiredFieldErrors() throws InterruptedException, TestException{
		
		try {
			
			clearAllFields();
			
			//Capture the screenshot for logging		
			BaseTest.TestLog.captureScreenShot("User Profile Section - Fields left blank");
			
			scrollDown(500);			
			submitEnteredPageInformation();
				
			if 	((Driver.findElements(FirstNameRequired_Error).size() == 0) ||
				(Driver.findElements(LastNameRequired_Error).size() == 0) 	||
				(Driver.findElements(InvalidEmail_Error).size() == 0) 		||
				(this.UserDetails.getApplicationID().equals("IOFM") && (Driver.findElements(CompanyRequired_Error).size() == 0)))
			{				
				return false ;
			}
			else
			{
				//Capture the screenshot for logging		
				BaseTest.TestLog.captureScreenShot("User Profile Section - Required Errors Thrown");
			
				FirstName_TextBox.sendKeys(this.UserDetails.getUserFirstName()) ;
				WaitUtil.waitFor(Driver, "IMPLICIT", (long) 2 );
				
				LastName_TextBox.sendKeys(this.UserDetails.getUserLastName());				
				
				Email_TextBox.sendKeys(this.UserDetails.getUserEmailId());			
				
				Company_TextBox.sendKeys(this.UserDetails.getUserCompany());
							
				if ((Driver.findElements(PasswordField_Error).size() == 0) 	||
				(Driver.findElements(ConfirmPasswordField_Error).size() == 0) ||
				(this.UserDetails.getApplicationID().equals("IOFM") && (Driver.findElements(IndustryRequired_Error).size() == 0)))				
					return false ;				
				else
				{
					//Capture the screenshot for logging
					BaseTest.TestLog.captureScreenShot("");
					return true ;
				}
					
			}
			
		} catch (NoSuchElementException e) {
			throw new TestException(" Element is not found"+ e.getMessage()) ;
		} catch (TimeoutException e) {
			throw new TestException(" Element was not loaded within the time limit" + e.getMessage()) ;
		}
		catch ( Exception e){ throw e ;	}
	}	
	public boolean checkForWeakPasswordError() throws InterruptedException, TestException{
		
		clearAllFields();		
		enterFields() ;		
		scrollUp();		
		
		try
		{
			
			if (this.UserDetails.getTypeOfTestData().equals("WeakPassword")){
				
				if (Driver.findElements(WeakPasswordErrorShow).size() != 0) 
				{
					//Capture the screenshot for logging
					BaseTest.TestLog.captureScreenShot(" User Profile Section - <Weak Password> Error");
					return true ;
				}
				else return false ;
			}
			else {
				
				if (Driver.findElements(WeakPasswordErrorNoShow).size() != 0) 
				{
					//Capture the screenshot for logging
					BaseTest.TestLog.captureScreenShot(" User Profile Section - No error for Strong Password");
					return true ;
				}
				else return false ;
			}
		}
		catch ( NoSuchElementException e){			
			throw new TestException("Element not found\n" + e.getMessage());	
		}
		catch ( Exception e){ throw e ; }
		
	}

	public boolean editUser() throws Exception {
	try {
		
		User EditTestUser = new User();
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyyhmmss");
		String formattedDate = sdf.format(date);
		
		EditTestUser.setFirstName("EditTestFirstName"+formattedDate) ;
		EditTestUser.setLastName("EditTestLastName"+formattedDate) ;
		EditTestUser.setEmailID("EditTestEmailName"+formattedDate+"@test.com") ;
		EditTestUser.setCompanyName("EditTestCompanyName");
		
		if (signInAsExistingUser())
		{
			if (editUserDetails(this.UserDetails,EditTestUser))
			{
				//Reset Values
				if (editUserDetails(EditTestUser,this.UserDetails))
					return true ;
				else 
					throw new TestException("Reset of Values failed") ;
			}
			else 
				throw new TestException("Edit User failed") ;
		}
		else 
		{
			throw new TestException("Sign in as existing user failed") ;
		}
	} 
	catch (Exception e) {
		throw e;
	}					
	}	
	public boolean signInAsExistingUser() throws Exception {
		
		try {
			
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long)100);	
			BaseTest.TestLog.captureScreenShot(" Existing User Login - Before Clicking Sign-In");
			
			SignIn_Link.click();
			
			BaseTest.TestLog.captureScreenShot(" Existing User Login - After Clicking Sign-In");			
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long)500);	
			
			if(new FrontEndCommonPage(Driver,this.UserDetails).login(FrontEndSPCPage.PageTitle))
			{
				return true ;	
			}
			
			else return false ;
		} 
		catch (Exception e) {
			throw e;
		}	
	}
	private boolean editUserDetails(User From, User To) throws InterruptedException {
		
		try {
			BaseTest.TestLog.captureScreenShot(" Before Clicking Edit Button");			
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long)500);	

			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long)1000);	
			
			UserInfoEdit_Button.click();
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long)100);	
			
			UserInfoEditForm_FirstName_TextBox.clear();
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long)200);

			UserInfoEditForm_LastName_TextBox.clear();
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long)200);
			
			UserInfoEditForm_Email_TextBox.clear();
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long)200);

			UserInfoEditForm_CompanyName_TextBox.clear();
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long)200);

			BaseTest.TestLog.captureScreenShot(" Edit User Pop-up - Before Editing User Details");		
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long)200);	
			
			UserInfoEditForm_FirstName_TextBox.sendKeys(To.getUserFirstName()) ;
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long)200);	
			
			UserInfoEditForm_LastName_TextBox.sendKeys(To.getUserLastName()) ;
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long)200);	
			
			UserInfoEditForm_Email_TextBox.sendKeys(To.getUserEmailId()) ;
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long)200);	
			
			UserInfoEditForm_CompanyName_TextBox.sendKeys(To.getUserCompany()) ;
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long)200);	
			
			BaseTest.TestLog.captureScreenShot(" Edit User Pop-up - Before Saving Details on User Edit Form");		
					

			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long)1000);
			UserInfoEditForm_Save_Button.click();			
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long)10000);
			
			return true ;
			
		} catch (Exception e) {
			throw e ;
		}
	}

}
