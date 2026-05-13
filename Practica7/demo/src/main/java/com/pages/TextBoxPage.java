package com.pages;

import org.openqa.selenium.By;
import com.base.BasePage;

public class TextBoxPage extends BasePage {

    private final By fullNameField         = By.id("userName");
    private final By emailField            = By.id("userEmail");
    private final By currentAddressField   = By.id("currentAddress");
    private final By permanentAddressField = By.id("permanentAddress");
    private final By submitButton          = By.id("submit");
    private final By outputSection         = By.id("output");
    private final By outputName            = By.id("name");
    private final By outputEmail           = By.id("email");

    public void open() {
        driver.get("https://demoqa.com/text-box");
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void enterFullName(String name) {
        type(fullNameField, name);
    }

    public void enterEmail(String email) {
        type(emailField, email);
    }

    public void enterCurrentAddress(String address) {
        type(currentAddressField, address);
    }

    public void enterPermanentAddress(String address) {
        type(permanentAddressField, address);
    }

    public void clickSubmit() {
        click(submitButton);
    }

    public boolean isOutputDisplayed() {
        return waitFor(outputSection).isDisplayed();
    }

    public String getOutputName() {
        return getText(outputName);
    }

    public String getOutputEmail() {
        return getText(outputEmail);
    }
}
