package tests;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FrontEndHomePage;
import utils.ErrorHandlerUtils.TestException;
import utils.WebDriverControlUtils.NavigationUtil;

public class ApplicationInitialLaunchValidation extends BaseTest{		
		
	@Test( groups = {"required"} )
	
	public void LaunchApplication() throws InterruptedException, IOException{
		
		try {	
			
			//Step 1 : Launch Test Application URL			
				NavigationUtil.navigate(Driver, TestApplicationParams.getInitialTestURL()) ;		
				String TitleOfPageLaunched = Driver.getTitle() ;							
				Assert.assertEquals(TitleOfPageLaunched, FrontEndHomePage.getPageTitle()) ;			
						
		} catch (TestException Te) {			
				Te.printException();
				assert false ;
		}		
	}
}