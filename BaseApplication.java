package coreapplicationclassesandinterfaces;

public abstract class BaseApplication {
	
	String ApplicationID ;	
	String TypeOfTest ;
	String TypeOfTestData ;	
	
	public abstract void setAttributes(String[] Values) ;

	public String getApplicationID(){		
		return ApplicationID ;
	}
	public String getTypeOfTest(){		
		return TypeOfTest ;
	}
	public String getTypeOfTestData(){		
		return TypeOfTestData ;
	}
}
