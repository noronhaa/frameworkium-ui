package com.frameworkium.ui.listeners;

import com.frameworkium.base.ReflectionHelper;
import com.frameworkium.ui.driver.DriverSetup.Browser;
import com.frameworkium.ui.tests.BaseUITest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.*;

import static com.frameworkium.base.properties.Property.BROWSER;
import static com.frameworkium.ui.driver.DriverSetup.Browser.ELECTRON;

public class ScreenshotListener extends TestListenerAdapter {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public void onTestFailure(ITestResult failingTest) {
        if (isScreenshotSupported(failingTest)) {
            takeScreenshotAndSaveLocally(failingTest.getName());
        }
    }

    @Override
    public void onTestSkipped(ITestResult skippedTest) {
        if (isScreenshotSupported(skippedTest)) {
            takeScreenshotAndSaveLocally(skippedTest.getName());
        }
    }

    private void takeScreenshotAndSaveLocally(String testName) {
        takeScreenshotAndSaveLocally(testName, BaseUITest.getDriver());
    }

    private void takeScreenshotAndSaveLocally(String testName, TakesScreenshot driver) {
        String screenshotDirectory = System.getProperty("screenshotDirectory");
        if (screenshotDirectory == null) {
            screenshotDirectory = "screenshots";
        }
        String fileName = String.format(
                "%s_%s.png",
                System.currentTimeMillis(),
                testName);
        Path screenshotPath = Paths.get(screenshotDirectory);
        Path absolutePath = screenshotPath.resolve(fileName);
        if (createScreenshotDirectory(screenshotPath)) {
            writeScreenshotToFile(driver, absolutePath);
            logger.info("Written screenshot to " + absolutePath);
        } else {
            logger.error("Unable to create " + screenshotPath);
        }
    }

    private boolean createScreenshotDirectory(Path screenshotDirectory) {
        try {
            Files.createDirectories(screenshotDirectory);
        } catch (IOException e) {
            logger.error("Error creating screenshot directory", e);
        }
        return Files.isDirectory(screenshotDirectory);
    }

    private void writeScreenshotToFile(TakesScreenshot driver, Path screenshot) {
        try (OutputStream screenshotStream = Files.newOutputStream(screenshot)) {
            byte[] bytes = driver.getScreenshotAs(OutputType.BYTES);
            screenshotStream.write(bytes);
            screenshotStream.close();
        } catch (IOException e) {
            logger.error("Unable to write " + screenshot, e);
        } catch (WebDriverException e) {
            logger.error("Unable to take screenshot.", e);
        }

        new ReflectionHelper().addScreenshotToAllureIfUsing("Failure Screenshot", screenshot);
    }

    private boolean isScreenshotSupported(ITestResult testResult) {
        boolean isElectron = BROWSER.isSpecified()
                && ELECTRON.equals(Browser.valueOf(BROWSER.getValue().toUpperCase()));
        boolean isUITest = testResult.getInstance() instanceof BaseUITest;
        return isUITest && !isElectron;
    }

}
