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

package com.automate.factories;

import com.automate.constants.FrameworkConstants;
import com.automate.driver.manager.DriverManager;
import com.automate.enums.WaitStrategy;
import io.appium.java_client.MobileElement;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;
import static com.automate.enums.WaitStrategy.CLICKABLE;
import static com.automate.enums.WaitStrategy.NONE;
import static com.automate.enums.WaitStrategy.PRESENCE;
import static com.automate.enums.WaitStrategy.VISIBLE;

@UtilityClass
public  class WaitFactory {

  private static final Map<WaitStrategy, Function<MobileElement, WebElement>> WAIT_FOR_ELEMENT_FUNCTION_MAP =
    new EnumMap<>(WaitStrategy.class);

  private static final Function<MobileElement, WebElement> CLICKABLE_ELEMENT = mobileElement ->
    new WebDriverWait(DriverManager.getDriver(), FrameworkConstants.EXPLICIT_WAIT)
      .until(ExpectedConditions.elementToBeClickable(mobileElement));
  private static final Function<MobileElement, WebElement> VISIBILITY_OF_ELEMENT = mobileElement ->
    new WebDriverWait(DriverManager.getDriver(), FrameworkConstants.EXPLICIT_WAIT)
      .until(ExpectedConditions.visibilityOf(mobileElement));
  private static final Function<MobileElement, WebElement> NO_MATCH = mobileElement -> mobileElement;

  private static final Map<WaitStrategy, Function<By, WebElement>> WAIT_FOR_ELEMENT_LOCATED_BY_FUNCTION_MAP =
    new EnumMap<>(WaitStrategy.class);

  private static final Function<By, WebElement> CLICKABLE_ELEMENT_BY = by ->
    new WebDriverWait(DriverManager.getDriver(), FrameworkConstants.EXPLICIT_WAIT)
      .until(ExpectedConditions.elementToBeClickable(by));
  private static final Function<By, WebElement> PRESENCE_OF_ELEMENT_BY = by ->
    new WebDriverWait(DriverManager.getDriver(), FrameworkConstants.EXPLICIT_WAIT)
      .until(ExpectedConditions.presenceOfElementLocated(by));
  private static final Function<By, WebElement> VISIBILITY_OF_ELEMENT_BY = by ->
    new WebDriverWait(DriverManager.getDriver(), FrameworkConstants.EXPLICIT_WAIT)
      .until(ExpectedConditions.visibilityOfElementLocated(by));
  private static final Function<By, WebElement> NO_MATCH_BY = by -> DriverManager.getDriver().findElement(by);

  static {
    WAIT_FOR_ELEMENT_FUNCTION_MAP.put(CLICKABLE, CLICKABLE_ELEMENT);
    WAIT_FOR_ELEMENT_FUNCTION_MAP.put(VISIBLE, VISIBILITY_OF_ELEMENT);
    WAIT_FOR_ELEMENT_FUNCTION_MAP.put(NONE, NO_MATCH);
    WAIT_FOR_ELEMENT_LOCATED_BY_FUNCTION_MAP.put(CLICKABLE, CLICKABLE_ELEMENT_BY);
    WAIT_FOR_ELEMENT_LOCATED_BY_FUNCTION_MAP.put(PRESENCE, PRESENCE_OF_ELEMENT_BY);
    WAIT_FOR_ELEMENT_LOCATED_BY_FUNCTION_MAP.put(VISIBLE, VISIBILITY_OF_ELEMENT_BY);
    WAIT_FOR_ELEMENT_LOCATED_BY_FUNCTION_MAP.put(NONE, NO_MATCH_BY);
  }

  public static WebElement explicitlyWaitForElementLocatedBy(WaitStrategy waitStrategy, By by) {
    return WAIT_FOR_ELEMENT_LOCATED_BY_FUNCTION_MAP.get(waitStrategy).apply(by);
  }

  public static WebElement explicitlyWaitForElement(WaitStrategy waitStrategy, MobileElement mobileElement) {
    return WAIT_FOR_ELEMENT_FUNCTION_MAP.get(waitStrategy).apply(mobileElement);
  }
}
