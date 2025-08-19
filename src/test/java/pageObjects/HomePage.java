package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;



public class HomePage {

	 WebDriver driver;
	 
	 public HomePage(WebDriver driver) {
	    	this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }
	 
	 @FindBy(xpath = "//span[contains(@class, 'ant-spin-dot ant-spin-dot-spin') ]") WebElement HomePageLoadingIcon;
	 @FindBy(xpath = "//div//span[text()='Procedure Mgmt.']") WebElement ProcedureManagementIcon;
	 @FindBy(xpath = "//div[span[text()='Procedure Setup']]") WebElement ProcedureSetup;
	 @FindBy(xpath = "//img[contains(@src, 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACsAAAAr')]") WebElement GroupMembershipIcon;
	 @FindBy(xpath = "//button//div[text()= '+ Add New']") WebElement AddNewButton;
	
	// Clicking on Procedure management
	 
	 public void waitdur() {
		 try {
			 Thread.sleep(3000);
		 }
		 catch(Exception e){}
	 }
	 
	public void clickingOnProcedureManagementIcon() {
				
		waitdur();
		ProcedureManagementIcon.click();
	}

	// Clicking on Procedure setUp

	public  void clickingOnProcedureSetup() {
		
		waitdur();
		ProcedureSetup.click();
	}

	// Clicking on Group Membership icon in the home page

	public  void clickingOnGroupMembership() {
		waitdur();
		GroupMembershipIcon.click();

	}

	// Clickon on Add New in the Group Membership page

	public  void clickingOnAddNewButton() {

		waitdur();
		AddNewButton.click();
	}

	// Home Page loading object

	public  String homePageLoading() {
		
		return HomePageLoadingIcon.getText();
	}

	
}
