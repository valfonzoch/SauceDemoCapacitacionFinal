package Paginas;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OverviewPage {
	
	//Elementos
	
	@FindBy(name="finish")
	WebElement btnFinish;
	
	//Constructor
	public OverviewPage(WebDriver driver) {
		PageFactory.initElements(driver, this);	
	}
	//Acciones
	public void clickFinish() {
		btnFinish.click();
	}
	
}
