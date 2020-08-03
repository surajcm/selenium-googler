package com.google.search;

import com.google.util.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleMyself extends TestBase {
	public static void main(String args[]) {
		getWebDriverInstance().get("https://www.google.com/");
		getWebDriverInstance().manage().window().maximize();
		getWebDriverInstance().findElement(By.name("q")).clear();
		getWebDriverInstance().findElement(By.name("q")).sendKeys("Suraj Muraleedharan");
		//getWebDriverInstance().findElement(By.xpath("//input[@name='btnK']")).click();
		getWebDriverInstance().findElement(By.name("btnK")).submit();
		//wait until page is loaded
		WebDriverWait wait = new WebDriverWait(getWebDriverInstance(), 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(@href,'surajcm.github.io')]")));
		getWebDriverInstance().findElement(By.xpath("//a[contains(@href,'surajcm.github.io')]")).click();
		//may be we can add a wait here
		teardown();
	}
}
