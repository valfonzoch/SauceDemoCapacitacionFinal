package Paginas;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	//Elementos de la pagina Web
	@FindBy(id="user-name")
	 WebElement txtuserName;
	 
	@FindBy(id="password")
	 WebElement txtPassword;
	 
	@FindBy(name="login-button")
	 WebElement btnLogin;
	
	//Constructor
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		
	}
	
	//Acciones sobre los elementos de la pagina Web
	
	public void clearUser() {
		txtuserName.clear();
	}
	
	public void escribirUsuario(String user) {
		txtuserName.sendKeys(user);
	}
	public void clearPassword() {
		txtPassword.clear();
	}
	
	public void escribirPassword(String password) {
		txtPassword.sendKeys(password);	
	}
	
	public void hacerClickEnLogin() {
		btnLogin.click();
	}
}
