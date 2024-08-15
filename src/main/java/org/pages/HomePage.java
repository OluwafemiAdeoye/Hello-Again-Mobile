package org.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.utils.LoggerUtil;

public class HomePage {

    public HomePage(AppiumDriver driver) {
        LoggerUtil.logInfo("Initializing HomePage elements");
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    // Add methods to interact with elements on the home page
}