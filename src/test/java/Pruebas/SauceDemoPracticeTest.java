package Pruebas;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Paginas.CheckoutPage;
import Paginas.CompletePage;
import Paginas.HomePage;
import Paginas.InformationPage;
import Paginas.InventoryPage;
import Paginas.OverviewPage;
import Utilities.CapturaEvidencia;
import Utilities.DatosExcel;




public class SauceDemoPracticeTest {
			//Variables
			String url = "https://www.saucedemo.com/";
			String driverPath = "..\\SauceDemo\\Drivers\\chromedriver.exe";
			WebDriver driver;
			String dirEvidencias = "..\\SauceDemo\\Evidencias\\";
			String nombreDocumento = "EvidenciasFinal.docx";
			
			
			// Abre la pagina y ejecuta algunas acciones antes de comenzar con las pruebas
			

			@BeforeSuite
			public void setUp() throws InvalidFormatException, IOException, InterruptedException {   
				
				System.setProperty("webdriver.chrome.driver", driverPath);
				
				driver = new ChromeDriver();
				driver.get(url);
				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
				CapturaEvidencia.escribirTituloEnDocumento(dirEvidencias + nombreDocumento, "Evidencias Proyecto Final SauceDemo", 18);
				//Para Poner las capturas en el documento 
			}
			
		
		@Test(description="IniciarSeccion", priority= 1, dataProvider="Inicio de Sección")
		public void IniciarSeccion(String user, String password) throws InvalidFormatException, IOException, InterruptedException {
						
			HomePage inicio = new HomePage(driver);
			inicio.clearUser();
			inicio.escribirUsuario(user);
			inicio.clearPassword();
			inicio.escribirPassword(password);
			CapturaEvidencia.capturarPantallaEnDocumento(driver, dirEvidencias + "imagenPantalla.jpg", dirEvidencias + nombreDocumento, "Pantalla Principal.click");
			inicio.hacerClickEnLogin();	
			
			String urlEsperada = "https://www.saucedemo.com/inventory.html";
			Assert.assertEquals(driver.getCurrentUrl(), urlEsperada);
			
		}
		
		
		@Test(description="SeleccionProducto", priority= 2)
		public void SeleccionProducto() throws InvalidFormatException, IOException, InterruptedException {
			InventoryPage paginaDos = new InventoryPage(driver);
			
			paginaDos.esperaProducto(driver);
			paginaDos.seleccionarCamisa();
			CapturaEvidencia.capturarPantallaEnDocumento(driver, dirEvidencias + "imagenPantalla.jpg", dirEvidencias + nombreDocumento, "Seleccion de Producto");
			paginaDos.clickCarritoCompras();
			
			
		}
		@Test(description="Checkout", priority= 3)
		public void Checkout() throws InvalidFormatException, IOException, InterruptedException {
			CheckoutPage paginaTres = new CheckoutPage(driver);
			CapturaEvidencia.capturarPantallaEnDocumento(driver, dirEvidencias + "imagenPantalla.jpg", dirEvidencias + nombreDocumento, "Checkout");
			paginaTres.clickCheckout();
			
			
		}
		@Test(description="PersonalInformation", priority= 4, dataProvider="DatosPersonales")
		public void PersonalInformation(String name, String lastName, String code) throws InvalidFormatException, IOException, InterruptedException {
			InformationPage paginaCuatro = new InformationPage(driver);
			
			paginaCuatro.writeName(name);
			paginaCuatro.writeLastName(lastName);
			paginaCuatro.writePostalCode(code);
			CapturaEvidencia.capturarPantallaEnDocumento(driver, dirEvidencias + "imagenPantalla.jpg", dirEvidencias + nombreDocumento, "Personal Infomation");
			paginaCuatro.clickContinue();
			
			
			try {
			String urlPosicional = "https://www.saucedemo.com/checkout-step-one.html";
			Assert.assertNotEquals(driver.getCurrentUrl(), urlPosicional);
			}catch (AssertionError e) {
				CapturaEvidencia.capturarPantallaEnDocumento(driver, dirEvidencias + "imagenPantalla.jpg", dirEvidencias + nombreDocumento, "Personal Infomation Error");
				driver.findElement(By.id("react-burger-menu-btn")).click();
				WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10)); // Tiempo máximo
				wait3.until(ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link"))); // Condicion de parada
				driver.findElement(By.id("logout_sidebar_link")).click();
			}
		}
		@Test(description="ClickFinish", priority= 5)
		public void ClickFinish() throws InvalidFormatException, IOException, InterruptedException {
			OverviewPage paginaCinco = new OverviewPage(driver);
			
			CapturaEvidencia.capturarPantallaEnDocumento(driver, dirEvidencias + "imagenPantalla.jpg", dirEvidencias + nombreDocumento, "Finish");
			paginaCinco.clickFinish();
				
			String urlFinal = "https://www.saucedemo.com/checkout-complete.html";
			Assert.assertEquals(driver.getCurrentUrl(), urlFinal);
		}
		@Test(description="Logout", priority= 6)
		public void Logout() throws InvalidFormatException, IOException, InterruptedException {
			CompletePage paginaSeis = new CompletePage(driver);
			
			paginaSeis.clickCuadroDeSalida();
			paginaSeis.esperaLogout(driver);
			CapturaEvidencia.capturarPantallaEnDocumento(driver, dirEvidencias + "imagenPantalla.jpg", dirEvidencias + nombreDocumento, "Logout");
			paginaSeis.clickLogout();
			
			CapturaEvidencia.capturarPantallaEnDocumento(driver, dirEvidencias + "imagenPantalla.jpg", dirEvidencias + nombreDocumento, "Logout2");
			
		}
		
		@AfterSuite
		public void tearDown() {
			driver.close();
		}

		@DataProvider(name = "Inicio de Sección")
		public Object[][] obtenerDatosExcel() throws Exception {
			return DatosExcel.leerExcel("..\\SauceDemo\\DatosHojaExcel\\Nombre.xlsx", "Hoja1");

		}
		@DataProvider(name = "DatosPersonales")
		public Object[][] obtenerDatosExcel2() throws Exception {
			return DatosExcel.leerExcel("..\\SauceDemo\\DatosHojaExcel\\DatosPersonales.xlsx", "Hoja1");

		}
}
