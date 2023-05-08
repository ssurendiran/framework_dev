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
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

/**
 * The ActionElements class provides utility methods to perform various actions on web elements using Selenium Actions.
 * <p>
 * This is a final utility class and cannot be extended.
 */
@UtilityClass
public class ActionElements {

    private static final String MSG1 = " successfully with help of action";
    private static final String MSG2 = "Moved to the ";
    private static final String MSG3 = "Skipping the method ";
    private static final String MSG4 = "since the element was not found";
    private static Actions actions = null;

    /**
     * This method returns the instance of the Actions class.
     *
     * @return the Actions instance
     */
    private static Actions sCreateAction() {
        if (actions == null) {
            actions = new Actions(BrowserManager.getDriver());
        }
        return actions;
    }

    /**
     * Performs a click action on the given element.
     *
     * @param locator   the By locator of the element to be clicked
     * @param fieldName the name of the element to be clicked
     */
    public static void sActionClick(By locator, String fieldName) {
        if (Waits.sVisibilityOfElement(locator) != null) {
            sCreateAction().click(BrowserSettings.sDriverFindElement(locator));
            ReportLogger.logReportInfo("Clicked " + fieldName + " " + MSG1);
        } else {
            ReportLogger.logReportWarn(MSG3 + "action click " + MSG4);
        }
    }

    /**
     * Performs a click and hold action on the given element.
     *
     * @param locator   the By locator of the element to be clicked and held
     * @param fieldName the name of the element to be clicked and held
     */
    public static void sActionClickAndHold(By locator, String fieldName) {
        if (Waits.sVisibilityOfElement(locator) != null) {
            sCreateAction().clickAndHold(BrowserSettings.sDriverFindElement(locator));
            ReportLogger.logReportInfo("Clicked and held " + fieldName + " " + MSG1);
        } else {
            ReportLogger.logReportWarn(MSG3 + "action click and hold " + MSG4);
        }
    }

    /**
     * Performs a double click action on the given element.
     *
     * @param locator   the By locator of the element to be double clicked
     * @param fieldName the name of the element to be double clicked
     */
    public static void sActionDoubleClick(By locator, String fieldName) {
        if (Waits.sVisibilityOfElement(locator) != null) {
            sCreateAction().doubleClick(BrowserSettings.sDriverFindElement(locator));
            ReportLogger.logReportInfo("Double clicked " + fieldName + " " + MSG1);
        } else {
            ReportLogger.logReportWarn(MSG3 + "action double click " + MSG4);
        }
    }

    /**
     * Moves the mouse to the element located by the specified locator and clicks it.
     *
     * @param locator   the locator used to locate the element to click on
     * @param fieldName the name of the field being interacted with
     */
    public static void sActionMoveToElementClick(By locator, String fieldName) {
        if (Waits.sVisibilityOfElement(locator) != null) {
            sCreateAction().moveToElement(BrowserSettings.sDriverFindElement(locator)).click();
            ReportLogger.logReportInfo(MSG2 + fieldName + " " + " and clicked the " + fieldName + " " + MSG1);
        } else {
            ReportLogger.logReportWarn(MSG3 + "action move to element and click " + MSG4);
        }
    }

    /**
     * Moves the mouse to the element located by the specified locator, clicks and holds it.
     *
     * @param locator   the locator used to locate the element to click and hold
     * @param fieldName the name of the field being interacted with
     */
    public static void sActionMoveToElementClickHold(By locator, String fieldName) {
        if (Waits.sVisibilityOfElement(locator) != null) {
            sCreateAction().moveToElement(BrowserSettings.sDriverFindElement(locator)).clickAndHold();
            ReportLogger.logReportInfo(MSG2 + fieldName + " " + " and clicked and held the " + fieldName + " " + MSG1);
        } else {
            ReportLogger.logReportWarn(MSG3 + "action move to element, click and hold " + MSG4);
        }
    }

    /**
     * Moves the mouse to the element located by the specified locator and double clicks it.
     *
     * @param locator   the locator used to locate the element to double click
     * @param fieldName the name of the field being interacted with
     */
    public static void sActionMoveToElementDoubleClick(By locator, String fieldName) {
        if (Waits.sVisibilityOfElement(locator) != null) {
            sCreateAction().moveToElement(BrowserSettings.sDriverFindElement(locator)).doubleClick();
            ReportLogger.logReportInfo(MSG2 + fieldName + " " + " and double clicked " + fieldName + " " + MSG1);
        } else {
            ReportLogger.logReportWarn(MSG3 + "action move to element and double click " + MSG4);
        }
    }

    /**
     * Scrolls the browser window to the element located by the specified locator.
     *
     * @param locator   the locator used to locate the element to scroll to
     * @param fieldName the name of the field being interacted with
     */
    public static void sActionScrollToElement(By locator, String fieldName) {
        if (Waits.sVisibilityOfElement(locator) != null) {
            sCreateAction().scrollToElement(BrowserSettings.sDriverFindElement(locator));
            ReportLogger.logReportInfo("Scrolled to " + fieldName + " " + MSG1);
        } else {
            ReportLogger.logReportWarn(MSG3 + "action scroll to element " + MSG4);
        }
    }

    /**
     * Sends a text to a web element specified by the given locator.
     *
     * @param locator   the By object representing the locator of the target element
     * @param sendText  the text to be sent to the target element
     * @param fieldName the name of the target field for reporting purposes
     */
    public static void sActionSendKey(By locator, CharSequence sendText, String fieldName) {
        if (Waits.sVisibilityOfElement(locator) != null) {
            sCreateAction().sendKeys(BrowserSettings.sDriverFindElement(locator), sendText);
            ReportLogger.logReportInfo("Entered text " + sendText + " in " + fieldName + " " + MSG1);
        } else {
            ReportLogger.logReportWarn(MSG3 + "action send key " + MSG4);
        }
    }

    /**
     * Drags an element specified by the source locator and drops it onto an element specified by the target locator.
     *
     * @param sourceLocator the By object representing the locator of the source element
     * @param targetLocator the By object representing the locator of the target element
     * @param fieldName1    the name of the source field for reporting purposes
     * @param fieldName2    the name of the target field for reporting purposes
     */
    public static void sActionDragDrop(By sourceLocator, By targetLocator, String fieldName1, String fieldName2) {
        if (Waits.sVisibilityOfElement(sourceLocator) != null && Waits.sVisibilityOfElement(targetLocator) != null) {
            sCreateAction().dragAndDrop(BrowserSettings.sDriverFindElement(sourceLocator), BrowserSettings.sDriverFindElement(targetLocator));
            ReportLogger.logReportInfo("Dragged from " + fieldName1 + " and dropped to " + fieldName2 + MSG1);
        } else {
            ReportLogger.logReportWarn(MSG3 + "action drag and drop " + MSG4);
        }
    }

    /**
     * Pastes the contents of the clipboard into the active element.
     */
    public static void sActionPasteClipBoard() {
        sCreateAction().sendKeys(Keys.chord(Keys.LEFT_CONTROL, "v")).build().perform();
        ReportLogger.logReportInfo("Pasting the text from clipboard to the field");
    }

    /**
     * Performs all actions in the action chain.
     */
    public static void sActionPerform() {
        sCreateAction().perform();
        ReportLogger.logReportInfo("Performing all the chain of actions");
    }
}