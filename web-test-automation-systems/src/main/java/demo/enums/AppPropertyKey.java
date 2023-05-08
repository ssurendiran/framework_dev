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

package demo.enums;

/**
 * This enum represents the keys for different application properties used in the framework.
 */
public enum AppPropertyKey {
    APP_NAME, // The name of the application
    BROWSER, // The browser name
    BROWSER_INSTANCES, // Number of browser instances to be created
    BROWSER_VERSION, // The version of the browser
    ENVIRONMENT, // The test environment
    EXECUTION_MODE, // The execution mode (local/remote)
    EXTENT_REPORT_LOG_LEVEL, // Log level for extent report
    FAILED_STEP_SCREENSHOT, // Flag to enable/disable capturing screenshots on failed steps
    GRID_URL, // The URL of the grid
    PASSED_STEP_SCREENSHOT, // Flag to enable/disable capturing screenshots on passed steps
    PLATFORM, // The platform on which the test is executed
    RETRY_COUNT, // Number of times a failed test should be retried
    RETRY_FAILED_TEST, // Flag to enable/disable retrying of failed tests
    TEST_CATEGORY; // The category of the test (smoke/regression/etc)
}