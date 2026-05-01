package com.pages;

import org.openqa.selenium.By;

import com.base.BasePage;

public class PimPage extends BasePage {
  private By tableHeaders = By.xpath("//div[@role='columnheader']");
  private By employeeNameInput = By.xpath("//label[text()='Employee Name']/ancestor::div[contains(@class,'oxd-input-group')]//input");
  private By employeeIdInput = By.xpath("//label[text()='Employee Id']/ancestor::div[contains(@class,'oxd-input-group')]//input");
  private By employmentStatus = By.xpath("//label[text()='Employment Status']/ancestor::div[contains(@class,'oxd-input-group')]//div[@class='oxd-select-text-input']");
  private By searchButton = By.xpath("//button[@type='submit']");
  private By resultsText = By.xpath("//div[contains(@class,'orangehrm-horizontal-padding')]//span[contains(@class,'oxd-text--span')]");
  private By noRecordsFound = By.xpath("//span[text()='No Records Found']");

  public PimPage() {
    super();
  }

  public java.util.List<String> getColumnHeaders() {
    waitFor(tableHeaders);
    return driver.findElements(tableHeaders).stream()
        .map(e -> e.getText())
        .toList();
  }

  public void searchByName(String name) {
    type(employeeNameInput, name);
    click(searchButton);
  }

  public void searchById(String id) {
    type(employeeIdInput, id);
    click(searchButton);
  }

  public void filterByEmploymentStatus(String status) {
    selectOption(employmentStatus, status);
    click(searchButton);
  }

  public String getResultsText() {
    return waitFor(resultsText).getText();
  }

  public boolean isNoRecordsFound() {
    waitFor(noRecordsFound);
    return true;
  }
}
