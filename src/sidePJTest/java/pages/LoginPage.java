package sidePJTest.java.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void openLoginPage() {
        driver.get("http://laundry365.store:8080/");
        driver.findElement(By.xpath("//strong[contains(text(),'로그인')]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("subMenuText")));
    }

    public void login(String username, String password) {
        driver.findElement(By.id("id")).sendKeys(username);
        driver.findElement(By.id("pwd")).sendKeys(password);
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }
}