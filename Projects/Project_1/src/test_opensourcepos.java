
import java.io.File;
import java.time.Duration;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class test_opensourcepos {

        private WebDriver driver;
        private WebDriverWait wait;

        @Before
        public void setUp() {
                // System.setProperty("webdriver.edge.driver",
                // "./src/test/resources/edgedriver/msedgedriver.exe");
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                wait = new WebDriverWait(driver, Duration.ofSeconds(10));

                // Navegar a la página de inicio de sesión
                driver.get("http://127.0.0.1/");

                // Encontrar elementos del formulario de inicio de sesión
                WebElement usernameField = driver.findElement(By.id("input-username"));
                WebElement passwordField = driver.findElement(By.id("input-password"));
                WebElement loginButton = driver.findElement(By.name("login-button"));

                // Llenar las credenciales
                usernameField.sendKeys("admin");
                passwordField.sendKeys("pointofsale");

                // Hacer clic en el botón de inicio de sesión
                loginButton.click();
        }

        @After
        public void tearDown() {
                if (driver != null) {
                        driver.quit();
                }
        }

        /*
         * Descripción
         * Este caso de prueba valida la entrada de datos en el campo "Email",
         * asegurando
         * que el formato del correo sea correcto.
         */
        @Test
        public void casoPruebaN_001() {
                // Precondición: Navegar al apartado de "Customers"
                WebElement customersLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("a[title='Customers']")));
                customersLink.click();

                // Precondición: Abrir el formulario de "New Customer"
                WebElement newCustomerButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("button[title='New Customer']")));
                newCustomerButton.click();

                // Paso 1: Marcar el checkbox "Registration consent"
                WebElement consentCheckbox = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//input[@type='checkbox' and @name='consent']")));
                if (!consentCheckbox.isSelected()) {
                        consentCheckbox.click();
                }

                // Paso 2: Completar los campos "First Name" y "Last Name"
                WebElement firstNameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("first_name")));
                WebElement lastNameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("last_name")));
                firstNameField.sendKeys("Ana");
                lastNameField.sendKeys("Gómez");

                // Paso 3: Completar el campo "Email" con un formato incorrecto
                WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                "/html/body/div[3]/div/div/div[2]/div/div/div/form/div/div/fieldset/div[5]/div/div/input")));
                emailField.sendKeys("correoNoValido");

                // Paso 4: Presionar el botón "Submit"
                WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
                submitButton.click();

                // Resultado esperado: Validar el mensaje de error para el campo "Email"
                WebElement emailError = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("label[for='email'].has-error")));
                Assert.assertTrue("El mensaje de error para el campo 'Email' no es visible.",
                                emailError.isDisplayed());
        }

        /*
         * Descripción
         * Este caso de prueba valida la entrada de datos en el campo "Phone Number",
         * asegurando que el formato del teléfono sea correcto.
         */
        @Test
        public void casoPruebaN_002() {
                // Precondición: Navegar al apartado de "Customers"
                WebElement customersLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("a[title='Customers']")));
                customersLink.click();

                // Precondición: Abrir el formulario de "New Customer"
                WebElement newCustomerButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("button[title='New Customer']")));
                newCustomerButton.click();

                // Paso 1: Marcar el checkbox "Registration consent"
                WebElement consentCheckbox = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//input[@type='checkbox' and @name='consent']")));
                if (!consentCheckbox.isSelected()) {
                        consentCheckbox.click();
                }

                // Paso 2: Completar los campos "First Name" y "Last Name"
                WebElement firstNameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("first_name")));
                WebElement lastNameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("last_name")));
                firstNameField.sendKeys("Ana");
                lastNameField.sendKeys("Gómez");

                // Paso 3: Completar el campo "Phone Number" con un formato incorrecto
                WebElement phoneNumberField = wait
                                .until(ExpectedConditions.elementToBeClickable(By.id("phone_number")));
                phoneNumberField.sendKeys("54651325");

                // Paso 4: Presionar el botón "Submit"
                WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
                submitButton.click();

                // Resultado esperado: Validar el mensaje de error para el campo "Phone Number"
                WebElement phoneNumberError = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("label[for='phone_number'].has-error")));
                Assert.assertTrue("El mensaje de error para el campo 'Phone Number' no es visible.",
                                phoneNumberError.isDisplayed());

                // Validar el contenido del mensaje de error
                String expectedErrorMessage = "The Phone Number field must contain a valid phone number.";
                Assert.assertEquals("El mensaje de error no coincide con lo esperado.",
                                expectedErrorMessage, phoneNumberError.getText());
        }

        /*
         * Descripción
         * Este caso de prueba verifica la funcionalidad de los 3 campos obligatorios en
         * el
         * formulario de “New Customer”. Se asegura de que los campos "Registration
         * consent", "First Name" y "Last Name" sean validados correctamente como
         * obligatorios.
         */
        @Test
        public void casoPruebaN_003() {
                // Precondición: Navegar al apartado de "Customers"
                WebElement customersLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("a[title='Customers']")));
                customersLink.click();

                // Precondición: Abrir el formulario de "New Customer"
                WebElement newCustomerButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("button[title='New Customer']")));
                newCustomerButton.click();

                // Paso 2: Dejar sin marcar el checkbox "Registration consent"
                WebElement consentCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//input[@type='checkbox' and @name='consent']")));
                if (consentCheckbox.isSelected()) {
                        consentCheckbox.click();
                }

                // Paso 3: Dejar en blanco los campos "First Name" y "Last Name"
                WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("first_name")));
                WebElement lastNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("last_name")));
                firstNameField.clear();
                lastNameField.clear();

                // Paso 4: Dejar en blanco o sin marcar todos los demás campos
                // Paso 5: Presionar el botón "Submit"
                WebElement submitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("submit")));
                submitButton.click();

                // Validar resultados esperados
                // Verificar que se muestren los mensajes de error esperados
                // Error para "Registration consent"
                WebElement consentError = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("label[for='consent'].has-error")));
                Assert.assertTrue("El mensaje de error para 'Registration consent' no es visible.",
                                consentError.isDisplayed());

                // Error para "First Name"
                WebElement firstNameError = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("label[for='first_name'].has-error")));
                Assert.assertTrue("El mensaje de error para 'First Name' no es visible.",
                                firstNameError.isDisplayed());

                // Error para "Last Name"
                WebElement lastNameError = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("label[for='last_name'].has-error")));
                Assert.assertTrue("El mensaje de error para 'Last Name' no es visible.",
                                lastNameError.isDisplayed());
        }

        /*
         * Descripción
         * Este caso de prueba valida que, si se marca "Registration consent” y se
         * completan
         * los campos "First Name" y "Last Name" el formulario se envíe exitosamente.
         */
        @Test
        public void casoPruebaN_004() {
                // Precondición: Navegar al apartado de "Customers"
                WebElement customersLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("a[title='Customers']")));
                customersLink.click();

                // Paso 1: Abrir el formulario de "New Customer"
                WebElement newCustomerButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("button[title='New Customer']")));
                newCustomerButton.click();

                // Paso 2: Marcar el checkbox "Registration consent"
                WebElement consentCheckbox = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//input[@type='checkbox' and @name='consent']")));
                if (!consentCheckbox.isSelected()) {
                        consentCheckbox.click();
                }

                // Paso 3: Completar los campos "First Name" y "Last Name"
                WebElement firstNameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("first_name")));
                WebElement lastNameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("last_name")));
                firstNameField.sendKeys("Ana");
                lastNameField.sendKeys("Gómez");

                // Paso 4: Presionar el botón "Submit"
                WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
                submitButton.click();

                // Resultado esperado: Validar que el formulario se envió exitosamente
                // Verificar que el cliente aparece en la lista
                WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector(".alert-success")));
                Assert.assertTrue("El formulario no se envió exitosamente, no se encontró el mensaje de éxito.",
                                successMessage.isDisplayed());

                // Validar que el nuevo cliente "Ana Gómez" aparece en la lista de clientes
                WebElement newCustomer = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("/html/body/div/div[3]/div/div[2]")));
                Assert.assertTrue("El cliente 'Ana Gómez' no aparece en la lista después de enviar el formulario.",
                                newCustomer.isDisplayed());
        }

        /*
         * Descripción
         * Este caso de prueba valida que al completar todos los campos y marcar todas
         * las
         * casillas del formulario este se envié correctamente.
         */
        @Test
        public void casoPruebaN_005() {
                // Precondición: Navegar al apartado de "Customers"
                WebElement customersLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("a[title='Customers']")));
                customersLink.click();

                // Paso 1: Abrir el formulario de "New Customer"
                WebElement newCustomerButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("button[title='New Customer']")));
                newCustomerButton.click();

                // Paso 2: Marcar el checkbox "Registration consent"
                WebElement consentCheckbox = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//input[@type='checkbox' and @name='consent']")));
                if (!consentCheckbox.isSelected()) {
                        consentCheckbox.click();
                }

                // Paso 3: Completar los campos obligatorios "First Name" y "Last Name"
                WebElement firstNameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("first_name")));
                WebElement lastNameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("last_name")));
                firstNameField.sendKeys("Carlos");
                lastNameField.sendKeys("Rodríguez");

                // Paso 4: Seleccionar el valor de "Gender" (M)
                WebElement genderMale = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//input[@type='radio' and @name='gender' and @value='1']")));
                genderMale.click();

                // Paso 5: Completar el campo "Email"
                WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                "/html/body/div[3]/div/div/div[2]/div/div/div/form/div/div/fieldset/div[5]/div/div/input")));
                emailField.sendKeys("carlos.rodriguez@example.com");

                // Paso 6: Completar el campo "Phone Number"
                WebElement phoneNumberField = wait
                                .until(ExpectedConditions.elementToBeClickable(By.id("phone_number")));
                phoneNumberField.sendKeys("987654321");

                // Paso 7: Completar el campo "Address 1"
                WebElement address1Field = wait.until(ExpectedConditions.elementToBeClickable(By.id("address_1")));
                address1Field.sendKeys("Calle Principal 456");

                // Paso 8: Completar opcionalmente el campo "Address 2"
                WebElement address2Field = wait.until(ExpectedConditions.elementToBeClickable(By.id("address_2")));
                address2Field.sendKeys("Calle Secundaria 654");

                // Paso 9: Completar el campo "City"
                WebElement cityField = wait.until(ExpectedConditions.elementToBeClickable(By.id("city")));
                cityField.sendKeys("San José");

                // Paso 10: Completar el campo "State"
                WebElement stateField = wait.until(ExpectedConditions.elementToBeClickable(By.id("state")));
                stateField.sendKeys("San José");

                // Paso 11: Completar el campo "Postal Code"
                WebElement postalCodeField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                "/html/body/div[3]/div/div/div[2]/div/div/div/form/div/div/fieldset/div[11]/div/input")));
                postalCodeField.sendKeys("10101");

                // Paso 12: Completar el campo "Country"
                WebElement countryField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                                "/html/body/div[3]/div/div/div[2]/div/div/div/form/div/div/fieldset/div[12]/div/input")));
                countryField.sendKeys("Costa Rica");

                // Paso 13: Completar opcionalmente el campo "Comments"
                WebElement commentsField = wait.until(ExpectedConditions.elementToBeClickable(By.id("comments")));
                commentsField.sendKeys("Cliente mayorista.");

                // Paso 14: Seleccionar un tipo de descuento en "Discount Type" (Percentage
                // Discount)
                WebElement discountTypePercentage = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//input[@type='radio' and @name='discount_type' and @value='0']")));
                discountTypePercentage.click();

                // Paso 15: Completar el campo "Discount"
                WebElement discountField = wait.until(ExpectedConditions.elementToBeClickable(By.id("discount")));
                discountField.sendKeys("10.00");

                // Paso 16: Completar el campo "Company"
                WebElement companyField = wait.until(ExpectedConditions.elementToBeClickable(By.id("company_name")));
                companyField.sendKeys("Tech Solutions");

                // Paso 17: Completar el campo "Account #"
                WebElement accountNumberField = wait
                                .until(ExpectedConditions.elementToBeClickable(By.id("account_number")));
                accountNumberField.sendKeys("123459"); // cambiar despues de caga ejecucion del test

                // Paso 18: Completar el campo "Tax Id"
                WebElement taxIdField = wait.until(ExpectedConditions.elementToBeClickable(By.id("tax_id")));
                taxIdField.sendKeys("789456123");

                // Paso 19: Marcar el checkbox "Taxable"
                WebElement taxableCheckbox = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//input[@type='checkbox' and @name='taxable']")));
                if (!taxableCheckbox.isSelected()) {
                        taxableCheckbox.click();
                }

                // Paso 20: Presionar el botón "Submit"
                WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
                submitButton.click();

                // Resultado esperado: Validar que el formulario se envió exitosamente
                WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector(".alert-success")));
                Assert.assertTrue("El formulario no se envió exitosamente, no se encontró el mensaje de éxito.",
                                successMessage.isDisplayed());
        }

        /*
         * Descripción
         * Este caso de prueba valida que, si se marca el consentimiento de registro, y
         * los
         * campos obligatorios "First Name" y "Last Name" están llenos correctamente,
         * el formulario se envíe exitosamente.
         */
        @Test
        public void casoPruebaN_006() {
                // Precondición: Navegar al apartado de "Customers"
                WebElement customersLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("a[title='Customers']")));
                customersLink.click();

                // Paso 1: Abrir el formulario de "New Customer"
                WebElement newCustomerButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("button[title='New Customer']")));
                newCustomerButton.click();

                // Paso 2: Marcar el checkbox "Registration consent"
                WebElement consentCheckbox = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//input[@type='checkbox' and @name='consent']")));
                if (!consentCheckbox.isSelected()) {
                        consentCheckbox.click();
                }

                // Paso 3: Completar los campos "First Name" y "Last Name"
                WebElement firstNameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("first_name")));
                WebElement lastNameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("last_name")));
                firstNameField.sendKeys("Ana");
                lastNameField.sendKeys("Gómez");

                // Paso 4: Presionar el botón "Submit"
                WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
                submitButton.click();

                // Resultado esperado: Validar que el formulario se envió exitosamente
                WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector(".alert-success")));
                Assert.assertTrue("El formulario no se envió exitosamente, no se encontró el mensaje de éxito.",
                                successMessage.isDisplayed());

        }

        /*
         * Descripción
         * Este caso de prueba verifica la funcionalidad de eliminar un cliente del
         * sistema.
         * El cliente debe ser eliminado y la página debe actualizarse correctamente.
         */
        @Test
        public void casoPruebaN_007() {
                // Precondición: Navegar al apartado de "Customers"
                WebElement customersLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("a[title='Customers']")));
                customersLink.click();

                // Paso 1: Seleccionar el checkbox del cliente que se desea eliminar
                String customerIndex = "1";
                WebElement customerCheckbox = wait.until(ExpectedConditions.elementToBeClickable(
                                By.cssSelector("input[data-index='" + customerIndex + "']")));
                customerCheckbox.click();

                // Paso 2: Presionar el botón "Delete"
                WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("delete")));
                deleteButton.click();

                // Paso 3: Confirmar la eliminación
                Alert alert = wait.until(ExpectedConditions.alertIsPresent());
                alert.accept();

                // Resultado esperado: Validar que el cliente fue eliminado
                // Verificar que el cliente ya no aparece en la tabla
                WebElement tableBody = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("table")));
                boolean isCustomerDeleted = !tableBody.getText().contains("Ana Gómez");
                Assert.assertTrue("El cliente 'Ana Gómez' no fue eliminado correctamente.", isCustomerDeleted);
        }

        /*
         * Descripción
         * Este caso de prueba verifica la funcionalidad al decidir cancelar la
         * eliminación de
         * un cliente del sistema. La acción de eliminación debe detenerse y el cliente
         * debe
         * permanecer en la lista.
         */
        @Test
        public void casoPruebaN_008() {
                // Precondición: Navegar al apartado de "Customers"
                WebElement customersLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("a[title='Customers']")));
                customersLink.click();

                // Contar los índices de los clientes antes de intentar eliminar
                List<WebElement> rowsBefore = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                                By.cssSelector("input[name='btSelectItem']")));
                int totalCustomersBefore = rowsBefore.size();

                // Paso 1: Seleccionar el checkbox del cliente que se desea eliminar (usando
                // data-index)
                String customerIndex = "1";
                WebElement customerCheckbox = wait.until(ExpectedConditions.elementToBeClickable(
                                By.cssSelector("input[data-index='" + customerIndex + "']")));
                customerCheckbox.click();

                // Paso 2: Presionar el botón "Delete"
                WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("delete")));
                deleteButton.click();

                // Paso 3: Cancelar la eliminación en el diálogo de confirmación
                Alert alert = wait.until(ExpectedConditions.alertIsPresent());
                alert.dismiss();

                // Contar los índices de los clientes después de cancelar la eliminación
                List<WebElement> rowsAfter = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                                By.cssSelector("input[name='btSelectItem']")));
                int totalCustomersAfter = rowsAfter.size();

                // Resultado esperado: Validar que la cantidad de clientes sigue siendo la misma
                Assert.assertEquals(
                                "El número de clientes cambió después de cancelar la eliminación.",
                                totalCustomersBefore, totalCustomersAfter);
        }

        /*
         * Descripción
         * Este caso de prueba verifica el funcionamiento de "Update Customer" en el
         * sistema.
         * El sistema debe desplegar el formulario de actualización y autocompletar
         * los campos con la información existente del cliente seleccionado.
         */
        @Test
        public void casoPruebaN_009() {
                // Precondición: Navegar al apartado de "Customers"
                WebElement customersLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("a[title='Customers']")));
                customersLink.click();

                // Paso 1: Dar clic en el ícono de "Update Customer"
                // Seleccionamos el primer cliente para actualizar
                WebElement updateCustomerButton = wait.until(ExpectedConditions.elementToBeClickable(
                                By.cssSelector("a[title='Update Customer']")));
                updateCustomerButton.click();

                // Paso 2: Validar que el formulario de actualización se despliega correctamente
                WebElement updateForm = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("form#customer_form")));
                Assert.assertTrue("El formulario de actualización no se desplegó correctamente.",
                                updateForm.isDisplayed());

                // Paso 3: Validar que los campos están autocompletados
                WebElement firstNameField = wait
                                .until(ExpectedConditions.visibilityOfElementLocated(By.id("first_name")));
                WebElement lastNameField = wait
                                .until(ExpectedConditions.visibilityOfElementLocated(By.id("last_name")));

                // Asegurar que los campos no están vacíos
                Assert.assertFalse("El campo 'First Name' no está autocompletado.",
                                firstNameField.getAttribute("value").isEmpty());
                Assert.assertFalse("El campo 'Last Name' no está autocompletado.",
                                lastNameField.getAttribute("value").isEmpty());
        }

        /*
         * Descripción
         * Este caso de prueba verifica el funcionamiento de “Send SMS” a un cliente.
         * El sistema debe desplegar el formulario para enviar un mensaje y
         * autocompletar
         * el campo "Phone number" del cliente seleccionado.
         */
        @Test
        public void casoPruebaN_010() {
                // Precondición: Navegar al apartado de "Customers"
                WebElement customersLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("a[title='Customers']")));
                customersLink.click();

                // Paso 1: Dar clic en el ícono de "Send SMS" (penúltima columna)
                WebElement sendSMSButton = wait.until(ExpectedConditions.elementToBeClickable(
                                By.cssSelector("a[title='Send SMS']")));
                sendSMSButton.click();

                // Validar que el formulario de "Send SMS" se despliega correctamente
                WebElement smsForm = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("required_fields_message")));
                Assert.assertTrue("El formulario 'Send SMS' no se desplegó correctamente.", smsForm.isDisplayed());

        }

        /*
         * Descripción
         * Este caso de prueba valida que si el campo "Phone number" está vacío en el
         * formulario "Send SMS", no se pueda enviar y se muestre un mensaje de error
         * indicando
         * que el campo es obligatorio.
         */
        @Test
        public void casoPruebaN_011() {
                // Precondición: Navegar al apartado de "Customers"
                WebElement customersLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("a[title='Customers']")));
                customersLink.click();

                // Abrir el formulario "Send SMS"
                WebElement sendSMSButton = wait.until(ExpectedConditions.elementToBeClickable(
                                By.cssSelector("a[title='Send SMS']")));
                sendSMSButton.click();

                // Paso 1: Dejar el campo "Phone number" vacío
                WebElement phoneNumberField = wait
                                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                                                "/html/body/div[3]/div/div/div[2]/div/div/div/form/fieldset/div[3]/div/div/input")));
                phoneNumberField.clear(); // Asegurarse de que el campo esté vacío

                // Paso 2: Completar el campo "Message"
                WebElement messageField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
                messageField.sendKeys("Este es un mensaje de prueba.");

                // Paso 3: Presionar el botón "Submit"
                WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
                submitButton.click();

                // Resultado esperado: Validar que se muestra un mensaje de error
                WebElement phoneNumberError = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("phone-error")));
                Assert.assertTrue("No se mostró el mensaje de error para el campo 'Phone number'.",
                                phoneNumberError.isDisplayed());
        }

        /*
         * Descripción
         * Este caso de prueba valida que el campo "Message" sea obligatorio y que el
         * formulario no se pueda enviar si está vacío.
         */
        @Test
        public void casoPruebaN_012() {
                // Precondición: Navegar al apartado de "Customers"
                WebElement customersLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("a[title='Customers']")));
                customersLink.click();

                // Abrir el formulario "Send SMS"
                WebElement sendSMSButton = wait.until(ExpectedConditions.elementToBeClickable(
                                By.cssSelector("a[title='Send SMS']")));
                sendSMSButton.click();

                // Paso 1: Dejar el campo "Message" vacío.
                WebElement messageField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
                messageField.clear();

                // Paso 3: Presionar el botón "Submit"
                WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
                submitButton.click();

                // Resultado esperado: Validar que se muestra un mensaje de error
                WebElement messageError = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("message-error")));
                Assert.assertTrue("No se mostró el mensaje de error para el campo 'Message'.",
                                messageError.isDisplayed());
        }

        /*
         * Descripción
         * Este caso de prueba valida que, al completar todos los campos correctamente,
         * el formulario "Send SMS" se envíe exitosamente y se muestre un mensaje de
         * confirmación.
         */
        @Test
        public void casoPruebaN_013() {
                // Precondición: Navegar al apartado de "Customers"
                WebElement customersLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("a[title='Customers']")));
                customersLink.click();

                // Abrir el formulario "Send SMS"
                WebElement sendSMSButton = wait.until(ExpectedConditions.elementToBeClickable(
                                By.cssSelector("a[title='Send SMS']")));
                sendSMSButton.click();

                // Paso 1: Completar el campo "Message"
                WebElement messageField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
                messageField.sendKeys("Este es un mensaje de prueba.");

                // Paso 2: Presionar el botón "Submit"
                WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
                submitButton.click();

                // Resultado esperado: Validar que se muestra un mensaje de confirmación
                WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector(".alert-success")));
                Assert.assertTrue("No se mostró el mensaje de confirmación de envío exitoso.",
                                successMessage.isDisplayed());
        }

        /*
         * Descripción
         * Este caso de prueba valida que los campos obligatorios (Item Name, Category,
         * Wholesale Price, Retail Price, Quantity Stock, Receiving Quantity, Reorder
         * Level)
         * sean verificados y que el formulario no pueda enviarse si estos están vacíos.
         */
        @Test
        public void casoPruebaN_014() {
                // Precondición: Navegar al apartado de "Items"
                WebElement itemsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("a[title='Items']")));
                itemsLink.click();

                // Paso 1: Dar clic en el botón "New Item"
                WebElement newItemButton = wait.until(ExpectedConditions.elementToBeClickable(
                                By.cssSelector("button[title='New Item']")));
                newItemButton.click();

                // Paso 2: Dejar en blanco los campos obligatorios
                // Limpiar los campos si tienen valores autocompletados
                String[] fieldIds = { "name", "category", "cost_price", "unit_price", "quantity_1",
                                "receiving_quantity",
                                "reorder_level" };
                for (String fieldId : fieldIds) {
                        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(fieldId)));
                        field.clear();
                }

                // Paso 3: Presionar el botón "Submit"
                WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
                submitButton.click();

                // Resultado esperado: Validar los mensajes de error
                WebElement messageError = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("error_message_box")));
                Assert.assertTrue(
                                "No se mostraron los mensajes de error para el campo los capos 'Item Name, Category,Wholesale Price, Retail Price, Quantity Stock, Receiving Quantity, ReorderLevel'.",
                                messageError.isDisplayed());

        }

        /*
         * Descripción
         * Este caso de prueba valida que los campos "Wholesale Price" y "Retail Price"
         * no acepten caracteres no numéricos. Si se ingresan caracteres no válidos,
         * el sistema debe mostrar mensajes de error.
         */
        @Test
        public void casoPruebaN_015() {
                // Precondición: Navegar al apartado de "Items"
                WebElement itemsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("a[title='Items']")));
                itemsLink.click();

                // Paso 1: Dar clic en el botón "New Item"
                WebElement newItemButton = wait.until(ExpectedConditions.elementToBeClickable(
                                By.cssSelector("button[title='New Item']")));
                newItemButton.click();

                // Paso 2: Completar los campos "Item Name" y "Category"
                WebElement itemNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
                WebElement categoryField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("category")));
                itemNameField.sendKeys("Tostadoras");
                categoryField.sendKeys("Electrodomésticos");

                // Paso 3: Completar los campos "Wholesale Price" y "Retail Price" con
                // caracteres no numéricos
                WebElement wholesalePriceField = wait
                                .until(ExpectedConditions.visibilityOfElementLocated(By.id("cost_price")));
                wholesalePriceField.clear();
                wholesalePriceField.sendKeys("ABC");

                WebElement retailPriceField = wait
                                .until(ExpectedConditions.visibilityOfElementLocated(By.id("unit_price")));
                retailPriceField.clear();
                retailPriceField.sendKeys("XYZ");

                // Paso 4: Presionar el botón "Submit"
                WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
                submitButton.click();

                // Resultado esperado: Validar los mensajes de error
                WebElement messageError = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("error_message_box")));
                Assert.assertTrue(
                                "No se mostraron los mensajes de error para el campo los capos 'Wholesale Price y Retail Price'.",
                                messageError.isDisplayed());
        }

        /*
         * Descripción
         * Este caso de prueba verifica que el formulario "New Item" se pueda enviar
         * exitosamente
         * cuando todos los campos obligatorios se completan correctamente con datos
         * válidos,
         * limpiando previamente los campos que puedan contener datos autocompletados.
         */
        @Test
        public void casoPruebaN_016() {
                // Precondición: Navegar al apartado de "Items"
                WebElement itemsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("a[title='Items']")));
                itemsLink.click();

                // Paso 1: Dar clic en el botón "New Item"
                WebElement newItemButton = wait.until(ExpectedConditions.elementToBeClickable(
                                By.cssSelector("button[title='New Item']")));
                newItemButton.click();

                // Paso 2-7: Completar todos los campos obligatorios
                WebElement itemNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
                itemNameField.clear();
                itemNameField.sendKeys("Trineos");

                WebElement categoryField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("category")));
                categoryField.clear();
                categoryField.sendKeys("Navidad");

                WebElement wholesalePriceField = wait
                                .until(ExpectedConditions.visibilityOfElementLocated(By.id("cost_price")));
                wholesalePriceField.clear();
                wholesalePriceField.sendKeys("50.00");

                WebElement retailPriceField = wait
                                .until(ExpectedConditions.visibilityOfElementLocated(By.id("unit_price")));
                retailPriceField.clear();
                retailPriceField.sendKeys("100.00");

                WebElement quantityField = wait
                                .until(ExpectedConditions.visibilityOfElementLocated(By.id("quantity_1")));
                quantityField.clear();
                quantityField.sendKeys("10");

                WebElement receivingQuantityField = wait
                                .until(ExpectedConditions.visibilityOfElementLocated(By.id("receiving_quantity")));
                receivingQuantityField.clear();
                receivingQuantityField.sendKeys("5");

                WebElement reorderLevelField = wait
                                .until(ExpectedConditions.visibilityOfElementLocated(By.id("reorder_level")));
                reorderLevelField.clear();
                reorderLevelField.sendKeys("3");

                // Paso 8: Presionar el botón "Submit"
                WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
                submitButton.click();

                // Resultado esperado: Validar que se muestra un mensaje de confirmación
                WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector(".alert-success")));
                Assert.assertTrue("No se mostró el mensaje de confirmación.", successMessage.isDisplayed());
        }

        /*
         * Descripción
         * Este caso de prueba verifica la funcionalidad de eliminar un Item del
         * sistema.
         * El Item debe ser eliminado correctamente y la página debe actualizarse.
         */
        @Test
        public void casoPruebaN_017() {
                // Precondición: Navegar al apartado de "Items"
                WebElement itemsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("a[title='Items']")));
                itemsLink.click();

                // Paso 1: Seleccionar el checkbox del Item que se desea eliminar
                String itemIndex = "1";
                WebElement itemCheckbox = wait.until(ExpectedConditions.elementToBeClickable(
                                By.cssSelector("input[data-index='" + itemIndex + "']")));
                itemCheckbox.click();

                // Paso 2: Presionar el botón "Delete"
                WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("delete")));
                deleteButton.click();

                // Paso 3: Confirmar la eliminación
                Alert alert = wait.until(ExpectedConditions.alertIsPresent());
                alert.accept(); // Aceptar la confirmación de eliminación

                // Resultado esperado: Validar que se muestra un mensaje de confirmación
                WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector(".alert-success")));
                Assert.assertTrue("No se mostró el mensaje de confirmación de la eliminacion del item.",
                                successMessage.isDisplayed());
        }

        /*
         * Descripción
         * Este caso de prueba verifica la funcionalidad al decidir cancelar la
         * eliminación
         * de un Item del sistema. El Item no debe ser eliminado y la página debe
         * permanecer
         * con la misma cantidad de filas.
         */
        @Test
        public void casoPruebaN_018() {
                // Precondición: Navegar al apartado de "Items"
                WebElement itemsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("a[title='Items']")));
                itemsLink.click();

                // Contar los índices de los Items antes de intentar eliminar
                List<WebElement> rowsBefore = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                                By.cssSelector("input[name='btSelectItem']")));
                int totalCustomersBefore = rowsBefore.size();

                // Paso 1: Seleccionar el checkbox del Item que se desea eliminar
                String itemIndex = "1";
                WebElement itemCheckbox = wait.until(ExpectedConditions.elementToBeClickable(
                                By.cssSelector("input[data-index='" + itemIndex + "']")));
                itemCheckbox.click();

                // Paso 2: Presionar el botón "Delete"
                WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("delete")));
                deleteButton.click();

                // Paso 3: Cancelar la eliminación en la alerta
                Alert alert = wait.until(ExpectedConditions.alertIsPresent());
                alert.dismiss(); // Cancelar la eliminación

                // Contar los índices de los Items después de cancelar la eliminación
                List<WebElement> rowsAfter = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                                By.cssSelector("input[name='btSelectItem']")));
                int totalCustomersAfter = rowsAfter.size();

                // Resultado esperado: Validar que la cantidad de Items sigue siendo la misma
                Assert.assertEquals(
                                "El número de Items cambió después de cancelar la eliminación.",
                                totalCustomersBefore, totalCustomersAfter);
        }

        /*
         * Descripción
         * Este caso de prueba verifica la funcionalidad de exportar los datos del
         * apartado
         * “Items” en formato CSV. El sistema debe generar correctamente el archivo CSV
         * y
         * permitir al usuario descargarlo.
         */
        @Test
        public void casoPruebaN_019() throws InterruptedException {
                // Precondición: Navegar al apartado de "Items"
                WebElement itemsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("a[title='Items']")));
                itemsLink.click();

                // Validar que estamos en el apartado de "Items"
                WebElement itemsTable = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("table")));
                Assert.assertTrue("El apartado de 'Items' no se cargó correctamente.", itemsTable.isDisplayed());

                // Paso 1: Presionar el botón "Export data"
                WebElement exportButton = wait.until(ExpectedConditions.elementToBeClickable(
                                By.cssSelector("button[title='Export data']")));
                exportButton.click();

                // Paso 2: Seleccionar la opción "CSV" del menú desplegable
                WebElement csvOption = wait.until(ExpectedConditions.elementToBeClickable(
                                By.cssSelector("ul.dropdown-menu li[data-type='csv'] a")));
                csvOption.click();

                // Esperar para que la descarga inicie
                Thread.sleep(3000);

                // Validar que el archivo CSV ha sido descargado (simulación)
                String downloadPath = System.getProperty("user.home") + "/Downloads/";
                String expectedFileName = "items.csv";
                File downloadedFile = new File(downloadPath + expectedFileName);

                Assert.assertTrue("El archivo CSV no se descargó correctamente.", downloadedFile.exists());
        }

        /*
         * Descripción
         * Este caso de prueba verifica el funcionamiento de “Update Item” para los
         * datos
         * de un ítem. El sistema debe mostrar el formulario con los campos
         * autocompletados.
         */
        @Test
        public void casoPruebaN_021() {
                // Precondición: Navegar al apartado de "Items"
                WebElement itemsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("a[title='Items']")));
                itemsLink.click();

                // Paso 1: Escoger y dar clic sobre el ícono "Update Item"
                WebElement updateItemButton = wait.until(ExpectedConditions.elementToBeClickable(
                                By.cssSelector("a[title='Update Item']")));
                updateItemButton.click();

                // Paso 2: Validar que se despliega el formulario para actualizar
                WebElement updateForm = wait
                                .until(ExpectedConditions.visibilityOfElementLocated(By.id("required_fields_message")));
                Assert.assertTrue("El formulario 'Update Item' no se mostró.", updateForm.isDisplayed());

                // Paso 3: Validar que los campos están autocompletados
                WebElement itemNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
                WebElement categoryField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("category")));
                WebElement wholesalePriceField = wait
                                .until(ExpectedConditions.visibilityOfElementLocated(By.id("cost_price")));
                WebElement retailPriceField = wait
                                .until(ExpectedConditions.visibilityOfElementLocated(By.id("unit_price")));

                Assert.assertFalse("El campo 'Item Name' está vacío, pero debería estar autocompletado.",
                                itemNameField.getAttribute("value").isEmpty());
                Assert.assertFalse("El campo 'Category' está vacío, pero debería estar autocompletado.",
                                categoryField.getAttribute("value").isEmpty());
                Assert.assertFalse("El campo 'Wholesale Price' está vacío, pero debería estar autocompletado.",
                                wholesalePriceField.getAttribute("value").isEmpty());
                Assert.assertFalse("El campo 'Retail Price' está vacío, pero debería estar autocompletado.",
                                retailPriceField.getAttribute("value").isEmpty());
        }

        /*
         * Descripción
         * Este caso de prueba verifica el funcionamiento de “Update Stocks” para los
         * datos
         * de un ítem. El sistema debe actualizar el stock correctamente al enviar el
         * formulario.
         */
        @Test
        public void casoPruebaN_022() {
                // Precondición: Navegar al apartado de "Items"
                WebElement itemsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("a[title='Items']")));
                itemsLink.click();

                // Paso 1: Escoger y dar clic sobre el ícono "Update Stocks"
                WebElement updateStocksButton = wait.until(ExpectedConditions.elementToBeClickable(
                                By.cssSelector("a[title='Update Inventory']")));
                updateStocksButton.click();

                // Paso 2: Validar que se despliega el formulario para actualizar el stock
                WebElement newQuantityField = wait
                                .until(ExpectedConditions.visibilityOfElementLocated(By.id("newquantity")));
                Assert.assertTrue("El formulario 'Update Inventory' no se mostró.",
                                newQuantityField.isDisplayed());

                // Paso 3: Ingresar la nueva cantidad en el campo "Add/remove the product"
                newQuantityField.clear();
                newQuantityField.sendKeys("25");

                // Paso 4: Presionar el botón "Submit"
                WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(
                                By.id("submit")));
                submitButton.click();

                // Resultado esperado: Validar que se actualizó el stock correctamente
                WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("div.alert.alert-success")));
                Assert.assertTrue("No se mostró el mensaje de éxito para la actualización de stock.",
                                successMessage.isDisplayed());
        }

        /*
         * Descripción
         * Este caso de prueba verifica que el campo "Add/remove the product" se
         * notifique
         * como obligatorio cuando el usuario lo deja vacío.
         */
        @Test
        public void casoPruebaN_023() {
                // Precondición: Navegar al apartado de "Items"
                WebElement itemsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("a[title='Items']")));
                itemsLink.click();

                // Paso 1: Escoger y dar clic sobre el ícono "Update Stocks"
                WebElement updateStocksButton = wait.until(ExpectedConditions.elementToBeClickable(
                                By.cssSelector("a[title='Update Inventory']")));
                updateStocksButton.click();

                // Paso 2: Validar que se despliega el formulario para actualizar el stock
                WebElement newQuantityField = wait
                                .until(ExpectedConditions.visibilityOfElementLocated(By.id("newquantity")));
                Assert.assertTrue("El formulario 'Update Inventory' no se mostró.",
                                newQuantityField.isDisplayed());

                // Paso 3: Dejar vacío el campo "Add/remove the product"
                newQuantityField.clear();

                // Paso 4: Presionar el botón "Submit"
                WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(
                                By.id("submit")));
                submitButton.click();

                // Resultado esperado: Validar que se muestra el mensaje de error
                WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("newquantity-error")));
                Assert.assertTrue("El mensaje de error para 'Add/remove the product' no se mostró.",
                                successMessage.isDisplayed());
        }

        /*
         * Descripción
         * Este caso de prueba verifica que el campo "Add/remove the product" valide
         * que solo permite valores numéricos y muestra un mensaje de error cuando
         * se ingresan caracteres no válidos.
         */
        @Test
        public void casoPruebaN_024() {
                // Precondición: Navegar al apartado de "Items"
                WebElement itemsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("a[title='Items']")));
                itemsLink.click();

                // Paso 1: Escoger y dar clic sobre el ícono "Update Stocks"
                WebElement updateStocksButton = wait.until(ExpectedConditions.elementToBeClickable(
                                By.cssSelector("a[title='Update Inventory']")));
                updateStocksButton.click();

                // Paso 2: Validar que se despliega el formulario para actualizar el stock
                WebElement newQuantityField = wait
                                .until(ExpectedConditions.visibilityOfElementLocated(By.id("newquantity")));
                Assert.assertTrue("El formulario 'Update Inventory' no se mostró.",
                                newQuantityField.isDisplayed());

                // Paso 3: Ingresar caracteres no numéricos en el campo "Add/remove the product"
                newQuantityField.clear();
                newQuantityField.sendKeys("asd");

                // Paso 4: Presionar el botón "Submit"
                WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(
                                By.id("submit")));
                submitButton.click();

                // Resultado esperado: Validar que se muestra el mensaje de error
                WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("newquantity-error")));
                Assert.assertTrue(
                                "El mensaje de error para 'Add/remove the product' no se mostró al ingresar caracteres no numéricos.",
                                successMessage.isDisplayed());
        }

        /*
         * Descripción
         * Este caso de prueba verifica que al hacer clic en el botón
         * "Inventory Details"
         * para un ítem, el sistema despliega un formulario con la información del ítem
         * y
         * su historial de modificaciones.
         */
        @Test
        public void casoPruebaN_025() {
                // Precondición: Navegar al apartado de "Items"
                WebElement itemsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("a[title='Items']")));
                itemsLink.click();

                // Validar que estamos en el apartado de "Items"
                WebElement itemsTable = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("table")));
                Assert.assertTrue("El apartado de 'Items' no se cargó correctamente.", itemsTable.isDisplayed());

                // Paso 1: Escoger y dar clic sobre el ícono "Inventory Details"
                WebElement inventoryDetailsButton = wait.until(ExpectedConditions.elementToBeClickable(
                                By.cssSelector("a[title='Inventory Count Details']")));
                inventoryDetailsButton.click();

                // Paso 2: Validar que se muestra el formulario con la información del ítem
                WebElement inventoryDetailsTable = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("items_count_details")));
                Assert.assertTrue("El formulario 'Inventory Details' no se mostró correctamente.",
                                inventoryDetailsTable.isDisplayed());
        }

        /*
         * Descripción
         * Este caso de prueba valida que los campos obligatorios ("Company name",
         * "Name", y "Surname") deben ser completados antes de poder enviar el
         * formulario.
         * 
         */
        @Test
        public void casoPruebaN_026() {
                // Precondición: Navegar al apartado de "Suppliers"
                WebElement customersLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("a[title='Suppliers']")));
                customersLink.click();

                // Precondición: Abrir el formulario de "New Supplier"
                WebElement newCustomerButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("button[title='New Supplier']")));
                newCustomerButton.click();

                // Paso 1: Dejar en blanco los campos "Company Name", "First Name" y "Last Name"
                WebElement companyNameField = wait
                                .until(ExpectedConditions.elementToBeClickable(By.id("company_name_input")));
                WebElement firstNameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("first_name")));
                WebElement lastNameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("last_name")));
                companyNameField.clear(); // El campo debería estar vacío por defecto
                firstNameField.clear(); // El campo debería estar vacío por defecto
                lastNameField.clear(); // El campo debería estar vacío por defecto

                // Paso 2: Completar los demás campos con datos válidos
                WebElement categoryField = wait.until(ExpectedConditions.elementToBeClickable(By.id("category")));
                new Select(categoryField).selectByValue("0"); // Seleccionar "Goods Supplier"

                WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("/html/body/div[3]/div/div/div[2]/div/div/div/form/fieldset/div[7]/div/div/input")));

                WebElement phoneNumberField = wait
                                .until(ExpectedConditions.elementToBeClickable(By.id("phone_number")));
                WebElement address1Field = wait.until(ExpectedConditions.elementToBeClickable(By.id("address_1")));
                emailField.sendKeys("supplier@example.com");
                phoneNumberField.sendKeys("123456789");
                address1Field.sendKeys("123 Supplier Street");

                // Paso 3: Presionar el botón "Save"
                WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
                saveButton.click();

                // Resultado esperado: Validar los mensajes de error para los campos "Company
                // Name", "First Name" y "Last Name"
                WebElement companyNameError = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("label[for='company_name_input'].has-error")));
                WebElement firstNameError = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("label[for='first_name'].has-error")));
                WebElement lastNameError = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("label[for='last_name'].has-error")));

                Assert.assertTrue("El mensaje de error para 'Company Name' no es visible.",
                                companyNameError.isDisplayed());
                Assert.assertTrue("El mensaje de error para 'First Name' no es visible.", firstNameError.isDisplayed());
                Assert.assertTrue("El mensaje de error para 'Last Name' no es visible.", lastNameError.isDisplayed());
        }

        /*
         * Descripción
         * Este caso de prueba valida que el campo "Email" debe contener un formato
         * válido
         * para direcciones de correo electrónico.
         */
        @Test
        public void casoPruebaN_027() {
                // Navegar al apartado de "Suppliers"
                WebElement suppliersLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("a[title='Suppliers']")));
                suppliersLink.click();

                // Presionar el botón "New Supplier"
                WebElement newSupplierButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("button[title='New Supplier']")));
                newSupplierButton.click();

                // Esperar a que el formulario se cargue completamente
                WebElement supplierForm = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("form[id='supplier_form']")));

                // Completar los campos obligatorios y opcionales
                WebElement companyNameField = wait
                                .until(ExpectedConditions.elementToBeClickable(By.id("company_name_input")));
                companyNameField.clear();
                companyNameField.sendKeys("ABC Suppliers");

                WebElement firstNameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("first_name")));
                firstNameField.clear();
                firstNameField.sendKeys("John");

                WebElement lastNameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("last_name")));
                lastNameField.clear();
                lastNameField.sendKeys("Doe");

                WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("/html/body/div[3]/div/div/div[2]/div/div/div/form/fieldset/div[7]/div/div/input")));
                emailField.clear();
                emailField.sendKeys("emailinvalido");

                // Completar otros campos con datos válidos si es necesario
                WebElement phoneNumberField = supplierForm.findElement(By.id("phone_number"));
                phoneNumberField.clear();
                phoneNumberField.sendKeys("987654321");

                WebElement addressField = supplierForm
                                .findElement(By.xpath(
                                                "/html/body/div[3]/div/div/div[2]/div/div/div/form/fieldset/div[9]/div/input"));
                addressField.clear();
                addressField.sendKeys("Main Street 123");

                // Paso 3: Presionar el botón "Save"
                WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
                saveButton.click();

                // Validar el mensaje de error para el campo "Email"
                WebElement lastNameError = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("label[for='email'].has-error")));

                Assert.assertTrue("El mensaje de error para 'email' no es visible.", lastNameError.isDisplayed());

        }

        /*
         * Descripción
         * Este caso de prueba verifica que el formulario se pueda enviar exitosamente
         * cuando
         * todos los campos obligatorios están completos con datos válidos.
         */
        @Test
        public void casoPruebaN_029() {
                // Precondición: Navegar al apartado de "Suppliers"
                WebElement suppliersLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("a[title='Suppliers']")));
                suppliersLink.click();

                // Paso 1: Dar clic en el botón "New Supplier"
                WebElement newSupplierButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("button[title='New Supplier']")));
                newSupplierButton.click();

                // Completar todos los campos obligatorios con datos válidos
                WebElement companyNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("company_name_input")));
                companyNameField.clear();
                companyNameField.sendKeys("Tech Solutions");

                WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("first_name")));
                firstNameField.clear();
                firstNameField.sendKeys("Maria");

                WebElement lastNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("last_name")));
                lastNameField.clear();
                lastNameField.sendKeys("Johnson");

                WebElement phoneNumberField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("phone_number")));
                phoneNumberField.clear();
                phoneNumberField.sendKeys("123456789");

                WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("/html/body/div[3]/div/div/div[2]/div/div/div/form/fieldset/div[7]/div/div/input")));
                emailField.clear();
                emailField.sendKeys("maria.johnson@example.com");

                // Completar opcionalmente otros campos
                WebElement address1Field = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("address_1")));
                address1Field.clear();
                address1Field.sendKeys("123 Tech Street");

                WebElement address2Field = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("address_2")));
                address2Field.clear();
                address2Field.sendKeys("Suite 456");

                WebElement cityField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("city")));
                cityField.clear();
                cityField.sendKeys("San Jose");

                WebElement stateField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("state")));
                stateField.clear();
                stateField.sendKeys("California");

                WebElement postcodeField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("postcode")));
                postcodeField.clear();
                postcodeField.sendKeys("95131");

                WebElement countryField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("country")));
                countryField.clear();
                countryField.sendKeys("United States");

                WebElement commentsField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("comments")));
                commentsField.clear();
                commentsField.sendKeys("Trusted supplier for electronics.");

                // Presionar el botón "Save"
                WebElement saveButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("submit")));
                saveButton.click();

                // Resultado esperado: Validar el mensaje de confirmación
                WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("/html/body/div[2]/span[3]")));
                Assert.assertTrue("El mensaje de confirmación es visible.", successMessage.isDisplayed());
                successMessage.getText().contains("You have succesfully added Suppliers");

                // Validar que el nuevo proveedor aparece en la lista
                WebElement tableBody = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("table")));
                boolean isSupplierPresent = tableBody.getText().contains("Tech Solutions");
                Assert.assertTrue(
                                "El proveedor 'Tech Solutions' no aparece en la lista después de enviar el formulario.",
                                isSupplierPresent);
        }

        /*
         * Descripción
         * Este caso de prueba valida que el campo "Tax No." acepte valores numéricos,
         * letras y caracteres especiales, y que el sistema los registre correctamente.
         */
        @Test
        public void casoPruebaN_030() {
                // Precondición: Navegar al apartado de "Suppliers"
                WebElement suppliersLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("a[title='Suppliers']")));
                suppliersLink.click();

                // Paso 1: Dar clic en el botón "New Supplier"
                WebElement newSupplierButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("button[title='New Supplier']")));
                newSupplierButton.click();

                // Completar todos los campos obligatorios con datos válidos
                WebElement companyNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("company_name_input")));
                companyNameField.clear();
                companyNameField.sendKeys("ABC Suppliers");

                WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("first_name")));
                firstNameField.clear();
                firstNameField.sendKeys("John");

                WebElement lastNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("last_name")));
                lastNameField.clear();
                lastNameField.sendKeys("Doe");

                WebElement taxNoField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("tax_id")));
                taxNoField.clear();
                taxNoField.sendKeys("12A34B56!");

                WebElement phoneNumberField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("phone_number")));
                phoneNumberField.clear();
                phoneNumberField.sendKeys("123456789");

                WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("/html/body/div[3]/div/div/div[2]/div/div/div/form/fieldset/div[7]/div/div/input")));
                emailField.clear();
                emailField.sendKeys("john.doe@example.com");

                // Completar opcionalmente otros campos
                WebElement addressField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("address_1")));
                addressField.clear();
                addressField.sendKeys("123 Supply Road");

                WebElement cityField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("city")));
                cityField.clear();
                cityField.sendKeys("New York");

                WebElement stateField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("state")));
                stateField.clear();
                stateField.sendKeys("NY");

                WebElement postcodeField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("postcode")));
                postcodeField.clear();
                postcodeField.sendKeys("10001");

                WebElement countryField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("country")));
                countryField.clear();
                countryField.sendKeys("United States");

                // Paso 3: Presionar el botón "Save"
                WebElement saveButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("submit")));
                saveButton.click();

                // Resultado esperado: Validar el mensaje de confirmación
                WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("/html/body/div[2]/span[3]")));
                Assert.assertTrue("El mensaje de confirmación es visible.", successMessage.isDisplayed());
                successMessage.getText().contains("You have succesfully added Suppliers");

                // Validar que el nuevo proveedor aparece en la lista con el "Tax No." correcto
                WebElement tableBody = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("table")));
                boolean isSupplierPresent = tableBody.getText().contains("ABC Suppliers");
                Assert.assertTrue(
                                "El proveedor 'ABC Suppliers' no aparece en la lista después de enviar el formulario.",
                                isSupplierPresent);
        }

        /*
         * Descripción
         * Este caso de prueba valida que el campo "Postal code" acepte valores
         * numéricos,
         * letras y caracteres especiales, y que el sistema los registre correctamente.
         */
        @Test
        public void casoPruebaN_031() {
                // Precondición: Navegar al apartado de "Suppliers"
                WebElement suppliersLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("a[title='Suppliers']")));
                suppliersLink.click();

                // Paso 1: Dar clic en el botón "New Supplier"
                WebElement newSupplierButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("button[title='New Supplier']")));
                newSupplierButton.click();

                // Completar todos los campos obligatorios con datos válidos
                WebElement companyNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("company_name_input")));
                companyNameField.clear();
                companyNameField.sendKeys("XYZ Corporation");

                WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("first_name")));
                firstNameField.clear();
                firstNameField.sendKeys("Alice");

                WebElement lastNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("last_name")));
                lastNameField.clear();
                lastNameField.sendKeys("Smith");

                WebElement phoneNumberField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("phone_number")));
                phoneNumberField.clear();
                phoneNumberField.sendKeys("123456789");

                WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("/html/body/div[3]/div/div/div[2]/div/div/div/form/fieldset/div[7]/div/div/input")));
                emailField.clear();
                emailField.sendKeys("alice.smith@example.com");

                WebElement postalCodeField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("postcode")));
                postalCodeField.clear();
                postalCodeField.sendKeys("ABC123!");

                // Completar opcionalmente otros campos
                WebElement addressField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("address_1")));
                addressField.clear();
                addressField.sendKeys("123 Example Street");

                WebElement cityField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("city")));
                cityField.clear();
                cityField.sendKeys("Example City");

                WebElement stateField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("state")));
                stateField.clear();
                stateField.sendKeys("Example State");

                WebElement countryField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("country")));
                countryField.clear();
                countryField.sendKeys("Example Country");

                // Paso 3: Presionar el botón "Save"
                WebElement saveButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("submit")));
                saveButton.click();

                // Resultado esperado: Validar el mensaje de confirmación
                WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("/html/body/div[2]/span[3]")));
                Assert.assertTrue("El mensaje de confirmación es visible.", successMessage.isDisplayed());
                successMessage.getText().contains("You have succesfully added Suppliers");

                // Validar que el nuevo proveedor aparece en la lista con el "Postal code"
                WebElement tableBody = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("table")));
                boolean isSupplierPresent = tableBody.getText().contains("XYZ Corporation");
                Assert.assertTrue(
                                "El proveedor 'ABC Suppliers' no aparece en la lista después de enviar el formulario.",
                                isSupplierPresent);
        }

        /*
         * Descripción
         * Este caso de prueba verifica que el campo "Account number" sea opcional y que
         * el formulario se pueda enviar correctamente aunque este campo esté vacío.
         */
        @Test
        public void casoPruebaN_032() {
                // Precondición: Navegar al apartado de "Suppliers"
                WebElement suppliersLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("a[title='Suppliers']")));
                suppliersLink.click();

                // Paso 1: Dar clic en el botón "New Supplier"
                WebElement newSupplierButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("button[title='New Supplier']")));
                newSupplierButton.click();

                // Completar todos los campos obligatorios con datos válidos
                WebElement companyNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("company_name_input")));
                companyNameField.clear();
                companyNameField.sendKeys("SupplyCo");

                WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("first_name")));
                firstNameField.clear();
                firstNameField.sendKeys("Laura");

                WebElement lastNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("last_name")));
                lastNameField.clear();
                lastNameField.sendKeys("Smith");

                WebElement phoneNumberField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("phone_number")));
                phoneNumberField.clear();
                phoneNumberField.sendKeys("987654321");

                WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("/html/body/div[3]/div/div/div[2]/div/div/div/form/fieldset/div[7]/div/div/input")));
                emailField.clear();
                emailField.sendKeys("laura.smith@example.com");

                // Dejar en blanco el campo "Account number" (opcional)
                WebElement accountNumberField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("account_number")));
                accountNumberField.clear();

                // Completar opcionalmente otros campos
                WebElement addressField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("address_1")));
                addressField.clear();
                addressField.sendKeys("456 Supplier Lane");

                WebElement cityField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("city")));
                cityField.clear();
                cityField.sendKeys("Supplier City");

                WebElement stateField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("state")));
                stateField.clear();
                stateField.sendKeys("Supplier State");

                WebElement postcodeField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("postcode")));
                postcodeField.clear();
                postcodeField.sendKeys("12345");

                WebElement countryField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("country")));
                countryField.clear();
                countryField.sendKeys("United States");

                // Paso 3: Presionar el botón "Save"
                WebElement saveButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("submit")));
                saveButton.click();

                // Resultado esperado: Validar el mensaje de confirmación
                WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("/html/body/div[2]/span[3]")));
                Assert.assertTrue("El mensaje de confirmación es visible.", successMessage.isDisplayed());
                successMessage.getText().contains("You have succesfully added Suppliers");

                // Validar que el nuevo proveedor aparece en la lista
                WebElement tableBody = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("table")));
                boolean isSupplierPresent = tableBody.getText().contains("SupplyCo")
                                && tableBody.getText().contains("Laura")
                                && tableBody.getText().contains("Smith");
                Assert.assertTrue(
                                "El proveedor 'SupplyCo' no aparece en la lista después de enviar el formulario.",
                                isSupplierPresent);
        }

        /*
         * Descripción
         * Este caso de prueba valida que se pueda seleccionar entre las dos categorías
         * disponibles: "Suppliers of goods" y "Supplier expenses".
         */
        @Test
        public void casoPruebaN_033() {
                // Precondición: Navegar al apartado de "Suppliers"
                WebElement suppliersLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("a[title='Suppliers']")));
                suppliersLink.click();

                // Paso 1: Dar clic en el botón "New Supplier"
                WebElement newSupplierButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("button[title='New Supplier']")));
                newSupplierButton.click();

                // Completar todos los campos obligatorios con datos válidos
                WebElement companyNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("company_name_input")));
                companyNameField.clear();
                companyNameField.sendKeys("Supplies Inc.");

                WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("first_name")));
                firstNameField.clear();
                firstNameField.sendKeys("William");

                WebElement lastNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("last_name")));
                lastNameField.clear();
                lastNameField.sendKeys("Miller");

                WebElement phoneNumberField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("phone_number")));
                phoneNumberField.clear();
                phoneNumberField.sendKeys("987654321");

                WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("/html/body/div[3]/div/div/div[2]/div/div/div/form/fieldset/div[7]/div/div/input")));
                emailField.clear();
                emailField.sendKeys("william.miller@example.com");

                // Completar el campo "Category" seleccionando "Suppliers of goods"
                WebElement categoryDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("category")));
                Select categorySelect = new Select(categoryDropdown);
                categorySelect.selectByVisibleText("Goods Supplier");

                // Presionar el botón "Save" para la primera categoría
                WebElement saveButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("submit")));
                saveButton.click();

                // Resultado esperado: Validar el mensaje de confirmación
                WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("/html/body/div[2]/span[3]")));
                Assert.assertTrue("El mensaje de confirmación es visible.", successMessage.isDisplayed());
                successMessage.getText().contains("You have succesfully added Suppliers");

                // Validar que el proveedor aparece en la lista con la categoría seleccionada
                WebElement tableBody = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("table")));
                boolean isSupplierPresentGoods = tableBody.getText().contains("Supplies Inc.")
                                && tableBody.getText().contains("Goods Supplier");
                Assert.assertTrue(
                                "El proveedor 'Supplies Inc.' no aparece en la lista con la categoría 'Goods Supplier'.",
                                isSupplierPresentGoods);

                // Prueba con la segunda categoría: "Supplier expenses"
                newSupplierButton.click();

                // Completar nuevamente todos los campos obligatorios
                companyNameField = wait
                                .until(ExpectedConditions.visibilityOfElementLocated(By.id("company_name_input")));
                companyNameField.clear();
                companyNameField.sendKeys("Supplies Inc.");

                firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first_name")));
                firstNameField.clear();
                firstNameField.sendKeys("William");

                lastNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("last_name")));
                lastNameField.clear();
                lastNameField.sendKeys("Miller");

                phoneNumberField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("phone_number")));
                phoneNumberField.clear();
                phoneNumberField.sendKeys("987654321");

                // ---

                emailField = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("/html/body/div[3]/div/div/div[2]/div/div/div/form/fieldset/div[7]/div/div/input")));
                emailField.clear();

                emailField.sendKeys("william.miller@example.com");
                // ---

                // Completar el campo "Category" seleccionando "Supplier expenses"
                WebElement categoryField = wait.until(ExpectedConditions.elementToBeClickable(By.id("category")));
                new Select(categoryField).selectByValue("1"); // Seleccionar "Cost Supplier"

                // Presionar el botón "Save"
                saveButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("submit")));
                saveButton.click();

                // Validar el mensaje de confirmación
                successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("/html/body/div[2]/span[3]")));
                Assert.assertTrue("El mensaje de confirmación es visible.", successMessage.isDisplayed());
                successMessage.getText().contains("You have succesfully added Suppliers");

                // Validar que el proveedor aparece en la lista con la categoría seleccionada
                tableBody = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("table")));
                boolean isSupplierPresentExpenses = tableBody.getText().contains("Supplies Inc.")
                                && tableBody.getText().contains("Cost Supplier");
                Assert.assertTrue(
                                "El proveedor 'Supplies Inc.' no aparece en la lista con la categoría 'Cost Supplier'.",
                                isSupplierPresentExpenses);
        }

        /*
         * Descripción
         * Este caso de prueba verifica que el campo "Agency name" sea opcional y que el
         * formulario se pueda enviar correctamente aunque este campo esté vacío.
         */
        @Test
        public void casoPruebaN_034() {
                // Precondición: Navegar al apartado de "Suppliers"
                WebElement suppliersLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("a[title='Suppliers']")));
                suppliersLink.click();

                // Paso 1: Dar clic en el botón "New Supplier"
                WebElement newSupplierButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("button[title='New Supplier']")));
                newSupplierButton.click();

                // Completar todos los campos obligatorios con datos válidos
                WebElement companyNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("company_name_input")));
                companyNameField.clear();
                companyNameField.sendKeys("SupplyCo");

                WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("first_name")));
                firstNameField.clear();
                firstNameField.sendKeys("Laura");

                WebElement lastNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("last_name")));
                lastNameField.clear();
                lastNameField.sendKeys("Smith");

                WebElement phoneNumberField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("phone_number")));
                phoneNumberField.clear();
                phoneNumberField.sendKeys("987654321");

                WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("/html/body/div[3]/div/div/div[2]/div/div/div/form/fieldset/div[7]/div/div/input")));
                emailField.clear();
                emailField.sendKeys("laura.smith@example.com");

                // Dejar el campo "Agency name" vacío
                WebElement agencyNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("agency_name_input")));
                agencyNameField.clear();

                // Completar opcionalmente otros campos
                WebElement addressField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("address_1")));
                addressField.clear();
                addressField.sendKeys("123 Supply Road");

                WebElement cityField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("city")));
                cityField.clear();
                cityField.sendKeys("New York");

                WebElement stateField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("state")));
                stateField.clear();
                stateField.sendKeys("NY");

                WebElement postcodeField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("postcode")));
                postcodeField.clear();
                postcodeField.sendKeys("10001");

                WebElement countryField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("country")));
                countryField.clear();
                countryField.sendKeys("United States");

                // Paso 3: Presionar el botón "Save"
                WebElement saveButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("submit")));
                saveButton.click();

                // Resultado esperado: Validar el mensaje de confirmación
                // Validar el mensaje de confirmación
                WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("/html/body/div[2]/span[3]")));
                Assert.assertTrue("El mensaje de confirmación es visible.", successMessage.isDisplayed());
                successMessage.getText().contains("You have succesfully added Suppliers");

                // Validar que el nuevo proveedor aparece en la lista
                WebElement tableBody = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("table")));
                boolean isSupplierPresent = tableBody.getText().contains("SupplyCo")
                                && tableBody.getText().contains("Laura")
                                && tableBody.getText().contains("Smith");
                Assert.assertTrue("El proveedor 'SupplyCo' no aparece en la lista después de enviar el formulario.",
                                isSupplierPresent);
        }

        /*
         * Descripción
         * Este caso de prueba verifica que los campos "Address 1" y "Address 2" sean
         * opcionales y que el formulario se pueda enviar correctamente aunque estos
         * campos estén vacíos.
         */
        @Test
        public void casoPruebaN_035() {
                // Precondición: Navegar al apartado de "Suppliers"
                WebElement suppliersLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("a[title='Suppliers']")));
                suppliersLink.click();

                // Paso 1: Dar clic en el botón "New Supplier"
                WebElement newSupplierButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("button[title='New Supplier']")));
                newSupplierButton.click();

                // Completar todos los campos obligatorios con datos válidos
                WebElement companyNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("company_name_input")));
                companyNameField.clear();
                companyNameField.sendKeys("SupplyCo");

                WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("first_name")));
                firstNameField.clear();
                firstNameField.sendKeys("Laura");

                WebElement lastNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("last_name")));
                lastNameField.clear();
                lastNameField.sendKeys("Smith");

                WebElement phoneNumberField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("phone_number")));
                phoneNumberField.clear();
                phoneNumberField.sendKeys("987654321");

                WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("/html/body/div[3]/div/div/div[2]/div/div/div/form/fieldset/div[7]/div/div/input")));
                emailField.clear();
                emailField.sendKeys("laura.smith@example.com");

                // Dejar en blanco los campos "Address 1" y "Address 2"
                WebElement address1Field = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("address_1")));
                address1Field.clear();

                WebElement address2Field = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("address_2")));
                address2Field.clear();

                // Completar opcionalmente otros campos
                WebElement cityField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("city")));
                cityField.clear();
                cityField.sendKeys("New York");

                WebElement stateField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("state")));
                stateField.clear();
                stateField.sendKeys("NY");

                WebElement postcodeField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("postcode")));
                postcodeField.clear();
                postcodeField.sendKeys("10001");

                WebElement countryField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("country")));
                countryField.clear();
                countryField.sendKeys("United States");

                // Paso 3: Presionar el botón "Save"
                WebElement saveButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("submit")));
                saveButton.click();

                // Resultado esperado: Validar el mensaje de confirmación
                WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("/html/body/div[2]/span[3]")));
                Assert.assertTrue("El mensaje de confirmación es visible.", successMessage.isDisplayed());
                successMessage.getText().contains("You have succesfully added Suppliers");

                // Validar que el nuevo proveedor aparece en la lista
                WebElement tableBody = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("table")));
                boolean isSupplierPresent = tableBody.getText().contains("SupplyCo")
                                && tableBody.getText().contains("Laura")
                                && tableBody.getText().contains("Smith");
                Assert.assertTrue("El proveedor 'SupplyCo' no aparece en la lista después de enviar el formulario.",
                                isSupplierPresent);
        }

        /*
         * Descripción
         * Este caso de prueba verifica que el campo "Region" (Country )sea opcional y
         * que el
         * formulario se pueda enviar correctamente aunque este campo esté vacío.
         */
        @Test
        public void casoPruebaN_036() {
                // Precondición: Navegar al apartado de "Suppliers"
                WebElement suppliersLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("a[title='Suppliers']")));
                suppliersLink.click();

                // Paso 1: Dar clic en el botón "New Supplier"
                WebElement newSupplierButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("button[title='New Supplier']")));
                newSupplierButton.click();

                // Completar todos los campos obligatorios con datos válidos
                WebElement companyNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("company_name_input")));
                companyNameField.clear();
                companyNameField.sendKeys("SupplyCo");

                WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("first_name")));
                firstNameField.clear();
                firstNameField.sendKeys("Laura");

                WebElement lastNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("last_name")));
                lastNameField.clear();
                lastNameField.sendKeys("Smith");

                WebElement phoneNumberField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("phone_number")));
                phoneNumberField.clear();
                phoneNumberField.sendKeys("987654321");

                WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("/html/body/div[3]/div/div/div[2]/div/div/div/form/fieldset/div[7]/div/div/input")));
                emailField.clear();
                emailField.sendKeys("laura.smith@example.com");

                // Completar opcionalmente otros campos
                WebElement cityField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("city")));
                cityField.clear();
                cityField.sendKeys("New York");

                WebElement postcodeField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("postcode")));
                postcodeField.clear();
                postcodeField.sendKeys("10001");

                // Este es el espacio que hace alusión a Region
                WebElement countryField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("country")));
                countryField.clear();
                countryField.sendKeys("United States");

                // Paso 3: Presionar el botón "Save"
                WebElement saveButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("submit")));
                saveButton.click();

                // Resultado esperado: Validar el mensaje de confirmación
                WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("/html/body/div[2]/span[3]")));
                Assert.assertTrue("El mensaje de confirmación es visible.", successMessage.isDisplayed());
                successMessage.getText().contains("You have succesfully added Suppliers");

                // Validar que el nuevo proveedor aparece en la lista
                WebElement tableBody = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("table")));
                boolean isSupplierPresent = tableBody.getText().contains("SupplyCo")
                                && tableBody.getText().contains("Laura")
                                && tableBody.getText().contains("Smith");
                Assert.assertTrue("El proveedor 'SupplyCo' no aparece en la lista después de enviar el formulario.",
                                isSupplierPresent);
        }

        /*
         * Descripción
         * Este caso de prueba verifica que el campo "Comments" sea opcional y que el
         * formulario se pueda enviar correctamente aunque este campo esté vacío.
         */
        @Test
        public void casoPruebaN_037() {
                // Precondición: Navegar al apartado de "Suppliers"
                WebElement suppliersLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("a[title='Suppliers']")));
                suppliersLink.click();

                // Paso 1: Dar clic en el botón "New Supplier"
                WebElement newSupplierButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("button[title='New Supplier']")));
                newSupplierButton.click();

                // Completar todos los campos obligatorios con datos válidos
                WebElement companyNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("company_name_input")));
                companyNameField.clear();
                companyNameField.sendKeys("SupplyCo");

                WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("first_name")));
                firstNameField.clear();
                firstNameField.sendKeys("Laura");

                WebElement lastNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("last_name")));
                lastNameField.clear();
                lastNameField.sendKeys("Smith");

                WebElement phoneNumberField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("phone_number")));
                phoneNumberField.clear();
                phoneNumberField.sendKeys("987654321");

                WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("/html/body/div[3]/div/div/div[2]/div/div/div/form/fieldset/div[7]/div/div/input")));
                emailField.clear();
                emailField.sendKeys("laura.smith@example.com");

                WebElement cityField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("city")));
                cityField.clear();
                cityField.sendKeys("New York");

                WebElement postcodeField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("postcode")));
                postcodeField.clear();
                postcodeField.sendKeys("10001");

                WebElement countryField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("country")));
                countryField.clear();
                countryField.sendKeys("United States");

                // Dejar en blanco el campo "Comments"
                WebElement commentsField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("comments")));
                commentsField.clear();

                // Paso 3: Presionar el botón "Save"
                WebElement saveButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("submit")));
                saveButton.click();

                // Resultado esperado: Validar el mensaje de confirmación
                WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("/html/body/div[2]/span[3]")));
                Assert.assertTrue("El mensaje de confirmación es visible.", successMessage.isDisplayed());
                successMessage.getText().contains("You have succesfully added Suppliers");

                // Validar que el nuevo proveedor aparece en la lista
                WebElement tableBody = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("table")));
                boolean isSupplierPresent = tableBody.getText().contains("SupplyCo")
                                && tableBody.getText().contains("Laura")
                                && tableBody.getText().contains("Smith");
                Assert.assertTrue("El proveedor 'SupplyCo' no aparece en la lista después de enviar el formulario.",
                                isSupplierPresent);
        }

        /*
         * Descripción
         * Este caso de prueba valida que un supplier pueda ser eliminado correctamente
         * desde la vista de "Suppliers" al seleccionar el supplier y hacer clic en
         * "Delete".
         */
        @Test
        public void casoPruebaN_039() {
                // Precondición: Navegar al apartado de "Suppliers"
                WebElement suppliersLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("a[title='Suppliers']")));
                suppliersLink.click();

                // Paso 1: Seleccionar un supplier existente (usando el checkbox
                // correspondiente)
                String supplierIndex = "1";
                WebElement supplierCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("input[data-index='" + supplierIndex + "']")));
                supplierCheckbox.click();

                // Paso 2: Hacer clic en el botón "Delete"
                WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(
                                By.id("delete")));
                deleteButton.click();

                // Paso 3: Confirmar la eliminación
                Alert confirmAlert = wait.until(ExpectedConditions.alertIsPresent());
                confirmAlert.accept();

                // Paso 4: Validar que el supplier fue eliminado de la tabla
                WebElement tableBody = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("table")));
                boolean isSupplierDeleted = !tableBody.getText().contains("John Doe");
                Assert.assertTrue(
                                "El supplier seleccionado no fue eliminado correctamente.",
                                isSupplierDeleted);
        }

        /*
         * Método para crear uno o varios suppliers sin correos electrónicos.
         */
        public void crearSuppliersSinEmails(int cantidad) {
                // Precondición: Navegar al apartado de "Suppliers"
                WebElement suppliersLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("a[title='Suppliers']")));
                suppliersLink.click();

                for (int i = 0; i < cantidad; i++) {
                        // Paso 1: Dar clic en el botón "New Supplier"
                        WebElement newSupplierButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                        By.cssSelector("button[title='New Supplier']")));
                        newSupplierButton.click();

                        // Paso 2: Completar todos los campos obligatorios excepto el correo electrónico
                        WebElement companyNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                        By.id("company_name_input")));
                        companyNameField.clear();
                        companyNameField.sendKeys("Supplier Sin Email " + (i + 1));

                        WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                        By.id("first_name")));
                        firstNameField.clear();
                        firstNameField.sendKeys("Nombre " + (i + 1));

                        WebElement lastNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                        By.id("last_name")));
                        lastNameField.clear();
                        lastNameField.sendKeys("Apellido " + (i + 1));

                        WebElement phoneNumberField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                        By.id("phone_number")));
                        phoneNumberField.clear();
                        phoneNumberField.sendKeys("123456789");

                        // No completar el campo de email (dejarlo vacío)
                        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(
                                        By.xpath("/html/body/div[3]/div/div/div[2]/div/div/div/form/fieldset/div[7]/div/div/input")));
                        emailField.clear();
                        emailField.clear();

                        // Completar campos opcionales, si se requiere
                        WebElement addressField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                        By.id("address_1")));
                        addressField.clear();
                        addressField.sendKeys("Address " + (i + 1));

                        WebElement cityField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                        By.id("city")));
                        cityField.clear();
                        cityField.sendKeys("City " + (i + 1));

                        WebElement stateField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                        By.id("state")));
                        stateField.clear();
                        stateField.sendKeys("State " + (i + 1));

                        WebElement postcodeField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                        By.id("postcode")));
                        postcodeField.clear();
                        postcodeField.sendKeys("1000" + i);

                        WebElement countryField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                        By.id("country")));
                        countryField.clear();
                        countryField.sendKeys("Country " + (i + 1));

                        // Paso 3: Presionar el botón "Save"
                        WebElement saveButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                        By.id("submit")));
                        saveButton.click();

                        // Validar que el proveedor fue creado exitosamente
                        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                        By.xpath("/html/body/div[2]/span[3]")));
                        Assert.assertTrue("El mensaje de confirmación es visible.", successMessage.isDisplayed());
                        successMessage.getText().contains("You have succesfully added Suppliers");
                }
        }

        /*
         * Descripción
         * Este caso de prueba valida que el botón "Email" no pueda ser utilizado si no
         * hay
         * correos electrónicos registrados en los suppliers seleccionados.
         */
        @Test
        public void casoPruebaN_040() {

                // Precondición: Navegar al apartado de "Suppliers"
                WebElement suppliersLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("a[title='Suppliers']")));
                suppliersLink.click();

                // Paso 0: Crear uno o varios suppliers sin correos electrónicos
                crearSuppliersSinEmails(3);

                // Paso 1: Seleccionar uno o varios suppliers sin correos electrónicos
                // registrados

                // Obtener todos los checkboxes de los suppliers visibles
                List<WebElement> supplierCheckboxes = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                                By.cssSelector("input[name='btSelectItem']")));

                // Verificar que hay al menos tres suppliers
                Assert.assertTrue("No hay suficientes suppliers en la lista.", supplierCheckboxes.size() >= 3);

                // Seleccionar los últimos tres suppliers dinámicamente
                for (int i = supplierCheckboxes.size() - 3; i < supplierCheckboxes.size(); i++) {
                        WebElement supplierCheckbox = supplierCheckboxes.get(i);
                        supplierCheckbox.click();
                }

                // Paso 2: Intentar hacer clic en el botón "Email"
                WebElement emailButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("email")));

                // Validar que el botón esté deshabilitado o se muestre un mensaje de
                // advertencia
                if (emailButton.isEnabled()) {
                        emailButton.click();

                        // Verificar si se muestra el mensaje de advertencia
                        WebElement warningMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                        By.cssSelector(".alert.alert-warning")));
                        Assert.assertTrue(
                                        "El sistema no mostró un mensaje de advertencia al intentar enviar un correo sin emails registrados.",
                                        warningMessage.getText().contains(
                                                        "No hay correos electrónicos registrados para los proveedores seleccionados"));
                } else {
                        Assert.assertFalse(
                                        "El botón 'Email' está habilitado, pero no debería estarlo si no hay correos registrados.",
                                        emailButton.isEnabled());
                }
        }

        /**
         * Caso de Prueba No. 051
         * Valida que el sistema permita agregar un nuevo producto al carrito de ventas,
         * completando únicamente todos los campos obligatorios del formulario.
         */
        @Test
        public void casoPruebaN_051() {
                // Precondición: Navegar al apartado de "Sales"
                WebElement salesLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("a[title='Sales']")));
                salesLink.click();

                // Paso 1: Dar clic en el botón "New Item"
                WebElement newItemButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("new_item_button")));
                newItemButton.click();

                // Paso 2: Rellenar los campos obligatorios
                WebElement itemNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.name("name")));
                itemNameField.clear();
                itemNameField.sendKeys("Laptop ASUS X509");

                WebElement categoryField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.name("category")));
                categoryField.clear();
                categoryField.sendKeys("Electrónica");

                WebElement wholesalePriceField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.name("cost_price")));
                wholesalePriceField.clear();
                wholesalePriceField.sendKeys("400");

                WebElement retailPriceField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.name("unit_price")));
                retailPriceField.clear();
                retailPriceField.sendKeys("850");

                WebElement quantityStockField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.name("quantity_1")));
                quantityStockField.clear();
                quantityStockField.sendKeys("12");

                WebElement receivingQuantityField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.name("receiving_quantity")));
                receivingQuantityField.clear();
                receivingQuantityField.sendKeys("13");

                WebElement reorderLevelField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.name("reorder_level")));
                reorderLevelField.clear();
                reorderLevelField.sendKeys("9");

                // Paso 3: Guardar el formulario
                WebElement submitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("submit")));
                submitButton.click();

                // Esperar hasta que el elemento tenga texto
                WebElement registerElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("/html/body/div[1]/div[3]/div/div[1]/table/tbody/tr[1]/td[3]")));

                // Validar que el texto "Laptop ASUS X509" está dentro del elemento
                // 'registerElement'
                String expectedText = "Laptop ASUS X509";
                String actualText = registerElement.getText().trim();

                // Assert para verificar que el texto esperado está presente
                Assert.assertTrue("El texto esperado no está presente en el elemento.",
                                actualText.contains(expectedText));
        }

        /**
         * Caso de Prueba No. 052
         * Valida que el sistema muestre un mensaje de error si se intenta agregar un
         * producto
         * sin completar uno o más campos obligatorios.
         */
        @Test
        public void casoPruebaN_052() {
                // Precondición: Navegar al apartado de "Sales"
                WebElement salesLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("a[title='Sales']")));
                salesLink.click();

                // Paso 1: Dar clic en el botón "New Item"
                WebElement newProductButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("button[title='New Item']")));
                newProductButton.click();

                // Paso 2: Escanear el código de barras del producto
                WebElement barcodeField = wait
                                .until(ExpectedConditions.visibilityOfElementLocated(By.name("item_number")));
                barcodeField.clear();
                barcodeField.sendKeys("9876543210987");

                // Paso 3: Rellenar el formulario, excepto el campo "Nombre del producto"
                WebElement categoryField = wait
                                .until(ExpectedConditions.visibilityOfElementLocated(By.name("category")));
                categoryField.sendKeys("Electrónica");

                WebElement wholesalePriceField = wait
                                .until(ExpectedConditions.visibilityOfElementLocated(By.name("cost_price")));
                wholesalePriceField.clear();
                wholesalePriceField.sendKeys("300.00");

                WebElement retailPriceField = wait
                                .until(ExpectedConditions.visibilityOfElementLocated(By.name("unit_price")));
                retailPriceField.clear();
                retailPriceField.sendKeys("400.00");

                WebElement quantityStockField = wait
                                .until(ExpectedConditions.visibilityOfElementLocated(By.name("quantity_1")));
                quantityStockField.clear();
                quantityStockField.sendKeys("15");

                WebElement receivingQuantityField = wait
                                .until(ExpectedConditions.visibilityOfElementLocated(By.name("receiving_quantity")));
                receivingQuantityField.clear();
                receivingQuantityField.sendKeys("5");

                WebElement reorderLevel = wait
                                .until(ExpectedConditions.visibilityOfElementLocated(By.name("reorder_level")));
                reorderLevel.clear();
                reorderLevel.sendKeys("3");

                // Paso 4: Intentar agregar el producto al carrito
                WebElement saveButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submit")));
                saveButton.click();

                // Resultado esperado: Validar el mensaje de error para "Nombre del producto"
                WebElement nameError = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("error_message_box")));
                Assert.assertTrue("El mensaje de error para el campo 'Item Name' no fue desplegado.",
                                nameError.isDisplayed());
                Assert.assertTrue("El mensaje de error no contiene la descripción esperada.",
                                nameError.getText().contains("Item Name is a required field."));
        }

        /**
         * Caso de Prueba No. 053
         * Valida que el sistema permita agregar más cantidad de un mismo producto ya
         * agregado anteriormente al carrito de ventas.
         */
        @Test
        public void casoPruebaN_053() {
                // Instanciar el generador de números aleatorios para códigos de barras
                RandomNumberGenerator randNums = new RandomNumberGenerator();

                // Precondición: Navegar al apartado de "Sales"
                WebElement salesLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("a[title='Sales']")));
                salesLink.click();

                // Paso 1: Dar clic en el botón "New Item"
                WebElement newItemButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("button[title='New Item']"))); 
                newItemButton.click();

                // Datos del Producto
                String barcode = randNums.generateRandomNumber();
                String productName = "Mouse Inalámbrico";
                String category = "Accesorios de Computadora";
                String wholesalePrice = "20.00";
                String retailPrice = "35.00";
                String quantity = "50";

                // Paso 2: Completar el formulario para el producto
                WebElement barcodeField1 = wait
                                .until(ExpectedConditions.visibilityOfElementLocated(By.name("item_number")));
                barcodeField1.clear();
                barcodeField1.sendKeys(barcode);

                WebElement productNameField1 = driver.findElement(By.name("name"));
                productNameField1.clear();
                productNameField1.sendKeys(productName);

                WebElement categoryField1 = driver.findElement(By.name("category"));
                categoryField1.clear();
                categoryField1.sendKeys(category);

                WebElement wholesalePriceField1 = driver.findElement(By.name("cost_price"));
                wholesalePriceField1.clear();
                wholesalePriceField1.sendKeys(wholesalePrice);

                WebElement retailPriceField1 = driver.findElement(By.name("unit_price"));
                retailPriceField1.clear();
                retailPriceField1.sendKeys(retailPrice);

                WebElement quantityField1 = driver.findElement(By.name("quantity_1"));
                quantityField1.clear();
                quantityField1.sendKeys(quantity);

                // Paso 3: Presionar el botón "Submit" para el producto
                WebElement submitButton1 = driver.findElement(By.id("submit"));
                submitButton1.click();

                // Esperar brevemente para asegurar la actualización del sistema
                try {
                        Thread.sleep(2000);
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }

                // Paso 4: Dar clic en el botón "New Item" nuevamente para agregar de nuevo más
                // del producto
                newItemButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("button[title='New Item']")));
                newItemButton.click();

                // Paso 5: Completar el formulario para el producto
                WebElement barcodeField2 = wait
                                .until(ExpectedConditions.visibilityOfElementLocated(By.name("item_number")));
                barcodeField2.clear();
                barcodeField2.sendKeys(barcode); // Mismo código de barras

                WebElement productNameField2 = driver.findElement(By.name("name"));
                productNameField2.clear();
                productNameField2.sendKeys(productName);

                WebElement categoryField2 = driver.findElement(By.name("category"));
                categoryField2.clear();
                categoryField2.sendKeys(category);

                WebElement wholesalePriceField2 = driver.findElement(By.name("cost_price"));
                wholesalePriceField2.clear();
                wholesalePriceField2.sendKeys(wholesalePrice);

                WebElement retailPriceField2 = driver.findElement(By.name("unit_price"));
                retailPriceField2.clear();
                retailPriceField2.sendKeys(retailPrice);

                WebElement quantityField2 = driver.findElement(By.name("quantity_1"));
                quantityField2.clear();
                quantityField2.sendKeys(quantity);

                // Paso 6: Presionar el botón "Submit" para el producto
                WebElement submitButton2 = driver.findElement(By.id("submit"));
                submitButton2.click();

                // Paso 7: Verificar que no se muestra un mensaje de error al intentar agregar
                // de nuevo más del mismo producto
                WebElement errorMessage = wait
                                .until(ExpectedConditions.visibilityOfElementLocated(By.id("error_message_box")));
                Assert.assertFalse(
                                "Se mostró un mensaje de error al intentar agregar más elementos de un mismo producto.",
                                errorMessage.isDisplayed());
        }

        /**
         * Caso de Prueba No. 054
         * Valida que el sistema permita agregar un cliente completando únicamente los
         * campos obligatorios.
         */
        @Test
        public void casoPruebaN_054() {
                // Paso 1: Navegar al apartado "Clientes"
                WebElement customersLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("a[title='Sales']")));
                customersLink.click();

                // Paso 2: Dar clic en el botón "New Customer"
                WebElement newCustomerButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("button[title='New Customer']")));
                newCustomerButton.click();

                // Paso 3: Marcar la casilla "Consentimiento para el registro"
                WebElement consentCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.name("consent")));
                if (!consentCheckbox.isSelected()) {
                        consentCheckbox.click();
                }

                // Paso 4: Completar los campos obligatorios: "Nombre" y "Apellido"
                WebElement firstNameField = wait
                                .until(ExpectedConditions.visibilityOfElementLocated(By.id("first_name")));
                firstNameField.clear();
                firstNameField.sendKeys("Ana");

                WebElement lastNameField = wait
                                .until(ExpectedConditions.visibilityOfElementLocated(By.id("last_name")));
                lastNameField.clear();
                lastNameField.sendKeys("Gómez");

                // Paso 5: Presionar el botón "Submit"
                WebElement submitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submit")));
                submitButton.click();

                // Validar que el cliente aparece en la lista
                WebElement customerName = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("/html/body/div[1]/div[3]/div/div[2]/div/form/table/tbody/tr[1]/th[2]/a")));
                boolean isCustomerPresent = customerName.getText().contains("Ana Gómez");
                Assert.assertTrue("El cliente 'Ana Gómez' no aparece en la lista de clientes.", isCustomerPresent);
        }

        /**
         * Caso de Prueba No. 055
         * Valida que el sistema muestre errores si se intenta agregar un cliente sin
         * completar los campos obligatorios.
         */
        @Test
        public void casoPruebaN_055() {
                // Precondición: Navegar al apartado "Sales"
                WebElement salesLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("a[title='Sales']")));
                salesLink.click();

                // Paso 1: Dar clic en el botón "New Customer"
                WebElement newCustomerButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("button[title='New Customer']")));
                newCustomerButton.click();

                // Paso 2: Dejar sin marcar la casilla "Consentimiento para el registro"
                WebElement consentCheckbox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.name("consent")));
                if (consentCheckbox.isSelected()) {
                        consentCheckbox.click();
                }

                // Paso 3: Dejar vacíos los campos "Nombre" y "Apellido"
                WebElement firstNameField = wait
                                .until(ExpectedConditions.visibilityOfElementLocated(By.id("first_name")));
                firstNameField.clear();

                WebElement lastNameField = wait
                                .until(ExpectedConditions.visibilityOfElementLocated(By.id("last_name")));
                lastNameField.clear();

                // Paso 4: Intentar presionar el botón "Submit"
                WebElement submitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submit")));
                submitButton.click();

                // Resultado esperado: Validar los mensajes de error para los campos
                // obligatorios
                WebElement errorMessageBox = wait
                                .until(ExpectedConditions.visibilityOfElementLocated(By.id("error_message_box")));
                Assert.assertTrue("El mensaje de error no fue desplegado.", errorMessageBox.isDisplayed());
                Assert.assertTrue("El mensaje de error no menciona el consentimiento obligatorio.",
                                errorMessageBox.getText().contains("Registration consent is a required field."));
                Assert.assertTrue("El mensaje de error no menciona que el campo 'First Name' es obligatorio.",
                                errorMessageBox.getText().contains("First Name is a required field."));
                Assert.assertTrue("El mensaje de error no menciona que el campo 'Last Name' es obligatorio.",
                                errorMessageBox.getText().contains("Last Name is a required field."));
        }

        /**
         * Caso de Prueba No. 059
         * Valida que el sistema no permita agregar productos con el mismo código de
         * barras,
         * independientemente del nombre o tipo.
         */
        @Test
        public void casoPruebaN_059() {
                // Instanciar el generador de números aleatorios para códigos de barras
                RandomNumberGenerator randNums = new RandomNumberGenerator();

                // Precondición: Navegar al apartado de "Sales"
                WebElement salesLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("a[title='Sales']")));
                salesLink.click();

                // Paso 1: Dar clic en el botón "New Item"
                WebElement newItemButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("button[title='New Item']")));
                newItemButton.click();

                // Datos del Producto 1
                String barcode = randNums.generateRandomNumber();
                String productName1 = "Mouse Inalámbrico";
                String category1 = "Accesorios de Computadora";
                String wholesalePrice1 = "20.00";
                String retailPrice1 = "35.00";
                String quantity1 = "50";

                // Paso 2: Completar el formulario para el primer producto
                WebElement barcodeField1 = wait
                                .until(ExpectedConditions.visibilityOfElementLocated(By.name("item_number")));
                barcodeField1.clear();
                barcodeField1.sendKeys(barcode);

                WebElement productNameField1 = driver.findElement(By.name("name"));
                productNameField1.clear();
                productNameField1.sendKeys(productName1);

                WebElement categoryField1 = driver.findElement(By.name("category"));
                categoryField1.clear();
                categoryField1.sendKeys(category1);

                WebElement wholesalePriceField1 = driver.findElement(By.name("cost_price"));
                wholesalePriceField1.clear();
                wholesalePriceField1.sendKeys(wholesalePrice1);

                WebElement retailPriceField1 = driver.findElement(By.name("unit_price"));
                retailPriceField1.clear();
                retailPriceField1.sendKeys(retailPrice1);

                WebElement quantityField1 = driver.findElement(By.name("quantity_1"));
                quantityField1.clear();
                quantityField1.sendKeys(quantity1);

                // Paso 3: Presionar el botón "Submit" para el primer producto
                WebElement submitButton1 = driver.findElement(By.id("submit"));
                submitButton1.click();

                // Esperar brevemente para asegurar la actualización del sistema
                try {
                        Thread.sleep(2000);
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }

                // Paso 4: Dar clic en el botón "New Item" nuevamente para agregar el segundo
                // producto
                newItemButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("button[title='New Item']")));
                newItemButton.click();

                // Datos del Producto 2
                String productName2 = "Teclado Inalámbrico";
                String category2 = "Accesorios de Computadora";
                String wholesalePrice2 = "25.00";
                String retailPrice2 = "45.00";
                String quantity2 = "30";

                // Paso 5: Completar el formulario para el segundo producto con el mismo código
                // de barras
                WebElement barcodeField2 = wait
                                .until(ExpectedConditions.visibilityOfElementLocated(By.name("item_number")));
                barcodeField2.clear();
                barcodeField2.sendKeys(barcode); // Mismo código de barras

                WebElement productNameField2 = driver.findElement(By.name("name"));
                productNameField2.clear();
                productNameField2.sendKeys(productName2);

                WebElement categoryField2 = driver.findElement(By.name("category"));
                categoryField2.clear();
                categoryField2.sendKeys(category2);

                WebElement wholesalePriceField2 = driver.findElement(By.name("cost_price"));
                wholesalePriceField2.clear();
                wholesalePriceField2.sendKeys(wholesalePrice2);

                WebElement retailPriceField2 = driver.findElement(By.name("unit_price"));
                retailPriceField2.clear();
                retailPriceField2.sendKeys(retailPrice2);

                WebElement quantityField2 = driver.findElement(By.name("quantity_1"));
                quantityField2.clear();
                quantityField2.sendKeys(quantity2);

                // Paso 6: Presionar el botón "Submit" para el segundo producto
                WebElement submitButton2 = driver.findElement(By.id("submit"));
                submitButton2.click();

                // Paso 7: Verificar que se muestra un mensaje de error indicando código de
                // barras duplicado
                WebElement errorMessage = wait
                                .until(ExpectedConditions.visibilityOfElementLocated(By.id("error_message_box")));
                Assert.assertTrue(
                                "El mensaje de error no se mostró al intentar agregar un producto con código de barras duplicado.",
                                errorMessage.isDisplayed());
                Assert.assertTrue(
                                "El mensaje de error no contiene el texto esperado sobre el código de barras duplicado.",
                                errorMessage.getText().contains("Item Number is already present in the database."));
        }

        /**
         * Caso de Prueba No. 067
         * Valida que se puede crear un nuevo Cashup llenando únicamente los campos
         * obligatorios del formulario,
         * "Opened By" y "Closed By".
         */
        @Test
        public void casoPruebaN_067() {
                // Precondición: Navegar a la sección "Cashups"
                WebElement cashupsLink = wait
                                .until(ExpectedConditions
                                                .visibilityOfElementLocated(By.cssSelector("a[title='Cashups']")));
                cashupsLink.click();

                // Paso 1: Esperar a que la tabla de Cashups sea visible
                WebElement tabla = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("table")));
                System.out.println("tabla: " + tabla.getAttribute("class"));

                // Paso 2: Verificar si la tabla está vacía, en caso contrario, eliminar Cashups
                // existentes
                WebElement tableRow = tabla.findElement(By.xpath("//tbody/tr[1]"));
                WebElement tableCol = tabla.findElement(By.xpath("//td[1]"));
                String colText = tableCol.getText();
                System.out.println("Text: " + colText);
                if (colText.equals("There are no Cashups to display")) {
                        System.out.println("No hay Cashups para eliminar.");
                } else {
                        // Seleccionar todos los Cashups existentes
                        WebElement selectAll = wait
                                        .until(ExpectedConditions.elementToBeClickable(By.name("btSelectAll")));
                        selectAll.click();
                        // Verificar que el botón de eliminar está habilitado
                        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("delete")));
                        deleteButton.click();
                        // Manejar la alerta de confirmación
                        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
                        alert.accept();
                        System.out.println("Todos los Cashups existentes fueron eliminados.");
                }

                // Paso 3: Seleccionar el botón "New Cashup"
                WebElement newCashupButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("button[title='New Cashup']")));
                newCashupButton.click();

                // Paso 4: Esperar a que el formulario sea visible
                WebElement cashupForm = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("/html/body/div[*]/div/div")));

                // Paso 5: Seleccionar fecha y hora en "Open Date" y "Close Date"
                WebElement openDateField = wait
                                .until(ExpectedConditions.visibilityOfElementLocated(By.id("open_date")));
                WebElement closeDateField = wait
                                .until(ExpectedConditions.visibilityOfElementLocated(By.id("close_date")));
                openDateField.clear();
                openDateField.sendKeys("11/21/2024 00:22:33");
                closeDateField.clear();
                closeDateField.sendKeys("11/22/2024 00:22:33");

                // Paso 6: Seleccionar usuario en "Opened By"
                WebElement openedByDropdown = wait
                                .until(ExpectedConditions.visibilityOfElementLocated(By.id("open_employee_id")));
                Select openedBySelect = new Select(openedByDropdown);
                openedBySelect.selectByVisibleText("John Doe");

                // Paso 7: Seleccionar usuario en "Closed By"
                WebElement closedByDropdown = wait
                                .until(ExpectedConditions.visibilityOfElementLocated(By.id("close_employee_id")));
                Select closedBySelect = new Select(closedByDropdown);
                closedBySelect.selectByVisibleText("John Doe");

                // Paso 8: Guardar el formulario
                WebElement saveButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submit")));
                saveButton.click();

                // Resultado esperado: Validar que el Cashup fue agregado correctamente
                tableRow = tabla.findElement(By.xpath("//tbody/tr[1]"));
                tableCol = tableRow.findElement(By.xpath("//td[4]"));
                colText = tableCol.getText();
                Assert.assertTrue(colText.equals("Jhon Doe"));
        }

        /**
         * Caso de Prueba No. 069
         * Verifica el despliegue del mensaje "There are no Cashups to display" cuando
         * no existen resultados
         * para mostrar en la lista de Cashups.
         */
        @Test
        public void casoPruebaN_069() {
                // Precondición: Navegar a la sección "Cashups"
                WebElement cashupsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("a[title='Cashups']")));
                cashupsLink.click();

                // Paso 1: Escribir en la barra de búsqueda un término sin coincidencias
                WebElement searchBar = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector(
                                                "#table_holder > div.bootstrap-table.bootstrap3 > div.fixed-table-toolbar > div.pull-right.search.input-group > input")));
                searchBar.click();
                searchBar.sendKeys("?!");

                // Paso 2: Revisar que la tabla de Cashups sea visible
                WebElement tabla = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.id("table")));
                // System.out.println("tabla: " + tabla.getAttribute("class"));

                // Paso 3: Acceder al mensaje en la primera fila y primera columna de la tabla
                // de Cashups
                WebElement tableRow = tabla.findElement(By.xpath("//tbody/tr[1]"));
                WebElement tableCol = tableRow.findElement(By.xpath("//td[1]"));
                String colClasses = tableCol.getText();
                // System.out.println("Text: " + colClasses);

                // Resultado esperado: Validar que se muestra el mensaje de ausencia de Cashups
                String colText = tableCol.getText();
                Assert.assertTrue(colText.equals("There are no Cashups to display"));
        }
}
