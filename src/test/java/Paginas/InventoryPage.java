package Paginas;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InventoryPage {
	
	//Elementos web de la pagina
	
	@FindBy(id="add-to-cart-test.allthethings()-t-shirt-(red)")
	WebElement productoCamisa; 

	@FindBy(css="div.page_wrapper div:nth-child(1) div.header_container:nth-child(1) div.primary_header div.shopping_cart_container:nth-child(3) > a.shopping_cart_link")
	WebElement carritoDeCompras;
	
	@FindBy(id="react-burger-menu-btn")
	WebElement cuadroDeSalida;
	
	@FindBy(id="logout_sidebar_link")
	WebElement logout;
	
	//Constructor
	
	public InventoryPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//Acciones 
	
	public void esperaProducto(WebDriver driver) {
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10)); // Tiempo máximo
		wait2.until(ExpectedConditions.elementToBeClickable(productoCamisa));
	}
	
	public void seleccionarCamisa() {
		productoCamisa.click();	
	}
	
	public void clickCarritoCompras() {
		carritoDeCompras.click();
	}
	
	public void clickCuadroDeSalida() {
		cuadroDeSalida.click();
	}
	
	public void clickLogout() {
		logout.click();
	}
}