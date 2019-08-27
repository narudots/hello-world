package utils.WebDriverControlUtils;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import tests.BaseTest;
import tests.CommonTestLibrary;
import utils.ErrorHandlerUtils.TestException;

public class NavigationUtil extends BaseTest{

	public static boolean navigate( WebDriver CurrentTestDriver, String NavigationUrl) throws TestException, InterruptedException{		
		
		CurrentTestDriver.get(NavigationUrl);		
		WaitUtil.waitFor(CurrentTestDriver, "THREAD_SLEEP",(long) 1000);
		
		CommonTestLibrary.acceptCookies() ;
		
		CurrentTestDriver.navigate().refresh();	
		CurrentTestDriver.manage().window().maximize();	

		WaitUtil.waitFor(CurrentTestDriver, "THREAD_SLEEP",(long) 1000);
		CurrentTestDriver.navigate().refresh();	
		
		try {
			
			if( CurrentTestDriver.getCurrentUrl().equals(NavigationUrl)) 
				return true; 			
			else{
				boolean successflag = RetryUtil.retryPageLaunch(CurrentTestDriver, NavigationUrl, NavigationUrl) ;
				
				if (successflag) return true;
				else
				throw new TestException("INTENDED PAGE WAS NOT LAUNCHED") ;				
			}
		} 
		catch(TestException te){			
			throw te;
		}
		
	}
	
	public static boolean navigate( WebDriver CurrentTestDriver, String LaunchUrl , String ActualUrlToCompare) throws TestException, InterruptedException{		
		
		CurrentTestDriver.get(LaunchUrl);		
		WaitUtil.waitFor(CurrentTestDriver, "THREAD_SLEEP",(long) 1000);
		
		CommonTestLibrary.acceptCookies() ;
		
		CurrentTestDriver.navigate().refresh();	
		CurrentTestDriver.manage().window().maximize();	
		
		try {
								
			if( CurrentTestDriver.getCurrentUrl().equals(ActualUrlToCompare)) 
				return true; 			
			else{
				boolean successflag = RetryUtil.retryPageLaunch(CurrentTestDriver, LaunchUrl, ActualUrlToCompare) ;
				
				if (successflag) return true;
				else
				throw new TestException("INTENDED PAGE WAS NOT LAUNCHED") ;				
			}
					
		} 
		catch(TestException te){			
			throw te;
		}
		
	}
	
	public static boolean navigateOffsetFromHome( WebDriver CurrentTestDriver, String Offset ) throws InterruptedException, TestException{
	
		WaitUtil.waitFor(CurrentTestDriver, "THREAD_SLEEP", (long)1000);
		String CurrentUrl = CurrentTestDriver.getCurrentUrl() ;
		
	
		if ((CurrentUrl.charAt(CurrentUrl.length() -1)) == '/'){
			CurrentUrl = CurrentUrl.substring(0, CurrentUrl.length()-1) ;
		}
		
		String TargetUrl = CurrentUrl + Offset ;	
		
		return(navigate(CurrentTestDriver,TargetUrl)) ;
		
	}
	public static boolean isAlertPresent( WebDriver CurrentTestDriver) {
		
        try {
        	CurrentTestDriver.switchTo().alert();
        	return true;
        } catch (NoAlertPresentException e) {
          return false;
        }
      }
}