package com.base;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.utils.DriverManager;

public class Base {
  protected WebDriver driver;
  protected WebDriverWait wait;

  public Base(){
    this.driver = DriverManager.getDriver();
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
  }
  protected WebElement waitFor(By locator){
    return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
  }
  protected List<WebElement> waitForAll(By locator){
    return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
  }
  protected void click(By locator){
    waitFor(locator).click();
  }
  protected void type(By locator, String text){
    waitFor(locator).sendKeys(text);
  }
  public void waitUntilUrlContains(String fragment){
    wait.until(ExpectedConditions.urlContains(fragment));
  }
  public void selectRadio(By locator, String optionText){
    List<WebElement> elements = waitForAll(locator);

    for (WebElement element : elements) {
      if (element.getText().trim().equalsIgnoreCase(optionText)) {
        element.click();
        break;
      }
    }
  }

  public void selectCheckbox(By locator, List<String> options){
    List<WebElement> elements = waitForAll(locator);

    for (WebElement element : elements) {
      String labelText = element.getText().trim();
      for (String option : options) {
        if (labelText.equalsIgnoreCase(option.trim())) {
          element.click();
          break;
        }
      }
    }
  }

}
