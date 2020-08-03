package com.google.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.Locale;

public class TestBase {
    private static final String DRIVER = "webdriver.chrome.driver";
    public static final String CHROME_DRIVER = "chromedriver";
    private static WebDriver chromeDriver;

    public enum OSType {
        WINDOWS, MAC_OS, LINUX, OTHER
    }

    public static WebDriver getWebDriverInstance() {
        if (chromeDriver == null) {
            System.out.println("Setting up new driver !!!");
            setupDriver();
        }
        return chromeDriver;
    }

    public static void teardown() {
        chromeDriver.quit();
    }

    private static void setupDriver() {
        TestBase.OSType ostype = getOperatingSystem();
        if (ostype == OSType.WINDOWS) {
            System.out.println("Using windows driver");
            System.setProperty(DRIVER, "chromedriver_win32" + File.separator + "chromedriver.exe");
        } else if (ostype == OSType.LINUX) {
            System.out.println("Using linux driver");
            System.setProperty(DRIVER, "chromedriver_linux_64" + File.separator + CHROME_DRIVER);
        } else if (ostype == OSType.MAC_OS) {
            System.out.println("Using mac driver");
            System.setProperty(DRIVER, "chromedriver_mac64" + File.separator + CHROME_DRIVER);
        } else {
            System.out.println("Unable to identify OS");
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
            System.out.println("Error occurred : "+ e);
            e.printStackTrace();
        }
    }

    private static OSType getOperatingSystem() {
        OSType detectedOS;
        String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
        if ((OS.indexOf("mac") >= 0) || (OS.indexOf("darwin") >= 0)) {
            detectedOS = OSType.MAC_OS;
        } else if (OS.indexOf("win") >= 0) {
            detectedOS = OSType.WINDOWS;
        } else if (OS.indexOf("nux") >= 0) {
            detectedOS = OSType.LINUX;
        } else {
            detectedOS = OSType.OTHER;
        }
        return detectedOS;
    }
}


