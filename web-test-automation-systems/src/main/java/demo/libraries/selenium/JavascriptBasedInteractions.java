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

import demo.browserfactory.BrowserManager;
import demo.libraries.loggers.ReportLogger;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

/**
 * Utility class for performing interactions using JavaScriptExecutor.
 */
@UtilityClass
public class JavascriptBasedInteractions {

    private static final String MSG3 = "Skipping the method ";
    private static final String MSG4 = "since the element was not found";

    /**
     * Creates a new instance of JavascriptExecutor using the current WebDriver instance.
     *
     * @return a new instance of JavascriptExecutor.
     */
    private static JavascriptExecutor sCreateJavaScriptExecutions() {
        return (JavascriptExecutor) BrowserManager.getDriver();
    }

    /**
     * Highlights an element on the web page using JavaScript.
     *
     * @param locator the By locator of the element to be highlighted.
     */
    public static void sJsExecutorHighlightElement(By locator) {
        sCreateJavaScriptExecutions().executeScript(
                "arguments[0].style.border='3px solid red'",
                BrowserSettings.sDriverFindElement(locator));
    }

    /**
     * Clicks on an element on the web page using JavaScript.
     *
     * @param locator   the By locator of the element to be clicked.
     * @param fieldName the name of the field being clicked.
     */
    public static void sJsExecutorClick(By locator, String fieldName) {
        if (Waits.sVisibilityOfElement(locator) != null) {
            sCreateJavaScriptExecutions().executeScript(
                    "arguments[0].click();",
                    BrowserSettings.sDriverFindElement(locator));
            ReportLogger.logReportInfo("J-clicked on the field " + fieldName);
        } else {
            ReportLogger.logReportWarn(MSG3 + " J-Click " + MSG4);
        }
    }

    /**
     * Scrolls to an element on the web page using JavaScript.
     *
     * @param locator    the By locator of the element to be scrolled to.
     * @param untilField the name of the field until which to scroll.
     */
    public static void sJsExecutorScroll(By locator, String untilField) {
        if (Waits.sVisibilityOfElement(locator) != null) {
            sCreateJavaScriptExecutions().executeScript(
                    "arguments[0].scrollIntoView();",
                    BrowserSettings.sDriverFindElement(locator));
            ReportLogger.logReportInfo("J-Scrolled until the field " + untilField);
        } else {
            ReportLogger.logReportWarn(MSG3 + " J-Scroll " + MSG4);
        }
    }

    /**
     * Enters text into an element on the web page using JavaScript.
     *
     * @param locator   the By locator of the element to enter text into.
     * @param text      the text to be entered into the element.
     * @param fieldName the name of the field being edited.
     */
    public static void sJsExecutorSetText(By locator, String text, String fieldName) {
        if (Waits.sVisibilityOfElement(locator) != null) {
            sCreateJavaScriptExecutions().executeScript(
                    "arguments[0].value='" + text + "';",
                    BrowserSettings.sDriverFindElement(locator));
            ReportLogger.logReportInfo("J-Entered text in the field " + fieldName);
        } else {
            ReportLogger.logReportWarn(MSG3 + " J-Set Text " + MSG4);
        }
    }

}
