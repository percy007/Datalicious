import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageObjectForGoogle {

	@FindBy(id="lst-ib")
	public WebElement searchField;
	
	@FindBy(xpath=".//input[@value='Google Search']")
	public WebElement searchButton;
	
	@FindBy(id="_fZl")
	public WebElement searchIcon;
	
	@FindBy(xpath=".//*[@id='rso']//div[@class='g']/div/div/h3/a[text()='Datalicious: Marketing Data Specialists']")
	public WebElement firstOrganicResult;
	
	public void enterDataToSearchField(){
		searchField.sendKeys("Datalicious");
	}
	public void clickonSearchButton(){
		searchButton.click();
	}
	public void clickonSearchIcon(){
		searchIcon.click();
	}
	public void clickonOrganicSearch(){
		firstOrganicResult.click();
	}
	
	
	
	
	
}
