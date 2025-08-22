package selenium;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class landingpageTest {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		//Data
	
		
		//연습사이트
		driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
