package com.ecommerce.stepdefinition;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Flipkart {
	
	
	@DataProvider(name="mobileName")
	public Object[][] mobileName() {
		
		return new Object[][] {{"iphone"}};
		
	}
	
	@DataProvider(name="username")
	public Object[][] username() {
		
		return new Object[][] {{"karthi","rajan"}};
		
	}
	
	static WebDriver driver;
	static long startTime;
	
	@BeforeClass(groups="default")
    public static void launch() {
		
		WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
	    driver.get("https://www.flipkart.com/");
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
	
	
	
	@Test(priority = -1,groups="searchFunctionality",dataProvider="username")
	public void login(String user, String pass) {
		
		System.out.println(user);
		System.out.println(pass);
		
		try{
			WebElement close = driver.findElement(By.xpath("//button[text()='✕']"));
			close.isDisplayed();
			close.click();
		}catch (Exception e) {
			System.out.println("close icon is not displayed");
		}
		

	}
	
	@Test(priority = 1,groups="searchFunctionality",dataProvider="mobileName")
	public void Search(String name) {
		
		WebElement search = driver.findElement(By.name("q"));
		search.sendKeys(name,Keys.ENTER);

	}
	
	static String name;
	
	
	@Test(priority = 2)
	public void choose() {
		
		WebElement mobile = driver.findElement(By.xpath("(//div[contains(text(),'realme')])[2]"));
		String text = mobile.getText();

		name = text;
		System.out.println(name);
		
		mobile.click();
		
	}
	
	@Test(priority = 3,groups="sanity")
	public void window() {
		
		String par = driver.getWindowHandle();
		Set<String> child = driver.getWindowHandles();
		
		for(String x : child) {
			if(!par.equals(x)) {
				System.out.println("tab switched");
				driver.switchTo().window(x);
			}
		}

	}
	
	@Test(priority = 4,enabled = true,groups="sanity")
	public void validation() {
		
		WebElement newMobile = driver.findElement(By.xpath("//span[contains(text(),'realme')]"));
		String last = newMobile.getText();
		System.out.println(last);
	//	Assert.assertEquals(name, last);
		
		SoftAssert s = new SoftAssert();
		
		s.assertEquals(name, last);
		
		System.out.println("matched");

	}
	
	@Test(priority = 5,invocationCount = 3,groups="sanity")
	public void screenshot() throws IOException, InterruptedException {
		
		Thread.sleep(1000);
		
		SimpleDateFormat sec = new SimpleDateFormat("ss");
		String date = sec.format(new Date(0));
		
		TakesScreenshot ts= (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File des = new File(".//target//report"+date+".png");
		
		FileUtils.copyFile(src, des);

	}
	
	
	
}
