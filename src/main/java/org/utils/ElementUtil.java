package org.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ElementUtil {

    private static final Logger logger = LogManager.getLogger(ElementUtil.class);

    public static void clickElement(WebDriver driver, WebElement element) {
        logger.info("Clicking on element: " + element.toString());
        element.click();
    }

    public static void typeText(WebElement element, String text) {
        logger.info("Typing text: '" + text + "' into element: " + element.toString());
        element.clear();
        element.sendKeys(text);
    }

    public static void hoverOverElement(WebDriver driver, WebElement element) {
        logger.info("Hovering over element: " + element.toString());
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }
}