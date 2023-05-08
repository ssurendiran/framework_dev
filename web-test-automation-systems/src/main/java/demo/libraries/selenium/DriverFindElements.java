
/*
 *
 * MIT License
 * Copyright (c) 2023 Surendiran Selvam
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *  ** Mar 05, 2023
 *  * @author Surendiran Selvam
 *  * @version 1.0
 *  * @since 1.0
 *
 */

package demo.libraries.selenium;

import demo.libraries.loggers.ReportLogger;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static demo.libraries.loggers.ReportLogger.logReportInfo;


/**
 * A utility class that provides methods to interact with web elements using the Selenium WebDriver.
 */
@UtilityClass
public final class DriverFindElements {
    private static final String MSG3 = "Skipping the method ";
    private static final String MSG4 = "since the element was not found";

    /**
     * Enters the specified text into the web element identified by the given locator.
     *
     * @param locator   the locator to identify the web element
     * @param text      the text to be entered into the web element
     * @param fieldName the name of the field being interacted with
     */
    public static void sSendKeys(By locator, String text, String fieldName) {
        if (Waits.sVisibilityOfElement(locator) != null) {
            BrowserSettings.sDriverFindElement(locator).click();
            BrowserSettings.sDriverFindElement(locator).clear();
            BrowserSettings.sDriverFindElement(locator).sendKeys(text);
            logReportInfo("Entering " + text + " in the " + fieldName);
        } else {
            ReportLogger.logReportWarn(MSG3 + " Send keys " + MSG4);
        }
    }

    /**
     * Presses the Enter key on the web element identified by the given locator.
     *
     * @param locator   the locator to identify the web element
     * @param fieldName the name of the field being interacted with
     */
    public static void sKeyboardEnter(By locator, String fieldName) {
        BrowserSettings.sDriverFindElement(locator).sendKeys(Keys.ENTER);
        logReportInfo("Pressed enter for the " + fieldName);
    }

    /**
     * Gets the value of the specified attribute from the web element identified by the given locator.
     *
     * @param locator   the locator to identify the web element
     * @param attribute the attribute whose value is to be retrieved
     * @return the value of the specified attribute
     */
    public static String sGetAttribute(By locator, String attribute) {
        var attr = "";
        if (Waits.sVisibilityOfElement(locator) != null) {
            attr = BrowserSettings.sDriverFindElement(locator).getAttribute(attribute);
            logReportInfo("Getting attribute from the locator " + locator);
        } else {
            ReportLogger.logReportWarn(MSG3 + " Get attribute " + MSG4);
        }
        return attr;
    }

    /**
     * Checks if the web element identified by the given locator is displayed on the page.
     *
     * @param locator the locator to identify the web element
     * @return true if the web element is displayed, false otherwise
     */
    public static boolean sIsDisplayed(By locator) {
        var val = BrowserSettings.sDriverFindElement(locator).isDisplayed();
        if (val) {
            logReportInfo(locator + " is displayed");
        } else {
            logReportInfo(locator + " is not displayed");
        }
        return val;
    }

    /**
     * * @param locator   the locator of the element to be clicked on
     *
     * @param fieldName the name of the field to be clicked on, for logging purposes
     */
    public static void sClick(By locator, String fieldName) {
        if (Waits.sVisibilityOfElement(locator) != null) {
            BrowserSettings.sDriverFindElement(locator).click();
            logReportInfo("Clicked the " + fieldName + " ");
        } else {
            ReportLogger.logReportWarn(MSG3 + " Click " + MSG4);
        }
    }

    /**
     * Clears the text from the element located by the specified locator and logs the action to the report.
     *
     * @param locator   the locator of the element to be cleared
     * @param fieldName the name of the field to be cleared, for logging purposes
     */
    public static void sClear(By locator, String fieldName) {
        if (Waits.sVisibilityOfElement(locator) != null) {
            BrowserSettings.sDriverFindElement(locator).clear();
            logReportInfo("Cleared the field " + fieldName + " ");
        } else {
            ReportLogger.logReportWarn(MSG3 + " Clear " + MSG4);
        }
    }

