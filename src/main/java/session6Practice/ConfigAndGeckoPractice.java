package session6Practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ConfigAndGeckoPractice {

	WebDriver driver;
	String browser = null;
	String url = null;

	@BeforeClass
	public void reacConfig() {

		Properties prop = new Properties();
		try {
			InputStream input = new FileInputStream("src\\main\\java\\config\\config.properties");
			prop.load(input);
			browser = prop.getProperty("browser");
			System.out.println("browser used " + browser);
			url = prop.getProperty("url");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@BeforeMethod
	public void init() {

		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "driver\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(url);
	}

	@Test
	public void testrandoninputs() {

		WebElement userNameElement = driver.findElement(By.xpath("//input[@id='username']"));
		WebElement passwordElement = driver.findElement(By.xpath("//*[@id='password']"));
		WebElement signInButtonElement = driver.findElement(By.xpath("//*[@class='btn btn-success block full-width']"));

		userNameElement.clear();
		userNameElement.sendKeys("demo@techfios.com");
		passwordElement.sendKeys("abc123");
		signInButtonElement.click();

		WebElement dashboardHeaderElement = driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div/h2"));
		Assert.assertEquals(dashboardHeaderElement.getText(), "Dashboard", "Dashboard page not found!");

		driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[3]/a/span[1]")).click();
		driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[3]/ul/li[1]/a")).click();

		WebElement contactsHeaderElement = driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div/h2"));
		Assert.assertEquals(contactsHeaderElement.getText(), "Contacts", "Contact page not found!");

		Random rnd = new Random();
		int contactPageRndInput = rnd.nextInt(999);
		
		byte[] array = new byte[8]; 
	    new Random().nextBytes(array);
	    String generatedString = new String(array, Charset.forName("UTF-8"));
	    
		driver.findElement(By.xpath("//*[@id=\"account\"]")).sendKeys(generatedString + contactPageRndInput);
		driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("Javaking" + contactPageRndInput + "@gmail.com");
		driver.findElement(By.xpath("//*[@id=\"address\"]")).sendKeys(contactPageRndInput + generatedString);
		driver.findElement(By.xpath("//*[@id=\"submit\"]")).click();
		
		
		
	}

}
