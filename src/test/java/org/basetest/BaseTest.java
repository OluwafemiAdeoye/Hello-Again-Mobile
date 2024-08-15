package org.basetest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.appium.java_client.AppiumDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.utils.DeviceUtil;
import org.utils.DriverManager;
import org.utils.PropertyManager;
import org.utils.ScreenshotUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.MalformedURLException;

public class BaseTest {

    private static final Logger logger = LogManager.getLogger(BaseTest.class);
    protected static AppiumDriver driver;
    protected static ExtentReports extent;
    protected static ExtentTest extentTest;
    protected static ScreenshotUtil screenshotUtil;
    protected static DeviceUtil deviceUtil;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        // Initialize ExtentReports
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/extent-report.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));

        // Retrieve platformName from config and initialize the driver
        String platformName = PropertyManager.getProperty("platformName");
        if (platformName == null || platformName.isEmpty()) {
            logger.error("The 'platformName' property is not set in config.properties.");
            throw new RuntimeException("The 'platformName' property is not set in config.properties.");
        }

        // Initialize driver using DriverManager
        driver = DriverManager.initializeDriver(platformName);

        // Initialize utilities
        screenshotUtil = new ScreenshotUtil(driver);
        deviceUtil = new DeviceUtil(driver);

        // Log the Appium version in the report
        String appiumVersion = DriverManager.getAppiumVersion();
        extent.setSystemInfo("Appium Version", appiumVersion);
        logger.info("Appium version: " + appiumVersion);

        extentTest = extent.createTest("Setup"); // Initialize extentTest to avoid null reference
    }

    @AfterClass
    public void tearDown() {
        DriverManager.quitDriver();
        if (extent != null) {
            extent.flush();
        }
        logger.info("Test execution completed and resources released.");
    }

    @BeforeMethod
    public void activitySetUp() {
        extentTest = extent.createTest("Test Setup"); // Ensure a new test instance for each method
        logger.info("Starting a new test.");
    }

    @AfterMethod
    public void recordFailure(ITestResult result) {
        if (extentTest == null) {
            extentTest = extent.createTest(result.getName()); // Ensure extentTest is initialized
        }

        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = screenshotUtil.captureScreenshot(result.getName());
            extentTest.fail("Test failed: " + result.getThrowable(),
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            logger.error("Test failed: " + result.getThrowable().getMessage());
        } else if (result.getStatus() == ITestResult.SKIP) {
            extentTest.skip("Test skipped: " + result.getThrowable());
            logger.warn("Test skipped: " + result.getThrowable().getMessage());
        } else {
            extentTest.pass("Test passed");
            logger.info("Test passed.");
        }

        // Navigate back to the dashboard after each test
        navigateToDashboard();
    }

    // Method to navigate back to the dashboard
    private void navigateToDashboard() {
        while (!isDashboardDisplayed()) {
            driver.navigate().back();
            logger.info("Navigating back to the dashboard.");
        }
    }

    // Example method to check if the dashboard is displayed
    private boolean isDashboardDisplayed() {
        // Placeholder, replace with actual logic to check if the dashboard is displayed
        logger.debug("Checking if the dashboard is displayed.");
        return true;
    }
}