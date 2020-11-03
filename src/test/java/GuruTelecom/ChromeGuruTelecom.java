package GuruTelecom;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

public class ChromeGuruTelecom {
	
	/*1.Open http://demo.guru99.com/ and open Telecom Project.
2.Click on Add Customer and enter/select valid details.
3.Submit details and copy that Customer Id
4.Return Back to Home and and Click on Tariff Plan.
5.Enter/Select valid value in all fields and submit it.
6.Click on Home and select AddTariff Plan to Customer.
7.Enter Customer id and select Tariff plan.
8.Submit it and click on Home link.

9.Click on Pay Billing and Enter Customer Id*/
	
	static WebDriver driver;
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
	    driver.findElement(By.linkText("Add Customer")).click(); 	 
		js.executeScript("document.getElementById('done').click();"); 
	    driver.findElement(By.id("fname")).sendKeys("Test");
	    driver.findElement(By.id("lname")).sendKeys("Test");
	    driver.findElement(By.id("email")).sendKeys("test@mail.ru");	    
		driver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("Hello world"); 
	    driver.findElement(By.id("telephoneno")).sendKeys("123234445");
	    clickSubmit();
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 
	    customerId = driver.findElement(
	    		By.xpath("//table/tbody/tr/td[2]/h3")).getText(); 		
	            System.out.println("id is --> "+ customerId); 	    
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
		//accept tariff
	    clickSubmit();
	    clickHome();


	  
  }
  @Test (dependsOnMethods = { "addTariffToCustomer" })
  public void payBilling() {
		driver.findElement(By.xpath("//*[text()='Pay Billing'][1]")).click();
		driver.findElement(By.id("customer_id")).sendKeys(customerId);
	    clickSubmit();
  
  }
  
  @BeforeTest
  public void beforeTest() {
	  
  }


  @BeforeSuite
  public void setUp() {
	  
	  System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		driver = new ChromeDriver();
	 	driver.get("http://demo.guru99.com/");
	    driver.findElement(By.linkText("Telecom Project")).click();

	 	
	 	
  }

}
