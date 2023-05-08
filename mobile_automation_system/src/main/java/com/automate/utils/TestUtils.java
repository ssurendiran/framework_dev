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
import lombok.experimental.UtilityClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

@UtilityClass
public  class TestUtils {

  public static void deleteFolder(File file) {
    File[] files = file.listFiles();
    if (Objects.nonNull(files))
      Arrays.asList(files).forEach(content -> file.delete());
  }

  public static HashMap<String, String> parseStringXML(InputStream in) throws IOException, SAXException, ParserConfigurationException {
    HashMap<String, String> map = new HashMap<>();

    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = docFactory.newDocumentBuilder();

    Document document = builder.parse(in);
    document.getDocumentElement().normalize();

    Element root = document.getDocumentElement();
    NodeList nList = document.getElementsByTagName("string");

    for (int temp = 0; temp < nList.getLength(); temp++) {
      Node node = nList.item(temp);
      if (node.getNodeType() == Node.ELEMENT_NODE) {
        Element element = (Element) node;
        map.put(element.getAttribute("name"), element.getTextContent());
      }
    }
    return map;
  }

  public static Logger log() {
    return LogManager.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());
  }
}
