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

package com.demo.tests;

import com.demo.annotations.FrameworkAnnotation;
import com.demo.reports.ExtentLogger;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.demo.enums.Authors.USER_1;
import static com.demo.enums.CategoryType.SMOKE;
import static com.demo.models.builders.RequestBuilder.createRequestSpecification;
import static com.demo.models.builders.ResponseBuilder.createResponseSpecification;
import static com.demo.reports.ExtentLogger.logRequestInReport;
import static com.demo.reports.ExtentLogger.logResponseInReport;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

@UtilityClass
public class sampleTest {

  @FrameworkAnnotation(author = USER_1, category = {SMOKE})
  @Test(description = "Validate the status code for GET request")
  public void getRequestToValidateStatusCode() {
     createRequestSpecification();
    Response response = given().
      spec(createRequestSpecification()).
      when().
      get("/normal/webapi/all").
      then().
      log().all().// All Response logging
        log().ifError().// Log response only if error
        log().ifValidationFails().// Log response only if there is failure in validation
        extract().response();

    logRequestInReport(response.toString());
    logResponseInReport("API RESPONSE", response.prettyPrint());

    response.then().assertThat().statusCode(200);
  }

  @FrameworkAnnotation(author = USER_1, category = {SMOKE})
  @Test(description = "Validate the JSON response body for GET request")
  public void getRequestToValidateJsonResponseBody() {
    Response response = given().
      spec(createRequestSpecification()).
      when().
      get("/normal/webapi/all").
      then().
      spec(createResponseSpecification()).
      extract().response();

    logRequestInReport(response.toString());
    logResponseInReport("API RESPONSE", response.prettyPrint());

    // Hamcrest Matchers assertion
    response.then().assertThat().body("[0].jobTitle", equalTo("Software Engg"),
                                      "[0].project[0].technology", hasItem("Kotlin"),
                                      "[0].jobId", equalTo(1));
  }

  @FrameworkAnnotation(author = USER_1, category = {SMOKE})
  @Test(description = "Validate the XML response body for GET request")
  public void getRequestToValidateXmlResponseBody() {
    Response response = given().
      spec(createRequestSpecification()).
      header("Accept", "application/xml").
      when().
      get("/normal/webapi/all").
      then().
      extract().response();

    logRequestInReport(response.toString());
    logResponseInReport("API RESPONSE", response.prettyPrint());

    response.then().assertThat().body("List.item.jobTitle", equalTo("Software Engg"),
                                      "List.item.project.project.technology.technology", hasItem("Kotlin"),
                                      "List.item.jobId", equalTo("1"));
  }

  @FrameworkAnnotation(author = USER_1, category = {SMOKE})
  @Test(description = "Validate the JSON response body using Json Path for GET request")
  public void getRequestToValidateResponseBodyUsingJsonPath() {
    Response response = given().
      spec(createRequestSpecification()).
      when().
      get("/normal/webapi/all").
      then().
      extract().response();

    String jobTitle = response.jsonPath().get("[0].jobTitle");

    logRequestInReport(response.toString());
    logResponseInReport("API RESPONSE", response.prettyPrint());
    logResponseInReport("API RESPONSE BODY", jobTitle);

    // TestNG Assertion
    Assert.assertEquals(jobTitle, "Software Engg");
  }

  @FrameworkAnnotation(author = USER_1, category = {SMOKE})
  @Test(description = "Validate the JSON response header for GET request")
  public void getRequestToValidateJsonResponseHeader() {
    Response response = given().
      spec(createRequestSpecification()).
      when().
      get("/normal/webapi/all");

    Headers responseHeaders = response.then().extract().headers();

    logRequestInReport(response.toString());
    logResponseInReport("API RESPONSE", response.prettyPrint());
    logResponseInReport("API RESPONSE HEADER", responseHeaders.toString());

    response.then().assertThat().header("content-type", equalTo("application/json"));
  }


}
