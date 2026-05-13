package com.pages;

import org.openqa.selenium.By;
import com.base.BasePage;

public class LoginPage extends BasePage {

    private final By usernameField = By.id("userName");
    private final By passwordField = By.id("password");
    private final By loginButton   = By.id("login");

    public void open() {
        driver.get("https://demoqa.com/login");
    }

    public void enterUsername(String username) {
        type(usernameField, username);
    }

    public void enterPassword(String password) {
        type(passwordField, password);
    }

    public void clickLogin() {
        click(loginButton);
    }
}
