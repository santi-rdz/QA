package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.pages.LoginPage;
import com.utils.DriverManager;

public class BaseTest {
  protected WebDriver driver;
  
  @BeforeMethod
  void setup(){
    driver = DriverManager.getDriver();
    driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    LoginPage loginPage = new LoginPage();
    loginPage.loginAs("Admin", "admin123");
    loginPage.waitForUrlContains("dashboard");
  }

  @AfterMethod
  void quit(){
    DriverManager.quitDriver();
  }
}
