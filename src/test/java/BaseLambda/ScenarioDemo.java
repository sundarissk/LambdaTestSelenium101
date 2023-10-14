package BaseLambda;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ScenarioDemo {
	public String username = "sundariksen";
	public String accesskey = "hC8ef7bSe7tUTJ32lZYF8Xdk1np7iHbaLOmZDFO4Hx14SnVwrK";
	public RemoteWebDriver driver = null;
	public String gridURL = "@hub.lambdatest.com/wd/hub";

	@BeforeMethod
	@Parameters(value = { "browser", "version", "platform" })
	public void SetUp(String browser, String version, String platform) {

		DesiredCapabilities browserOptions = new DesiredCapabilities();

		HashMap<String, Object> ltOptions = new HashMap<String, Object>();

		ltOptions.put("browserName", browser);
		ltOptions.put("version", version);
		ltOptions.put("platform", platform);

		ltOptions.put("build", "ParallelExceution");
		ltOptions.put("project", "SundariLamdaTestDemo");
		ltOptions.put("name", "ParallelLambdaExceution");

		ltOptions.put("visual", true);
		ltOptions.put("video", true);
		ltOptions.put("network", true);
		ltOptions.put("w3c", true);

		// ltOptions.put("plugin", "java-java");

		browserOptions.setCapability("LT:Options", ltOptions);

		try {
			driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), browserOptions);

		} catch (MalformedURLException e) {
			System.out.println("Invalid grid URL");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		driver.get("https://www.lambdatest.com/selenium-playground/");
	}

	@Test(priority = 1)
	public void SimpleFormDemo() {

		// Click Simple form Demo
		driver.findElement(By.xpath("//a[contains(text(),'Simple Form Demo')]")).click();

		// Simple form demo URL validation
		String runsimplurl = driver.getCurrentUrl();
		String demourl = "https://www.lambdatest.com/selenium-playground/simple-form-demo";
		Assert.assertEquals(runsimplurl, demourl);
		System.out.println("URL Matching");

		String m = "Welcome to LambdaTest";

		// “Enter Message” is sending
		driver.findElement(By.id("user-message")).sendKeys(m);

		// Click “Get Checked Value”.
		driver.findElement(By.id("showInput")).click();

		// “Get Checked Value” Validation .
		String v = driver.findElement(By.xpath("//p[@id='message']")).getText();
		Assert.assertEquals(v, m);
		System.out.println("String Matches");
		// System.out.println("Inside SimpleFormDemo Test : " +
		// Thread.currentThread().getId());
		System.out.println("End of Test 1: SimpleFormDemo");

	}

	@Test(priority = 2)
	public void DragDropSliders() {

		// Click Drag & Drop
		driver.findElement(By.xpath("//a[contains(text(),'Drag & Drop Sliders')]")).click();

		// Drag to 95
		WebElement val = driver.findElement(By.xpath("//input[@value='15']"));
		Actions action = new Actions(driver);

		// Reading value before dragging
		String ev = driver.findElement(By.xpath("//output[@id='rangeSuccess']")).getText();
		System.out.println(ev);

		/*
		 * dragging to 95 value action.dragAndDropBy(val, 95, 0).build().perform();
		 * 
		 * String ev2 =
		 * driver.findElement(By.xpath("//output[@id='rangeSuccess']")).getText();
		 * System.out.println(ev2);
		 * 
		 * String eev2 = "70"; System.out.println("Assertion Pass for 70 Value");
		 * Assert.assertEquals(ev2, eev2);
		 */

		// dragging to 95 value
		action.dragAndDropBy(val, 215, 0).build().perform();

		String ev1 = driver.findElement(By.xpath("//output[@id='rangeSuccess']")).getText();
		System.out.println(ev1);
		String eev = "95";

		// Asserting value 95
		Assert.assertEquals(ev1, eev);

		System.out.println("Assertion Pass for 95 Value");
		// System.out.println("Inside DragDropSliders Test : " +
		// Thread.currentThread().getId());
		System.out.println("End of Test 2 : DragDropSliders");

	}

	@Test(priority = 3)
	public void InputFormSubmit() {

		// Click Input Form Submit
		driver.findElement(By.xpath("//a[contains(text(),'Input Form Submit')]")).click();

		driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();

		// Entering fields values in form
		driver.findElement(By.id("name")).sendKeys("abcd");
		driver.findElement(By.id("inputEmail4")).sendKeys("abcd@gmail.com");
		driver.findElement(By.id("inputPassword4")).sendKeys("abcd12345");
		driver.findElement(By.id("company")).sendKeys("abcdcompany");
		driver.findElement(By.id("websitename")).sendKeys("abcdcompany.org");

		// Selecting country by test United states
		WebElement w = driver.findElement(By.name("country"));
		Select dropd = new Select(w);
		dropd.selectByVisibleText("United States");

		driver.findElement(By.id("inputCity")).sendKeys("abcdcity");
		driver.findElement(By.id("inputAddress1")).sendKeys("abcdaddress1");
		driver.findElement(By.id("inputAddress2")).sendKeys("abcdaddress2");
		driver.findElement(By.id("inputState")).sendKeys("abcdstate");
		driver.findElement(By.id("inputZip")).sendKeys("12345");
		driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();

		String ctext = driver.findElement(By.xpath("//p[contains(text(),'Thanks for contacting us,')]")).getText();

		String etext = "Thanks for contacting us, we will get back to you shortly.";

		// Asserting after submission message
		Assert.assertEquals(ctext, etext);

		System.out.println("Assertion pass");
		// System.out.println("Inside InputFormSubmit Test : " +
		// Thread.currentThread().getId());
		System.out.println("End of Test 3: InputFormSubmit");

	}

	@AfterMethod
	public void driverSetup() {
		if (driver != null) {
			// driver.close();
			driver.quit();

		}
	}

}
