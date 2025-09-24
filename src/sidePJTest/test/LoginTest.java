package sidePJTest.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import sidePJTest.java.base.BaseTest;
import sidePJTest.java.pages.LoginPage;
import sidePJTest.java.utils.ExcelReader;

public class LoginTest extends BaseTest {

	@DataProvider(name = "loginData")
	public Object[][] loginDataProvider() throws IOException {
	    Properties config = new Properties();
	    FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
	    config.load(fis);
	    fis.close();

	    String filePath = config.getProperty("loginDataPath");
	    String sheetName = config.getProperty("sheetName");
	    return ExcelReader.getSheetData(filePath, sheetName);
	}

	@Test(dataProvider = "loginData")
	public void testValidLogin(String username, String password) {
	    LoginPage loginPage = new LoginPage(driver);
	    loginPage.openLoginPage();
	    loginPage.login(username, password);

	    Assert.assertTrue(driver.findElement(By.cssSelector("ul[class='menu1'] a")).isDisplayed());
	    System.out.println("로그인 성공: " + username);
	}

	//@Test(dataProvider = "loginData")
	//public void testInValidLogin(String username, String password) {
	
	
	
	}


