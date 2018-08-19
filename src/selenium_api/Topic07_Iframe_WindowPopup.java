package selenium_api;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic07_Iframe_WindowPopup {
	WebDriver driver;
	WebDriverWait wait;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test(enabled = false)
	public void TC_01() {
		driver.get("http://www.hdfcbank.com/");
		// Switch to Iframe
		List<WebElement> iframe = driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
		if (iframe.size() > 0) {
			if (iframe.get(0).isDisplayed()) {
				driver.switchTo().frame(iframe.get(0));
				driver.findElement(By.xpath("//div[@id='div-close']")).click();
				System.out.println("Closed popup!");
				driver.switchTo().defaultContent();
			}
		}
		// Step 03- verify

		WebElement verifyFrame = driver.findElement(By.xpath("//div[@class='flipBannerWrap']//iframe"));
		driver.switchTo().frame(verifyFrame);

		String text = driver.findElement(By.xpath("//span[@id='messageText']")).getText();
		Assert.assertEquals(text, "What are you looking for?");

		// step 04 //div[@class='bannerimage-container']//img
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='slidingbanners']//iframe")));

		List<WebElement> img = driver.findElements(By.xpath("//div[@class='bannerimage-container']//img"));
		int nb_img = img.size();
		// check img = 6
		Assert.assertEquals(nb_img, 6);
		// check img presence
		wait.until(ExpectedConditions
				.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='bannerimage-container']//img")));
		// switch to default content
		driver.switchTo().defaultContent();

		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='flipBanner']")).isDisplayed(), true);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='flipBanner']")).isDisplayed());

		List<WebElement> flipBannerImage = driver
				.findElements(By.xpath("//div[@class='flipBanner']//img[@class='front icon']"));
		int flipBannerNumber = flipBannerImage.size();
		Assert.assertEquals(flipBannerNumber, 8);
		int i = 0;

		for (WebElement imge : flipBannerImage) {
			Assert.assertTrue(imge.isDisplayed());
			i++;
			System.out.println("Image [" + i + "] displayed");
		}

	}

	@Test(enabled=false)
	public void TC_02() {
		driver.get("http://daominhdam.890m.com/");
		driver.findElement(By.xpath("//a[text()='Click Here']")).click();
		String parentWindow = driver.getWindowHandle();
		switchWindowByGUID(parentWindow);
		String title = driver.getTitle();
		Assert.assertEquals(title, "Google");
		closeAllWithoutParentWindows(parentWindow);

	}
	@Test
	public void TC_03() {
		driver.get("http://www.hdfcbank.com/");
		String parentWindow = driver.getWindowHandle();
		List<WebElement> iframe = driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
		if (iframe.size() > 0) {
			if (iframe.get(0).isDisplayed()) {
				driver.switchTo().frame(iframe.get(0));
				driver.findElement(By.xpath("//div[@id='div-close']")).click();
				System.out.println("Closed popup!");
				driver.switchTo().defaultContent();
			}
		}
		WebElement Agri = driver.findElement(By.xpath("//a[text()='Agri']"));
		Agri.click();
		switchWindowByGUID(parentWindow);
		driver.findElement(By.xpath("//p[contains(.,'Account Details')]")).click();
		switchWindowByTitle("Welcome to HDFC Bank NetBanking");
		WebElement frameFooter = driver.findElement(By.xpath("//frame[@name='footer']"));
		driver.switchTo().frame(frameFooter);
		driver.findElement(By.xpath("//a[text()='Privacy Policy']")).click();
		switchWindowByTitle("HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");
		driver.findElement(By.xpath("//a[@title='Corporate Social Responsibility']")).click();
		driver.switchTo().window(parentWindow);
		closeAllWithoutParentWindows(parentWindow);
	}
	public void switchWindowByGUID(String parent) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parent)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	// switch window
	public void switchWindowByTitle(String title) {
		Set<String> allWindow = driver.getWindowHandles();
		for (String runWindow : allWindow) {
			driver.switchTo().window(runWindow);
			String currentWin = driver.getTitle();
			if (currentWin.equals(title)) {
				break;
			}
		}
	}

	public boolean closeAllWithoutParentWindows(String parenWindow) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parenWindow)) {
				driver.switchTo().window(runWindow);
				driver.close();
			}
		}
		driver.switchTo().window(parenWindow);
		if (driver.getWindowHandles().size() == 1)
			return true;
		else
			return false;

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
