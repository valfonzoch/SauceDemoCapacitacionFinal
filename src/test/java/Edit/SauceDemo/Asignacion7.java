package Edit.SauceDemo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utilities.DatosExcel;

public class Asignacion7 {
	// Variables
	String url = "https://www.saucedemo.com/";
	String driverPath = "..\\SauceDemo\\Drivers\\chromedriver.exe";
	WebDriver driver;

	// Abre la pagina y ejecuta algunas acciones antes de comenzar con las pruebas

	@BeforeSuite
	public void setUp() {

		System.setProperty("webdriver.chrome.driver", driverPath);

		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}

	@Test(dataProvider = "Datos Login Excel")
	public void generarOrden(String user, String password, String nombre, String apellido, String codigoPostal) {
		
		//PRIMERA PAGINA
		
		// Escribir Usuario
		
		driver.findElement(By.id("user-name")).clear();
		driver.findElement(By.id("user-name")).sendKeys(user);

		// Escribir Password
		driver.findElement(By.cssSelector("#password")).clear();
		driver.findElement(By.cssSelector("#password")).sendKeys(password);

		// Click en Login
		driver.findElement(By.name("login-button")).click();

		// Comprobar si el usuario se pudo loguear
		String urlEsperada = "https://www.saucedemo.com/inventory.html";
		Assert.assertEquals(driver.getCurrentUrl(), urlEsperada);
		
		//SEGUNDA PAGINA

		// Es necesaria una espera
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10)); // Tiempo máximo
		wait2.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-test.allthethings()-t-shirt-(red)"))); // Condicion de parada
																														
		//System.out.println("Fallo en seleccion de producto"); 

		WebElement producto = driver.findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)"));
		producto.click();

		// Hago click en el carrito de compras
		driver.findElement(By.cssSelector("div.page_wrapper div:nth-child(1) div.header_container:nth-child(1) div.primary_header div.shopping_cart_container:nth-child(3) > a.shopping_cart_link")).click();
			
		//TERCERA PAGINA

		// Hacer click en Checkout
		driver.findElement(By.id("checkout")).click();
		
		//CUARTA PAGINA

		// Completo mi informacion personal
		
		driver.findElement(By.name("firstName")).sendKeys(nombre);
		driver.findElement(By.cssSelector("#last-name")).sendKeys(apellido);
		driver.findElement(By.xpath("//input[@id='postal-code']")).sendKeys(codigoPostal);

		// Hacer click en Continue
			driver.findElement(By.id("continue")).click();
		try {
		String urlPosicional = "https://www.saucedemo.com/checkout-step-one.html";
		Assert.assertNotEquals(driver.getCurrentUrl(), urlPosicional);
		} catch (AssertionError e) {
			driver.findElement(By.id("react-burger-menu-btn")).click();
			WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10)); // Tiempo máximo
			wait3.until(ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link"))); // Condicion de parada
			driver.findElement(By.id("logout_sidebar_link")).click();
		}
		
		//QUINTA PAGINA
		
		// Hacer click en Finish
		driver.findElement(By.id("finish")).click();

		String urlFinal = "https://www.saucedemo.com/checkout-complete.html";
		Assert.assertEquals(driver.getCurrentUrl(), urlFinal);
		
		//ULTIMA PAGINA

		// Desloguear al usuario
		driver.findElement(By.id("react-burger-menu-btn")).click();
		WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10)); // Tiempo máximo
		wait3.until(ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link"))); // Condicion de parada
		driver.findElement(By.id("logout_sidebar_link")).click();
		
	}

	@AfterSuite
	public void tearDown() {
		driver.close();
	}

	@DataProvider(name = "Datos Login Excel")
	public Object[][] obtenerDatosExcel() throws Exception {
		return DatosExcel.leerExcel("..\\SauceDemo\\DatosHojaExcel\\DatosLogin.xlsx", "Hoja1");

	}

}
