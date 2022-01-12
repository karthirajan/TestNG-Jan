package com.ecommerce.stepdefinition;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon {

	static WebDriver driver;
	static long startTime;
	
	@BeforeClass(groups="default")
    public static void launch() {
		
		WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
	    driver.get("https://www.amazon.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.navigate().refresh();
		

	}
	
	@AfterClass(groups="default")
	public static void quit() {
		
		  driver.quit();
      
	}
	
	@BeforeMethod(groups="default")
	public void start() {
		
		startTime = System.currentTimeMillis();

	}
	
	@AfterMethod(groups="default")
	public void end() {
		
		long endTime = System.currentTimeMillis();
		System.out.println("That took " + (endTime - startTime) + " milliseconds");

	}
	
	@Parameters({"username","password"})
	@Test(groups="searchFunctionality")
	public void searchMobile(String username, String password) {
		
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("mobiles",Keys.ENTER);
		
		System.out.println(username);
		System.out.println(password);

	}
	
}
