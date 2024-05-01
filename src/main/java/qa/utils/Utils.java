package qa.utils;

import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

public class Utils {

	
	public static final int IMPLICIT_WAIT_TIME=10;
	public static final int PAGE_WAIT_TIME=5;
	
	
	public static String generateEmailWithTimeStamp() {
		
		Date date=new Date();
		String timestamp= date.toString().replace(" ", "_").replace(":", "_");
		return "semaskarshubham94"+timestamp+"@gmail.com";
	}
	
	public static Object[][] getTestDataFromExcel(String sheetName)
	{
		XSSFWorkbook workbook= new XSSFWorkbook();
		XSSFSheet sheet= workbook.getSheet(sheetName);
		
		int rows =sheet.getLastRowNum(); // get no of row
		int cols= sheet.getRow(0).getLastCellNum();
		
		Object [][] data= new Object[rows][cols];
		
		for(int i=0;i<rows;i++) {
			XSSFRow row = sheet.getRow(i+1);
			
			for(int j=0;j<cols;j++) {
				
				XSSFCell cell = row.getCell(j);
				CellType cellType = cell.getCellType();
				
				switch(cellType) {
				
				case STRING:
					data[i][j] = cell.getStringCellValue();
					break;
				case NUMERIC:
					data[i][j] = Integer.toString((int)cell.getNumericCellValue());
					break;
				case BOOLEAN:
					data[i][j] = cell.getBooleanCellValue();
					break;	
		
				}
				
			}
		}
		return data;
	}
	
			public static void captureScreenshot(WebDriver driver,String testname) {
				
				  if (driver != null) {
				        try {
				            // Capture and save the screenshot
				        } catch (Exception e) {
				            e.printStackTrace();
				        }
				
				
			}
			}
}
