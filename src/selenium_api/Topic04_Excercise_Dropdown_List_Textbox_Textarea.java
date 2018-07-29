package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.internal.FindsByXPath;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic04_Excercise_Dropdown_List_Textbox_Textarea {
	WebDriver driver;
	By JobRole01_el = By.xpath("//select[@id='job1']");

	By txt_name = By.xpath("//input[@name='name']");
	By txt_dob = By.xpath("//input[@id='dob']");
	By txt_add = By.xpath("//textarea[@name='addr']");
	By txt_city = By.xpath("//input[@name='city']");
	By txt_state = By.xpath("//input[@name='state']");
	By txt_pin = By.xpath("//input[@name='pinno']");
	By txt_phone = By.xpath("//input[@name='telephoneno']");
	By txt_mail = By.xpath("//input[@name='emailid']");
	By txt_password = By.xpath("//input[@name='password']");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();

	}

	@Test(enabled = false)
	public void TC01_DropdownList() {
		driver.get("http://daominhdam.890m.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		Select JobRole01 = new Select(driver.findElement(JobRole01_el));
		Assert.assertTrue(!JobRole01.isMultiple());

		JobRole01.selectByVisibleText("Automation Tester");
		String currentText = JobRole01.getFirstSelectedOption().getText();
		Assert.assertEquals(currentText, "Automation Tester");

		JobRole01.selectByValue("manual");
		Assert.assertEquals(JobRole01.getFirstSelectedOption().getText(), "Manual Tester");

		JobRole01.selectByIndex(3);
		Assert.assertEquals(JobRole01.getFirstSelectedOption().getText(), "Mobile Tester");

		Assert.assertEquals(JobRole01.getOptions().size(), 5);
	}

	@Test(enabled = false)
	public void TC02_CustomDropdownList() throws Exception {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		// Select number_drop = new
		// Select(driver.findElement(By.xpath("//select[@id='number']")));
		driver.findElement(By.xpath("//span[@id='number-button']")).click();
		Thread.sleep(5000);
		List<WebElement> numbers = ((FindsByXPath) driver).findElementsByXPath("//div[contains(@id,'ui-id')]");
		for (int i = 0; i < numbers.size(); i++) {
			String text = numbers.get(i).getText();
			switch (text) {
			case "19":
				numbers.get(i).click();
				break;

			default:
				break;
			}

		}
		String curr_number = driver
				.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text']")).getText();
		Assert.assertEquals(curr_number, "19");
	}

	@Test
	public void TC03_Textbox_Textarea() {
		driver.get("http://demo.guru99.com/v4");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		WebElement txt_uid = driver.findElement(By.xpath("//input[@name='uid']"));
		txt_uid.clear();
		txt_uid.sendKeys("mngr145652");

		WebElement txt_pass = driver.findElement(By.xpath("//input[@name='password']"));
		txt_pass.clear();
		txt_pass.sendKeys("Epepadu");

		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

		// driver.findElement(By.xpath("//marquee[contains(text(),'Welcome
		// To')]")).isDisplayed();
		Assert.assertTrue(driver.findElement(By.xpath("//marquee[contains(text(),'Welcome To')]")).isDisplayed());

		driver.findElement(By.xpath("//a[contains(text(),'New Customer')]")).click();

		// Data
		String name = "Thuy Nguyen";
		String Dob = "08/05/1994";
		String Add = "123 ABD";
		String city = "Ninh Binh";
		String State = "Yen Khanh";
		String Pin = "123456";
		String Phone = "0122444435";
		String mail = getEmail("thuyntt","gmail.com");
		String pass = "1234567";

		driver.findElement(txt_name).sendKeys(name);
		driver.findElement(By.xpath("//input[@value='f']")).click();
		driver.findElement(txt_dob).sendKeys(Dob);
		driver.findElement(txt_add).sendKeys(Add);
		driver.findElement(txt_city).sendKeys(city);
		driver.findElement(txt_state).sendKeys(State);
		driver.findElement(txt_pin).sendKeys(Pin);
		driver.findElement(txt_phone).sendKeys(Phone);
		driver.findElement(txt_mail).sendKeys(mail);
		driver.findElement(txt_password).sendKeys(pass);

		driver.findElement(By.xpath("//input[@name='sub']")).click();
		
		String cusID = driver.findElement(By.xpath("//td[text()='Customer ID']//following-sibling::td")).getText();
		driver.findElement(By.xpath("//a[contains(.,'Edit Customer')]")).click();
		
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(cusID);
		driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();
		
//		mngr145658 nedejar 30294
		
//		Assert.assertEquals(driver.findElement(txt_name).getText(),name);
		Assert.assertEquals(driver.findElement(txt_add).getText(),Add);
		
		String new_city = "Edited City";
		String new_addr= "Edited Addr";
	
		driver.findElement(txt_add).clear();
		driver.findElement(txt_add).sendKeys(new_addr);
		
		driver.findElement(txt_city).clear();
		driver.findElement(txt_city).sendKeys(new_city);
		
		driver.findElement(By.xpath("//input[@name='sub']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']//following-sibling::td")).getText(), new_city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']//following-sibling::td")).getText(), new_addr);
		
	}
    public String getEmail(String suffix,String prefix){
        int randomNo = (int)(Math.random() * 10000);
        return suffix + randomNo + "@" + prefix; 
    }

	@AfterClass
	public void afterClass() {
		 driver.quit();
	}

}
