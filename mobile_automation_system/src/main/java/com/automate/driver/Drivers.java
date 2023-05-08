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

package com.automate.driver;

import com.automate.constants.FrameworkConstants;
import com.automate.customexceptions.DriverInitializationException;
import com.automate.enums.ConfigJson;
import com.automate.enums.MobileBrowserName;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

import static com.automate.utils.configloader.JsonUtils.getConfig;

@UtilityClass
public  class Drivers {

  public static AppiumDriver<MobileElement> createAndroidDriverForNativeApp(String deviceName, String udid, int port, String emulator) {
    try {
      var capability = new DesiredCapabilities();
      capability.setCapability(CapabilityType.PLATFORM_NAME, Platform.ANDROID);
      capability.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
      capability.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2); // Specific to Android
      capability.setCapability(MobileCapabilityType.UDID, udid); // To uniquely identify device
      capability.setCapability(MobileCapabilityType.APP, FrameworkConstants.ANDROID_APK_PATH);
      capability.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, getConfig(ConfigJson.APP_PACKAGE));
      capability.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, getConfig(ConfigJson.APP_ACTIVITY));
      capability.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT,
                               port); // To set different port for each thread - This port is used to communicate with UiAutomator2
      if (emulator.equalsIgnoreCase("yes")) {
        capability.setCapability(AndroidMobileCapabilityType.AVD, deviceName);
        capability.setCapability(AndroidMobileCapabilityType.AVD_LAUNCH_TIMEOUT,
                                 Integer.parseInt(getConfig(ConfigJson.AVD_LAUNCH_TIMEOUT)));
      }
      return new AndroidDriver<>(new URL(getConfig(ConfigJson.APPIUM_URL)), capability);
    } catch (Exception e) {
      throw new DriverInitializationException("Failed to initialize driver. Please check the desired capabilities", e);
    }
  }

  public static AppiumDriver<MobileElement> createAndroidDriverForWeb(String deviceName, String udid, int port, String emulator) {
    try {
      var capability = new DesiredCapabilities();
      capability.setCapability(CapabilityType.PLATFORM_NAME, Platform.ANDROID);
      capability.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
      capability.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
      capability.setCapability(MobileCapabilityType.UDID, udid);
      capability.setCapability(CapabilityType.BROWSER_NAME, MobileBrowserName.CHROME);
      capability.setCapability(AndroidMobileCapabilityType.CHROMEDRIVER_PORT,
                               port); // For Web view/Chrome browser to launch the browser on different port
      if (emulator.equalsIgnoreCase("yes")) {
        capability.setCapability(AndroidMobileCapabilityType.AVD, deviceName);
        capability.setCapability(AndroidMobileCapabilityType.AVD_LAUNCH_TIMEOUT,
                                 Integer.parseInt(getConfig(ConfigJson.AVD_LAUNCH_TIMEOUT)));
      }

      return new AndroidDriver<>(new URL(getConfig(ConfigJson.APPIUM_URL)), capability);
    } catch (Exception e) {
      throw new DriverInitializationException("Failed to initialize driver. Please check the desired capabilities", e);
    }
  }

  public static AppiumDriver<MobileElement> createIOSDriverForNativeApp(String deviceName, String udid, int port) {
    try {
      var capability = new DesiredCapabilities();
      capability.setCapability(CapabilityType.PLATFORM_NAME, Platform.IOS);
      capability.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
      capability.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
      capability.setCapability(MobileCapabilityType.UDID, udid);
      capability.setCapability(MobileCapabilityType.APP, FrameworkConstants.IOS_APP_PATH);
      capability.setCapability(IOSMobileCapabilityType.BUNDLE_ID, getConfig(ConfigJson.BUNDLE_ID));
      capability.setCapability(IOSMobileCapabilityType.WDA_LOCAL_PORT,
                               port); // To set different port for each thread - This port is used to communicate with WebDriverAgent driver

      return new IOSDriver<>(new URL(getConfig(ConfigJson.APPIUM_URL)), capability);
    } catch (Exception e) {
      throw new DriverInitializationException("Failed to initialize driver. Please check the desired capabilities", e);
    }
  }

  public static AppiumDriver<MobileElement> createIOSDriverForWeb(String deviceName, String udid, int port) {
    try {
      var capability = new DesiredCapabilities();
      capability.setCapability(CapabilityType.PLATFORM_NAME, Platform.IOS);
      capability.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
      capability.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
      capability.setCapability(MobileCapabilityType.UDID, udid);
      capability.setCapability(IOSMobileCapabilityType.BUNDLE_ID, getConfig(ConfigJson.BUNDLE_ID));
      capability.setCapability(CapabilityType.BROWSER_NAME, MobileBrowserName.SAFARI);
      capability.setCapability("webkitDebugProxyPort", port); // For web view/Safari browser testing on real device

      return new IOSDriver<>(new URL(getConfig(ConfigJson.APPIUM_URL)), capability);
    } catch (Exception e) {
      throw new DriverInitializationException("Failed to initialize driver. Please check the desired capabilities", e);
    }
  }
}