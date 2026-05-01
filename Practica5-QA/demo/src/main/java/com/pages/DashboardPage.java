package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.base.BasePage;

public class DashboardPage extends BasePage {
  private By quickLaunchItem = By.className("orangehrm-quick-launch-card");
  private By timeAtWorkWidget = By.className("orangehrm-attendance-card");

  public DashboardPage(){
    super();
  }
  public void clickQuickLaunch(String name){
    waitFor(quickLaunchItem);
    driver.findElements(quickLaunchItem)
    .stream()
    .filter(e -> e.getText().contains(name))
    .findFirst()
    .ifPresent(WebElement::click);  
  }
  public boolean isWidgetVisible(){
    return waitFor(timeAtWorkWidget).isDisplayed();
  }
  public void gotoModule(String name){
    By link = By.xpath("//span[text()='" + name + "']");
    waitFor(link).click();
  }
}
