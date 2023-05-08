package com.demo.models.builders;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.specification.RequestSpecification;
import lombok.experimental.UtilityClass;
import org.apache.commons.io.output.WriterOutputStream;
import java.io.PrintStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import static com.demo.config.ConfigFactory.getConfig;
import static io.restassured.RestAssured.given;


@UtilityClass
public class RequestBuilder {
  /**
   * Creates a RequestSpecification object with the base URI and logging filter set based on the FrameworkConfig settings.
   *
   * @return a RequestSpecification object with the configured base URI and logging filter
   */
  public static RequestSpecification createRequestSpecification() {
    PrintStream printStream = new PrintStream(new WriterOutputStream(new StringWriter(), StandardCharsets.UTF_8), true);
    return new RequestSpecBuilder().setBaseUri(getConfig().base_uri()).addFilter(new RequestLoggingFilter(printStream)).
      log(LogDetail.ALL).build();
  }

  /**
   * Builds a RequestSpecification object with a default base URI and content type header.
   *
   * @return a RequestSpecification object with a default base URI and content type header
   */
  public static RequestSpecification buildRequest() {
    return given().baseUri("http://localhost:3000").header("Content-Type", "application/json");
  }
}
