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
 *
 */

package demo.libraries.assertions;

import demo.libraries.loggers.ReportLogger;
import lombok.experimental.UtilityClass;

import java.util.Objects;


/**
 * A utility class that provides commonly used assertion methods.
 */
@UtilityClass
public class FrameworkAssert {

    private static final String EXPECTED_ACTUAL_SEPARATOR = ") but found (";

    /**
     * Asserts that two strings are equal.
     *
     * @param actual        The actual string value.
     * @param expected      The expected string value.
     * @param assertDetails The details of the assertion.
     */
    public static void assertEquals(String actual, String expected, String assertDetails) {
        if (expected.equals(actual)) {
            ReportLogger.logReportPass(assertDetails + " || The actual string value (" + actual + ") matches with expected string value (" + expected + ")");
        } else {
            ReportLogger.logReportFail(assertDetails + " || Expected string value (" + expected + EXPECTED_ACTUAL_SEPARATOR + actual + ")");
        }
    }

    /**
     * Asserts that two boolean values are equal.
     *
     * @param actual        The actual boolean value.
     * @param expected      The expected boolean value.
     * @param assertDetails The details of the assertion.
     */
    public static void assertEquals(boolean actual, boolean expected, String assertDetails) {
        if (actual == expected) {
            ReportLogger.logReportPass(assertDetails + " || The actual boolean value (" + actual + ") matches with expected boolean value (" + expected + ")");
        } else {
            ReportLogger.logReportFail(assertDetails + " || Expected boolean value to be (" + expected + EXPECTED_ACTUAL_SEPARATOR + actual + ")");
        }
    }

    /**
     * Asserts that a boolean value is true.
     *
     * @param actual        The actual boolean value.
     * @param assertDetails The details of the assertion.
     */
    public static void assertTrue(boolean actual, String assertDetails) {
        if (actual) {
            ReportLogger.logReportPass(assertDetails + " || The actual boolean value (true) matches with the expected boolean value (true)");
        } else {
            ReportLogger.logReportFail(assertDetails + " || Expected boolean value to be (true), but got boolean value (false)");
        }
    }

    /**
     * Asserts that a boolean value is false.
     *
     * @param actual        The actual boolean value.
     * @param assertDetails The details of the assertion.
     */
    public static void assertFalse(boolean actual, String assertDetails) {
        if (!actual) {
            ReportLogger.logReportPass(assertDetails + " || The actual boolean value (false) matches with the expected boolean value (false)");
        } else {
            ReportLogger.logReportFail(assertDetails + " || Expected boolean value to be (false), but got boolean value (true)");
        }
    }

    /**
     * Asserts that two objects are equal.
     *
     * @param actual        The actual object value.
     * @param expected      The expected object value.
     * @param assertDetails The details of the assertion.
     */
    public static void assertObject(Object actual, Object expected, String assertDetails) {
        if (Objects.equals(actual, expected)) {
            ReportLogger.logReportPass(assertDetails + " || The actual object value (" + actual + ") matches with expected object value (" + expected + ")");
        } else {
            ReportLogger.logReportFail(assertDetails + " || Expected object boolean value to be (" + expected + EXPECTED_ACTUAL_SEPARATOR + actual + ")");
        }
    }
}