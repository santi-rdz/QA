package com.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.utils.DriverManager;
import com.pages.LoginPage;

public class LoginTest {

    private LoginPage loginPage;

    @BeforeClass
    public void setUp() {
        loginPage = new LoginPage();
        loginPage.open();
    }

    @AfterClass
    public void tearDown() {
        DriverManager.closeDriver();
    }

    @Test
    public void testLoginPageLoads() {
        Assert.assertTrue(loginPage.getCurrentUrl().contains("login"));
    }

    @Test(dependsOnMethods = "testLoginPageLoads")
    public void testInvalidLogin() {
        loginPage.enterUsername("wrongUser");
        loginPage.enterPassword("wrongPass");
        loginPage.clickLogin();
        Assert.assertFalse(loginPage.getCurrentUrl().contains("profile"));
    }
}
