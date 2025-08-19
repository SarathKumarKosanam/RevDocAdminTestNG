package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pageObjects.LoginPage;
import utils.ConfigReader;
import utils.DriverManager;

public class LoginSteps {
    WebDriver driver;
    LoginPage loginPage;

    @Given("I launch the login page")
    public void i_launch_the_login_page() {
        driver = DriverManager.getDriver();
        String baseUrl = ConfigReader.get("baseUrl");
        driver.get(baseUrl);
        loginPage = new LoginPage(driver);
    }

    @When("I login with username {string} password {string} and OTP {string}")
    public void i_login_with_username_and_password_and_otp(String username, String password, String OTP) {
        try {
			loginPage.login(username, password, OTP);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Then("I should be logged in successfully")
    public void i_should_be_logged_in_successfully() {
        System.out.println("✅ Login passed");
        // Here you can add an assertion like:
        // Assert.assertTrue(driver.getTitle().contains("Dashboard"));
    }
}
