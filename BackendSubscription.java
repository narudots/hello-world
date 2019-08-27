package coreapplicationclassesandinterfaces;

public class BackendSubscription extends BaseApplication{
	
	public static final String TestDataSheetName = "BackendSubscriptionDetails";

	private String ProductName ;
	private String Status ;
	private String Period ;
	private String Length;
	private String SeatCount;	
	private String GrandfatheredOption;
	private String GrandfatheredPrice;
	private String AutoRenewOption;
	private String SubscriptionStartDate;
	private String SubscriptionExpiryDate;
	
	@Override
	public void setAttributes(String[] AttributeVal){		
		this.ApplicationID 				= 	AttributeVal[0];
		this.TypeOfTest					=  	AttributeVal[1];
		this.TypeOfTestData				= 	AttributeVal[2];
		this.setProductName(AttributeVal[3]);
		this.Status 					= 	AttributeVal[4];
		this.Period						=	AttributeVal[5];
		this.Length						= 	AttributeVal[6];
		this.SeatCount 					= 	AttributeVal[7];		
		this.GrandfatheredOption		=	AttributeVal[8];
		this.GrandfatheredPrice			=	AttributeVal[9];
		this.AutoRenewOption			=	AttributeVal[10];
		this.SubscriptionStartDate		=	AttributeVal[11];
		this.SubscriptionExpiryDate		=	AttributeVal[12];
		
	}	

	public static String getTestdatasheetname() {
		return TestDataSheetName;
	}

	
	public String getStatus() {
		return Status;
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

	public String getGrandfatheredOption() {
		return GrandfatheredOption;
	}

	public String getGrandfatheredPrice() {
		return GrandfatheredPrice;
	}

	public String getAutoRenewOption() {
		return AutoRenewOption;
	}

	public String getSubscriptionStartDate() {
		return SubscriptionStartDate;
	}

	public String getSubscriptionExpiryDate() {
		return SubscriptionExpiryDate;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}			
}