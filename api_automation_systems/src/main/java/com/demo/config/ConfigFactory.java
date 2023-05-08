package com.demo.config;/*
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

import com.demo.config.FrameworkConfig;
import lombok.experimental.UtilityClass;
import org.aeonbits.owner.ConfigCache;

/**
 * The ConfigFactory class is a utility class that provides a static method
 * to get an instance of FrameworkConfig. The instance is obtained using
 * ConfigCache.getOrCreate method which creates a new instance of FrameworkConfig
 * or returns a cached instance of FrameworkConfig if it exists.
 */
@UtilityClass
public class ConfigFactory {

  /**
   * Returns an instance of FrameworkConfig by calling ConfigCache.getOrCreate
   * method which creates a new instance of FrameworkConfig or returns a cached
   * instance of FrameworkConfig if it exists.
   *
   * @return an instance of FrameworkConfig
   */
  public static FrameworkConfig getConfig() {
    return ConfigCache.getOrCreate(FrameworkConfig.class);
  }
}
