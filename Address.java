package coreapplicationclassesandinterfaces;

public class Address extends BaseApplication {

	public static final String TestDataSheetName = "AddressDetails";
	
	private String AddressLine1 ;
	private String AddressLine2 ;
	private String City ;
	private String State ;
	private String Country ;
	private String PostalCode ;
	private String Phone ;
	
	@Override
	public void setAttributes(String[] AttributeVal) {
		
		try {
			this.ApplicationID 	= 	AttributeVal[0];
			this.TypeOfTest		= 	AttributeVal[1];
			this.TypeOfTestData	= 	AttributeVal[2];
			this.AddressLine1	= 	AttributeVal[3];
			this.AddressLine2	= 	AttributeVal[4];
			this.City 			= 	AttributeVal[5];
			this.State 			= 	AttributeVal[6];
			this.Country 		= 	AttributeVal[7];
			this.PostalCode 	= 	AttributeVal[8];
			this.Phone 			= 	AttributeVal[9];
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	public String getAddressLine1()
	{
		return this.AddressLine1 ;
	}	
	public String getAddressLine2()
	{
		return this.AddressLine2 ;
	}
	public String getCity()
	{
		return this.City ;
	}
	public String getState()
	{
		return this.State ;
	}
	public String getCountry()
	{
		return this.Country ;
	}
	public String getPostalCode()
	{
		return this.PostalCode ;
	}	
	public String getPhone()
	{
		return this.Phone ;
	}
	
	public void setAddressLine1(String AddressLine1Passed)
	{
		this.AddressLine1 = AddressLine1Passed;
	}
	
	public void setPostalCode(String PostalCodePassed)
	{
		this.PostalCode = PostalCodePassed;
	}	
}
