package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import utils.DriverFactory;
import utils.DriverManager;
import utils.ScreenshotUtil;

public class Hooks {

    @Before
    public void setUp() {
        WebDriver driver = DriverFactory.initializeDriver();
        DriverManager.setDriver(driver);
    }

    @After
    public void tearDown(Scenario scenario) {
        WebDriver driver = DriverManager.getDriver();
        if (driver != null && scenario.isFailed()) {
            byte[] bytes = ScreenshotUtil.captureForCucumberAttach(driver, scenario.getName());
            scenario.attach(bytes, "image/png", "Failure Screenshot");
        }
        DriverFactory.quitDriver();
    }
}
