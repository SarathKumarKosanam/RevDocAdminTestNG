package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.DriverManager;

public class LoginPage {
    WebDriver driver;

    @FindBy(id = "email") WebElement username;
    @FindBy(id = "password") WebElement password;
    @FindBy(xpath =  "//button[@type=\"submit\"]") WebElement loginButton;
    @FindBy(id = "user_input_code") WebElement OTPField;
    @FindBy(xpath="//button[@class='ant-btn css-5uvb3z ant-btn-default ant-btn-color-default ant-btn-variant-outlined revdocButton filled']") WebElement VerifyOTPButton;

  
    public LoginPage(WebDriver driver) {
    	this.driver = driver;
        PageFactory.initElements(driver, this);
    }

	public void login(String uname, String pwd,String OTP) throws InterruptedException {
        username.sendKeys(uname);
        password.sendKeys(pwd);
        loginButton.click();
       Thread.sleep(10000);
        OTPField.sendKeys(OTP);
        VerifyOTPButton.click();
    }
    
    @FindBy(id = "email") WebElement emailField;
    @FindBy(id = "password") WebElement passwordField;
   // @FindBy(xpath = "//button[@type='submit']") WebElement loginButton1;
    @FindBy(id = "email_help") WebElement emailError;
    @FindBy(id = "password_help") WebElement passwordError;
    @FindBy(xpath = "//div[contains(text(),'email and password combination')]") WebElement loginPopupError;

    public void clickLogin() {
    	loginButton.click();
    }

    public void enterEmail(String email) {
        emailField.sendKeys(Keys.CONTROL + "a");
        emailField.sendKeys(Keys.DELETE);
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(Keys.CONTROL + "a");
        passwordField.sendKeys(Keys.DELETE);
        passwordField.sendKeys(password);
    }

    public String getEmailErrorMsg() {
        return emailError.getText();
    }

    public String getPasswordErrorMsg() {
        return passwordError.getText();
    }
    public WebElement getInvalidLoginError() throws InterruptedException {  	
    	Thread.sleep(5000);
    	
    	int dur = 10;
    	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(dur));
    	
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='The email and password combination you entered is incorrect.']")));
    }
}
