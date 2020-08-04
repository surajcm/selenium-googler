package com.google.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.Locale;

public class DriverConfigurations {
    private static final Logger logger = LogManager.getLogger(DriverConfigurations.class);

    private static final String DRIVER = "webdriver.chrome.driver";
    public static final String CHROME_DRIVER = "chromedriver";
    private WebDriver chromeDriver;

    public enum OSType {
        WINDOWS, MAC_OS, LINUX, OTHER
    }

    public WebDriver getWebDriverInstance() {
        if (chromeDriver == null) {
            logger.info("Setting up new driver !!!");
            setupDriver();
        }
        return chromeDriver;
    }

    public void tearDown() {
        chromeDriver.quit();
    }

    private void setupDriver() {
        DriverConfigurations.OSType ostype = getOperatingSystem();
        if (ostype == OSType.WINDOWS) {
            logger.info("Using windows driver");
            System.setProperty(DRIVER, "chromedriver_win32" + File.separator + "chromedriver.exe");
        } else if (ostype == OSType.LINUX) {
            logger.info("Using linux driver");
            System.setProperty(DRIVER, "chromedriver_linux_64" + File.separator + CHROME_DRIVER);
        } else if (ostype == OSType.MAC_OS) {
            logger.info("Using mac driver");
            System.setProperty(DRIVER, "chromedriver_mac64" + File.separator + CHROME_DRIVER);
        } else {
            logger.error("Unable to identify OS");
        }
        try {
            if (ostype == OSType.LINUX) {
                //assuming this is jenkins
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                chromeDriver = new ChromeDriver(chromeOptions);
            } else {
                chromeDriver = new ChromeDriver();
            }
        } catch (Exception e) {
            logger.info("Error occurred : "+ e);
            e.printStackTrace();
        }
    }

    private OSType getOperatingSystem() {
        OSType detectedOS;
        String os = System.getProperty("os.name", "generic")
                .toLowerCase(Locale.ENGLISH);
        if ((os.contains("mac")) || (os.contains("darwin"))) {
            detectedOS = OSType.MAC_OS;
        } else if (os.contains("win")) {
            detectedOS = OSType.WINDOWS;
        } else if (os.contains("nux")) {
            detectedOS = OSType.LINUX;
        } else {
            detectedOS = OSType.OTHER;
        }
        return detectedOS;
    }
}


