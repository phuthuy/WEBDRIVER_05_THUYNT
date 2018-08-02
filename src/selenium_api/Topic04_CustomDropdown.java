package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic04_CustomDropdown {
	WebDriver driver;
	WebDriverWait wait;

	@BeforeClass
	public void beforeClass() {
//		driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver,30);
		
	}

	@Test
	public void TC01_CustomDropdown() throws Exception {
////		Angular
//		driver.get("https://material.angular.io/components/select/examples");
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		
//		selectCustomDropdownList("//mat-select[@placeholder='Favorite food']","//mat-option/span","Tacos");
//		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='mat-select-value']//span[text()='Tacos']")).isDisplayed());
//		
////		Kendo
//		driver.get("https://demos.telerik.com/kendo-ui/dropdownlist/index");
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		
//		selectCustomDropdownList("//span[@aria-labelledby='color_label']","//ul[@id='color_listbox']/li","Grey");
//		Assert.assertTrue(driver.findElement(By.xpath("//span[@aria-labelledby='color_label']//span[@class='k-input' and text()='Grey']/parent::span")).isDisplayed());
//		
////		VueJS
//		driver.get("https://mikerodham.github.io/vue-dropdowns/");
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		
//		selectCustomDropdownList("//div[@class='btn-group']/li","//div[@class='btn-group']/ul/li","Third Option");
//		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'Third Option')]")).isDisplayed());

		//		EditableDropdown
		driver.get("http://indrimuska.github.io/jquery-editable-select/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		selectCustomDropdownList("//div[@id='default-place']/input","//div[@id='default-place']/ul/li","Audi");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='es-visible' and text()='Audi']")).isDisplayed());
		
		
		
	}
    public void selectCustomDropdownList(String dropdown, String listIem, String valueItem) throws Exception {
    	// click vao dropdown
    	WebElement dropdownElement = driver.findElement(By.xpath(dropdown));
    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",dropdownElement);
    	Thread.sleep(3000);
    	driver.findElement(By.xpath(dropdown)).click();
    	//get tat ca item trong dropdown list vao 1 list element 
    	List<WebElement> allItems = driver.findElements(By.xpath(listIem));
//    	wait de tat ca cac item trong list duoc hien thi
    	wait.until(ExpectedConditions.visibilityOfAllElements(allItems));
    	// dung for de duyet qua tung phan tu
    	for (WebElement item : allItems) {
			if(item.getText().equals(valueItem)){
				//scroll to item
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", item);
				Thread.sleep(3000);
				item.click();
				break;
				}
		}
    	
    }
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
