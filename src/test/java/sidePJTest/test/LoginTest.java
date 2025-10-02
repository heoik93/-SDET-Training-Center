package sidePJTest.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import sidePJTest.base.BaseTest;
import sidePJTest.pages.LoginPage;
import sidePJTest.utils.ExcelReader;

public class LoginTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] loginDataProvider() throws IOException {
        Properties config = new Properties();
        String configPath = new File("src/resources/config.properties").getAbsolutePath();
        FileInputStream fis = new FileInputStream(configPath);
        config.load(fis);
        fis.close();
        String filePath = "src/main/java/sidePJTest/data/LoginDate.xlsx";
        String sheetName = "LoginData";
        return ExcelReader.getSheetData(filePath, sheetName);
    }
	
    @Test(dataProvider = "loginData")
    public void testValidLogin(String no, String username, String password, String email, String userType) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage();
        loginPage.login(username, password);

        boolean isLoggedIn = false;

        try {
            // 로그인 성공 여부 확인
            isLoggedIn = driver.findElement(By.cssSelector("ul[class='menu1'] a")).isDisplayed();
        } catch (Exception e) {
            // 로그인 실패 시 alert 대기 및 처리
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
                Alert alert = wait.until(ExpectedConditions.alertIsPresent());
                System.out.println("⚠️ 경고창 발생: " + alert.getText());
                alert.accept(); // alert 닫기
            } catch (TimeoutException te) {
                System.out.println("❌ 3초 내에 alert가 뜨지 않음");
            }
            isLoggedIn = false;
        }

        switch (userType.toLowerCase()) {
            case "nomaluser":
                Assert.assertTrue(isLoggedIn, "nomaluser가 로그인에 실패하였습니다. 로그인실패 유저명 : " + username);
                break;
            case "loginfailuser":
                Assert.assertFalse(isLoggedIn, "failuser가 로그인에 성공하였습니다. 로그인성공 유저명: " + username);
                break;
        }
    }
}