/*
 *
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
 *  *  ** Mar 05, 2023
 *  *  * @author Surendiran Selvam
 *  *  * @version 1.0
 *  *  * @since 1.0
 *  *
 *
 *
 */

package com.demo.constants;
import com.demo.config.ConfigFactory;
import lombok.experimental.UtilityClass;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 This class contains constants and utility methods for the framework. */
@UtilityClass
public class FrameworkConstants {
  /**

   The absolute path of the project directory. */
  private static final String PROJECT_PATH = System.getProperty("user.dir");
  /**

   The path of the resources folder. */
  public static final String RESOURCES_FOLDER_PATH = PROJECT_PATH + File.separator + "src" + File.separator +"test" + File.separator + "resources";
  /**

   The path of the config.properties file. */
  public static final String CONFIG_PROPERTIES_PATH = RESOURCES_FOLDER_PATH + File.separator + "config.properties";
  /**

   The path of the JSON schema file. */
  public static final String JSON_SCHEMA_PATH = RESOURCES_FOLDER_PATH + File.separator + "json" + File.separator +"json-schema.json";
  /**

   The path of the XML XSD schema file. */
  public static final String XML_XSD_SCHEMA_PATH = RESOURCES_FOLDER_PATH + File.separator + "xml" + File.separator +"xml-xsd-schema.xsd";
  /**

   The path of the XML DTD schema file. */
  public static final String XML_DTD_SCHEMA_PATH = RESOURCES_FOLDER_PATH + File.separator + "xml" + File.separator +"xml-dtd-schema.dtd";
  /**

   The path of the extent report directory. */
  private static final String EXTENT_REPORT_PATH = PROJECT_PATH + File.separator + "extent-test-report";
  /**

   Returns the path of the extent report file based on the configuration.

   @return the path of the extent report file. */
  public static String getExtentReportPath() { if (ConfigFactory.getConfig().override_reports().equalsIgnoreCase("yes")) { return EXTENT_REPORT_PATH + File.separator + "index.html"; } else { return EXTENT_REPORT_PATH + File.separator + getCurrentDateTime() + File.separator + "index.html"; } }
  /**

   Returns the current date and time as a formatted string.

   @return the current date and time as a formatted string. */
  private static String getCurrentDateTime() { DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy_MM_dd-HH_mm_ss");
    return dateTimeFormatter.format(LocalDateTime.now()); } }
