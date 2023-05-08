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
import lombok.experimental.UtilityClass;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;

import static demo.libraries.loggers.ReportLogger.logReportInfo;
import static demo.libraries.loggers.ReportLogger.logReportWarn;

@UtilityClass
public final class Waits {

    private static final int IMP_WAIT = 10;
    private static final int FLU_WAIT = 30;
    private static final int POLL_WAIT = 30;
    private static final WebDriver driver = BrowserManager.getDriver();
    private static FluentWait<WebDriver> fluentWait = null;

    /**
     * Generates an error message for when a specified locator is not found.
     *
     * @param locator The locator that could not be found.
     * @return The error message.
     */
    private static String noLocatorErrorMsg(By locator) {
        return "No such " + locator + " element is available";
    }

    /**
     * Returns a FluentWait instance with default timeouts and polling interval, and ignores StaleElementReferenceException.
     *
     * @return A FluentWait instance with default settings.
     */
    private static FluentWait<WebDriver> sFluentWait() {
        if (fluentWait == null) {
            fluentWait = new FluentWait<>(BrowserManager.getDriver())
                    .withTimeout(Duration.ofSeconds(FLU_WAIT))
                    .pollingEvery(Duration.ofSeconds(POLL_WAIT))
                    .ignoring(StaleElementReferenceException.class);
        }
        return fluentWait;
    }

    /**
     * Applies an implicit wait of 10 seconds to the WebDriver instance.
     */
    public static void sImplicitWait() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMP_WAIT));
        logReportInfo("Applied implicit wait " + IMP_WAIT + " seconds");
    }

    /**
     * Waits until an element specified by the locator is visible on the page, and highlights it.
     *
     * @param locator The locator of the element to wait for.
     * @return The WebElement object when it becomes visible, or null if it doesn't.
     */
    public static WebElement sVisibilityOfElement(By locator) {
        try {
            WebElement element = sFluentWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
            JavascriptBasedInteractions.sJsExecutorHighlightElement(locator);
            return element;
        } catch (TimeoutException e) {
            logReportWarn(noLocatorErrorMsg(locator));
            return null;
        }
    }

    /**
     * Waits until an element specified by the locator is clickable, and highlights it.
     *
     * @param locator The locator of the element to wait for.
     * @return The WebElement object when it becomes clickable, or null if it doesn't.
     */
    public static WebElement sElementClickable(By locator) {
        try {
            WebElement element = sFluentWait().until(ExpectedConditions.elementToBeClickable(locator));
            JavascriptBasedInteractions.sJsExecutorHighlightElement(locator);
            return element;
        } catch (TimeoutException e) {
            logReportWarn(noLocatorErrorMsg(locator));
            return null;
        }
    }

    /**
     * Waits until an alert is present, and returns it.
     *
     * @return The Alert object when it appears, or null if it doesn't.
     */
    public static Alert sIsAlertPresent() {
        try {
            return sFluentWait().until(ExpectedConditions.alertIsPresent());
        } catch (NoAlertPresentException e) {
            logReportWarn("No such alert is present");
            return null;
        }
    }

    /**
     * Waits until all elements specified by the locator are visible on the page, and highlights them.
     *
     * @param locator The locator of the elements to wait for.
     * @return A List of all WebElement objects when visible
     */

    public static List<WebElement> sGetListElements(By locator) {
        List<WebElement> elements = sFluentWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        if (elements != null) {
            JavascriptBasedInteractions.sJsExecutorHighlightElement(locator);
        } else {
            logReportWarn(noLocatorErrorMsg(locator));
        }
        return elements;
    }


}

