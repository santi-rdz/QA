package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pages.DashboardPage;


public class Dashboard extends BaseTest {
  @Test
  void dashboardDisplayed(){
    Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
  }
  @Test
  void navigateToQuickLaunchItem(){
    DashboardPage dashboardPage =  new DashboardPage();
    dashboardPage.clickQuickLaunch("Leave");
    dashboardPage.waitForUrlContains("leave");
    Assert.assertTrue(driver.getCurrentUrl().contains("leave"));

  }
  @Test
  void visibleWidget(){
    DashboardPage dashboardPage = new DashboardPage();
    Assert.assertTrue(dashboardPage.isWidgetVisible(), "Widget Time is not visible");
  }
  @Test 
  void visitPimModule(){
    DashboardPage dashboardPage = new DashboardPage();
    dashboardPage.gotoModule("PIM");
    dashboardPage.waitForUrlContains("pim");
    Assert.assertTrue(driver.getCurrentUrl().contains("pim"));
  }
  @Test
  void visitAdminModule(){
    DashboardPage dashboardPage = new DashboardPage();
    dashboardPage.gotoModule("Admin");
    dashboardPage.waitForUrlContains("admin");
    Assert.assertTrue(driver.getCurrentUrl().contains("admin"));
  }
}
