package coreapplicationclassesandinterfaces;

public class ELearningProduct extends BaseApplication{
	
	public static final String TestDataSheetName = "ELearningProductDetails";

	private String CourseName ;
	private String SeatCount ;
	private String DefaultPackageSeatCount;
	private String DefaultPackagePrice;
	private String SeatPackage1Name;
	private String SeatPackage1SeatCount;
	private String SeatPackage1Price;
	private String SeatPackage2Name;
	private String SeatPackage2SeatCount;
	private String SeatPackage2Price;
	private String SeatPackage3Name;
	private String SeatPackage3SeatCount;
	private String SeatPackage3Price;
	
	@Override
	public void setAttributes(String[] AttributeVal){		
		this.ApplicationID 				= 	AttributeVal[0];
		this.TypeOfTest					=  	AttributeVal[1];
		this.TypeOfTestData				= 	AttributeVal[2];
		this.CourseName 				= 	AttributeVal[3];
		this.SeatCount					=	AttributeVal[4];
		this.DefaultPackageSeatCount	= 	AttributeVal[5];
		this.DefaultPackagePrice 		= 	AttributeVal[6];
		this.SeatPackage1Name 			= 	AttributeVal[7];
		this.SeatPackage1SeatCount		= 	AttributeVal[8];
		this.SeatPackage1Price 			= 	AttributeVal[9];
		this.SeatPackage2Name 			= 	AttributeVal[10];
		this.SeatPackage2SeatCount		= 	AttributeVal[11];
		this.SeatPackage2Price 			= 	AttributeVal[12];
		this.SeatPackage3Name 			= 	AttributeVal[13];
		this.SeatPackage3SeatCount		= 	AttributeVal[14];
		this.SeatPackage3Price 			= 	AttributeVal[15];
	}	

	public static String getTestdatasheetname() {
		return TestDataSheetName;
	}

	public String getCourseName() {
		return CourseName;
	}

	public String getSeatCount() {
		return SeatCount;
	}

	public String getDefaultPackageSeatCount() {
		return DefaultPackageSeatCount;
	}

	public String getDefaultPackagePrice() {
		return DefaultPackagePrice;
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