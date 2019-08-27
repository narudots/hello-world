package utils.ErrorHandlerUtils;

public class ErrorHandler {

//Error Codes
	public static final String PAGENAVIGATIONERROR  = "PAGENAVIGATIONERROR";
	public static final String LOGINFAILED = "LOGINFAILED";
	public static final String INVALIDLOGINCREDENTIALS = "IINVALIDLOGINCREDENTIALS";
	public static final String LOGOUTFAILED = "LOGOUTFAILED";
	public static final String NOREQUIREDERRORTHROWN = "NOREQUIREDERRORTHROWN";
	public static final String PRODUCTCHECKOUTERROR = "PRODUCTCHECKOUTERROR";
	public static final String ADDTOCARTERROR = "ADDTOCARTERROR";
	public static final String DISCOUNTERROR = "DISCOUNTERROR";
	public static final String PRODUCTINCORRECTERROR = "PRODUCTINCORRECTERROR";
	public static final String INVALIDBROWSER = "INVALIDBROWSER" ;
	public static final String INVALIDBROWSERFILEPATH = "INVALIDBROWSERFILEPATH";
	public static final String FILENOTFOUND = "FILENOTFOUND";
	public static final String FILECANNOTBEREAD = "FILECANNOTBEREAD";


	public static void logError(){
		
		switch(ERROR.ErrorCode){

		case PAGENAVIGATIONERROR 		: 	System.out.println("**ERROR : PAGE CANNOT BE LAUNCHED: " + ERROR.ErrorParam) ; 
											break ;
		case LOGINFAILED 				:	System.out.println("**ERROR : LOGIN FAILED FOR | : " + ERROR.ErrorParam) ;
											break ;
		case LOGOUTFAILED 				:	System.out.println("**ERROR : LOGOUT FAILED FOR | : " + ERROR.ErrorParam) ;
											break ;		
		case INVALIDLOGINCREDENTIALS 	:	System.out.println("**ERROR : INVALID LOGIN ATTEMPT | : " + ERROR.ErrorParam) ;
											break ;
		case NOREQUIREDERRORTHROWN 		:	System.out.println("**ERROR : REQUIRED ERROR NOT THROWN FOR FIELD | : " + ERROR.ErrorParam) ;
											break ;		
		case PRODUCTCHECKOUTERROR 		:	System.out.println("**ERROR : PRODUCT CANNOT BE CHECKED OUT  | : " + ERROR.ErrorParam) ;
		break ;	
		
		case ADDTOCARTERROR 			:	System.out.println("**ERROR : PRODUCT CANNOT BE ADDED TO CART | : " + ERROR.ErrorParam) ;
		break ;	
		
		case DISCOUNTERROR 				:	System.out.println("**ERROR : DISCOUNT CANNOT BE APPLIED | : " + ERROR.ErrorParam) ;
		break ;	
		
		case PRODUCTINCORRECTERROR 		:	System.out.println("**ERROR : PRODUCT NOT ADDED TO CART | : " + ERROR.ErrorParam) ;
		break ;
		
		case INVALIDBROWSER				:	System.out.println ( "**ERROR : INVALID BROWSER NAME**\n"
											+ "--CHECK BROWSER INPUT IN TESTNG.XML--\n") ;
		break ;
		
		case FILENOTFOUND				:	System.out.println ( "**ERROR : FILE CANNOT BE FOUND**\n"
											+ ERROR.ErrorParam) ;
		break ;

		case FILECANNOTBEREAD			:	System.out.println ( "**ERROR : FILE CANNOT BE READ**\n"
											+ ERROR.ErrorParam) ;
		break ;

		case INVALIDBROWSERFILEPATH		:	System.out.println ( "**ERROR : INVALID BROWSER FILEPATH **\n"
											+ "--CHECK BROWSER FILE PATH INPUT IN TESTNG.XML--\n") ;
		}	
	}
}
