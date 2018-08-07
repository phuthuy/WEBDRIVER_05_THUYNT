package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic05_Excercise_Radio_RadioButton_Alert {
	WebDriver driver;
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test(enabled=false)
	public void TC_01() {
		driver.get("http://live.guru99.com/");
		
		driver.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/login/");
		
		clickElementByJavascript(driver.findElement(By.xpath("//a[@title='Create an Account']")));
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/create/");
	}
	@Test(enabled=false)
	public void TC_02() {
		driver.get(" http://demos.telerik.com/kendo-ui/styling/checkboxes");
		WebElement label_dual = driver.findElement(By.xpath("//label[contains(text(),'Dual-zone')]"));
		WebElement checkbox_dual = driver.findElement(By.xpath("//label[contains(text(),'Dual-zone')]/preceding-sibling::input"));
		label_dual.click();
		Assert.assertTrue(checkbox_dual.isSelected());
		label_dual.click();
		Assert.assertFalse(checkbox_dual.isSelected());
	}
	@Test(enabled=false)
	public void TC_03() {
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		WebElement radio_147kw= driver.findElement(By.xpath("//label[contains(text(),'2.0 Petrol, 147kW')]/preceding-sibling::input"));
		WebElement label_147kw = driver.findElement(By.xpath("//label[contains(text(),'2.0 Petrol, 147kW')]"));
		label_147kw.click();
		if(!radio_147kw.isSelected()) {
			label_147kw.click();
		}
	}
	@Test
	public void TC_04() {
		driver.get("http://daominhdam.890m.com/");
		WebElement btn_JS = driver.findElement(By.xpath("//button[text()='Click for JS Alert']"));
	
		btn_JS.click();
		Alert alert_JS = driver.switchTo().alert();
		Assert.assertEquals(alert_JS.getText(), "I am a JS Alert");
		alert_JS.accept();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(),"You clicked an alert successfully");
	}
	@Test
	public void TC_05() {
		driver.get("http://daominhdam.890m.com/");
		WebElement btn_Confirm = driver.findElement(By.xpath("//button[text()='Click for JS Confirm']"));
	
		btn_Confirm.click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(),"You clicked: Cancel");
	}
	@Test
	public void TC_06() {
		driver.get("http://daominhdam.890m.com/");
		WebElement btn_Confirm = driver.findElement(By.xpath("//button[text()='Click for JS Prompt']"));
	
		btn_Confirm.click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		alert.sendKeys("daominhdam");
		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(),"You entered: daominhdam");
	}
	
	public void clickElementByJavascript(WebElement element) {
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].click();", element);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
