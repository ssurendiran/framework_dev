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

import demo.libraries.loggers.ReportLogger;
import lombok.experimental.UtilityClass;

/**
 * The AlertElements class provides methods to interact with alerts.
 */
@UtilityClass
public final class AlertElements {

    private static final String MSG3 = "Skipping the method ";
    private static final String MSG4 = "since the alert was not found";

    /**
     * Returns the text of the alert and logs it.
     *
     * @param alertName the name of the alert
     * @return the text of the alert
     */
    public static String sAlertGetText(String alertName) {
        var alert = Waits.sIsAlertPresent();
        if (alert != null) {
            var text = alert.getText();
            ReportLogger.logReportInfo("Retrieved " + text + " from alert " + alertName);
            return text;
        } else {
            ReportLogger.logReportWarn(MSG3 + "Alert get text " + MSG4);
            return "";
        }
    }

    /**
     * Accepts the alert and logs it.
     *
     * @param alertName the name of the alert
     */
    public static void sAlertAccept(String alertName) {
        var alert = Waits.sIsAlertPresent();
        if (alert != null) {
            alert.accept();
            ReportLogger.logReportInfo("Clicked accept button on the alert " + alertName);
        } else {
            ReportLogger.logReportWarn(MSG3 + "Alert accept " + MSG4);
        }
    }

    /**
     * Dismisses the alert and logs it.
     *
     * @param alertName the name of the alert
     */
    public static void sAlertDismiss(String alertName) {
        var alert = Waits.sIsAlertPresent();
        if (alert != null) {
            alert.dismiss();
            ReportLogger.logReportInfo("Clicked dismiss button on the alert " + alertName);
        } else {
            ReportLogger.logReportWarn(MSG3 + "Alert dismiss " + MSG4);
        }
    }

}




