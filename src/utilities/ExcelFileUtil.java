package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil {
	Workbook wb;
	// constructor for reading excelpath
	public ExcelFileUtil(String excelPath) throws Throwable
	{
		FileInputStream fi = new FileInputStream(excelPath);
		wb = WorkbookFactory.create(fi);
		
	}
	
	//method for counting no rows in a sheets
	
	public int rowCount(String sheetName)
	{
		return wb.getSheet(sheetName).getLastRowNum();
		
	}
	
	// method for count cell in a row
	
	public int cellCount(String sheetname)
	{
		return wb.getSheet(sheetname).getRow(0).getLastCellNum();
	}
	
	
	//method to read cell data
	public String readData(String sheetName,int row,int cell)
	{
		String data="";
		if(wb.getSheet(sheetName).getRow(row).getCell(cell).getCellType()==Cell.CELL_TYPE_NUMERIC)
		{
			int celldata = (int)wb.getSheet(sheetName).getRow(row).getCell(cell).getNumericCellValue();
			data = String.valueOf(celldata);
		}else
		{
		data = wb.getSheet(sheetName).getRow(row).getCell(cell).getStringCellValue();
		}
		return data;
	}
	
	// method to set cell data
	public void writeData(String sheetName,int row,int cell,String status,String writeexcel) throws Throwable
	{
		// get sheet from wb
		Sheet ws = wb.getSheet(sheetName);
		// get row from sheet
		Row rowNum = ws.getRow(row);
		//create a cell in a row
		Cell newCell = rowNum.createCell(cell);
		newCell.setCellValue(status); 
		if(status.equalsIgnoreCase("pass"))
		{
			CellStyle style = wb.createCellStyle();
			Font font = wb.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rowNum.getCell(cell).setCellStyle(style);
			
		}else if(status.equalsIgnoreCase("fail"))
		{
			CellStyle style = wb.createCellStyle();
			Font font = wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rowNum.getCell(cell).setCellStyle(style);
		}else if(status.equalsIgnoreCase("Blocked"))
		{
			CellStyle style = wb.createCellStyle();
			Font font = wb.createFont();
			font.setColor(IndexedColors.BLUE.getIndex());
			font.setBold(true);
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rowNum.getCell(cell).setCellStyle(style);
		}
		
		FileOutputStream fo = new FileOutputStream(writeexcel);
		wb.write(fo);
			
		
	}

}
