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

package com.automate.utils.configloader;

import com.automate.constants.FrameworkConstants;
import com.automate.customexceptions.InvalidPathException;
import com.automate.customexceptions.JsonFileUsageException;
import com.automate.enums.ConfigJson;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@UtilityClass
public  class JsonUtils {

  private static Map<String, String> map;

  public static String getValue(String key) {
    try {
      return JsonPath.read(new File(FrameworkConstants.CONFIG_JSON_PATH), key);
    } catch (IOException e) {
      throw new InvalidPathException("Check the config.json");
    }
  }

  static void readJson(String jsonPath) {
    try {
      map = new ObjectMapper().readValue(new File(jsonPath),
                                         new TypeReference<HashMap<String, String>>() {
                                         });
    } catch (IOException e) {
      throw new JsonFileUsageException("IOException occurred while reading Json file in the specified path");
    }
  }

  public static String getConfig(ConfigJson key) {
    readJson(FrameworkConstants.CONFIG_JSON_PATH);
    if (Objects.isNull(map.get(key.name().toLowerCase()))) {
      throw new JsonFileUsageException("Property name - " + key + " is not found. Please check the config.json");
    }
    return map.get(key.name().toLowerCase());
  }
}
