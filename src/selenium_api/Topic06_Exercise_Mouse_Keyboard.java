package selenium_api;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic06_Exercise_Mouse_Keyboard {
	WebDriver driver;
	Actions action = new Actions(driver);

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test(enabled=false)
	public void TC_01_Case1() {
		driver.get("http://daominhdam.890m.com/");
		WebElement hoverText = driver.findElement(By.xpath("//a[contains(text(),'Hover over me')]"));
		action.moveToElement(hoverText).perform();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='tooltip-inner']")).isDisplayed());
	}

	@Test(enabled=false)
	public void TC_01_Case2() {
		driver.get("http://www.myntra.com/");
		WebElement account = driver.findElement(By.xpath("//span[@class='myntraweb-sprite desktop-iconUser sprites-user']"));
		action.moveToElement(account).perform();
		driver.findElement(By.xpath("//a[contains(text(),'login')]")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='login-box']")).isDisplayed());
	}
	@Test(enabled=false)
	public void TC_02() {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		List<WebElement> items = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee']"));
		action.clickAndHold(items.get(0)).moveToElement(items.get(3)).release().perform();
		List<WebElement> item_selected = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
		Assert.assertTrue(item_selected.size()==4);
	}
	@Test(enabled=false) 
	public void TC_03() {
		driver.get("http://www.seleniumlearn.com/double-click");
		WebElement db_click = driver.findElement(By.xpath("//button[contains(.,'Double-Click Me!')]"));
		action.doubleClick(db_click).perform();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "The Button was double-clicked.");
		alert.accept();
	}
	@Test(enabled=false) 
	public void TC_04() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		WebElement btn_right = driver.findElement(By.xpath("//span[contains(.,'right click me')]"));
		action.contextClick(btn_right).perform();
		action.moveToElement(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]"))).perform();
		WebElement btn_quit = driver.findElement(By.xpath("//li[contains(@class,'context-menu-visible') and contains(@class,'context-menu-hover')]/span[text()='Quit']"));
		Assert.assertTrue((btn_quit).isDisplayed());
		btn_quit.click();	
		Alert alert = driver.switchTo().alert();
		alert.accept();
		
	}
	@Test
	public void TC_05_case01() {
		driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular");
		WebElement source = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement target = driver.findElement(By.xpath("//div[@id='droptarget']"));
		action.dragAndDrop(source, target).build().perform();
		Assert.assertEquals(target.getText(), "You did great!");
	}
	@Test
	public void TC_05_case02() {
		driver.get("http://jqueryui.com/resources/demos/droppable/default.html");
		WebElement source = driver.findElement(By.xpath("//p[contains(.,'Drag me to my target')]"));
		WebElement target = driver.findElement(By.xpath("//p[contains(.,'Drop here')]"));
		action.dragAndDrop(source, target).build().perform();
		Assert.assertEquals(target.getText(), "Dropped!");
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
