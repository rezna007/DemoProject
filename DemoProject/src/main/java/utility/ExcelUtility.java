package utility;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;

public class ExcelUtility {

	public static XSSFWorkbook w;
	public static XSSFSheet sh;
	public static FileInputStream f;
	public static XSSFCell Cell;
	
	public ExcelUtility() throws IOException
	{ 
		//obtaining input bytes from a Excelfile 
		 f =new FileInputStream("E:\\EclipseWS_Automation\\DemoProject\\src\\main\\resources\\Demo Excel.xlsx");
		//creating workbook instance that refers to .xls file  E:\EclipseWS_Automation\DemoProject\src\main\resources\Login Excel.xlsx
		 w=new XSSFWorkbook(f);
		//creating a Sheet object to retrieve the object  
		 sh=w.getSheet("sheet1");
	}
	public String readData(int row, int column)
	{
		//Row r=sh.getRow(row);
		//Cell c =r.getCell(column);
	   Cell = sh.getRow(row).getCell(column);
		//get the cell type 
	   CellType type=Cell.getCellType();
		switch(type)
		{
		case  STRING:
		{
		return Cell.getStringCellValue();
			
		}
		case NUMERIC:
		{
			int a=(int) Cell.getNumericCellValue();
			return String.valueOf(a);
		}
		}
		return null;
		
		}
	
}

