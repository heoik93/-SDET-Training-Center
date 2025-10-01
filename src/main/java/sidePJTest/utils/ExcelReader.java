package sidePJTest.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public static Object[][] getSheetData(String filePath, String sheetName) throws IOException {
	    FileInputStream fis = new FileInputStream(filePath);
	    try (Workbook workbook = new XSSFWorkbook(fis)) {
	        Sheet sheet = workbook.getSheet(sheetName);
	        int rowCount = sheet.getPhysicalNumberOfRows();
	        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

	        List<Object[]> rows = new ArrayList<>();

	        for (int i = 1; i < rowCount; i++) {
	            Row row = sheet.getRow(i);
	            Cell noCell = row.getCell(0); // No 셀

	            if (noCell == null || noCell.getCellType() == CellType.BLANK || noCell.toString().trim().isEmpty()) {
	                break; 
	            }

	            Object[] rowData = new Object[colCount];
	            for (int j = 0; j < colCount; j++) {
	                Cell cell = row.getCell(j);
	                rowData[j] = getCellValue(cell);
	            }
	            rows.add(rowData);
	        }

	        // List → Object[][]
	        Object[][] data = new Object[rows.size()][colCount];
	        for (int i = 0; i < rows.size(); i++) {
	            data[i] = rows.get(i);
	        }
	        return data;
	    }
	}

    private static Object getCellValue(Cell cell) {
        if (cell == null) {
			return "";
		}
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                } else {
                    return String.valueOf((long) cell.getNumericCellValue()); // 숫자 → 문자열
                }
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            default:
                return cell.toString();
        }
    }
}