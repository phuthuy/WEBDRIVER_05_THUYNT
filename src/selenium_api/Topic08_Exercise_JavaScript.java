package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic08_Exercise_JavaScript {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test(enabled = false)
	public void TC_01() {
		driver.get("http://live.guru99.com/");
		// get domain
		String domain = (String) executeJSForWebBrowser("return document.domain");
		Assert.assertEquals(domain, "live.guru99.com");
		// get URL
		String URL = (String) executeJSForWebBrowser("return document.URL");
		Assert.assertEquals(URL, "http://live.guru99.com/");
		// open mobile page
		executeJSForWebElement(driver.findElement(By.xpath("//a[contains(.,'Mobile')]")));
		WebElement addCart = driver.findElement(
				By.xpath("//h2[a[@title='Samsung Galaxy']]/following-sibling::div[@class='actions']/button"));
		executeJSForWebElement(addCart);
		// verify msg hien hthi - get inner text
		String sText = executeJSForWebBrowser("return document.documentElement.innerText;").toString();
		Assert.assertTrue(sText.contains("Samsung Galaxy was added to your shopping cart."));

		WebElement privacy = driver.findElement(By.xpath("//a[contains(.,'Privacy Policy')]"));
		executeJSForWebElement(privacy);

		String titlePrivacy = executeJSForWebBrowser("return document.title;").toString();
		Assert.assertEquals(titlePrivacy, "Privacy Policy");

		scrollToBottomPage();

		WebElement wishlist = driver.findElement(By.xpath("//th[text()='WISHLIST_CNT']/following-sibling::td"));
		Assert.assertTrue(wishlist.isDisplayed());

		executeJSForWebBrowser("window.location = 'http://demo.guru99.com/v4/'");
		// get domain
		String demo_domain = (String) executeJSForWebBrowser("return document.domain");
		Assert.assertEquals(demo_domain, "demo.guru99.com");

	}

	@Test
	public void TC_02() {
		driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_disabled");
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='iframeResult']")));
		WebElement lastname = driver.findElement(By.xpath("//input[@name='lname']"));
		removeAttributeInDOM(lastname, "disabled");
		lastname.sendKeys("Automation Testing");
		WebElement submit = driver.findElement(By.xpath("//input[@type='submit']"));
		submit.click();
		
		// verify msg hien hthi - get inner text
				String sText = executeJSForWebBrowser("return document.documentElement.innerText;").toString();
				Assert.assertTrue(sText.contains("Automation Testing"));
		
	
	}

	public void HightLightElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.boder='6px groove red'", element);
	}

	public Object executeJSForWebBrowser(String javascript) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript(javascript);
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
			return null;
		}
	}

	public Object executeJSForWebElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].click();", element);
	}

	public Object removeAttributeInDOM(WebElement element, String attribute) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public Object scrollToBottomPage() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
