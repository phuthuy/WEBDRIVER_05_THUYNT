package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic02_Exercise_WebDriver {
	WebDriver driver;
	By myAccountLink = By.xpath("//*[@class='footer']//*[@title='My Account']");
	By login_txtEmail= By.id("email");
	By login_txtPass = By.id("pass");
	By login_btnSend = By.id("send2");
//	By sigin_txtemail = By.id("email_address");
//	By sigin_txtFirstname = By.id("firstname");
//	By sigin_txtMiddlename = By.id("middlename");
//	By sigin_txtLastname = By.id("lastname");
//	By sigin_txtPass = By.id("password");
//	By sigin_txtConfirmPass = By.id("confirmation");
	

	
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();

		// System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		// driver = new ChromeDriver();

		// System.setProperty("webdriver.ie.driver", ".\\driver\\IEDriverServer.exe");
		// driver = new InternetExplorerDriver();

		driver.get("http://live.guru99.com");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public void gotoLogin() {
		driver.findElement(myAccountLink).click();
	}

	@Test
	public void TestScrip01_VerifyURLandTitle() {
		// check title
		String homePageTitle = driver.getTitle();
		Assert.assertEquals(homePageTitle, "Home page");

		// click My Account
		gotoLogin();
		// click Create an account
		driver.findElement(By.xpath("//*[@class='button']//*[contains(text(),'Create')]")).click();
		// back man hinh
		driver.navigate().back();
		// check URL login
		String loginUrl = driver.getCurrentUrl();
		Assert.assertEquals(loginUrl, "http://live.guru99.com/index.php/customer/account/login/");
		// check URL sign up
		driver.navigate().forward();
		String signupUrl = driver.getCurrentUrl();
		Assert.assertEquals(signupUrl, "http://live.guru99.com/index.php/customer/account/create/");

	}

	@Test
	public void TestScript02_LoginEmpty() {
		gotoLogin();
		driver.findElement(login_btnSend).click();
		String requireMail = driver.findElement(By.id("advice-required-entry-email")).getText();
		Assert.assertEquals(requireMail, "This is a required field.");

		String requirePass = driver.findElement(By.id("advice-required-entry-pass")).getText();
		Assert.assertEquals(requirePass, "This is a required field.");
	}

	@Test
	public void TestScript03_invalidEmail() {
		gotoLogin();
		driver.findElement(login_txtEmail).sendKeys("123434234@12312.123123");
		driver.findElement(login_btnSend).click();
		String invalidMail = driver.findElement(By.id("advice-validate-email-email")).getText();
		Assert.assertEquals(invalidMail, "Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test
	public void TestScript04_incorrectPass() {
		gotoLogin();
		driver.findElement(login_txtEmail).sendKeys("automation@gmail.com");
		driver.findElement(login_txtPass).sendKeys("123");
		driver.findElement(login_btnSend).click();
		String incorrectPass = driver.findElement(By.id("advice-validate-password-pass")).getText();
		Assert.assertEquals(incorrectPass, "Please enter 6 or more characters without leading or trailing spaces.");
	}

	@Test
	public void TestScript05_creatAccount() {
		gotoLogin();
		
		//Nhap thong tin
		String email = getEmail("testmail","gmail.com");
		driver.findElement(By.xpath("//*[@class='button']//*[contains(text(),'Create')]")).click();
		driver.findElement(By.id("firstname")).sendKeys("Selenium");
		driver.findElement(By.id("middlename")).sendKeys("auto");
		driver.findElement(By.id("lastname")).sendKeys("test");
		driver.findElement(By.id("email_address")).sendKeys(email);
		driver.findElement(By.id("password")).sendKeys("auto1234");
		driver.findElement(By.id("confirmation")).sendKeys("auto1234");
		//Click Register
		driver.findElement(By.xpath("//*[@id='form-validate']//*[@title='Register']")).click();
		//Verify message
		String thank = driver.findElement(By.xpath("//*[contains(text(),'Thank you for registering')]")).getText();
		Assert.assertEquals(thank, "Thank you for registering with Main Website Store.");
		//logout
		driver.findElement(By.xpath("//*[@id='header']//*[text()='Account']")).click();
		driver.findElement(By.xpath("//*[@title='Log Out']")).click();
		//Check navigate ve Home page
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String homepage = driver.getCurrentUrl();
		Assert.assertEquals(homepage,"http://live.guru99.com/index.php/");
		
	}

    public String getEmail(String suffix,String prefix){
        int randomNo = (int)(Math.random() * 1000);
        return suffix + randomNo + "@" + prefix; 
    }

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
