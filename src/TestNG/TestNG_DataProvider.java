package TestNG;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import org.testng.annotations.AfterMethod;

public class TestNG_DataProvider {
	WebDriver driver;
	@BeforeMethod
	public void beforeClass() {
		driver= new FirefoxDriver();
		driver.get("http://live.guru99.com/index.php/customer/account/login/");
	}

	@Test(dataProvider = "LoginToSystem")
	public void TC_LoginToSystem(String username, String password) {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(password);
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//h1[contains(.,'My Dashboard')]")).isDisplayed());
		
	}

	@DataProvider
	public Object[][] LoginToSystem() {
		return new Object[][] { 
			    new Object[] { "automationvalid_01@gmail.com", "111111" },
				new Object[] { "automationvalid_02@gmail.com", "111111" },
				new Object[] { "automationvalid_03@gmail.com", "111111" }, };
	}

	@AfterMethod
	public void afterClass() {
		driver.quit();
	}

}
