package com.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.utils.DriverManager;
import com.pages.HomePage;

public class HomeTest {

    private HomePage homePage;

    @BeforeClass
    public void setUp() {
        homePage = new HomePage();
    }

    @AfterClass
    public void tearDown() {
        DriverManager.closeDriver();
    }

    @Test
    public void testHomePageLoads() {
        homePage.open();
        Assert.assertTrue(homePage.getCurrentUrl().contains("demoqa.com"));
    }

    @Test(dependsOnMethods = "testHomePageLoads")
    public void testNavigateToElements() {
        homePage.goToElements();
        Assert.assertTrue(homePage.getCurrentUrl().contains("elements"));
    }
}
