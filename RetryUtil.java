package utils.WebDriverControlUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RetryUtil {
	
	
	public static boolean retryingFindClick( WebElement Element) {
		
	    int attempts = 0;
	    while(attempts < 2) {
	        
	    	try {
	    		
	            Element.click();
	            return true;
	            
	        } catch(StaleElementReferenceException e) {
	        
	        }
	        attempts++;
	    }
	    return false;
	}

	public static boolean retryingFindClick( WebDriver CurrentTestDriver,By by) {
		
	    int attempts = 0;
	    while(attempts < 2) {
	        
	    	try {
	    		
	    		CurrentTestDriver.findElement(by).click();
	            return true;
	            
	        } catch(StaleElementReferenceException e) {
	        
	        }
	        attempts++;
	    }
	    return false;
	}
	
	public static boolean retryingFindElement( WebElement Element)  {
		
	    int attempts = 0;
	    while(attempts < 2) {
	        
	    	try {
	    		
	            Element.isSelected() ;
	            return true;
	            
	        } catch(StaleElementReferenceException e) {
	        
	        }
	        attempts++;
	    }
	    return false;
	}

	public static boolean retryPageLaunch( WebDriver CurrentTestDriver,String LaunchUrl, String ActualUrlToCompare) throws InterruptedException {
		
	    int attempts = 0;
	    while(attempts < 1) {    	
	    		
    		CurrentTestDriver.get(LaunchUrl);		
    		WaitUtil.waitFor(CurrentTestDriver, "THREAD_SLEEP",(long) 1000);
    		
    		CurrentTestDriver.navigate().refresh();	
    		CurrentTestDriver.manage().window().maximize();	    		
    	
			if( CurrentTestDriver.getCurrentUrl().equals(ActualUrlToCompare)) 
				return true; 			
			else
				attempts++;
	    }
	    return false;
	}
}
