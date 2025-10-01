package sidePJTest.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import sidePJTest.utils.ScreenshotUtil;

public class BaseTest {

    protected WebDriver driver;
    protected Properties config;
    
    @BeforeMethod
    public void setUp() {
        config = new Properties();
        try {
            FileInputStream fis = new FileInputStream("src/resources/config.properties");
            config.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String browser = config.getProperty("browser");
        driver = DriverManager.getDriver(browser); 
        driver.get(config.getProperty("url"));
        //driver.manage().window().maximize();
    }

    
    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            ScreenshotUtil.capture(driver, "Failure Screenshot: " + result.getName());
        }
        driver.quit();
    	
        if (driver != null) {
            driver.quit();
        }
    }
}