package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class TestWebDriverConfig {	

	private WebDriver  Driver;
	
	public WebDriver setUpWebDriverForTest( String Browser, 
											String BrowserEXEFilePath ){		
		switch ( Browser.toLowerCase() ){	
			
			case "chrome" : 
					
				System.setProperty(	 "webdriver.chrome.driver", 
														BrowserEXEFilePath + 
														"chromedriver.exe" ) ;				
				this.Driver = new ChromeDriver() ;				
				return this.Driver ;				
		  		  		
			case "firefox" :
					
				System.setProperty(	 "webdriver.gecko.driver", 
													BrowserEXEFilePath+
													"geckodriver.exe" ) ;				
				this.Driver = new FirefoxDriver() ;				
				return this.Driver ;
				
			case "edge" :
				
				
				System.setProperty(	 "webdriver.edge.driver", 
													BrowserEXEFilePath+
													"MicrosoftWebDriver.exe" ) ;		
				this.Driver = new EdgeDriver() ;				
				return this.Driver ;
				
			case "ie" :
				
				
				System.setProperty(	 "webdriver.ie.driver", 
													BrowserEXEFilePath+
													"IEDriverServer.exe" ) ;				
				this.Driver = new InternetExplorerDriver() ;				
				return this.Driver ;	
			default : this.Driver = null ;
			
		} 		
		return this.Driver;		
	}
}
