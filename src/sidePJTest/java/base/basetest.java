package sidePJTest.java.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class basetest {

	@BeforeMethod
	public void setUp() {
	    String browser = config.getProperty("browser"); // config.properties에서 읽기
	    driver = DriverManager.getDriver(browser);
	    driver.manage().window().maximize();
	    driver.get(config.getProperty("url"));

	@AfterMethod
	public void tearDown() {
	    if (driver != null) {
	        driver.quit();
	    }
	}


	}

}
