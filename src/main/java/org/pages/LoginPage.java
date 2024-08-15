package org.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.utils.ElementUtil;
import org.utils.LoggerUtil;

public class LoginPage {

    private AppiumDriver driver;

    @FindBy(xpath = "android.widget.EditText[@index='5']")
    private WebElement phoneNumberField;

    @FindBy(xpath = "android.widget.EditText[@index='7']")
    private WebElement pinField;

    @FindBy(xpath = "android.view.View[@index='8']")
    private WebElement loginButton;

    public LoginPage(AppiumDriver driver) {
        this.driver = driver;
        LoggerUtil.logInfo("Initializing LoginPage elements");
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void enterPhoneNumber(String phoneNumber) {
        try {
            LoggerUtil.logInfo("Entering phone number: " + phoneNumber);
            ElementUtil.typeText(phoneNumberField, phoneNumber);
        } catch (Exception e) {
            LoggerUtil.logError("Error entering phone number: " + e.getMessage());
            throw e;
        }
    }

    public void enterPin(String pin) {
        try {
            LoggerUtil.logInfo("Entering pin: " + pin);
            ElementUtil.typeText(pinField, pin);
        } catch (Exception e) {
            LoggerUtil.logError("Error entering pin: " + e.getMessage());
            throw e;
        }
    }

    public void tapLoginButton() {
        try {
            LoggerUtil.logInfo("Tapping login button");
            ElementUtil.clickElement(driver, loginButton);
        } catch (Exception e) {
            LoggerUtil.logError("Error tapping login button: " + e.getMessage());
            throw e;
        }
    }

    public boolean isLoginPageDisplayed() {
        LoggerUtil.logInfo("Checking if LoginPage is displayed");
        return loginButton.isDisplayed();
    }
}