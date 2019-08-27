package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.WebDriverControlUtils.WaitUtil;

public class FrontEndHomePage extends FrontEndCommonPage{
	
	static String PageUrl ;
	static String PageTitle ;
	
	public FrontEndHomePage(WebDriver CurrentTestDriver) {
		super(CurrentTestDriver);
	}
	public static void setPageTitleAndURL(String HomePageTitle, String HomePageURL){
		PageTitle = HomePageTitle ;
		PageUrl = HomePageURL ;		 
	}
	public static String getPageTitle(){
		
		return PageTitle;
	}
	public static String getPageUrl(){
	
		return PageUrl;
	}
	public  boolean addMembershipProductToCartASAP() throws Exception {
		
		try {
			Driver.findElement(By.className("joinASAPCTA")).click() ;
			WaitUtil.waitFor(Driver, "THREAD_SLEEP", (long) 100);
			
			Driver.findElement(By.className("//a[@class='add-to-cart']")).click();
			WaitUtil.waitFor(Driver, "THREAD_SLEEP", (long) 100);
			
			return new FrontEndShoppingCartPage(Driver).checkout() ;
			
		} catch (Exception e) {
			throw e ;
		}
	}
	public  boolean addMembershipProductToCartSS() throws Exception {
		
		try {
			Driver.findElement(By.xpath("//div[@id='memberMenu']//a[contains(text(),'Become a member')]")).click() ;
			WaitUtil.waitFor(Driver, "THREAD_SLEEP", (long) 100);
			
			Driver.findElement(By.xpath("//div[@class='t3p0-field t3p0-field-blade']//div[4]//p[1]//a[1]")).click();
			WaitUtil.waitFor(Driver, "THREAD_SLEEP", (long) 100);
			
			return new FrontEndShoppingCartPage(Driver).checkout() ;
			
		} catch (Exception e) {
			throw e ;
		}
	}
	public boolean addMembershipProductToCartIP() throws Exception {
		
		try {
			Driver.findElement(By.xpath("//div[@id='memberMenu']//a[contains(text(),'Become a member')]")).click() ;
			WaitUtil.waitFor(Driver, "THREAD_SLEEP", (long) 100);
			
			Driver.findElement(By.xpath("//div[@class='professionalMembershipCTA']//a[contains(text(),'Professional')]")).click();			
			WaitUtil.waitFor(Driver, "THREAD_SLEEP", (long) 100);
			
			return new FrontEndShoppingCartPage(Driver).checkout() ;
			
		} catch (Exception e) {
			throw e ;
		}
		
	}
}