package com.demo.base;

import demo.constansts.FrameworkConstants;
import demo.enums.BrowserTypes;
import demo.enums.DemoAppNames;
import demo.enums.ExecutionMode;
import demo.libraries.selenium.BrowserSettings;
import org.openqa.selenium.Platform;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static demo.enums.AppPropertyKey.*;
import static demo.libraries.utils.TestConfig.getProperty;


public class baseTest{


@BeforeMethod
public void setUp(){
    BrowserSettings.openBrowser(ExecutionMode.valueOf(getProperty(EXECUTION_MODE)),
            BrowserTypes.valueOf(getProperty(BROWSER)),
            getProperty(BROWSER_VERSION),
            Platform.valueOf(getProperty(PLATFORM)),
            getProperty(GRID_URL));
    System.out.println(DemoAppNames.valueOf(getProperty(APP_NAME)));
    BrowserSettings.sNavigateURL(FrameworkConstants.getAppUrl(DemoAppNames.valueOf(getProperty(APP_NAME))));
}

@AfterMethod
public  void tearDown(){
BrowserSettings.closeBrowser();
}


}
