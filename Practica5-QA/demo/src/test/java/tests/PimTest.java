package tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.pages.PimPage;

public class PimTest extends BaseTest {

  @BeforeTest
  void setup() {
    super.setup();
    driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList");
  }

  @Test
  void employeeListShowsTable() {
    PimPage pim = new PimPage();
    List<String> headers = pim.getColumnHeaders();
    Assert.assertTrue(headers.contains("Id"), "Falta columna: Id");
    Assert.assertTrue(headers.contains("First (& Middle) Name"), "Falta columna: First Name");
    Assert.assertTrue(headers.contains("Last Name"), "Falta columna: Last Name");
    Assert.assertTrue(headers.contains("Job Title"), "Falta columna: Job Title");
  }

  @Test
  void searchEmployeeByName() {
    PimPage pim = new PimPage();
    pim.searchByName("test");
    Assert.assertTrue(pim.getResultsText().contains("Records Found"));
  }

  @Test
  void filterByEmploymentStatus() {
    PimPage pim = new PimPage();
    pim.filterByEmploymentStatus("Full-Time Permanent");
    Assert.assertTrue(pim.getResultsText().contains("Records Found"));
  }

  @Test
  void searchByNonExistentId() {
    PimPage pim = new PimPage();
    pim.searchById("99999");
    Assert.assertTrue(pim.isNoRecordsFound(), "Debería mostrar 'No Records Found'");
  }
}
