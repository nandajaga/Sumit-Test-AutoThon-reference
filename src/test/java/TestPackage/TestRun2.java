package TestPackage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestRun2 {

	static WebDriver driver;
	
	@BeforeTest()
	public void setup() throws InterruptedException {
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\ab36194\\eclipse-workspace\\TestAutothon\\Drivers\\chromedriver.exe");
	 driver = new ChromeDriver();
	}
	
	
	@Test()
	public void myTest() throws InterruptedException {
	driver.get("https://www.google.com/");
	assertThat("Verify Title", driver.getTitle().equalsIgnoreCase("Google"));
	}
	
	
	
	@AfterTest()
	public void quitTest() throws InterruptedException {
		driver.quit();
	}
	
}
