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

import demo.enums.AppPropertyKey;
import lombok.*;
import lombok.experimental.UtilityClass;

import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

/**
 * The TestConfig class contains methods for loading and retrieving properties from an application properties file.
 */
@UtilityClass
public class TestConfig {
    /**
     * Loads the properties from the specified resource file.
     *
     * @param resourceFileName the name of the resource file to load
     * @return a Properties object containing the loaded properties
     * @throws java.io.IOException if an I/O error occurs while reading the resource file
     */

    @SneakyThrows
    private static Properties loadProperties(String resourceFileName) {
        Properties configuration = new Properties();
        InputStream inputStream = TestConfig.class.getClassLoader().getResourceAsStream(resourceFileName);
        configuration.load(inputStream);
        Objects.requireNonNull(inputStream).close();
        return configuration;
    }

    /**
     * Returns the value of the property associated with the specified key.
     *
     * @param key the key of the property to retrieve
     * @return the value of the property associated with the specified key
     */
    public static String getProperty(AppPropertyKey key) {
        return loadProperties("application.properties").getProperty(key.toString());
    }

}
