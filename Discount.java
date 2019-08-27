package coreapplicationclassesandinterfaces;

public class Discount extends BaseApplication {

	private String CouponCode ;
	private String DiscountType ;
	private String DiscountValue ;
	
	public static final String TestDataSheetName = "DiscountDetails" ;
	
	@Override
	public void setAttributes(String[] AttributeVal) {
		
		this.ApplicationID 		= 	AttributeVal[0];
		this.TypeOfTest			= 	AttributeVal[1];	
		this.TypeOfTestData	= 	AttributeVal[2];
		this.CouponCode  		= 	AttributeVal[3];
		this.DiscountType 		= 	AttributeVal[4];
		this.DiscountValue 	= 	AttributeVal[5];
		
	}
	public String getCouponCode()
	{
		return this.CouponCode ;
	}	
	public String getDiscountType()
	{
		return this.DiscountType ;
	}	
	public String getDiscountValue()
	{
		return this.DiscountValue ;
	}
}
