import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.utils.DriverManager;
import com.pages.FormPage;
import java.util.Arrays;

public class FormTest {
  WebDriver driver;
  FormPage formPage;

  @BeforeMethod
  void setup(){
    driver =  DriverManager.getDriver();
    driver.get("https://demoqa.com/automation-practice-form");
    formPage = new FormPage();
  }

  @AfterMethod
  void quit(){
    DriverManager.quitDriver();
  }

  // --- CASOS POSITIVOS ---

  @Test(description = "TC-001 Positivo: Registro exitoso con todos los campos")
  public void registerAllFields() {
    formPage.fillForm(
        "Juan", "Perez", "juan@test.com", "Male", "1234567890",
        Arrays.asList("Sports", "Reading"), "Maths", "123 Main St", "NCR", "Delhi"
    );
    formPage.clickSubmit();
    Assert.assertTrue(formPage.isSuccessModalDisplayed(), "El modal de registro exitoso no se mostró.");
  }

  @Test(description = "TC-002 Positivo: Registro exitoso solo con campos obligatorios")
  public void registerMandatoryFieldsOnly() {
    formPage.fillMandatoryFields("Ana", "Gomez", "Female", "0987654321");
    formPage.clickSubmit();
    Assert.assertTrue(formPage.isSuccessModalDisplayed(), "El modal de registro exitoso no se mostró.");
  }

  @Test(description = "TC-003 Positivo: Selección de múltiples hobbies")
  public void selectMultipleHobbies() {
    formPage.fillMandatoryFields("Carlos", "Lopez", "Male", "1122334455");
    formPage.selectHobbies(Arrays.asList("Sports", "Reading", "Music"));
    formPage.clickSubmit();
    Assert.assertTrue(formPage.isSuccessModalDisplayed(), "El modal de registro exitoso no se mostró.");
  }

  @Test(description = "TC-004 Positivo: Selección correcta de Estado y Ciudad dependiente")
  public void selectStateAndCity() {
    formPage.fillMandatoryFields("Maria", "Garcia", "Female", "9876543210");
    formPage.selectStateCity("NCR", "Delhi");
    formPage.clickSubmit();
    Assert.assertTrue(formPage.isSuccessModalDisplayed(), "El modal de registro exitoso no se mostró.");
  }

  // --- CASOS NEGATIVOS ---

  @Test(description = "TC-005 Negativo: Envío sin nombre (campo obligatorio vacío)")
  public void submitWithoutFirstName() {
    // Al pasar null, no se llena el campo name
    formPage.fillMandatoryFields(null, "Gomez", "Female", "0987654321");
    formPage.clickSubmit();
    Assert.assertTrue(formPage.isSuccessModalHidden(), "El formulario se envió sin nombre, lo cual es incorrecto.");
  }

  @Test(description = "TC-006 Negativo: Email con formato inválido")
  public void submitWithInvalidEmail() {
    formPage.fillMandatoryFields("Ana", "Gomez", "Female", "0987654321");
    formPage.fillEmail("invalid_email.com"); // Email sin @
    formPage.clickSubmit();
    Assert.assertTrue(formPage.isSuccessModalHidden(), "El formulario se envió con un email inválido.");
  }

  @Test(description = "TC-007 Negativo: Número de móvil con menos de 10 dígitos")
  public void submitWithShortMobile() {
    formPage.fillMandatoryFields("Ana", "Gomez", "Female", "12345"); // Menos de 10 dígitos
    formPage.clickSubmit();
    Assert.assertTrue(formPage.isSuccessModalHidden(), "El formulario se envió con un teléfono de menos de 10 dígitos.");
  }

  @Test(description = "TC-008 Negativo: Envío sin seleccionar género")
  public void submitWithoutGender() {
    // Al pasar null, no selecciona género
    formPage.fillMandatoryFields("Ana", "Gomez", null, "0987654321");
    formPage.clickSubmit();
    Assert.assertTrue(formPage.isSuccessModalHidden(), "El formulario se envió sin seleccionar el género.");
  }

  // --- CASOS DE VALIDACIÓN DE INTERFAZ ---

  @Test(description = "TC-009 Verificar visibilidad del título del formulario")
  public void verifyFormTitleVisibility() {
    Assert.assertTrue(formPage.isFormTitleVisible(), "El título del formulario no está visible en pantalla.");
  }

  @Test(description = "TC-010 Verificar que los radio buttons de género están presentes")
  public void verifyGenderRadiosPresent() {
    Assert.assertTrue(formPage.areGenderOptionsPresent(), "Las opciones de género no están presentes.");
  }

  @Test(description = "TC-011 Verificar que los checkboxes de hobbies están presentes")
  public void verifyHobbiesCheckboxesPresent() {
    Assert.assertTrue(formPage.areHobbiesOptionsPresent(), "Los checkboxes de hobbies no están presentes.");
  }

  @Test(description = "TC-012 Verificar que el botón Submit está visible")
  public void verifySubmitButtonVisible() {
    Assert.assertTrue(formPage.isSubmitBtnVisible(), "El botón de Submit no está visible.");
  }

  // --- CASOS DE NAVEGACIÓN ---

  @Test(description = "TC-013 Navegación: Acceso directo a la página del formulario")
  public void directAccessToFormPage() {
    String currentUrl = driver.getCurrentUrl();
    Assert.assertTrue(currentUrl.contains("automation-practice-form"), "La URL no corresponde a la página de Formulario.");
  }

  @Test(description = "TC-014 Navegación: Recargar la página limpia el formulario")
  public void refreshPageClearsForm() {
    formPage.fillNameLastName("Pedro", "Diaz");
    driver.navigate().refresh(); // Simula presionar F5
    String nameValue = formPage.getFirstNameValue();
    Assert.assertTrue(nameValue.isEmpty(), "El formulario no se limpió después de recargar la página.");
  }

  @Test(description = "TC-015 Navegación: Boton de 'Atras' de navegador")
  public void navigateBackToForm() {
    driver.get("https://demoqa.com/"); // Navegamos lejos del formulario
    driver.navigate().back(); // Simulamos presionar el botón de regresar del navegador
    Assert.assertTrue(driver.getCurrentUrl().contains("automation-practice-form"), "No se regresó correctamente al formulario.");
  }

  @Test(description = "TC-016 Navegación: Navegar a la página principal mediante el logo superior")
  public void navigateToHomeViaLogo() {
    formPage.clickHeaderLogo(); // Click en la imagen de DemoQA en la parte superior izquierda
    Assert.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/", "No se navegó a la página principal.");
  }
}
