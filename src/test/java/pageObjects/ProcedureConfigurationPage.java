package pageObjects;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProcedureConfigurationPage {
 WebDriver driver;
	 
	 public ProcedureConfigurationPage(WebDriver driver) {
	    	this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }
	 @FindBy(xpath = "//button[span[text()='+ Add New Procedure']]") WebElement AddNewProcedrueButton;
	 @FindBy(xpath = "//input[@id = \"procedurePdf\"]") WebElement UploadPDFButton;

	 public void waitdur() {
		 try {
			 Thread.sleep(3000);
		 }
		 catch(Exception e){}
	 }
	 
	 // Clicking on Add New Procedure Button
	 public void clickingOnAddNewProcedrueButton() {
		 waitdur();
		 AddNewProcedrueButton.click();
		}
	 // Uploading test PDF
	 
	 public void clickingOnUploadPDFButton() {
		 waitdur();
		 UploadPDFButton.sendKeys("/RevDocAdminTestNG/src/test/resources/testfiles/Test PDF file.pdf");
		}
	 public boolean isPDFUploadedSuccessfully() {
		    waitdur();
		    WebElement uploadedPDF = driver.findElement(By.xpath("//span[text() = \" File selected\"]"));
		    return uploadedPDF.isDisplayed(); // Will throw if element not found
		}
}
