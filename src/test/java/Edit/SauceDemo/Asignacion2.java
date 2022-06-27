package Edit.SauceDemo;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Asignacion2 {
	String URL ="https://www.saucedemo.com/";
	String ChromeDriverPath = "..\\SauceDemo\\Drivers\\chromedriver.exe";
	String FirefoxDriverPath = "..\\SauceDemo\\Drivers\\geckodriver.exe";

	@Test
	public void InicioDeSesion () {
		
	//Paso1: Indicar donde estan los drivers del navegador
		
		System.setProperty("webdriver.chrome.driver", ChromeDriverPath);
		
	//Paso2: Abrir el navegador de la pagina a probar
		
	WebDriver navegador = new ChromeDriver ();
	navegador.get(URL);
	//Paso3: Escribir el UserName
	
	WebElement NombreDeUsuario = navegador.findElement(By.id("user-name"));
	NombreDeUsuario.sendKeys("standard_user");
		
	//Paso4: Escribir el Password
	
	WebElement Password = navegador.findElement(By.id("password"));
	Password.sendKeys("secret_sauce");
	
	//Paso5: Hacer clic en Login
	
	WebElement Login = navegador.findElement(By.id("login-button"));
	Login.sendKeys(Keys.ENTER);
		
		
	//Paso5: Cerrar navegador 
	navegador.close();
		
	}
	
	@Test
	public void InicioDeSesionFirefox () {
		
	//Paso1: Indicar donde estan los drivers del navegador
		
		System.setProperty("webdriver.gecko.driver", FirefoxDriverPath);
		
	//Paso2: Abrir el navegador de la pagina a probar
		
	WebDriver navegador = new FirefoxDriver ();
	navegador.get(URL);
	//Paso3: Escribir el UserName
	
	WebElement NombreDeUsuario = navegador.findElement(By.id("user-name"));
	NombreDeUsuario.sendKeys("standard_user");
		
	//Paso4: Escribir el Password
	
	WebElement Password = navegador.findElement(By.id("password"));
	Password.sendKeys("secret_sauce");
	
	//Paso5: Hacer clic en Login
	
	WebElement Login = navegador.findElement(By.id("login-button"));
	Login.sendKeys(Keys.ENTER);
		
		
	//Paso5: Cerrar navegador 
	navegador.close();
		
	}


}
