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

package demo.constansts;

import demo.enums.DemoAppNames;
import lombok.experimental.UtilityClass;

import java.util.Map;

/**
 * A utility class containing constants and methods related to the demo application framework.
 */
@UtilityClass
public class FrameworkConstants {

    /**
     * A map of demo application names to their corresponding URLs.
     */
    private static final Map<DemoAppNames, String> APP_URLS = Map.of(
            DemoAppNames.FLIPKART, "www.amazon.in",
            DemoAppNames.AMAZON, "www.flipkart.com"

    );

    /**
     * Gets the URL for the specified demo application.
     *
     * @param appName the name of the demo application
     * @return the URL for the specified demo application
     * @throws IllegalArgumentException if the specified application name is not recognized
     */
    public static String getAppUrl(DemoAppNames appName) {
        return APP_URLS.get(appName);
    }

}