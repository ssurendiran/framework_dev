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
import org.openqa.selenium.support.ui.Select;

/**
 * This is a utility class for handling the selection of elements using the Select class in Selenium WebDriver.
 */
@UtilityClass
public final class SelectElements {

    /**
     * The message to log when skipping a method because the element was not found.
     */
    private static final String MSG3 = "Skipping the method ";

    /**
     * The message to log when an element was not found.
     */
    private static final String MSG4 = "since the element was not found";

    /**
     * The message to log when selecting a value from an element.
     */
    private static final String MSG1 = "Selected the value from the ";

    /**
     * The message to log when deselecting a value from an element.
     */
    private static final String MSG2 = "Deselected the value from the ";

    /**
     * Returns a Select object for the given element.
     *
     * @param element the element to select from
     * @return a Select object for the given element
     */
    private static Select sSelect(By element) {
        return new Select(BrowserSettings.sDriverFindElement(element));
    }

    /**
     * Selects an option from a drop-down list using its index.
     *
     * @param locator   the locator for the drop-down list element
     * @param value     the index of the option to select
     * @param fieldName the name of the field being selected
     */
    public static void sSelectByIndex(By locator, int value, String fieldName) {
        if (Waits.sVisibilityOfElement(locator) != null) {
            sSelect(locator).selectByIndex(value);
            ReportLogger.logReportInfo(MSG1 + fieldName);
        } else {
            ReportLogger.logReportWarn(MSG3 + " Select By Index  " + MSG4);
        }
    }

    /**
     * Selects an option from a drop-down list using its visible text.
     *
     * @param locator   the locator for the drop-down list element
     * @param text      the visible text of the option to select
     * @param fieldName the name of the field being selected
     */
    public static void sSelectByVisibleText(By locator, String text, String fieldName) {
        if (Waits.sVisibilityOfElement(locator) != null) {
            sSelect(locator).selectByVisibleText(text);
            ReportLogger.logReportInfo(MSG1 + fieldName);
        } else {
            ReportLogger.logReportWarn(MSG3 + " Select By visible text  " + MSG4);
        }
    }

    /**
     * Selects an option from a drop-down list using its value attribute.
     *
     * @param locator   the locator for the drop-down list element
     * @param text      the value attribute of the option to select
     * @param fieldName the name of the field being selected
     */
    public static void sSelectByValue(By locator, String text, String fieldName) {
        if (Waits.sVisibilityOfElement(locator) != null) {
            sSelect(locator).selectByValue(text);
            ReportLogger.logReportInfo(MSG1 + fieldName);
        } else {
            ReportLogger.logReportWarn(MSG3 + " Select By value " + MSG4);
        }
    }

    /**
     * Deselects an option from a drop-down list using its index.
     *
     * @param locator   the locator for the drop-down list element
     * @param value     the index of the option to deselect
     * @param fieldName the name of the field being deselected
     */
    public static void sDeSelectByIndex(By locator, int value, String fieldName) {
        if (Waits.sVisibilityOfElement(locator) != null) {
            sSelect(locator).deselectByIndex(value);
            ReportLogger.logReportInfo(MSG2 + fieldName);
        } else ReportLogger.logReportWarn(MSG3 + " DeSelect By Index  " + MSG4);

    }

    /**
     * Deselects an option from a drop-down list using its index.
     *
     * @param locator   the locator for the drop-down list element
     * @param text      the text option to deselect
     * @param fieldName the name of the field being deselected
     */

    public static void sDeSelectByVisibleText(By locator, String text, String fieldName) {
        if (Waits.sVisibilityOfElement(locator) != null) {
            sSelect(locator).deselectByVisibleText(text);
            ReportLogger.logReportInfo(MSG2 + fieldName);
        } else ReportLogger.logReportWarn(MSG3 + " DeSelect By visible text  " + MSG4);
    }

    /**
     * Deselects an option from a drop-down list using its index.
     *
     * @param locator   the locator for the drop-down list element
     * @param text      the text  option to deselect
     * @param fieldName the name of the field being deselected
     */
    public static void sDeSelectByValue(By locator, String text, String fieldName) {
        if (Waits.sVisibilityOfElement(locator) != null) {
            sSelect(locator).deselectByValue(text);
            ReportLogger.logReportInfo(MSG2 + fieldName);
        } else ReportLogger.logReportWarn(MSG3 + " DeSelect By value " + MSG4);
    }


}