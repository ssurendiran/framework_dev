/*
 *
 *  *
 *  * MIT License
 *  * Copyright (c) 2023 Surendiran Selvam
 *  * Permission is hereby granted, free of charge, to any person obtaining a copy
 *  * of this software and associated documentation files (the "Software"), to deal
 *  * in the Software without restriction, including without limitation the rights
 *  * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  * copies of the Software, and to permit persons to whom the Software is
 *  * furnished to do so, subject to the following conditions:
 *  * The above copyright notice and this permission notice shall be included in all
 *  * copies or substantial portions of the Software.
 *  * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  * FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE
 *  * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  * SOFTWARE.
 *  * Mar 05, 2023
 *  * @author Surendiran Selvam
 *  * @version 1.0
 *  * @since 1.0
 *
 *
 */

package com.automate.reports;

import com.automate.constants.FrameworkConstants;
import com.automate.driver.manager.DeviceManager;
import com.automate.driver.manager.PlatformManager;
import com.automate.enums.CategoryType;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import lombok.experimental.UtilityClass;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Objects;

@UtilityClass
public  class ExtentReportManager {

  private static final ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(FrameworkConstants.getExtentReportPath());
  private static final ThreadLocal<ExtentTest> threadLocalExtentTest = new ThreadLocal<>();
  private static ExtentReports extentReports;
  private static InetAddress ip;
  private static String hostname;

  /**
   * This method is to initialize the Extent Report
   */
  public static void initExtentReport() {
    try {
      if (Objects.isNull(extentReports)) {
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        ip = InetAddress.getLocalHost();
        hostname = ip.getHostName();
        extentReports.setSystemInfo("Host Name", hostname);
        extentReports.setSystemInfo("Environment", "Mobile Automation - Appium");
        extentReports.setSystemInfo("User Name", System.getProperty("user.name"));
        extentSparkReporter.config().setDocumentTitle("HTML Report");
        extentSparkReporter.config().setReportName("Mobile Automation Test");
        extentSparkReporter.config().setTheme(Theme.DARK);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void createTest(String testCaseName) {
    setExtentTest(extentReports.createTest(testCaseName));
  }

  public static void flushExtentReport() {
    if (Objects.nonNull(extentReports)) {
      extentReports.flush();
    }
    unload();
    try {
      Desktop.getDesktop().browse(new File(FrameworkConstants.getExtentReportPath()).toURI());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static ExtentTest getExtentTest() {
    return threadLocalExtentTest.get();
  }

  static void setExtentTest(ExtentTest test) {
    threadLocalExtentTest.set(test);
  }

  static void unload() {
    threadLocalExtentTest.remove();
  }

  public static void addAuthors(String[] authors) {
    for (String author : authors) {
      getExtentTest().assignAuthor(author);
    }
  }

  public static void addCategories(CategoryType[] categories) {
    for (CategoryType category : categories) {
      getExtentTest().assignCategory(category.toString());
    }
  }

  public static void addDevices() {
    getExtentTest().assignDevice(PlatformManager.getPlatformName() + "-" + DeviceManager.getDeviceName());
  }
}
