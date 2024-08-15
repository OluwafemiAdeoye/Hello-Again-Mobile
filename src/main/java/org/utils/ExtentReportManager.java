package org.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

    private static ExtentReports extent;
    private static ExtentTest test;

    // Initialize the ExtentReports
    public static ExtentReports getExtentReports(String appiumVersion) {  // Accept the Appium version dynamically
        if (extent == null) {
            // Specify the location of the report
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/extent-report.html");
            sparkReporter.config().setDocumentTitle("Appium Test Report");
            sparkReporter.config().setReportName("Mobile Automation Test Report");
            sparkReporter.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            // Additional system information to include in the report
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("Appium Version", appiumVersion);  // Use the passed Appium version
        }
        return extent;
    }

    // Start a new test in the report
    public static ExtentTest createTest(String testName, String description) {
        test = getExtentReports(null).createTest(testName, description);  // Pass null as default; caller should set Appium version first
        return test;
    }

    // Get the current test instance
    public static ExtentTest getTest() {
        return test;
    }

    // Flush the reports
    public static void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }
}