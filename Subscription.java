package coreapplicationclassesandinterfaces;

public class Subscription extends BaseApplication{
	
	public static final String TestDataSheetName = "SubscriptionProductDetails";

	private String Type ;
	private String Period ;
	private String Length;
	private String SeatCount;
	private String SeatPackage1Name;
	private String SeatPackage1SeatCount;
	private String SeatPackage1Price;
	private String SeatPackage2Name;
	private String SeatPackage2SeatCount;
	private String SeatPackage2Price;
	private String SeatPackage3Name;
	private String SeatPackage3SeatCount;
	private String SeatPackage3Price;	
	private String GrandfatheredOption;
	private String AutoRenewOption;
	private String CreditCartRequiredOption;
	
	@Override
	public void setAttributes(String[] AttributeVal){		
		this.ApplicationID 				= 	AttributeVal[0];
		this.TypeOfTest					=  	AttributeVal[1];
		this.TypeOfTestData				= 	AttributeVal[2];
		this.Type 						= 	AttributeVal[3];
		this.Period						=	AttributeVal[4];
		this.Length						= 	AttributeVal[5];
		this.SeatCount 					= 	AttributeVal[6];
		this.SeatPackage1Name 			= 	AttributeVal[7];
		this.SeatPackage1SeatCount		= 	AttributeVal[8];
		this.SeatPackage1Price 			= 	AttributeVal[9];
		this.SeatPackage2Name 			= 	AttributeVal[10];
		this.SeatPackage2SeatCount		= 	AttributeVal[11];
		this.SeatPackage2Price 			= 	AttributeVal[12];
		this.SeatPackage3Name 			= 	AttributeVal[13];
		this.SeatPackage3SeatCount		= 	AttributeVal[14];
		this.SeatPackage3Price 			= 	AttributeVal[15];
		this.GrandfatheredOption		=	AttributeVal[16];
		this.AutoRenewOption			=	AttributeVal[17];
		this.CreditCartRequiredOption	=	AttributeVal[18];
		
	}	

	public static String getTestdatasheetname() {
		return TestDataSheetName;
	}
	
	public String getType() {
		return Type;
	}

	public String getPeriod() {
		return Period;
	}

	public String getLength() {
		return Length;
	}

	public String getSeatCount() {
		return SeatCount;
	}

	public String getSeatPackage1Name() {
		return SeatPackage1Name;
	}

	public String getSeatPackage1SeatCount() {
		return SeatPackage1SeatCount;
	}

	public String getSeatPackage1Price() {
		return SeatPackage1Price;
	}

	public String getSeatPackage2Name() {
		return SeatPackage2Name;
	}

	public String getSeatPackage2SeatCount() {
		return SeatPackage2SeatCount;
	}

	public String getSeatPackage2Price() {
		return SeatPackage2Price;
	}

	public String getSeatPackage3Name() {
		return SeatPackage3Name;
	}

	public String getSeatPackage3SeatCount() {
		return SeatPackage3SeatCount;
	}

	public String getSeatPackage3Price() {
		return SeatPackage3Price;
	}

	public String getGrandfatheredOption() {
		return GrandfatheredOption;
	}

	public String getAutoRenewOption() {
		return AutoRenewOption;
	}

	public String getCreditCartRequiredOption() {
		return CreditCartRequiredOption;
	}
	
	public String getSeatPackageInfo(String Variable, String Index) {
		
		switch(Variable+Index){
			
			case "Name1" 	: return getSeatPackage1Name() 		; 
			case "Count1" 	: return getSeatPackage1SeatCount() ; 
			case "Price1" 	: return getSeatPackage1Price() 	; 
			
			case "Name2" 	: return getSeatPackage2Name() 		; 
			case "Count2" 	: return getSeatPackage2SeatCount() ; 
			case "Price2" 	: return getSeatPackage2Price() 	; 
			
			case "Name3" 	: return getSeatPackage3Name() 		; 
			case "Count3" 	: return getSeatPackage3SeatCount() ; 
			case "Price3" 	: return getSeatPackage3Price() 	; 
			default 		: return "none" ;
		
		}		
	}
}