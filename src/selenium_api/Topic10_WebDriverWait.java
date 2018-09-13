package selenium_api;

import org.testng.annotations.Test;

import com.google.common.base.Function;

import org.testng.annotations.BeforeClass;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic10_WebDriverWait {
	WebDriverWait wait;
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 30);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}

	@Test(enabled = false)
	public void TC_01() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		// wait 2s de click vao start button
		WebElement start_btn = driver.findElement(By.xpath("//button[contains(.,'Start')]"));
		start_btn.click();
		WebElement helloWorld_txt = driver.findElement(By.xpath("//h4[contains(.,'Hello World!')]"));

		Assert.assertEquals("Hello World!", helloWorld_txt.getText());
	}

	@Test(enabled = false)
	public void TC_02() {
		// Step 01 - Truy cập vào trang:
		driver.get("http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

		WebElement datetime_pk = driver.findElement(By.xpath("//div[@class='calendarContainer']"));

		// step 02 wait datetime picker duoc visible: bat buoc phai display
		wait.until(ExpectedConditions.visibilityOf(datetime_pk));
		// present: k quan tam element co display hay k, nhung phai co trong DOM
		// wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='calendarContainer']")));

		// Step 03 - In ra ngày đã chọn (Before AJAX call)
		WebElement dateSelected = driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceholder1_ctl00_ContentPlaceholder1_Label1Panel']"));
		printSelected(dateSelected);

		// chon ngay
		driver.findElement(By.xpath("//a[text()='9']/parent::td")).click();
		// wait ajax khong con visible
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv']")));
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].style.border='2px groove green'", driver.findElement(By.xpath("//div[@class='raDiv']")));
//		System.out.println("Da invisibled");

		// wait cho selected 9 duoc visible
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='9']/parent::td[@class='rcSelected']")));
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='9']/parent::td[@class='rcSelected']")).isDisplayed());

		// verify ngay da chon
		WebElement dateSelected_after = driver.findElement(By.xpath("//*[@id='ctl00_ContentPlaceholder1_ctl00_ContentPlaceholder1_Label1Panel']"));
		printSelected(dateSelected_after);
		Assert.assertEquals(dateSelected_after.getText(), "Sunday, September 09, 2018");


	}

	@Test(enabled = true)
	public void TC_03() {
		driver.get("https://daominhdam.github.io/fluent-wait/");
		WebElement countdount = driver.findElement(By.xpath("//*[@id='javascript_countdown_time']"));
		wait.until(ExpectedConditions.visibilityOf(countdount));
//		Khoi tao fluent wait
		new FluentWait<WebElement>(countdount)
//		tong wait la 15s
		.withTimeout(15, TimeUnit.SECONDS)
//		tan so moi 1s check 1 lan
		.pollingEvery(1, TimeUnit.SECONDS)
//		neu gap exception la find k thay element se bo qua
		.ignoring(NoSuchElementException.class)
//		Kiem tra dieu kien
		.until(new Function<WebElement, Boolean>(){
			public Boolean apply(WebElement element) {
//				kiem tra dieu kien countdount =00
				boolean flag=element.getText().endsWith("00");
				System.out.println("Time="+ element.getText());
//				return gia tri cho function apply
				return flag;
			}
		});

	}

	public void printSelected(WebElement element) {
		System.out.println(element.getText());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
