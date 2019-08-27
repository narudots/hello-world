package coreapplicationclassesandinterfaces;

public class Bundle extends BaseApplication{
	
	public static final String TestDataSheetName = "BundleProductDetails";

	private String Product1Name ;
	private String Product1Price ;
	private String Product2Name ;
	private String Product2Price ;
	private String Product3Name ;
	private String Product3Price ;
	
	@Override
	public void setAttributes(String[] AttributeVal){		
		this.ApplicationID 				= 	AttributeVal[0];
		this.TypeOfTest					=  	AttributeVal[1];
		this.TypeOfTestData				= 	AttributeVal[2];
		this.Product1Name				= 	AttributeVal[3];
		this.Product1Price				=	AttributeVal[4];
		this.Product2Name				= 	AttributeVal[5];
		this.Product2Price				= 	AttributeVal[6];
		this.Product3Name	 			= 	AttributeVal[7];
		this.Product3Price				= 	AttributeVal[8];
	}	

	public String getProduct1Name() {
		return Product1Name;
	}

	public String getProduct1Price() {
		return Product1Price;
	}

	public String getProduct2Name() {
		return Product2Name;
	}

	public String getProduct2Price() {
		return Product2Price;
	}

	public String getProduct3Name() {
		return Product3Name;
	}

	public String getProduct3Price() {
		return Product3Price;
	}

	public static String getTestdatasheetname() {
		return TestDataSheetName;
	}
	
}