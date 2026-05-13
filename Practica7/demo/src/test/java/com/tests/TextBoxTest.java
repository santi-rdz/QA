package com.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.utils.DriverManager;
import com.pages.TextBoxPage;

public class TextBoxTest {

    private TextBoxPage textBoxPage;

    @BeforeClass
    public void setUp() {
        textBoxPage = new TextBoxPage();
        textBoxPage.open();
    }

    @AfterClass
    public void tearDown() {
        DriverManager.closeDriver();
    }

    @Test
    public void testPageLoads() {
        Assert.assertTrue(textBoxPage.getCurrentUrl().contains("text-box"));
    }

    @Test(dependsOnMethods = "testPageLoads")
    public void testFormSubmission() {
        textBoxPage.enterFullName("Juan Perez");
        textBoxPage.enterEmail("juan@example.com");
        textBoxPage.enterCurrentAddress("Calle 123");
        textBoxPage.enterPermanentAddress("Av. Principal 456");
        textBoxPage.clickSubmit();
        Assert.assertTrue(textBoxPage.isOutputDisplayed());
    }

    @Test(dependsOnMethods = "testFormSubmission")
    public void testOutputContainsName() {
        Assert.assertTrue(textBoxPage.getOutputName().contains("Juan Perez"));
    }

    @Test(dependsOnMethods = "testFormSubmission")
    public void testOutputContainsEmail() {
        Assert.assertTrue(textBoxPage.getOutputEmail().contains("juan@example.com"));
    }
}
