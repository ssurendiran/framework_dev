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

package demo.libraries.loggers.extent;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import demo.enums.Authors;
import demo.enums.CategoryType;
import demo.libraries.loggers.ReportManager;
import demo.libraries.utils.ScreenshotUtils;
import demo.libraries.utils.TestConfig;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static demo.enums.AppPropertyKey.*;

/**
 * The ExtentImpl class is a utility class for setting up and generating Extent Reports. This class provides methods for starting and ending the reports, as well as logging various types of events and messages.
 */
@UtilityClass
public class ExtentImpl {

    private static ExtentSparkReporter extentSparkReporter;
    private static final ExtentReports extentReports = new ExtentReports();
    private static final String USER_DIR = System.getProperty("user.dir");
    private static final Path RESOURCES_PATH = Path.of(USER_DIR, "src", "test", "resources");
    private static final Path REPORT_PATH = Path.of(RESOURCES_PATH.toString(), "reports");
    private static final String EXTENT_REPORT_NAME = "<B>Automation Test Report : demo </B>";
    private static final String EXTENT_DOCUMENT_TITLE = "Automation Report : demo";
    private static final Path EXTENT_REPORT_PATH = Path.of(REPORT_PATH.toString(), "extent_reports");
    private static final String GENERATE_EXTENT_REPORT_PATH = getSetReportPath();


    /**
     * Sets up the report by initializing the ExtentSparkReporter and applying a view configuration.
     */
    private static void setUpReport() {
        extentSparkReporter = new ExtentSparkReporter(new File(String.valueOf(GENERATE_EXTENT_REPORT_PATH)))
                .viewConfigurer()
                .viewOrder()
                .as(new ViewName[]{ViewName.DASHBOARD, ViewName.TEST})
                .apply();
    }

    /**
     * Sets the test information by configuring the ExtentSparkReporter and attaching it to the ExtentReports object.
     */
    private static void setTestInfo() {
        extentSparkReporter.config().setDocumentTitle(EXTENT_DOCUMENT_TITLE);
        extentSparkReporter.config().setReportName(EXTENT_REPORT_NAME);
        extentSparkReporter.config().setTheme(Theme.DARK);
        extentReports.attachReporter(extentSparkReporter);
        extentReports.setSystemInfo("PLATFORM NAME", TestConfig.getProperty(PLATFORM));
        extentReports.setSystemInfo("BROWSER", TestConfig.getProperty(BROWSER));
        extentReports.setSystemInfo("BROWSER VERSION", TestConfig.getProperty(BROWSER_VERSION));
        extentReports.setSystemInfo("ENVIRONMENT", TestConfig.getProperty(ENVIRONMENT));
        extentReports.setSystemInfo("APPLICATION", TestConfig.getProperty(APP_NAME));
        extentReports.setSystemInfo("TEST CATEGORY", TestConfig.getProperty(TEST_CATEGORY));
    }

    //have to refactor this method with string builder and latest date class
    private static String getSetReportPath() {
        return EXTENT_REPORT_PATH + File.separator + Calendar.getInstance().get(Calendar.YEAR) + File.separator +
                new SimpleDateFormat("MMMM").format(new Date()) + File.separator + Calendar.getInstance().get(Calendar.DATE) + File.separator + TestConfig.getProperty(ENVIRONMENT) + File.separator +
                TestConfig.getProperty(BROWSER) + File.separator + TestConfig.getProperty(TEST_CATEGORY) + File.separator + new SimpleDateFormat("ddMMMyyyy-HHmm").format(new Date()) + ".html";
    }


    /**
     * Generates the report by calling the setUpReport and setTestInfo methods.
     */
    private static void createReport() {
        setUpReport();
        setTestInfo();
    }

    /**
     * Starts the Extent report logging for a given test case.
     *
     * @param testCaseName The name of the test case.
     */

    public static void extentCreateTest(String testCaseName) {
        ReportManager.setExtentTest(extentReports.createTest(testCaseName));
    }


    public static void extentStartLog() {
        createReport();
    }

    /**
     * Ends the Extent report logging for the current test case.
     */
    public static void extentEndLog() {
        ReportManager.getExtentTest().getExtent().flush();
    }

    /**
     * Logs an informational message to the Extent report.
     *
     * @param message The message to be logged.
     */
    public static void extentLogInfo(String message) {
        if (TestConfig.getProperty(EXTENT_REPORT_LOG_LEVEL).equalsIgnoreCase("DEBUG")) {
            ReportManager.getExtentTest().log(Status.INFO, message);
        }
    }

    /**
     * Logs a skipped test message to the Extent report.
     *
     * @param message The message to be logged.
     */
    public static void extentLogSkip(String message) {
        ReportManager.getExtentTest().log(Status.SKIP, message);
    }

    /**
     * Logs a warning message to the Extent report.
     *
     * @param message the warning message to be logged
     */
    public static void extentLogWarn(String message) {
        ReportManager.getExtentTest().log(Status.WARNING, message);
    }

    /**
     * Logs a pass message to the Extent report.
     *
     * @param message the pass message to be logged
     */
    public static void extentLogPass(String message) {
        if (TestConfig.getProperty(PASSED_STEP_SCREENSHOT).equalsIgnoreCase(String.valueOf(false))) {
            ReportManager.getExtentTest().log(Status.PASS, MarkupHelper.createLabel(message, ExtentColor.GREEN));
        } else {
            ReportManager.getExtentTest().log(Status.PASS, MarkupHelper.createLabel(message, ExtentColor.GREEN))
                    .pass(MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.captureScreenshotAsBase64()).build());
        }
    }

    /**
     * Logs a fail message to the Extent report.
     *
     * @param message the fail message to be logged
     */
    public static void extentLogFail(String message) {
        if (TestConfig.getProperty(FAILED_STEP_SCREENSHOT).equalsIgnoreCase(String.valueOf(true))) {
            ReportManager.getExtentTest().log(Status.FAIL, MarkupHelper.createLabel(message, ExtentColor.RED)).
                    fail(MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.captureScreenshotAsBase64()).build());
        } else {
            ReportManager.getExtentTest().log(Status.FAIL, MarkupHelper.createLabel(message, ExtentColor.RED));
        }
    }

    /**
     * Adds the given authors to the Extent report.
     *
     * @param authors an array of authors to be added
     */
    public static void extentAddAuthors(Authors[] authors) {
        for (Authors author : authors) {
            ReportManager.getExtentTest().assignAuthor(author.toString());
        }
    }

    /**
     * Adds the given categories to the Extent report.
     *
     * @param categories an array of category types to be added
     */
    public static void extentAddCategories(CategoryType[] categories) {
        for (CategoryType categoryType : categories) {
            ReportManager.getExtentTest().assignCategory(categoryType.toString());
        }
    }
}
