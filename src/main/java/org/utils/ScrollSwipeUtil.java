package org.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;


public class ScrollSwipeUtil {

    private final AppiumDriver driver;

    public ScrollSwipeUtil(AppiumDriver driver) {
        this.driver = driver;
    }

    // Method to scroll down
    public void scrollDown() {
        Dimension dimension = driver.manage().window().getSize();
        int scrollStart = (int) (dimension.getHeight() * 0.5);
        int scrollEnd = (int) (dimension.getHeight() * 0.2);

        new TouchAction<>((PerformsTouchActions) driver)
            .press(PointOption.point(0, scrollStart))
            .moveTo(PointOption.point(0, scrollEnd))
            .release()
            .perform();
    }

    // Method to scroll up
    public void scrollUp() {
        Dimension dimension = driver.manage().window().getSize();
        int scrollStart = (int) (dimension.getHeight() * 0.5);
        int scrollEnd = (int) (dimension.getHeight() * 0.8);

        new TouchAction<>((PerformsTouchActions) driver)
            .press(PointOption.point(0, scrollStart))
            .moveTo(PointOption.point(0, scrollEnd))
            .release()
            .perform();
    }

    // Method to swipe left
    public void swipeLeft() {
        Dimension size = driver.manage().window().getSize();
        int startX = (int) (size.width * 0.8);
        int endX = (int) (size.width * 0.2);
        int startY = size.height / 2;

        new TouchAction<>((PerformsTouchActions) driver)
            .press(PointOption.point(startX, startY))
            .moveTo(PointOption.point(endX, startY))
            .release()
            .perform();
    }

    // Method to swipe right
    public void swipeRight() {
        Dimension size = driver.manage().window().getSize();
        int startX = (int) (size.width * 0.2);
        int endX = (int) (size.width * 0.8);
        int startY = size.height / 2;

        new TouchAction<>((PerformsTouchActions) driver)
            .press(PointOption.point(startX, startY))
            .moveTo(PointOption.point(endX, startY))
            .release()
            .perform();
    }
}