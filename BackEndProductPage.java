package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import coreapplicationclassesandinterfaces.Bundle;
import coreapplicationclassesandinterfaces.ELearningProduct;
import coreapplicationclassesandinterfaces.GroupAccess;
import coreapplicationclassesandinterfaces.Product;
import coreapplicationclassesandinterfaces.Subscription;
import coreapplicationclassesandinterfaces.Webex;
import tests.BaseTest;
import utils.WebDriverControlUtils.WaitUtil;

public class BackEndProductPage extends Page{

	private WebDriver 	Driver ;
	private Product 	ProductObj ;
	static 	String 		PageUrlExtn = "";
	static	String 		PageTitle =	"";
	
	public static String getPageTitle(){		
		return PageTitle;
	}
		
	@FindBy(how = How.XPATH, using = "//input[@placeholder='Enter a product name']")
	private WebElement ProductName_Textbox;	
	
	@FindBy(how = How.XPATH, using = "//select[contains(@ng-model,'editCtl.data.product_type')]")
	private WebElement ProductType_Select;
		
	@FindBy(how = How.XPATH, using = "//iframe[@id='ui-tinymce-47_ifr']")
	private WebElement FullDesc_Html;
	
	@FindBy(how = How.XPATH, using = "//iframe[@id='ui-tinymce-48_ifr']")
	private WebElement EmailDesc_Html;
	
	@FindBy(how = How.XPATH, using = "//input[contains(@ng-model,\'editCtl.data.marketing_url\')]")
	private WebElement MarketingCode_Textbox;
	
	@FindBy(how = How.XPATH, using = "//input[contains(@ng-model,\'editCtl.data.accounting_code\')]")
	private WebElement AccountingCode_Textbox;
	
	@FindBy(how = How.XPATH, using = "//select[contains(@ng-model,\'editCtl.data.tax_code')]")
	private WebElement TaxCode_Select;
	
	@FindBy(how = How.XPATH, using = "//input[@placeholder='Product SKU']")
	private WebElement SKU_TextBox;
	
	@FindBy(how = How.XPATH, using = "//input[@placeholder='Product Price']")
	private WebElement ProductPrice_TextBox;
	
	@FindBy(how = How.XPATH, using = "//input[contains(@ng-model,'editCtl.data.position')]")
	private WebElement Position_Textbox;
	
	@FindBy(how = How.XPATH, using = "//input[contains(@ng-model,'editCtl.data.can_add_to_cart')]")
	private WebElement AddToCart_Checkbox;
	
	@FindBy(how = How.XPATH, using = "//input[contains(@ng-model,'editCtl.data.featured')]")
	private WebElement Featured_Checkbox;

	@FindBy(how = How.XPATH, using = "//input[contains(@ng-model,'editCtl.data.display')]")
	private WebElement Display_Checkbox;	
	
	@FindBy(how = How.XPATH, using = "//div[contains(@class,'panel-footer')]//button[3]")
	private WebElement SaveAndClose_Button;		
	
	//Webex Page	
		
	@FindBy(how = How.XPATH, using = "//input[contains(@ng-model,\'editCtl.data.webex_username\')]")
	private WebElement Webex_Username;
	
	@FindBy(how = How.XPATH, using = "//input[contains(@ng-model,\'editCtl.data.webex_password\')]")
	private WebElement Webex_Password;
	
	@FindBy(how = How.XPATH, using = "//input[contains(@ng-model,\'editCtl.data.webex_site_id\')]")
	private WebElement Webex_SiteId;
	
	@FindBy(how = How.XPATH, using = "//input[contains(@ng-model,\'editCtl.data.webex_site_url\')]")
	private WebElement Webex_SiteUrl;
	
	@FindBy(how = How.XPATH, using = "//input[contains(@ng-model,\'editCtl.data.webex_id\')]")
	private WebElement Webex_ID;
	
	//E-Learning Page	
	
	@FindBy(how = How.XPATH, using = "//input[contains(@placeholder,'Search for Courses')]")
	private WebElement ELearning_CourseName_TextBox;
	
