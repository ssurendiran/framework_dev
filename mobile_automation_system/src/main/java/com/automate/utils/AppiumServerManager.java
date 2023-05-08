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

package com.automate.utils;

import com.automate.constants.FrameworkConstants;
import com.automate.enums.ConfigProperties;
import com.automate.utils.configloader.PropertyUtils;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import lombok.experimental.UtilityClass;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;

@UtilityClass
public  class AppiumServerManager {

  private static AppiumDriverLocalService service;

  static boolean checkIfServerIsRunning(int port) {
    boolean isServerRunning = false;
    try {
      ServerSocket serverSocket = new ServerSocket(port);
      serverSocket.close();
    } catch (IOException e) {
      isServerRunning = true;
    }
    return isServerRunning;
  }

  public static void startAppiumServer() {
    if (PropertyUtils.getPropertyValue(ConfigProperties.START_APPIUM_SERVER).equalsIgnoreCase("yes")) {
      if (!AppiumServerManager.checkIfServerIsRunning(FrameworkConstants.APPIUM_SERVER_PORT)) {
        //Build the Appium service
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.usingDriverExecutable(new File(FrameworkConstants.NODEJS_PATH))
          .withAppiumJS(new File(FrameworkConstants.APPIUM_JS_PATH))
          .withIPAddress(FrameworkConstants.APPIUM_SERVER_HOST)
          .usingPort(FrameworkConstants.APPIUM_SERVER_PORT)
          .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
          .withArgument(GeneralServerFlag.ALLOW_INSECURE, "chromedriver_autodownload")
          .withLogFile(new File(FrameworkConstants.getAppiumServerLogsPath()));
        //Start the server with the builder
        service = AppiumDriverLocalService.buildService(builder);
//				service = AppiumDriverLocalService.buildDefaultService();
        service.start();
        service.clearOutPutStreams();
      }
    }
  }

  public static void stopAppiumServer() {
    if (PropertyUtils.getPropertyValue(ConfigProperties.START_APPIUM_SERVER).equalsIgnoreCase("yes")) {
      service.stop();
      Runtime runtime = Runtime.getRuntime();
      try {
        runtime.exec("taskkill /F /IM node.exe");
        runtime.exec("taskkill /F /IM cmd.exe");
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
