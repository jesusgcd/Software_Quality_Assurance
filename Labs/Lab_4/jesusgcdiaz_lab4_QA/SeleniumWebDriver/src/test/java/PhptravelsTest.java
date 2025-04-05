import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class PhptravelsTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        System.setProperty("webdriver.edge.driver", "./src/test/resources/edgedriver/msedgedriver.exe");
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.phptravels.net/");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testExisteAccount() {
        WebElement accountDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/header/div/div[2]/div[2]/ul/li[3]/a")));

        Assert.assertTrue("El elemento 'Account' no está visible en la barra de navegación.", accountDropdown.isDisplayed());
        
        accountDropdown.click();

        WebElement loginOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Login")));
        WebElement signupOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Signup")));

        Assert.assertTrue("La opción 'Login' no está visible en el menú desplegable.", loginOption.isDisplayed());
        Assert.assertTrue("La opción 'Signup' no está visible en el menú desplegable.", signupOption.isDisplayed());
    }
    
    
    @Test
    public void testNavegarAlLogin() throws InterruptedException {
    	WebElement accountDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/header/div/div[2]/div[2]/ul/li[3]/a")));
        accountDropdown.click();
        WebElement loginLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Login")));
        loginLink.click();

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        WebElement passwordField = driver.findElement(By.id("password"));
        
        emailField.sendKeys("user@phptravels.com");  
        passwordField.sendKeys("demouser");    
        
        WebElement botonLogin = driver.findElement(By.id("submitBTN"));
        botonLogin.click();

        WebElement dashboardElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/main/div/div/div/div[1]/div/div/div/div[1]/img")));
        Assert.assertTrue("Inicio de sesión fallido, no se encontró el elemento esperado en el dashboard.", dashboardElement.isDisplayed());
    }

    @Test
    public void testNavegarAlSignup() throws InterruptedException {
    	WebElement accountDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/header/div/div[2]/div[2]/ul/li[3]/a")));
        accountDropdown.click();
        WebElement loginLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Signup")));
        loginLink.click();

        WebElement botonSubmitBTN = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submitBTN")));

        Assert.assertTrue("El elemento 'Account' no está visible en la barra de navegación.", botonSubmitBTN.isDisplayed());
    }
    
    
    @Test
    public void testCambiarIdiomaAGermany() throws InterruptedException {
    	WebElement accountDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/header/div/div[2]/div[2]/ul/li[1]/a")));
        accountDropdown.click();
        
        WebElement idiomaGermany = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Germany")));
        idiomaGermany.click();

        WebElement KontoDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/header/div/div[2]/div[2]/ul/li[3]/a")));

        Assert.assertTrue("El elemento 'Konto' no está visible en la barra de navegación.", KontoDropdown.isDisplayed());
        
    }
    
    @Test
    public void testCambiarMonedaAUnitedKingdom() throws InterruptedException {
    	WebElement accountDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/header/div/div[2]/div[2]/ul/li[2]/a")));
        accountDropdown.click();
        
        WebElement modenaGBP = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/header/div/div[2]/div[2]/ul/li[2]/ul/li[2]/a")));
        modenaGBP.click();

        WebElement cambioGBP = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("GBP")));

        Assert.assertTrue("La modena no cambio a GBP.", cambioGBP.isDisplayed());
        
    }
    
    @Test
    public void testBuscarHoteles() {
        WebElement hotelsLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Hotels")));
        hotelsLink.click();

        WebElement divHoteles = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/main")));
        Assert.assertTrue("Featured Hotels no está visible", divHoteles.isDisplayed());
    }
    
    @Test
    public void testFiltrarHoteles() throws InterruptedException {
    	WebElement HoterlesCombobox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/main/div[1]/div[2]/div[2]/div/div[2]/div/div/div[1]/form/div/div[1]/div[1]/span/span[1]/span")));
    	HoterlesCombobox.click();
        
        WebElement ciudadSeleccionada = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/span/span/span[2]/ul/div/div[1]/div/strong")));
        ciudadSeleccionada.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/main/div[1]/div[2]/div[2]/div/div[2]/div/div/div[1]/form/div/div[5]/button"))).click();
        
        WebElement exitoEnBusqueda = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("hotelsFilter")));

        Assert.assertTrue("La modena no cambio a GBP.", exitoEnBusqueda.isDisplayed());
        
    }

    
    @Test
    public void testTramitarVisa() throws InterruptedException {
    	WebElement selecionarVisa = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/main/div[1]/div[2]/div[2]/div/div[2]/ul/li[5]/button")));
    	selecionarVisa.click();
    	
    	WebElement paisOrigenCombobox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/main/div[1]/div[2]/div[2]/div/div[2]/div/div/div[5]/form/div/div[1]/div[1]/div[2]/span/span[1]/span")));
    	paisOrigenCombobox.click();
        
        WebElement paisOrigen = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/span/span/span[2]/ul/li[3]")));
        paisOrigen.click();
        
        WebElement paisDestidoCombobox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/main/div[1]/div[2]/div[2]/div/div[2]/div/div/div[5]/form/div/div[2]/div[1]/div[2]/span/span[1]/span")));
        paisDestidoCombobox.click();

        WebElement paisDestido = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/span/span/span[2]/ul/li[51]")));
        paisDestido.click();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/main/div[1]/div[2]/div[2]/div/div[2]/div/div/div[5]/form/div/div[4]/button"))).click();
      
        WebElement botonSubmitVisa = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submit")));

        Assert.assertTrue("El formulario de Visa no esta visible.", botonSubmitVisa.isDisplayed());
        
    }
}
