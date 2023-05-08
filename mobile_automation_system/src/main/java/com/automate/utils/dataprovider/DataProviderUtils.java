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

package com.automate.utils.dataprovider;

import com.automate.constants.FrameworkConstants;
import com.automate.entity.LoginData;
import com.automate.entity.SearchData;
import com.automate.entity.TestData;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

@UtilityClass
public  class DataProviderUtils {

  private static List<Map<String, String>> list = new ArrayList<>();

  @DataProvider
  public static Object[][] getData(Method method) {
    LoginData loginData;
    SearchData searchData;
    TestData testData = null;
    String testName = method.getName();

    if (list.isEmpty())
      list = ExcelUtils.getTestDetails(FrameworkConstants.TEST_DATA_SHEET);

    List<Map<String, String>> smallList = new ArrayList<>(list);

    Predicate<Map<String, String>> isTestNameNotMatching = map -> !map.get("TestCaseName").equalsIgnoreCase(testName);

    smallList.removeIf(isTestNameNotMatching);

    for (Map<String, String> mapData : smallList) {

      loginData = LoginData.builder()
        .setLoginUsername(mapData.get("username"))
        .setLoginPassword(mapData.get("password"))
        .build();

      searchData = SearchData.builder()
        .setSearchText(mapData.get("searchTerm"))
        .build();

      testData = TestData.builder()
        .setLoginData(loginData)
        .setSearchData(searchData)
        .build();
    }
    return new Object[][] {
      {testData}
    };
  }
}

