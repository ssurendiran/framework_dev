/*
 *
 * MIT License
 * Copyright (c) 2023 Surendiran Selvam
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *  ** Mar 05, 2023
 *  * @author Surendiran Selvam
 *  * @version 1.0
 *  * @since 1.0
 *
 */

package demo.libraries.utils;

import demo.enums.Column;
import demo.exceptions.FrameworkExceptions;
import demo.exceptions.NoSuchExcelSheet;
import lombok.experimental.UtilityClass;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

import static org.apache.commons.lang3.SystemUtils.USER_DIR;


/**
 * Utility class for Excel related operations.
 */
@UtilityClass
public final class ExcelUtils {

    private static final Path RESOURCES_PATH = Path.of(USER_DIR, "src", "test", "resources");
    public static final String SET_TEST_DATA_PATH = RESOURCES_PATH + File.separator + "ExcelTestCases";
    public static final String SET_DEMO_TEST_DATA = SET_TEST_DATA_PATH + File.separator + "Demo.xlsx";

    /**
     * Sets up the specified Excel sheet for reading and returns it as an XSSFSheet object.
     *
     * @param sheetName the name of the Excel sheet to read
     * @return an XSSFSheet object representing the Excel sheet
     * @throws NoSuchExcelSheet    if the specified Excel sheet cannot be found
     * @throws FrameworkExceptions if an IOException occurs while reading the Excel file
     */
    private static XSSFSheet setExcelDataResources(String sheetName) {
        XSSFWorkbook wb;
        try (FileInputStream fis = new FileInputStream(SET_DEMO_TEST_DATA)) {
            wb = new XSSFWorkbook(fis);
        } catch (FileNotFoundException e) {
            throw new NoSuchExcelSheet("Excel File you trying to read is not found");
        } catch (IOException e) {
            throw new FrameworkExceptions("IOException happened while reading excel file");
        }
        return wb.getSheet(sheetName);
    }

    /**
     * Retrieves all rows of data from the specified Excel sheet and returns it as a 2D Object array.
     *
     * @param sheetName the name of the Excel sheet to read
     * @return a 2D Object array containing all rows of data in the Excel sheet
     */

    public static Object[][] getAllRowExcelData(String sheetName) {
        var sheet = setExcelDataResources(sheetName);
        var lastRowNum = sheet.getLastRowNum();
        var lastColNum = sheet.getRow(0).getLastCellNum();
        Object[][] obj = new Object[lastRowNum][1];
        for (int i = 0; i < lastRowNum; i++) {
            Map<Column, String> datamap = new LinkedHashMap<>();
            var row = sheet.getRow(i + 1);
            if (row != null) {
                for (int j = 0; j < lastColNum; j++) {
                    var key = Column.valueOf(sheet.getRow(0).getCell(j).toString().toUpperCase().trim());
                    var value = row.getCell(j).toString().trim();
                    datamap.put(key, value);
                }
                obj[i][0] = datamap;
            }
        }
        return obj;
    }

    /**
     * Retrieves all rows of data from the specified Excel sheet and returns it as a 2D Object array.
     *
     * @param sheetName    the name of the Excel sheet to read
     * @param testCaseName the name of the test case to be fetched from excel
     * @return a 2D Object array containing single row of data of the specified testcase  in the Excel sheet
     */

    public static Object[][] getSingleRowExcelData(String sheetName, String testCaseName) {
        var sheet = setExcelDataResources(sheetName);
        var lastRowNum = sheet.getLastRowNum();
        var lastColNum = sheet.getRow(0).getLastCellNum();
        Map<Column, String> datamap = new LinkedHashMap<>();
        Object[][] obj = new Object[1][1];
        for (int i = 0; i < lastRowNum; i++) {
            if (!sheet.getRow(i + 1).getCell(0).getStringCellValue().equalsIgnoreCase(testCaseName)) {
                if (i == lastRowNum - 1) {
                    throw new UnsupportedOperationException("Test case name not available in the excel sheet " + SET_DEMO_TEST_DATA);
                }
            } else {
                for (int j = 0; j < lastColNum; j++) {
                    var key = Column.valueOf(sheet.getRow(0).getCell(j).toString().toUpperCase().trim());
                    var value = sheet.getRow(i + 1).getCell(j).toString().trim();
                    datamap.put(key, value);
                }
                break;
            }
        }
        obj[0][0] = datamap;
        return obj;
    }

}




















