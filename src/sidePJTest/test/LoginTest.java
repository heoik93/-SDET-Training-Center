package sidePJTest.test;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import sidePJTest.java.base.BaseTest;
import sidePJTest.java.pages.LoginPage;

public class LoginTest extends BaseTest {


	@Test(dataProvider = "loginData")
	public void testValidLogin(String username, String password) throws IOException {
		
		String[] credentials = TestDataLoader.getLoginCredentials(1); // 원하는 유저 인덱스
		username = credentials[0];
		password = credentials[1];


		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.openLoginPage();
		loginPage.login(username, password);
		
		//로그아웃버튼 나오면 성공
		Assert.assertTrue(driver.findElement(By.cssSelector("ul[class='menu1'] a")).isDisplayed());
		System.out.println(username);
	
	}

	//@Test(dataProvider = "loginData")
	//public void testInValidLogin(String username, String password) {
	
	
	
	}


