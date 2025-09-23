package sidePJTest.java.utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public static String getCellData(String filePath, String sheetName, int rowNum, int colNum) throws IOException {
	    FileInputStream fis = new FileInputStream(filePath);
	    try (XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
	        Sheet sheet = workbook.getSheet(sheetName);
	        Row row = sheet.getRow(rowNum);
	        Cell cell = row.getCell(colNum);
	        return cell.getStringCellValue();
	    }
	}		

}

