package demo.listeners;

import demo.annotations.FrameworkAnnotations;
import demo.libraries.loggers.ReportLogger;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static demo.libraries.loggers.ReportLogger.addAuthors;
import static demo.libraries.loggers.ReportLogger.addCategories;


public class ListenerClass implements ITestListener, ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        ReportLogger.startLogReporting();
    }

    @Override
    public void onFinish(ISuite suite) {
        ReportLogger.endLogReporting();

    }

    @Override
    public void onTestStart(ITestResult result) {
        ReportLogger.createTestName(result.getMethod().getDescription());
        addAuthors(result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotations.class)
                .author());
        addCategories(result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotations.class)
                .category());


    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ReportLogger.logReportInfo("Test - <b>" + result.getMethod().getMethodName() + "</b> is passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ReportLogger.logReportFail("Test - <b>" + result.getMethod().getMethodName() + "</b> is failed");
    }


}
