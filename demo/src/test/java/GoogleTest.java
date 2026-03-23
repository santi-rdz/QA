import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GoogleTest {
  @Test
  public void OpenGoogle(){
    WebDriverManager.chromedriver().setup();
    WebDriver driver = new ChromeDriver();

    driver.get("http://localhost:5173/login");

    //locators
    WebElement loginInput = driver.findElement(By.name("email"));
    WebElement btn_submit = driver.findElement(By.className("btn-submit"));
    loginInput.sendKeys("Selenium WebDriver");
  }

}