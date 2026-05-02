package tests;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pages.LoginPage;
import com.utils.DriverManager;

public class LoginTest {
  WebDriver driver;
  @BeforeMethod
  void setup(){
    driver = DriverManager.getDriver();
    driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
  }
  @Test
  void loginSucccess(){
    LoginPage loginPage = new LoginPage();
    loginPage.loginAs("Admin", "admin123");
    loginPage.waitForUrlContains("dashboard");

    Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
  }
  @Test
  void loginInvalidPassword(){
    LoginPage loginPage = new LoginPage();
    loginPage.loginAs("Admin", "wrongpass");

    String errorMsg = loginPage.getErrorMessage();
    Assert.assertEquals(errorMsg, "Invalid credentials");

  }
  @Test
  void loginEmptyFields(){
    LoginPage loginPage = new LoginPage();
    loginPage.loginAs("", "");

    List<String> errors = loginPage.getValidationErrors();
    Assert.assertEquals(errors.size(), 2);
    Assert.assertTrue(errors.stream().allMatch(e -> e.equals("Required")));
    
  }
  @Test
  void emptyUsrname(){
    LoginPage loginPage = new LoginPage();
    loginPage.loginAs("", "123");
    Assert.assertEquals(loginPage.getValidationErrors().get(0),"Required");
   
  }
  @Test
  void emptyPassword(){
    LoginPage loginPage = new LoginPage();
    loginPage.loginAs("", "123");

    Assert.assertEquals(loginPage.getValidationErrors().get(0), "Required");
    
  }
  @AfterMethod
  void quit(){
    DriverManager.quitDriver();
  }
}
