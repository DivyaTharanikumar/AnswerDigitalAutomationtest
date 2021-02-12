package SeleniumTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class AnsDigiAutomationTest {

	public

	WebDriver driver;
	String driver_path = "C:\\Users\\srifo\\eclipse-workspace\\chromedriver.exe";
	String base_Url = "http://the-internet.herokuapp.com/";
	String LoggedIn_Url = "http://the-internet.herokuapp.com/secure";
	String LoggedOut_Url = "http://the-internet.herokuapp.com/login";
	String expectedTitle = "The Internet";
	String invalid_str = "invalid";
	String valid_user = "tomsmith";
	String valid_pwd = "SuperSecretPassword!";
	SoftAssert softAssert = new SoftAssert();

	@BeforeMethod
	public void beforeMethod() {

		// Initiating driver instance
		driver = new ChromeDriver();

		// Maximising window
		driver.manage().window().maximize();

		// Navigating to URL
		driver.get("http://the-internet.herokuapp.com");
		// Assert URL
		String expectedTitle = "The Internet";
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(driver.getTitle(), expectedTitle);
		System.out.println("Site opened successfully");

	}

	@Test
	public void testInvalidPassword() throws InterruptedException {
		// Test Scenario 1 : Invalid Password
		// Navigate to form Authentication menu
		driver.findElement(By.cssSelector("#content > ul > li:nth-child(21) > a")).click();

		// Enter valid user name invalid password
		driver.findElement(By.cssSelector("#username")).sendKeys(valid_user);
		driver.findElement(By.cssSelector("#password")).sendKeys(invalid_str);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#login > button > i")).click();

		// Assert login failure
		softAssert.assertNotEquals(driver.getCurrentUrl(), LoggedIn_Url);
		System.out.println("Invalid Password! Please try again!");
	}

	@Test
	public void testInvalidUserName() throws InterruptedException {
		// Test Scenario 2 : Invalid UserName
		// Navigate to login page
		driver.findElement(By.cssSelector("#content > ul > li:nth-child(21) > a")).click();

		// Enter invalid user name and valid password
		driver.findElement(By.cssSelector("#username")).sendKeys(invalid_str);
		driver.findElement(By.cssSelector("#password")).sendKeys(valid_pwd);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#login > button > i")).click();

		// Assert login failure
		softAssert.assertNotEquals(driver.getCurrentUrl(), LoggedIn_Url);
		System.out.println("Invalid UserName! Please try again!");
	}

	@Test
	public void testValidLogin() throws InterruptedException {
		// Test Scenario 3 : Valid login credentials
		// Navigate to login page
		driver.findElement(By.cssSelector("#content > ul > li:nth-child(21) > a")).click();

		// Enter valid user name and password
		driver.findElement(By.cssSelector("#username")).sendKeys(valid_user);
		driver.findElement(By.cssSelector("#password")).sendKeys(valid_pwd);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#login > button > i")).click();

		// Assert login success
		softAssert.assertEquals(driver.getCurrentUrl(), LoggedIn_Url);
		System.out.println("Logged in Successfully!");

		// click logout button
		driver.findElement(By.cssSelector("#content > div > a > i")).click();
		Thread.sleep(2000);
		// Assert logout operation
		softAssert.assertEquals(driver.getCurrentUrl(), LoggedOut_Url);
		System.out.println("You are logged out successfully!");
	}

	@Test
	public void testInfiniteScroll() throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.findElement(By.cssSelector("#content > ul > li:nth-child(26) > a")).click();

		System.out.println("Scrolling vertically down..");
		Thread.sleep(2000);
		js.executeScript("window.scroll(0,document.documentElement.scrollHeight)");

		System.out.println("Scrolling vertically down2..");
		Thread.sleep(2000);
		js.executeScript("window.scroll(0,document.documentElement.scrollHeight)");

		System.out.println("Scrolling vertically up");
		Thread.sleep(2000);
		js.executeScript("window.scrollTo(0,0)");
		Thread.sleep(2000);

		WebElement text = driver.findElement(By.cssSelector("#content > div > h3"));
		softAssert.assertEquals(text.getText(), "Infinite Scroll");
		Thread.sleep(2000);
		System.out.println("Infinite Scroll element asserted as " + text.getText());
	}

	@Test
	public void testKeyPresses() throws InterruptedException {
		// Navigate to Key Presses
		driver.findElement(By.cssSelector("#content > ul > li:nth-child(31) > a")).click();

		// Key Press : ESCAPE
		driver.findElement(By.cssSelector("#target")).sendKeys(Keys.ESCAPE);
		Thread.sleep(2000);
		WebElement result1 = driver.findElement(By.id("result"));
		softAssert.assertEquals(result1.getText(), "You entered: ESCAPE");
		System.out.println("Key Press:Escape asserted as " + result1.getText());

		// Key Press : TAB
		driver.findElement(By.cssSelector("#target")).sendKeys(Keys.TAB);
		Thread.sleep(2000);
		WebElement result2 = driver.findElement(By.id("result"));
		softAssert.assertEquals(result2.getText(), "You entered: TAB");
		System.out.println("Key Press:TAB asserted as " + result2.getText());

		// Key Press : ENTER
		driver.findElement(By.cssSelector("#target")).sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		WebElement result3 = driver.findElement(By.id("result"));
		softAssert.assertEquals(result3.getText(), "You entered: ENTER");
		System.out.println("Key Press:Enter asserted as " + result3.getText());

		// Key Press : ARROW_DOWN
		driver.findElement(By.cssSelector("#target")).sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(2000);
		WebElement result4 = driver.findElement(By.id("result"));
		softAssert.assertEquals(result4.getText(), "You entered: DOWN");
		System.out.println("Key Press:Arrow_Down asserted as " + result4.getText());
	}

	@AfterMethod
	public void afterMethod() throws InterruptedException {
		Thread.sleep(1000);
		// closing browser window
		driver.close();
	}

}
