package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class DriverFactory {

    public static WebDriver initializeDriver() {
        String browser  = ConfigReader.get("browser").toLowerCase();
        boolean headless = ConfigReader.getBool("headless", false);
        int implicitSec  = ConfigReader.getInt("implicitWaitSec", 0);
        int pageLoadSec  = ConfigReader.getInt("pageLoadTimeoutSec", 60);

        WebDriver driver;

        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions ch = new ChromeOptions();
                ch.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                if (headless) ch.addArguments("--headless=new");
                ch.addArguments("--start-maximized", "--remote-allow-origins=*");
                driver = new ChromeDriver(ch);
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions ff = new FirefoxOptions();
                if (headless) ff.addArguments("-headless");
                driver = new FirefoxDriver(ff);
                driver.manage().window().maximize();
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions ed = new EdgeOptions();
                if (headless) ed.addArguments("--headless=new");
                driver = new EdgeDriver(ed);
                driver.manage().window().maximize();
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadSec));
        if (implicitSec > 0) {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitSec));
        }

        DriverManager.setDriver(driver);
        return driver;
    }

    public static void quitDriver() {
        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            try { driver.quit(); } finally { DriverManager.unload(); }
        }
    }
}
