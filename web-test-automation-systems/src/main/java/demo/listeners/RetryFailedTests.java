package demo.listeners;

import demo.enums.AppPropertyKey;
import demo.libraries.utils.TestConfig;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedTests implements IRetryAnalyzer {

    private int count = 0;
    private final String maxRetry = TestConfig.getProperty(AppPropertyKey.RETRY_COUNT);

    @Override
    public boolean retry(ITestResult result) {
        boolean value = false;
        if (TestConfig.getProperty(AppPropertyKey.RETRY_FAILED_TEST).equalsIgnoreCase("true")) {
            value = count < Integer.parseInt(maxRetry);
            count++;
        }
        return value;
    }
}
