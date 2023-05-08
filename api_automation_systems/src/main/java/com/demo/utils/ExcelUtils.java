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

package com.demo.utils;

import com.demo.constants.FrameworkConstants;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class ExcelUtils {

  FileInputStream in;
  FileOutputStream out;
  int cellNum;
  int rowNum;
  private XSSFWorkbook wb;
  private XSSFSheet sh;
  private XSSFRow row;
  private XSSFCell cell;

  public ExcelUtils() {
    String filePath = FrameworkConstants.RESOURCES_FOLDER_PATH + File.separator + "TestData.xlsx";
    try {
      wb = new XSSFWorkbook(filePath);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public XSSFSheet getSheetByIndex(int index) {
    sh = wb.getSheetAt(index);
    return sh;
  }

  public int getRowCount() {
    return getSheetByIndex(0).getLastRowNum();
  }

  public int getColumnCount() {
    row = getSheetByIndex(0).getRow(0);
    return row.getLastCellNum();
  }

  public Map<String, ArrayList<Object>> getExcelData() {
    HashMap<String, ArrayList<Object>> map = new HashMap<>();
    ArrayList<Object> list;

    for (int i = 0; i < getColumnCount(); i++) {
      list = new ArrayList<>();
      for (int j = 1; j < getRowCount(); j++) {
        cell = getSheetByIndex(0).getRow(j).getCell(i);

        switch (cell.getCellType()) {
          case NUMERIC:
            list.add(cell.getNumericCellValue());
            break;
          case FORMULA:
            list.add(cell.getCellFormula());
            break;
          default:
            list.add(cell.getStringCellValue());
            break;
        }
      }
      map.put(getSheetByIndex(0).getRow(0).getCell(i).getStringCellValue(), list);
    }
    return map;
  }

  public void writeData(String tcID, String key, String value) {
    try {
      File f = new File(System.getProperty("user.dir") + "/TestData.xlsx");
      in = new FileInputStream(f);
      XSSFWorkbook workbook = new XSSFWorkbook(in);

      in.close();

      sh = workbook.getSheet("Test");
      int cellCount = sh.getRow(0).getLastCellNum();

      for (int i = 1; i <= cellCount; i++) {
        if ((sh.getRow(0).getCell(i).getStringCellValue()).equals(key)) {
          cellNum = i;
          break;
        }
      }
      int rowCount = sh.getLastRowNum() + 1;
      for (int j = 1; j <= rowCount; j++) {
        if ((sh.getRow(j).getCell(0).getStringCellValue()).equals(tcID)) {
          rowNum = j;
          break;
        }
      }
      row = sh.getRow(rowNum);
      cell = row.createCell(cellNum);
      cell.setCellValue(value);

      out = new FileOutputStream(f);
      workbook.write(out);
      out.close();
      System.out.println("Written successfully on file....");
      workbook.close();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (in != null || out != null) {
          in.close();
          out.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

}
