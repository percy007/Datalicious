import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageActionForDataLicious {

	WebDriver driver;
	PageObjectForGoogle pog;
	WebDriverWait wait;
	public PageActionForDataLicious(WebDriver driver) {
		this.driver=driver;
		pog= PageFactory.initElements(driver, PageObjectForGoogle.class);
		wait= new WebDriverWait(driver, 15);
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	}
	public String goTODatalicious(){
		pog.enterDataToSearchField();
		try{
			pog.clickonSearchButton();
		}catch(Exception e){
			pog.clickonSearchIcon();
		}
		pog.clickonOrganicSearch();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[text()='MARKETING DATA SPECIALISTS']")));
		
		
		return driver.getCurrentUrl();

	}






}
