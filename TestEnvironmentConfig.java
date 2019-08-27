package config;

public class TestEnvironmentConfig {
	
	private String 	TestBrowser ;
		
	public TestEnvironmentConfig( String Browser ){
	
		this.TestBrowser		= 	Browser ;
	}
	public String getCurrentTestBrowser( String Browser ){
		
		return this.TestBrowser	;
	}
}
