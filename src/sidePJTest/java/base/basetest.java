package sidePJTest.java.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;
    protected Properties config;
    
    @BeforeMethod
    public void setUp() {
        config = new Properties();
        try {
            FileInputStream fis = new FileInputStream("src/sidePJTest/resources/config.properties");
            config.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String browser = config.getProperty("browser");
        driver = DriverManager.getDriver(browser); 
        driver.get(config.getProperty("url"));
    }

    
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}