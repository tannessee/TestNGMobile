package GuruTelecom;

import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.aspectj.lang.annotation.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

public class MobileGuruTelecom {
	static AndroidDriver driver;
	static String customerId;
	
	 public void clickHome() {
	    	WebElement homeBtn= driver.findElement(By.xpath("//*[@href='index.html' and @class='button'][1]"));
		    homeBtn.click();     	
	    }
	    
	    public void clickSubmit() {
	    	WebElement submitBtn=driver.findElement(By.name("submit"));
	    	submitBtn.sendKeys(Keys.ENTER);       	
	    }

	@Test
	public void addCustomer() {

		JavascriptExecutor js = (JavascriptExecutor) driver;
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 

		driver.findElement(By.linkText("Add Customer")).click();
		js.executeScript("document.getElementById('done').click();");
		driver.findElement(By.id("fname")).sendKeys("Test");
		driver.findElement(By.id("lname")).sendKeys("Test");
		driver.findElement(By.id("email")).sendKeys("test@mail.ru");
		driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("Hello world");
		driver.findElement(By.id("telephoneno")).sendKeys("123234445");
	    clickSubmit();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		customerId = driver.findElement(By.xpath("//table/tbody/tr/td[2]/h3")).getText();
		//System.out.println("id is --> " + customerId);
	    clickHome();
	}

	@Test 
	public void addTariffPlan() {
		driver.findElement(By.xpath("//*[text()='Add Tariff Plan']")).click();
		driver.findElement(By.id("rental1")).sendKeys("10");
		driver.findElement(By.id("local_minutes")).sendKeys("300");
		driver.findElement(By.id("inter_minutes")).sendKeys("50");
		driver.findElement(By.id("sms_pack")).sendKeys("90");
		driver.findElement(By.id("minutes_charges")).sendKeys("5");
		driver.findElement(By.id("inter_charges")).sendKeys("10");
		driver.findElement(By.id("sms_charges")).sendKeys("2");
	    clickSubmit();
	    clickHome();
	}

	@Test (dependsOnMethods = { "addCustomer" })
	public void addTariffToCustomer() {
		driver.findElement(By.xpath("//*[text()='Add Tariff Plan to Customer'][1]")).click();
		driver.findElement(By.id("customer_id")).sendKeys(customerId);
	    clickSubmit();
		// accept tariff
	    clickSubmit();
	    clickHome();
	}

	@Test  (dependsOnMethods = { "addTariffToCustomer" })
	public void payBilling() {
		driver.findElement(By.xpath("//*[text()='Pay Billing'][1]")).click();
		driver.findElement(By.id("customer_id")).sendKeys(customerId);
		driver.findElement(By.name("submit")).sendKeys(Keys.ENTER);

	}

	@BeforeTest
	public void beforeTest() {

	}
		
	@AfterTest
	public AndroidDriver afterTests() {
		return driver;
	}

	@BeforeSuite
	public void setUp() throws MalformedURLException {
		DesiredCapabilities cap = new DesiredCapabilities();

		cap.setCapability("deviceName", "Pixel 2 API 29");
		cap.setCapability("platformName", "Android");
		cap.setCapability("udid", "emulator-5554");
		cap.setCapability("platformVersion", "10.0(Q)");
		cap.setCapability("automationName", "UiAutomator1");

		// driver for mobile
		cap.setCapability("browserName", "Chrome");
		cap.setCapability("chromedriverExecutable", "C:\\android\\chromedriver.exe");

		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		driver = new AndroidDriver(url, cap);
		driver.get("http://demo.guru99.com/");
		driver.findElement(By.linkText("Telecom Project")).click();

	}
}
