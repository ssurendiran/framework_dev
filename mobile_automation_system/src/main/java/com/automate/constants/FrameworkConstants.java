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

package com.automate.constants;

import com.automate.enums.ConfigProperties;
import com.automate.utils.configloader.PropertyUtils;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public  class FrameworkConstants {

  public static final String PROJECT_PATH = System.getProperty("user.dir");
  public static final String TEST_RESOURCES_DIR = PROJECT_PATH + File.separator + "src/test/resources";
  public static final String ANDROID_APK_PATH =
    TEST_RESOURCES_DIR + File.separator + "app" + File.separator +
      "Android.SauceLabs.Mobile.Sample.app.2.7.1.apk";
  public static final String CHROME_DRIVER_EXE_PATH =
    TEST_RESOURCES_DIR + File.separator + "executables" + File.separator + "chromedriver.exe";
  public static final String CONFIG_PROPERTIES_PATH =
    TEST_RESOURCES_DIR + File.separator + "config" + File.separator + "config.properties";
  public static final String CONFIG_JSON_PATH =
    TEST_RESOURCES_DIR + File.separator + "config" + File.separator + "config.json";
  public static final String TEST_DATA_FILEPATH =
    TEST_RESOURCES_DIR + File.separator + "data" + File.separator + "testdata.xlsx";
  public static final String APPIUM_SERVER_HOST = "127.0.0.1";
  public static final int APPIUM_SERVER_PORT = 4723;
  public static final String APPIUM_JS_PATH = System.getenv("APPIUM_HOME") + File.separator + "main.js";
  public static final String CREDENTIALS_JSON = "data/credentials.json";
  public static final long EXPLICIT_WAIT = 15;
  public static final String TEST_DATA_SHEET = "TEST_DATA";
  public static final String IOS_APP_PATH = "";
  public static final String SCREENSHOT_PATH = PROJECT_PATH + File.separator + "screenshots";
  public static final String NODEJS_PATH = System.getenv("NODE_HOME") + File.separator + "node.exe";

  private static final String EXTENT_REPORT_PATH = PROJECT_PATH + File.separator + "extent-test-report";
  private static final String APPIUM_SERVER_LOGS_PATH = PROJECT_PATH + File.separator + "server-logs";
  private static final String SCREEN_RECORDING_PATH = PROJECT_PATH + File.separator + "screen-recordings";

  public static String getExtentReportPath() {
    if (PropertyUtils.getPropertyValue(ConfigProperties.OVERRIDE_REPORTS).equalsIgnoreCase("yes")) {
      return EXTENT_REPORT_PATH + File.separator + "index.html";
    } else {
      return EXTENT_REPORT_PATH + File.separator + getCurrentDateTime() + File.separator + "index.html";
    }
  }

  public static String getAppiumServerLogsPath() {
    if (PropertyUtils.getPropertyValue(ConfigProperties.OVERRIDE_SERVER_LOG).equalsIgnoreCase("yes")) {
      return APPIUM_SERVER_LOGS_PATH + File.separator + "server.log";
    } else {
      return APPIUM_SERVER_LOGS_PATH + File.separator + getCurrentDateTime() + File.separator + "server.log";
    }
  }

  public static String getScreenRecordingsPath() {
    File screenRecordingsDir = new File(SCREEN_RECORDING_PATH);
    if (!screenRecordingsDir.exists()) {
      screenRecordingsDir.mkdir();
    }
    return SCREEN_RECORDING_PATH;
  }

  private static String getCurrentDateTime() {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy_MM_dd-HH_mm_ss");
    LocalDateTime localDateTime = LocalDateTime.now();
    return dateTimeFormatter.format(localDateTime);
  }
}
