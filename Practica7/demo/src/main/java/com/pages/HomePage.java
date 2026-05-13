package com.pages;

import org.openqa.selenium.By;
import com.base.BasePage;

public class HomePage extends BasePage {

    private final By elementsCard = By.xpath("//h5[text()='Elements']");

    public void open() {
        driver.get("https://demoqa.com");
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void goToElements() {
        click(elementsCard);
    }
}
