package com.base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.utils.DriverManager;

public abstract class BasePage {
  protected WebDriver driver;
  protected WebDriverWait wait;

  public BasePage(){
    this.driver = DriverManager.getDriver();
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
  }

  protected WebElement waitFor(By locator){
    return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
  }
  protected void click(By locator){
    waitFor(locator).click();
  }
  protected void type(By locator, String text){
    waitFor(locator).sendKeys(text);
  }
  public void waitForUrlContains(String fragment){
    wait.until(ExpectedConditions.urlContains(fragment));
  }
  protected void selectOption(By dropdown, String option){
    click(dropdown);
    By optionLocator = By.xpath("//div[@role='option']//span[text()='" + option + "']");
    waitFor(optionLocator).click();
  }
}
