package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.ProcedureConfigurationPage;
import utils.ConfigReader;
import utils.DriverManager;

public class UploadThePDFInAddNewProcedureScreenSteps {
	
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    ProcedureConfigurationPage procedureConfigurationPage;

    @Given("LogIn to the Admin")
    public void ILoginToTheAdmin() throws Exception {
//        ConfigReader.loadProperties();
        driver = DriverManager.getDriver();
        driver.get(ConfigReader.get("baseUrl"));
        loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"), ConfigReader.get("OTP"));
    }
    
    @When("I should be able to navigate to the procedure configuration screen and upload PDF")
    public void NavigatingToProcedureConfiguration() {
        homePage = new HomePage(driver);
        procedureConfigurationPage = new ProcedureConfigurationPage(driver);
        homePage.clickingOnProcedureManagementIcon();
        homePage.clickingOnProcedureSetup();
        procedureConfigurationPage.clickingOnAddNewProcedrueButton();	  
    }

    @Then("I should be able to upload the PDF successfully")
    public void pdfUpload() {
        procedureConfigurationPage.clickingOnUploadPDFButton();

        boolean isUploaded = procedureConfigurationPage.isPDFUploadedSuccessfully();

        // ✅ TestNG assertion (integrates with reports)
        Assert.assertTrue(isUploaded, "PDF Upload verification failed.");
    }
}