	@FindBy(how = How.XPATH, using = "//input[contains(@ng-model,\'editCtl.data.course_seats')]")
	private WebElement ELearning_SeatsCount_Textbox;
	
	@FindBy(how = How.XPATH, using = "//input[contains(@ng-model,'editCtl.data.course_seats')]")
	private WebElement ELearning_DefaultPackageSeatCount_Textbox;
	
	@FindBy(how = How.XPATH, using = "//input[contains(@ng-model,'editCtl.data.price')]")
	private WebElement ELearning_DefaultPackagePrice_Textbox;
	
	//Subscription Page
	
	@FindBy(how = How.XPATH, using = "//select[contains(@ng-model,'editCtl.data.subscription_type')]")
	private WebElement Subscription_Type_Select;

	@FindBy(how = How.XPATH, using = "//select[contains(@ng-model,'editCtl.data.subscription_period')]")
	private WebElement Subscription_Period_Select;
	
	@FindBy(how = How.XPATH, using = "//input[contains(@ng-model,'editCtl.data.subscription_length')]")
	private WebElement Subscription_Length_Textbox;
	
	@FindBy(how = How.XPATH, using = "//input[contains(@ng-model,'editCtl.data.subscription_seats')]")
	private WebElement Subscription_Seats_Textbox;
	
	@FindBy(how = How.XPATH, using = "//input[contains(@ng-model,'editCtl.data.subscription_grandfathered')]")
	private WebElement Subscription_Grandfathered_Option;
	
	@FindBy(how = How.XPATH, using = "//input[contains(@ng-model,'editCtl.data.subscription_autorenew')]")
	private WebElement Subscription_AutoRenew_Option;
	
	@FindBy(how = How.XPATH, using = "//input[contains(@ng-model,'editCtl.data.subscription_trial_require_payment')]")
	private WebElement Subscription_RequireCreditCard_Option;
	
	//Bundle
	
	@FindBy(how = How.XPATH, using = "//input[@placeholder='Search to Add Products']")
	private WebElement Bundle_SearchProduct_Textbox;
	
	//Group Access
	
	@FindBy(how = How.XPATH, using = "//div[@class='tab-pane ng-scope active']//input[@placeholder='Search to Add Groups']")
	private WebElement GroupAccess_SearchCustomerGroup_Textbox;
	
	//Common
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Add Seat Package')]")
	private WebElement AddSeatPackage_Button;
	
	private By ProductMenuItem 					= By.xpath("//ul[contains(@class,'nav nav-tabs')]/li[4]") ;	
	
	String SeatPackageParentPath 				= "//div[contains(@class,'tab-content')]//div[contains(@class,'row ng-scope')]/div[1]" ;	
	String SeatPackageNamePath 					= "//input[contains(@placeholder,'Name')]" ;	
	String SeatPackageCountPath 				= "//input[contains(@placeholder,'Seats')]" ;	
	String SeatPackagePricePath 				= "//input[contains(@placeholder,'Price')]" ;	
	
	//Miscellaneous
	
	private By Desc_xpath 								= By.xpath("/html[1]/body[1]" );
	private By ELearning_CourseName_DropDown 			= By.xpath("//lv-course-search[@class='ng-scope ng-isolate-scope']//li[1]") ;
	private By Bundle_ProductName_DropDown 				= By.xpath("//lv-product-search[@placeholder='Search to Add Products']//li[1]") ;
	private By GroupAccess_CustomerGroupName_DropDown	= By.xpath("//lv-customer-group-search[@placeholder='Search to Add Groups']//li[1]") ;
	private By AlertMessage 							= By.xpath("//div[@class='alert ng-scope alert-danger']") ;		
	
	//==============================================================================================//
	
	public BackEndProductPage(WebDriver CurrentTestDriver){		 
		super(CurrentTestDriver);
		this.Driver = CurrentTestDriver ;
		PageFactory.initElements(this.Driver, this);
	}
	
