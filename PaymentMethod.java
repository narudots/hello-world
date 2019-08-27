package coreapplicationclassesandinterfaces;

public class PaymentMethod extends BaseApplication {
	
	public static final String TestDataSheetName = "PaymentDetails" ;
	
	private String PaymentMethod;
	private String CardNumber;
	private String ExpirationMonth;
	private String ExpirationYear;
	private String CVV;

	@Override
	public void setAttributes(String[] AttributeVal) {
		
		this.ApplicationID  = AttributeVal[0];
		this.TypeOfTest		= AttributeVal[1];
		this.TypeOfTestData = AttributeVal[2];
		this.PaymentMethod	= AttributeVal[3];
		this.CardNumber		= AttributeVal[4];
		this.ExpirationMonth= AttributeVal[5];
		this.ExpirationYear	= AttributeVal[6];
		this.CVV			= AttributeVal[7];
	
	}
	public String getPaymentMethod()
	{
		return this.PaymentMethod ;
	}
	public String getCardNumber()
	{
		return this.CardNumber ;
	}
	public String getExpirationMonth()
	{
		return this.ExpirationMonth ;
	}
	public String getExpirationYear()
	{
		return this.ExpirationYear ;
	}	
	public String getCVV()
	{
		return this.CVV ;
	}
	public void setPaymentMethod(String PaymentMethodPassed)
	{
		this.PaymentMethod = PaymentMethodPassed ;
	}

}
