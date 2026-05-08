package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.base.Base;

public class FormPage extends Base {
  private By nameInput = By.id("firstName");
  private By lastNameInput = By.id("lastName");
  private By emailInput = By.id("userEmail");
  private By genderRadios = By.cssSelector("#genterWrapper label");
  private By hobbiesCheckboxes = By.cssSelector("#hobbiesWrapper label");
  private By phoneInput = By.id("userNumber");
  private By subjectInput = By.id("subjectsInput");
  private By addressInput = By.id("currentAddress");
  private By stateInput = By.id("react-select-3-input");
  private By cityInput = By.id("react-select-4-input");
  private By submitBtn = By.id("submit");
  private By successModalTitle = By.id("example-modal-sizes-title-lg");
  private By formTitle = By.className("practice-form-wrapper");
  private By headerLogo = By.xpath("//header//a");


  
  public FormPage(){
    super();
  }
  public void fillNameLastName(String name, String lastName){
    if (name != null) type(nameInput, name);
    if (lastName != null) type(lastNameInput, lastName);
  }
  public void fillEmail(String email){
    if (email != null) type(emailInput, email);
  }
  public void selectGender(String gender){
    if (gender != null) selectRadio(genderRadios, gender);
  }
  public void fillMobile(String mobile){
    if (mobile != null) type(phoneInput, mobile);
  }
  public void selectHobbies(List<String> hobbies){
    selectCheckbox(hobbiesCheckboxes, hobbies);
  }
  public void fillSubjects(String subject){
    type(subjectInput, subject);
    waitFor(subjectInput).sendKeys(org.openqa.selenium.Keys.ENTER);
  }
  public void fillAddress(String address){
    type(addressInput, address);
  }
  public void selectStateCity(String state, String city){
    type(stateInput, state);
    waitFor(stateInput).sendKeys(org.openqa.selenium.Keys.ENTER);
    type(cityInput, city);
    waitFor(cityInput).sendKeys(org.openqa.selenium.Keys.ENTER);
  }
  public void clickSubmit(){
    waitFor(submitBtn).sendKeys(org.openqa.selenium.Keys.ENTER);
  }

  public boolean isSuccessModalDisplayed(){
    return waitFor(successModalTitle).isDisplayed();
  }

  public boolean isSuccessModalHidden(){
    try {
      // Esperar hasta que el elemento sea invisible o desaparezca del DOM (útil por las animaciones 'fade' del modal)
      return wait.until(ExpectedConditions.invisibilityOfElementLocated(successModalTitle));
    } catch (Exception e) {
      return false; // Si se acaba el tiempo y sigue visible
    }
  }

  public boolean isFormTitleVisible() {
    return waitFor(formTitle).isDisplayed();
  }

  public boolean areGenderOptionsPresent() {
    return !driver.findElements(genderRadios).isEmpty();
  }

  public boolean areHobbiesOptionsPresent() {
    return !driver.findElements(hobbiesCheckboxes).isEmpty();
  }

  public boolean isSubmitBtnVisible() {
    return waitFor(submitBtn).isDisplayed();
  }

  public void clickHeaderLogo() {
    click(headerLogo);
  }

  public String getFirstNameValue() {
    return waitFor(nameInput).getAttribute("value");
  }

  public void fillMandatoryFields(String name, String lastName, String gender, String mobile){
    fillNameLastName(name, lastName);
    selectGender(gender);
    fillMobile(mobile);
  }

  
  public void fillForm(String name, String lastName, String email, String gender, String mobile, List<String> hobbies, String subject, String address, String state, String city){
    fillNameLastName(name, lastName);
    fillEmail(email);
    selectGender(gender);
    fillMobile(mobile);
    selectHobbies(hobbies);
    fillSubjects(subject);
    fillAddress(address);
    selectStateCity(state, city);
  }
}
 