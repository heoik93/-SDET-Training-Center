package sidePJTest.java.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class driverManager {
	public static WebDriver getDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                return new ChromeDriver();
            case "edge":
                return new EdgeDriver();
            case "firefox":
                return new FirefoxDriver();
            case "safari":
                return new SafariDriver();
            default:
                throw new IllegalArgumentException("지원하지 않는 브라우저: " + browser);
        }
    }

}
