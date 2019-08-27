package utils.ErrorHandlerUtils;

public class ERROR {
	static String ErrorCode ;
	static String ErrorParam ;
	
	public static void initError(String arg0){
		
		ErrorCode = arg0 ;
		ErrorParam = "" ;
	}
	public static void initError(String arg0, String arg1){
		
		ErrorCode = arg0 ;
		ErrorParam = arg1 ;
	}
}