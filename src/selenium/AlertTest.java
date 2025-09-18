package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AlertTest {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");

		// Input_AlertMSG
		String userName = "AlertMSG";
		driver.findElement(By.id("name")).sendKeys(userName);

		String alertmessage = "Hello " + userName + ", share this practice page and share your knowledge";

		// Alert
		driver.findElement(By.id("alertbtn")).click();
		String meesageCheck = driver.switchTo().alert().getText();

		if (meesageCheck.equals(alertmessage)) {
			driver.switchTo().alert().accept();
			System.out.println("Pass");
		} else {
			driver.switchTo().alert().dismiss();
			System.out.println("Fail");
		}

	}

}
