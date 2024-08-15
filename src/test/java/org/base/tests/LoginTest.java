package org.base.tests;

import org.basetest.BaseTest;
import org.pages.LoginPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.utils.JsonReader;
import org.utils.LoggerUtil;

import java.io.IOException;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @Test(dataProvider = "loginDataProvider")
    public void loginTest(String phoneNumber, String pin, String expectedResult) {
        SoftAssert softAssert = new SoftAssert();

        // Initialize the login page
        loginPage = new LoginPage(driver);
        LoggerUtil.logInfo("LoginPage initialized");

        // Perform login action using methods from LoginPage
        loginPage.enterPhoneNumber(phoneNumber);
        LoggerUtil.logInfo("Entered phone number: " + phoneNumber);

        loginPage.enterPin(pin);
        LoggerUtil.logInfo("Entered pin");

        loginPage.tapLoginButton();
        LoggerUtil.logInfo("Tapped login button");

        // Capture the actual result from the application
        // String actualResult = loginPage.getLoginResult();
        // LoggerUtil.logInfo("Captured login result: " + actualResult);

        // Soft assert the actual result against the expected result
        // softAssert.assertEquals(actualResult, expectedResult, "Login test failed for phone number: " + phoneNumber);
        // LoggerUtil.logInfo("Expected result: " + expectedResult + ", Actual result: " + actualResult);

        // Call assertAll to aggregate all assertions
        // softAssert.assertAll();
        // LoggerUtil.logInfo("Soft assertions completed");
    }

    @DataProvider(name = "loginDataProvider")
    public Object[][] getData() throws IOException {
        LoggerUtil.logInfo("Loading test data from JSON");
        return JsonReader.getJSONData("src/test/resources/test-data/TestData.json", "LoginTestData");
    }
}