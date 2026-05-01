package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.base.BasePage;

public class LoginPage extends BasePage{
  private By usernameInput = By.name("username");
  private By passwordInput = By.name("password");
  private By submitBtn = By.xpath("//button[@type='submit']");
  private By alertError = By.className("oxd-alert-content-text");
  private By inputErrors = By.className("oxd-input-field-error-message");


  public LoginPage(){
    super();
  }
  public void loginAs(String username, String password){
    type(usernameInput, username);
    type(passwordInput, password);
    click(submitBtn);
  }
  public String getErrorMessage(){
    return waitFor(alertError).getText();
  }
  public List<String> getValidationErrors(){
    waitFor(inputErrors);
    return driver.findElements(inputErrors).stream().map(WebElement::getText).toList();
  }
}
