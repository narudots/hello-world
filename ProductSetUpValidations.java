package tests;

import static org.testng.Assert.assertEquals;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import coreapplicationclassesandinterfaces.Product;
import pages.FrontEndCommonPage;
import utils.ExcelUtils.TestDataInput;

public class ProductSetUpValidations extends BaseTest{
	
	@BeforeGroups(groups = "ProductSetUpValidations")
	public static void loginToAdminSiteBeforeProductsAreAdded() {
		
		try {
			CommonTestLibrary.loginAsAdmin() ;	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
		
	//************************************************************************************************
	// ** TEST 1 :  ** Products Set-Up **
	//************************************************************************************************

	@Test( dataProvider= "ProductSetUpTestData" , groups= {"ProductSetUpValidations"})
	public static void SetUpNewProduct(String [] ProductDetailsInArray) throws Exception {
		
		try {			
		
			Product ProductToBeAdded = new Product();			
			ProductToBeAdded.setAttributes(ProductDetailsInArray);
			
			assertEquals(true, CommonTestLibrary.addNewProduct(ProductToBeAdded) );		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@AfterGroups(groups = "ProductSetUpValidations")
	public static void logoutFromAdminSiteAfterProductsAdditionsAreTested(ITestContext result) {
		
		try {
			if (result.getFailedTests().size() == 0) {
				
				// Capture the screenshot for logging			
				BaseTest.TestLog.captureScreenShot("Before Logging out ");
				new FrontEndCommonPage(Driver).logout() ;
			}
				
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	//************************************************************************************************					
	// ** DATA PROVIDER 1 : Product Set Up Data	**
	//************************************************************************************************
			
	@DataProvider ( name = "ProductSetUpTestData" )
	
	public static String[][] fetchProductData(){
		
		String[][] ProductSetUpTestData ;
		
		ProductSetUpTestData = TestDataInput.getMatchedInputTableRowsForApplCodeAndTestDataType(
																		Product.TestDataSheetName,
																		TestApplicationParams.getCurrentTestApplication(),
																		"ProductSetUpTestData");				
		return (ProductSetUpTestData);
		
	}	
}
