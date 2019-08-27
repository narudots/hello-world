package utils.WebDriverControlUtils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtil {
	
	public static void waitFor(	WebDriver CurrentTestDriver,
								String Type, 
								Long Duration) throws InterruptedException{
		
		try {
			
			switch (Type) {
			
			case "THREAD_SLEEP" : Thread.sleep(Duration);
								  break;
			case "IMPLICIT" 	: CurrentTestDriver.manage().timeouts().implicitlyWait(
																		 Duration, TimeUnit.SECONDS);
								  break;
			
			}
			
		} 
		catch (InterruptedException e) {
		
			throw e;
		}	
		catch ( Exception e){
			
			e.printStackTrace();
		}
		
	}
	
	//************************************************************************************************
	// waitForWebElementToBeVisible - ExpectedConditionsCheck(Element Visible)
	//************************************************************************************************
	
	public static void waitForWebElementToBeVisible(WebDriver CurrentTestDriver,
													WebElement PageElement,
													Long Duration)throws NoSuchElementException,TimeoutException{
		
		try {
	
			WebDriverWait wait = new WebDriverWait(CurrentTestDriver, Duration);
			wait.until( ExpectedConditions.visibilityOf( PageElement ));			
			
		} 
		catch ( Exception e){
			
			throw e;
		}
		
		
	}
	
	//************************************************************************************************
	// waitForWebElementToBeInVisible - ExpectedConditionsCheck(Element InVisible)
	//************************************************************************************************
	
	public static void waitForWebElementToBeInvisible(	WebDriver CurrentTestDriver,
														WebElement PageElement,
														Long Duration) throws NoSuchElementException,TimeoutException{
		
		try {
			
			WebDriverWait wait = new WebDriverWait(CurrentTestDriver, Duration);
			
			wait.until( ExpectedConditions.invisibilityOf( PageElement ));			
			
		} 
		catch (NoSuchElementException e) {
		
			throw e ;
		}
		catch (TimeoutException e) {
			
			throw e ;
		}
		catch ( Exception e){
			
			e.printStackTrace();
		}
		
		
	}
	
}
