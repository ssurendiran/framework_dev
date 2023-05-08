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

import com.automate.enums.ConfigProperties;
import com.automate.utils.configloader.PropertyUtils;
import com.automate.utils.screenshot.ScreenshotService;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import lombok.experimental.UtilityClass;

@UtilityClass
public  class ExtentReportLogger {

  public static void logPass(String message) {
    if (PropertyUtils.getPropertyValue(ConfigProperties.PASSED_STEP_SCREENSHOTS).equalsIgnoreCase("yes")) {
      ExtentReportManager.getExtentTest().pass(message,
                                               MediaEntityBuilder.createScreenCaptureFromBase64String(
                                                 ScreenshotService.getScreenshotAsBase64()).build());
    } else {
      ExtentReportManager.getExtentTest().pass(MarkupHelper.createLabel(message, ExtentColor.GREEN));
    }
  }

  public static void logFail(String message, Throwable t) {
    if (PropertyUtils.getPropertyValue(ConfigProperties.FAILED_STEP_SCREENSHOTS).equalsIgnoreCase("yes")) {
      ExtentReportManager.getExtentTest().fail(MarkupHelper.createLabel(message, ExtentColor.RED))
        .fail(MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotService.getScreenshotAsBase64()).build())
        .fail(t);
    } else {
      ExtentReportManager.getExtentTest().fail(message).fail(t);
    }
  }

  public static void logSkip(String message) {
    if (PropertyUtils.getPropertyValue(ConfigProperties.SKIPPED_STEP_SCREENSHOTS).equalsIgnoreCase("yes")) {
      ExtentReportManager.getExtentTest().skip(message,
                                               MediaEntityBuilder.createScreenCaptureFromBase64String(
                                                 ScreenshotService.getScreenshotAsBase64()).build());
    } else {
      ExtentReportManager.getExtentTest().log(Status.SKIP, message);
    }
  }

  public static void logInfo(String message) {
    ExtentReportManager.getExtentTest().log(Status.INFO, message);
  }

  public static void warning(String message) {
    ExtentReportManager.getExtentTest().log(Status.WARNING, message);
  }
}
