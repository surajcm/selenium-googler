package com.google.search;

import com.google.util.DriverConfigurations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Searcher {

    public void doGoogleSearch() {
        DriverConfigurations driverConfigurations = new DriverConfigurations();
        WebDriver webDriver = driverConfigurations.getWebDriverInstance();
        webDriver.get("https://www.google.com/");
        webDriver.manage().window().maximize();
        webDriver.findElement(By.name("q")).clear();
        webDriver.findElement(By.name("q")).sendKeys("Suraj Muraleedharan");
        //getWebDriverInstance().findElement(By.xpath("//input[@name='btnK']")).click();
        webDriver.findElement(By.name("btnK")).submit();
        //wait until page is loaded
        WebDriverWait wait = new WebDriverWait(webDriver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[contains(@href,'surajcm.github.io')]")));
        webDriver.findElement(By.xpath("//a[contains(@href,'surajcm.github.io')]"))
                .click();
        WebDriverWait wait2 = new WebDriverWait(webDriver, 20);
        wait2.until(Object::toString);
        driverConfigurations.tearDown();
    }
}
