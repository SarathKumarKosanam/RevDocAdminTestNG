package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    private static final String SCREENSHOT_DIR = "target/ExtentReports/screenshots";

    public static byte[] captureForCucumberAttach(WebDriver driver, String scenarioName) {
        // Raw bytes for scenario.attach(...)
        byte[] bytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        // Also save a copy to disk for Extent
        saveToDisk(bytes, scenarioName);
        return bytes;
    }

    public static String saveToDisk(byte[] bytes, String scenarioName) {
        String ts = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());
        String safeName = scenarioName.replaceAll("[^a-zA-Z0-9\\-_.]", "_");
        File dir = new File(SCREENSHOT_DIR);
        if (!dir.exists()) dir.mkdirs();
        File out = new File(dir, safeName + "_" + ts + ".png");
        try {
            Files.write(out.toPath(), bytes);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save screenshot to " + out.getAbsolutePath(), e);
        }
        return out.getAbsolutePath();
    }
}
