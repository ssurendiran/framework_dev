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
 *        This class provides utility methods for logging test reports using the Extent framework & you can utilise for any other reporting framework.
        The methods provided in this class allow users to log different types of messages such as information,
        warnings, skips, passes and fails, and also start and end test logs.*/

package demo.libraries.loggers;

import demo.enums.Authors;
import demo.enums.CategoryType;
import lombok.experimental.UtilityClass;

import static demo.libraries.loggers.extent.ExtentImpl.*;


@UtilityClass
public class ReportLogger {
    /**
     * This method starts the test log reporting using the Extent framework.
     */
    public static void startLogReporting() {
        extentStartLog();

    }


    /**
     * This method starts the test log reporting using the Extent framework.
     *
     * @param testCaseName the name of the test case being executed
     */
    public static void createTestName(String testCaseName) {
        extentCreateTest(testCaseName);
    }

    /**
     * This method logs an information message to the test report using the Extent framework.
     *
     * @param message the message to be logged
     */
    public static void logReportInfo(String message) {
        extentLogInfo(message);
    }

    /**
     * This method logs a skip message to the test report using the Extent framework.
     *
     * @param message the message to be logged
     */
    public static void logReportSkip(String message) {
        extentLogSkip(message);
    }

    /**
     * This method logs a warning message to the test report using the Extent framework.
     *
     * @param message the message to be logged
     */
    public static void logReportWarn(String message) {
        extentLogWarn(message);
    }

    /**
     * This method logs a pass message to the test report using the Extent framework.
     *
     * @param message the message to be logged
     */
    public static void logReportPass(String message) {
        extentLogPass(message);
    }

    /**
     * This method logs a fail message to the test report using the Extent framework.
     *
     * @param message the message to be logged
     */
    public static void logReportFail(String message) {
        extentLogFail(message);
    }

    /**
     * This method ends the test log reporting using the Extent framework.
     */
    public static void endLogReporting() {
        extentEndLog();
    }


    public static void addAuthors(Authors[] authors) {
        extentAddAuthors(authors);
    }

    public static void addCategories(CategoryType[] categories) {
        extentAddCategories(categories);
    }


}