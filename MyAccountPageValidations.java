package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import coreapplicationclassesandinterfaces.Product;
import pages.FrontEndCommonPage;
import utils.ErrorHandlerUtils.TestException;
import utils.ExcelUtils.TestDataInput;

public class MyAccountPageValidations extends BaseTest {	

	//************************************************************************************************
	// ** TEST : checkIf_ValidUserCredentialsAreAccepted **
	//************************************************************************************************
	
	@Test( dataProvider="SubscriptionUpgradeTestData" , groups ={ "SubscriptionUpgradeValidations"})	
					
	public static void IOFMMembershipUpgrade( String ApplicationCode , String TestType,String TestDataType, String Source, String Target){		
	
		try {		
			
			 	String[] SourceMembershipData = TestDataInput.getFirstRowInSheetMatchingApplCodeAndTestDataType(
															 							Product.TestDataSheetName,
															 							TestApplicationParams.getCurrentTestApplication(),
															 							Source) ;
			
			 	CommonTestLibrary.purchaseIOFMMembershipProduct(SourceMembershipData) ;
			 	
			 	CommonTestLibrary.upgradeMembership(Source, Target) ;
			 	
			 	new FrontEndCommonPage(Driver).logout() ;
		} 
		catch ( TestException Te)
		{
			TestLog.logTestStep("Error/Exception : " + Te.getExceptionName());
			Te.printException();
			assert(false);
		}		
		catch (InterruptedException e){
			//do nothing		
		}
		catch ( Exception e)
		{
			e.printStackTrace();
			TestLog.logTestStep("Error/Exception " + e.getMessage());
			assert(false) ;
		}		
	}
	//************************************************************************************************
	// ** DATA PROVIDER 1 : NewUserData	**
	//************************************************************************************************
	
	@DataProvider ( name = "SubscriptionUpgradeTestData" )	
	public static String[][] fetchMembershipUpgradeCombosToValidate(){
	
		String [][] MembershipUpgradeCombos = 	TestDataInput.getMatchedInputTableRowsForApplCodeAndTestDataType(											
											"IOFMMembershipUpgradeDetails",
											TestApplicationParams.getCurrentTestApplication(),
											"SubscriptionUpgradeTest") ;
		return (MembershipUpgradeCombos); 	
	}

}