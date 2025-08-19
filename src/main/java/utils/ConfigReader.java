package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static final Properties props = new Properties();

    static {
        String path = "src/test/resources/testdata/config.properties";
        try (FileInputStream fis = new FileInputStream(path)) {
            props.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties at " + path, e);
        }
    }

    public static String get(String key) {
        String val = System.getProperty(key); // allow -D overrides
        if (val != null && !val.trim().isEmpty()) return val.trim();
        val = props.getProperty(key);
        if (val == null) throw new RuntimeException("Key not found in config.properties: " + key);
        return val.trim();
    }

    public static int getInt(String key, int def) {
        String v = System.getProperty(key, props.getProperty(key));
        try { return v == null ? def : Integer.parseInt(v.trim()); }
        catch (NumberFormatException e) { return def; }
    }

    public static boolean getBool(String key, boolean def) {
        String v = System.getProperty(key, props.getProperty(key));
        return v == null ? def : Boolean.parseBoolean(v.trim());
    }
}
