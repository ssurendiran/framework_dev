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

package com.demo.reports;

import com.demo.enums.Authors;
import com.demo.enums.CategoryType;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.restassured.http.Header;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import lombok.experimental.UtilityClass;
import java.util.List;

@UtilityClass
public class ExtentLogger {

  public static void pass(String message) {
    ExtentManager.getExtentTest().pass(MarkupHelper.createLabel(message, ExtentColor.GREEN));
  }

  public static void logFailureDetails(String message) {
    ExtentManager.getExtentTest().fail(message);
  }

  public static void skip(String message) {
    ExtentManager.getExtentTest().skip(message);
  }

  public static void logInfo(Markup markup) {
    ExtentManager.getExtentTest().log(Status.INFO, markup);
  }

  // Overloaded method
  public static void logInfo(String message) {
    ExtentManager.getExtentTest().info(message);
  }

  public static void logResponse(String response) {
    ExtentManager.getExtentTest().pass(MarkupHelper.createCodeBlock(response, CodeLanguage.JSON));
  }

  public static void logRequest(RequestSpecification requestSpecification) {
    QueryableRequestSpecification query = SpecificationQuerier.query(requestSpecification);
    ExtentManager.getExtentTest().pass(MarkupHelper.createCodeBlock(String.valueOf(query.getBody()),
                                                                    CodeLanguage.JSON));
    for (Header header : query.getHeaders()) {
      logInfo(header.getName() + ":" + header.getValue());
    }
  }

  public static void logRequestInReport(String request) {
    logInfo(MarkupHelper.createLabel("API REQUEST", ExtentColor.ORANGE));
    logInfo(MarkupHelper.createCodeBlock(request));
  }

  public static void logResponseInReport(String label, String response) {
    logInfo(MarkupHelper.createLabel(label, ExtentColor.ORANGE));
    logInfo(MarkupHelper.createCodeBlock(response));
  }

  public static void logHeaders(List<Header> headerList) {
    String[][] headers = headerList.stream().map(header -> new String[] {header.getName(), header.getValue()})
      .toArray(String[][] :: new);
    ExtentManager.getExtentTest().info(MarkupHelper.createTable(headers));
  }

  public static void addAuthors(Authors[] authors) {
    for (Authors author : authors) {
      ExtentManager.getExtentTest().assignAuthor(String.valueOf(author));
    }
  }

  public static void addCategories(CategoryType[] categoryTypes) {
    for (CategoryType categoryType : categoryTypes) {
      ExtentManager.getExtentTest().assignCategory(String.valueOf(categoryType));
    }
  }
}
