package tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pages.RecruitePage;

public class RecruitTest extends BaseTest {

  @BeforeMethod
  void goToCandidates() {
    driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/recruitment/viewCandidates");
  }

  @Test
  void searchCandidateByName() {
    RecruitePage recruite = new RecruitePage();
    recruite.searchByKeyword("test");
    Assert.assertTrue(recruite.getResultsText().contains("No Records Found"));
  }

  @Test
  void navigateToVacanciesTab() {
    RecruitePage recruite = new RecruitePage();
    recruite.visitTab("Vacancies");
    recruite.waitForUrlContains("viewJobVacancy");
    Assert.assertTrue(driver.getCurrentUrl().contains("viewJobVacancy"));
  }

  @Test
  void vacanciesTableHasCorrectColumns() {
    RecruitePage recruite = new RecruitePage();
    recruite.visitTab("Vacancies");
    recruite.waitForUrlContains("viewJobVacancy");
    List<String> headers = recruite.getColumnHeaders();
    Assert.assertTrue(headers.contains("Job Title"), "Falta columna: Job Title");
    Assert.assertTrue(headers.contains("Hiring Manager"), "Falta columna: Hiring Manager");
    Assert.assertTrue(headers.contains("Status"), "Falta columna: Status");
  }

  @Test
  void filterByVacancy() {
    RecruitePage recruite = new RecruitePage();
    recruite.filterByVacancy("Senior QA Lead");
    Assert.assertTrue(recruite.getResultsText().contains("Records Found"));
  }
}
