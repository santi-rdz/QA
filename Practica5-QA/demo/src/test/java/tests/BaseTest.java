package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.pages.LoginPage;
import com.utils.DriverManager;

public class BaseTest {
  protected WebDriver driver;
  
  @BeforeTest
  void setup(){
    driver = DriverManager.getDriver();
    driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    LoginPage loginPage = new LoginPage();
    loginPage.loginAs("Admin", "admin123");
    loginPage.waitForUrlContains("dashboard");
  }

  @AfterTest
  void quit(){
    DriverManager.quitDriver();
  }
}
