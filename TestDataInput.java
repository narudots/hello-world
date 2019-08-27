package utils.ExcelUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;

/*__________________________________________________________________________________________________________________________
													<Sample TABLE>
|-------Col0------|-----------Col1--------|--Col2---|---Col3---|----Col4----|---Col5----|---Col6---|---Col7--|----Col8-----|
____________________________________________________________________________________________________________________________
| ApplicationCode | Test_Data_Description | User_ID | Password | First_Name | Last_Name	| Email_ID | Company | Member_Type |
___________________________________________________________________________________________________________________________*/

public class TestDataInput {

	static XSSFSheet 		SheetToRetrieveDataFrom ;	
	static ExcelSetUpUtil	ExcelHndl 			= new ExcelSetUpUtil();
	static String[][]		TableFromExcel ;
	
	private static int getOutputArrayLength(String[][] TableToBeParsed, 
											String ValueToBeMatched1, 
											int columntocheck1,
											String ValueToBeMatched2, 
											int columntocheck2){
		
		int rowsmatched = 0 ;
		
		for (int row = 0; row < TableFromExcel.length ; row++){
			
			if (TableFromExcel[row][columntocheck1].equals(ValueToBeMatched1) & TableFromExcel[row][columntocheck2].equals(ValueToBeMatched2))
			{		
				rowsmatched++ ;
			}
		}
		
		return rowsmatched ;
		
	}	
	public static String[][] getMatchedInputTableRowsForApplCodeAndTestDataType(String DataSheetName,
																				String ApplicationCode, 
																				String TestDataRef) {
		
		SheetToRetrieveDataFrom = 	ExcelHndl.getTestDataSheet(DataSheetName);
		TableFromExcel 			=	ExcelWorkSheetReadUtil.readSheetDataIntoStringArray(SheetToRetrieveDataFrom);
		
		int applicationcodecolumnreference = 0 ;
		int testdatatypecolumnreference = 1;
		int outputrow = 0 ;
		
		String[][] ToBeReturnedUserList;
		try {
			ToBeReturnedUserList = new String[getOutputArrayLength( 	TableFromExcel,
																				ApplicationCode,
																				applicationcodecolumnreference,
																				TestDataRef,
																				testdatatypecolumnreference
																				)]
														[TableFromExcel[0].length];
			
			for ( int row = 0; row < TableFromExcel.length ; row++){
				
				if ( TableFromExcel[row][applicationcodecolumnreference].equals(ApplicationCode) &
					 TableFromExcel[row][testdatatypecolumnreference].equals(TestDataRef))
				{		
					for ( 	int column =0, outputcolumn = 0; 
							column < TableFromExcel[0].length; 
							column++, outputcolumn++ ){
						
						ToBeReturnedUserList[outputrow][outputcolumn] = TableFromExcel[row][column] ;
						
					}				
					outputrow++ ;
				}
			}			
			
			return ToBeReturnedUserList;
			
		} catch (Exception e) {
			
			return null ;
		}


	}
	
	public static String[] getFirstRowInSheetMatchingApplCodeAndTestDataType(	String DataSheetName,
																				String ApplicationCode, 
																				String TestDataRef) {
	
		SheetToRetrieveDataFrom = 	ExcelHndl.getTestDataSheet(DataSheetName);
		TableFromExcel 			=	ExcelWorkSheetReadUtil.readSheetDataIntoStringArray(SheetToRetrieveDataFrom);
		
		String[] ToBeReturnedRow;
		
		try {
			int applicationcodecolumnreference = 0 ;
			int testdatatypecolumnreference = 2;
			
			ToBeReturnedRow = new String[TableFromExcel[0].length];
			
			for ( int row = 0; row < TableFromExcel.length ; row++){
			
				if ( 	TableFromExcel[row][applicationcodecolumnreference].equals(ApplicationCode) &
						TableFromExcel[row][testdatatypecolumnreference].equals(TestDataRef))
				{		
					
					for ( 	int column =0, outputcolumn = 0; 
							column < TableFromExcel[0].length; 
							column++, outputcolumn++ ){
				
						ToBeReturnedRow[outputcolumn] = TableFromExcel[row][column] ;
										
					}				
					break ;					
				}
			}
			
			return ToBeReturnedRow;
			
		} catch (Exception e) {
			
			System.out.println(" Exception in Excel Util " + e.getMessage());
			return null ;
		}		
	}		
}