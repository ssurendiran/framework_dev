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
import demo.browserfactory.local.LocalBrowsers;
import demo.browserfactory.remote.RemoteBrowsers;
import demo.enums.BrowserTypes;
import demo.enums.ExecutionMode;
import demo.libraries.loggers.ReportLogger;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.*;

import java.util.List;


/**
 * Utility class for browser settings and actions.
 */
@UtilityClass
public final class BrowserSettings {
    /**
     * Retrieves the title of the current web page.
     *
     * @return the title of the current web page
     */
    public static String sGetTitle() {
        var title = BrowserManager.getDriver().getTitle();
        ReportLogger.logReportInfo("Title value " + title + " has been retrieved from the browser");
        return title;
    }

    /**
     * Finds a web element based on the given locator.
     *
     * @param locator the locator of the web element
     * @return the web element found
     */
    public static WebElement sDriverFindElement(By locator) {
        return BrowserManager.getDriver().findElement(locator);
    }

    /**
     * Finds a list of web elements based on the given locator.
     *
     * @param locator the locator of the web elements
     * @return the list of web elements found
     */
    public static List<WebElement> sDriverFindElements(By locator) {
        return BrowserManager.getDriver().findElements(locator);
    }

    /**
     * Retrieves the current URL of the web page.
     *
     * @return the current URL of the web page
     */
    public static String sCurrentUrl() {
        return BrowserManager.getDriver().getCurrentUrl();
    }

    /**
     * Navigates to the specified URL.
     *
     * @param url the URL to navigate to
     * @return the current URL of the web page after navigation
     */
    public static void sNavigateURL(String url) {
        BrowserManager.getDriver().get(url);
    }

    /**
     * Navigates to the previous page in the browser history.
     */
    public static void sNavigateBack() {
        BrowserManager.getDriver().navigate().back();
    }

    /**
     * Navigates to the next page in the browser history.
     */
    public static void sNavigateForward() {
        BrowserManager.getDriver().navigate().forward();
        var cUrl = sCurrentUrl();
        ReportLogger.logReportInfo("Navigated to the forward url " + cUrl);
    }

    /**
     * Refreshes the current web page.
     */
    public static void sRefreshBrowser() {
        BrowserManager.getDriver().navigate().refresh();
        ReportLogger.logReportInfo("Refreshed the browser");
    }

    /**
     * Opens a new window in the browser.
     */
    public static void sSwitchNewWindow() {
        BrowserManager.getDriver().switchTo().newWindow(WindowType.TAB);
    }

    /**
     * Maximizes the browser window.
     */
    private static void maximizeBrowser() {
        BrowserManager.getDriver().manage().window().maximize();
    }

    /**
     * Clears all cookies stored in the browser.
     */
    private static void clearCookies() {
        BrowserManager.getDriver().manage().deleteAllCookies();
    }

    /**
     * Quits the current instance of the browser driver.
     */
    private static void quitDriver() {
        if (BrowserManager.getDriver() != null) {
            BrowserManager.getDriver().quit();
        }
    }

    /**
     * Closes the current window of the browser.
     */
    public static void closeCurrentWindow() {
        BrowserManager.getDriver().close();
    }

    /**
     * Closes the entire  browser.
     */

    public static void closeBrowser() {
        quitDriver();
    }

    /**
     * Opens the local /remote browser based on input.
     */
    public static void openBrowser(ExecutionMode executionMode, BrowserTypes browser, String browserVersion, Platform platform, String gridUrl) {
        if (executionMode.equals(ExecutionMode.LOCAL)) {
            LocalBrowsers.openLocalBrowser(browser);
        } else RemoteBrowsers.openRemoteBrowser(browser, gridUrl, browserVersion, platform);
        clearCookies();
        maximizeBrowser();
    }

}
