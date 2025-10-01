package selenium;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.SoftAssert;

public class urlCheckTest {

	public static void main(String[] args) throws MalformedURLException, IOException {
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		
		
		//get all URL
		List<WebElement> links = driver.findElements(By.cssSelector("li[class='gf-li'] a"));
		
		//SoftAssert=모두 확인후 결과취합
	      SoftAssert a =new SoftAssert();

	      for(WebElement link : links)
	      {
	          String url= link.getAttribute("href");
	          
	          //중요한부분
	          HttpURLConnection   conn= (HttpURLConnection)new URL(url).openConnection();
	          conn.setRequestMethod("HEAD");
	          conn.connect();
	          int respCode = conn.getResponseCode();
	          a.assertTrue(respCode<400, link.getText()+"의 링크는 연결할 수 없습니다." +respCode);
	      }

	     

	      a.assertAll();
}}
