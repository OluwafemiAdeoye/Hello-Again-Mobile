package org.utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ScreenshotUtil {

    private final AppiumDriver driver;

    // Constructor to initialize the AppiumDriver instance
    public ScreenshotUtil(AppiumDriver driver) {
        this.driver = driver;
    }

    // Method to capture screenshots
    public String captureScreenshot(String screenshotName) {
        String screenshotPath = System.getProperty("user.dir") + "/resources/screenshots/" + screenshotName + ".jpeg";
        try {
            TakesScreenshot camera = driver;
            File source = camera.getScreenshotAs(OutputType.FILE);
            File destination = new File(screenshotPath);
            Files.copy(source.toPath(), destination.toPath());
        } catch (IOException e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
        }
        return screenshotPath;
    }
}