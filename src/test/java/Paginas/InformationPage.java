package Paginas;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InformationPage {
	
	//Elementos
	@FindBy(id= "first-name")
	WebElement txtName;
	
	@FindBy(id="last-name")
	WebElement txtLastName;
	
	@FindBy(name="postalCode")
	WebElement txtPostalCode;
	
	@FindBy(id="continue")
	WebElement btnContinuar;
	
	@FindBy(id="react-burger-menu-btn")
	WebElement cuadroDeSalida;
	
	@FindBy(id="logout_sidebar_link")
	WebElement logout;
	
	//Constructor
	public InformationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//Acciones
	public void writeName(String name) {
		txtName.sendKeys(name);
	}
	public void writeLastName(String lastName) {
		txtLastName.sendKeys(lastName);
	}
	public void writePostalCode(String code) {
		txtPostalCode.sendKeys(code);
	}
	
	public void clickContinue() {
		btnContinuar.click();
	}
	public void clickCuadroDeSalida() {
		cuadroDeSalida.click();
	}
	public void clickLogout() {
		logout.click();
	}
}

