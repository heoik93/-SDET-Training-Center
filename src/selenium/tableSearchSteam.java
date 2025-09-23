package selenium;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class tableSearchSteam {

	public static void main(String[] args) throws InterruptedException, IOException {

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/upload-download-test/index.html");

		driver.findElement(By.id("downloadButton")).click();

		// 엑셀편집하기
		Thread.sleep(3000);
		String localdownloadpath = "C:\\Users\\shell\\Downloads\\";
		String excelfilename = "download.xlsx";

		FileInputStream fis = new FileInputStream(localdownloadpath + excelfilename);
		try (XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
			Sheet sheet = workbook.getSheetAt(0);

			// 상품 & 변경할 값
			String needItem = "Apple";
			double modifPrice = 600;

			// 셀 내용확인
			int i = 0; // row_number

			for (i = 0; i < 10; i++) {
				Row row = sheet.getRow(i);
				Cell cell = row.getCell(1);
				String item = cell.getStringCellValue();
				if (needItem.equals(item)) {
					double price = row.getCell(3).getNumericCellValue();
					System.out.println(item + "의 현재 가격은" + price + "입니다.");

					// 셀 내용변경
					row.getCell(3).setCellValue(modifPrice);
					System.out.println(item + "의 가격을" + modifPrice + "으로 변경했습니다.");

					break;
				}

			}
			fis.close();

			// 변경사항저장
			FileOutputStream fos = new FileOutputStream(localdownloadpath + excelfilename);
			workbook.write(fos);
			fos.close();
			workbook.close();
		}

		// 업로드
		WebElement upload = driver.findElement(By.id("fileinput"));
		upload.sendKeys(localdownloadpath + excelfilename);

	}
}
