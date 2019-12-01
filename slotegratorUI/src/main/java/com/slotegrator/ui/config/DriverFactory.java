package com.slotegrator.ui.config;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import static com.slotegrator.ui.config.DriverType.CHROME;
import static com.slotegrator.ui.config.DriverType.valueOf;

public class DriverFactory {

    private RemoteWebDriver webDriver;
    private DriverType selectedDriverType;
    private String currentTestName;
    private Properties properties;

    private final boolean useRemoteWebDriver = Boolean.getBoolean("remoteDriver");

    public void setTestName(String testname) {
        currentTestName = testname;
    }

    public DriverFactory() throws IOException {
        DriverType driverType = CHROME;

        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + ".properties";

        properties = new Properties();
        try {
            properties.load(new FileInputStream(appConfigPath));
        }
        catch (FileNotFoundException e) {
            System.out.println(appConfigPath + " not found, using defaults.");
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("default.properties");
            properties.load(inputStream);
        }

        Properties systemProperties = System.getProperties();

        // override with system properties
        properties.putAll(systemProperties);

        String browser = properties.getProperty("browser", driverType.toString()).toUpperCase();

        try {
            driverType = valueOf(browser);
        } catch (IllegalArgumentException ignored) {
            System.err.println("Unknown driver specified, defaulting to '" + driverType + "'...");
        } catch (NullPointerException ignored) {
            System.err.println("No driver specified, defaulting to '" + driverType + "'...");
        }
        selectedDriverType = driverType;
    }

    public RemoteWebDriver getDriver() throws MalformedURLException {
        if (null == webDriver) {
            instantiateWebDriver(selectedDriverType);
        }

        return webDriver;
    }

    public void quitDriver() {
        if (null != webDriver) {
            webDriver.quit();
            webDriver = null;
        }
    }

    private void instantiateWebDriver(DriverType driverType) throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        if (useRemoteWebDriver) {
            System.out.println("Connecting to Selenium Grid: " + useRemoteWebDriver);
            URL seleniumGridURL = new URL(properties.getProperty("gridURL"));
            String desiredBrowserVersion = properties.getProperty("desiredBrowserVersion");
            String desiredPlatform = properties.getProperty("desiredPlatform");

            if (null != desiredPlatform && !desiredPlatform.isEmpty()) {
                desiredCapabilities.setPlatform(Platform.valueOf(desiredPlatform.toUpperCase()));
            }

            if (null != desiredBrowserVersion && !desiredBrowserVersion.isEmpty()) {
                desiredCapabilities.setVersion(desiredBrowserVersion);
            }

            desiredCapabilities.setBrowserName(selectedDriverType.toString());
            webDriver = new RemoteWebDriver(seleniumGridURL, desiredCapabilities);
        } else {
            String headlessPropertyValue = properties.getOrDefault("headless", "false").toString();
            boolean headless = headlessPropertyValue.equalsIgnoreCase("true");
            desiredCapabilities.setCapability("headless", headless);
            System.out.println("Selected Browser: " + selectedDriverType + ", headless mode? " + headless);
            webDriver = driverType.getWebDriverObject(desiredCapabilities);
        }
    }
}