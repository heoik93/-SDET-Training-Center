package selenium;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class landingpageTest {

	public static void main(String[] args) {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		//실제사이트 
		driver.get("https://flyasiana.com/C/KR/KO/index/");
		
		//테스트용사이트
		driver.get("https://rahulshettyacademy.com/dropdownsPractise/");

	}

}
