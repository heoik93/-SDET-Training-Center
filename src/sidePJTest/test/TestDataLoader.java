package sidePJTest.test;

import java.io.IOException;

import sidePJTest.java.utils.ExcelReader;

public class TestDataLoader {
    public static String[] getLoginCredentials(int rowIndex) throws IOException {
        String filePath = "src/test/resources/LoginData.xlsx";
        String sheetName = "LoginData";

        String username = ExcelReader.getCellData(filePath, sheetName, rowIndex, 1);
        String password = ExcelReader.getCellData(filePath, sheetName, rowIndex, 2);

        return new String[] { username, password };
    }
}
