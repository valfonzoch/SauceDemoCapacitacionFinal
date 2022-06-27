package Paginas;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {
	
	//Encontrar los elementos
	@FindBy(name="checkout")
	WebElement checkout;
	
	//Constructor
	public CheckoutPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	//Acciones
	public void clickCheckout() {
		checkout.click();
	}
}