	public BackEndProductPage(WebDriver CurrentTestDriver, Product productToBeAdded) {
		super(CurrentTestDriver);
		this.Driver = CurrentTestDriver ;
		PageFactory.initElements(this.Driver, this);
		setObjectsForThisPage(productToBeAdded);
	}

	private void setObjectsForThisPage(Product productToBeAdded) {
		this.ProductObj = productToBeAdded ;		
	}	
	
	public boolean enterWebexDetails(Webex WebexToBeAdded) {		
		try {			
			// Capture the screenshot for logging			
			BaseTest.TestLog.captureScreenShot("Webex Add Form - Before Entering Details ");
			
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 100 );	
			Driver.findElement(ProductMenuItem).click();
			
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 1000 );	
			Webex_Username.sendKeys(WebexToBeAdded.getUserName());			
			
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 100 );	
			Webex_Password.sendKeys(WebexToBeAdded.getPassword());			
			
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 100 );	
			Webex_SiteId.sendKeys(WebexToBeAdded.getSiteId());			
			
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 100 );	
			Webex_SiteUrl.sendKeys(WebexToBeAdded.getSiteUrl());			
			
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 100 );	
			Webex_ID.sendKeys(WebexToBeAdded.getWebexId());			
			
			//Capture the screenshot for logging			
			BaseTest.TestLog.captureScreenShot("Webex Add Form - After Entering Details ");		
			
			return true ;			
		} catch (InterruptedException e) {
			return false ;
		}		
	}
	
	public boolean enterGroupAccessDetails(GroupAccess GroupAccessToBeAdded) {		
		try {
			
			// Capture the screenshot for logging			
			BaseTest.TestLog.captureScreenShot("Group Access Add Form - Before Entering Details ");
			
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 100 );	
			Driver.findElement(ProductMenuItem).click();
			
			String 	CustomerGroupName			= 	GroupAccessToBeAdded.getCustomerGroupName().replaceAll("\\s","") ;
			String 	CustomerGroupNameSubstring ;
			int 	letterIndex 				= 	0;
			int 	CustomerGroupNameLength 	= 	CustomerGroupName.length() ;		
	
			while(letterIndex+3 < CustomerGroupNameLength)
			{
				CustomerGroupNameSubstring 		= 	CustomerGroupName.substring(letterIndex,letterIndex+3) ;
				letterIndex						=	letterIndex+3 ;
						
				WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 5000 );	
				GroupAccess_SearchCustomerGroup_Textbox.sendKeys(CustomerGroupNameSubstring);
				WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 5000 );	
			
				List <WebElement> autoProductSuggestions = Driver.findElements(GroupAccess_CustomerGroupName_DropDown) ;
			
				if (autoProductSuggestions.size() > 0)
				{
					autoProductSuggestions.get(0).click();
					break ;
				}
					
			}
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 3000 );	
			
			//Capture the screenshot for logging			
			BaseTest.TestLog.captureScreenShot("Group Access Add Form - After Entering Details ");		
			
			if (Driver.findElements(AlertMessage).size() > 0) return false ;
			else return true ;
			
		}
		catch (Exception e) {
			System.out.println("Exception Occured in Backendproductpage.java : enterGroupAccessDetails");
			e.printStackTrace();
			return false ;
		}
	}
	public boolean enterBundleDetails(Bundle BundleToBeAdded) {		
		try {
			
			// Capture the screenshot for logging			
			BaseTest.TestLog.captureScreenShot("Bundle Add Form - Before Entering Details ");
			
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 100 );	
			Driver.findElement(ProductMenuItem).click();
			
			String 	Product1Name		= 	BundleToBeAdded.getProduct1Name().replaceAll("\\s","") ;
			String 	Product1NameSubstring ;
			int 	letterIndex 		= 	0;
			int 	Product1NameLength 	= 	Product1Name.length() ;		

			while(letterIndex+3 < Product1NameLength)
			{
				Product1NameSubstring = 	Product1Name.substring(letterIndex,letterIndex+3) ;
				letterIndex			=	letterIndex+3 ;
						
				WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 5000 );	
				Bundle_SearchProduct_Textbox.sendKeys(Product1NameSubstring);
				WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 5000 );	
			
				List <WebElement> autoProductSuggestions = Driver.findElements(Bundle_ProductName_DropDown) ;
			
				if (autoProductSuggestions.size() > 0)
				{
					
					autoProductSuggestions.get(0).click();
					break ;
				}
					
			}
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 3000 );	
			
			String 	Product2Name		= 	BundleToBeAdded.getProduct2Name().replaceAll("\\s","") ;
			String 	Product2NameSubstring ;
			letterIndex 		= 	0;
			int 	Product2NameLength 	= 	Product1Name.length() ;		

			while(letterIndex+3 < Product2NameLength)
			{
				Product2NameSubstring = 	Product2Name.substring(letterIndex,letterIndex+3) ;
				letterIndex			=	letterIndex+3 ;
						
				WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 5000 );	
				Bundle_SearchProduct_Textbox.sendKeys(Product2NameSubstring);
				WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 5000 );	
			
				List <WebElement> autoProductSuggestions = Driver.findElements(Bundle_ProductName_DropDown) ;
			
				if (autoProductSuggestions.size() > 0)
				{
					
					autoProductSuggestions.get(0).click();
					break ;
				}
					
			}
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 1000 );	
			
			//Capture the screenshot for logging			
			BaseTest.TestLog.captureScreenShot("Bundle Add Form - After Entering Details ");		
			
			if (Driver.findElements(AlertMessage).size() > 0) return false ;
			else return true ;
			
		}
		catch (Exception e) {
			System.out.println("Exception Occured in Backendproductpage.java : enterBundleDetails");
			e.printStackTrace();
			return false ;
		}
	}
			
	public boolean enterELearningDetails(ELearningProduct CourseELearningProductToBeAdded) {		
		try {
			
			// Capture the screenshot for logging			
			BaseTest.TestLog.captureScreenShot("E-Learning Add Form - Before Entering Details ");
			
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 100 );	
			Driver.findElement(ProductMenuItem).click();
			
			String 	CourseName			= 	CourseELearningProductToBeAdded.getCourseName().replaceAll("\\s","") ;
			String 	CourseNameSubstring ;
			int 	letterIndex 		= 	0;
			int 	courseNameLength 	= 	CourseName.length() ;		

			while(letterIndex+3 < courseNameLength)
			{
				CourseNameSubstring = 	CourseName.substring(letterIndex,letterIndex+3) ;
				letterIndex			=	letterIndex+3 ;
						
				WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 5000 );	
				ELearning_CourseName_TextBox.sendKeys(CourseNameSubstring);
				WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 5000 );	
			
				List <WebElement> autoCourseSuggestions = Driver.findElements(ELearning_CourseName_DropDown) ;
			
				if (autoCourseSuggestions.size() > 0)
				{
					autoCourseSuggestions.get(0).click();
					break ;
				}
					
			}
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 1000 );	
			ELearning_SeatsCount_Textbox.clear();
			
			if (!CourseELearningProductToBeAdded.getSeatCount().toLowerCase().equals("notused"))
			{
				
				WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 100 );	
				ELearning_SeatsCount_Textbox.sendKeys(CourseELearningProductToBeAdded.getSeatCount());			
			}
			else {
				
				WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 1000 );	
				AddSeatPackage_Button.click();			
				
				WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 100 );
				ELearning_DefaultPackageSeatCount_Textbox.clear();
				ELearning_DefaultPackageSeatCount_Textbox.sendKeys(CourseELearningProductToBeAdded.getDefaultPackageSeatCount()) ;
				
				WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 100 );
				ELearning_DefaultPackagePrice_Textbox.clear();
				ELearning_DefaultPackagePrice_Textbox.sendKeys(CourseELearningProductToBeAdded.getDefaultPackagePrice()) ;
				
				// For now 3 seat packages only can be added. This can be increased if needed. 
				for ( Integer i= 1;i<=3;i++)
				{
					WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 100 );
					Driver.findElement(By.xpath(SeatPackageParentPath+"/div["+i+"]"+SeatPackageNamePath)).sendKeys(CourseELearningProductToBeAdded.getSeatPackageInfo("Name", i.toString()));
					
					WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 100 );
					Driver.findElement(By.xpath(SeatPackageParentPath+"/div["+i+"]"+SeatPackageCountPath)).sendKeys(CourseELearningProductToBeAdded.getSeatPackageInfo("Count", i.toString()));
					
					WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 100 );
					Driver.findElement(By.xpath(SeatPackageParentPath+"/div["+i+"]"+SeatPackagePricePath)).sendKeys(CourseELearningProductToBeAdded.getSeatPackageInfo("Price", i.toString()));
					
					if ( i < 3 )
					{
						WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 100 );	
						AddSeatPackage_Button.click();	
					}	
					
				}
			} 						
			
			//Capture the screenshot for logging			
			BaseTest.TestLog.captureScreenShot("E-Learning Add Form - After Entering Details ");		
			
			if (Driver.findElements(AlertMessage).size() > 0) return false ;
			else return true ;
			
		} catch (Exception e) {
			System.out.println("Exception Occured in Backendproductpage.java : enterELearningDetails");
			System.out.println(e.getMessage());
			return false ;
		}
	}
	
	public boolean enterSubscriptionDetails(Subscription SubscriptionToBeAdded) {
		
		try {			
			// Capture the screenshot for logging			
			BaseTest.TestLog.captureScreenShot("Subscription Add Form - Before Entering Details ");
			
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 100 );	
			Driver.findElement(ProductMenuItem).click();
			
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long)100);		
			Select SubscriptionType_dropdown = 	new Select(Subscription_Type_Select) ;		
			SubscriptionType_dropdown.selectByVisibleText(SubscriptionToBeAdded.getType());
			
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long)100);		
			Select SubscriptionPeriod_dropdown = 	new Select(Subscription_Period_Select) ;		
			SubscriptionPeriod_dropdown.selectByVisibleText(SubscriptionToBeAdded.getPeriod());
			
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 1000 );	
			Subscription_Length_Textbox.sendKeys(SubscriptionToBeAdded.getLength());				

			if (!SubscriptionToBeAdded.getSeatCount().toLowerCase().equals("notused"))
			{
				WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 1000 );	
			}
			else 
			{				
				WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 100 );	
				AddSeatPackage_Button.click();			
				
				// For now 3 seat packages only can be added. This can be increased if needed. 
				for ( Integer i= 1;i<=3;i++)
				{
					WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 100 );
					Driver.findElement(By.xpath(SeatPackageParentPath+"/div["+i+"]"+SeatPackageNamePath)).sendKeys(SubscriptionToBeAdded.getSeatPackageInfo("Name", i.toString()));
					
					WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 100 );
					Driver.findElement(By.xpath(SeatPackageParentPath+"/div["+i+"]"+SeatPackageCountPath)).sendKeys(SubscriptionToBeAdded.getSeatPackageInfo("Count", i.toString()));
					
					WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 100 );
					Driver.findElement(By.xpath(SeatPackageParentPath+"/div["+i+"]"+SeatPackagePricePath)).sendKeys(SubscriptionToBeAdded.getSeatPackageInfo("Price", i.toString()));
					
					if ( i < 3 )
					{
						WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 100 );	
						AddSeatPackage_Button.click();	
					}						
				}				
			}

			if (SubscriptionToBeAdded.getGrandfatheredOption().toLowerCase().equals("yes"))
			{
				WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 1000 );	
				Subscription_Grandfathered_Option.click();	
			}
			if (SubscriptionToBeAdded.getAutoRenewOption().toLowerCase().equals("yes"))
			{
				WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 1000 );	
				Subscription_AutoRenew_Option.click();	
			}
			if (SubscriptionToBeAdded.getCreditCartRequiredOption().toLowerCase().equals("yes"))
			{
				WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 1000 );	
				Subscription_RequireCreditCard_Option.click();	
			}
			BaseTest.TestLog.captureScreenShot("Subscription Add Form - After Entering Details ");		
			
			return true ;
			
		} catch (InterruptedException e) {
			return false ;
		}	
		catch (Exception e) {
			throw e ;
		}
	}
	
	
	
	public boolean clickSaveAndClose() throws InterruptedException {
		
		BaseTest.TestLog.logTestStep("Clicking Save and Close");
		SaveAndClose_Button.click() ;
		WaitUtil.waitFor(Driver, "THREAD_SLEEP",(long)10000);
		return true ;
		
	}
	public boolean enterCommonProductFields() throws Exception {
	
		// Capture the screenshot for logging			
		BaseTest.TestLog.captureScreenShot("Product Form - Before Entering Details ");
		
		try {
			
			// Product Type
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long)1000);		
			Select ProductType_dropdown = 	new Select(ProductType_Select) ;		
			ProductType_dropdown.selectByVisibleText(this.ProductObj.getProductType());		
			
			// Product Name
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 1000 );	
			ProductName_Textbox.sendKeys(this.ProductObj.getProductName());			
	
			// Short Description
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 2000 );	
			Driver.switchTo().frame(0);
			Driver.findElement(Desc_xpath).sendKeys(this.ProductObj.getProductShortDesc());
			
			Driver.switchTo().defaultContent() ;
			
			// Full Description
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 1000);	
			Driver.switchTo().frame(1);
			Driver.findElement(Desc_xpath).sendKeys(this.ProductObj.getProductShortDesc());
			
			// Capture the screenshot for logging			
			BaseTest.TestLog.captureScreenShot("Product Form - Top Section After Entering Data ");
			
			scrollDown(500);
			Driver.switchTo().defaultContent() ;
			
			// Email Instructions
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 1000 );				
			Driver.switchTo().frame(2);
			Driver.findElement(Desc_xpath).sendKeys(this.ProductObj.getProductEmailInstructions());
			
			Driver.switchTo().defaultContent() ;
			
			// MarketingUrl
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 1000 );	
			MarketingCode_Textbox.sendKeys(this.ProductObj.getProductMarketingUrl());			
			
			// Accounting Code
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 1000 );	
			AccountingCode_Textbox.sendKeys(this.ProductObj.getProductAccoutingCode());		
			
			// Tax Code
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long)1000);
			Select TaxCode_dropdown = 	new Select(TaxCode_Select) ;		
			TaxCode_dropdown.selectByVisibleText(this.ProductObj.getProductTaxCode());	
			
			// Capture the screenshot for logging			
			BaseTest.TestLog.captureScreenShot("Product Form - Middle Section After Entering Data ");
			
			scrollDown(500);
			
			// Product SKU
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 1000 );	
			SKU_TextBox.sendKeys(this.ProductObj.getProductSKU());		
			
			//Product Price
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 1000 );	
			ProductPrice_TextBox.sendKeys(this.ProductObj.getProductPrice());	
			
			// Position
			WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 1000 );	
			Position_Textbox.sendKeys(this.ProductObj.getProductPosition());	
			
			// AddToCart 
			if ( this.ProductObj.getAddToCartOption().toLowerCase().equals("yes")) {
				WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 1000 );	
				AddToCart_Checkbox.click();
			}
			// Featured 
			if ( this.ProductObj.getFeaturedOption().toLowerCase().equals("yes")) {
				WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 1000 );	
				Featured_Checkbox.click();
			}
			// Display 
			if ( this.ProductObj.getDisplayOption().toLowerCase().equals("yes")) {
				WaitUtil.waitFor(Driver,"THREAD_SLEEP", (long) 1000 );	
				Display_Checkbox.click();
			}			
			// Capture the screenshot for logging			
			BaseTest.TestLog.captureScreenShot("Product Form - Bottom Section After Entering Data ");					
			
			return true ;
			
			
		} catch (Exception e) {
			System.out.println("Exception Occured in Backendproductpage.java : enterELearningDetails");
			System.out.println(e.getMessage());
			return false ;
		}
	}
}