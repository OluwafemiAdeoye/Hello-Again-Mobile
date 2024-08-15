package org.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.utils.LoggerUtil;

public class LandingPage {

    private final AppiumDriver driver;

    @FindBy(xpath = "android.view.View[@index='6']")
    private WebElement skipButton;

    @FindBy(xpath = "android.view.View[@index='2']")
    private WebElement signInButton;

    @FindBy(xpath = "android.view.View[@index='3']")
    private WebElement grantPermissionButton;

    @FindBy(id = "com.android.permissioncontroller:id/permission_allow_button")
    private WebElement allowButton;

    public LandingPage(AppiumDriver driver) {
        this.driver = driver;
        LoggerUtil.logInfo("Initializing LandingPage elements");
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public LoginPage tapSkipButton() {
        try {
            LoggerUtil.logInfo("Tapping Skip button");
            skipButton.click();
            return new LoginPage(driver);
        } catch (Exception e) {
            LoggerUtil.logError("Error tapping Skip button: " + e.getMessage());
            throw e;
        }
    }

    public boolean isLandingPageDisplayed() {
        LoggerUtil.logInfo("Checking if LandingPage is displayed");
        return skipButton.isDisplayed();
    }

    public LoginPage tapSignInButton() {
        try {
            LoggerUtil.logInfo("Tapping Sign In button");
            signInButton.click();
            return new LoginPage(driver);
        } catch (Exception e) {
            LoggerUtil.logError("Error tapping Sign In button: " + e.getMessage());
            throw e;
        }
    }

    public LoginPage tapgrantPermissionButton() {
        try {
            LoggerUtil.logInfo("Tapping Grant Permission button");
            grantPermissionButton.click();
            return new LoginPage(driver);
        } catch (Exception e) {
            LoggerUtil.logError("Error tapping Grant Permission button: " + e.getMessage());
            throw e;
        }
    }

    public LoginPage tapAllowButton() {
        try {
            LoggerUtil.logInfo("Tapping Allow button");
            allowButton.click();
            return new LoginPage(driver);
        } catch (Exception e) {
            LoggerUtil.logError("Error tapping Allow button: " + e.getMessage());
            throw e;
        }
    }
}