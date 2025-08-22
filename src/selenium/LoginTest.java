package selenium;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;



public class LoginTest {
	
	
	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		//Data
		String userName = "rahul";
		String failPassword = "fail0123";
		
		String userEmail = "login@gmail.com";
		String userPhoneNumber = "000-0000-0000";
		String userPassword = "rahulshettyacademy";
		
		
		//테스트용 사이트
		driver.get("https://rahulshettyacademy.com/locatorspractice/");
		
		//로그인실패
		driver.findElement(By.id("inputUsername")).sendKeys(userName);
		driver.findElement(By.cssSelector("input[placeholder='Password']")).sendKeys(failPassword);
		driver.findElement(By.cssSelector("button.submit.signInBtn")).click();
		
		//명시적대기
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector( "p.error")));
		
		//로그인실패체크
		String loginErrorMsg = driver.findElement(By.cssSelector( "p.error")).getText();
		Assert.assertEquals(loginErrorMsg, "* Incorrect username or password");
		
		//패스워드찾기
		driver.findElement(By.xpath("//a[.='Forgot your password?']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form[action='#'] h2")));
		driver.findElement(By.cssSelector("input[placeholder='Name']")).sendKeys(userName);
		driver.findElement(By.cssSelector("input[placeholder='Email']")).sendKeys(userEmail);
		driver.findElement(By.cssSelector("input[placeholder='Phone Number']")).sendKeys(userPhoneNumber);		
		driver.findElement(By.cssSelector("button.reset-pwd-btn")).click();
		String successMsg = driver.findElement(By.cssSelector("p.infoMsg")).getText();
		Assert.assertEquals(successMsg, "Please use temporary password 'rahulshettyacademy' to Login.");
		driver.findElement(By.cssSelector("button.go-to-login-btn")).click();
		
		
		//로그인성공
		driver.findElement(By.id("inputUsername")).sendKeys(userName);
		driver.findElement(By.cssSelector("input[placeholder='Password']")).sendKeys(userPassword);
		driver.findElement(By.id("chkboxOne")).isSelected();
		driver.findElement(By.id("chkboxTwo")).isSelected();
		driver.findElement(By.cssSelector("button.submit.signInBtn")).click();
		
		Thread.sleep(2000);
		String successUserName = driver.findElement(By.cssSelector("div[class='login-container'] h2")).getText();
		Assert.assertEquals(successUserName, "Hello "+userName+",");
		String successLoginMsg = driver.findElement(By.cssSelector("div[class='login-container'] p")).getText();
		Assert.assertEquals(successLoginMsg, "You are successfully logged in.");
		System.out.println("Test is Passed");
		driver.close();
	}

}
