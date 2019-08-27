package utils.ErrorHandlerUtils;

public class TestException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String ExceptionDescription ;

	public TestException ( String ExceptionString){		
		ExceptionDescription = ExceptionString;		
	}
	public String toString(){ 
		return ("Exception Occurred: "+ ExceptionDescription) ;
	}
	public void printException(){
		System.out.println("Exception Occurred: "+ ExceptionDescription) ;		
	}
	public String getExceptionName()
	{
		return ExceptionDescription ;
		
	}
	
	 
}
