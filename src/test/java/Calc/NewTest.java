package Calc;
import static org.junit.Assert.*;

import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;
import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;

public class NewTest {
	static AndroidDriver driver;

	@Test(priority = 1)
	public void f1() {
		// test case 2-2*5=
		WebElement two = driver.findElementById("com.google.android.calculator:id/digit_2");
		two.click();
		WebElement minus = driver.findElementByAccessibilityId("minus");
		minus.click();
		two.click();
		WebElement mult = driver.findElementByAccessibilityId("multiply");
		mult.click();
		WebElement five = driver.findElementById("com.google.android.calculator:id/digit_5");
		five.click();
		
		WebElement equal = driver.findElementByAccessibilityId("equals");
		equal.click();

		WebElement result = driver.findElementById("com.google.android.calculator:id/result_final");
		String actualresult = result.getText();
		System.out.println("Result is: "+actualresult);

		String expectedresult = "0";
		assertEquals(actualresult, expectedresult);
		
//		if (actualresult.contentEquals(expectedresult)) {
//			System.out.println("Test passed.");
//		} else {
//			System.out.println("Test failed.");
//		}

	}

	@Test(priority = 1)
	public void f2() {
		 
		  //Test case 2+5=7
		  WebElement two=driver.findElementById("com.google.android.calculator:id/digit_2");
			 two.click();
			 WebElement add=driver.findElementByAccessibilityId("plus");
			 add.click();
			 WebElement five=driver.findElementById("com.google.android.calculator:id/digit_5");
			 five.click();
			 WebElement equal=driver.findElementByAccessibilityId("equals");
			 equal.click();
			 
			 WebElement result=driver.findElementById("com.google.android.calculator:id/result_final");
				String expectedresult = "7";

			 String actualresult=result.getText();
				assertEquals(actualresult, expectedresult);

			 
//			 if(actualresult.contentEquals("7"))
//			 {
//				 System.out.println("Test Pass");
//			 }
//			 else
//			 {
//				 System.out.println("Test Fail");
//			 }
				

	}

	@BeforeTest
	public void beforeTest() throws MalformedURLException {
		DesiredCapabilities cap = new DesiredCapabilities();

		cap.setCapability("deviceName", "Pixel 2 API 29");
		cap.setCapability("platformName", "Android");
		cap.setCapability("udid", "emulator-5554");
		cap.setCapability("platformVersion", "10.0(Q)");

		cap.setCapability("automationName", "UiAutomator1");

		//launching Calculator
		cap.setCapability("appPackage", "com.google.android.calculator");		
		cap.setCapability("appActivity", "com.android.calculator2.Calculator");	


		URL url = new URL("http://0.0.0.0:4723/wd/hub");
		driver = new AndroidDriver(url, cap);
		System.out.println("App launched...");

	}

	@AfterTest
	public AndroidDriver afterTest() {

		return driver;
	}

}
