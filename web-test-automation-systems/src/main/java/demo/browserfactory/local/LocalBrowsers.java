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
 * Mar 05, 2023
 * @author Surendiran Selvam
 * @version 1.0
 * @since 1.0
 */

package demo.browserfactory.local;

import demo.browserfactory.BrowserManager;
import demo.enums.BrowserTypes;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.EnumMap;
import java.util.Map;

/**
 * Utility class for managing local web browsers.
 *
 * @see demo.browserfactory.remote.RemoteBrowsers
 * @see BrowserManager
 */
@UtilityClass
public class LocalBrowsers {

    private static final Map<BrowserTypes, WebDriver> BROWSER_MAP = new EnumMap<>(BrowserTypes.class);

    /**
     * Opens a local browser of the specified type.
     *
     * @param browser the type of browser to open
     */
    public static void openLocalBrowser(BrowserTypes browser) {
        WebDriver driver = BROWSER_MAP.get(browser);
        if (driver == null) {
            driver = createDriver(browser);
            BROWSER_MAP.put(browser, driver);
        }
        BrowserManager.setWebDriver(driver);
    }

    private static WebDriver createDriver(BrowserTypes browser) {
        return switch (browser) {
            case CHROME -> new ChromeDriver();
            case FIREFOX -> new FirefoxDriver();
            case EDGE -> new EdgeDriver();
            case SAFARI -> new SafariDriver();
            default -> throw new UnsupportedOperationException("Unsupported browser type: " + browser);
        };
    }

    /**
     * Closes all open local browsers.
     */
    public static void close() {
        for (WebDriver driver : BROWSER_MAP.values()) {
            try {
                driver.quit();
            } catch (Exception e) {
                // Log the exception or do nothing
            }
        }
        BROWSER_MAP.clear();
    }
}