package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic03_Exercise_WebElement_Browser {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
//		driver = new FirefoxDriver();
//		 System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
//		 driver = new ChromeDriver();

		 System.setProperty("webdriver.ie.driver", ".\\driver\\IEDriverServer.exe");
		 driver = new InternetExplorerDriver();
		
		driver.get("http://daominhdam.890m.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC01_CheckDisplayElement() {
		WebElement txt_Mail = driver.findElement(By.id("mail"));
		WebElement radio_AgeUnder18 = driver.findElement(By.id("under_18"));
		WebElement textarea_Education = driver.findElement(By.id("edu"));

		Assert.assertTrue(txt_Mail.isDisplayed());
		Assert.assertTrue(radio_AgeUnder18.isDisplayed());
		Assert.assertTrue(textarea_Education.isDisplayed());
		
		if(isDisplayed(txt_Mail)&&isDisplayed(radio_AgeUnder18)&&isDisplayed(textarea_Education)) {
			txt_Mail.sendKeys("Autotest123@gmail.com");
			radio_AgeUnder18.click();
			textarea_Education.sendKeys("test");
		}
		
		
	}
	
	@Test
	private void TC02_CheckEnableElement() {
		WebElement txt_Mail  = driver.findElement(By.id("mail"));
		WebElement radio_AgeUnder18 = driver.findElement(By.id("under_18"));
		WebElement textarea_Education = driver.findElement(By.id("edu"));
		WebElement txt_Role1  = driver.findElement(By.id("job1"));
		WebElement checkbox_Interests = driver.findElement(By.id("development"));
		WebElement slider01 = driver.findElement(By.id("slider-1"));
		WebElement btn_Enable = driver.findElement(By.id("button-enabled"));
		WebElement pass  = driver.findElement(By.id("password"));
		WebElement radio_AgeDisable = driver.findElement(By.id("radio-disabled"));
		WebElement Biography = driver.findElement(By.id("bio"));
		WebElement txt_Role2  = driver.findElement(By.id("job2"));
		WebElement checkbox_Disable = driver.findElement(By.id("check-disbaled"));
		WebElement slider02 = driver.findElement(By.id("slider-2"));
		WebElement btn_Disable = driver.findElement(By.id("button-disabled"));


		 WebElement e[] = new WebElement[14] ;
		 e[0]=txt_Mail;
		 e[1]=radio_AgeUnder18;
		 e[2]=textarea_Education;
		 e[3]=txt_Role1;
		 e[4]=checkbox_Interests;
		 e[5]=slider01;
		 e[6]=btn_Enable;
		 e[7]=pass;
		 e[8]=radio_AgeDisable;
		 e[9]=Biography;
		 e[10]=txt_Role2;
		 e[11]=checkbox_Disable;
		 e[12]=slider02;
		 e[13]=btn_Disable;
		 
		 for (int i = 0; i < e.length; i++) {
			if(e[i].isEnabled()==true) {
				System.out.println(e[i]+": Element is enable");
			}
			else 
			{
				System.out.println(e[i]+": Element is disable");
			}
		}

	}
	
	@Test
	private void TC03_CheckSelectElement() {
		// TODO Auto-generated method stub
		WebElement radio_AgeUnder18 = driver.findElement(By.id("under_18"));
		WebElement checkbox_Interests = driver.findElement(By.id("development"));
		
		radio_AgeUnder18.click();
		checkbox_Interests.click();
		if(radio_AgeUnder18.isSelected()==true&&checkbox_Interests.isSelected()==true) {
			System.out.println("Elements are selected");
		}
		else if (radio_AgeUnder18.isSelected()!=true&&checkbox_Interests.isSelected()!=true) {
			System.out.println("Elements are not selected");
			radio_AgeUnder18.click();
			checkbox_Interests.click();
		} else {
			if(radio_AgeUnder18.isSelected()!=true) {
				System.out.println("Age (Under 18) is not selected");
				radio_AgeUnder18.click();
			} else {
				System.out.println("Interests (Development) is not selected");
				checkbox_Interests.click();
			}
			
		}

	}
	
	public boolean isDisplayed(WebElement element) {
		return element.isDisplayed();	
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
