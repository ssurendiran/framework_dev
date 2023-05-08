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
import com.automate.customexceptions.FrameworkException;
import com.automate.customexceptions.InvalidPathException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@UtilityClass
public  class ExcelUtils {

  public static List<Map<String, String>> getTestDetails(String sheetName) {
    List<Map<String, String>> list;

    try (FileInputStream fs = new FileInputStream(FrameworkConstants.TEST_DATA_FILEPATH)) {
      XSSFWorkbook workbook = new XSSFWorkbook(fs);
      XSSFSheet sheet = workbook.getSheet(sheetName);

      int lastRowNum = sheet.getLastRowNum();
      int lastColNum = sheet.getRow(0).getLastCellNum();

      Map<String, String> map;
      list = new ArrayList<>();

      for (int i = 1; i <= lastRowNum; i++) {
        map = new HashMap<>();
        for (int j = 0; j < lastColNum; j++) {
          String key = sheet.getRow(0).getCell(j).getStringCellValue();
          String value = sheet.getRow(i).getCell(j).getStringCellValue();
          map.put(key, value);
        }
        list.add(map);
      }
    } catch (FileNotFoundException e) {
      throw new InvalidPathException("Excel File you trying to read is not found");
    } catch (IOException e) {
      throw new FrameworkException("IOException happened while reading excel file");
    }
    return list;
  }


}
