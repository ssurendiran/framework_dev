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

package com.demo.config;

import org.aeonbits.owner.Config;
/**
 * The FrameworkConfig interface is used to define the configuration properties
 * required for the application. It extends the Config interface from the
 * org.aeonbits.owner library.
 *
 * The configuration properties can be loaded from different sources using the
 * @Config.Sources annotation. In this case, the properties are loaded from
 * system properties, environment variables and a properties file located at
 * ${user.dir}/src/test/resources/config/config.properties.
 *
 * The configuration properties are defined as constants and have corresponding
 * methods to retrieve their values. The @DefaultValue annotation is used to
 * provide default values for the configuration properties, in case they are
 * not present in the loaded configuration.
 */
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
  "system:properties",
  "system:env",
  "file:${user.dir}/src/test/resources/config/config.properties"
})
public interface FrameworkConfig extends Config {

  String OVERRIDE_REPORTS = "override_reports";
  String RETRY_FAILED_TESTS = "retry_failed_tests";
  String RETRY_COUNT = "retry_count";
  String BASE_URI = "base_uri";

  /**
   * Returns the value of the 'override_reports' configuration property.
   *
   * @return the value of the 'override_reports' configuration property.
   */
  @DefaultValue("")
  String override_reports();

  /**
   * Returns the value of the 'retry_failed_tests' configuration property.
   *
   * @return the value of the 'retry_failed_tests' configuration property.
   */
  @DefaultValue("false")
  boolean retry_failed_tests();

  /**
   * Returns the value of the 'retry_count' configuration property.
   *
   * @return the value of the 'retry_count' configuration property.
   */
  @DefaultValue("0")
  int retry_count();

  /**
   * Returns the value of the 'base_uri' configuration property.
   *
   * @return the value of the 'base_uri' configuration property.
   */
  @DefaultValue("")
  String base_uri();
}
