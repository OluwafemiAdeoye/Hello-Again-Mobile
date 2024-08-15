package org.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.screenrecording.CanRecordScreen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class DeviceUtil {

    private static final Logger logger = LogManager.getLogger(DeviceUtil.class);
    private final AppiumDriver driver;
    private final String platformName;

    public DeviceUtil(AppiumDriver driver) {
        this.driver = driver;
        this.platformName = driver.getCapabilities().getCapability("platformName").toString();
    }

    // Method to check if the device is online (Android and iOS)
    public boolean isDeviceOnline() {
        if (platformName.equalsIgnoreCase("Android")) {
            return isAndroidDeviceOnline();
        } else if (platformName.equalsIgnoreCase("iOS")) {
            return isIOSDeviceOnline();
        } else {
            logger.warn("Network check not implemented for this platform: " + platformName);
            return false;
        }
    }

    // Connectivity check for Android using shell command
    private boolean isAndroidDeviceOnline() {
        logger.info("Checking Android device connectivity...");
        try {
            // Execute shell command to check network connectivity
            Map<String, Object> commandArgs = new HashMap<>();
            commandArgs.put("command", "ping -c 1 google.com");
            String connectivityStatus = ((AndroidDriver) driver).executeScript("mobile: shell", commandArgs).toString();
            logger.info("Connectivity check result: " + connectivityStatus);
            return connectivityStatus.contains("1 packets transmitted, 1 received");
        } catch (Exception e) {
            logger.error("Failed to check Android connectivity: " + e.getMessage());
            return false;
        }
    }

    // Connectivity check for iOS using network-related capabilities
    private boolean isIOSDeviceOnline() {
        logger.info("Checking iOS device connectivity...");
        try {
            // Check if network connection is enabled (this can be expanded as needed)
            boolean isConnected = driver.getCapabilities().getCapability("networkConnectionEnabled").equals(true);
            logger.info("iOS device network connection status: " + isConnected);
            return isConnected;
        } catch (Exception e) {
            logger.error("Failed to check iOS connectivity: " + e.getMessage());
            return false;
        }
    }

    // Other methods...

    public String getDeviceDetails() {
        // Retrieve more device details from capabilities
        return driver.getCapabilities().toString();
    }

    public void stopScreenRecording(String fileName) {
        if (driver instanceof CanRecordScreen) {
            String videoBase64 = ((CanRecordScreen) driver).stopRecordingScreen();
            try {
                byte[] decodedBytes = Base64.getDecoder().decode(videoBase64);
                Path filePath = Path.of(fileName);
                Files.write(filePath, decodedBytes, StandardOpenOption.CREATE_NEW);
            } catch (IOException e) {
                logger.error("Failed to save screen recording: " + e.getMessage());
            }
        }
    }
}