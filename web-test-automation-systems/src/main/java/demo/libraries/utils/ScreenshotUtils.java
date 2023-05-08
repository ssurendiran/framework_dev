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

package demo.libraries.utils;

import demo.browserfactory.BrowserManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.nio.file.Path;

import static org.apache.commons.lang3.SystemUtils.USER_DIR;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ScreenshotUtils {

    private static final Path RESOURCES_PATH = Path.of(USER_DIR, "src", "test", "resources");
    private static final Path REPORT_PATH = Path.of(RESOURCES_PATH.toString(), "reports");
    private static final String SET_SCREENSHOT_PATH = REPORT_PATH + File.separator + "screenshot";

    @SneakyThrows
    public static void captureScreenshotAsFile(String testCaseName) {
        File source = ((TakesScreenshot) BrowserManager.getDriver()).getScreenshotAs(OutputType.FILE);
        File destination = new File(SET_SCREENSHOT_PATH + File.separator + testCaseName + ".png");
        FileUtils.copyFile(source, destination);
    }

    public static String captureScreenshotAsBase64() {
        return ((TakesScreenshot) BrowserManager.getDriver()).getScreenshotAs(OutputType.BASE64);
    }
}
