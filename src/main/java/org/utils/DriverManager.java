package org.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class DriverManager {

    private static final Logger logger = LogManager.getLogger(DriverManager.class);
    private static AppiumDriver driver;
    private static AppiumDriverLocalService service;

    public static AppiumDriver initializeDriver(String platformName) throws MalformedURLException {
        try {
            // Start the Appium service
            logger.info("Starting Appium service");
            service = new AppiumServiceBuilder()
                    .withIPAddress(PropertyManager.getProperty("appium.ipAddress"))
                    .usingPort(Integer.parseInt(PropertyManager.getProperty("appium.port")))
                    .withArgument(GeneralServerFlag.BASEPATH, PropertyManager.getProperty("appium.basePath"))
                    .withTimeout(Duration.ofSeconds(60))
                    .build();
            service.start();

            String appiumServerURL = service.getUrl().toString();
            logger.info("Appium service started at " + appiumServerURL);

            // Initialize the driver based on the platform
            if (platformName.equalsIgnoreCase("Android")) {
                logger.info("Initializing Android driver");
                UiAutomator2Options androidOptions = new UiAutomator2Options()
                        .setDeviceName(PropertyManager.getProperty("deviceName"))
                        .setAppPackage(PropertyManager.getProperty("android.appPackage"))
                        .setAppActivity(PropertyManager.getProperty("android.appActivity"))
                        .setAutoGrantPermissions(true)
                        .setNoReset(true); // Set noReset to true
                driver = new AppiumDriver(new URL(appiumServerURL), androidOptions);
            } else if (platformName.equalsIgnoreCase("iOS")) {
                logger.info("Initializing iOS driver");
                XCUITestOptions iosOptions = new XCUITestOptions()
                        .setDeviceName(PropertyManager.getProperty("ios.deviceName"))
                        .setPlatformVersion(PropertyManager.getProperty("ios.platformVersion"))
                        .setBundleId(PropertyManager.getProperty("ios.bundleId"))
                        .setAutoAcceptAlerts(true)
                        .setNoReset(true); // Set noReset to true
                driver = new AppiumDriver(new URL(appiumServerURL), iosOptions);
            }

            logger.info(platformName + " driver initialized successfully.");
        } catch (MalformedURLException e) {
            logger.error("Malformed URL Exception: " + e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error("Failed to initialize driver: " + e.getMessage(), e);
            throw new RuntimeException("Driver initialization failed", e);
        }

        return driver;
    }

    public static void quitDriver() {
        try {
            if (driver != null) {
                logger.info("Quitting driver");
                driver.quit();
            }
            if (service != null) {
                logger.info("Stopping Appium service");
                service.stop();
            }
        } catch (Exception e) {
            logger.error("Error during driver/service shutdown: " + e.getMessage(), e);
        }
    }

    public static String getAppiumVersion() {
        try {
            logger.info("Retrieving Appium version");
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URL(service.getUrl().toString() + "/status").toURI())
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject jsonResponse = new JSONObject(response.body());
            String version = jsonResponse.getJSONObject("value").getJSONObject("build").getString("version");
            logger.info("Appium version retrieved: " + version);
            return version;
        } catch (Exception e) {
            logger.error("Failed to retrieve Appium version: " + e.getMessage(), e);
            return "Unknown Version";
        }
    }
}