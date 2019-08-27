package pages;

import org.openqa.selenium.WebDriver;

import tests.BaseTest;
import utils.WebDriverControlUtils.WaitUtil;

public class Page {

		protected WebDriver Driver ;
		
		public Page(WebDriver CurrentTestDriver){
			
			this.Driver = CurrentTestDriver ;	
			
		}
		public void scrollUp()  throws InterruptedException {
			
			BaseTest.getJSExecutor().executeScript("window.scrollBy(0,0)");
			WaitUtil.waitFor(Driver, "THREAD_SLEEP", (long)500);
		}
		public void scrollDown(int length) throws InterruptedException {
			
			//Scroll down to get Continue button in view
			BaseTest.getJSExecutor().executeScript("window.scrollBy(0,length)");		
			WaitUtil.waitFor(Driver, "THREAD_SLEEP", (long)500);
		}
	
}
