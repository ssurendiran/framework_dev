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
 ** Mar 05, 2023
 * @author Surendiran Selvam
 * @version 1.0
 * @since 1.0
 */

package demo.browserfactory.remote;

import demo.browserfactory.BrowserManager;
import demo.enums.BrowserTypes;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.URL;
import java.util.EnumMap;
import java.util.Map;

/**
 * Utility class for opening remote browsers(selenium grid) with specific capabilities.
 *
 * @see demo.browserfactory.local.LocalBrowsers
 * @see demo.browserfactory.BrowserManager
 */
@UtilityClass
public class RemoteBrowsers {

    private static final Map<BrowserTypes, Capabilities> BROWSER_CAPABILITIES = new EnumMap<>(BrowserTypes.class);

    static {
        BROWSER_CAPABILITIES.putIfAbsent(BrowserTypes.CHROME, new ChromeOptions());
        BROWSER_CAPABILITIES.putIfAbsent(BrowserTypes.FIREFOX, new FirefoxOptions());
        BROWSER_CAPABILITIES.putIfAbsent(BrowserTypes.EDGE, new EdgeOptions());
        BROWSER_CAPABILITIES.putIfAbsent(BrowserTypes.SAFARI, new SafariOptions());
    }

    /**
     * Opens a remote browser with the specified capabilities.
     *
     * @param browserType  the type of browser to open
     * @param gridUrl      the URL of the Selenium Grid hub
     * @param version      the desired version of the browser
     * @param platformName the desired platform name for the browser
     */
    @SneakyThrows
    public static void openRemoteBrowser(BrowserTypes browserType, String gridUrl, String version, Platform platformName) {
        Capabilities capabilities = BROWSER_CAPABILITIES.get(browserType);
        ((DesiredCapabilities) capabilities).setVersion(version);
        ((DesiredCapabilities) capabilities).setPlatform(platformName);
        BrowserManager.setWebDriver(new RemoteWebDriver(new URL(gridUrl), capabilities));
    }

}


