package session6Practice;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Session6Practice {

	WebDriver driver;

	@BeforeMethod
	public void init() {

		System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.yahoo.com/");
	}

	@Test
	public void Session6Tests() throws InterruptedException {

//		Syntax:
//		
//
//			Set <String> handles = driver.getWindowHandles() 
//	 - It returns a set of handles of the all the pages available
//
//	Advance For Loop/For Each Loop:
//			for(String i : driver.getWindowHandles()) {
//				driver.switchTo().window(i);
//			}

		WebElement yahooSearchBox = driver.findElement(By.xpath("//input[@class='_yb_1rhu6']"));
		yahooSearchBox.sendKeys("xpath");

		WebElement searchButoonElement = driver.findElement(By.xpath("//input[@id='ybar-search']"));
		searchButoonElement.click();

		WebElement xpath = driver.findElement(By.xpath("//a[contains(text(),'XPath Tutorial - W3Schools')]"));
		xpath.click();

		Set<String> handles = driver.getWindowHandles();
		System.out.println(handles);
		for (String i : handles) {
			System.out.println(i);
			driver.switchTo().window(i);
		}
		
		WebElement Nodes = driver.findElement(By.xpath("//*[@id=\"leftmenuinnerinner\"]/a[50]"));
		Nodes.click();
		
		WebElement NodesAssertionIdentifier = driver.findElement(By.xpath("//*[@id=\"main\"]/h1"));
		Assert.assertEquals(NodesAssertionIdentifier.getText(), "XPath Nodes", "Nodes Header Not Found!");
		
		system.out.println("I declare myself as the java king");
	}

	// @AfterMethod
	public void teardown() {

		driver.close();
		driver.quit();

	}
}
