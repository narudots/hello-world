package utils.ExcelUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import utils.ErrorHandlerUtils.ERROR;
import utils.ErrorHandlerUtils.ErrorHandler;

public class ExcelSetUpUtil {	
    
	XSSFSheet ExcelWSheet;
	public static XSSFWorkbook ExcelWBook;	    
       
    public static void getTestDataWorkbook(String Path) {
    	
		try {
			FileInputStream ExcelFile = new FileInputStream(Path);
			   
			   // Access the required test data workbook

			   ExcelWBook = new XSSFWorkbook(ExcelFile);
			   
		} catch (FileNotFoundException e) {
		
			ERROR.initError(ErrorHandler.FILENOTFOUND, Path);
			ErrorHandler.logError();
			
		} catch (IOException e) {
		
			ERROR.initError(ErrorHandler.FILECANNOTBEREAD, Path);
			ErrorHandler.logError();
			
		}
        
    }
    
    public XSSFSheet getTestDataSheet(String SheetName) {    	  
              
		return this.ExcelWSheet = ExcelWBook.getSheet(SheetName);
		
    }
}