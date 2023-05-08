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

package com.automate.utils.screenrecording;

import com.automate.constants.FrameworkConstants;
import com.automate.driver.manager.DriverManager;
import io.appium.java_client.screenrecording.CanRecordScreen;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;
import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@UtilityClass
public  class ScreenRecordingUtils {

  public static void startScreenRecording() {
    ((CanRecordScreen) DriverManager.getDriver()).startRecordingScreen();
  }

  public static void stopScreenRecording(String methodName) {
    var recordedVideoFile = ((CanRecordScreen) DriverManager.getDriver()).stopRecordingScreen();
    var pathToWriteVideoFile = FrameworkConstants.getScreenRecordingsPath() + File.separator + methodName + ".mp4";
    writeToOutputStream(pathToWriteVideoFile, recordedVideoFile);
  }

  static void writeToOutputStream(String filePathToWrite, String recordedVideoFile) {
    try (FileOutputStream outputStream = new FileOutputStream(filePathToWrite)) {
      outputStream.write(Base64.decodeBase64(recordedVideoFile));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
