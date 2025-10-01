package selenium;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class ecommerceAppTest {
	
	static WebDriver driver;
	static List<WebElement> products;
	static List<WebElement> addButtons;
	static List<String> NeedList;
	static String matchItem;
	static int matchItemSize;
	static String[] Needitems = {"Carrot","Corn","Tomato"};

	public static void main(String[] args) throws InterruptedException {
		
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		//연습 사이트
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
		Thread.sleep(3000);

		matchItemSize=0;
		additems(driver, Needitems);
		driver.findElement(By.cssSelector("img[alt='Cart']")).click();
		driver.findElement(By.cssSelector("div[class='cart-preview active'] button[type='button']")).click();
		
		checkItem();
		driver.close();
		System.out.println("Test is successed");
				
	}
	
	//필요한 아이템 추가하기
	public static void additems(WebDriver driver, String[] Needitems)
	{
		
	 NeedList = Arrays.asList(Needitems);
	 products = driver.findElements(By.cssSelector("h4.product-name"));
     addButtons = driver.findElements(By.xpath("//div/button[text()='ADD TO CART']"));
		
     for (int i = 0;i<products.size();i++)
     	{
     matchItem = products.get(i).getText().split("-")[0].trim();
     if(NeedList.contains(matchItem))
     		{
    	 addButtons.get(i).click();
    	 matchItemSize++;
     		}
     	}
     }

	//장바구니확인
	public static void checkItem() 
	{
		List<WebElement> buyItem = driver.findElements(By.cssSelector("p.product-name")).stream().filter(e -> !e.getText().trim().isEmpty()).toList();
		for(int i = 0;i<buyItem.size();i++) {
			String a= buyItem.get(i).getText().split("-")[0].trim();
			List<String> needList = Arrays.asList(Needitems); 
			Assert.assertTrue(needList.contains(a),  "장바구니 아이템이 기대값과 다릅니다: " + a);
			}
		Assert.assertEquals(buyItem.size(), Needitems.length, "장바구니에 담긴 아이템 수가 예상과 다릅니다.");
	}
	
}
