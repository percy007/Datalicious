import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DataliciousTEst {
	WebDriver driver;
	PageActionForDataLicious pad;
	NetworkCapture nc;
		
	
	@BeforeClass
	public void beforClass(){
		driver= new FirefoxDriver();
		pad= PageFactory.initElements(driver, PageActionForDataLicious.class);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.get("https://www.google.co.in");
		nc= PageFactory.initElements(driver, NetworkCapture.class);
	}


	@Test
	public void clickonGoogle() throws Exception{
		String url=pad.goTODatalicious();
		System.out.println(url);
		nc.setUp(driver, url);
	
	}

	@AfterClass
	public void afterClass(){
		
		driver.quit();
	}


}
