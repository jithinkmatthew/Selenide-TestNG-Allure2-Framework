package io.github.symonk.testcases;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

@Slf4j
public class TestBaseTemplate {

    private static final String TEST_NAME = "test_name";

    @BeforeMethod(alwaysRun = true, description = "Setup logger for test")
    public void initiateLogger(final Method method) {
        startTestLogging(method.getName());
        log.info("Executing: + " + method.getName());
    }

    @AfterMethod
    public void finalizeLogger(final Method method) {
        stopTestLogging();
    }

    private void startTestLogging(String name) {
        log.info("Multi threaded logger initialized for test: " + name);
        MDC.put(TEST_NAME, name);
    }

    private String stopTestLogging() {
        String name = MDC.get(TEST_NAME);
        MDC.remove(TEST_NAME);
        return name;
    }


}
