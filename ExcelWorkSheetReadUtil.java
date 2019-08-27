package utils.ExcelUtils;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class ExcelWorkSheetReadUtil {	
    
	static XSSFSheet ExcelWSheet;
	static XSSFCell Cell;

    static int RowCount = 0;
    static int ColumnCount = 0;

        
    public static String[][] readSheetDataIntoStringArray(XSSFSheet TestDataSheet) {    
    	
    	
    	String[][] TestDataTable;
    	
		ExcelWSheet = TestDataSheet;
		
		try {
					
			getRowCount();       		
			getColumnCount();  		
					
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		TestDataTable = new String[RowCount-1][ColumnCount];	
				
		for ( int i = 1, targetrow=0; i < RowCount; i++,targetrow++)
		{
			for (int j=0; j < ColumnCount; j++)
			{
				
				try {
					
					TestDataTable[targetrow][j] = getCellData(i, j) ;
					
				} catch (Exception e) {

					e.printStackTrace();
				}
				
			}
		}
		
		return TestDataTable;
   	   	
    }
    
    //This method returns the number of rows that have been used in the current sheet
    
    private static void getRowCount(){    	  
              
		RowCount = ExcelWSheet.getLastRowNum() + 1;
		
    }
    
    //This method returns the number of columns that have been used in the current sheet
            
    private static void getColumnCount(){
            	  
		int ColumnCountItr = 0;    
		
		try {
			
			
			while (ExcelWSheet.getRow(1).getCell(ColumnCountItr) != null){
				
				ColumnCountItr = ColumnCountItr + 1;
				
			}
			
		} 
		catch (Exception e) {
			
			e.printStackTrace();
			
		}                  
		
		ColumnCount = ColumnCountItr;
		
    }
 
    //This method reads the data of any cell(in the current sheet) given the cell 
    //coordinates(row,column)
 
    private static String getCellData(int RowNum, int ColNum) throws Exception{
    	
    	String CellData = null ;

    	try {
    		
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);	
		
	        switch (Cell.getCellType()) {
	        
               case XSSFCell.CELL_TYPE_NUMERIC :       	   
                   CellData = String.valueOf(Cell.getNumericCellValue()) ;
                   int i = CellData.indexOf('.');                   
                   CellData = CellData.substring(0, i) ; 
                   break;         
               default :
                   CellData = Cell.getStringCellValue() ;
                   break;    
                   
           }
			
		}
    	catch(NullPointerException ne) {
    		System.out.println("NullPointerException during Excel"+ExcelWSheet.getSheetName()+" data input : Error in cell[" +RowNum+","+ColNum+"]");
    		ne.printStackTrace();
    	}
    	catch (Exception e) {			
			
			e.printStackTrace();
		}
    	
		return CellData;
          
    }     
    
}