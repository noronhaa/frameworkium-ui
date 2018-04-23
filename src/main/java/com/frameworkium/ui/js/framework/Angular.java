package com.frameworkium.ui.js.framework;

import com.frameworkium.ui.js.AbstractFramework;
import com.paulhammant.ngwebdriver.NgWebDriver;
import org.openqa.selenium.JavascriptExecutor;

public class Angular implements AbstractFramework {

    public static final String IS_PRESENT_JS = "return typeof angular == 'object';";

    @Override
    public boolean isPresent(JavascriptExecutor javascriptExecutor) {
        return (Boolean) javascriptExecutor.executeScript(IS_PRESENT_JS);
    }

    @Override
    public void waitToBeReady(JavascriptExecutor javascriptExecutor) {
        new NgWebDriver(javascriptExecutor).waitForAngularRequestsToFinish();
    }
}
