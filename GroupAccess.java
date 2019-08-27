package coreapplicationclassesandinterfaces;

public class GroupAccess extends BaseApplication{
	
	public static final String TestDataSheetName = "GroupAccessProductDetails";

	private String CustomerGroupName ;
	
	@Override
	public void setAttributes(String[] AttributeVal){		
		
		this.ApplicationID 				= 	AttributeVal[0];
		this.TypeOfTest					=  	AttributeVal[1];
		this.TypeOfTestData				= 	AttributeVal[2];
		this.CustomerGroupName			= 	AttributeVal[3];
		
	}	

	public String getCustomerGroupName() {
		return CustomerGroupName;
	}			

	public static String getTestdatasheetname() {
		return TestDataSheetName;
	}
	
}