package Paginas;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CompletePage {
	@FindBy(id="react-burger-menu-btn")
	WebElement cuadroDeSalida;
	
	@FindBy(id="logout_sidebar_link")
	WebElement logout;
	
	//Constructor
	public CompletePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	//Acciones
	public void clickCuadroDeSalida() {
		cuadroDeSalida.click();
	}
	
	public void esperaLogout(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Tiempo máximo
		wait.until(ExpectedConditions.elementToBeClickable(logout));
	}
	public void clickLogout() {
		logout.click();
	}
	
}
