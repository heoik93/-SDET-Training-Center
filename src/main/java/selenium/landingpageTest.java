package selenium;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class landingpageTest {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		
		//Data
		String FormAirport = "HBX";
		String ToAirport = "CMB";
		int DepartMonth = 4;
		int DepartDay = 16;

		
		//연습사이트
		driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
		
		//1st Step
		driver.findElement(By.cssSelector("#ctl00_mainContent_ddl_originStation1_CTXTaction")).click();
		driver.findElement(By.xpath("(//a[@value='"+FormAirport+"'])[1]")).click();
		driver.findElement(By.xpath("(//a[@value='"+ToAirport+"'])[2]")).click();
		driver.findElement(By.xpath("//td[@data-month='"+DepartMonth+"']/a[contains(.,'"+DepartDay+"')]")).click();
		driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency")).click();
		driver.findElement(By.cssSelector("option[value='AED']")).click();
		driver.findElement(By.id("ctl00_mainContent_btn_FindFlights")).click();
		System.out.println("First Step Passed");
		
		//2nd Step
		driver.findElement(By.id("autosuggest")).sendKeys("ko");
		List<WebElement> countrySelects = driver.findElements(By.cssSelector("a.ui-corner-all"));
		for(WebElement countrySelect :countrySelects)
		{
			if(countrySelect.getText().equalsIgnoreCase("Korea, Republic of"))
			{
				countrySelect.click();
				break;
			}
		}
		System.out.println("Second Step Passed");
		driver.close();		
		
		
	}

}
