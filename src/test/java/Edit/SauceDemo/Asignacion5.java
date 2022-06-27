package Edit.SauceDemo;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import Utilities.CapturaEvidencia;

public class Asignacion5 {
	//Variables
	String url = "https://www.saucedemo.com/";
	String driverPath = "..\\SauceDemo\\Drivers\\chromedriver.exe";
	WebDriver driver;
	File pantalla;
	String dirEvidencias = "..\\SauceDemo\\Evidencias\\";
	String nombreDocumento = "Evidencias.docx";
	
	// Abre la pagina y ejecuta algunas acciones antes de comenzar con las pruebas
	

	@BeforeSuite
	public void setUp() {   
		
		System.setProperty("webdriver.chrome.driver", driverPath);
		
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}
	
	@Test(description="CP Iniciar Seccion", priority=1)
	public void iniciarSeccion() throws IOException, InvalidFormatException, InterruptedException {
	
		//Para ponerle Titulo al documento de Evidencias
		CapturaEvidencia.escribirTituloEnDocumento(dirEvidencias + nombreDocumento, "Evidencias Proyecto SauceDemo", 18);
		//Para Poner las capturas en el documento 
		CapturaEvidencia.capturarPantallaEnDocumento(driver, dirEvidencias + "imagenPantalla.jpg", dirEvidencias + nombreDocumento, "Pantalla Principal");
			
		//Escribir Usuario
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		
		//Escribir Password
		driver.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
		
		//Para capturar imagenes sueltas
		
		pantalla = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(pantalla, new File(dirEvidencias + "01_pantalla_principal.jpg"));
		
		//Captura en doc
		CapturaEvidencia.capturarPantallaEnDocumento(driver, dirEvidencias + "imagenPantalla.jpg", dirEvidencias + nombreDocumento, "Login");
		
		
		//Click en Login
		driver.findElement(By.name("login-button")).click();
		
		//Comprobacion
		
		String tituloEsperado = "Swag Labs";
		String tituloActual = driver.getTitle();
		Assert.assertEquals(tituloActual, tituloEsperado);
				
	}
	
	@Test(description="CP Compra", priority=2)
	public void compra () throws InvalidFormatException, IOException, InterruptedException {
		
	// Es necesaria una espera
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Tiempo máximo
		wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-test.allthethings()-t-shirt-(red)"))); // Condicion de parada
		
		WebElement producto = driver.findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)"));
		producto.click();
		
		//Captura doc.
		CapturaEvidencia.capturarPantallaEnDocumento(driver, dirEvidencias + "imagenPantalla.jpg", dirEvidencias + nombreDocumento, "Inventario");
		
		//Hago click en el carrito de compras
		driver.findElement(By.cssSelector("div.page_wrapper div:nth-child(1) div.header_container:nth-child(1) div.primary_header div.shopping_cart_container:nth-child(3) > a.shopping_cart_link")).click();
		
		// Captura doc
		CapturaEvidencia.capturarPantallaEnDocumento(driver, dirEvidencias + "imagenPantalla.jpg", dirEvidencias + nombreDocumento, "Checkout");
		
		//Hacer click en Checkout
		driver.findElement(By.id("checkout")).click();
		
		//Completo mi informacion personal 
		
		driver.findElement(By.name("firstName")).sendKeys("Sara");
		driver.findElement(By.cssSelector("#last-name")).sendKeys("Lara");
		driver.findElement(By.xpath("//input[@id='postal-code']")).sendKeys("14044");
		
		//Captura doc
		CapturaEvidencia.capturarPantallaEnDocumento(driver, dirEvidencias + "imagenPantalla.jpg", dirEvidencias + nombreDocumento, "Checkout your information");
		
		// Hacer click en Continue
		driver.findElement(By.id("continue")).click();
		
		//Captura doc.
		CapturaEvidencia.capturarPantallaEnDocumento(driver, dirEvidencias + "imagenPantalla.jpg", dirEvidencias + nombreDocumento, "Checkout Overview");
		
		// Hacer click en Finish
		driver.findElement(By.id("finish")).click();
		
		//Captura doc.
		CapturaEvidencia.capturarPantallaEnDocumento(driver, dirEvidencias + "imagenPantalla.jpg", dirEvidencias + nombreDocumento, "Checkout Complete");
		
		//Comprobar que estamos en la pagina de checkout Complete
		String urlEsperada = "https://www.saucedemo.com/checkout-complete.html";
		String urlActual = driver.getCurrentUrl();
		
		Assert.assertEquals(urlActual, urlEsperada);
		
	}
	
	//Para cerrar la pagina luego de ejecutados los 2 metodos 
	


	@AfterSuite
	public void tearDown() {   
		driver.close();
	}

}
