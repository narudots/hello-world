package config;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserStackConfig {
	
	 private WebDriver Driver;
	 	    
	 public  WebDriver setUpBrowserStackAndRemoteDriver(String Browser, TestApplicationConfig testApplicationParams) throws MalformedURLException {
	 String     BSUserName = "ericwagner1" ;
	 String  	BSAccessKey = "R5kFyC3ThdEJiFyLxu8f" ;
	 String BSURL   =  "https://"+BSUserName+":"+BSAccessKey+"@hub.browserstack.com/wd/hub";
	 
	 DesiredCapabilities caps = new DesiredCapabilities() ;
	 
	 switch (Browser.toLowerCase()) {
	case "chrome":
		 caps.setPlatform(Platform.WINDOWS);
		 caps.setBrowserName("chrome");
		 caps.setVersion("75");
		break;

	case "firefox" : 
		caps.setPlatform(Platform.ANY);
		caps.setBrowserName("firefox");
		caps.setVersion("66");
		break;
	
	case "edge" :
		caps.setPlatform(Platform.ANY);
		caps.setBrowserName("edge");
		caps.setVersion("15");
		break ;
		
	case "ie":
		
		caps.setPlatform(Platform.ANY);
		caps.setBrowserName("ie");
		caps.setVersion("11");
		break ;
	
		
	case "safari" :
	    caps.setCapability("browser", "Safari");
	    caps.setCapability("browser_version", "6.0");
	    caps.setCapability("os", "OS X");
	    caps.setCapability("os_version", "Lion");
	    
		break ;
		
	case "ios" :
		caps.setCapability("os_version", "11");
		caps.setCapability("device", "iPhone 8 Plus");
		caps.setCapability("real_mobile", "true");
		caps.setCapability("browserstack.local", "false");
		break;
		
	case "andriod":
	}
		 
	 caps.setCapability("name", testApplicationParams.getCurrentTestApplication()+" testing in " + Browser);
	 
	 URL BrowserStackUrl = new URL(BSURL) ;
	 
	 Driver = new RemoteWebDriver(BrowserStackUrl, caps) ;
	 
	return Driver;
	 
	 }
}
