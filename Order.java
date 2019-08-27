package coreapplicationclassesandinterfaces;

public class Order extends BaseApplication{
	
	public static final String TestDataSheetName = "OrderDetails";

	private String Order1Name ;
	private String Order2Name ;
	private String Order3Name ;
	private String OrderStatus ;
	private String StatusChangeEmailsDisabledOption ;
	
	@Override
	public void setAttributes(String[] AttributeVal){		
		this.ApplicationID 						= 	AttributeVal[0];
		this.TypeOfTest							=  	AttributeVal[1];
		this.TypeOfTestData						= 	AttributeVal[2];
		this.Order1Name							= 	AttributeVal[3];
		this.Order2Name							=	AttributeVal[4];
		this.Order3Name							= 	AttributeVal[5];
		this.OrderStatus						= 	AttributeVal[6];
		this.StatusChangeEmailsDisabledOption	= 	AttributeVal[7];
	}

	public String getOrder1Name() {
		return Order1Name;
	}

	public String getOrder2Name() {
		return Order2Name;
	}

	public String getOrder3Name() {
		return Order3Name;
	}

	public String getOrderStatus() {
		return OrderStatus;
	}

	public String getStatusChangeEmailsDisabledOption() {
		return StatusChangeEmailsDisabledOption;
	}		
	
}