    /**
     * Returns a boolean value indicating if the element specified by the given locator is currently selected.
     * If the element is not visible, it returns false.
     *
     * @param locator   the By locator of the element to check
     * @param fieldName the name of the field being checked, used for logging purposes
     * @return true if the element is selected, false otherwise
     */
    public static boolean sIsSelected(By locator, String fieldName) {
        boolean flag = false;
        if (Waits.sVisibilityOfElement(locator) != null) {
            flag = BrowserSettings.sDriverFindElement(locator).isSelected();
            if (flag) {
                logReportInfo(fieldName + " is selected");
            } else {
                logReportInfo(fieldName + " is not selected");
            }
        } else {
            ReportLogger.logReportWarn(MSG3 + " isSelected " + MSG4);
        }
        return flag;
    }

    /**
     * Returns the text value of the element specified by the given locator.
     * If the element is not visible, it returns an empty string.
     *
     * @param locator   the By locator of the element to retrieve the text from
     * @param fieldName the name of the field being retrieved, used for logging purposes
     * @return the text value of the element, or an empty string if the element is not visible
     */
    public static String sGetText(By locator, String fieldName) {
        String text = "";
        if (Waits.sVisibilityOfElement(locator) != null) {
            text = BrowserSettings.sDriverFindElement(locator).getText();
            logReportInfo("Retrieved the text " + text + " from the " + fieldName);
        } else {
            ReportLogger.logReportWarn(MSG3 + " Get Text " + MSG4);
        }
        return text;
    }

    /**
     * Returns the tag name of the first web element matching the given locator.
     *
     * @param locator the locator used to identify the web element
     * @return the tag name of the first web element matching the locator
     */
    public static String sGetTagName(By locator) {
        String tagName = "";
        if (Waits.sVisibilityOfElement(locator) != null) {
            tagName = BrowserSettings.sDriverFindElement(locator).getTagName();
            logReportInfo("Retrieved the tag name " + tagName);
        } else {
            ReportLogger.logReportWarn(MSG3 + " Get Tag name " + MSG4);
        }
        return tagName;
    }

    /**
     * Clicks all web elements matching the given locator.
     *
     * @param locator   the locator used to identify the web elements
     * @param fieldName the name of the field being interacted with
     */
    public static void sClickListValues(By locator, String fieldName) {
        if (Waits.sVisibilityOfElement(locator) != null) {
            BrowserSettings.sDriverFindElements(locator).forEach(WebElement::click);
            logReportInfo("Clicked all the values from the " + fieldName + " ");
        } else ReportLogger.logReportWarn(MSG3 + " Click List Values " + MSG4);
    }

    /**
     * This method retrieves a list of text values from web elements identified by the given locator.
     *
     * @param locator   the By locator used to identify the web elements.
     * @param fieldName the name of the field that the web elements belong to.
     * @return a List of String containing the text values of the web elements.
     */

    public static List<String> sGetListTxtValues(By locator, String fieldName) {
        var listVal = new ArrayList<String>();
        if (Waits.sVisibilityOfElement(locator) != null) {
            List<WebElement> webElements = BrowserSettings.sDriverFindElements(locator);
            for (var eachElement : webElements) {
                listVal.add(eachElement.getText().trim());
            }
            logReportInfo("Retrieved the values " + listVal + " from the " + fieldName + " ");
        } else ReportLogger.logReportWarn(MSG3 + " Get List Values " + MSG4);
        return listVal;
    }

    /**
     * This method retrieves the size of a list of web elements identified by the given locator.
     *
     * @param locator the By locator used to identify the web elements.
     * @return the size of the list of web elements.
     */
    public static int sGetTotalListSize(By locator) {
        var size = 0;
        if (Waits.sVisibilityOfElement(locator) != null) {
            size = BrowserSettings.sDriverFindElements(locator).size();
            logReportInfo("Retrieved size fof the list is " + size);
        }
        return size;
    }


}


