package coreapplicationclassesandinterfaces;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Product extends BaseApplication{
	
	public static final String TestDataSheetName = "ProductDetails";

	private String ProductType ;
	private String ProductName ;
	private String ProductShortDescription;
	private String ProductFullDescription;
	private String ProductEmailInstructions;
	private String ProductMarketingUrl;
	private String ProductAccountingCode;
	private String ProductTaxCode;
	private String ProductSKU ;
	private String ProductPrice ;
	private String ProductPosition ;
	private String AddToCartOption;
	private String FeaturedOption;
	private String DisplayOption;
	private String ProductCheckoutLink ;
	
	@Override
	public void setAttributes(String[] AttributeVal){
		
		this.ApplicationID 						= 	AttributeVal[0];
		this.TypeOfTest							=  AttributeVal[1];
		this.TypeOfTestData 					= 	AttributeVal[2];
		this.ProductType 							= 	AttributeVal[3];
		
		if (this.TypeOfTest.toLowerCase().contains("productsetup")){
			Date date 							= 		new Date();
			SimpleDateFormat sdf 	= 		new SimpleDateFormat("MMddyyyyhmmss");
			String formattedDate 		= 		sdf.format(date);			
			this.ProductName 			= 		AttributeVal[4] + formattedDate ;
		}
		else 	
			this.ProductName 						= 	AttributeVal[4];
		
		this.ProductShortDescription	=	AttributeVal[5];
		this.ProductFullDescription 		= 	AttributeVal[6];
		this.ProductEmailInstructions 	= 	AttributeVal[7];
		this.ProductMarketingUrl 			= 	AttributeVal[8];
		this.ProductAccountingCode 	= 	AttributeVal[9];		
		this.ProductTaxCode 					= 	AttributeVal[10];
		this.ProductSKU 							= 	AttributeVal[11];
		this.ProductPrice 							= 	AttributeVal[12];
		this.ProductPosition 					= 	AttributeVal[13];	
		this.AddToCartOption 				= 	AttributeVal[14];
		this.FeaturedOption 					= 	AttributeVal[15];
		this.DisplayOption 						= 	AttributeVal[16];	
		this.ProductCheckoutLink 			= 	AttributeVal[17];
	}	

	public String getProductType()
	{
		return this.ProductType ;
	}		
	public String getProductName()
	{
		return this.ProductName ;
	}		
	public String getProductShortDesc()
	{
		return this.ProductShortDescription ;
	}	
	public String getProductFullDesc()
	{
		return this.ProductFullDescription ;
	}	
	public String getProductEmailInstructions()
	{
		return this.ProductEmailInstructions ;
	}	
	public String getProductMarketingUrl()
	{
		return this.ProductMarketingUrl ;
	}	
	public String getProductAccoutingCode()
	{
		return this.ProductAccountingCode ;
	}	
	public String getProductTaxCode()
	{
		return this.ProductTaxCode ;
	}	
	public String getProductSKU()
	{
		return this.ProductSKU ;
	}	
	public String getProductPrice()
	{
		return this.ProductPrice ;
	}	
	public String getProductPosition()
	{
		return this.ProductPosition ;
	}			
	public String getAddToCartOption()
	{
		return this.AddToCartOption ;
	}		
	public String getFeaturedOption()
	{
		return this.FeaturedOption ;
	}	
	public String getDisplayOption()
	{
		return this.DisplayOption ;
	}
	public String getProductCheckoutLink() 
	{
		return this.ProductCheckoutLink;
	}	
	
}
