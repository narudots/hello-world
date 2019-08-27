package coreapplicationclassesandinterfaces;

import java.text.SimpleDateFormat;
import java.util.Date;

public class User extends BaseApplication {

	public static final String TestDataSheetName = "UserDetails" ;
	private 			String UserName ;
	private 			String Password ;
	private 			String FirstName ;
	private 			String LastName ;
	private 			String EmailId ;
	private 			String PhoneNumber ;
	private 			String JobTitle ;
	private 			String Company ;
	private 			String Industry ;
	
	public void 	setAttributes(String[] AttributeVal){
		
		this.ApplicationID 	= 	AttributeVal[0];
		this.TypeOfTest		= 	AttributeVal[1];	
		this.TypeOfTestData = 	AttributeVal[2];
		this.UserName 		= 	AttributeVal[3];
		this.Password 		= 	AttributeVal[4];
		this.FirstName		=	AttributeVal[5];
		this.LastName 		= 	AttributeVal[6];
		this.EmailId 		= 	AttributeVal[7];
		this.PhoneNumber    =   AttributeVal[8];
		this.JobTitle 		= 	AttributeVal[9];
		this.Company		=   AttributeVal[10];
		this.Industry		=	AttributeVal[11];

		
		try {
			if (this.UserName.equals("GenerateUserName"))
			{
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyyhmmss");
				String formattedDate = sdf.format(date);
				
				this.UserName = FirstName + formattedDate + "@test.com";
				this.EmailId = this.UserName ;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	public String 	getUserName()
	{
		return this.UserName ;
	}	
	public String 	getUserPassword()
	{
		return this.Password ;
	}
	public String 	getUserFirstName()
	{
		return this.FirstName ;
	}
	public String	getUserLastName()
	{
		return this.LastName ;
	}
	public String 	getUserEmailId()
	{
		return this.EmailId ;
	}
	public String 	getUserCompany()
	{
		return this.Company ;
	}	
	public User 	getUser()
	{
		return this ;
	}
	public String 	getUserJobTitle(){
		
		return JobTitle ;
	}
	public String 	getUserIndustry(){
		
		if (this.ApplicationID.equals("IOFM"))
		{
			switch (this.Industry) {			
				case "AP" : return "Accounts Payable & Procure-to-Pay" ;					
				case "AR" : return "Accounts Receivable & Order-to-Cash" ;					
				default	  : return "Both" ;
			}
		}	
		else {
			return this.Industry ;
		}
	}
	public String 	getPhoneNumber() {
		return PhoneNumber;
	}	
	public boolean 	isUserAdmin(){
		
		if (TypeOfTestData.equals("Admin"))
			return true ;
		
		else
			return false ;
	}
	public boolean 	isUserMember(){
		
		if (TypeOfTestData.equals("Member"))
			return true ;
		
		else
			return false ;
	}
	public boolean 	isUserNonMember(){
		
		if (TypeOfTestData.equals("NonMember"))
			return true ;
		
		else
			return false ;
	}
	public boolean 	isInvalid() {
		if (TypeOfTestData.equals("Invalid"))
			return true ;
		
		else
			return false ;
	}
	public boolean 	isNewUser(){
		if (TypeOfTestData.contains("NewUser"))
			return true ;
		
		else
			return false ;
	}
	public void 	setFirstName(String firstNameString) {
		this.FirstName = firstNameString ;		
	}
	public void 	setLastName(String lastNameString) {
		this.LastName = lastNameString ;		
	}
	public void 	setEmailID(String EmailIDString) {
		this.EmailId = EmailIDString ;		
	}
	public void 	setCompanyName(String companyNameString) {
		this.Company = companyNameString ;		
	}
	public void 	setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	
}