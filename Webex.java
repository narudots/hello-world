package coreapplicationclassesandinterfaces;

public class Webex extends BaseApplication{
	
	public static final String TestDataSheetName = "WebexProductDetails";

	private String UserName ;
	private String Password ;
	private String SiteId;
	private String SiteUrl;
	private String WebexId;
	
	@Override
	public void setAttributes(String[] AttributeVal){
		
		this.ApplicationID 		= 	AttributeVal[0];
		this.TypeOfTest			=  AttributeVal[1];
		this.TypeOfTestData	= 	AttributeVal[2];
		this.UserName 			= 	AttributeVal[3];
		this.Password				=	AttributeVal[4];
		this.SiteId						= 	AttributeVal[5];
		this.SiteUrl 					= 	AttributeVal[6];
		this.WebexId 				= 	AttributeVal[7];
	}	

	public String getUserName()
	{
		return this.UserName ;
	}		
	public String getPassword()
	{
		return this.Password ;
	}		
	public String getSiteId()
	{
		return this.SiteId ;
	}	
	public String getSiteUrl()
	{
		return this.SiteUrl ;
	}	
	public String getWebexId()
	{
		return this.WebexId ;
	}	
}