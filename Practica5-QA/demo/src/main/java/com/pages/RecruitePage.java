package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.base.BasePage;

public class RecruitePage extends BasePage {
  private By tableHeaders = By.xpath("//div[@role='columnheader']");
  private By vacancyDropdown = By.xpath("//label[text()='Vacancy']/ancestor::div[contains(@class,'oxd-input-group')]//div[@class='oxd-select-text-input']");
  private By keywordsInput = By.xpath("//input[@placeholder='Enter comma seperated words...']");
  private By searchButton = By.xpath("//button[@type='submit']");
  private By resultsText = By.xpath("//div[contains(@class,'orangehrm-horizontal-padding')]//span[contains(@class,'oxd-text--span')]");
  private By noRecordsFound  = By.xpath("//span[text()='No Records Found']");

  public RecruitePage() {
    super();
  }

  public void visitTab(String name) {
    By tab = By.xpath("//a[text()='" + name + "']");
    waitFor(tab).click();
  }

  public List<String> getColumnHeaders() {
    waitFor(tableHeaders);
    return driver.findElements(tableHeaders).stream()
        .map(WebElement::getText)
        .toList();
  }

  public void filterByVacancy(String vacancy) {
    selectOption(vacancyDropdown, vacancy);
    click(searchButton);
  }

  public void searchByKeyword(String keyword) {
    type(keywordsInput, keyword);
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
